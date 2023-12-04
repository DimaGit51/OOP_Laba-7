package Threads;

import ErrorsPackage.*;
import Package.*;

public class ThreadPrintPricesModels extends Thread{
    private transport_vehicle tv;

    public ThreadPrintPricesModels(transport_vehicle value){
        tv = value;
    }

    public void run(){
//        System.out.println("Start ThreadPrintPricesModels");
        double[] arr = tv.getModelPriceMass();
        for(int i = 0; i < arr.length; i++){
//            System.out.println("(поток ThreadPrintPricesModels)_ ");
            System.out.print("Цена модели: ");
            System.out.println(arr[i]);
        }
        System.out.println("End ThreadPrintPricesModels");
    }
}
