package co.com.donnareggina.bean;



import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.primefaces.model.ResponsiveOption;
import co.com.donnareggina.model.ProductViewData;


@ManagedBean(name="carouselView")
@ViewScoped
public class CarouselView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{producServiceView}")
    private ProductService serviceProduct;
	
	private List<ProductViewData> products;
	private ProductViewData  selectedProduct;
    private List<ResponsiveOption> responsiveOptions;
    
    
    
    @PostConstruct
    public void init() {
    	
		 this.products = serviceProduct.getProducts();
   
    	

	       responsiveOptions = new ArrayList<>();
           responsiveOptions.add(new ResponsiveOption("1600", 3, 3));
           responsiveOptions.add(new ResponsiveOption("1200px", 2, 2));
           responsiveOptions.add(new ResponsiveOption("400px", 1, 1));
       
		  
       
    }

    public ProductViewData getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductViewData selectedProduct) {
		this.selectedProduct = selectedProduct;
	}


	public List<ProductViewData> getProducts() {
        return products;
    }
    
  

	public void setProducts(List<ProductViewData> products) {
		this.products = products;
	}

    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
        this.responsiveOptions = responsiveOptions;
    }

	public ProductService getServiceProduct() {
		return serviceProduct;
	}

	public void setServiceProduct(ProductService serviceProduct) {
		this.serviceProduct = serviceProduct;
	}
    
    
}
