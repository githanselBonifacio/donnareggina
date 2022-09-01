package co.com.donnareggina.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.Addresser;
import co.com.donnareggina.model.Customer;
import co.com.donnareggina.model.Product;
import co.com.donnareggina.model.Sale;
import co.com.donnareggina.model.SaleDetails;

@ManagedBean(name="saleDetail")
@ViewScoped
public class DetailSaleView implements Serializable{

	private static final long serialVersionUID = 1L;
	private Sale saleResume;
	private SaleDetails saleDetail;
	private List<Product> listProduct;
	private Customer customer;
	@ManagedProperty(value = "#{sales}")
	private SalesView salesView;
	private Addresser addresserSend;
	private String addresserFull;
	private String nameFull;
	 @PostConstruct
	    public void init() {
		 this.saleResume = salesView.getSale();
		 this.listProduct = new ArrayList<Product>();
	 }
	


	public String getNameFull() {
		return nameFull;
	}



	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}



	public String getAddresserFull() {
		return addresserFull;
	}



	public void setAddresserFull(String addresserFull) {
		this.addresserFull = addresserFull;
	}



	public Addresser getAddresserSend() {
		return addresserSend;
	}




	public void setAddresserSend(Addresser addresserSend) {
		this.addresserSend = addresserSend;
	}




	public List<Product> getListProduct() {
		return listProduct;
	}


	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}


	public Sale getSaleResume() {
		return saleResume;
	}

	public void setSaleResume(Sale saleResume) {
		this.saleResume = saleResume;
	}

	public SalesView getSalesView() {
		return salesView;
	}

	public void setSalesView(SalesView salesView) {
		this.salesView = salesView;
	}

	public SaleDetails getSaleDetail() {
		return saleDetail;
	}
	public void setSaleDetail(SaleDetails saleDetail) {
		this.saleDetail = saleDetail;
	}
	
	public void addSaleDetail() {
		Query query = new Query();
		this.listProduct = query.getProductSale(this.salesView.getSale().getBillNumber());
		this.setCustomer(query.getCustomerSale(this.salesView.getSale().getBillNumber()));
		this.addresserSend = (query.getAddresserSale(this.salesView.getSale().getBillNumber()));
		this.addresserFull = this.addresserSend.toString();
		setFullName();
	}




	public Customer getCustomer() {
		return customer;
	}




	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setFullName() {
		this.nameFull = this.customer.getFirsName()+" "+this.customer.getSecondName()+" "+this.customer.getLastName()+" "+this.customer.getSecondLastname();
	}
}
