package isad.flickr.kudeatzaileak;


import java.util.Date;
import java.util.List;

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
			}
			kont++;
		}
		
		
	}


}
