package ServerNet.ServerPart.ParallelServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Package.*;
import Threads.*;
import ErrorsPackage.*;

public class ServerThread extends Thread{
    private Socket clientSocket;

    public ServerThread(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    public void run()
    {
        try
        {
            System.out.println("Соединение с клиентом установлено");
            System.out.println("Запускаем выполнение программы");
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            transport_vehicle[] vehicles = (transport_vehicle[]) objectInputStream.readObject();

            System.out.println("Получили массив ТС: \n");
            for (int i = 0; i < vehicles.length; i++)
            {
                System.out.println(vehicles[i].toString());
            }

            objectOutputStream.writeDouble(StaticClass.arithmeticMeanOfModelPrices(vehicles));
            System.out.println("Среднее арифметическое цен моделей = " + StaticClass.arithmeticMeanOfModelPrices(vehicles));

            objectOutputStream.flush();
            System.out.println("Значение передано на сторону клиента...");
        }
        catch (IOException | ClassNotFoundException | NoSuchModelNameException e)
        {
            e.printStackTrace();
        }

    }
}
