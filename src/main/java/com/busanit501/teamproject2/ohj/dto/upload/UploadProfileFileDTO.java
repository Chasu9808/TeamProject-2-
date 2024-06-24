package com.busanit501.teamproject2.ohj.dto.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadProfileFileDTO {
    private MultipartFile file;
}
