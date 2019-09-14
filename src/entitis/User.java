package entitis;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class User {
	private int idUs;
	private String phoneUs;
	private String passwordUs;
	private String nameUs;
	private String imgUs;
	private Date dateUs;
	private boolean genderUs;
	private int port;
	private String ip;
	private boolean activeUs;
	private boolean checkOnline;

	public boolean isCheckOnline() {
		return checkOnline;
	}

	public void setCheckOnline(boolean checkOnline) {
		this.checkOnline = checkOnline;
	}

	public int getIdUs() {
		return idUs;
	}

	public void setIdUs(int idUs) {
		this.idUs = idUs;
	}

	public String getPhoneUs() {
		return phoneUs;
	}

	public void setPhoneUs(String phoneUs) {
		this.phoneUs = phoneUs;
	}

	public String getPasswordUs() {
		return passwordUs;
	}

	public void setPasswordUs(String passwordUs) {
		this.passwordUs = passwordUs;
	}

	public String getNameUs() {
		return nameUs;
	}

	public void setNameUs(String nameUs) {
		this.nameUs = nameUs;
	}

	public String getImgUs() {
		return imgUs;
	}

	public void setImgUs(String imgUs) {
		this.imgUs = imgUs;
	}

	public Date getDateUs() {
		return dateUs;
	}

	public void setDateUs(Date dateUs) {
		this.dateUs = dateUs;
	}

	public boolean isGenderUs() {
		return genderUs;
	}

	public void setGenderUs(boolean genderUs) {
		this.genderUs = genderUs;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() throws UnknownHostException {
		return ip = InetAddress.getLocalHost().getHostAddress();
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isActiveUs() {
		return activeUs;
	}

	public void setActiveUs(boolean activeUs) {
		this.activeUs = activeUs;
	}

	/**
	 * @param idUs
	 * @param phoneUs
	 * @param passwordUs
	 * @param nameUs
	 * @param imgUs
	 * @param dateUs
	 * @param genderUs
	 * @param port
	 * @param ip
	 * @param activeUs
	 * @param checkOnline
	 */
	public User(int idUs, String phoneUs, String passwordUs, String nameUs, String imgUs, Date dateUs, boolean genderUs,
			int port, String ip, boolean activeUs, boolean checkOnline) {
		super();
		this.idUs = idUs;
		this.phoneUs = phoneUs;
		this.passwordUs = passwordUs;
		this.nameUs = nameUs;
		this.imgUs = imgUs;
		this.dateUs = dateUs;
		this.genderUs = genderUs;
		this.port = port;
		this.ip = ip;
		this.activeUs = activeUs;
		this.checkOnline = checkOnline;
	}

	/**
	 * 
	 */
	public User() {
	}

}
