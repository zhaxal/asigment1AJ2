package task2;

import java.io.File;
import java.io.FileNotFoundException;

public class RunnableThread extends Searcher implements Runnable {
    File file;
    String searchStr;

    public RunnableThread(File file, String searchStr){
        this.file = file;
        this.searchStr = searchStr;
    }

    @Override
    public void run() {
        try {
            findMatch(file,searchStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
