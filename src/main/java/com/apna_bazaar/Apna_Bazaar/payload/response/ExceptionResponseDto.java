package com.apna_bazaar.Apna_Bazaar.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDto {
    private String message;
    private String errorType;
    private LocalDateTime timeStamp;
}
