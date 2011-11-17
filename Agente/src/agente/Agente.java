/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

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

            // Objeto de tipo LectorXML para poder ejecutar la lectura del XML
            // Para poder obtener el IP y el Puerto de destino a la conexion.
            LectorXML lx = new LectorXML();
            lx.lecturaXML();

            //Utilizamos el ip y el pueto leidos en el XML.
            Socket s1 = new Socket(lx.ip, lx.port);


            ObjectOutputStream oos = new ObjectOutputStream(s1.getOutputStream());

            // Comandos para leer la salida standard del Terminal.
            Runtime rt = Runtime.getRuntime();
            Process ps = rt.exec("./script.sh");
            BufferedReader input = new BufferedReader(new 
                                        InputStreamReader(ps.getInputStream()));

            String line = null;
            ArrayList<String> listado = new ArrayList<String>();

            //Ciclo para leer todas las lineas de la salida standard.
            while ((line = input.readLine()) != null) {
//                System.out.println(line);
                listado.add(line);
            }

            // Variable utilizada para imprimir el status arrojado por la
            // salida standard del terminal.
            int exitval = ps.waitFor();
            System.out.println("valor de salida: " + exitval);
            oos.writeObject(listado);

            // Imprime el texto recibido por el servidor.
            ObjectInputStream ois = new ObjectInputStream(s1.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);


            oos.close();
            ois.close();


        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (ConnectException connExc) {
            System.err.println("Imposible establecer la conexion");
        } catch (IOException e) {
        }
    }
}