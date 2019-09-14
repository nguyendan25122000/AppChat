package controller;

import dao.UserDao;
import entitis.User;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingController {

	@FXML
	private Label lblChangePassword, lblLogOut;

	public void initCaiDat(Stage formChat, Stage primaryStage, Stage formCaiDat) {
		lbl_LogOut(formChat, primaryStage, formCaiDat);
		lbl_ChangePassword(formCaiDat);
	}

	public void lbl_LogOut(Stage formChat, Stage primaryStage, Stage formCaiDat) {
		lblLogOut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				try {
					checkOffline();
					formChat.close();
					formCaiDat.close();
					primaryStage.show();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});
	}

	public void lbl_ChangePassword(Stage formCaiDat) {
		lblChangePassword.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				doiPass();
				formCaiDat.close();
			}
		});

	}

	public void doiPass() {
		try {
			Stage doiPass = new Stage();
			doiPass.initModality(Modality.APPLICATION_MODAL);
//			btn_Register.setOnCloseRequest(d -> {
//				d.consume(); 
//			});

			// load loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/DoiPass.fxml"));
			// load UI
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);

			// load controller
			controller.DoiPasswordController control = loader.getController();
			control.initDoiPass(doiPass);

			doiPass.setTitle("Change Password");
			doiPass.setScene(scene);
			doiPass.show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// CHECK OFFLINE
	public void checkOffline() {
		User userOff = new User();
		userOff.getPhoneUs();
		userOff.setCheckOnline(true);
		UserDao.updateCheckOff(userOff);
	}

}
