package ServerNet.ServerPart.ParallelServer;

import Package.*;
import Threads.*;
import ErrorsPackage.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class parallelServer {
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;

        try
        {
            serverSocket = new ServerSocket( 4444);
        }
        catch (IOException e)
        {
            System.out.println("Could not listen on port: 4444");
            System.exit(-1);
        }

        Socket clientSocket = null;

        while (true)
        {
            try
            {
                clientSocket = serverSocket.accept();
                System.out.println("Поступил запрос со стороны клиента... \n");
            }
            catch (IOException e)
            {
                System.out.println("Accept failed: 4444");
                System.exit(-1);
            }

            Runnable rnbl = new ServerThread(clientSocket);
            Thread thr = new Thread(rnbl);
            thr.start();
        }
    }
}
