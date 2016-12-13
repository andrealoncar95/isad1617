package isad.flickr.kudeatzaileak;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;



public class DBkud {

	Connection conn = null;

	private void conOpen() {
		try {

			String url = "jdbc:sqlite:flickr.db";

			Class.forName("org.sqlite.JDBC").newInstance();
			
			conn = (Connection) DriverManager.getConnection(url);

			System.out.println("Database connection established");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Cannot connect to database server");
		}
	}



	private void conClose() {

		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		System.out.println("Database connection terminated");

	}

	private ResultSet query(Statement s, String query) {

		ResultSet rs = null;

		try {
			rs = s.executeQuery(query);
			// rs = s.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	// singleton patroia
	private static DBkud instantzia = new DBkud();

	private DBkud() {
		this.conOpen();

	}

	public static DBkud getInstantzia() {
		return instantzia;
	}

	//
	public ResultSet execSQL(String query) {
		int count = 0;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			s = (Statement) conn.createStatement();
			if (query.toLowerCase().indexOf("select") == 0) {
				// select agindu bat
				rs = this.query(s, query);
				
			} else {
				// update, delete, create agindu bat
				count = s.executeUpdate(query);
				System.out.println(count + " rows affected");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			if (s != null)
//				try {
//					s.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}

		return rs;
	}
}
