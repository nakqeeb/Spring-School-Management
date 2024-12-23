package com.nakqeeb.sms.dto;

import lombok.Data;

@Data
public class UpdateClassDto {
    private String className;
    private Long teacherId;
    private Long subjectId;
    private String classRoomNumber;
}
