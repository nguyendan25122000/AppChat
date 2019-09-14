package entitis;

import java.util.Date;

public class Group {
	private int idG;
	private String nameG;
	private Date dateCreate;

	public int getIdG() {
		return idG;
	}

	public void setIdG(int idG) {
		this.idG = idG;
	}

	public String getNameG() {
		return nameG;
	}

	public void setNameG(String nameG) {
		this.nameG = nameG;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	/**
	 * @param idG
	 * @param nameG
	 * @param dateCreate
	 */
	public Group(String nameG, Date dateCreate) {
		this.nameG = nameG;
		this.dateCreate = dateCreate;
	}

	/**
	 * 
	 */
	public Group() {
		// TODO Auto-generated constructor stub
	}

}
