package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ShopServer {
	Registry reg; // rejestr nazw obiektow
	ShopServant servant; // klasa uslugowa
	
	public static void main(String[] args) {
		try {
		  new ShopServer();
		} catch (Exception e) {
			e.printStackTrace(); System.exit(1);
	} }
	
	protected ShopServer() throws RemoteException {
		try { 
		  reg = LocateRegistry.createRegistry(1099); // Utworzenie rejestru nazw  
		  servant = new ShopServant();        // utworzenie zdalnego obiektu		  
		  reg.rebind("ShopServer", servant);  // zwiazanie nazwy ze zdalnym obiektem
		  System.out.println("ShopServer READY");
		} catch(RemoteException e) {
		  e.printStackTrace(); 
		  throw e;
	}	}
}

