package controller;

import dao.ChatBoxDao;
import dao.RequestDao;
import entitis.ChatBox;
import entitis.Request;
import entitis.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DanhSachController {
	@FXML
	private Label btnChapNhan;
	@FXML
	private Label btnTuChoi;
	@FXML
	private Label nameUs;

	private void actionChapNhan(User user) {
		btnChapNhan.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int state = 1;
				RequestDao.updateKhiChapNhan(user, state);
			}
		});
	}

	private void actionTuChoi(User user) {
		btnTuChoi.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				RequestDao.updateRequest(user);
			}
		});
	}

	private void getNameUs(User user) {
		nameUs.setText(user.getNameUs());
	}

	public void init(User user) {
		getNameUs(user);
		actionChapNhan(user);
		actionTuChoi(user);
	}

}
