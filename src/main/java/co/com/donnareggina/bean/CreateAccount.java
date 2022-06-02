package co.com.donnareggina.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import co.com.donnareggina.database.Query;
import co.com.donnareggina.model.DataUser;
import co.com.donnareggina.model.User;


@ManagedBean(name="createAccount")
@SessionScoped
public class CreateAccount {
	
	private String userName;
	private String password;
	private String passwordRepeat;
	private User userNew;
	
	private DataUser dataUser;
	
	private String identificationType;
	private String identificatorNumber;
	private String firsName;
	private String secondName;
	private String lastName;
	private String secondLastName;
	private String birthDate;
	private String email;
	
	@PostConstruct  
    public void init(){
		this.dataUser = new DataUser();
	}
	
	
	@Override
	public String toString() {
		return "CreateAccount [userName=" + userName + ", password=" + password + ", passwordRepeat=" + passwordRepeat
				+ ", userNew=" + userNew + ", dataUser=" + dataUser + ", identificationType=" + identificationType
				+ ", identificatorNumber=" + identificatorNumber + ", firsName=" + firsName + ", secondName="
				+ secondName + ", LastName=" + lastName + ", secondLastName=" + secondLastName + ", birthDate="
				+ birthDate + ", email=" + email + "]";
	}
	public String getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}
	public String getIdentificatorNumber() {
		return identificatorNumber;
	}
	public void setIdentificatorNumber(String identificatorNumber) {
		this.identificatorNumber = identificatorNumber;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getFirsName() {
		return firsName;
	}
	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}
	public String getSecondLastName() {
		return secondLastName;
	}
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DataUser getDataUser() {
		return dataUser;
	}
	public void setDataUser(DataUser dataUser) {
		this.dataUser = dataUser;
	}
	public User getUserNew() {
		return userNew;
	}
	public void setUserNew(User userNew) {
		this.userNew = userNew;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordRepeat() {
		return passwordRepeat;
	}
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	
	public void createUser() {
		Query query = new Query();
		
		if(password.equals(passwordRepeat)) {
			boolean isCreate = query.createAccount(userName,password);
			if(isCreate) {
				userNew = query.getUserAccount(userName, password).get(0); 
				FacesContext facesContext = FacesContext.getCurrentInstance();
				NavigationHandler nav = facesContext.getApplication().getNavigationHandler();
				nav.handleNavigation(facesContext, null, "registerData");
			}else {
				error("no se pudo crear cuenta intente mas tarde");
			}
		}else {
			error("Las contraseñas no coiciden");
		}
	}
	public void registerDataUser() {
		Query query = new Query();
		 buildDataUser();
		
		boolean isRegister = query.createDataCustomer(userNew.getIdUser(),this.dataUser);
		if(isRegister) {
			
			corret("Datos fueron enviados correctamente, por favor inicie sessión");
			setPassword("");
			setPasswordRepeat("");
			setUserName("");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			NavigationHandler nav = facesContext.getApplication().getNavigationHandler();
			nav.handleNavigation(facesContext, null, "register-login");
			cleanData();
		}else {
			error("No se pudo enviar datos");
			cleanData();
		}
	}
	public void buildDataUser() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.dataUser.setIdentificationType(identificationType);
		this.dataUser.setIdentificationNumber(identificatorNumber);
		this.dataUser.setFirsName(firsName);
		this.dataUser.setSecondName(secondName);
		this.dataUser.setLastName(lastName);
		this.dataUser.setSecondLastName(secondLastName);
		try {
			Date date = sdf.parse(birthDate);
			long dateLong = date.getTime();
			this.dataUser.setDateBirth(new java.sql.Date(dateLong));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.dataUser.setEmail(email);
		System.out.println(this);
	}
	
	public void cleanData() {
		this.dataUser.setIdentificationNumber("");
		this.dataUser.setFirsName("");
		this.dataUser.setSecondName("");
		this.dataUser.setLastName("");
		this.dataUser.setSecondLastName("");
		this.dataUser.setDateBirth(null);
		
		setFirsName("");
		setSecondLastName("");
		setLastName("");
		setSecondLastName("");
		setBirthDate("");
		setIdentificatorNumber("");
		setEmail("");
	}
	
	public void error(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",msg));
    }
	public void corret(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto",msg));
    }
}
