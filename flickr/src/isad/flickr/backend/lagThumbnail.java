package isad.flickr.backend;

import java.sql.Date;

import javax.swing.ImageIcon;

public class lagThumbnail {
	
	ImageIcon image;
	String izena;
	Date noizAtara;
	Boolean deskargatu;
	
	public lagThumbnail(ImageIcon image, String izena, Date noizAtara, Boolean deskargatu) {
			super();
			this.izena = izena;
			this.image = image;
			this.noizAtara = noizAtara;
			this.deskargatu = deskargatu;
		}
	@Override
	public String toString() {
		return "Lag [image =" + image +", izena=" + izena + ", Noiz atara da=" + noizAtara + ", deskargatu=" + deskargatu + "]";
	}
	
	}
