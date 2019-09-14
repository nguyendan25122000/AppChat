package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.imageio.ImageIO;

import dao.UserDao;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import entitis.User;

public class UpdateInfoController {
	@FXML
	private TextField tfName, tfPhone;
	@FXML
	private Label lblUpdate;
	@FXML
	private DatePicker tfDate;
	@FXML
	private ImageView imgView, imgUpload;
	@FXML
	private ToggleGroup Group;
	@FXML
	private RadioButton rdnMale, rdnFemale;
	private static File file;

	public void initUpdateInfo(User infoUser, Stage formUpdateInfo) {
		showInfoFriend(infoUser);
		action_imgUpload(formUpdateInfo);
		lblUpdate.setDisable(true);
		tfDate.setDisable(true);
		action_lblUpdateInfo(infoUser, formUpdateInfo);
		all_clicked();
	}

	// chuyen doi date thanh local date
	public static final LocalDate LOCAL_DATE(String dateString) {
		LocalDate localDate = LocalDate.parse(dateString);
		return localDate;
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}

	public void action_imgUpload(Stage formUpdateInfo) {
		imgUpload.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				try {
					insertImg(formUpdateInfo);
					lblUpdate.setDisable(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	// mo dialog file de chon img
	public boolean insertImg(Stage btn_Register) throws IOException {
		FileChooser fs = new FileChooser();
		fs.setInitialDirectory(new File("D:\\hinhdalat"));
		file = fs.showOpenDialog(btn_Register);
		if (file != null) {
			try {
				System.out.println(file);
				imgView.setImage(new Image(file.toURI().toURL().toString()));
				BufferedImage buffer = ImageIO.read(file);
				File fileSave = new File(file.getName());
				System.out.println(file.getName());
				return ImageIO.write(buffer, "png", fileSave);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;

	}

	// show info User
	private void showInfoFriend(User infoUser) {
		try {
			if (infoUser.getImgUs() != null) {
				loadImg(infoUser.getImgUs(), imgView);
			}
			tfName.setText(infoUser.getNameUs());
			tfPhone.setText(infoUser.getPhoneUs());
			if (infoUser.isGenderUs() == true) {
				rdnMale.setSelected(true);
			} else {
				rdnFemale.setSelected(true);
			}
			tfDate.setValue(LOCAL_DATE(infoUser.getDateUs() + ""));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// function load img
	public boolean loadImg(String linkImg, ImageView loadImg) throws IOException {
		File file = new File(linkImg);
		if (file != null) {
			try {
				System.out.println(file);
				loadImg.setImage(new Image(file.toURI().toURL().toString()));
				BufferedImage buffer = ImageIO.read(file);
				File fileSave = new File(file.getName());
				System.out.println(file.getName());
				return ImageIO.write(buffer, "png", fileSave);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	public void action_lblUpdateInfo(User infoUser, Stage formUpdateInfo) {
		lblUpdate.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				updateInfo(infoUser);
				formUpdateInfo.close();
			}
		});
	}

	public void updateInfo(User infoUser) {
		infoUser.setNameUs(tfName.getText());
//			infoUser.setDateUs(asDate(tfDate.getValue()));
		infoUser.setGenderUs(rdnMale.isSelected());
		if (file == null) {
			infoUser.setImgUs(infoUser.getImgUs());
		} else {
			infoUser.setImgUs(file + " ");
		}
		UserDao.updateIp(infoUser);
	}

	public void all_clicked() {
		name_clicked();
		date_clicked();
		genderFemale_clicked();
		genderMale_clicked();
	}

	public void name_clicked() {
		tfName.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				lblUpdate.setDisable(false);
			}
		});
	}

	public void date_clicked() {
		tfDate.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				lblUpdate.setDisable(false);
			}
		});
	}

	public void genderMale_clicked() {
		rdnMale.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				lblUpdate.setDisable(false);
			}
		});
	}

	public void genderFemale_clicked() {
		rdnFemale.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				lblUpdate.setDisable(false);
			}
		});
	}
}
