package Threads;

import ErrorsPackage.*;
import Package.*;

public class RunnablePrintPricesModels implements  Runnable{
    private TransportSynchronizer tv;

    public RunnablePrintPricesModels(TransportSynchronizer value)
    {
        tv = value;
    }

    public void run(){
        while (tv.canPrintPrice()) {
            try {
                tv.printPrice();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
