package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Searcher {
    private static Integer SLEEP_MILLS = 1000;

    public void findMatch(File file, String searchStr) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        boolean success = false;
        while(scan.hasNext()){
            String line = scan.nextLine().toLowerCase().toString();
            if(line.contains(searchStr)){
                success = true;
                System.out.println(file.getName()+" has: "+line);
            }
        }

        if(!success){
            findMatchHard(file, searchStr);
        }
    }

    public void findMatchSimple(File file, String searchStr) throws FileNotFoundException, InterruptedException {

        Scanner scan = new Scanner(file);
        while(scan.hasNext()){
            String line = scan.nextLine().toLowerCase();
            if(line.contains(searchStr)){

                System.out.println(file.getName()+" has: "+line);
            }
        }
        Thread.sleep(SLEEP_MILLS);
    }

    public void findMatchHard(File file,String searchStr){
        String[] splited = searchStr.split("\\s+");
        ExecutorService threadExecutor = Executors.newCachedThreadPool();

        for (int i = 0; i < splited.length; i++){
            threadExecutor.execute(new MyThread(file,splited[i]));
        }
        threadExecutor.shutdown();

    }

    public void initialize() throws InterruptedException {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter directory");

        String directory = myObj.nextLine();

        System.out.println("Enter search string");

        String searchStr = myObj.nextLine();

        int coreCount = Runtime.getRuntime().availableProcessors();

        ArrayList<File> filesList = createFileList(directory);

        ArrayBlockingQueue<RunnableThread> threads = new ArrayBlockingQueue<>(coreCount-1);

        for (int i = 0; i < filesList.size(); i++){
            threads.put(new RunnableThread(filesList.get(i),searchStr));
            threads.take().run();

        }


    }

    private ArrayList<File> createFileList(String directory){
        File location = new File(directory);

        return new ArrayList<>(Arrays.asList(location.listFiles()));
    }
}

