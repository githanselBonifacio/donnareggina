package co.com.donnareggina.bean;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
	 
	 private List<String> colors;
	 private List<String> sizes;
	
	 private  List<Product> optionsProductBuy;
	 private int selectedProductQuantity;
	 private String nombreProduct;
	 
	 private List<byte[]> imageOption;
	 private List<ResponsiveOption> responsiveOptionsImage;
	 private int activeIndex = 0;
	 
	 private Product productSend;
	 private Query query;
	
	 
	 
	 
	@PostConstruct
	public void init() {
		
		query = new Query();
		productSend = new Product();
		
		this.responsiveOptionsImage = new ArrayList<>();
		this.responsiveOptionsImage.add(new ResponsiveOption("1500px", 5));
		this.responsiveOptionsImage.add(new ResponsiveOption("1024px", 3));
		this.responsiveOptionsImage.add(new ResponsiveOption("768px", 2));
		this.responsiveOptionsImage.add(new ResponsiveOption("560px", 1));
		
		this.imageOption = new ArrayList<byte[]>();
		selectedProductQuantity = 1;
		
		colors = new ArrayList<String>();
		sizes = new ArrayList<String>();
		
		
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

	public String getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}

	public String getSelectedSize() {
		return selectedSize;
	}


	public void setSelectedSize(String selectedSize) {
		this.selectedSize = selectedSize;
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


	public List<Product> getOptionsProductBuy() {
		return optionsProductBuy;
	}


	public void setOptionsProductBuy(List<Product> optionsProductBuy) {
		this.optionsProductBuy = optionsProductBuy;
	}



	public int getSelectedProductQuantity() {
		return selectedProductQuantity;
	}


	public void setSelectedProductQuantity(int selectedProductQuantity) {
		this.selectedProductQuantity = selectedProductQuantity;
	}

	public String getNombreProduct() {
		return nombreProduct;
	}


	public void setNombreProduct(String nombreProduct) {
		this.nombreProduct = nombreProduct;
	}


	public List<byte[]> getImageOption() {
		return imageOption;
	}


	public void setImageOption(List<byte[]> imageOption) {
		this.imageOption = imageOption;
	}


	public List<ResponsiveOption> getResponsiveOptionsImage() {
		return responsiveOptionsImage;
	}


	public void setResponsiveOptionsImage(List<ResponsiveOption> responsiveOptionsImage) {
		this.responsiveOptionsImage = responsiveOptionsImage;
	}


	public int getActiveIndex() {
		return activeIndex;
	}



	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}



	public Product getProductSend() {
		return productSend;
	}



	public void setProductSend(Product productSend) {
		this.productSend = productSend;
	}


	public void redirect(String rol) {
		try {
		
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
		   	    		   colors.add(p.getColor());
						}
					this.colors = colors.stream().distinct().collect(Collectors.toList());
					System.out.println(this.selectedColor);
					onChangeColor();
				}
			}
		
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

   public void onChangeColor() {
	   System.out.println("entro a cambiar color");
	   List <Product> listProduct =  query.getProductByName(this.selectedProduct.getNombre());
	   for(Product p:listProduct) {
		   if(p.getColor().equals(this.selectedColor)) {
			   this.sizes.add(p.getSize());
		   }
	   }

   }

	public void addCart() {
		try {
			System.out.println("entro a agregar producto");
			System.out.println(this.selectedColor);
			System.out.println(this.selectedProductQuantity);
			System.out.println(this.selectedProduct);
		
		}catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}
	public boolean isSizeMajorOne() {
		return imageOption.size()>1;
	}
	
	public int getSizeOptionImagen() {
		return imageOption.size();
	}
}
