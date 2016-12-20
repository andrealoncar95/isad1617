package isad.flickr.backend;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;
import com.flickr4java.flickr.tags.Tag;
import com.flickr4java.flickr.util.IOUtilities;

import flickrBackup.model.Argazkia;
import flickrBackup.model.LagThumbnail;

public class ArgazkiakPantailaratu {
// komentario
	static String apiKey;

	static String sharedSecret;

	static Flickr f;

	REST rest;

	RequestContext requestContext;

	 Properties properties = null;

	public ArgazkiakPantailaratu() throws IOException {
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/setup.properties");
			properties = new Properties();

			properties.load(in);
		} finally {
			IOUtilities.close(in);
		}
		f = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
		requestContext = RequestContext.getRequestContext();
		Auth auth = new Auth();
		auth.setPermission(Permission.READ);
		auth.setToken(properties.getProperty("token"));
		auth.setTokenSecret(properties.getProperty("tokensecret"));
		requestContext.setAuth(auth);
		Flickr.debugRequest = false;
		Flickr.debugStream = false;
	}

	public static void main(String[] args) {
		try {
			ArgazkiakPantailaratu t = new ArgazkiakPantailaratu();
			t.irudiakItzuli();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public ArrayList<LagThumbnail> argazkiakErakutsi() {
		ArrayList<LagThumbnail> argkol = new ArrayList<LagThumbnail>();
		String userId = properties.getProperty("nsid");
		String secret1 = properties.getProperty("secret");
		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		PhotosInterface pi = f.getPhotosInterface();
		try {
			Collection<Photo> argazkiak = pi.getNotInSet(100, 1);
			LagThumbnail thumbnail;
			for (Photo argazki: argazkiak){
				//if (!badago(argazki.getId())){
					Photo arg = pi.getInfo(argazki.getId(), secret1);
					System.out.println("\t" + "Title: "+arg.getTitle()+" Deskr: "+arg.getDescription()+" Tags: " +arg.getTags().toString());
					//ids.put(arg.getId(), null);
					thumbnail = argazkiaSortu(arg);
					argkol.add(thumbnail);
				//}
			}
			photosets = photosetsInterface.getList(userId);
			Collection<Photoset> bildumak = photosets.getPhotosets();

			for (Photoset photoset : bildumak) {
				String id = photoset.getId();
				String title = photoset.getTitle();
				String secret = photoset.getSecret();
				int photoCount = photoset.getPhotoCount();
				System.out.println("Title:" + title +" Deskr: " + photoset.getDescription());

				PhotoList<Photo> col;
				int PHOTOSPERPAGE = 2;
				int HOWMANYPAGES = photoCount/PHOTOSPERPAGE;
				if (photoCount % PHOTOSPERPAGE != 0){
					HOWMANYPAGES++;
				}
				for (int page = 1; page <= HOWMANYPAGES; page++) {
					col = photosetsInterface.getPhotos(id /* photosetId */, PHOTOSPERPAGE, page);
					
					for (Photo argazkia : col) {
						if (!badago(argazkia.getId())){
							Photo arg = pi.getInfo(argazkia.getId(), secret1);
							ids.put(arg.getId(), null);
							System.out.println("\t" + "Title: "+arg.getTitle()+" Deskr: "+arg.getDescription()+" Tags: " +arg.getTags().toString());
							a = argazkiaSortu(arg);
//							saveImage(argazkia);
							argkol.add(a);
						}
					}
				}
			}
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		return argkol;
	}

	
	private LagThumbnail argazkiaSortu(Photo p){
		String id =p.getId();
		String deskribapena = p.getDescription();
		String title = p.getTitle();
		ImageIcon img = null;
		try{
			img = new ImageIcon(p.getThumbnailImage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<Tag> tags= p.getTags();
		ArrayList<String> etiketak = new ArrayList<String>();
		String etiketa = null;
		for(Tag tag: tags){
			etiketa = tag.getValue();
			etiketak.add(etiketa);
		}
		LagThumbnail arg = new LagThumbnail(title, deskribapena, etiketak, img, id);
		arg.setPribatutasuna(p.isPublicFlag(), p.isFriendFlag(), p.isFamilyFlag());
		return arg;
	}
	public void showPhotos() {

		String userId = properties.getProperty("nsid");
		//String secret = properties.getProperty("secret");

		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		PhotosInterface photosInterface = f.getPhotosInterface();
		try {
			photosets = photosetsInterface.getList(userId);

			Collection<Photoset> bildumak = photosets.getPhotosets();

			for (Photoset photoset : bildumak) {
				String id = photoset.getId();
				String title = photoset.getTitle();
				String secret = photoset.getSecret();
				int photoCount = photoset.getPhotoCount();

				System.out.println("Albumaren titulua:" + title + " Secret:" + secret + " Count:" + photoCount);

				PhotoList<Photo> col;
				int PHOTOSPERPAGE = 2;
				int HOWMANYPAGES = 1; // (int) Math.ceil(photoCount / 10);
				for (int page = 1; page <= HOWMANYPAGES; page++) {
					col = photosetsInterface.getPhotos(id /* photosetId */, PHOTOSPERPAGE, page);

					for (Photo argazkia : col) {
						saveImage(argazkia);
						System.out.println(argazkia.getTitle() + ": ");
						System.out.println(argazkia.getDescription() + ": ");
						System.out.println(argazkia.getDateAdded() + ": ");
						System.out.println(argazkia.getDatePosted() + ": ");
						System.out.println(argazkia.getDateTaken() + ": ");
						System.out.println(argazkia.getGeoData() + ": ");
						System.out.println(argazkia.getTags());
					}
				}
			}
		} catch (FlickrException e) {
			e.printStackTrace();
		}

	}
	
	public List<Photo> irudiakItzuli() {
		

		String userId = properties.getProperty("nsid");
		// String secret = properties.getProperty("secret");
		PhotoList<Photo> lista = new PhotoList<Photo>();
		PhotosetsInterface photosetsInterface = f.getPhotosetsInterface();
		Photosets photosets;
		
		try {
			photosets = photosetsInterface.getList(userId);
			
			Collection<Photoset> bildumak = photosets.getPhotosets();		
			for (Photoset photoset : bildumak) {
				String id = photoset.getId();
				photoset.getPhotoCount();
				PhotoList<Photo> col;
				int PHOTOSPERPAGE = 2;
				int HOWMANYPAGES = 1; // (int) Math.ceil(photoCount / 10);
				for (int page = 1; page <= HOWMANYPAGES; page++) {
					col = photosetsInterface.getPhotos(id, PHOTOSPERPAGE, page);

					for (Photo argazkia : col) {
						
						lista.add(argazkia);
						
					}
				}
				
			}
			
			
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		
		return lista;
	}


	// convert filename to clean filename
	public static String convertToFileSystemChar(String name) {
		String erg = "";
		Matcher m = Pattern.compile("[a-z0-9 _#&@\\[\\(\\)\\]\\-\\.]", Pattern.CASE_INSENSITIVE).matcher(name);
		while (m.find()) {
			erg += name.substring(m.start(), m.end());
		}
		if (erg.length() > 200) {
			erg = erg.substring(0, 200);
			System.out.println("cut filename: " + erg);
		}
		return erg;
	}

	@SuppressWarnings("deprecation")
	public boolean saveImage(Photo p) {

		String path = "images" + File.separator;
		String cleanTitle = convertToFileSystemChar(p.getTitle());

		File orgFile = new File(path + File.separator + cleanTitle + "_" + p.getId() + "_o." + p.getOriginalFormat());
		File largeFile = new File(path + File.separator + cleanTitle + "_" + p.getId() + "_b." + p.getOriginalFormat());

		if (orgFile.exists() || largeFile.exists()) {
			System.out.println(p.getTitle() + "\t" + p.getLargeUrl() + " skipped!");
			return false;
		}

		try {
			Photo nfo = f.getPhotosInterface().getInfo(p.getId(), null);
			if (nfo.getOriginalSecret().isEmpty()) {
				ImageIO.write(p.getLargeImage(), p.getOriginalFormat(), largeFile);
				System.out.println(p.getTitle() + "\t" + p.getLargeUrl() + " was written to " + largeFile.getName());
			} else {
				p.setOriginalSecret(nfo.getOriginalSecret());
				ImageIO.write(p.getOriginalImage(), p.getOriginalFormat(), orgFile);
				System.out.println(p.getTitle() + "\t" + p.getOriginalUrl() + " was written to " + orgFile.getName());
			}
		} catch (FlickrException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}