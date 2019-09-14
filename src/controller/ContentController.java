package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import commun.StringJson;
import dao.ChatBoxDao;
import dao.ContentDao;
import entitis.ChatBox;
import entitis.ChatGroupMap;
import entitis.Content;
import entitis.ContentChat;
import entitis.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ContentController {
	@FXML
	private TextField txtNhanTin;
	@FXML
	private TextArea txtAreaChat;
	@FXML
	private ImageView imgSend;

	// bat su kien content
	private void xuLyContent(int idCt) {
		int i = 0;
		ContentChat contentChat = new ContentChat();
		i = i + 1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		ContentDao.getDate(date);
		contentChat.setId(i);
		contentChat.setNameUs(LoginController.user.getNameUs());
		contentChat.setContent(txtNhanTin.getText());
		contentChat.setDateTime(dateFormat.format(date));
		org.json.JSONObject contentJson = StringJson.Content(contentChat);
		int count;
		Content content = new Content();
		count = ContentDao.checkContent(idCt, content);
		ContentDao.updateContent(content.getIdCt(), contentJson + "", content.getContent(), count);
	}

	// --------------------------------------------------------------------

	// action send
	private void actionSend(int idCt) {
		imgSend.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				xuLyContent(idCt);
				txtNhanTin.setText("");
			}

		});
	}

	// ---------------------------------------------------------------------
	private void selectChatBoxAddContent(User user, Content content) {
		ChatBox chatBox = new ChatBox();
		ChatBoxDao.selectContentChat(user, chatBox);
		ContentDao.selectContent(chatBox.getIdCt(), content);
	}

	int count;
//	static String contentJson;

	// --------------------------------------------------------------------------
	// select content java
	private void selectContentJava(User user) {
		try {

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Content content = new Content();

					selectChatBoxAddContent(user, content);
					JSONArray arrayJson = new JSONArray(content.getContent());
//					contentJson = 

					if (arrayJson.length() > count) {
						txtAreaChat.setText("");
						selectTinNhan(user);
					}

				}
			}, 0, 500);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

//----------------------------------------------------------------------
	// select textArea
	private void selectTinNhan(User user) {

		Content content = new Content();

		selectChatBoxAddContent(user, content);

		JSONArray arrayJson = new JSONArray(content.getContent());

		for (int i = 0; i < arrayJson.length(); i++) {

			count = arrayJson.length();

			Object obj = JSONValue.parse(arrayJson.get(i).toString());
			JSONObject jsonObject = (JSONObject) obj;

			ContentChat contenChat = new ContentChat();

			contenChat.setId(Integer.valueOf(jsonObject.get("id") + ""));
			contenChat.setNameUs(jsonObject.get("nameUsr") + "");
			contenChat.setContent(jsonObject.get("content") + "");
			contenChat.setDateTime(jsonObject.get("dateTime") + "");

			txtAreaChat.appendText(contenChat.getDateTime() + "\n");
			String noiDung = contenChat.getNameUs() + " : " + contenChat.getContent() + "\n";
			txtAreaChat.appendText(noiDung);
		}
	}

	// ----------------------------------------------------------------------------------
	public void init(User user, int idCt) {
		actionSend(idCt);
		selectTinNhan(user);
		selectContentJava(user);
	}

	// ---------------------------------------------------------------

	int demSo;

	// select content java
	private void selectContentGroup(ChatGroupMap chatGroupMap) {

		try {

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Content content = new Content();
					int count = ContentDao.checkContent(chatGroupMap.getIdCt(), content);
					if (count == 1) {
						JSONArray arrayJson = new JSONArray("[" + content.getContent() + "]");

						if (arrayJson.length() > demSo) {
							txtAreaChat.setText("");
							selectTinNhanGroup(chatGroupMap);
						}
					}

				}
			}, 0, 500);

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// select textArea
	private void selectTinNhanGroup(ChatGroupMap chatGroupMap) {

		Content content = new Content();

		int count = ContentDao.checkContent(chatGroupMap.getIdCt(), content);
		if (count == 1) {

			JSONArray arrayJson = new JSONArray("[" + content.getContent() + "]");

			demSo = arrayJson.length();

			for (int i = 0; i < arrayJson.length(); i++) {

				Object obj = JSONValue.parse(arrayJson.get(i).toString());
				JSONObject jsonObject = (JSONObject) obj;

				ContentChat contenChat = new ContentChat();

				contenChat.setId(Integer.valueOf(jsonObject.get("id") + ""));
				contenChat.setNameUs(jsonObject.get("nameUsr") + "");
				contenChat.setContent(jsonObject.get("content") + "");
				contenChat.setDateTime(jsonObject.get("dateTime") + "");

				txtAreaChat.appendText(contenChat.getDateTime() + "\n");
				String noiDung = contenChat.getNameUs() + " : " + contenChat.getContent() + "\n";
				txtAreaChat.appendText(noiDung);

			}
		}
	}

	public void init2(ChatGroupMap chatGroupMap) {
		selectTinNhanGroup(chatGroupMap);
		selectContentGroup(chatGroupMap);
		actionSend(chatGroupMap.getIdCt());
	}

}
