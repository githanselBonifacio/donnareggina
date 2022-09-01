package co.com.donnareggina.database;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.com.donnareggina.model.Addresser;
import co.com.donnareggina.model.Customer;
import co.com.donnareggina.model.DataUser;
import co.com.donnareggina.model.Product;
import co.com.donnareggina.model.ProductViewData;
import co.com.donnareggina.model.Sale;
import co.com.donnareggina.model.User;

public class Query extends Setup{
	
	private List<Integer> listIdeCustomer;

	public List <Product> buildProduct(ResultSet resultSet){
		List <Product> listProduct = new ArrayList<>();
		try {
			while (resultSet.next()){  
				listProduct.add(
							new Product(
									resultSet.getString(1),
									resultSet.getString(2),
									resultSet.getString(3),
									resultSet.getString(4),
									
									resultSet.getDouble(5),
									
									resultSet.getString(6),
									resultSet.getString(7),
									resultSet.getString(8),
									
									resultSet.getInt   (9),
									
									resultSet.getString(10),
									
									resultSet.getInt   (11),
									resultSet.getInt   (12),
									resultSet.getBytes (13)
									)
							
							) ;
				
			 }
		} catch (SQLException e) {
			System.out.println("Error al consitruir producto");
			e.printStackTrace();
		}
		return listProduct;
	}
	public List <Product> getAllProductByIdProduct(String idProduct) {
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(String.format("SELECT *  from product WHERE idproduct='%s'",idProduct));
			
			
		List <Product> listProduct =buildProduct(resultSet);
		
		
		return listProduct;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List <Product> getProductByName(String nombre){
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(String.format("SELECT *  from product WHERE nombre ='%s' and quantity>0",nombre));
			
			
		List <Product> listProduct =buildProduct(resultSet);
		
		
		return listProduct;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List<String> getNameDistictProduct() {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT DISTINCT nombre from product WHERE  quantity>0");
			
		List<String> productOption = new ArrayList<String>();
		
		while (resultSet.next()){ 
			
			productOption.add(resultSet.getString("nombre"));
		}
		
		return productOption;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<byte[]> getProductByNameImage(String nombre){
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(String.format("SELECT DISTINCT color ,image  from product WHERE nombre ='%s' and quantity>0",nombre));
			
		List<byte[]> imageOption = new ArrayList<byte[]>();
		
		while (resultSet.next()){ 
			
			imageOption.add(resultSet.getBytes(2));
		}
		
		return imageOption;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List <Product> getAllProduct() {
		List <Product> listProduct=null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT *  from product" );
			
			listProduct =buildProduct(resultSet);
			
			
			return listProduct;
	
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList <Product>();
		}
	}
	public List <ProductViewData> getAllProductView() {
		List <ProductViewData> listProduct = new ArrayList<>();
		try {
			List <String> listIdProduct = new ArrayList<>();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT DISTINCT nombre from product" );
			
			while (resultSet.next()){  
				listIdProduct.add(resultSet.getString(1)) ;
			 }
			for(String id:listIdProduct) {
				String sql =String.format( "SELECT idproduct, nombre, description,price,category,quantity,inventorystatus,rating,solicitadasventa,image "
						+ "from product "
						+ "WHERE nombre = '%s' "
						+ "LIMIT 1",id);
				
				
				ResultSet resultSetProducts = statement.executeQuery(sql);
				
				while (resultSetProducts.next()){  
					listProduct.add(
							new ProductViewData(
									resultSetProducts.getString(1),
									resultSetProducts.getString(2),
									resultSetProducts.getString(3),
									resultSetProducts.getDouble(4),
									resultSetProducts.getString(5),
									resultSetProducts.getInt   (6),
									resultSetProducts.getString(7),
									resultSetProducts.getInt   (8),
									resultSetProducts.getInt   (9),
									resultSetProducts.getBytes (10)
									)
							
							) ;
				 }
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProduct;
	}
	
	public boolean addProduct(Product product) {
		
		 try {
			PreparedStatement pstm = connection.prepareStatement("INSERT into " +
                    " product(idproduct, code,nombre,description,price,category,color,talla,quantity,inventoryStatus,rating,solicitadasVenta,image) " + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pstm.setString(1, product.getIdProducto());
			pstm.setString(2, product.getCode());
			pstm.setString(3, product.getNombre());
			pstm.setString(4, product.getDescription());
			pstm.setDouble(5, product.getPrice());
			pstm.setString(6, product.getCategory());
			pstm.setString(7, product.getColor());
			pstm.setString(8, product.getSize());
			pstm.setInt(9, product.getQuantity());
			pstm.setString(10, product.getInventoryStatus());
			pstm.setInt(11, product.getRating());
			pstm.setInt(12, product.getSolicitadasVenta());
			pstm.setBytes(13, product.getImage());
			
			pstm.execute();
			pstm.close();
			System.out.println("producto agregado");
			return true;
		 } catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	public boolean deleteProductById(String code) {
		
		try {
		
			PreparedStatement pstm =connection.prepareStatement(String.format("DELETE FROM product WHERE code = '%s'",code ));
			pstm.execute();
			pstm.close();
			return true;
			
		} catch (SQLException e) {
			System.out.println("Error al eliminar producto");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean updateProduct(String code,String nombre, String description,double price, int quantity) {
		try {
			PreparedStatement pstm = connection.prepareStatement("UPDATE product "+
			                                           " SET nombre = '"+nombre+"',"+
			                                           " description = '"+description+"',"+
			                                           " price = "+price+","+
			                                           " quantity = "+quantity+
			                                           " WHERE code = '"+code+"';");
			pstm.execute();
			pstm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	public boolean addImage(String idImageApp, byte[] image) {
	
        try {
             
             PreparedStatement pstm = connection.prepareStatement("INSERT into " +
                        " images(idimageapp, image) " + " VALUES(?,?)");
         
             pstm.setString(1,idImageApp);
             pstm.setBytes(2,image);
             pstm.execute();
             pstm.close();
           
             return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
		
	}
	
	@SuppressWarnings("resource")
	public byte[] getImageLocal(String path) {
		 FileInputStream fis = null;
		   
	     File file = new File(path);
	     try {
			fis = new FileInputStream(file);
			return fis.readAllBytes();
			
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
			
		}
	}
	
	public ArrayList<byte[]> getImages(String idImageApp) {
		ArrayList<byte[]> dml = new ArrayList<byte[]>();
		 
	        try {
	 
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT idImageApp,image FROM images WHERE idImageApp like '"+idImageApp+"%'" );
	            
	          while (resultSet.next()){  
	        	  byte[] bfImage = resultSet.getBytes("image");
	            
	           
	            dml.add(bfImage);
	           
	          }
	       }
	       catch (SQLException ex) {
	          System.err.println(ex.getMessage());
	       }
	       return dml;
	    }
	
	public  boolean createAccount(String userName, String password) {
		try {
			PreparedStatement pstm = connection.prepareStatement(
					"INSERT INTO users (nameuser, passworduser,rol)"+" VALUES ('"+userName+"',PGP_SYM_ENCRYPT('"+password+"','AES_KEY'),'CUSTOMER')"
				);
			
			pstm.execute();
            pstm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList <User> getUserAccount(String nameUser, String password) {
		ArrayList<User> dml = new ArrayList<User>();
		
		try {
			 
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT iduser, nameuser,pgp_sym_decrypt(passworduser::bytea,'AES_KEY'), rol"+
            
            		" FROM users WHERE nameuser='"+nameUser+"' and pgp_sym_decrypt(passworduser::bytea,'AES_KEY') ='"+password+"';");
          
            
            while (resultSet.next())
          {  
        	  User user = new User();
        	  user.setIdUser(resultSet.getInt("iduser"));
        	  user.setUserName(resultSet.getString("nameuser"));
        	  user.setRol(resultSet.getString("rol"));

            dml.add(user);
          }
              
        	  return dml;
		}
       catch (SQLException ex) {
    	   System.err.println(ex.getMessage());
    	   return null;
       }
	}
	public ArrayList <DataUser> getDataUser(int idUser) {
		
		ArrayList<DataUser> dml = new ArrayList<DataUser>();
		
		try {
			 
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from customer WHERE iduser="+idUser);
            
       
            while (resultSet.next())
          {  
              DataUser user = new DataUser();
        	  user.setIdentificationType(resultSet.getString(2));
        	  user.setIdentificationNumber(resultSet.getString(3));
        	  user.setFirsName(resultSet.getString(4));
        	  user.setSecondName(resultSet.getString(5));
        	  user.setLastName(resultSet.getString(6));
        	  user.setSecondLastName(resultSet.getString(7));
        	  user.setDateBirth(resultSet.getDate(9));
        	  user.setEmail(resultSet.getString(10));
            dml.add(user);
          }

        	  return dml;
		}
       catch (SQLException ex) {
    	   System.err.println(ex.getMessage());
    	   return null;
       }
	}
	public  boolean createDataCustomer(int idUser, DataUser dataUser) {
		try {
			PreparedStatement pstm = connection.prepareStatement(
					"INSERT INTO customer (iduser, identificaciontype,identificationnumber,firsname,secondname,lastname,secondlastname,datebirth,email)"+
			        " VALUES (?,?,?,?,?,?,?,?,?)"
				);
			pstm.setInt   (1, idUser);
			pstm.setString(2,dataUser.getIdentificationType() );
			pstm.setString(3, dataUser.getIdentificationNumber());
			pstm.setString(4, dataUser.getFirsName());
			pstm.setString(5, dataUser.getSecondName());
			pstm.setString(6, dataUser.getLastName());
			pstm.setString(7, dataUser.getSecondLastName());
			pstm.setDate  (8,(Date) dataUser.getDateBirth());
			pstm.setString(9, dataUser.getEmail());
			pstm.execute();
            pstm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public  boolean createCustomer(int idUser, Customer customer) {
		try {
			PreparedStatement pstm = connection.prepareStatement(
					"INSERT INTO customer (iduser, identificaciontype,identificationnumber,firsname,secondname,lastname,secondlastname,phone,email)"+
			        " VALUES (?,?,?,?,?,?,?,?,?)"
				);
			pstm.setInt   (1, idUser);
			pstm.setString(2,customer.getIdetificationType() );
			pstm.setString(3, customer.getIdentificationNumber());
			pstm.setString(4, customer.getFirsName());
			pstm.setString(5, customer.getSecondName());
			pstm.setString(6, customer.getLastName());
			pstm.setString(7, customer.getSecondLastname());
			pstm.setString(8 ,customer.getPhone());
			pstm.setString(9, customer.getEmail());
			pstm.execute();
            pstm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public List <Customer> getAllcustomer() {
		List <Customer> listCustomer=new ArrayList<Customer>();
		listIdeCustomer = new ArrayList<Integer>();
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT *  from users where rol = 'CUSTOMER'" );
			
			
			while (resultSet.next()){ 
				listIdeCustomer.add(resultSet.getInt(1));
				   
		    }
		
			for(int id:listIdeCustomer) {
				ResultSet resultSetCustomer = statement.executeQuery("SELECT * from customer where iduser="+id+";" );
				while (resultSetCustomer.next()) {
					
					listCustomer.add(
							
							new Customer(resultSetCustomer.getString(1), 
										 resultSetCustomer.getString(2), 
										 resultSetCustomer.getString(3), 
										 resultSetCustomer.getString(4), 
										 resultSetCustomer.getString(5), 
										 resultSetCustomer.getString(6), 
										 resultSetCustomer.getString(7), 
										 resultSetCustomer.getString(9),
										 resultSetCustomer.getString(10)
										 )
							
							);
					
					
				}
			}

			return listCustomer;
	
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList <Customer>();
		}
	}
	
	public int getNumberProductInCart(String identificationNumber) {
		int number_product = 0;
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT count(*) from shoppingcart where idcustomer= '"+identificationNumber+"';" );
			
			while (resultSet.next()) {
				number_product = resultSet.getInt(1);
				
			}
			
			return number_product;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	
		
	}
	public List<Product> getProductCartByCustomer(int idUser) {
		try {
		
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT *"
					+ " from product"
					+ " INNER JOIN shoppingcart ON shoppingcart.idproduct = product.code"
					+ " INNER JOIN customer ON customer.identificationnumber = shoppingcart.idcustomer"
					+ " INNER JOIN users ON users.iduser = customer.iduser"
					+ " WHERE users.iduser ="+idUser+";");
			
		
			List<Product> listProduct = buildProduct(resultSet);
					
			return listProduct;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Product>();
		}
	}
	public boolean addAddressCustomer(String idCustomer,Addresser addresser) {
		try {
			PreparedStatement pstm = connection.prepareStatement(
					"INSERT INTO addresscustomer (iddustomer, mainavenuetype,mainavenuenumber,secondavenuenumber,homenumber,department,municipality,additionaldescription)"+
			        " VALUES (?,?,?,?,?,?,?,?)"
				);
			pstm.setString(1, idCustomer);
			pstm.setString(2, addresser.getMainAvenueType());
			pstm.setString(3, addresser.getMainAvenueNumber());
			pstm.setString(4, addresser.getSecondAvenueNumber());
			pstm.setString(5, addresser.getHomeNumber());
			pstm.setString(6, addresser.getDepartment());
			pstm.setString(7, addresser.getMinicipally());
			pstm.setString(8 ,addresser.getAdditonalDescription());
			pstm.execute();
            pstm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean addSale(String billNumber,Sale sale) {
		try {
			PreparedStatement pstm = connection.prepareStatement(
					"INSERT INTO sales (billnumber,idcustomer,datesale,idproduct,quantity,ispaid,status)"+
			        " VALUES (?,?,?,?,?,?,?)"
				);
			pstm.setString (1, billNumber);
			pstm.setString (2, sale.getIdCustomer());
			pstm.setDate   (3, sale.getDateSale());
			pstm.setString (4, sale.getIdProduct());
			pstm.setInt    (5, sale.getQuantity());
			pstm.setBoolean(6, sale.isPaid());
			pstm.setString( 7, sale.getStatus());
			
			pstm.execute();
            pstm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public List <Sale> buildSalesTable(ResultSet resultSet){
		List <Sale> listSales = new ArrayList<>();
		try {
			while (resultSet.next()){
				

				listSales.add(
							new Sale(
									resultSet.getString (1),
									resultSet.getString (2),
									resultSet.getDate   (3),
									resultSet.getBoolean(4),
									resultSet.getString (5)

									)
							
							) ;
				
			 }
		} catch (SQLException e) {
			System.out.println("Error al consitruir sale");
			e.printStackTrace();
		}
		return listSales;
	}
	
	public List <Sale> getAllSalesTable() {
		List <Sale> listSales=null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT DISTINCT billnumber, idcustomer, datesale, ispaid,status from sales" );
			
			listSales =buildSalesTable(resultSet);
			
			
			return listSales;
	
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList <Sale>();
		}
	}
	public List<Product> getProductSale(String billNumber){
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT "
					+" product.idproduct,product.code,product.nombre,product.description,"
					+"product.price,product.category,product.color,product.talla,sales.quantity,product.inventorystatus,"
					+"product.rating,product.solicitadasventa,product.image "
					+"from product "
					+"INNER JOIN sales ON sales.idproduct = product.code "
					+"WHERE sales.billnumber = '"+billNumber+"'");
			
		
			List<Product> listProduct = buildProduct(resultSet);
					
			return listProduct;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Product>();
		}
	
	}
	public Customer getCustomerSale(String billNumber) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT customer.* "
					+ "from customer "
					+ "INNER JOIN sales ON sales.idcustomer = customer.identificationnumber "
					+ "WHERE sales.billnumber = '"+billNumber+"'"
					+ "LIMIT 1");

			Customer customer = buildCustomer(resultSet);
					
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
			return new Customer();
		}
	}
	public Customer buildCustomer(ResultSet resultSet){
		
		try {
			while (resultSet.next()){
				
					return	new Customer(
									resultSet.getString (1),
									resultSet.getString (2),
									resultSet.getString (3),
									resultSet.getString (4),
									resultSet.getString (5),
									resultSet.getString (6),
									resultSet.getString (7),
									resultSet.getString (9),
									resultSet.getString (10)
									);
				
			 }
		} catch (SQLException e) {
			System.out.println("Error al consitruir customer");
			e.printStackTrace();
		}
		return new Customer();
	}
	public Addresser buildAddresser(ResultSet resultSet) {
		Addresser addresser = null;
		try {
			while (resultSet.next()){
				
				addresser =	new Addresser(
									resultSet.getString (2),
									resultSet.getString (3),
									resultSet.getString (4),
									resultSet.getString (5),
									resultSet.getString (6),
									resultSet.getString (7),
									resultSet.getString (8),
									resultSet.getString (9)
									);
				
			 }
			return addresser;
		} catch (SQLException e) {
			System.out.println("Error al consitruir addresser");
			e.printStackTrace();
			return new Addresser();
		}

	}
	public Addresser getAddresserSale(String billNumber) {
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"Select addresscustomer.*"+
					" from addresscustomer"+
					" INNER JOIN customer ON customer.identificationnumber = addresscustomer.iddustomer"+
					" INNER JOIN sales ON sales.idcustomer = customer.identificationnumber"+
					" WHERE sales.billnumber = '"+billNumber+"'"+
					" LIMIT 1;");
		
			return buildAddresser(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Addresser();
		}
	}
} 
