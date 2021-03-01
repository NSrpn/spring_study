package com.nsrpn.app.files;

import com.nsrpn.app.utils.Consts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStorageConfiguration {

  private FileStorage storage;

  @Value("${fileStorage.type}")
  private String fileStorageType;

  @Value("${fileStorage.folder}")
  private String fileStorageFolder;

  public FileStorage getFileStorage() {
    if (storage == null) {
      if (fileStorageType.equals(Consts.App.FileStorage.DATABASE.name()))
        storage = new DBFileStorage();
      else if (fileStorageType.equals(Consts.App.FileStorage.FILE.name())) {
        if (fileStorageFolder != null)
          storage = new NativeFileStorage(fileStorageFolder);
      } else
        throw new RuntimeException("File storage is not setup");

    }
    return storage;
  }

}