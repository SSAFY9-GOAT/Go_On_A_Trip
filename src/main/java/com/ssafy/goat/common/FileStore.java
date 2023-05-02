package com.ssafy.goat.common;

import com.ssafy.goat.hotplace.UploadFile;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

public class FileStore {

    public String fileDir = "C:\\SSAFY\\workspace\\0goat\\Go_On_A_Trip\\src\\main\\resources\\static\\assets\\img\\userUpload\\";


    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public UploadFile storeFile(Part part) throws IOException {
        String originalFileName = getFilename(part);

        if (!originalFileName.isEmpty()) {
            String storeFileName = createStoreFileName(originalFileName);
            part.write(fileDir + storeFileName);
            return new UploadFile(originalFileName, storeFileName);
        }

        return null;
    }

    private String getFilename(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] split = contentDisp.split(";");
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }
        return "";
    }

    private String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }
}
