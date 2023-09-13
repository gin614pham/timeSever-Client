package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class severThread extends Thread {
    ArrayList<Socket> socketList = new ArrayList<>();
    Socket socket = null;
    Boolean isPause = false;

    public severThread(Socket socket){
        this.socket = socket;
    }

    public void addSocket(Socket socket){
        socketList.add(socket);
    }

    public ArrayList<Socket> getSocketList(){
        return socketList;
    }

    public void setSocketList(ArrayList<Socket> socketList){
        this.socketList = socketList;
    }

    public void pause(){
        this.isPause = true;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while (true) {
            	 if (socket.isClosed()) {
                     return;
                 }
                String msg = null;
                msg = in.readUTF();
                switch (msg) {
                  case "time": break;
                  case "OUT": {
                    System.out.println("Client " + socket + " disconnected");
                    out.writeUTF("stop");
                    out.flush();
                    socketList.remove(socket);
                    socket.close();
                    pause();
                    break;
                    
                  }
                  case "stop": {
                    pause();
                    break;
                  }
                  case "continue": {
                    this.isPause = false;
                    break; }
                }
                if (isPause) {
                    continue;
                }
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss E dd/MM/yyyy");
                String time = sdf.format(date);

                out.writeUTF(time);
                out.flush();
                sleep(1000);
               
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
