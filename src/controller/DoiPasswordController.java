package controller;

import dao.UserDao;
import entitis.User;
import helper.DataValidation;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DoiPasswordController {

	@FXML
	private TextField tfPhoneUser;
	@FXML
	private PasswordField tfOldPassword, tfNewPassword, tfEnterPassword;
	@FXML
	private Label lblCheck, lblChange;
	@FXML
	private Button btnCheck, btnChange;

	public void initDoiPass(Stage doiPass) {
		action_btnCheck();
		action_btnChange(doiPass);
		enterAll(doiPass);
	}

	public void action_btnChange(Stage doiPass) {
		btnChange.addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (submibCheck() == 0) {
					doiPass.close();
				}
			}
		});
	}

	public void action_btnCheck() {
		btnCheck.addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				check();
			}
		});
	}

	public void check() {
		try {
			if (UserDao.selectPassword(tfPhoneUser.getText(), tfOldPassword.getText()) == true) {
				tfNewPassword.setDisable(false);
				tfEnterPassword.setDisable(false);
				btnChange.setDisable(false);
				tfPhoneUser.setDisable(true);
				tfOldPassword.setDisable(true);
				btnCheck.setDisable(true);
				lblCheck.setText("");
			} else {
				lblCheck.setText("The account or password is incorrect!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void changePass() {
		User user = new User();
		user.setPhoneUs(tfPhoneUser.getText());
		user.setPasswordUs(tfEnterPassword.getText());
		UserDao.ChangePassword(user);
	}

	public int submibCheck() {
		int check = 1;
		boolean checkPassword = DataValidation.PasswordFiedlNull(tfNewPassword, lblChange, "");
		boolean checkPassword1 = DataValidation.PasswordFiedlNull(tfEnterPassword, lblChange,
				"Enter the password again");
		if (!tfNewPassword.getText().equals(tfEnterPassword.getText())) {
			lblChange.setText("Password doesn't match");
			tfEnterPassword.clear();
		} else if (checkPassword == false && checkPassword1 == false
				&& tfNewPassword.getText().equals(tfEnterPassword.getText())) {
			changePass();
			check = 0;
		}
		return check;
	}

	public void enterAll(Stage doiPass) {
		enter_tfPhoneUser();
		enter_tfOldPassword();
		enter_tfNewPassword(doiPass);
		enter_tfEnterPassword(doiPass);
	}

	public void enter_tfPhoneUser() {
		tfPhoneUser.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					check();
				default:
					break;
				}
			}
		});
	}

	public void enter_tfOldPassword() {
		tfOldPassword.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					check();
				default:
					break;
				}
			}
		});
	}

	public void enter_tfNewPassword(Stage doiPass) {
		tfNewPassword.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submibCheck() == 0) {
						doiPass.close();
					}
				default:
					break;
				}
			}
		});
	}

	public void enter_tfEnterPassword(Stage doiPass) {
		tfEnterPassword.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submibCheck() == 0) {
						doiPass.close();
					}
				default:
					break;
				}
			}
		});
	}
}
