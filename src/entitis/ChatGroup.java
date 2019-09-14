package entitis;

public class ChatGroup {
	private int idCG;
	private int idG;
	private int idUs;
	private int idCt;

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

	/**
	 * @param idCG
	 * @param idG
	 * @param idUs
	 * @param idCt
	 */
	public ChatGroup(int idCG, int idG, int idUs, int idCt) {
		this.idCG = idCG;
		this.idG = idG;
		this.idUs = idUs;
		this.idCt = idCt;
	}

	/**
	 * 
	 */
	public ChatGroup() {
		// TODO Auto-generated constructor stub
	}

}
