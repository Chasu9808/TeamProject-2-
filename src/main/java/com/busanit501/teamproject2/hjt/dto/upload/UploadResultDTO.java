package com.busanit501.teamproject2.hjt.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String uuid;
    private String fileName;
    private boolean imgCheck;

    public String getLink() {
        if (imgCheck) {
            return "s_" + uuid + "_" + fileName;
        } else {
            return uuid + "_" + fileName;
        }
    }
}
