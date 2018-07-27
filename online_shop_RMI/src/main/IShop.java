package main;

import java.rmi.*;
import java.util.List;

public interface IShop extends Remote {
  List<String> lista(String filtr) throws RemoteException;
  boolean kup(String id, ICallback n) throws RemoteException;
  Product produkt(String id) throws RemoteException;
  //List<Product> produkty(String filtr) throws RemoteException;
  public void wyswietlProdukty() throws RemoteException;
}
