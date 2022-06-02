package co.com.donnareggina.bean;

import java.beans.EventHandler;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.UnselectEvent;
import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.InventoryStatus;
import co.com.donnareggina.model.Product;



@ManagedBean(name="crudView")
@ViewScoped
public class CrudView implements Serializable {
	
	@ManagedProperty(value = "#{producServiceView}")
    private ProductService serviceProduct;
	
	@ManagedProperty(value = "#{uploadData}")
	private UploadData uploadedData;
	
	private static final long serialVersionUID = -96209626538946893L;
	
	private List<Product> products;
	
    private Product selectedProduct;
    private List<Product> selectedProducts;
    
    
    @ManagedProperty(value = "#{checkboxView}")
    private CheckboxView checkboxView;
    
    private String codeProduct;
    
    @PostConstruct
    public void init() {
    	this.selectedProduct = new Product() ;
		this.products = serviceProduct.getProductsCrudEdit();

	
    }
    public void reset() {
    	this.selectedProduct = new Product() ;
    	
    }
	public CheckboxView getCheckboxView() {
		return checkboxView;
	}
	
	

	public String getCodeProduct() {
		return codeProduct;
	}
	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}
	public void setCheckboxView(CheckboxView checkboxView) {
		this.checkboxView = checkboxView;
	}


	public ProductService getServiceProduct() {
		return serviceProduct;
	}


	public void setServiceProduct(ProductService serviceProduct) {
		this.serviceProduct = serviceProduct;
	}


	public UploadData getUploadedData() {
		return uploadedData;
	}

	public void setUploadedData(UploadData uploadedData) {
		this.uploadedData = uploadedData;
	}


	public List<Product> getProducts() {
		return products;
	}



	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}



	public List<Product> getSelectedProducts() {
		return selectedProducts;
	}


	public void setSelectedProducts(List<Product> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
	
	
	public void openNew() {
        this.selectedProduct = new Product();
        this.selectedProduct.setCategory("Ropa exterior");
    }

    public void saveProduct() {
    	try {
    		Query query = new Query();
		    	String codeBaseProduct = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9);
		     
		    	
		        if (this.selectedProduct.getIdProducto()==null) {
		        	
		        	this.selectedProduct.setIdProducto(this.selectedProduct.getNombre()+"-"+this.selectedProduct.getColor()+"-"+this.selectedProduct.getSize()+"-R");
		        	this.selectedProduct.setCode(codeBaseProduct+"-"+this.selectedProduct.getSize()+"-"+this.selectedProduct.getColor());
		        	this.selectedProduct.setInventoryStatus(InventoryStatus.INSTOCK.getText());
		        	
		        	
		        	if(this.uploadedData.getImages().isEmpty()) {
		        		System.out.println("imagen vacia: "+this.uploadedData.getImages().isEmpty());
		        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No cargo imagen, el produto no fue guardado"));
		        	}else {
		        		this.selectedProduct.setImage(uploadedData.getImages().get(0));
		        	
		        	for(String size:checkboxView.getSelectedSizes()) {
		        		this.selectedProduct.setIdProducto(this.selectedProduct.getNombre()+"-"+this.selectedProduct.getColor()+"-"+size+"-R");
			        	this.selectedProduct.setCode(codeBaseProduct+"-"+size+"-"+this.selectedProduct.getColor());
		        		this.selectedProduct.setSize(size);
		        		query.addProduct(selectedProduct);
		        		

		        	}
		        
		        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Creado"));
		        	}
	
		        }
		        else {
		        	if(query.updateProduct(this.selectedProduct.getCode(), 
		        			                    this.selectedProduct.getNombre(),
		        			                     this.selectedProduct.getDescription(),
		        			                      this.selectedProduct.getPrice(),
		        			                         this.selectedProduct.getQuantity())) {
		        		
		        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado"));
		        	}else {
		        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El producto no pudo ser actualizado"));
		        	}
		        	
		            
		        }
		        
		        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		        PrimeFaces.current().executeScript("window.location.reload()");
		        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		        
		        
				
    	}catch (Exception e) {
    		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallo al crear o actualizar produto"));
			System.out.println("Fallo al crear o actualizar produto");
			System.out.println(e);
		}
		    	
    }

    public void deleteProduct() {
    	System.out.println(this.selectedProduct);
    	Query query = new Query();
    	
    	if(query.deleteProductById(this.selectedProduct.getCode())) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado"));
    	}else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto No pudo ser eliminado"));
    	}
    	 FacesContext facesContext = FacesContext.getCurrentInstance();
		 NavigationHandler nav = facesContext.getApplication().getNavigationHandler();
		 nav.handleNavigation(facesContext, null, "adminProducts");

        
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public String getDeleteButtonMessage() {
        if (!hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " productos seleccionados" : "1 producto seleccionado";
        }

        return "Eliminar";
    }

    
    public boolean hasSelectedProducts() {
        return !(this.selectedProducts != null && !this.selectedProducts.isEmpty());
    }

    public void deleteSelectedProducts() {
    	//System.out.println("se elimino producto");
    	//System.out.println(this.selectedProducts.toString());
        //this.products.removeAll(this.selectedProducts);
        //this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se eliminaron los productos"));
        PrimeFaces.current().ajax().update("form:form:messages", "form:form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
        FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nav = facesContext.getApplication().getNavigationHandler();
		nav.handleNavigation(facesContext, null, "home");
    }
    
    public void onItemUnselect(UnselectEvent<EventHandler> event) {
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
 
    @Override
	public String toString() {
		return "CrudView [selectedProduct=" + selectedProduct + ", selectedProducts=" + selectedProducts + "]";
	}
}
