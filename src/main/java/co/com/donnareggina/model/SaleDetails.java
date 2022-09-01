package co.com.donnareggina.model;

import java.io.Serializable;
import java.util.List;

public class SaleDetails implements Serializable{
  
	private static final long serialVersionUID = 1L;
	
	private Sale saleData;
	private Customer customer;
	private List<Product> productsSales;
	
	
	public Sale getSaleData() {
		return saleData;
	}
	public void setSaleData(Sale saleData) {
		this.saleData = saleData;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProductsSales() {
		return productsSales;
	}
	public void setProductsSales(List<Product> productsSales) {
		this.productsSales = productsSales;
	}
  

}
