package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import dao.UserDao;
import entitis.ChatGroupMap;
import entitis.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TieuDeContentController {
	@FXML
	private Label nameUs, lblCheckOnline;
	@FXML
	private ImageView imgView;

	private void showInfoFriend(User user) {
		try {
			loadImg(user.getImgUs(), imgView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nameUs.setText(user.getNameUs());
		selectOnline(user);
	}

	public void selectOnline(User user) {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (UserDao.checkOn(user.getPhoneUs(), user.getPasswordUs()) == true) {
							lblCheckOnline.setText("Đang hoạt động");
						} else {
							lblCheckOnline.setText("Không hoạt động");
						}
					}
				});
			}
		}, 0, 1000);

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

	public void init(User user) {
		showInfoFriend(user);
	}

	// ---------------------------------------------
	private void showInfoGroup(ChatGroupMap chatGroupMap) {
		nameUs.setText(chatGroupMap.getNameG());
	}

	public void init2(ChatGroupMap chatGroupMap) {
		showInfoGroup(chatGroupMap);
	}
}
