package co.com.donnareggina.model;

import java.io.Serializable;

public class Addresser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String idCustomer;
	private String mainAvenueType;
	private String mainAvenueNumber;
	private String secondAvenueType;
	private String secondAvenueNumber;
	private String homeNumber;
	private String department;
	private String minicipally;
	private String additonalDescription;
	
	public Addresser() {};
	
	public Addresser(String idCustomer, String mainAvenueType, String mainAvenueNumber, String secondAvenueType,
			String secondAvenueNumber, String homeNumber, String department, String minicipally,
			String additonalDescription) {
		super();
		this.idCustomer = idCustomer;
		this.mainAvenueType = mainAvenueType;
		this.mainAvenueNumber = mainAvenueNumber;
		this.secondAvenueType = secondAvenueType;
		this.secondAvenueNumber = secondAvenueNumber;
		this.homeNumber = homeNumber;
		this.department = department;
		this.minicipally = minicipally;
		this.additonalDescription = additonalDescription;
	}
	public Addresser(String idCustomer, String mainAvenueType, String mainAvenueNumber,
			String secondAvenueNumber, String homeNumber, String department, String minicipally,
			String additonalDescription) {
		super();
		this.idCustomer = idCustomer;
		this.mainAvenueType = mainAvenueType;
		this.mainAvenueNumber = mainAvenueNumber;
		this.secondAvenueNumber = secondAvenueNumber;
		this.homeNumber = homeNumber;
		this.department = department;
		this.minicipally = minicipally;
		this.additonalDescription = additonalDescription;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getMainAvenueType() {
		return mainAvenueType;
	}
	public void setMainAvenueType(String mainAvenueType) {
		this.mainAvenueType = mainAvenueType;
	}
	public String getMainAvenueNumber() {
		return mainAvenueNumber;
	}
	public void setMainAvenueNumber(String mainAvenueNumber) {
		this.mainAvenueNumber = mainAvenueNumber;
	}
	public String getSecondAvenueType() {
		return secondAvenueType;
	}
	public void setSecondAvenueType(String secondAvenueType) {
		this.secondAvenueType = secondAvenueType;
	}
	public String getSecondAvenueNumber() {
		return secondAvenueNumber;
	}
	public void setSecondAvenueNumber(String secondAvenueNumber) {
		this.secondAvenueNumber = secondAvenueNumber;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMinicipally() {
		return minicipally;
	}
	public void setMinicipally(String minicipally) {
		this.minicipally = minicipally;
	}
	public String getAdditonalDescription() {
		return additonalDescription;
	}
	public void setAdditonalDescription(String additonalDescription) {
		this.additonalDescription = additonalDescription;
	}

	@Override
	public String toString() {
		return  mainAvenueType +" "+ mainAvenueNumber + " #"+ secondAvenueNumber + " -" + homeNumber + ", " + department + "-"+ minicipally;
	}
	
	

}
