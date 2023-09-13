package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Sever {
    public Sever() throws IOException {
        ArrayList<severThread> threadList = new ArrayList<>();
        try (ServerSocket serverSocket = new ServerSocket(2512)) {
            System.out.println("Sever is running...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connect from " + socket);
                severThread thread = new severThread(socket);
                threadList.add(thread);

                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public static void main(String[] args) {
        try {
            new Sever();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
