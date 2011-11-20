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
 * @author albertogg & carlos fernandes
 */
public class ConexionHelper {
    
    // Crea la conexion rmi, el puerto y la direccion a la cual se conecta
    // el cliente via rmi.
    public void conexionRMI() {
        try {
            System.out.println("Establecida la conexion RMI");
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
