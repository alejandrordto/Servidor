package edu.escuelaing.arem.servidor;



import edu.escuelaing.arem.mapeo.Component;
import edu.escuelaing.arem.mapeo.Mapping;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * clase servidor, es un servidor web basico pero capas de respinder perticiones html y png
 * @author Alejandro Rodriguez
 */
@Component  
public class servidor {
    /**
     * Hilo principal del servidor cuenta con dos sockets, un cliente y un servidor, se asigna el puerto
     * del servidor y se procede a trabajar con la clase cargador.
     * @param args
     * @throws IOException 
     */
    @Mapping("/results")
    //public static void main(String[] args) throws IOException {
    public void ejecucion(){
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
        ExecutorService ex = Executors.newFixedThreadPool(15);
        while (true) {
            
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            Cargador carga = new Cargador(clientSocket);
            ex.execute(carga);
            
        }

    }
}
