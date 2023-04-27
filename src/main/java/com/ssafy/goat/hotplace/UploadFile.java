package com.ssafy.goat.hotplace;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;

    @Builder
    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
