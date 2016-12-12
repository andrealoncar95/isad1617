package isad.flickr.backend;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

public class MyTableModel extends AbstractTableModel {
	//static final File dir = new File("C:/Users/eduardo/Pictures/ORDENADOR DE MESA/cadiz/cadiz1");
	private PhotoList<Photo> lista = new PhotoList<Photo>();
	private Vector<LagThumbnail> data = new Vector<LagThumbnail>();
	private Vector<String> columnNames = new Vector<String>();
	private ArgazkiakPantailaratu ap;
	private FileChooser fC;
	
	public MyTableModel(){
		kargatu();
	}
	
	private void kargatu(){
		hasieratuZutabeIzenak();
		//lista = ap.irudiakItzuli();
		fC= new FileChooser();
		lista= fC.irudiakLortu();
            for ( Photo f : lista) {
            	//System.out.println("image: " + f.getName());
            	
                ImageIcon image = new ImageIcon(f.getTitle());
               
            	Image img = image.getImage();
            	Image argazkia = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            	ImageIcon ikonoBerria = new ImageIcon(argazkia);
            	Date d = (Date) f.getLastUpdate();
            	String karpetaIzena= "";
            	data.add(new LagThumbnail(ikonoBerria, f.getTitle() ,d,  false, karpetaIzena));
            }
        }
	
	public void hasieratuZutabeIzenak(){
		columnNames.add("Irudia");
		columnNames.add("Izena");
		columnNames.add("Data");
		columnNames.add("Deskargatua?");
		columnNames.add("Karpeta");
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0).getBalioa(arg1);
	}
	
	public String getColumnName(int zut) {
		return columnNames.get(zut);
	}
	
	public Class<?> getColumnClass(int col){
		Class o = null;
		switch (col) {
		case 0:
			o = ImageIcon.class;
			break;
		case 1:
			o = String.class;
			break;
		case 2:
			o = Date.class;
			break;
		case 3:
			o = Boolean.class;
			break;
		case 4:
			o = String.class;
			break;
		default:
			break;
		}
		return o;
	}
	
	public boolean isCellEditable(int row, int col){
		return col == 1;
	}
	
	public void setValueAt (Object value, int i, int j){
			data.get(i).insertElementAt(value, j);
	}
	

}

