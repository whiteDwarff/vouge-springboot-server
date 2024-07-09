package com.vogue.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class BaseFileUtil {

  @Value("${server.file.path}")
  private String filePath;

  public List<HashMap<String, Object>> fileUploadUtil(MultipartFile[] files, String uploadPath) throws Exception {

    List<HashMap<String, Object>> result  =  new ArrayList<>();

    for (MultipartFile file : files) {
      // 저장시간
      String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      // 실제 파일명
      String originalFileName = file.getOriginalFilename();
      // 파일의 확장자
      String ext = extractExt(originalFileName);
      // 서버에 저장되는 파일명
      String saveFileName = generateUUID() + "." + ext;
      // 저장경로
      String uploadFilePath = filePath + uploadPath + "/" + saveFileName;

      log.info("@@@@@@@@ : " + uploadFilePath);

      File uploadFile = new File(uploadFilePath);

      // 해당 경로에 폴더가 없다면 폴더 생성
      if(!uploadFile.exists())  uploadFile.mkdirs();

      Path path = Paths.get(uploadFilePath).toAbsolutePath();

      file.transferTo(path.toFile());

      HashMap<String, Object> map = new HashMap<>();
      map.put("filePath", uploadPath + File.separator +  saveFileName);
      map.put("saveFileName", saveFileName);
      map.put("originalFileName", originalFileName);
      map.put("fileExt", ext);
      map.put("fileSize", file.getSize());
      map.put("insDt", currentTime);

      result.add(map);
    }
    return result;
  }
  // 파일 확장자 반환
  public String extractExt(String fileName) {
    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }

  public String generateUUID() {
    UUID uuid = UUID.randomUUID();
    return String.valueOf(uuid);
  }

}
