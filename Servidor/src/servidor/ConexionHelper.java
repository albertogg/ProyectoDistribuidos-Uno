/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author albertogg
 */
public class ConexionHelper {

    public void conexionRMI() {
        try {
            System.out.println("Entreeeeeeeeee CONEXIONRMI");
            ImplementacionCliente impcl = new ImplementacionCliente();

            LocateRegistry.createRegistry(1099);

            Naming.rebind("cliente", impcl);

            System.out.println("Se ejecuto el enlace al cliente");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
