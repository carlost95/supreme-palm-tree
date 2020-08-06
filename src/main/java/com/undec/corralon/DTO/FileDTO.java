package com.undec.corralon.DTO;

public class FileDTO {
    byte[] file;
    String name;

    public FileDTO() {
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
