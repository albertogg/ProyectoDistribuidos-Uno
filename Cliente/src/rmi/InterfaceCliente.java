/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author albertogg
 */
public interface InterfaceCliente extends Remote {
    public String bolas1(String hola)throws RemoteException;
}
