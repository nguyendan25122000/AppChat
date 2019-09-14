package entitis;

public class ChatBox {
	private int idCB;
	private int idUs1;
	private int idUs2;
	private int idCt;

	public int getIdCB() {
		return idCB;
	}

	public void setIdCB(int idCB) {
		this.idCB = idCB;
	}

	public int getIdUs1() {
		return idUs1;
	}

	public void setIdUs1(int idUs1) {
		this.idUs1 = idUs1;
	}

	public int getIdUs2() {
		return idUs2;
	}

	public void setIdUs2(int idUs2) {
		this.idUs2 = idUs2;
	}

	public int getIdCt() {
		return idCt;
	}

	public void setIdCt(int idCt) {
		this.idCt = idCt;
	}

	/**
	 * @param idCB
	 * @param idUs1
	 * @param idUs2
	 * @param idCt
	 */
	public ChatBox(int idCB, int idUs1, int idUs2, int idCt) {
		this.idCB = idCB;
		this.idUs1 = idUs1;
		this.idUs2 = idUs2;
		this.idCt = idCt;
	}

	/**
	 * 
	 */
	public ChatBox() {
		// TODO Auto-generated constructor stub
	}

}
