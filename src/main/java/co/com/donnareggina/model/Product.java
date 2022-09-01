package co.com.donnareggina.model;

import java.io.Serializable;


public class Product implements Serializable{
	

	private static final long serialVersionUID = -1657701754682602725L;
	private String idProducto;
    private String code;
    private String nombre;
    private String description;
    
    private Double price;
    
    private String category;
    private String color;
    private String size;
    
    private int quantity;
    
    private String inventoryStatus;
    
    private int rating;
    private int solicitadasVenta;
    private byte [] image;
   
    
    
    @Override
	public String toString() {
		return "Product [idProducto=" + idProducto + ", code=" + code + ", nombre=" + nombre + ", description="
				+ description + ", image=" + image + ", price=" + price + ", category=" + category + ", color=" + color
				+ ", talla=" + size + ", quantity=" + quantity + ", inventoryStatus=" + inventoryStatus + ", rating="
				+ rating + ", solicitadasVenta=" + solicitadasVenta + "]";
	}

	public Product() {};
    
   

    public Product(String idProducto, String code, String nombre, String description, Double price, String category,
			String color, String size, int quantity, String inventoryStatus, int rating, int solicitadasVenta, byte[] image) {
    	super();
		this.idProducto = idProducto;
		this.code = code;
		this.nombre = nombre;
		this.description = description;
		this.price = price;
		this.category = category;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.inventoryStatus = inventoryStatus;
		this.rating = rating;
		this.solicitadasVenta = solicitadasVenta;
		this.image = image;
	}


	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getSolicitadasVenta() {
		return solicitadasVenta;
	}

	public void setSolicitadasVenta(int solicitadasVenta) {
		this.solicitadasVenta = solicitadasVenta;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
