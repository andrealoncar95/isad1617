package isad.flickr.backend;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;

import isad.flickr.kudeatzaileak.DBkud;
	

public class LoginAuthentication {
	
		public static LoginAuthentication instantzia = new LoginAuthentication();

		public LoginAuthentication(){
			
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