package com.busanit501.teamproject2.msy.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class msyMemberDTO {
    private String mid;
    private String mpw;
    private String name;
    private String email;
    private String phone;
}
