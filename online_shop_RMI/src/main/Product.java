package main;
import java.io.Serializable;

public class Product implements Serializable {
	private int id;
    private String name;
    private double price;
    private String category;
    private boolean available;
	
    public Product(int id, String name, double price, String category, boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.available = available;
	}
    
    public int getId() {
    	return id;
    }

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	public boolean isAvailable() {
		return available;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", available=" + available
				+ "]";
	}

	public boolean purchase() {
        if (available == true) {
            available = false;
            return true; // success !
        }
        return false;
    }
}
