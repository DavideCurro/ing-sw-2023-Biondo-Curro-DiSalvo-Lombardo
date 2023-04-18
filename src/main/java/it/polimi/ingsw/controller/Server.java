package it.polimi.ingsw.controller;
import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws  IOException{
        try {
            int port = 2000;
            ServerSocket ss = new ServerSocket(port);
            try {
                ss.setSoTimeout(15000);
            }catch(SocketException e){
                e.printStackTrace();

            }
            while (true) {

                Socket s = ss.accept();
                System.out.println("Client connected");

                InputStreamReader in = new InputStreamReader(s.getInputStream());
                BufferedReader bf = new BufferedReader(in);
                String str = bf.readLine();
                System.out.println("client said:" + str);

                OutputStream os = s.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write("risposta del server");
                System.out.println("Server said: risposta del server");
                bw.flush();

            }
        }catch(Exception e){
            System.out.println("Error:" + e);
            e.printStackTrace();
        }
    }
}
