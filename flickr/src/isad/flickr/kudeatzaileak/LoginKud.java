package isad.flickr.kudeatzaileak;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.util.IOUtilities;


public class LoginKud {
	public static LoginKud instantzia = new LoginKud();
	private Properties properties = null;
	public LoginKud(){

	}

	public Properties konektatu(String api, String secret) throws IOException, FlickrException {
		InputStream in = null;
		try {
			in = new FileInputStream("setup.properties");
			properties = new Properties();
			properties.load(in);
		} finally {
			IOUtilities.close(in);
		}
		if (properties.getProperty("token").length() < 2){
			properties.setProperty("apiKey", api);
			properties.setProperty("secret", secret);
			ArgazkiKud.f = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
			Flickr.debugStream = false;
			AuthInterface authInterface = ArgazkiKud.f.getAuthInterface();

			Scanner scanner = new Scanner(System.in);

			Token token = authInterface.getRequestToken();
			System.out.println("token: " + token);

			String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);

			System.out.println("Hurrengo URL-a nabigatzailean irekiko zaizu: ");
			System.out.println(url);
			
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Itsatsi hemen emandako kodea:");
			System.out.print(">>");

			String tokenKey = scanner.nextLine();
			scanner.close();

			Token requestToken = authInterface.getAccessToken(token, new Verifier(tokenKey));
			System.out.println("Ondo konektatuta");

			Auth auth = authInterface.checkToken(requestToken);

			properties.setProperty("token", requestToken.getToken());
			properties.setProperty("tokensecret", requestToken.getSecret());
			properties.setProperty("nsid", auth.getUser().getId());
			properties.setProperty("displayname", auth.getUser().getRealName());
			properties.setProperty("username", auth.getUser().getUsername());
			File file = new File("setup.properties");
			FileOutputStream fr=new FileOutputStream(file);
			properties.store(fr, null);
		}
			REST rest = new REST();
			rest.setHost(properties.getProperty("host"));

			ArgazkiKud.f = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), rest);

			Auth auth = new Auth();
			auth.setPermission(Permission.READ);
			auth.setToken(properties.getProperty("token"));
			auth.setTokenSecret(properties.getProperty("tokensecret"));

			RequestContext requestContext = RequestContext.getRequestContext();
			requestContext.setAuth(auth);
			ArgazkiKud.f.setAuth(auth);

		
		return properties;
	}
}