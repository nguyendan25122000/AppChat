package dao;

import entitis.ChatGroup;
import lib.Sop.vn.ConnectionSQL;

public class ChatGroupDao {
	public static void insertChatGroup(ChatGroup chatGroup) {
		try {

			String query = "insert into ChatGroup(idG,idUs,idCt) values (?,?,?)";
			String[] param = { chatGroup.getIdG() + "", chatGroup.getIdUs() + "", chatGroup.getIdCt() + "" };
			ConnectionSQL.Create(query, param);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
