package isad.flickr.kudeatzaileak;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
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
	            in = getClass().getResourceAsStream("/setup.properties");
	            properties = new Properties();
	            properties.load(in);
	        } finally {
	            IOUtilities.close(in);
	        }
	        if (properties.getProperty("token") == null){
	        properties.setProperty("apiKey", api);
	        properties.setProperty("secret", secret);
	        Flickr flickr = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
	        Flickr.debugStream = false;
	        AuthInterface authInterface = flickr.getAuthInterface();

	        Scanner scanner = new Scanner(System.in);

	        Token token = authInterface.getRequestToken();
	        System.out.println("token: " + token);

	        String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
	        System.out.println("Itsatsi hurrengo URL-a nabigatzailean: ");
	        System.out.println(url);
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
	        }
	        return properties;
	}
}