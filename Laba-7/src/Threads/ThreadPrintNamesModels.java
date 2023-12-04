package Threads;

import ErrorsPackage.*;
import Package.*;

public class ThreadPrintNamesModels extends Thread{
    private transport_vehicle tv;

    public ThreadPrintNamesModels(transport_vehicle value){
        tv = value;
    }

    public void run(){
//        System.out.println("Start ThreadPrintNamesModels");
        String[] arr = tv.getModelNameMass();
        for(int i = 0; i < arr.length; i++){
//            System.out.print("(поток ThreadPrintNamesModels)_ ");
            System.out.print("Название модели: ");
            System.out.println(arr[i]);
        }
        System.out.println("End ThreadPrintNamesModels");
    }
}
