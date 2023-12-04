package Threads;

import Package.*;
import ErrorsPackage.*;
public class TransportSynchronizer {
    private transport_vehicle tv;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public TransportSynchronizer(transport_vehicle value) {
        tv = value;
    }

    public void printPrice() throws InterruptedException {
        double val;
        synchronized(lock) {
            double [] p = tv.getModelPriceMass();
            if (!canPrintPrice()) throw new InterruptedException();
            while (!set)
                lock.wait();
            val = p[current++];
            System.out.println("Print price: " + val);
            set = false;
            lock.notifyAll();
        }
    }

    public void printModel() throws InterruptedException {
        synchronized(lock) {
            String [] s = tv.getModelNameMass();
            if (!canPrintModel()) throw new InterruptedException();
            while (set)
                lock.wait();
            System.out.println("Print model: " + s[current]);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canPrintPrice() {
        return current < tv.getLenModelMass();
    }

    public boolean canPrintModel() {
        return (!set && current < tv.getLenModelMass()) || (set && current < tv.getLenModelMass() - 1);
    }
}

