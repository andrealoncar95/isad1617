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

public class MyTableModel extends AbstractTableModel {
	static final File dir = new File("C:/Users/zarraga/Pictures/Referee");
	private Vector<LagThumbnail> data = new Vector<LagThumbnail>();
	private Vector<String> columnNames = new Vector<String>();
	
	
	public MyTableModel(){
		kargatu();
	}
	
	private void kargatu(){
		hasieratuZutabeIzenak();

        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles()) {
            	//System.out.println("image: " + f.getName());
            	
                ImageIcon image = new ImageIcon("dir"+ f.getName());
               
            	Image img = image.getImage();
            	Image argazkia = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            	ImageIcon ikonoBerria = new ImageIcon(argazkia);
            	Long ms = f.lastModified();
            	Date d = new Date(ms);
            	data.add(new LagThumbnail(ikonoBerria, f.getName() ,d,  false));
            }
        }
	}
	
	public void hasieratuZutabeIzenak(){
		columnNames.add("Irudia");
		columnNames.add("Izena");
		columnNames.add("Data");
		columnNames.add("Deskargatua?");
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
		default:
			break;
		}
		return o;
	}
	
	public boolean isCellEditable(int row, int col){
		return col < 2;
	}
	
	public void setValueAt (Object value, int i, int j){
			data.get(i).insertElementAt(value, j);
	}
	
	public static void main(String[] args) {
		MyTableModel taula = new MyTableModel();
		System.out.println("Zutabeak:" + taula.getColumnCount());
		System.out.println("Lerroak: " + taula.getRowCount());
		System.out.println("(2,2) elementuaren balioa:" + taula.getValueAt(2, 2));
		System.out.println("Lehenengo zutabearen izena:" + taula.getColumnName(0));
	
	}
	

}