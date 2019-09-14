package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import dao.UserDao;
import entitis.User;
import helper.DataValidation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RegisterController {
	@FXML
	private ToggleGroup MyGroup;
	@FXML
	private ImageView imgView;
	private static File file;
	@FXML
	private Label lblPhoneUser, lblPassword, lblPassword1, lblNameUser, lblGender, lblDate, lblForgot;
	@FXML
	private TextField tfPhoneUser, tfNameUser;
	@FXML
	private PasswordField tfPassword, tfPassword1;
	@FXML
	private DatePicker tfDate;
	@FXML
	private RadioButton radMale, radFemale;
	@FXML
	private Button btnCreate, btnUpload;

	public void initRegister(Stage btn_Register, Stage primaryStage) {
		action_btnCreate(btn_Register, primaryStage);
		action_btnUpload(btn_Register);
		all_enter(btn_Register, primaryStage);
		tfPhoneUser.setText("0");

	}

	public void action_btnCreate(Stage btn_Register, Stage primaryStage) {
		btnCreate.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (submitButtonAction() == 0) {
					String phoneUsr = tfPhoneUser.getText();
					if (UserDao.selectUsr(phoneUsr) == true) {
						JOptionPane.showMessageDialog(null, "Duplicate phone number");
					} else {
						Register_btnCreate();
						primaryStage.close();
						btn_Register.close();
						stageLogin();
					}
				}
			}
		});
	}

	public void stageLogin() {
		Stage LOGIN = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/Login.fxml"));
		BorderPane root = null;
		try {
			root = (BorderPane) loader.load();
		} catch (IOException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		Scene scene = new Scene(root);
		controller.LoginController control = loader.getController();
		control.init(LOGIN);
		LOGIN.setTitle("Login User");
		LOGIN.setScene(scene);
		LOGIN.show();
	}

	// chuyen doi date thanh local date
	public static final LocalDate LOCAL_DATE(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}

	public void Register_btnCreate() {
		User user = new User();
		user.setPhoneUs(tfPhoneUser.getText());
		user.setPasswordUs(tfPassword.getText());
		user.setNameUs(tfNameUser.getText());
		user.setDateUs(asDate(tfDate.getValue()));
		user.setGenderUs(radMale.isSelected());
		if (file == null) {
			user.setImgUs("C:\\Users\\nguye\\eclipse-workspace\\AppChat\\src\\img\\NotPicture.jpg");
		} else {
			user.setImgUs(file + " ");
		}

		user.setIp("");

		UserDao.insertUsr(user);

	}

	public int submitButtonAction() {
		int check = 1;
		boolean phoneUsr = DataValidation.dataLength(tfPhoneUser, lblPhoneUser, "Must be 9 add number", "10");
		System.out.println("phone:" + phoneUsr);

		boolean password = DataValidation.PasswordFiedlNull(tfPassword, lblPassword, "Password is Required");
		System.out.println("pass:" + password);

		boolean password1 = DataValidation.PasswordFiedlNull(tfPassword1, lblPassword1, "Enter the password");
		boolean nameUsr = DataValidation.textAlphabet(tfNameUser, lblNameUser, "Only enter letters from a - z");
		boolean dataPicker = DataValidation.datePicker(tfDate, lblDate, "Enter your birthday");
		boolean genderUsr = DataValidation.toggleGroup(MyGroup, lblGender, "Please select a gender");
		System.out.println("name:" + nameUsr);

		if (!tfPassword.getText().equals(tfPassword1.getText())) {
			lblPassword.setText("Password doesn't match");
			lblPassword1.setText("Password doesn't match");
		} else if (phoneUsr == true && nameUsr == true && tfPassword.getText().equals(tfPassword1.getText())) {
//			System.out.println("Good");
			check = 0;
		}
		return check;
	}

	public void action_btnUpload(Stage btn_Register) {
		btnUpload.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					insertImg(btn_Register);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	// load img tu database len
	public static boolean loadImg(String linkImg, ImageView loadImg) throws IOException {
		File file = new File(linkImg);
		if (file != null) {
			try {
				System.out.println(file);
				loadImg.setImage(new Image(file.toURI().toURL().toString()));
				BufferedImage buffer = ImageIO.read(file);
				File fileSave = new File(file.getName());
				System.out.println(file.getName());
				return ImageIO.write(buffer, "png", fileSave);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
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

	// tu dong up hinh khi null
	public static void SaveImage(String link, ImageView imgView) {
		File file = new File(link);
		if (file != null) {
			BufferedImage bImage;
			try {
				imgView.setImage(new Image(file.toURI().toURL().toString()));
				imgView.setSmooth(true);
				bImage = ImageIO.read(new File(file.getAbsolutePath()));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bImage, "png", baos);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// all enter
	public void all_enter(Stage btn_Register, Stage primaryStage) {
		enter_tfNameUser(btn_Register, primaryStage);
		enter_tfPhoneUser(btn_Register, primaryStage);
		enter_tfPassword(btn_Register, primaryStage);
		enter_tfPassword1(btn_Register, primaryStage);
		enter_radMale(btn_Register, primaryStage);
		enter_radFemale(btn_Register, primaryStage);

	}

	// enter event

	public void enter_tfPassword1(Stage btn_Register, Stage primaryStage) {
		tfPassword1.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submitButtonAction() == 0) {
						String phoneUsr = tfPhoneUser.getText();
						if (UserDao.selectUsr(phoneUsr) == true) {
							lblPhoneUser.setText("Duplicate phone number");
							tfPhoneUser.clear();
						} else {
							Register_btnCreate();
							primaryStage.close();
							btn_Register.close();
							stageLogin();
						}
					}
				default:
					break;
				}
			}
		});
	}

	public void enter_tfPassword(Stage btn_Register, Stage primaryStage) {
		tfPassword.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submitButtonAction() == 0) {
						String phoneUsr = tfPhoneUser.getText();
						if (UserDao.selectUsr(phoneUsr) == true) {
							lblPhoneUser.setText("Duplicate phone number");
							tfPhoneUser.clear();
						} else {
							Register_btnCreate();
							primaryStage.close();
							btn_Register.close();
							stageLogin();
						}
					}
				default:
					break;
				}
			}
		});
	}

	public void enter_radFemale(Stage btn_Register, Stage primaryStage) {
		radFemale.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submitButtonAction() == 0) {
						String phoneUsr = tfPhoneUser.getText();
						if (UserDao.selectUsr(phoneUsr) == true) {
							lblPhoneUser.setText("Duplicate phone number");
							tfPhoneUser.clear();
						} else {
							Register_btnCreate();
							primaryStage.close();
							btn_Register.close();
							stageLogin();
						}
					}
				default:
					break;
				}
			}
		});
	}

	public void enter_radMale(Stage btn_Register, Stage primaryStage) {
		radMale.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submitButtonAction() == 0) {
						String phoneUsr = tfPhoneUser.getText();
						if (UserDao.selectUsr(phoneUsr) == true) {
							lblPhoneUser.setText("Duplicate phone number");
							tfPhoneUser.clear();
						} else {
							Register_btnCreate();
							primaryStage.close();
							btn_Register.close();
							stageLogin();
						}
					}
				default:
					break;
				}
			}
		});
	}

	public void enter_tfDate(Stage btn_Register, Stage primaryStage) {
		tfDate.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submitButtonAction() == 0) {
						String phoneUsr = tfPhoneUser.getText();
						if (UserDao.selectUsr(phoneUsr) == true) {
							lblPhoneUser.setText("Duplicate phone number");
							tfPhoneUser.clear();
						} else {
							Register_btnCreate();
							primaryStage.close();
							btn_Register.close();
							stageLogin();
						}
					}
				default:
					break;
				}
			}
		});
	}

	public void enter_tfPhoneUser(Stage btn_Register, Stage primaryStage) {
		tfPhoneUser.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submitButtonAction() == 0) {
						String phoneUsr = tfPhoneUser.getText();
						if (UserDao.selectUsr(phoneUsr) == true) {
							lblPhoneUser.setText("Duplicate phone number");
							tfPhoneUser.clear();
						} else {
							Register_btnCreate();
							primaryStage.close();
							btn_Register.close();
							stageLogin();
						}
					}
				default:
					break;
				}
			}
		});
	}

	public void enter_tfNameUser(Stage btn_Register, Stage primaryStage) {
		tfNameUser.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					if (submitButtonAction() == 0) {
						String phoneUsr = tfPhoneUser.getText();
						if (UserDao.selectUsr(phoneUsr) == true) {
							lblPhoneUser.setText("Duplicate phone number");
							tfPhoneUser.clear();
						} else {
							Register_btnCreate();
							primaryStage.close();
							btn_Register.close();
							stageLogin();
						}
					}
				default:
					break;
				}
			}
		});
	}

}
