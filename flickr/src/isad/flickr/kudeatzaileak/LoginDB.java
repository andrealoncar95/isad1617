package isad.flickr.kudeatzaileak;

import java.sql.ResultSet;
import java.sql.SQLException;

import isad.flickr.backend.LoginLag;


public class LoginDB {
	public static LoginDB instantzia = new LoginDB();

	public LoginDB(){
		
	}

	public LoginLag konektatu(String erabiltzaile, String pasahitza) {

		DBkud dbkud = DBkud.getInstantzia();
		ResultSet rs = dbkud.execSQL("select username, password from erabiltzailea where username = '" + erabiltzaile + "' and password = '" + pasahitza + "')");
		LoginLag emaitza = null;
		String username =  null;
		String password = null;
		try {
			while(rs.next()){
				username = rs.getString("erabiltzaile");
				password= rs.getString("pasahitza");
				
				emaitza = new LoginLag(username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emaitza;
	}
	

}