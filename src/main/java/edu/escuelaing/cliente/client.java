package edu.escuelaing.CLiente;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *clase que actua como el cliente aws.
 * @author Alejandro Rodriguez del Toro
 * 
 **/
public class client {
    public static Integer hilos;
    public static Integer peticiones;

    /**
     * metodo principa del cliente, recibe argumentos en el cual estara
     * el link, puede tener o no el recurso que queramos buscar.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        hilos=25;
        ExecutorService executor = Executors.newFixedThreadPool(hilos);
        while (hilos>0){
            executor.execute(new URLReader(args));
            hilos-=1;
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
       
    }
    
    
    
}
