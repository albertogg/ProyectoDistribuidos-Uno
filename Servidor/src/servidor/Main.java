/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;
import java.lang.*;

/**
 *
 * @author albertogg & carlos fernandes
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