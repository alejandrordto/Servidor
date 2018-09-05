/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;

/**
 *
 * @author AsusPC
 */
public class Carga {

    public void carga(Socket clientSocket) throws IOException  {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        String inputu = null;
        String format = null;
        String data = null;
        byte[] bytes = null;
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
        }
        if (inputu != null) {
            String[] temp;
            temp = inputu.split(" ");
            String flag = "";
            if (temp[1].endsWith(".html")) {
                bytes = Files.readAllBytes(new File("./" + temp[1].substring(1)).toPath());
                data = "" + bytes.length;
                format = "text/html";
            } else if (temp[1].endsWith("png")) {
                bytes = Files.readAllBytes(new File("./" + temp[1].substring(1)).toPath());
                data = "" + bytes.length;
                format = "image/png";
            } else {
                inputu = ("<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "</head>"
                        + "<body>"
                        + "Por favor ingrese un recurso valido"
                        + "</body>"
                        + "</html>");
            }
            inputu = flag;
        }
        outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: "
                + format
                + "\r\n"
                + "Content-Length: "
                + data
                + "\r\n\r\n";
        byte[] bite = outputLine.getBytes();
        try {
        byte[] salida = new byte[bytes.length + bite.length];
        for (int i = 0; i < bite.length; i++) {
            salida[i] = bite[i];
        }
        for (int i = bite.length; i < bite.length + bytes.length; i++) {
            salida[i] = bytes[i - bite.length];
        }
        clientSocket.getOutputStream().write(salida);
        } catch (java.lang.NullPointerException e){
            
        }
         clientSocket.close();

    }

}
