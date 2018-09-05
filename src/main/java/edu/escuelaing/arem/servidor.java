package edu.escuelaing.arem;

import java.awt.Image;
import java.net.*;
import java.io.*;
import javax.swing.ImageIcon;

public class servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Integer PORT;
        try {
            PORT = new Integer(System.getenv("PORT"));
        } catch (Exception e) {
            PORT = 35000;
        }
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        //while (true) {
            
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            Carga carga = new Carga();
            carga.carga(clientSocket);
            
        }

    //}
}
