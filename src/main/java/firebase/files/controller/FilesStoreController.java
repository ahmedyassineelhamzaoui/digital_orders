package firebase.files.controller;

import firebase.files.service.FileStoreService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/filesave")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FilesStoreController {

    private final FileStoreService fileStoreService;
    private final Logger logger = LoggerFactory.getLogger(FilesStoreController.class);


    @GetMapping
    public String hello() {
        return "welcome to firebase storage";
    }



    @PostMapping("/images/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return fileStoreService.upload(multipartFile);
    }

    @PostMapping("/images/download/{fileName}")
    public Object download(@PathVariable String fileName) throws IOException {
        logger.info("HIT -/download | File Name : {}", fileName);
        return fileStoreService.download(fileName);
    }

}
