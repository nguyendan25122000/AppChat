package controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import entitis.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TextFriendController {
	@FXML
	private TextField FriendInGroup;
	@FXML
	private Label XoaField;

	// xoa field
	private void removeTextField(User user, AnchorPane scroll1, VBox vboxGroupAdd, List<User> listFriendInGroup) {
		XoaField.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {

				LinkedHashSet<User> hashSet = new LinkedHashSet<>(VBoxFriendController.listFriendInGroup);

				List<User> list = new ArrayList<>(hashSet);

				for (int i = 0; i < list.size(); i++) {

					if (list.get(i).getIdUs() == user.getIdUs()) {
						list.remove(i);
						break;
					}

					// khuc nay chua biet nha. Dm

//					listFriendInGroup.clear();
//					list = listFriendInGroup;
//					System.out.println(list);
//					System.out.println(listFriendInGroup);
				}

//				test(user, scroll1, vboxGroupAdd);

			}
		});
	}

	public void test(User user, AnchorPane scroll1, VBox vboxGroupAdd) {
		try {
			for (int j = 0; j < VBoxFriendController.listFriendInGroup.size(); j++) {
				VBoxFriendController.listFriendInGroup.get(j).getIdUs();
			}
			boolean flag = true;
			for (int i = 0; i < VBoxFriendController.listFriendInGroup.size(); i++) {
				if (VBoxFriendController.listFriendInGroup.get(i).getIdUs() == user.getIdUs()) {
					flag = false;
				}
			}
			VBoxFriendController.listFriendInGroup.add(user);
			VBoxFriendController.listFriendInGroup.add(user);

			int height = 29;
			for (int i = 0; i < VBoxFriendController.listFriendInGroup.size(); i++) {
				if (flag == true) {

					FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/TextFieldFriend.fxml"));
					BorderPane root1 = (BorderPane) loader1.load();
					TextFriendController controll = loader1.getController();
					controll.showInfoUser(user);
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

	private void showInfoUser(User user) {
		FriendInGroup.setText(user.getNameUs());
	}

	public void init(User user, AnchorPane scroll1, VBox vboxGroupAdd, List<User> listFriendInGroup) {
//		removeTextField(user, scroll1, vboxGroupAdd, listFriendInGroup);
		showInfoUser(user);
	}

}
