package it.polimi.ingsw.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            int port = 2000;
            String host = "local host";
            InetAddress address = InetAddress.getByName(host);
            Socket s = new Socket(address, port);
            System.out.println("Connection with server");

            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);
            String str = bf.readLine();
            System.out.println("server said:" + str);

            OutputStream os = s.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("risposta del client");
            System.out.println("client said: risposta del client");
            bw.flush();







            s.close();

        }catch(Exception e){
            System.out.println("Error: "+e);
            e.printStackTrace();
        }






    }
}
