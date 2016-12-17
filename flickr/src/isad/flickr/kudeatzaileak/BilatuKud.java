package isad.flickr.kudeatzaileak;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.RequestContext;

public class BilatuKud {
	public static BilatuKud instantzia = new BilatuKud();
	static Flickr f;
	
	//Properties properties;
	//REST rest;

	RequestContext requestContext;
	
	
	private BilatuKud(){
		
	}
	
	public boolean bilatuArgazkia(){
		return false;
	}
}
