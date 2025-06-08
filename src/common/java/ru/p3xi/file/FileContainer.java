package ru.p3xi.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileContainer {
    private final String filePath;
    public FileContainer(String filePath) throws NullPointerException {
        if (filePath == null) throw new NullPointerException();
        this.filePath = filePath;
    }

    public String readFile() throws FileException {
        FileInputStream obj;
        String content;
        try {
            obj = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new FileException("Файл "+filePath+" не найден");
        }
        BufferedInputStream bis = new BufferedInputStream(obj);
        try {
            content = new String(bis.readAllBytes());
            bis.close();
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
        try {
            obj.close();
        } catch (Exception e) { }
        return content;
    }

    public void writeFile(String content) throws FileException {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath, false);
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }
}
