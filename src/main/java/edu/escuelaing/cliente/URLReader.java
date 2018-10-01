package edu.escuelaing.CLiente;


import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *leedor del URL.
 * @author Alejnadro Rodriguez del Toro
 */
public class URLReader implements Runnable {

    String[] args;
    /**
     * creador del url recibe sus argumentos.
     * @param args 
     */
    public URLReader(String[] args) {
        this.args = args;
    }
    
    @Override
    public void run() {
        
        try {
            URL url = new URL(this.args[0]);
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                String inputLine = null;
                while ((inputLine = reader.readLine()) != null) {
                    //System.out.println(inputLine);
                }
            } catch (IOException x) {
                System.err.println(x);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
