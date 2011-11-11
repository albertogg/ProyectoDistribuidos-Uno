/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import rmi.InterfaceCliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author albertogg
 */
public class ImplementacionCliente 
        extends UnicastRemoteObject implements InterfaceCliente {
    
    public String hola;
    
    	public ImplementacionCliente() throws RemoteException {
		
	}
       
        
    @Override
        public String bolas1(String hola) throws RemoteException {
             this.hola = hola;
             System.out.println(hola);
             return hola;
        }
    
}
