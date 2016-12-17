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
	
	public boolean bilatuArgazkia(List<LagThumbnail> data, Integer ilara, String erabiltzailea, String md5){
				String izena = data.get(kont).izena;
				Date noiz = data.get(kont).noizAtara;
				Boolean igota = data.get(kont).igo;
				int igo = 0;
				if (igota) igo = 1;
				String karpeta = data.get(kont).karpeta;			
				

			    PhotosInterface photosInterface = f.getPhotosInterface();
			    Collection photosCollection = null;
			    photosCollection = photosInterface.getPhoto(id)(extras, 500, 0);

			    int i = 0;

			    Photo photo = null;
			    Iterator photoIterator = photosCollection.iterator();
			    while (photoIterator.hasNext()) {
			        i++;
			        photo = (Photo) photoIterator.next();
			        System.out.println(i + " - Description: " + photo.getSmallUrl())
				
				
				String patha = karpeta + File.separator + izena;
				
				File imageFile = new File(patha);
				System.out.println(patha);
			    InputStream in = null;
			    Uploader uploader = ArgazkiKud.f.getUploader();
			    PhotosInterface pint = ArgazkiKud.f.getPhotosInterface();

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
		
		
		
		
		
		
		return true;
	}
}
