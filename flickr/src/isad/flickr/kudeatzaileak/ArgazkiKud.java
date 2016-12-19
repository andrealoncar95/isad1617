package isad.flickr.kudeatzaileak;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.scribe.model.Token;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;
import com.flickr4java.flickr.util.IOUtilities;

import isad.flickr.backend.LagThumbnail;


public class ArgazkiKud {
	public static ArgazkiKud instantzia = new ArgazkiKud();
	static Flickr f;
	
	//Properties properties;
	//REST rest;

	RequestContext requestContext;
	
	private ArgazkiKud(){
		
	}



	public void argazkiakIgo(List<LagThumbnail> data, String erabiltzailea) throws FlickrException, IOException {
		DBkud dbkud = DBkud.getInstantzia();
		int kont = 0;
		/* InputStream in1 = null;
	        try {
	            in1 = getClass().getResourceAsStream("/setup.properties");
	            properties = new Properties();
	            properties.load(in1);
	        } finally {
	            IOUtilities.close(in1);
	        }*/
		//f = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
		while (kont < data.size()) {
			if (data.get(kont).igo){

				String izena = data.get(kont).izena;
				Date noiz = data.get(kont).noizAtara;
				Boolean igota = data.get(kont).igo;
				int igo = 0;
				if (igota) igo = 1;
				String karpeta = data.get(kont).karpeta;	
				String patha = karpeta + File.separator + izena;
				
				File imageFile = new File(patha);
				System.out.println(patha);
				String md5 = data.get(kont).md5Lortu(imageFile);
				
				//Datu basean MD5a ere sartu dogu!
				dbkud.execSQL("REPLACE INTO Argazkia (erabiltzailea, izena, data, igo, karpeta, md5)  values ('" + erabiltzailea  + "', '"+ izena + "', '"+ noiz + "', "+ igo +", '"+  karpeta + "', '"+ md5 + "')");
				
			    InputStream in = null;
			    Uploader uploader = ArgazkiKud.f.getUploader();
			    try {
			            in = new FileInputStream(imageFile);
			            UploadMetaData metaData = buildPrivatePhotoMetadata();
			            metaData.setPublicFlag(false);
			            metaData.setTitle(izena);
			            String photoId = uploader.upload(in, metaData);
			            
			          /*  try {
			                pint.delete(photoId);
			            } catch (FlickrException e) {
			                // Ignore if user doesn't have delete permissions
			                // This will leave a *private* photo in the test account's photostream!
			                if (!e.getErrorCode().equals("99")) {
			                    throw e;
			                }
			            }
*/
			            System.out.println("photoId:" + photoId + " igota!");
			        } finally {
			            IOUtilities.close(in);
			}
			}
			kont++;
		}
	}
	
	
	
	private UploadMetaData buildPrivatePhotoMetadata() {
        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setPublicFlag(false);
        return uploadMetaData;
}


}

