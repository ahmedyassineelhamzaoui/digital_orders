package firebase.files.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStoreService {
    Object upload(MultipartFile multipartFile);

    Object download(String fileName) throws IOException;

    String extractPath(String url) ;

}
