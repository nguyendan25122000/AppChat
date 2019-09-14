package controller;

import javax.swing.JOptionPane;

import dao.UserDao;
import entitis.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FindFriendController {
	@FXML
	private TextField txtSoDienThoai;
	@FXML
	private Label lblTim;

	// click find
	private void findFriend(Stage primaryStage) {
		lblTim.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				User user = new User();
				UserDao.showInfoAccount(txtSoDienThoai.getText(), user);
				if (user.getPhoneUs() == null) {
					JOptionPane.showMessageDialog(null, "SDT khong phu hop !");
				} else if (user.getPhoneUs().equals(LoginController.user.getPhoneUs())) {
					form_InfoUser(LoginController.user);
					primaryStage.close();
				} else {
					showFriendForm(user, primaryStage);
					primaryStage.close();
				}
			}
		});
	}

	// form Friend
	private void showFriendForm(User user, Stage stage) {
		try {
			Stage primaryStage = new Stage();
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/Friend.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			controller.FriendController control = loader.getController();
			control.init(user, primaryStage, stage);
			primaryStage.setTitle("Add Friend");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// form loginUser
	public void form_InfoUser(User infoUser) {
		try {
			Stage formInfo = new Stage();
			formInfo.initModality(Modality.APPLICATION_MODAL);
//			btn_Register.setOnCloseRequest(d -> {
//				d.consume();
//			});

			// load loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/InfoUser.fxml"));
			// load UI
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);

			// load controller
			controller.InfoUserController control = loader.getController();
			control.initInfoUser(infoUser, formInfo);

			formInfo.setTitle("Info User");
			formInfo.setScene(scene);
			formInfo.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(Stage primaryStage) {
		findFriend(primaryStage);
	}
}
