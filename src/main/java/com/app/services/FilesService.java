package com.app.services;

import com.app.models.ContractFile;

public interface FilesService {

    public default void SaveFilePath(ContractFile file){}
}
