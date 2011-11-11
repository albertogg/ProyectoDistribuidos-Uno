/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import rmi.InterfaceCliente;
import java.rmi.Naming;

/**
 *
 * @author albertogg
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        		try {
			InterfaceCliente h = 
				(InterfaceCliente) Naming.lookup("rmi://localhost:1099/cliente");
			System.out.println(h.bolas1("cliente serveeeeer!!"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
