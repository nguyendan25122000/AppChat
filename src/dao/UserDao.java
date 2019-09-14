package dao;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import connection.ConnectionSQLAn;
import controller.LoginController;
import entitis.User;
import lib.Sop.vn.ConnectionSQL;

public class UserDao {
	// show Us
	public static void showUs(User user, int idUs) {
		try {
			String query = "select * from [User] where idUs = " + idUs;
			ResultSet rs = ConnectionSQL.Query(query);
			while (rs.next()) {
				user.setIdUs(rs.getInt("idUs"));
				user.setPhoneUs(rs.getString("phoneUs"));
				user.setPasswordUs(rs.getString("passwordUs"));
				user.setNameUs(rs.getString("nameUs"));
				user.setImgUs(rs.getString("imgUs"));
				user.setDateUs(rs.getDate("dateUs"));
				user.setGenderUs(rs.getBoolean("genderUs"));
				user.setPort(rs.getInt("port"));
				user.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// call proc LoginUser
	public static Integer loginUser(User user, String phoneUs, String passwordUs, int count) {
		String query = "[loginUser]";
		String[] param = { phoneUs, passwordUs };
		try {
			ResultSet rs = ConnectionSQL.CallProc(query, param);
			while (rs.next()) {
				user.setIdUs(rs.getInt("idUs"));
				user.setPhoneUs(rs.getString("phoneUs"));
				user.setPasswordUs(rs.getString("passwordUs"));
				user.setNameUs(rs.getString("nameUs"));
				user.setImgUs(rs.getString("imgUs"));
				user.setDateUs(rs.getDate("dateUs"));
				user.setGenderUs(rs.getBoolean("genderUs"));
				user.setPort(rs.getInt("port"));
				user.setIp(rs.getString("ip"));
				count = 1;
			}
			return count;
		} catch (Exception e) {
			e.getStackTrace();
			count = 0;
			return count;
		}
	}

	// show info accont and friend
	public static void showInfoAccount(String phoneUs, User user) {
		String query = "select * from [User] where phoneUs = '" + phoneUs + "' and activeUs = 'true'";
		ResultSet rs = ConnectionSQL.Query(query);
		try {
			while (rs.next()) {
				user.setIdUs(rs.getInt("idUs"));
				user.setPhoneUs(rs.getString("phoneUs"));
				user.setPasswordUs(rs.getString("passwordUs"));
				user.setNameUs(rs.getString("nameUs"));
				user.setImgUs(rs.getString("imgUs"));
				user.setDateUs(rs.getDate("dateUs"));
				user.setGenderUs(rs.getBoolean("genderUs"));
				user.setPort(rs.getInt("port"));
				user.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// =========================================================================
//---------------------------------------------------------------An
	// -----------------------------Login
	// check Login
	public final static boolean checkUser(String phoneUs, String passwordUs) {

		try {
			if (ConnectionSQLAn.CallProc("loginUser", new String[] { phoneUs, passwordUs }) == true) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;

	}

	// CHECK ONLINE && OFFLINE

	public final static boolean checkOn(String phoneUs, String passwordUs) {

		try {
			if (ConnectionSQLAn.CallProc("[selectCheckOnline]", new String[] { phoneUs, passwordUs }) == true) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public static void updateCheckOnl(User user) {
		try {
			String query = "[checkOnline]";
			String[] param = { user.getPhoneUs(), "True" };
			ConnectionSQL.CallProcExec(query, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// update ip User
	public static void updateIp(User user) {
		try {
			String query = "[updateIp]";
			String[] param = { user.getPhoneUs(), user.getIp() };
			ConnectionSQLAn.CallProcExec(query, param);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Update Password Fail!");
			e.printStackTrace();
		}
	}

	public static void updateCheckOff(User user) {
		try {
			String query = "[checkOffline]";
			String[] param = { LoginController.user.getPhoneUs() };
			ConnectionSQL.CallProcExec(query, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------
	// -------------------------------------------------------
	// CHANGE PASSWORD
	public static boolean selectPassword(String phoneUsr, String password) {
		try {
			if (ConnectionSQLAn.CallProc("[loginUser]", new String[] { phoneUsr, password }) == true) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

//----------------
	public static void ChangePassword(User user) {
		try {
			String query = "[changePassword]";
			String[] param = { user.getPhoneUs(), user.getPasswordUs() };
			ConnectionSQL.CallProcExec(query, param);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Update Password Fail!");
			e.printStackTrace();
		}
	}

	/// -------------------------

	// -------------------------------------------Register
	// create user
	public static void insertUsr(User user) {
		try {
			String query = "[RegisterUser]";
			String[] param = { user.getPhoneUs(), user.getPasswordUs(), user.getNameUs(),
					DateToString(user.getDateUs()), user.isGenderUs() + "", "2222", user.getIp(), "true",
					user.getImgUs() };
			ConnectionSQLAn.CallProcExec(query, param);

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Create Fail!");
			e.printStackTrace();
		}

	}

	// select user
	public static boolean selectUsr(String phoneUs) {
		try {
			if (ConnectionSQLAn.CallProc("[selectUsr]", new String[] { phoneUs }) == true) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	// parse date
	public static final String DateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	// -----------------------------------------------------------------------
	// call proc LoginUser xu ly tren form chat
	public final static String loginUser(String phoneUs) {
		String query = "[selectUsr]";
		String[] param = { phoneUs };
		try {
			ResultSet rs = ConnectionSQLAn.CallProc1(query, param);
			while (rs.next()) {
				User user = new User();
				user.setIdUs(rs.getInt("idUs"));
				user.setPhoneUs(rs.getString("phoneUs"));
				user.setPasswordUs(rs.getString("passwordUs"));
				user.setNameUs(rs.getString("nameUs"));
				user.setImgUs(rs.getString("imgUs"));
				user.setDateUs(rs.getDate("dateUs"));
				user.setGenderUs(rs.getBoolean("genderUs"));
				user.setPort(rs.getInt("port"));
				user.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	// xu ly tren form chat
	public static User getInfoUser(String phoneUs) {
		// TODO Auto-generated method stub
		String query = "[selectUsr]";
		String[] param = { phoneUs };
		User user = new User();
		try {
			ResultSet rs = ConnectionSQLAn.CallProc1(query, param);
			while (rs.next()) {
				user.setIdUs(rs.getInt("idUs"));
				user.setPhoneUs(rs.getString("phoneUs"));
				user.setPasswordUs(rs.getString("passwordUs"));
				user.setNameUs(rs.getString("nameUs"));
				user.setImgUs(rs.getString("imgUs"));
				user.setDateUs(rs.getDate("dateUs"));
				user.setGenderUs(rs.getBoolean("genderUs"));
				user.setPort(rs.getInt("port"));
				user.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	// -----------------------------------------Forgot Pasword
	public static boolean selectPhoneCheck(String phoneUsr) {
		try {
			if (ConnectionSQLAn.CallProc("[selectUsr]", new String[] { phoneUsr }) == true) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public static void updatePassword(User user) {
		try {
			String query = "[updateUser]";
			String[] param = { user.getPhoneUs(), user.getPasswordUs() };
			ConnectionSQL.CallProcExec(query, param);
			JOptionPane.showMessageDialog(null, "Create Success!");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Update Password Fail!");
			e.printStackTrace();
		}
	}

	// --------------------------------------------------
	// ---------------------------------------------------------------Update infoDao
	public final static boolean checkUser(String phoneUs) {
		try {
			if (ConnectionSQLAn.CallProc("[selectUsr]", new String[] { phoneUs }) == true) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	// -----------------------------------------------CreateGroup

}
