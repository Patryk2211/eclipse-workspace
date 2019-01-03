package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ShopServant
        extends UnicastRemoteObject
        implements IShop {
    private Map<String, Product> products = new HashMap<String, Product>();

    public ShopServant() throws RemoteException {
        products.put("1", new Product(1, "Computer mouse", 19.99, "Computers", true));
        products.put("2", new Product(2, "DC Comics T-Shirt", 39.99, "Clothes", true));
        products.put("3", new Product(3, "Spalding basketball", 59.99, "Sport", true));
    }

    public List<String> lista(String filtr) throws RemoteException {
        List<String> list = new ArrayList<String>();
        for(Product b : products.values())
            if(b.getName().matches(filtr) || b.getCategory().matches(filtr))
                list.add(b.toString());
        
        return list;
    }

    public boolean kup(String id, ICallback n) throws RemoteException {
        Product b = products.get(id);
        String bid = "Produkt " + id;
        if (b != null) {
            if (b.purchase()) {
                // komunikat zwrotny
                n.komunikuj(bid + " kupiony!");
                return true;
            } else {
                n.komunikuj(bid + " niedostepny - wyprzedany");
                return false;
            }
        } else {
            // komunikat zwrotny
            n.komunikuj(bid + " nie istnieje!");
            return false;
        }
    }

    public Product produkt(String id) throws RemoteException {
        Product b = products.get(id);
        if (b != null) {
            if (b.purchase())
                return b;
            else
                return null;
        } else
            return null;
    }

    /*public List<Product> produkty(String filtr) throws RemoteException {
        List<Product> list = new ArrayList<Product>();
        for(Product b : products.values())
        	if(b.getName().matches(filtr) || b.getCategory().matches(filtr))
                list.add(b);
        return list;
    }*/
     
    public void wyswietlProdukty() throws RemoteException {
    	for(Product b : products.values())
    		if(b.isAvailable())
    			System.out.println(b.toString());
    }
}
