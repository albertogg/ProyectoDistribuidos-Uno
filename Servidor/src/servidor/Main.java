/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

/**
 *
 * @author albertogg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private int port = 5432;
    private ServerSocket server;
    private boolean boleana = true;

    // Constructor.
    public Main() {
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Metodo encargado de escuchar aceptando las conexiones entrantes,
    // provenientes de los agentes.
    public void conectorServidor() {
        System.out.println("Encendido y escuchando... ");

        while (boleana) {
            try {
                Socket s1 = server.accept();
                new ManejadorDeConexion(s1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ConexionHelper ch = new ConexionHelper();
        ch.conexionRMI();

        Main nuevaConn = new Main();
        nuevaConn.conectorServidor();
    }
}
// Implementa de la clase Runnable para utilizar hilos
// Maneja las conexiones entrantes en base a hilos.
//class ManejadorDeConexion1 implements Runnable {
//    
//    private Socket socket;
//    
//    // Constructor, Inicializamos el hilo dentro de el.
//    public ManejadorDeConexion1(Socket socket) {
//        this.socket = socket;
//        
//        Thread hilo = new Thread(this);
//        hilo.start();
//    }
//    
//    
//    @Override
//    public void run() {
//        try {
//            // Aqui ira el codigo con un switch/case para ejecutar una accion.
//            // accion de guardar, leer, escribir, dependiendo del mensaje
//            // que se reciba.
//            
//            
//            // Recibimos un mensaje enviado por la red.
//            ObjectInputStream ois = new ObjectInputStream(
//                                                    socket.getInputStream());
//            ArrayList mensaje = (ArrayList) ois.readObject();
//            System.out.println("Mensaje Recibido: " + mensaje);
//            
//            
//            // Leemos un mensaje enviado desde el agente.
//            ObjectOutputStream oos = new ObjectOutputStream(
//                                                    socket.getOutputStream());
//            oos.writeObject("Te conectaste al Servidor.");
//            
//            // Cerramos sockets.
//            ois.close();
//            oos.close();
//            socket.close();
//            
//            System.out.println("Escuchando.. ");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }    
//    }
//            
//}
