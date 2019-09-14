package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import dao.GroupDao;
import dao.RequestDao;
import dao.UserDao;
import entitis.ChatGroupMap;
import entitis.Request;
import entitis.User;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientController {
	@FXML
	private GridPane gridPaneChu;
	@FXML
	private GridPane gridPane2;
	@FXML
	private GridPane gridPane3;
	@FXML
	private AnchorPane anchoScroll;
	@FXML
	private BorderPane borderPane;
	@FXML
	private BorderPane bP;
	@FXML
	private VBox vboxFriend;
	@FXML
	private VBox vboxDanhBa;
	@FXML
	private ImageView imgDanhBa;
	@FXML
	private ImageView imgCaiDat;
	@FXML
	private ImageView imgTinNhan;
	@FXML
	private ImageView imgAddFriend;
	@FXML
	private ImageView imgAddGroup;
	@FXML
	private TextField searchFriend;
	@FXML
	private Label lblOnline;

	List<Request> listRequest = new ArrayList<Request>();
	List<Request> listFriend = new ArrayList<Request>();
	List<ChatGroupMap> listGroup = new ArrayList<ChatGroupMap>();

	static Timer timer1 = new Timer();
	static Timer timer2 = new Timer();

	// ---------------------------------------------------------------An
	@FXML
	private ImageView imgViewUser;

	public void init(User infoUser, Stage formChat, Stage primaryStage) {
		gridPane3.getChildren().clear();
		bP.getChildren().clear();
		vboxDanhBa.getChildren().clear();
		ImgUser(infoUser);
		action_imgViewUser(infoUser, formChat);
		clickAddFriend();
		clickDanhBa();
		clickTinNhan();
		showDanhSachFriend();
		clickSearchFriend();
		actionSearchFriend();
		clickFindGroup();
		settings(formChat, primaryStage);
		selectOnline();
		nutClose(formChat, primaryStage);
		VBoxFriend();
	}

	public void ImgUser(User infoUser) {
		try {
			loadImg(infoUser.getImgUs(), imgViewUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void action_imgViewUser(User infoUser, Stage formChat) {
		imgViewUser.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				form_InfoUser(infoUser, formChat);
			}
		});
	}

	public void form_InfoUser(User infoUser, Stage formChat) {
		try {
			Stage formInfo = new Stage();
			formInfo.initModality(Modality.APPLICATION_MODAL);
//			btn_Register.setOnCloseRequest(d -> {
//				d.consume();
//			});

			// load loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/InfoUser.fxml"));
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

	// ---------------------------------------------------------Dan
	// giao dien findFriend
	private void findFriendFrom() {
		try {
			Stage primaryStage = new Stage();
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/FindFriend.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			controller.FindFriendController control = loader.getController();
			control.init(primaryStage);
			primaryStage.setTitle("Add Friend");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// click add friend
	private void clickAddFriend() {
		imgAddFriend.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				findFriendFrom();
			}
		});
	}

	// click tin nhan
	private void clickTinNhan() {
		imgTinNhan.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				clickContent();
				timer1.cancel();
				timer2.cancel();
				timer2.cancel();
			}
		});
	}

	// giao dien content
	private void clickContent() {
		try {
			gridPane3.getChildren().clear();
			bP.getChildren().clear();
//------------------
//-------------------
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// img danh ba
	private void clickDanhBa() {
		imgDanhBa.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				danhSachKetBan();
				VBoxFriend();
			}
		});
	}

	// giao dien Danh ba
	private void danhSachKetBan() {
		try {

			gridPane3.getChildren().clear();
			bP.getChildren().clear();
			vboxDanhBa.getChildren().clear();
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/TieuDeDanhBa.fxml"));
			AnchorPane root1 = (AnchorPane) loader1.load();
			gridPane3.getChildren().add(root1);

			timer1 = new Timer();
			timer1.schedule(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								bP.getChildren().clear();
								vboxDanhBa.getChildren().clear();
								listRequest.clear();
								int state = 3;
								RequestDao.requestUser(listRequest, state);
								System.out.println(listRequest.size());
								for (int i = 0; i < listRequest.size(); i++) {
									User user = new User();
									UserDao.showUs(user, listRequest.get(i).getIdUs2());
									FXMLLoader loader2 = new FXMLLoader(
											getClass().getResource("../res/DanhSachKetBan.fxml"));
									BorderPane root2 = (BorderPane) loader2.load();
									DanhSachController controll = loader2.getController();
									controll.init(user);
									bP.setCenter(vboxDanhBa);
									vboxDanhBa.getChildren().add(root2);
									int x = 0;
									x = x + 94;
									anchoScroll.setPrefSize(640, x);
								}
							} catch (Exception e) {
								e.getStackTrace();
							}
						}
					});
				}
			}, 0, 2000);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// -------------
	private void VBoxFriend() {
		try {
			int state = 1;

			timer2 = new Timer();
			timer2.schedule(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							vboxFriend.getChildren().clear();
							listFriend.clear();
							RequestDao.requestUser(listFriend, state);
							try {
								for (int i = 0; i < listFriend.size(); i++) {
									User user = new User();
									UserDao.showUs(user, listFriend.get(i).getIdUs2());
									FXMLLoader loader1 = new FXMLLoader(
											getClass().getResource("../res/VboxFriend.fxml"));
									AnchorPane root1 = (AnchorPane) loader1.load();
									VBoxFriendController controll = loader1.getController();
									controll.init(user, gridPane3, bP, listFriend.get(i).getIdCt());
									vboxFriend.getChildren().add(root1);
								}

								listGroup.clear();
								GroupDao.selectGroup(listGroup);

								for (int i = 0; i < listGroup.size(); i++) {
									ChatGroupMap chatGroupMap = listGroup.get(i);
									FXMLLoader loader1 = new FXMLLoader(
											getClass().getResource("../res/VboxFriend.fxml"));
									AnchorPane root1 = (AnchorPane) loader1.load();
									VBoxFriendController controll = loader1.getController();
									controll.init3(chatGroupMap, gridPane3, bP);
									vboxFriend.getChildren().add(root1);

								}

							} catch (Exception e) {
								e.getStackTrace();
							}
						}
					});
				}
			}, 0, 2000);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

//--------------------------------------------------------------------------
	// showDanhSachFriend
	private void showDanhSachFriend() {
		try {
			int state = 1;

			vboxFriend.getChildren().clear();
			listFriend.clear();
			RequestDao.requestUser(listFriend, state);

			for (int i = 0; i < listFriend.size(); i++) {
				User user = new User();
				UserDao.showUs(user, listFriend.get(i).getIdUs2());
				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/VboxFriend.fxml"));
				AnchorPane root1 = (AnchorPane) loader1.load();
				VBoxFriendController controll = loader1.getController();
				controll.init(user, gridPane3, bP, listFriend.get(i).getIdCt());
				vboxFriend.getChildren().add(root1);
			}

			listGroup.clear();
			GroupDao.selectGroup(listGroup);

			for (int i = 0; i < listGroup.size(); i++) {
				ChatGroupMap chatGroupMap = listGroup.get(i);
				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/VboxFriend.fxml"));
				AnchorPane root1 = (AnchorPane) loader1.load();
				VBoxFriendController controll = loader1.getController();
				controll.init3(chatGroupMap, gridPane3, bP);
				vboxFriend.getChildren().add(root1);

			}

			System.out.println("duma");

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// -------------

	// giao dien cai dat
	public void formCaiDat(Stage formChat, Stage primaryStage) {
		try {
			Stage formCaiDat = new Stage();
			formCaiDat.initModality(Modality.APPLICATION_MODAL);

			// load loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/CaiDat.fxml"));
			// load UI
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);

			// load controlle
			controller.SettingController control = loader.getController();
			control.initCaiDat(formChat, primaryStage, formCaiDat);

			formCaiDat.setTitle("Settings");
			formCaiDat.setScene(scene);
			formCaiDat.show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void settings(Stage formChat, Stage primaryStage) {
		imgCaiDat.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				formCaiDat(formChat, primaryStage);
			}
		});
	}

	public void selectOnline() {
		if (UserDao.checkOn(LoginController.user.getPhoneUs(), LoginController.user.getPasswordUs()) == true) {
			lblOnline.setText("Đang hoạt động");
		} else {
			lblOnline.setText("Không hoạt động");
		}
	}
	// ---------------------------------------------------------------------------------------

	// Gan cai nay vao cai stage can tat roi set false
	public void nutClose(Stage formChat, Stage primaryStage) {
		formChat.setOnHiding((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						formChat.close();
						checkOffline();
					}
				});
			}
		});
	}

	// CHECK OFFLINE
	public void checkOffline() {
		User userOff = new User();
		userOff.getPhoneUs();
		userOff.setCheckOnline(true);
		UserDao.updateCheckOff(userOff);
	}

	// ===================

	// click show friendGroup
	private void clickFindGroup() {
		imgAddGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {

					Stage primaryStage = new Stage();
					primaryStage.initModality(Modality.APPLICATION_MODAL);
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/CreateGroup.fxml"));
					BorderPane root = (BorderPane) loader.load();
					Scene scene = new Scene(root);
					controller.CreateGroupController control = loader.getController();
					control.init(primaryStage);
					primaryStage.setTitle("Add Friend Group");
					primaryStage.setScene(scene);
					primaryStage.show();

				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		});
	}

	// click searchFriend
	private void clickSearchFriend() {
		searchFriend.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				timer1.cancel();
				timer2.cancel();
			}
		});
	}

	// acction searchFriend
	private void actionSearchFriend() {
		searchFriend.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getCode()) {
				case ENTER:
					timer1.cancel();
					timer2.cancel();
				default:
					break;
				}
			}
		});
	}

}
