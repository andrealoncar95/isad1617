package isad.flickr.kudeatzaileak;

import java.sql.ResultSet;
import java.sql.SQLException;

import isad.flickr.backend.LoginLag;


public class LoginDB {
	public static LoginDB instantzia = new LoginDB();

	public LoginDB(){
		
	}

	public LoginLag konektatu(String username, String pasahitza) {

		DBkud dbkud = DBkud.getInstantzia();
		ResultSet rs = dbkud.execSQL("select username, pasahitza from erabiltzailea where username = '" + username + "' and pasahitza = '" + pasahitza + "')");
		LoginLag emaitza = null;
		try {
			while(rs.next()){
				String erabiltzaile= rs.getString("username");
				String password = rs.getString("pasahitza");
				
				emaitza = new LoginLag(erabiltzaile, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	

}