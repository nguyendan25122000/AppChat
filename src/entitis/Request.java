package entitis;

public class Request {
	private int idR;
	private int idUs1;
	private int idUs2;
	private int state;
	private int idCt;

	public int getIdR() {
		return idR;
	}

	public void setIdR(int idR) {
		this.idR = idR;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIdCt() {
		return idCt;
	}

	public void setIdCt(int idCt) {
		this.idCt = idCt;
	}

	/**
	 * @param idR
	 * @param idUs1
	 * @param idUs2
	 * @param state
	 * @param type
	 * @param idCt
	 */
	public Request(int idR, int idUs1, int idUs2, int state, int idCt) {
		super();
		this.idR = idR;
		this.idUs1 = idUs1;
		this.idUs2 = idUs2;
		this.state = state;
		this.idCt = idCt;
	}

	/**
	 * 
	 */
	public Request() {
		// TODO Auto-generated constructor stub
	}

}
