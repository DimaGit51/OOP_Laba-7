package ServerNet.ServerPart.SerialServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Package.*;
import Threads.*;
import ErrorsPackage.*;

public class serialServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchModelNameException
    {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;

        try
        {
            serverSocket = new ServerSocket( 4444);
            System.out.println("Соединение с клиентом установлено");
            System.out.println("Запускаем выполнение программы");
        }
        catch (IOException e)
        {
            System.out.println("Could not listen on port: 4444");
            System.exit(-1);
        }

        while (true)
        {
            try
            {
                clientSocket = serverSocket.accept();
                System.out.println("Поступил запрос со стороны клиента.. \n");
            }
            catch (IOException e)
            {
                System.out.println("Accept failed: 4444");
                System.exit(-1);
            }

            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

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

            objectInputStream.close();
            objectOutputStream.close();
            clientSocket.close();
        }
    }
}
