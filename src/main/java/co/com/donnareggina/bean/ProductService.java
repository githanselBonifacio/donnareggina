package co.com.donnareggina.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.Product;
import co.com.donnareggina.model.ProductViewData;

@ManagedBean(name="producServiceView")
@ViewScoped
public class ProductService implements Serializable{

	private static final long serialVersionUID = -5266230957030585094L;
	private List<ProductViewData> products;
	private List<Product> productsCrudEdit;
	


	private Map<String, Map<String, String>> dataColorSize = new HashMap<>();
	
	private Map<String,Map<String, Map<String, String>>> dataProduct = new HashMap<>();
	 
	@PostConstruct
	public void init() {
		Query query = new Query();
   	 	products = query.getAllProductView();
   	    productsCrudEdit = query.getAllProduct();
   	    
   	    List<String> nameProductDiferent = query.getNameDistictProduct();
   
   	    
   	    for( String nombreProductDistict:nameProductDiferent) {
   	    	
   	    	  List <Product> productSame = query.getProductByName(nombreProductDistict);
   	    	  Map<String,String> mapColor = new HashMap<>();
   	    	  
   	    	  for(Product p:productSame) {
   	    		   mapColor.put(p.getColor(), p.getColor());
				}
				
				for(String color:mapColor.keySet()) {
					Map<String, String> map = new HashMap<>();
					for(Product p:this.productsCrudEdit) {
						if(p.getColor().equals(color)) {
							map.put(p.getSize(), p.getSize());
						}
						
					}
					dataColorSize.put(color, map);
					
				}
				dataProduct.put(nombreProductDistict, dataColorSize);
   	    }
   	
	}
	



	public Map<String, Map<String, Map<String, String>>> getDataProduct() {
		return dataProduct;
	}



	public void setDataProduct(Map<String, Map<String, Map<String, String>>> dataProduct) {
		this.dataProduct = dataProduct;
	}



	public Map<String, Map<String, String>> getDataColorSize() {
		return dataColorSize;
	}



	public void setDataColorSize(Map<String, Map<String, String>> dataColorSize) {
		this.dataColorSize = dataColorSize;
	}



	public List<ProductViewData> getProducts() {
		return products;
	}

	public void setProducts(List<ProductViewData> products) {
		this.products = products;
	}
	

	public List<Product> getProductsCrudEdit() {
		return productsCrudEdit;
	}

	public void setProductsCrudEdit(List<Product> productsCrudEdit) {
		this.productsCrudEdit = productsCrudEdit;
	}

	
	
	public List<ProductViewData> getProducts(int size) {

        if (size > products.size()) {
            Random rand = new Random();

            List<ProductViewData> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(products.size());
                randomList.add(products.get(randomIndex));
            }

            return randomList;
        }

        else {
            return new ArrayList<>(products.subList(0, size));
        }

    }
	public void pruebaColor() {
		System.out.println("prueba exitosa");
	}
}
