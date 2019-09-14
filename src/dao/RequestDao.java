package dao;

import java.sql.ResultSet;
import java.util.List;

import controller.LoginController;
import entitis.Request;
import entitis.User;
import lib.Sop.vn.ConnectionSQL;

public class RequestDao {
	// add friend

	public static void sendRequestFriend(int requestSend, Request request) {
		try {

			requestSend = request.getIdUs2();

			// insert vao host
			String query1 = "insert into Request(idUs1,idUs2,state,idCt) values(?,?,?,?)";
			String[] param1 = { request.getIdUs1() + "", request.getIdUs2() + "", 33 + "", request.getIdCt() + "" };
			ConnectionSQL.Create(query1, param1);
			// insert vao Friend
			String query2 = "insert into Request(idUs1,idUs2,state,idCt) values(?,?,?,?)";
			String[] param2 = { request.getIdUs2() + "", request.getIdUs1() + "", 3 + "", request.getIdCt() + "" };
			ConnectionSQL.Create(query2, param2);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

//status : 1.(Chap nhan, duoc nhan tin) 2.(Chap nhan, khong duoc nhan tin) 3.(khong chap nhan,duoc nhan tin) 4.(khong chap nhan,khong duoc nhan tin)
	// 5.(tu choi luon nhung van duoc nhan tin)
	// check state
	public static Integer selectRequest(int idUs2, Request request) {
		int count = 0;
		try {
			String query = "select * from Request where idUs1 = (?) and idUs2 = (?)";
			String[] param = { LoginController.user.getIdUs() + "", idUs2 + "" };
			ResultSet rs = ConnectionSQL.Query(query, param);
			while (rs.next()) {
				request.setState(rs.getInt("state"));
				request.setIdCt(rs.getInt("idCt"));
				count = 1;
			}
			return count;
		} catch (Exception e) {
			e.getStackTrace();
			count = 0;
			return count;
		}
	}

	// update khi huy ket ban
	public static void updateRequest(User user) {
		try {
			String query = "delete from Request where (idUs1 = (?) and idUs2 = (?)) or (idUs1 = (?) and idUs2 = (?))";
			String[] param = { LoginController.user.getIdUs() + "", user.getIdUs() + "", user.getIdUs() + "",
					LoginController.user.getIdUs() + "" };
			ConnectionSQL.Query(query, param);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// select accend

	public static void selectRequestFriend(List<Request> list) {
		try {
			Request request = new Request();
			String query = "select * from Request where idUs1 = (?) and state = 0";
			String[] param = { LoginController.user.getIdUs() + "" };
			ResultSet rs = ConnectionSQL.Query(query, param);
			while (rs.next()) {
				list.clear();
				request.setIdUs2(rs.getInt("idUs2"));
				list.add(request);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// get User Friend
	public static void friendInfoAgree(int idUs, User user) {
		try {
			String query = "select * from [User] where idUs = (?) ";
			String[] param = { idUs + "" };
			ResultSet rs = ConnectionSQL.Query(query, param);
			while (rs.next()) {
				user.setIdUs(rs.getInt("idUs"));
				user.setPhoneUs(rs.getString("phoneUs"));
				user.setNameUs(rs.getString("nameUs"));
				user.setDateUs(rs.getDate("dateTime"));
				user.setGenderUs(rs.getBoolean("genderUs"));
				user.setImgUs(rs.getString("imgUs"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// get request
	// ------------------------------form ClientController
	public static void requestUser(List<Request> listRequest, int state) {
		try {
			String query = "select * from Request where idUs1 = (?) and state = (?)";
			String[] param = { LoginController.user.getIdUs() + "", state + "" };
			ResultSet rs = ConnectionSQL.Query(query, param);
			while (rs.next()) {
			
				Request request = new Request();
				request.setIdUs1(rs.getInt("idUs1"));
				request.setIdUs2(rs.getInt("idUs2"));
				request.setState(rs.getInt("state"));
				request.setIdCt(rs.getInt("idCt"));
				listRequest.add(request);
			
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// update chap nhan DanhSachController
	public static void updateKhiChapNhan(User user, int state) {
		try {
			String query = "update Request set state = (?) where (idUs1 = (?) and idUs2 = (?)) or (idUs1 = (?) and idUs2 = (?)) ";
			String[] param = { state + "", LoginController.user.getIdUs() + "", user.getIdUs() + "",
					user.getIdUs() + "", LoginController.user.getIdUs() + "" };
			ConnectionSQL.Create(query, param);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// checkRequest co phai friend khong
	public static void checkRequestFriend(String search, Request request, List<User> listFindFriend, int x) {
		try {
			if (x == 1) {
				String query = "select * from Request where idUs1 = (?) and idUs2 = (select idUs from [User] where phoneUs = (?) or nameUs = (?)) and state = 1";
				String[] param = { LoginController.user.getIdUs() + "", search, search };
				ResultSet rs = ConnectionSQL.Query(query, param);
				while (rs.next()) {
					User user = new User();
					user.setIdUs(rs.getInt("idUs2"));
					listFindFriend.add(user);
				}
			} else if (x == 2) {
				String query = "select * from Request where idUs1 = (?) and state = 1";
				String[] param = { LoginController.user.getIdUs() + "" };
				ResultSet rs = ConnectionSQL.Query(query, param);
				while (rs.next()) {
					User user = new User();
					user.setIdUs(rs.getInt("idUs2"));
					listFindFriend.add(user);
				}
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
