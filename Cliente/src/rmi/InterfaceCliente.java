/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author albertogg & carlos fernandes
 */
public interface InterfaceCliente extends Remote {
    
    public ArrayList<String> listado1 = new ArrayList<String>();
    
    public ArrayList<String> bolas1(int hola) throws RemoteException;
}
