package isad.flickr.backend;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AuthenticationProba {
	
	String uri = "https://www.flickr.com/services/oauth/request_token";
	
	
	public void main(String[] args) throws MalformedURLException, IOException {
		URLConnection uc = new URL("http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=e3471e67d4ac10c64055420d9b211b4f&per_page=1&text=Bangalore").openConnection();

	}
}
