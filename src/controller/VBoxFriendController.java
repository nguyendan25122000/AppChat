package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entitis.ChatGroupMap;
import entitis.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class VBoxFriendController {
	@FXML
	private Label nameUs;
	@FXML
	public ImageView imgXoatinNhan;

	public void init(User user, GridPane gridPane3, BorderPane bP, int idCt) {
		showInfoFriend(user);
		clickUs(user, gridPane3, bP, idCt);
		actionRemoveContent();
	}

	public void clickUs(User user, GridPane gridPane3, BorderPane bP, int idCt) {
		nameUs.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					gridPane3.getChildren().clear();
					bP.getChildren().clear();

					FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/TieuDeContent.fxml"));
					GridPane root1 = (GridPane) loader1.load();
					TieuDeContentController controll1 = loader1.getController();
					controll1.init(user);
					gridPane3.getChildren().add(root1);

					FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../res/Content.fxml"));
					BorderPane root2 = (BorderPane) loader2.load();
					ContentController controll2 = loader2.getController();
					controll2.init(user, idCt);
					bP.setCenter(root2);
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		});
	}

	public void showInfoFriend(User user) {
		nameUs.setText(user.getNameUs());
	}

	// xoa tin nhan
	private void OpenAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Bạn có muốn xóa cuộc hội thoại không ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

		} else {
			alert.close();
		}
	}

	private void actionRemoveContent() {
		imgXoatinNhan.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				OpenAlert();
			}
		});
	}

	// --------------------------------------------------------------------------
	static List<User> listFriendInGroup = new ArrayList<>();

	public void clickUs2(User user, AnchorPane scroll1, VBox vboxGroupAdd) {
		nameUs.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {

					for (int j = 0; j < listFriendInGroup.size(); j++) {

						listFriendInGroup.get(j).getIdUs();

					}

					boolean flag = true;
					for (int i = 0; i < listFriendInGroup.size(); i++) {
						if (listFriendInGroup.get(i).getIdUs() == user.getIdUs()) {
							flag = false;
						}
					}
					listFriendInGroup.add(user);
					listFriendInGroup.add(user);

					int height = 29;
					for (int i = 0; i < listFriendInGroup.size(); i++) {

						if (flag == true) {

							FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/TextFieldFriend.fxml"));
							BorderPane root1 = (BorderPane) loader1.load();
							TextFriendController controll = loader1.getController();

							controll.init(user, scroll1, vboxGroupAdd, listFriendInGroup);

							height = height + 29;
							scroll1.setMinHeight(height);
							vboxGroupAdd.getChildren().add(root1);
							flag = false;

						}
					}

				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		});
	}

	public void init2(User user, AnchorPane scroll1, VBox vboxGroupAdd) {
		imgXoatinNhan.setVisible(false);
		showInfoFriend(user);
		clickUs2(user, scroll1, vboxGroupAdd);
	}

	// ---------------------------------------------------------------
	private void showInfoGroup(ChatGroupMap chatGroupMap) {
		nameUs.setText(chatGroupMap.getNameG());
	}

	// setclick textfiield()
	private void clickTextField(ChatGroupMap chatGroupMap,GridPane gridPane3, BorderPane bP) {
		nameUs.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {

					gridPane3.getChildren().clear();
					bP.getChildren().clear();

					FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/TieuDeContent.fxml"));
					GridPane root1 = (GridPane) loader1.load();
					TieuDeContentController controll1 = loader1.getController();
					controll1.init2(chatGroupMap);
					gridPane3.getChildren().add(root1);

					FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../res/Content.fxml"));
					BorderPane root2 = (BorderPane) loader2.load();
					ContentController controll2 = loader2.getController();
					controll2.init2(chatGroupMap);
					bP.setCenter(root2);
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		});
	}

	public void init3(ChatGroupMap chatGroupMap, GridPane gridPane3, BorderPane bP) {
		showInfoGroup(chatGroupMap);
		clickTextField(chatGroupMap,gridPane3, bP);
	}

}
