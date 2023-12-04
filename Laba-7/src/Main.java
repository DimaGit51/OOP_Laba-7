import Package.*;
import Threads.*;
import ErrorsPackage.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        transport_vehicle car = new Car("Carsing", 10);

//        //Задание 1
//        System.out.println("----------Task1----------");
//        Thread thrNames1 = new ThreadPrintNamesModels(car);
//        Thread thrPrices1 = new ThreadPrintPricesModels(car);
//
//        thrNames1.setPriority(Thread.MAX_PRIORITY);
//        thrPrices1.setPriority(Thread.MIN_PRIORITY);
//
//        thrNames1.start();
//        thrPrices1.start();
//
//        //Задание 2
//        System.out.println("----------Task2----------");
//        TransportSynchronizer transport = new TransportSynchronizer(car);
//        RunnablePrintNamesModels runNames = new RunnablePrintNamesModels(transport);
//        RunnablePrintPricesModels runPrices = new RunnablePrintPricesModels(transport);
//        Thread thrNames2 = new Thread(runNames);
//        Thread thrPrices2 = new Thread(runPrices);
//        thrNames2.start();
//        thrPrices2.start();
//
//        //Задание 3
//        System.out.println("----------Task3----------");
//        ReentrantLock lck = new ReentrantLock();
//        ReentrantLockPrintNamesModels rtlNames = new ReentrantLockPrintNamesModels(car,lck);
//        ReentrantLockPrintPricesModels rtlPrices = new ReentrantLockPrintPricesModels(car,lck);
//        Thread thrNames3 = new Thread(rtlNames);
//        Thread thrPrices3 = new Thread(rtlPrices);
//        thrPrices3.start();
//        thrNames3.start();
//
//        //Задание 4
//        System.out.println("----------Task4----------");
//        transport_vehicle [] trs = new transport_vehicle[4];
//        trs[0] = new Car("brandCar",5);
//        trs[1] = new Bike("brandBike",5);
//        trs[2] = new Moped("brandMoped",5);
//        trs[3] = new Scooter("brandScooter",5);
//
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        for (int i = 0; i < trs.length; i++)
//        {
//            RunnablePrintBrand brand = new RunnablePrintBrand(trs[i]);
//            executor.submit(brand);
//        }
//        executor.shutdown();
//
//        //Задание 5
//        System.out.println("----------Task5----------");
//        String[] files = new String[]{"file_0.txt", "file_1.txt", "file_2.txt", "file_3.txt", "file_4.txt"};
//        ArrayBlockingQueue queue = new ArrayBlockingQueue(5);
//        for (String file : files) {
//            FileRunnablePrintBrand brand = new FileRunnablePrintBrand(file, queue);
//            Thread thread = new Thread(brand);
//            thread.start();
//        }
//        for (int i = 0; i < files.length; i++) {
//            System.out.println(queue.take());
//        }
    }
}
