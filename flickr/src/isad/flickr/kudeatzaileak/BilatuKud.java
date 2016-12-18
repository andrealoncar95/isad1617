package isad.flickr.kudeatzaileak;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.collections.Collection;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;
import com.flickr4java.flickr.util.IOUtilities;

import isad.flickr.backend.LagThumbnail;

public class BilatuKud {
	public static BilatuKud instantzia = new BilatuKud();
	static Flickr f;
	
	//Properties properties;
	//REST rest;

	RequestContext requestContext;
	
	
	private BilatuKud(){
		
	}
	
	public Boolean bilatuArgazkia(String md5){
		//SELECT bat datu basetik ilara horretako argazkia itzultzeko
		return //SELECT-AN LORTU BADA ARGAZKIA ORDUAN JADA IGOTA DAGO BERAZ TRUE BESTELA FALSE;
	}
}
