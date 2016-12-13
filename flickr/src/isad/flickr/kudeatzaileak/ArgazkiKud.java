package isad.flickr.kudeatzaileak;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.scribe.model.Token;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;
import com.flickr4java.flickr.util.IOUtilities;

import isad.flickr.backend.LagThumbnail;


public class ArgazkiKud {
	public static ArgazkiKud instantzia = new ArgazkiKud();
	static Flickr f;

	REST rest;

	RequestContext requestContext;
	
	private ArgazkiKud(){
		
	}



	public void argazkiakIgo(List<LagThumbnail> data, String erabiltzailea) throws FileNotFoundException, FlickrException {
		DBkud dbkud = DBkud.getInstantzia();
		int kont = 0;
		while (kont < data.size()) {
			if (data.get(kont).igo){

				String izena = data.get(kont).izena;
				Date noiz = data.get(kont).noizAtara;
				Boolean igota = data.get(kont).igo;
				int igo = 0;
				if (igota) igo = 1;
				String karpeta = data.get(kont).karpeta;			
				
				dbkud.execSQL("INSERT INTO Argazkia (erabiltzailea, izena, data, igo, karpeta)  values ('" + erabiltzailea  + "', '"+ izena + "', '"+ noiz + "', "+ igo +", '"+  karpeta + "')");
				String patha = data.get(kont).image.getDescription();
				
				File imageFile = new File(patha);
				System.out.println(patha);
			    InputStream in = null;
			    Uploader uploader = f.getUploader();
			    PhotosInterface pint = f.getPhotosInterface();

			        try {
			            in = new FileInputStream(imageFile);
			            UploadMetaData metaData = buildPrivatePhotoMetadata();
			            metaData.setPublicFlag(false);
			            metaData.setTitle(izena);
			            String photoId = uploader.upload(in, metaData);
			            try {
			                pint.delete(photoId);
			            } catch (FlickrException e) {
			                // Ignore if user doesn't have delete permissions
			                // This will leave a *private* photo in the test account's photostream!
			                if (!e.getErrorCode().equals("99")) {
			                    throw e;
			                }
			            }

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
