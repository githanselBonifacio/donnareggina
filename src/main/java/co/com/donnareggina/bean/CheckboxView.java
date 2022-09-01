package co.com.donnareggina.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.UnselectEvent;

@ManagedBean(name ="checkboxView")
@RequestScoped
public class CheckboxView {
	private String[] selectedSizes;
    private List<String> sizes;
    
    private String[] selectedSizesClothes;
    private List<String> sizesClothes;
    
    private String[] selectedOptions;
    
    @PostConstruct
    public void init() {
    	sizes = new ArrayList<>();
    	sizes.add("XS");
    	sizes.add("S");
    	sizes.add("M");
    	sizes.add("L");
    	sizes.add("XL");
    	sizes.add("XXL");
    	sizes.add("28");
    	sizes.add("30");
    	sizes.add("32");
    	sizes.add("34");
    	sizes.add("36");
    	sizes.add("38");
    	sizes.add("40");
    	sizes.add("N/A");
    }

	public String[] getSelectedSizesClothes() {
		return selectedSizesClothes;
	}

	public void setSelectedSizesClothes(String[] selectedSizesClothes) {
		this.selectedSizesClothes = selectedSizesClothes;
	}

	public List<String> getSizesClothes() {
		return sizesClothes;
	}

	public void setSizesClothes(List<String> sizesClothes) {
		this.sizesClothes = sizesClothes;
	}

	public String[] getSelectedSizes() {
		return selectedSizes;
	}

	public void setSelectedSizes(String[] selectedSizes) {
		this.selectedSizes = selectedSizes;
	}

	public List<String> getSizes() {
		return sizes;
	}

	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}
	
	

	 public String[] getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(String[] selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	public <T> void onItemUnselect(UnselectEvent<T> event) {
	        FacesMessage msg = new FacesMessage();
	        msg.setSummary("Item unselected: " + event.getObject().toString());
	        msg.setSeverity(FacesMessage.SEVERITY_INFO);

	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }

	    public void selectedOptionsChanged() {
	        String message = "selectedOptions changed to: ";
	        if (selectedOptions != null) {
	            for (int i = 0; i < selectedOptions.length; i++) {
	                if (i > 0) {
	                    message += ", ";
	                }
	                message += selectedOptions[i];
	            }
	        }

	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	    }
	    
}
