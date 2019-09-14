package dao;

import java.sql.ResultSet;

import controller.LoginController;
import entitis.ChatBox;
import entitis.User;
import lib.Sop.vn.ConnectionSQL;

public class ChatBoxDao {
	// chat khi hai nguoi ket ban
	public static void ChatVsFriend(User user, int idCt) {
		try {
			String query = "insert into ChatBox(idUs1,idUs2,idCt) values (?,?,?)";
			String[] param = { LoginController.user.getIdUs() + "", user.getIdUs() + "", idCt + "" };
			ConnectionSQL.Create(query, param);

			String query2 = "insert into ChatBox(idUs1,idUs2,idCt) values (?,?,?)";
			String[] param2 = { user.getIdUs() + "", LoginController.user.getIdUs() + "", idCt + "" };
			ConnectionSQL.Create(query2, param2);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// select ChatBox
	public static void selectContentChat(User user, ChatBox chatBox) {
		try {
			String query = "select * from ChatBox where idUs1 = (?) and idUs2 = (?)";
			String[] param = { LoginController.user.getIdUs() + "", user.getIdUs() + "" };
			ResultSet rs = ConnectionSQL.Query(query, param);
			while (rs.next()) {
				chatBox.setIdCt(rs.getInt("idCt"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}