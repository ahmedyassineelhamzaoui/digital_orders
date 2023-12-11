package com.app.services.impl;

import com.app.models.ContractFile;
import com.app.repositories.FilesRepository;
import com.app.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesRepository filesRepository;



    public void SaveFilePath(ContractFile file){
        filesRepository.save(file);
        return ;
    }
}
