package ServerNet.ClientPart;

import Package.*;
import Threads.*;
import ErrorsPackage.*;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException{
        transport_vehicle car = new Car("Carsing", 5);
        transport_vehicle bike = new Bike("Biking", 5);

        System.out.println("Создание и Вывод car:");
        StaticClass.printNameModels(car);
        StaticClass.printPriseModels(car);
        System.out.println("");
        System.out.println("Создание и Вывод bike:");
        StaticClass.printNameModels(bike);
        StaticClass.printPriseModels(bike);

        transport_vehicle[] tr = new transport_vehicle[]{car, bike};

        Socket socket = null;
        ObjectOutputStream socketOutputStream = null;
        ObjectInputStream socketInputStream = null;

        try
        {
            socket = new Socket("localhost", 4444);
            socketOutputStream = new ObjectOutputStream(socket.getOutputStream());
            socketInputStream = new ObjectInputStream(socket.getInputStream());

        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
//            e.printStackTrace();
            System.exit(1);
        }

        socketOutputStream.writeObject(tr);

        System.out.println("Массивы моделей: \n");
        for (int i = 0; i < tr.length; i++)
        {
            System.out.println(tr[i].toString());
        }

        socketOutputStream.flush();

        System.out.println("Среднее арифметическое цен моделей: " + socketInputStream.readDouble());

        socketOutputStream.close();
        socketInputStream.close();
        socket.close();
    }
}
