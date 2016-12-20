package isad.flickr.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Date;

import javax.swing.ImageIcon;

public class LagThumbnail {

	public ImageIcon image;
	public String izena;
	public Date noizAtara;
	public Boolean igo;
	public String karpeta;
	
	public LagThumbnail(ImageIcon image, String izena, Date noizAtara, Boolean igo, String karpeta) {
			this.izena = izena;
			this.image = image;
			this.noizAtara = noizAtara;
			this.igo = igo;
			this.karpeta = karpeta;
		}
	@Override
	public String toString() {
		return "Lag [image =" + image +", izena=" + izena + ", Noiz atara da=" + noizAtara + ", igo=" + igo + ", karpeta=" + karpeta + "]";
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
			o = this.igo;
			break;
		case 4:
			o = this.karpeta;
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
			this.igo = (Boolean) value;
			break;
		case 4:
			this.karpeta = (String) value;
			break;
		default:
			break;
		}
	}
	
	public byte[] createChecksum(String filename) throws Exception {
	       InputStream fis =  new FileInputStream(filename);

	       byte[] buffer = new byte[1024];
	       MessageDigest complete = MessageDigest.getInstance("MD5");
	       int numRead;

	       do {
	           numRead = fis.read(buffer);
	           if (numRead > 0) {
	               complete.update(buffer, 0, numRead);
	           }
	       } while (numRead != -1);

	       fis.close();
	       return complete.digest();
	   }
	
	public String getMD5Checksum(String filename) throws Exception {
	       byte[] b = createChecksum(filename);
	       String result = "";

	       for (int i=0; i < b.length; i++) {
	           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
	       }
	       return result;
	   }

	
}
