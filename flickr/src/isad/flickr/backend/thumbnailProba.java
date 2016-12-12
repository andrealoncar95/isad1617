package isad.flickr.backend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

public class thumbnailProba extends JFrame{
    static File dir = null;
    private JFileChooser file= new JFileChooser();
   
    Vector<LagThumbnail> data = new Vector<LagThumbnail>();
    private Vector<String> columnNames = new Vector<String>();
    
    private JPanel panela = new JPanel();
    
    public thumbnailProba() {
    	
    	FileNameExtensionFilter filtroJPG = new FileNameExtensionFilter("*.JPG", "jpg");
    	FileNameExtensionFilter filtroGIF = new FileNameExtensionFilter("*.GIF", "gif");
    	FileNameExtensionFilter filtroPNG = new FileNameExtensionFilter("*.PNG", "png");
    	file.setFileFilter(filtroGIF);
    	file.setFileFilter(filtroPNG);
    	file.setFileFilter(filtroJPG);
    	file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    	file.showOpenDialog(this);
    	dir=file.getSelectedFile();
    
    	
    	setLayout(new GridLayout(5, 5));

    	
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles()) {
            	//System.out.println("image: " + f.getName());
            	//Path fitx = "C:/Users/eduardo/Pictures/ORDENADOR DE MESA/cadiz/cadiz1/"+ f.getName();
            	//System.out.println(Files.readAttributes(fitx, BasicFileAttributes.class));
                ImageIcon image = new ImageIcon(dir.toString());
                Icon img = new ImageIcon(image.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
            	//Image argazki= (Image) img;
               // ImageIcon ikonoBerria = new ImageIcon(argazki);
            	//String izena = img.toString();
            	//System.out.println(image.getImage());
            	
            	//data.add(new lagThumbnail("Kathy", "Smith", "Snowboarding", 5, false));
            	
            	JLabel imagelabel = new JLabel(img);
                imagelabel.setPreferredSize(new Dimension(17, 22));
                panela.add(imagelabel, BorderLayout.CENTER);
            }
            getContentPane().add(panela);

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

