package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ShopClient {
	
	private Scanner userInput = new Scanner(System.in);
	private IShop remoteObject;
	private ICallback callBack;
	
	public static void main(String[] args) {
	  new ShopClient();
    }
	  public ShopClient() {
	  
		  	IShop remoteObject; // referencja do zdalnego obiektu
			Registry reg;	// rejestr nazw obiektow
			try {
			  // pobranie referencji do rejestru nazw obiektow
			  reg = LocateRegistry.getRegistry();
			  remoteObject = (IShop) reg.lookup("ShopServer");
			  System.out.println("Client ready");
			  System.out.println(reg);
			  loop();
			}
			catch(RemoteException e) {
			  e.printStackTrace();
			}
			catch(NotBoundException e) {
			  e.printStackTrace();
			}	
	  }
	  
	  public void loop()
	  {
		  try {
		  remoteObject = new ShopServant();
		  } catch(RemoteException ex) {
			  ex.getMessage();
		  }
		  
		  try {
			  callBack = new ICallbackImpl();
		  } catch(RemoteException ex) {
			  ex.getMessage();
		  }
		  
		  String input;
		  
		  while(true)
		  {
			  System.out.println("\nSelect action: s - serach product, b - buy product, l - show list of available products");
			  input = userInput.nextLine();
			  
			  switch(input)
			  {
			  case "s":
				  System.out.println("Enter product name or category:");
				  input = userInput.nextLine();
				  try {
					  List<String> katalog = remoteObject.lista(input);
					  System.out.println("Katalog zawiera: ");
					  for(String s : katalog)
					  	System.out.println(" - " + s);
				  } catch(RemoteException ex) {
					  ex.getMessage();
				  }
				  break;
			  case "b":
				  System.out.println("Enter product id:");
				  input = userInput.nextLine();
				  try {
					  remoteObject.kup(input, callBack);
				  } catch(RemoteException ex) {
					  ex.getMessage();
				  }
				  break;
			  case "l":
				  try {
					  remoteObject.wyswietlProdukty();
				  } catch(RemoteException ex) {
					  ex.getMessage();
				  }
				  break;
			  }
		  }
	  }
	  
}