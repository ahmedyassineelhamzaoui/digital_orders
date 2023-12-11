package firebase.files.controller;

import com.app.models.ContractFile;
import com.app.services.FilesService;
import firebase.files.service.FileStoreService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/filesave")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FilesStoreController {

    private final FileStoreService fileStoreService;

    private FilesService filesServicee;
    private final Logger logger = LoggerFactory.getLogger(FilesStoreController.class);


    @GetMapping
    public String hello() {
        return "welcome to firebase storage";
    }



    @PostMapping("/images/upload/{id}")
    public Object upload(@PathVariable UUID id, @RequestParam("file") MultipartFile multipartFile) {
        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());

        Object url =  fileStoreService.upload(multipartFile);

        String path =  url.toString();


        ContractFile contractFile = new ContractFile();
        contractFile.setId(id);
        contractFile.setPath(path.split(",")[1].split("=")[1].trim()+"?alt=media");;
        filesServicee.SaveFilePath(contractFile);
        System.out.println(url);
        System.out.println();
        return  url;
    }

    @PostMapping("/images/download/{fileName}")
    public Object download(@PathVariable String fileName) throws IOException {
        logger.info("HIT -/download | File Name : {}", fileName);
        return fileStoreService.download(fileName);
    }

}
