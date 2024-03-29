/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import rmi.InterfaceCliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import persistencia.LectorXML;

/**
 *
 * @author albertogg & carlos fernandes
 */
public class ImplementacionCliente
        extends UnicastRemoteObject implements InterfaceCliente {

    public int hola;
    public ArrayList<String> listado11 = new ArrayList<String>();

    public ImplementacionCliente() throws RemoteException {
    }

    @Override
    public ArrayList<String> bolas1(int hola) throws RemoteException {
        try {
            this.hola = hola;
            System.out.println(hola);

            switch (hola) {
                case 0:

                    // Utilizamos un script en el servidor el cual nos busca 
                    // en el directorio donde se encuetra archivos con el nombre
                    // del xml para asi poder mandarlo a leer.
                    Runtime rt = Runtime.getRuntime();
                    Process ps = rt.exec("./script1.sh");
                    BufferedReader input = new BufferedReader(new InputStreamReader(ps.getInputStream()));

                    String line = null;
                    ArrayList<String> listadoz = new ArrayList<String>();

                    //Ciclo para leer todas las lineas de la salida standard.
                    while ((line = input.readLine()) != null) {
                        //                System.out.println(line);

                        listadoz.add(line);

                    }

                    // Variable utilizada para imprimir el status arrojado por la
                    // salida standard del terminal.
                    int exitval = ps.waitFor();
                    System.out.println("valor de salida: " + exitval);

                    // Llamamos a leer xml pasandole como parametro el nombre
                    // del xml y nos regresa la lista, la cual mandaremos al 
                    // cliente con toda la informacion.
                    LectorXML lxml = new LectorXML();
                    Iterator ii = listadoz.iterator();
                    while (ii.hasNext()) {
                        String dd = ii.next().toString();
                        System.out.println(dd);
                        lxml.lecturaXML(dd);
                    }

                    listado11 = lxml.listado;
//                    System.out.println(listado11);

            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Retornamos la lista al cliente.
        return (listado11);
    }
}
