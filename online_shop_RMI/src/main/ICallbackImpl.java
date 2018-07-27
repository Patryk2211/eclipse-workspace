package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ICallbackImpl 
  extends UnicastRemoteObject 
  implements ICallback {
	public ICallbackImpl() throws RemoteException {
		super();
	}
	public void komunikuj(String text) throws RemoteException {
		System.out.println("Odebrano komunikat: " + text);   
	}
}
