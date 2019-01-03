package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICallback extends Remote {
	public void komunikuj(String text) throws RemoteException;
}
