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
        while (true) {
            
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            String inputu = null;
            while ((inputLine = in.readLine()) != null) {
                try {
                    if (inputLine.startsWith("GET")) {
                        if (!inputLine.startsWith("GET / ")) {
                            inputu = inputLine;
                        }
                    }
                } catch (java.lang.StringIndexOutOfBoundsException e) {
                }
                //System.out.println(inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            if (inputu != null) {
                String[] temp;
                temp = inputu.split(" ");
                String flag = "";
                if (temp[1].endsWith(".html")) {
                    File f = new File(temp[1].substring(1));
                    BufferedReader entrada;

                    try {
                        entrada = new BufferedReader(new FileReader(f));
                        while (entrada.ready()) {
                            flag += entrada.readLine();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (temp[1].endsWith("png")){
                     inputu=("<!DOCTYPE html>"
                    +"<html>"
                    +"<head>"
                    +"<title>Page Title</title>"
                    +"</head>"
                    +"<body>"
                    +"<img src=\""+ temp[1].substring(1) +"\" height=\"200\" width=\"200\" >"
                    + "</body>"
                    + "</html>");
                } else {
                    inputu=("<!DOCTYPE html>"
                    +"<html>"
                    +"<head>"
                    +"</head>"
                    +"<body>"
                   +"Por favor ingrese un recurso valido"
                    +"</body>"
                    + "</html>");
                }
                inputu = flag;
            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + inputu
                    + inputLine;
            out.println(outputLine);
            //out.close();
            //*clientSocket.close();
        }
    }
}
