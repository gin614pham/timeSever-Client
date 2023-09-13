package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client extends Thread {
    DataInputStream in = null;
    DataOutputStream out = null;
    Socket socket = null;
    Client_GUI gui = null;
    Boolean isStop = false;

    public Client (String ip, int port) throws UnknownHostException, IOException {
        this.socket = new Socket("127.0.0.1", 2512);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        gui = new Client_GUI();
        gui.btnStop.addActionListener(e -> {
            try {
                out.writeUTF("stop");
                out.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        gui.btnRun.addActionListener(e -> {
            try {
                out.writeUTF("continue");
                out.flush();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
           
        });

        gui.frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                 try {
                int choice = JOptionPane.showConfirmDialog(gui.frame, "Do you want to exit the application?", "Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    isStop = true;
                    // If the user confirms, exit the application
                    out.writeUTF("OUT");
                    out.flush();          
                    
                    String msg = in.readUTF();
                    if (msg.equals("stop")) {
                        System.exit(0);
                    }
                }
                // Send data to the server before closing
            } catch (IOException ex) {
                ex.printStackTrace();
                // Handle the exception
            } 
            }
        });

        this.start();
    }

    @Override
    public void run() {
        while (!isStop) {
            try {
                out.writeUTF("time");
                out.flush();
                String time = in.readUTF();
                System.out.println(time);
                gui.textFTime.setText(time);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client("127.0.0.1", 1032);
    }
}
