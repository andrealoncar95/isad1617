package isad.flickr.backend;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class thumbnailProba extends JFrame{
    static final File dir = new File("C:/Users/eduardo/Pictures/ORDENADOR DE MESA/cadiz/cadiz1");

    public thumbnailProba() {
    	
    	setLayout(new GridLayout(5, 5));

        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles()) {
            	System.out.println("image: " + f.getName());
                
                ImageIcon image = new ImageIcon("C:/Users/eduardo/Pictures/ORDENADOR DE MESA/cadiz/cadiz1/"+ f.getName());
               
            	Image img = image.getImage();
            	Image argazkia = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            	ImageIcon ikonoBerria = new ImageIcon(argazkia);
            	
            	JLabel imagelabel = new JLabel(ikonoBerria);
                imagelabel.setPreferredSize(new Dimension(17, 22));

                add(imagelabel);
            }
        }
    }
    public void bistaratu() {
		setSize(275, 125);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
    public static void main(String[] args) {
		thumbnailProba proba = new thumbnailProba();
		proba.bistaratu();

	}
}

