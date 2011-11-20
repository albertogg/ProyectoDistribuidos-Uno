/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import persistencia.EscribirXML;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import persistencia.LectorXML;

/**
 *
 * @author albertogg & carlos fernandes
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

            // Guardamos la data recolectada del Agente
            EscribirXML ex = new EscribirXML();
            ex.writeXMLFile(mensaje);
            System.out.println(mensaje);
//            LectorXML lx = new LectorXML();
//            lx.lecturaXML();


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
