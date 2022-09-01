package co.com.donnareggina.bean;


import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


import org.primefaces.model.ResponsiveOption;



@ManagedBean(name="imageView")
@ApplicationScoped
public class ImageView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<ResponsiveOption> responsiveOptions;
	private List<byte []> images;
	private int activeIndex = 0;
	@PostConstruct  
    public void init(){
		

		this.images = new ArrayList<byte[]>();
	
		try {
			
			System.out.println(System.getProperty("user.dir"));
			File file1 = new File(System.getProperty("user.dir")+"\\donnareggina\\src\\main\\webapp\\img\\4477509.jpg");
			FileInputStream fis1 = new FileInputStream(file1);
			this.images.add(fis1.readAllBytes());
			
			File file2 = new File(System.getProperty("user.dir")+"\\donnareggina\\src\\\\main\\webapp\\img\\824.jpg");
			FileInputStream fis2 = new FileInputStream(file2);
			this.images.add(fis2.readAllBytes());
			fis1.close();
			fis2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
