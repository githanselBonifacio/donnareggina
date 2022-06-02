package co.com.donnareggina.bean;


import java.io.Serializable;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import co.com.donnareggina.model.ProductViewData;

@ManagedBean(name="dataGridView")
@ViewScoped
public class DataGridView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{producServiceView}")
    private ProductService serviceProduct;
	
	private List<ProductViewData> products;
    private ProductViewData productAddCart;



    @PostConstruct
    public void init() {
    	 this.productAddCart = new ProductViewData();
		 this.products = serviceProduct.getProducts();
		

    }

   
    public ProductService getServiceProduct() {
		return serviceProduct;
	}


	public void setServiceProduct(ProductService serviceProduct) {
		this.serviceProduct = serviceProduct;
	}


	public List<ProductViewData> getProducts() {
        return products;
    }

    
    public void setProducts(List<ProductViewData> products) {
		this.products = products;
	}

	

    public ProductViewData getProductAddCart() {

		return productAddCart;
	}


	public void setProductAddCart(ProductViewData productAddCart) {
		this.productAddCart = productAddCart;
	}

	public void addCart() {
		System.out.println(this.productAddCart.toString());
	}
	public void clearMultiViewState() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, this::showMessage);
    }

    private void showMessage(String clientId) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, clientId + " multiview state has been cleared out", null));
    }
}
