/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.util.ArrayList;
import rmi.InterfaceCliente;
import java.rmi.Naming;

/**
 *
 * @author albertogg & carlos fernandes
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public ArrayList<String> arreglolp = new ArrayList<String>();

    public ArrayList<String> iniciarConexion() {
        try {
            InterfaceCliente h =
                    (InterfaceCliente) Naming.lookup("rmi://localhost:1099/cliente");
            arreglolp = h.bolas1(0);
            System.out.println(arreglolp);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return arreglolp;
    }

}
