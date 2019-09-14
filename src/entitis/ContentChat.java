package entitis;

public class ContentChat {
	private int id;
	private String nameUs;
	private String dateTime;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameUs() {
		return nameUs;
	}

	public void setNameUs(String nameUs) {
		this.nameUs = nameUs;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param id
	 * @param nameUs
	 * @param dateTime
	 * @param content
	 */
	public ContentChat(int id, String nameUs, String dateTime, String content) {
		this.id = id;
		this.nameUs = nameUs;
		this.dateTime = dateTime;
		this.content = content;
	}

	/**
	 * 
	 */
	public ContentChat() {
	}
}
