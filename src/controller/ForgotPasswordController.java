package controller;

import dao.UserDao;
import entitis.User;
import helper.DataValidation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ForgotPasswordController {
	@FXML
	private Label lblPhoneCheck, lblPasswordCheck, lblPasswordCheck1;
	@FXML
	private Button btnCheck, btnLoginCheck;
	@FXML
	private TextField tfCheckPhone;
	@FXML
	private PasswordField tfCheckPassword, tfCheckPassword1;

	RegisterController rs = new RegisterController();

	public void initForgotPassword(Stage lbl_ForgotPassword, Stage primaryStage) {
		action_btnCheck();
		action_btnLogin(lbl_ForgotPassword, primaryStage);
		all_enter(lbl_ForgotPassword, primaryStage);
	}

	public void action_btnCheck() {
		btnCheck.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnCheck();
			}
		});
	}

	public void action_btnLogin(Stage lbl_ForgotPassword, Stage primaryStage) {
		btnLoginCheck.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (submibCheck(primaryStage, primaryStage) == 0) {
					lbl_ForgotPassword.close();
				}
			}
		});
	}

	public void forgotPassword() {
		User User = new User();
		User.setPhoneUs(tfCheckPhone.getText());
		User.setPasswordUs(tfCheckPassword.getText());
		UserDao.updatePassword(User);
	}

	public int submibCheck(Stage lbl_ForgotPassword, Stage primaryStage) {
		int check = 1;
		boolean checkPassword = DataValidation.PasswordFiedlNull(tfCheckPassword, lblPasswordCheck,
				"Enter the password");
		boolean checkPassword1 = DataValidation.PasswordFiedlNull(tfCheckPassword1, lblPasswordCheck1,
				"Enter the password again");
		if (!tfCheckPassword.getText().equals(tfCheckPassword1.getText())) {
			lblPasswordCheck1.setText("Password doesn't match");
			tfCheckPassword1.clear();
		} else if (checkPassword == false && checkPassword1 == false
				&& tfCheckPassword.getText().equals(tfCheckPassword1.getText())) {
			forgotPassword();
			primaryStage.close();
			rs.stageLogin();
			check = 0;
		}
		return check;
	}

	public void btnCheck() {
		String phoneCheck = tfCheckPhone.getText();
		try {
			if (UserDao.selectPhoneCheck(phoneCheck) == true) {
				tfCheckPassword.setDisable(false);
				tfCheckPassword1.setDisable(false);
				btnLoginCheck.setDisable(false);
				btnCheck.setDisable(true);
				tfCheckPhone.setDisable(true);
				lblPhoneCheck.setText("");

			} else {
				lblPhoneCheck.setText("Phone number not available!");

				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void all_enter(Stage lbl_ForgotPassword, Stage primaryStage) {
		enter_tfCheckPhone();
		enter_tfCheckPassword(lbl_ForgotPassword, primaryStage);
		enter_tfCheckPassword1(lbl_ForgotPassword, primaryStage);

	}

	public void enter_tfCheckPassword(Stage lbl_ForgotPassword, Stage primaryStage) {
		tfCheckPassword.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submibCheck(primaryStage, primaryStage) == 0) {
						lbl_ForgotPassword.close();
					}

				default:
					break;
				}
			}
		});
	}

	public void enter_tfCheckPassword1(Stage lbl_ForgotPassword, Stage primaryStage) {
		tfCheckPassword1.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submibCheck(primaryStage, primaryStage) == 0) {
						lbl_ForgotPassword.close();
					}

				default:
					break;
				}
			}
		});
	}

	public void enter_tfCheckPhone() {
		tfCheckPhone.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					btnCheck();

				default:
					break;
				}
			}
		});
	}

}
