package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import dao.UserDao;
import entitis.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML
	private Label lblForgot, lblRegister, lblLogin;
	@FXML
	private TextField tfUserPhone;
	@FXML
	private PasswordField tfPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private CheckBox rememberBox;

	Preferences preferences;

	public static User user = new User();

	public void init(Stage primaryStage) {
		action_lblRegister(primaryStage);
		action_btnLogin(primaryStage);
		action_lblForgotPassword(primaryStage);
		all_enter(primaryStage);

	}

	public void all_enter(Stage primaryStage) {
		enter_txtLogin(primaryStage);
		enter_txtPassword(primaryStage);
	}

	public void enter_txtLogin(Stage primaryStage) {
		tfUserPhone.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					checkUser(primaryStage);
				default:
					break;
				}
			}
		});
	}

	public void enter_txtPassword(Stage primaryStage) {
		tfPassword.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					checkUser(primaryStage);
				default:
					break;
				}
			}
		});
	}

	public void action_lblForgotPassword(Stage primaryStage) {
		lblForgot.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				Register_lblForgotPassword(primaryStage);
			}
		});
	}

	public void action_lblRegister(Stage primaryStage) {
		lblRegister.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				Register_lblResgister(primaryStage);
			}
		});
	}

	public void action_btnLogin(Stage primaryStage) {
		btnLogin.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				checkUser(primaryStage);
			}
		});
	}

	// remember password
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		preferences = Preferences.userNodeForPackage(LoginController.class);
		if (preferences != null) {
			if (preferences.get("phoneUs", null) != null && !preferences.get("passwordUs", null).isEmpty()) {
				tfUserPhone.setText(preferences.get("phoneUs", null));
				tfPassword.setText(preferences.get("passwordUs", null));
				rememberBox.setSelected(true);

			}
		}
	}

	// check login
	public void checkUser(Stage primaryStage) {
		String phoneUsr = tfUserPhone.getText();
		System.out.println(phoneUsr);
		String passwordUsr = tfPassword.getText();
		System.out.println(passwordUsr);
		try {
			if (UserDao.checkUser(phoneUsr, passwordUsr) == true) {
				// cung la 1 phan cua remember password
				preferences.putBoolean("rememberBox", rememberBox.isSelected());
				if (rememberBox.isSelected()) {
					preferences.put("phoneUs", tfUserPhone.getText());
					preferences.put("passwordUs", tfPassword.getText());

				} else {
					preferences.put("phoneUs", "");
					preferences.put("passwordUs", "");
				}
				// ------------------------------------
				// ------------------------------------
				LoadIp();
				checkOnline();
				user = UserDao.getInfoUser(phoneUsr);
				formChat(user, primaryStage);
				primaryStage.close();
			} else {
				lblLogin.setText("The account or password is incorrect!");
				tfPassword.clear();
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void Register_lblResgister(Stage primaryStage) {
		try {
			Stage btn_Register = new Stage();
			btn_Register.initModality(Modality.APPLICATION_MODAL);
//			btn_Register.setOnCloseRequest(d -> {
//				d.consume();
//			});

			// load loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/Register.fxml"));
			// load UI
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);

			// load controller
			controller.RegisterController control = loader.getController();
			control.initRegister(btn_Register, primaryStage);

			btn_Register.setTitle("Create User");
			btn_Register.setScene(scene);
			btn_Register.show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void Register_lblForgotPassword(Stage primaryStage) {
		try {
			Stage lbl_ForgotPassword = new Stage();
			lbl_ForgotPassword.initModality(Modality.APPLICATION_MODAL);
//			btn_Register.setOnCloseRequest(d -> {
//				d.consume();
//			});

			// load loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/ForgotPassword.fxml"));
			// load UI
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);

			// load controller
			controller.ForgotPasswordController control = loader.getController();
			control.initForgotPassword(lbl_ForgotPassword, primaryStage);

			lbl_ForgotPassword.setTitle("Forgot Password");
			lbl_ForgotPassword.setScene(scene);
			lbl_ForgotPassword.show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void formChat(User infoUser, Stage primaryStage) {
		try {
			Stage formChat = new Stage();
			formChat.initModality(Modality.APPLICATION_MODAL);

//			btn_Register.setOnCloseRequest(d -> {
//				d.consume();
//			});

			// load loader

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/Client.fxml"));
			// load UI
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);

			// load controller
			controller.ClientController control = loader.getController();
			control.init(infoUser, formChat, primaryStage);

			formChat.setScene(scene);
			formChat.show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void LoadIp() {
		User user = new User();
		user.setPhoneUs(tfUserPhone.getText());
		user.setIp("");
		UserDao.updateIp(user);
	}

	// CHECK ONLINE
	public void checkOnline() {
		User user = new User();
		user.setPhoneUs(tfUserPhone.getText());
		user.setCheckOnline(true);
		UserDao.updateCheckOnl(user);

	}

}
