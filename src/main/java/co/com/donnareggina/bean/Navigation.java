package co.com.donnareggina.bean;


import java.io.Serializable;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean(name="navigation")
@ApplicationScoped
public class Navigation implements Serializable{


	private static final long serialVersionUID = 1L;
	

	public void AdminProducts() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nav = facesContext.getApplication().getNavigationHandler();
		nav.handleNavigation(facesContext, null, "adminProducts");
	}
	
	
}
