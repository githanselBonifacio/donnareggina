package co.com.donnareggina.bean;


import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.Addresser;
import co.com.donnareggina.model.Customer;
import co.com.donnareggina.model.Product;
import co.com.donnareggina.model.Sale;
import co.com.donnareggina.model.User;

@ManagedBean(name="sales")
@SessionScoped
public class SalesView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Query query;
	private List<Product> products;
	private Product product;
	private int size;
	private List<Product> productsShop;
	private int cantidadProductos;
	private String code;
	private List<String> listCode;
	private byte [] image;
	private double subTotal;
	private double priceTransport;
	private double impuestos;
	private double total;
	private String isUserRegister;
	private Customer customer;
	private Addresser addresser;
	private List<Sale> salesTable;
	private Sale sale;
	private Sale saleSend;
	
    @PostConstruct
    public void init() {
    	System.out.println("inicio");
        query = new Query();
    	products = query.getAllProduct();
    	size = products.size();
    	this.productsShop = new ArrayList<>();
    	this.cantidadProductos=1;
    	this.listCode = this.products.stream().map(t -> t.getCode()).collect(Collectors.toList());
    	this.code = listCode.get(0);
    	onChangeCode();
    	this.isUserRegister="false";
    	this.customer = new Customer();
    	this.addresser = new Addresser();
    	this.salesTable = query.getAllSalesTable();
    }



	public Sale getSaleSend() {
		return saleSend;
	}



	public void setSaleSend(Sale saleSend) {
		this.saleSend = saleSend;
	}



	public List<Sale> getSalesTable() {
		return salesTable;
	}



	public void setSalesTable(List<Sale> salesTable) {
		this.salesTable = salesTable;
	}



	public Sale getSale() {
		return sale;
	}



	public void setSale(Sale sale) {
		this.sale = sale;
	}


	public Query getQuery() {
		return query;
	}


	public void setQuery(Query query) {
		this.query = query;
	}


	public Addresser getAddresser() {
		return addresser;
	}


	public void setAddresser(Addresser addresser) {
		this.addresser = addresser;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public String getIsUserRegister() {
		return isUserRegister;
	}


	public void setIsUserRegister(String isUserRegister) {
		this.isUserRegister = isUserRegister;
	}

	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public double getImpuestos() {
		return impuestos;
	}


	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}


	public double getPriceTransport() {
		return priceTransport;
	}


	public void setPriceTransport(double priceTransport) {
		this.priceTransport = priceTransport;
	}


	public double getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public List<String> getListCode() {
		return listCode;
	}


	public void setListCode(List<String> listCode) {
		this.listCode = listCode;
	}


	public int getCantidadProductos() {
		return cantidadProductos;
	}


	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}


	public List<Product> getProductsShop() {
		return productsShop;
	}

	public void setProductsShop(List<Product> productsShop) {
		this.productsShop = productsShop;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void addProduct() {
		
		if(this.productsShop.contains(this.product)) {
			addMessage(FacesMessage.SEVERITY_WARN, "No se puede agregar", "El producto ya se encuentra en su lista");
		}else {
			
			Product p = this.product;
			p.setQuantity(this.cantidadProductos);
			this.productsShop.add(p);
			calculateSubTotal();
			calculatePriceTransport ();
			calculateImpuestos();
			calculateTotal();
			addMessage(FacesMessage.SEVERITY_INFO, "Felicidades", "Se a agregado el producto");
			
			
		}
		this.productsShop.forEach(t ->System.out.println(t) );
	}
	public void deleteProduct() {
		
		this.productsShop =  this.productsShop.stream()
				.filter(t ->!t.getCode().equals(this.product.getCode()))
				.collect(Collectors.toList());
		calculateSubTotal();
		calculatePriceTransport ();
		calculateImpuestos();
		calculateTotal();
		
	}
	public void onChangeCode() {
		this.product = this.products.stream().filter(t -> t.getCode().equals(this.code)).collect(Collectors.toList()).get(0);
		this.image = this.product.getImage();

	}
	
	public String productFormat(String code) {
		Product p = this.products.stream().filter(t -> t.getCode().equals(this.code)).collect(Collectors.toList()).get(0);
		return p.getNombre()+"-"+p.getColor()+"-"+p.getSize();
		
	}
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
	public void calculateSubTotal () {
		this.subTotal =	this.productsShop.stream().mapToDouble(p->p.getPrice()*p.getQuantity()).sum();
	
	}
	public void calculatePriceTransport () {
		if(this.subTotal>=250000) {
			this.priceTransport = 0;
		}else {
			this.priceTransport = 25000;
		}
	
	}
	public void calculateImpuestos() {
		this.impuestos = this.subTotal*0.19;
	}
	public void calculateTotal() {
		this.total =  this.impuestos +this.subTotal + this.priceTransport;
	}
	
	public boolean isRegister() {
		return this.isUserRegister.equals("true");
		
	}
	public void send() {
		
		query.createAccount(this.customer.getEmail(), "00000000");
		User newUser = query.getUserAccount(this.customer.getEmail(),"00000000").get(0);
		query.createCustomer(newUser.getIdUser(),this.customer);
		query.addAddressCustomer(this.customer.getIdentificationNumber(),this.addresser);
		String billSale = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9);
		long mil = System.currentTimeMillis();
		Date actualDate = new Date(mil);
		this.saleSend = new Sale();
		this.saleSend.setBillNumber(billSale);
		this.saleSend.setDateSale(actualDate);
		this.saleSend.setIdCustomer(this.customer.getIdentificationNumber());
		this.saleSend.setStatus("EN PREPARACION");
		this.saleSend.setPaid(false);
		for(Product p :this.productsShop) {
			
			saleSend.setIdProduct(p.getCode());
			saleSend.setQuantity(p.getQuantity());
			query.addSale(billSale, saleSend);
			
		}
		
		PrimeFaces.current().executeScript("PF('confirmSale').show()");
		resetAll();
		updateSalesTable();
		PrimeFaces.current().ajax().update("form:tabMain:consultSale");

	}
	public void resetAll() {
		this.customer=new Customer();
		this.addresser = new Addresser();
		this.productsShop = new ArrayList<>();
		this.subTotal=0;
		this.priceTransport=0;
		this.impuestos = 0;
		this.total = 0;
	}
	public void updateSalesTable() {
		this.salesTable = query.getAllSalesTable();
	}
}
