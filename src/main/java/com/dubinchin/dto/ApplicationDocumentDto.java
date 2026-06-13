package com.dubinchin.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDocumentDto {
    private String id;
    private String name;
    private String type;
    private String url;
    private String uploadedAt;
}
