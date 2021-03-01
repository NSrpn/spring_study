package com.nsrpn.app.files;

import com.nsrpn.app.entities.BaseEntity;
import com.nsrpn.app.entities.Files;
import com.nsrpn.app.storage.DBFilesStorage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class DBFileStorage implements FileStorage {
  @Override
  public void saveFile(BaseEntity entity, MultipartFile file) throws IOException {
    if (file != null) {
      Files storeFile = new Files(entity);
      storeFile.setFileData(Base64.getEncoder().encodeToString(file.getBytes()));
      storeFile.setTitle(file.getOriginalFilename());
      DBFilesStorage storage = new DBFilesStorage();
      storage.save(storeFile);
    }
  }

  @Override
  public byte[] getFile(BaseEntity entity) {
    DBFilesStorage storage = new DBFilesStorage();
    Files file = storage.getByEntity(entity.getClass().getName(), entity.getId());
    return file != null ? Base64.getDecoder().decode(file.getFileData()) : null;
  }
}
