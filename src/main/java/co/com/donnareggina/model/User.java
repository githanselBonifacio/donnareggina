package co.com.donnareggina.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idUser;
	private boolean isSession;
	private String userName;
	private String rol;
	private DataUser dataUser;
	
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public boolean getIsSession() {
		return isSession;
	}
	public void setSession(boolean isSession) {
		this.isSession = isSession;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public DataUser getDataUser() {
		return dataUser;
	}
	public void setDataUser(DataUser dataUser) {
		this.dataUser = dataUser;
	}
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", isSession=" + isSession + ", userName=" + userName + ", rol=" + rol
				+ ", dataUser=" + dataUser + "]";
	}
	
	
	
		
	
}
