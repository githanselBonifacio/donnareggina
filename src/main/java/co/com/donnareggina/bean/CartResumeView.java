package co.com.donnareggina.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.Product;

@ManagedBean(name="cartResume")
@ViewScoped
public class CartResumeView {
	
	private List<Product> productsCart;
	
	@ManagedProperty(value = "#{loginController}")
	private LoginController loginController;
	
	private double totalPrice;
	
	@PostConstruct
	public void init() {
		Query query = new Query();
		this.productsCart = query.getProductCartByCustomer("1140883857");
		this.totalPrice = 0.0;
		for(Product product: this.productsCart) {
			this.totalPrice +=product.getPrice();
		}
		
	}

	

	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public List<Product> getProductsCart() {
		return productsCart;
	}



	public void setProductsCart(List<Product> productsCart) {
		this.productsCart = productsCart;
	}



	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
}
