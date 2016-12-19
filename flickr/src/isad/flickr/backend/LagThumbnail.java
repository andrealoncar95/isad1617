package isad.flickr.backend;

import java.io.File;
import java.io.FileInputStream;
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
	
	public String md5Lortu(File f){
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
	        FileInputStream fis = new FileInputStream(f);
	
	        byte[] dataBytes = new byte[1024];
	
	        int nread = 0;
	        while ((nread = fis.read(dataBytes)) != -1) {
	          md.update(dataBytes, 0, nread);
	        };
	        byte[] mdbytes = md.digest();
	
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < mdbytes.length; i++) {
	          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }

			return sb.toString();
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
