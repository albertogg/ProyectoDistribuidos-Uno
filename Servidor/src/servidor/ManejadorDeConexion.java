/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author albertogg
 */
public class ManejadorDeConexion implements Runnable {
    
    private Socket socket;
    
    // Constructor, Inicializamos el hilo dentro de el.
    public ManejadorDeConexion(Socket socket) {
        this.socket = socket;
        
        Thread hilo = new Thread(this);
        hilo.start();
    }
    
    
    @Override
    public void run() {
        try {
            
            // Recibimos un mensaje enviado por la red.
            ObjectInputStream ois = new ObjectInputStream(
                                                    socket.getInputStream());
            ArrayList mensaje = (ArrayList) ois.readObject();
//            System.out.println("Mensaje Recibido: " + mensaje);
            
            //
            EscribirXML ex = new EscribirXML();
            ex.writeXMLFile(mensaje);
            
            
            // Leemos un mensaje enviado desde el agente.
            ObjectOutputStream oos = new ObjectOutputStream(
                                                    socket.getOutputStream());
            oos.writeObject("Te conectaste al Servidor.");
            
            // Cerramos sockets.
            ois.close();
            oos.close();
            socket.close();
            
            System.out.println("Escuchando.. ");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }    
    }
            
}
