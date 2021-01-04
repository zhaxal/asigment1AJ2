package task1;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyThread implements Runnable {
    String directory;
    String filename;
    File file;

    public MyThread(String directory, String filename, File file){
        this.directory = directory;
        this.filename = filename;
        this.file = file;
    }
    @Override
    public void run() {
        try{
        filename = filename + "." + FilenameUtils.getExtension(file.getName());
        System.out.println(filename);

        Path source = Paths.get(file.getAbsolutePath());

        Files.move(source, source.resolveSibling(filename));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
