package sqlwebconnection;

import java.sql.*;

class MysqlCon {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		SQLDriver drive = new SQLDriver();
		//drive.markTopComplete();
		System.out.println(drive.isData());
		drive.markTopComplete();
		drive.close();
	}
}