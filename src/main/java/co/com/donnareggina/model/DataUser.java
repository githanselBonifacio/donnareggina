package co.com.donnareggina.model;

import java.io.Serializable;
import java.sql.Date;



public class DataUser  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String identificationType;
	private String identificationNumber;
	private String firsName;
	private String SecondName;
	private String lastName;
	private String SecondLastName;
	private Date   dateBirth;
	private String email;
	
	
	public String getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
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
		return SecondName;
	}
	public void setSecondName(String secondName) {
		SecondName = secondName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSecondLastName() {
		return SecondLastName;
	}
	public void setSecondLastName(String secondLastName) {
		SecondLastName = secondLastName;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return firsName+" "+lastName;
	}
	
	

}
