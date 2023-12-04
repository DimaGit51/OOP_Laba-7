package Threads;
import ErrorsPackage.*;
import Package.*;
public class RunnablePrintBrand implements Runnable{
    private transport_vehicle tv;

    public RunnablePrintBrand(transport_vehicle value)
    {
        tv = value;
    }


    public void run() {
        System.out.print("Бренд (марка): ");
        System.out.println(tv.getBrand());
    }
}
