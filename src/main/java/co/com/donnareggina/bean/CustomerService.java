package co.com.donnareggina.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.Customer;

@ManagedBean(name="customerData")
@ViewScoped
public class CustomerService {
	
	private List <Customer> customers;
	
	
	@PostConstruct
	public void init() {
		Query query = new Query();
		customers = query.getAllcustomer();
	}


	public List<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}


	
	
	
}
