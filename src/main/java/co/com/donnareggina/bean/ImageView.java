package co.com.donnareggina.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.ResponsiveOption;

import co.com.donnareggina.database.Query;

@ManagedBean(name="imageView")
@ApplicationScoped
public class ImageView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<ResponsiveOption> responsiveOptions;
	private List<byte []> images;
	private int activeIndex = 0;
	@PostConstruct  
    public void init(){
		
		Query query = new Query();
		images = query.getImages("new");
		
		responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1500px", 5));
        responsiveOptions.add(new ResponsiveOption("1024px", 3));
        responsiveOptions.add(new ResponsiveOption("768px", 2));
        responsiveOptions.add(new ResponsiveOption("560px", 1));
      
    }

	


	public int getActiveIndex() {
		return activeIndex;
	}


	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public List<byte[]> getImages() {
		return images;
	}

	public List<ResponsiveOption> getResponsiveOptions() {
		return responsiveOptions;
	}

	public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
		this.responsiveOptions = responsiveOptions;
	}

	public void setImages(List<byte[]> images) {
		this.images = images;
	}
	 public void changeActiveIndex() {
	        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	        this.activeIndex = Integer.valueOf(params.get("index"));
	    }

   
}
