package co.com.donnareggina.bean;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ManagedBean(name="navigation")
@ViewScoped
public class Navigation implements Serializable{


	private static final long serialVersionUID = 1L;
	private final String pathHome     = "templates/home.xhtml";
	private final String pathTienda   = "templates/tienda.xhtml";
	private final String pathAcercaDe = "templates/acercaDe.xhtml";
	private final String pathContacto = "templates/Contacto.xhtml";
	private final String pathAdmin    = "templates/admin.xhtml";
	
	
	private  String pathActual;
	

	@PostConstruct  
    public void init(){
		home();
    }
	
	public void home() {
		setPathActual(pathHome);
	}
	 
	public void tienda() {
		setPathActual(pathTienda);
	}
	 
	public void acercaDe() {
		setPathActual(pathAcercaDe);
		
	}
	 
	public void contacto() {
		setPathActual(pathContacto);
		
	}
	public void admin() {
		setPathActual(pathAdmin);
		
	}
	public void AdminProducts() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nav = facesContext.getApplication().getNavigationHandler();
		nav.handleNavigation(facesContext, null, "adminProducts");
	}
	
	public String getPathAdmin() {
		return pathAdmin;
	}

	public String actual() {
		return pathActual;
	}
	public void setPathActual(String pathActual) {
		this.pathActual = pathActual;
	}

	public String getPathActual() {
		return pathActual;
	}

	public String getPathHome() {
		return pathHome;
	}

	public String getPathTienda() {
		return pathTienda;
	}

	public String getPathAcercaDe() {
		return pathAcercaDe;
	}

	public String getPathContacto() {
		return pathContacto;
	}
	
}
