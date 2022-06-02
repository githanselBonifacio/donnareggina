package co.com.donnareggina.bean;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.ResponsiveOption;
import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.Product;
import co.com.donnareggina.model.ProductViewData;


@ManagedBean(name="manageCart")
@ViewScoped
public class CartView implements Serializable{
	
	
	private static final long serialVersionUID = 7641226470440782056L;

	@ManagedProperty(value = "#{carouselView}")
	private CarouselView  carouselView;
	
	ProductViewData selectedProduct;
	
	
	 private String selectedColor;
	
	 private String selectedSize;
	 
	 @ManagedProperty(value = "#{producServiceView}")
	 private ProductService productService;
	 
	 private Map<String, String> colors;
	 private Map<String, String> sizes;
	 private Map<String,Map<String, Map<String, String>>> dataProduct; 
	 
	 private  List<Product> optionsProductBuy;
	 private int selectedProductQuantity;
	 private String nombreProduct;
	 
	 private List<byte[]> imageOption;
	 private List<ResponsiveOption> responsiveOptionsImage;
	 private int activeIndex = 0;
	 
	 private Product productSend;
	
	
	 
	 
	 
	@PostConstruct
	public void init() {
		
		productSend = new Product();
		
		this.responsiveOptionsImage = new ArrayList<>();
		this.responsiveOptionsImage.add(new ResponsiveOption("1500px", 5));
		this.responsiveOptionsImage.add(new ResponsiveOption("1024px", 3));
		this.responsiveOptionsImage.add(new ResponsiveOption("768px", 2));
		this.responsiveOptionsImage.add(new ResponsiveOption("560px", 1));
		
		this.imageOption = new ArrayList<byte[]>();
		selectedProductQuantity = 1;
		
		colors = new HashMap <>();
		sizes = new HashMap <>();
		dataProduct = productService.getDataProduct();
	}
	
	



	public Map<String, String> getColors() {
		return colors;
	}


	public void setColors(Map<String, String> colors) {
		this.colors = colors;
	}


	public Map<String, String> getSizes() {
		return sizes;
	}


	public void setSizes(Map<String, String> sizes) {
		this.sizes = sizes;
	}


	public Product getProductSend() {
		return productSend;
	}


	public void setProductSend(Product productSend) {
		this.productSend = productSend;
	}


	public String getSelectedSize() {
		return selectedSize;
	}


	public void setSelectedSize(String selectedSize) {
		this.selectedSize = selectedSize;
	}


	public String getNombreProduct() {
		return nombreProduct;
	}


	public void setNombreProduct(String nombreProduct) {
		this.nombreProduct = nombreProduct;
	}


	public int getSelectedProductQuantity() {
		return selectedProductQuantity;
	}


	public void setSelectedProductQuantity(int selectedProductQuantity) {
		this.selectedProductQuantity = selectedProductQuantity;
	}


	public int getActiveIndex() {
		return activeIndex;
	}


	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}


	public List<ResponsiveOption> getResponsiveOptionsImage() {
		return responsiveOptionsImage;
	}


	public void setResponsiveOptionsImage(List<ResponsiveOption> responsiveOptionsImage) {
		this.responsiveOptionsImage = responsiveOptionsImage;
	}


	public List<byte[]> getImageOption() {
		return imageOption;
	}


	public void setImageOption(List<byte[]> imageOption) {
		this.imageOption = imageOption;
	}



	public List<Product> getOptionsProductBuy() {
		return optionsProductBuy;
	}


	public void setOptionsProductBuy(List<Product> optionsProductBuy) {
		this.optionsProductBuy = optionsProductBuy;
	}


	public String getSelectedColor() {
		return selectedColor;
	}


	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}


	public CarouselView getCarouselView() {
		return carouselView;
	}


	public void setCarouselView(CarouselView carouselView) {
		this.carouselView = carouselView;
	}



	public ProductViewData getSelectedProduct() {
		return selectedProduct;
	}


	public void setSelectedProduct(ProductViewData selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	
	
	public ProductService getProductService() {
		return productService;
	}


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public Map<String, Map<String, Map<String, String>>> getDataProduct() {
		return dataProduct;
	}


	public void setDataProduct(Map<String, Map<String, Map<String, String>>> dataProduct) {
		this.dataProduct = dataProduct;
	}


	public void redirect(String rol) {
		try {
			
			Query query = new Query();
			this.optionsProductBuy = query.getProductByName(this.selectedProduct.getNombre());
			this.imageOption = query.getProductByNameImage(this.selectedProduct.getNombre());
			this.productSend.setNombre(this.selectedProduct.getNombre());
			
			//colors and sizes
			
			
			if(rol.equals("ADMIN")) {
				
				PrimeFaces.current().executeScript("PF('manageCartAdmin').show()");
			}else {
				if(rol.equals("")){
					PrimeFaces.current().executeScript("PF('goInitSesion').show()");
				
				}else {
					PrimeFaces.current().executeScript("PF('manageCartCustomer').show()");
					
					List <Product> listProduct =  query.getProductByName(this.selectedProduct.getNombre());
					for(Product p:listProduct) {
		   	    		   colors.put(p.getColor(), p.getColor());
						}
	
				}
			}
			
		
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
   public void onChangeColor() {
	 System.out.println("entro a cambiar color");
   }

	public void addCart() {
		try {
			System.out.println("entro a agragar producto");
			System.out.println(this.selectedColor);
			System.out.println(this.selectedProductQuantity);
			System.out.println(this.selectedProduct);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
