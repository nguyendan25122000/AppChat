package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import entitis.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoUserController {
	@FXML
	public Label lblNameInfo, lblPhoneInfo, lblGenderInfo, lblDateInfo, lblEdit;
	@FXML
	public ImageView imgView;
	@FXML
	public ImageView imgEdit;

	public void initInfoUser(User infoUser, Stage formInfo) {
		showInfoFriend(infoUser);
		lblEdit(infoUser, formInfo);
		imgEdit(infoUser, formInfo);
	}

	public void imgEdit(User infoUser, Stage formInfo) {
		imgEdit.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				form_UpdateInfo(infoUser);
				formInfo.close();
			}
		});
	}

	public void lblEdit(User infoUser, Stage formInfo) {
		lblEdit.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				form_UpdateInfo(infoUser);
				formInfo.close();
			}
		});
	}

	// chuyen doi date thanh local date
	public final LocalDate LOCAL_DATE(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}

	static Format formatter;

	// show info friend
	public void showInfoFriend(User infoUser) {
		try {
			loadImg(infoUser.getImgUs(), imgView);
		} catch (IOException e) {
			e.printStackTrace();
		}
		lblNameInfo.setText(infoUser.getNameUs());
		lblPhoneInfo.setText(infoUser.getPhoneUs());
		if (infoUser.isGenderUs() == true) {
			lblGenderInfo.setText("Nam");
		} else {
			lblGenderInfo.setText("Nữ");
		}

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		// CHO NAY DANG LOI CHO A TOAN FIX
		String infDate = formatter.format(infoUser.getDateUs());
		lblDateInfo.setText(infDate);
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

	public void form_UpdateInfo(User infoUser) {
		try {
			Stage formUpdateInfo = new Stage();
			formUpdateInfo.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/UpdateInfo.fxml"));
			// load UI
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			// load controller
			controller.UpdateInfoController control = loader.getController();
			control.initUpdateInfo(infoUser, formUpdateInfo);

			formUpdateInfo.setTitle("Update Info");
			formUpdateInfo.setScene(scene);
			formUpdateInfo.show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
