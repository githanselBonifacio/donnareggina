package co.com.donnareggina.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SlideEndEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name="filterStored")
@ViewScoped
public class FilterStoreView {


	private double precio;
	private  List<String> colors;
	private String[] selectedColors;
	
	private  List<String> sizes;
	private String[] selectedsizes;
	
	private ArrayList<String> categories;
	private String[] selectedCategory;
	
	@PostConstruct
	private void init() {
		this.precio=25000.00;
		
		this.categories = new ArrayList<String>();
		this.categories.add("Accesorios");
		this.categories.add("Ropa exterior");
		this.categories.add("Ropa interior");
		this.categories.add("Deporte");
		
		this.colors = new ArrayList<String>();
		this.colors.add("Azul");
		this.colors.add("Verde");
		this.colors.add("Negro");
		this.colors.add("Blanco");
		this.colors.add("Beige");
		this.colors.add("Gris");
		this.colors.add("Purpura");
		this.colors.add("Fucsia");	
		this.colors.add("Rosa");
		this.colors.add("Amarillo");
		this.colors.add("Café");
		
		this.sizes = new ArrayList<String>();
		this.sizes.add("28");
		this.sizes.add("30");
		this.sizes.add("32");
		this.sizes.add("34");
		this.sizes.add("36");
		this.sizes.add("38");
		
		this.sizes.add("XS");
		this.sizes.add("S");
		this.sizes.add("M");
		this.sizes.add("L");
		
	}


	public String[] getSelectedColors() {
		return selectedColors;
	}


	public void setSelectedColors(String[] selectedColors) {
		this.selectedColors = selectedColors;
	}


	public String[] getSelectedsizes() {
		return selectedsizes;
	}


	public void setSelectedsizes(String[] selectedsizes) {
		this.selectedsizes = selectedsizes;
	}


	public ArrayList<String> getCategories() {
		return categories;
	}


	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}


	public String[] getSelectedCategory() {
		return selectedCategory;
	}


	public void setSelectedCategory(String[] selectedCategory) {
		this.selectedCategory = selectedCategory;
	}


	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public List<String> getColors() {
		return colors;
	}



	public void setColors(List<String> colors) {
		this.colors = colors;
	}



	public List<String> getSizes() {
		return sizes;
	}



	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}

	public void onInputChanged(ValueChangeEvent event) {
		this.precio = (double) event.getNewValue();
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public void onSlideEnd(SlideEndEvent event) {
		this.precio = (double)event.getValue();
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	 public void onItemUnselect(UnselectEvent event) {
	        FacesMessage msg = new FacesMessage();
	        msg.setSummary("Item unselected: " + event.getObject().toString());
	        msg.setSeverity(FacesMessage.SEVERITY_INFO);

	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 public void selectedOptionsChanged() {
	        String message = "selectedOptions changed to: ";
	        if (selectedCategory != null) {
	            for (int i = 0; i < selectedCategory.length; i++) {
	                if (i > 0) {
	                    message += ", ";
	                }
	                message += selectedCategory[i];
	            }
	        }

	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	    }
}
