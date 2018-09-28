package edu.escuelaing.CLiente;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author estudiante
 * 
 **/
public class client {
    public static Integer threads= 4;
    public static Integer request= 4;

    
    public static void main(String[] args) throws IOException {
        
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        while (request>0){
            executor.execute(new URLReader(args));
            request-=1;
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
       
    }
    
    
    
}
