package com.dubinchin.exception;

import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String error;
    private LocalDateTime timestamp;
}
