package co.com.donnareggina.model;

import java.io.Serializable;
import java.util.Arrays;

public class ProductViewData implements Serializable{
	
	private static final long serialVersionUID = -1425889528483314492L;
	private String idProducto;
	private String nombre;
	private String description;
	private Double price;
	private String category;
	private int quantity;
	private String inventoryStatus;
	private int rating;
	private int solicitadasVenta;
	private byte [] image;
	
	
	public ProductViewData() {};
	
	public ProductViewData(String idProducto, String nombre, String description, Double price, String category,
			int quantity, String inventoryStatus, int rating, int solicitadasVenta, byte[] image) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.description = description;
		this.price = price;
		this.category = category;
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
	public void setSolicitadasventa(int solicitadasventa) {
		this.solicitadasVenta = solicitadasventa;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	@Override
	public String toString() {
		return "ProductViewData [idProducto=" + idProducto + ", nombre=" + nombre + ", description=" + description
				+ ", price=" + price + ", category=" + category + ", quantity=" + quantity + ", inventorystatus="
				+ inventoryStatus + ", rating=" + rating + ", solicitadasventa=" + solicitadasVenta+ "]";
	}

	

}
