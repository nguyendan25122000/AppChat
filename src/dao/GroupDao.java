package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import controller.LoginController;
import entitis.ChatGroupMap;
import entitis.Group;
import lib.Sop.vn.ConnectionSQL;

public class GroupDao {
	// create group
	public static Integer createGroup(String nameG, Date dateCreate) {
		Integer idG = 0;
		try {
			String query = "jdbc:sqlserver://ChatApp.mssql.somee.com;databaseName=ChatApp;user=Huudan2512_SQLLogin_1;password=ciwvrn7w67";
			var conn = DriverManager.getConnection(query);
			var cs = conn.prepareCall("{call [createGroup](?,?)}");
			try (conn; cs;) {
				cs.setString(1, nameG);
				java.sql.Date sDate = new java.sql.Date(dateCreate.getTime());
				cs.setDate(2, sDate);
				boolean isResultSet = cs.execute();
				boolean hasmoveResult = true;
				while (hasmoveResult) {
					if (isResultSet) {
						var rs = cs.getResultSet();
						while (rs.next()) {
							idG = rs.getInt("idG");
						}
					} else {
						var rowCount = cs.getUpdateCount();
						if (rowCount == -1) {
							hasmoveResult = false;
						} else {
							System.out.println(String.format("ok Query", rowCount));
						}
					}
					isResultSet = cs.getMoreResults();
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idG;
	}

	// select group
	public static void selectGroup(List<ChatGroupMap> listGroup) {
		try {
			String query = "select * from [Group] [G] inner join ChatGroup CG on [G].idG = CG.idG where CG.idUs = (?) ";
			String[] param = { LoginController.user.getIdUs() + "" };
			ResultSet rs = ConnectionSQL.Query(query, param);
			while (rs.next()) {
				ChatGroupMap chatGroupMap = new ChatGroupMap();
				chatGroupMap.setIdCG(rs.getInt("idCG"));
				chatGroupMap.setIdG(rs.getInt("idG"));
				chatGroupMap.setIdUs(rs.getInt("idUs"));
				chatGroupMap.setIdCt(rs.getInt("idCt"));
				chatGroupMap.setNameG(rs.getString("nameG"));
				chatGroupMap.setDateCreate(rs.getDate("dateCreate"));
				listGroup.add(chatGroupMap);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
