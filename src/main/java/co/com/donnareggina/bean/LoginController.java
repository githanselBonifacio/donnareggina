package co.com.donnareggina.bean;


import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.User;

@ManagedBean(name="loginController")
@SessionScoped
public class LoginController implements Serializable{

	private static final long serialVersionUID = 1L;
	private User user;
	private String userName;
	private String password;
	
	//productos en carro
	private int quantityInCart;
	
	@PostConstruct  
    public void init(){
		this.user=new User();
		this.userName="";
		this.password="";

    }
	
	public int getQuantityInCart() {
		return quantityInCart;
	}

	public void setQuantityInCart(int quantityInCart) {
		this.quantityInCart = quantityInCart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	 public void reset() {
			setUserName("");
			setPassword("");
	    }
	
	 public void resetFail() {
	        this.userName = null;
	        this.password = null;

	    }
	 
	public String validateUsernamePassword() {
		
		Query query = new Query();
		ArrayList<User> userResponse = query.getUserAccount(userName,password);
		
		if(!userResponse.isEmpty()) {
			
			corret();
			setUser(userResponse.get(0));
			user.setDataUser(query.getDataUser(user.getIdUser()).get(0));
			user.setSession(true);
			this.quantityInCart = query.getNumberProductInCart(this.user.getDataUser().getIdentificationNumber());
			reset();
			return "home";	
			
			
	
		}else {
			 reset();
			 setUser(new User());
			 error();
			 return null;
		}
		
	}
	public String logout() {
		 setUser(new User());
		 setUserName("");
		 setPassword("");
		 return "/index.xhtml";	
		
		 
	}
	
	
	public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "por favor verifique su usuario y contraseña"));
    }
	public void corret() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Sesion iniciada correctamente"));
    }

}
