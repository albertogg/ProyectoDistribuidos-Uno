/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albertogg
 */
public class Agente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket s1 = new Socket("127.0.0.1", 5432);


            ObjectOutputStream oos = new ObjectOutputStream(s1.getOutputStream());
            oos.writeObject("hola server");

            
            ObjectInputStream ois = new ObjectInputStream(s1.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            
            
            oos.close();
            ois.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectException connExc) {
            System.err.println("Imposible establecer la conexion");
        } catch (IOException e) {
        }
    }
}
