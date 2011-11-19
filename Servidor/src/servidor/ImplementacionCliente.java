/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import rmi.InterfaceCliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import persistencia.LectorXML;

/**
 *
 * @author albertogg
 */
public class ImplementacionCliente 
        extends UnicastRemoteObject implements InterfaceCliente {
    
    public int hola;
    public ArrayList<String> listado11 = new ArrayList<String>();
    
    	public ImplementacionCliente() throws RemoteException {
		
	}
       
        
    @Override
        public ArrayList<String> bolas1(int hola) throws RemoteException {
             this.hola = hola;
             System.out.println(hola);

             switch (hola) {
                 case 0:
                     LectorXML lxml = new LectorXML();
                     lxml.lecturaXML();
                     listado11 =lxml.listado;
                     System.out.println(listado11);
                
             }
             return(listado11);
        }
    
}
