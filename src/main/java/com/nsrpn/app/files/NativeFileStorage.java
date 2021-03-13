package com.nsrpn.app.files;

import com.nsrpn.app.entities.BaseEntity;
import com.nsrpn.app.entities.Files;
import com.nsrpn.app.storage.DBFilesStorage;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class NativeFileStorage implements FileStorage {

  private final String folder;

  public NativeFileStorage(String destFolder) {
    folder = destFolder;
  }
  @Override
  public void saveFile(BaseEntity entity, MultipartFile file) throws IOException {
    if (file != null) {
      String path = String.format("%s\\%s\\%s", folder, entity.getClass().getName(), entity.getId().toString());
      File dir = new File(path);
      if (!dir.exists())
        dir.mkdir();

      FileUtils.writeByteArrayToFile(new File(path + "\\" + file.getOriginalFilename()), file.getBytes());
      Files storeFile = new Files(entity);
      storeFile.setFileData(path + "\\" + file.getOriginalFilename());
      storeFile.setTitle(file.getOriginalFilename());
      DBFilesStorage storage = new DBFilesStorage();
      storage.save(storeFile);
    }
  }

  @Override
  public Files getFile(BaseEntity entity) {
    DBFilesStorage storage = new DBFilesStorage();
    Files fileEntity = storage.getByEntity(entity.getClass().getName(), entity.getId());
    return (fileEntity != null && (new File(fileEntity.getFileData()).exists())) ? fileEntity : null;
  }

  @Override
  public byte[] getFileDataByte(Files file) {
    {
      File fileData = new File(file.getFileData());
      if (fileData.exists()) {
        try {
          return FileUtils.readFileToByteArray(fileData);
        } catch (IOException e) {
          return null;
        }
      }
    }
    return null;
  }
}
