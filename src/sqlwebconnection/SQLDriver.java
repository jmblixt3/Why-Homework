package sqlwebconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sqlwebconnection.TODO;

public class SQLDriver {
	private Connection con;
	private Statement stmt;

	public SQLDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://85.10.205.173:3306/whydohomework?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"jmblixt3", "pig18sheep");
			// "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
			// here sonoo is database name, root is username and password
			stmt = con.createStatement();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String[] getTerm() {
		String[] identification = new String[3];
		try {

			ResultSet rs = stmt.executeQuery(" SELECT username, password, url \n" + "from whyhomework\n" + ";");
			rs.next();
			System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
			identification[0] = rs.getString(1);// username
			identification[1] = rs.getString(2);// password
			identification[2] = rs.getString(3);// url
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return identification;
	}

	public void deleteCompleted() {
		try {
			stmt = con.createStatement();
			String sql = "DELETE FROM whyhomework " + "WHERE fulfilled = 1";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void markTopComplete() {
		try {
			String username = null;
			ResultSet rs = stmt.executeQuery("SELECT * FROM whyhomework " + "LIMIT 1;");
			rs.next();
			username = rs.getString(1);
			System.out.println(username);
			stmt = con.createStatement();
			String sql = "UPDATE `whydohomework`.`whyhomework` SET `fulfilled` = '1' WHERE (username= '"+username+"');\n";
			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// TODO Make Verification
	public boolean isData() {// Temporary code with now verfication
		try {
			boolean isData;
			ResultSet rs = stmt.executeQuery("SELECT COUNT(1) FROM whyhomework");
			rs.next();
			//System.out.println(rs.getString(1));
			if(Integer.parseInt(rs.getString(1)) >= 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
