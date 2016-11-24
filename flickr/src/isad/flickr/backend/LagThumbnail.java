package isad.flickr.backend;

import java.sql.Date;

import javax.swing.ImageIcon;

public class LagThumbnail {
	
	ImageIcon image;
	String izena;
	Date noizAtara;
	Boolean deskargatu;
	
	public LagThumbnail(ImageIcon image, String izena, Date noizAtara, Boolean deskargatu) {
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
	public Object getBalioa(int i) {
		Object o = null;
		switch (i) {
		case 0:
			o= this.image;
			break;
		case 1:
			o = this.izena;
			break;
		case 2:
			o = this.noizAtara;
			break;
		case 3:
			o = this.deskargatu;
			break;
		default:
			break;
		}
		return o;

	}

	public void insertElementAt(Object value, int i){
		switch (i) {
		case 0:
			this.image = (ImageIcon) value;
			break;
		case 1:
			this.izena = (String) value;
			break;
		case 2:
			this.noizAtara = (Date) value;
			break;
		case 3:
			this.deskargatu = (Boolean) value;
			break;
		default:
			break;
		}
	}
	
}