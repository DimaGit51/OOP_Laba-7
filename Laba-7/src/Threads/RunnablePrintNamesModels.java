package Threads;
import ErrorsPackage.*;
import Package.*;
public class RunnablePrintNamesModels implements  Runnable{
    private TransportSynchronizer tv;
    public RunnablePrintNamesModels(TransportSynchronizer value)
    {
        tv = value;
    }

    public void run(){
        while (tv.canPrintModel()) {
            try {
                tv.printModel();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
