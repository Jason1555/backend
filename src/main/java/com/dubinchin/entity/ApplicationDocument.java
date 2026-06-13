package com.dubinchin.entity;

import java.time.LocalDateTime;

import com.dubinchin.entity.enums.DocumentType;

import jakarta.persistence.*;

@Entity
@Table(name = "application_documents")
public class ApplicationDocument {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType type;

    private String url;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "application_id",
            nullable = false
    )
    private Application application;

    public ApplicationDocument() {
    }

    public ApplicationDocument(String id, String name, DocumentType type, String url, String content,
            LocalDateTime uploadedAt, Application application) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.content = content;
        this.uploadedAt = uploadedAt;
        this.application = application;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
