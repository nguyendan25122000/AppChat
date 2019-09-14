package entitis;

public class Content {
	private int idCt;
	private String content;

	public int getIdCt() {
		return idCt;
	}

	public void setIdCt(int idCt) {
		this.idCt = idCt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param idCt
	 * @param content
	 */
	public Content(int idCt, String content) {
		super();
		this.idCt = idCt;
		this.content = content;
	}

	/**
	 * 
	 */
	public Content() {
	}

}
