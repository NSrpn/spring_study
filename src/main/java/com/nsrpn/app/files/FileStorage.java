package com.nsrpn.app.files;

import com.nsrpn.app.entities.BaseEntity;
import com.nsrpn.app.entities.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorage {
  void saveFile(BaseEntity entity, MultipartFile file) throws IOException;

  Files getFile(BaseEntity entity);

  byte[] getFileDataByte(Files file);
}
