package co.com.donnareggina.model;

import java.io.Serializable;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = 2332999401497694779L;
	private String idUser;
	private String idetificationType;
	private String identificationNumber;
	private String firsName;
	private String secondName;
	private String lastName;
	private String secondLastname;
	private String email;
	
	
	
	public Customer(String idUser, String idetificationType, String identificationNumber, String firsName,
			String secondName, String lastName, String secondLastname, String email) {
		super();
		this.idUser = idUser;
		this.idetificationType = idetificationType;
		this.identificationNumber = identificationNumber;
		this.firsName = firsName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.secondLastname = secondLastname;
		this.email = email;
	}



	public String getIdUser() {
		return idUser;
	}



	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}



	public String getIdetificationType() {
		return idetificationType;
	}



	public void setIdetificationType(String idetificationType) {
		this.idetificationType = idetificationType;
	}



	public String getIdentificationNumber() {
		return identificationNumber;
	}



	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}



	public String getFirsName() {
		return firsName;
	}



	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}



	public String getSecondName() {
		return secondName;
	}



	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getSecondLastname() {
		return secondLastname;
	}



	public void setSecondLastname(String secondLastname) {
		this.secondLastname = secondLastname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Customer [idUser=" + idUser + ", idetificationType=" + idetificationType + ", identificationNumber="
				+ identificationNumber + ", firsName=" + firsName + ", secondName=" + secondName + ", lastName="
				+ lastName + ", secondLastname=" + secondLastname + ", email=" + email + "]";
	}
	
	
	
	

}
