package Threads;
import ErrorsPackage.*;
import Package.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class FileRunnablePrintBrand implements Runnable{
    private transport_vehicle tv;

    public FileRunnablePrintBrand(String nameFile, BlockingQueue bq) {
        try {
            FileReader read = new FileReader(nameFile);
            BufferedReader bufStream = new BufferedReader(read);
            String brand = bufStream.readLine();
            tv = new Car(brand, 2);
            bq.add(tv);
        } catch (IOException e) {
            System.out.println(e);
        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }


    public void run() {
        System.out.println(tv.getBrand());
    }
}
