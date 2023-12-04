package Threads;
import ErrorsPackage.*;
import Package.*;
import java.util.concurrent.locks.ReentrantLock;
public class ReentrantLockPrintNamesModels implements Runnable{
    private transport_vehicle tv;
    private ReentrantLock locker;

    public ReentrantLockPrintNamesModels(transport_vehicle value, ReentrantLock locker) {
        tv = value;
        this.locker = locker;
    }

    public void run() {
        try {
            locker.lock();
            String[] arr = tv.getModelNameMass();
            for (String s : arr) {
                System.out.print("Название модели: ");
                System.out.println(s);
            }
        } finally {
            locker.unlock();
        }
    }
}
