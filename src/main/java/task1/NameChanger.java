package task1;

import task1.MyThread;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NameChanger {

    public void changeName(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter directory");

        String directory = myObj.next();

        File location = new File(directory);

        int coreCount = Runtime.getRuntime().availableProcessors();


        ExecutorService threadExecutor = Executors.newCachedThreadPool();

        for (int i = 0; i < location.listFiles().length; i++){
            System.out.println(location.listFiles()[i].getName());
            threadExecutor.execute(new MyThread(directory, Integer.toString(i+1), location.listFiles()[i]));

        }
        threadExecutor.shutdown();
    }


}
