package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import javafx.stage.Stage;
import dao.ChatBoxDao;
import dao.ContentDao;
import dao.RequestDao;
import entitis.Request;
import entitis.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FriendController {
	@FXML
	private ImageView imgDaiDien;
	@FXML
	private Label lblName;
	@FXML
	private Label lblGioiTinh;
	@FXML
	private Label lblNgaySinh;
	@FXML
	private Label lblBack;
	@FXML
	private Label btnKetBan;
	@FXML
	private Label btnHuyKetBan;
	@FXML
	private Label btnNhanTin;

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

	// action back
	private void actionBack(Stage primaryStage, Stage stage) {
		lblBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				primaryStage.close();
				stage.show();
			}
		});
	}

	// show info friend
	private void showInfoFriend(User user) {
		try {
			loadImg(user.getImgUs(), imgDaiDien);
		} catch (IOException e) {
			e.printStackTrace();
		}
		lblName.setText(user.getNameUs());
		if (user.isGenderUs() == true) {
			lblGioiTinh.setText("Nam");
		} else {
			lblGioiTinh.setText("Nữ");
		}
		lblNgaySinh.setText(user.getDateUs() + "");
	}

	public static int requestSend;

	// btn ket ban
	private void actionKetBan(User user) {
		btnKetBan.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int count;
				Request request = new Request();
				count = RequestDao.selectRequest(user.getIdUs(), request);
				if (count == 0) {
					Request request2 = new Request();
					ContentDao.createRowContent(request2);
					request2.setIdUs1(LoginController.user.getIdUs());
					request2.setIdUs2(user.getIdUs());
					RequestDao.sendRequestFriend(requestSend, request2);
					ChatBoxDao.ChatVsFriend(user, request2.getIdCt());
				}
				System.out.println("ok");
				btnKetBan.setVisible(false);
				btnHuyKetBan.setVisible(true);

			}
		});
	}

	// btn huy ket ban
	private void actionHuyKetBan(User user) {
		btnHuyKetBan.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				RequestDao.updateRequest(user);
				btnKetBan.setVisible(true);
				btnHuyKetBan.setVisible(false);
			}
		});
	}

	// btn nhan tin //doi sau
	private void actionNhantin(User user, Stage primaryStage) {
		btnNhanTin.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int count;
				Request request = new Request();
				count = RequestDao.selectRequest(user.getIdUs(), request);
				if (count == 1) {
					primaryStage.close();
				}
			}
		});
	}

	// show nut
	private void showButtonKetBanOrHuyKetBan(User user) {
		Request request = new Request();
		RequestDao.selectRequest(user.getIdUs(), request);
		if (request.getState() == 1) {
			btnHuyKetBan.setVisible(false);
			btnKetBan.setVisible(false);
			btnNhanTin.setVisible(true);
		} else if (request.getState() == 33) {
			btnKetBan.setVisible(false);
			btnHuyKetBan.setVisible(true);
		} else if (request.getState() == 2) {
			btnKetBan.setVisible(true);
			btnHuyKetBan.setVisible(false);
			btnNhanTin.setLayoutX(78);
		} else if (request.getState() == 33) {
			btnKetBan.setVisible(true);
			btnHuyKetBan.setVisible(false);
		}

	}

	public void init(User user, Stage primaryStage, Stage stage) {
		showInfoFriend(user);
		actionBack(primaryStage, stage);
		actionKetBan(user);
		actionHuyKetBan(user);
		actionNhantin(user,primaryStage);
		showButtonKetBanOrHuyKetBan(user);
	}
}
