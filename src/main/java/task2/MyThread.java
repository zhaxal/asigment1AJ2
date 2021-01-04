package task2;

import java.io.File;
import java.io.FileNotFoundException;

public class MyThread extends Thread{

    final Searcher searcher;
    File file;
    String searchStr;


    public MyThread(File file, String searchStr){
        this.file = file;
        this.searchStr = searchStr;
        searcher = new Searcher();

    }




    public void run(){
        synchronized (searcher){
            try {
                searcher.findMatchSimple(file,searchStr);

            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
