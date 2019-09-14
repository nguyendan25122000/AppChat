package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import dao.ChatGroupDao;
import dao.ContentDao;
import dao.GroupDao;
import dao.RequestDao;
import dao.UserDao;
import entitis.ChatGroup;
import entitis.ChatGroupMap;
import entitis.Request;
import entitis.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateGroupController {
	@FXML
	private Button btnTim;
	@FXML
	private TextField txtSearch;
	@FXML
	private VBox vboxGroup;
	@FXML
	private VBox vboxGroupAdd;
	@FXML
	private AnchorPane scroll1;
	@FXML
	private AnchorPane scroll2;
	@FXML
	private Label btnCreateGroup;

	List<User> listFindFriend = new ArrayList<>();

	// actionButton
	private void actionTim() {
		btnTim.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {

					vboxGroup.getChildren().clear();
					listFindFriend.clear();
					int x = 1;
					Request request = new Request();
					RequestDao.checkRequestFriend(txtSearch.getText(), request, listFindFriend, x);
					int height = 51;
					for (int i = 0; i < listFindFriend.size(); i++) {
						User user = new User();
						UserDao.showUs(user, listFindFriend.get(i).getIdUs());
						FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/VboxFriend.fxml"));
						AnchorPane root1 = (AnchorPane) loader1.load();
						VBoxFriendController controll = loader1.getController();
						controll.init2(user, scroll1, vboxGroupAdd);
						height = height + 51;
						scroll2.setPrefSize(468, height);
						vboxGroup.getChildren().add(root1);

					}
					System.out.println("cc");
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		});
	}

	// select nhung ai da la ban ra luon
	private void selectFriend() {
		try {

			vboxGroup.getChildren().clear();
			listFindFriend.clear();
			int x = 2;
			Request request = new Request();
			RequestDao.checkRequestFriend(txtSearch.getText(), request, listFindFriend, x);

			int height = 51;
			for (int i = 0; i < listFindFriend.size(); i++) {

				User user = new User();
				UserDao.showUs(user, listFindFriend.get(i).getIdUs());
				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../res/VboxFriend.fxml"));
				AnchorPane root1 = (AnchorPane) loader1.load();
				VBoxFriendController controll = loader1.getController();
				controll.init2(user, scroll1, vboxGroupAdd);
				height = height + 51;
				scroll2.setPrefSize(468, height);
				vboxGroup.getChildren().add(root1);
				System.out.println("hello cc");

			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// action create nhom chat
	private void actionCreateGroup(Stage stage) {
		btnCreateGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				createGroup(stage);
			}
		});
	}

	static StringBuilder name = new StringBuilder();

	// create group
	private void createGroup(Stage stage) {

		LinkedHashSet<User> hashSet = new LinkedHashSet<>(VBoxFriendController.listFriendInGroup);

		List<User> list = new ArrayList<>(hashSet);

		String nameList;

		name = name.append(LoginController.user.getNameUs());
		for (int i = 0; i < list.size(); i++) {
			nameList = list.get(i).getNameUs();
			if (i == 0) {
				name = name.append("," + nameList);
			} else {
				name = name.append("," + nameList);
			}
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Date date2 = new Date();
		try {
			date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateFormat.format(date));
		} catch (Exception e) {
			e.getStackTrace();
		}
		Integer idG = GroupDao.createGroup(name + "", date2);

		// cho nay hoi bi bien tau
		Request request = new Request();
		ContentDao.createRowContent(request);
		// -----------------------
		ChatGroup chatGroup = new ChatGroup();
		int idUs = 0;
		for (int i = 0; i < list.size(); i++) {
			idUs = list.get(i).getIdUs();
			chatGroup.setIdUs(idUs);
			chatGroup.setIdG(idG);
			chatGroup.setIdCt(request.getIdCt());
			ChatGroupDao.insertChatGroup(chatGroup);
		}
		idUs = LoginController.user.getIdUs();
		// -----------------------
		chatGroup.setIdUs(idUs);
		chatGroup.setIdG(idG);
		chatGroup.setIdCt(request.getIdCt());
		ChatGroupDao.insertChatGroup(chatGroup);
		stage.close();

	}

	public void init(Stage stage) {

		actionTim();
		selectFriend();
		actionCreateGroup(stage);

	}
}
