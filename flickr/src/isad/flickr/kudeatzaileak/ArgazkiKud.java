package isad.flickr.kudeatzaileak;


import java.io.File;
import java.util.Date;
import java.util.List;

import org.scribe.model.Token;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;

import isad.flickr.backend.LagThumbnail;


public class ArgazkiKud {
	public static ArgazkiKud instantzia = new ArgazkiKud();
	
	private ArgazkiKud(){
		
	}



	public void argazkiakIgo(List<LagThumbnail> data, String erabiltzailea) {
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
			
				
				/*Uploader uploader = new Uploader(apiKey, secretKey);
				UploadMetaData uploadMetaData = new UploadMetaData();
				uploadMetaData.setTitle(izena);

				Flickr flickr;
				Auth auth = flickr.getAuthInterface().checkToken(token);
				System.out.println("auth: " + auth);
				RequestContext.getRequestContext().setAuth(auth);
				uploader.upload(data.get(kont).image, uploadMetaData);*/
			}
			kont++;
		}
		
		
	}


}
