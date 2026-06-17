package com.dubinchin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
