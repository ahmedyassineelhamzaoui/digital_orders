package firebase.files.service.impl;

import firebase.files.service.FileStoreService;
import firebase.utils.config.Const.Constants;
import firebase.utils.helper.FilesManager;
import firebase.utils.helper.ResponseHandler;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FileStorageServiceImpl implements FileStoreService {


    private final FilesManager filesManager;
    private final ResponseHandler responseHandler;

    @Override
    public Object upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(filesManager.getExtension(fileName));  // to generated random string values for file name.

            File file = filesManager.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = filesManager.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return responseHandler.sendResponse("Successfully Uploaded !", TEMP_URL);                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.sendResponse("500", e, "Unsuccessfully Uploaded!");
        }

    }

    @Override
    public Object download(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(filesManager.getExtension(fileName));     // to set random strinh for destination file name
        String destFilePath = Constants.DOWNLOAD_PATH + destFileName;                                         // to set destination file path

        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(Constants.SERVICE_ACCOUNT_KEY_PATH));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of(Constants.BUCKET_NAME, fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return responseHandler.sendResponse("200", "Successfully Downloaded!");
    }
}
