package com.ideamerry.repair.entity;

import lombok.Data;

@Data
public class FileUploadEntity {
    private String original;
    private String size;
    private String state;
    private String title;
    private String type;
    private String url;
    private String thumbnails;
}
