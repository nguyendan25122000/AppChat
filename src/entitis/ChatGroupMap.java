package entitis;

import java.util.Date;

public class ChatGroupMap {
	private int idCG;
	private int idG;
	private int idUs;
	private int idCt;

	private String nameG;
	private Date dateCreate;

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

	public int getIdCG() {
		return idCG;
	}

	public void setIdCG(int idCG) {
		this.idCG = idCG;
	}

	public int getIdG() {
		return idG;
	}

	public void setIdG(int idG) {
		this.idG = idG;
	}

	public int getIdUs() {
		return idUs;
	}

	public void setIdUs(int idUs) {
		this.idUs = idUs;
	}

	public int getIdCt() {
		return idCt;
	}

	public void setIdCt(int idCt) {
		this.idCt = idCt;
	}

}
