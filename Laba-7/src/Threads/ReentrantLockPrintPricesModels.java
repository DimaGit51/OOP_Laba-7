package Threads;
import ErrorsPackage.*;
import Package.*;
import java.util.concurrent.locks.ReentrantLock;
public class ReentrantLockPrintPricesModels implements Runnable{
    private transport_vehicle tv;
    private ReentrantLock locker;

    public ReentrantLockPrintPricesModels(transport_vehicle value, ReentrantLock locker) {
        tv = value;
        this.locker = locker;
    }

    public void run() {
        try {
            locker.lock();
            double[] arr = tv.getModelPriceMass();
            for (double s : arr) {
                System.out.print("Цена модели: ");
                System.out.println(s);
            }
        } finally {
            locker.unlock();
        }
    }
}
