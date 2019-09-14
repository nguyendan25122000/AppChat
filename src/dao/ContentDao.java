package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import entitis.Content;
import entitis.Request;
import lib.Sop.vn.ConnectionSQL;

public class ContentDao {
	// khi User gui di request add friend
	public static void createRowContent(Request request) {
		try {
			String query = "jdbc:sqlserver://ChatApp.mssql.somee.com;databaseName=ChatApp;user=Huudan2512_SQLLogin_1;password=ciwvrn7w67";
			var conn = DriverManager.getConnection(query);
			var cs = conn.prepareCall("{call createContent(?)}");
			try (conn; cs;) {
				cs.setString(1, "");
				boolean isResultSet = cs.execute();
				boolean hasmoveResult = true;
				while (hasmoveResult) {
					if (isResultSet) {
						var rs = cs.getResultSet();
						while (rs.next()) {
							request.setIdCt(rs.getInt("idCt"));
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
	}

	// check coi da nt lan nao chua
	public static Integer checkContent(int idCt, Content content) {
		Integer count = null;
		try {
			String query = "select * from [Content] where idCt = (?)";
			String[] param = { idCt + "" };
			ResultSet rs = ConnectionSQL.Query(query, param);
			while (rs.next()) {
				content.setIdCt(rs.getInt("idCt"));
				content.setContent(rs.getString("content"));
				if (content.getContent().equals("")) {
					count = 0;
					return count;
				} else {
					count = 1;
					return count;
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return count;
	}

	// update content
	public static void updateContent(int idCt, String contentNew, String contentOld, int count) {
		try {
			String query = "update Content set content = (?) where idCt = (?) ";
			if (count == 1) {
				String[] param = { contentOld + "," + contentNew, idCt + "" };
				ConnectionSQL.Create(query, param);
			} else if (count == 0) {
				String[] param = { contentNew, idCt + "" };
				ConnectionSQL.Create(query, param);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// getDate
	public static void getDate(Date date) {
		try {
			String query = "Select getdate() as date";
			ResultSet rs = ConnectionSQL.Query(query);
			while (rs.next()) {
				date = rs.getDate("date");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// select contentChat()
	public static void selectContent(int idCt, Content content) {
		try {
			String query = "select * from [Content] where idCt = " + idCt;
			ResultSet rs = ConnectionSQL.Query(query);
			while (rs.next()) {
				content.setContent("[" + rs.getString("content") + "]");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
