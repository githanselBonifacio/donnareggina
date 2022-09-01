package co.com.donnareggina.model;

import java.sql.Date;

public class Sale {

	private String billNumber;
	private String idCustomer ;
	private Date dateSale;
	private String idProduct;
	private int quantity;
	private boolean isPaid;
	private String status;
	
	public Sale() {
		super();
	
	}
	public Sale(String billNumber, String idCustomer, Date dateSale, String idProduct, int quantity, boolean isPaid,
			String status) {
		super();
		this.billNumber = billNumber;
		this.idCustomer = idCustomer;
		this.dateSale = dateSale;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.isPaid = isPaid;
		this.status = status;
	}
	
	public Sale(String billNumber, String idCustomer, Date dateSale, boolean isPaid, String status) {
		super();
		this.billNumber = billNumber;
		this.idCustomer = idCustomer;
		this.dateSale = dateSale;
		this.isPaid = isPaid;
		this.status = status;
	}
	
	
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public Date getDateSale() {
		return dateSale;
	}
	public void setDateSale(Date dateSale) {
		this.dateSale = dateSale;
	}
	public String getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
