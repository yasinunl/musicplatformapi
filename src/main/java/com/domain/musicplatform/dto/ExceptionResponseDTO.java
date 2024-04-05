package com.domain.musicplatform.dto;

import lombok.Data;

@Data
public class ExceptionResponseDTO {
    private String message;
    private long timeStamp;
    private int status;

    public ExceptionResponseDTO() {
    }

    public ExceptionResponseDTO(String message, long timeStamp, int status) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.status = status;
    }
}
