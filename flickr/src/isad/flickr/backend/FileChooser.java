package isad.flickr.backend;

import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

public class FileChooser extends JFrame {

	File dir = null;
	private JFileChooser file= new JFileChooser();

	public FileChooser() {

		FileNameExtensionFilter filtroJPG = new FileNameExtensionFilter("*.JPG", "jpg");
		FileNameExtensionFilter filtroGIF = new FileNameExtensionFilter("*.GIF", "gif");
		FileNameExtensionFilter filtroPNG = new FileNameExtensionFilter("*.PNG", "png");
		file.setFileFilter(filtroGIF);
		file.setFileFilter(filtroPNG);
		file.setFileFilter(filtroJPG);
		file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		file.showOpenDialog(this);
		dir=file.getSelectedFile();
		getContentPane().add(file);
		setVisible(true);
		

	}

	public ArrayList<Photo> irudiakLortu() {
		ArrayList<Photo> argazkiak= new ArrayList<Photo>();
		System.out.println(dir);
		if (dir.isDirectory()) { // make sure it's a directory
			for (final File f : dir.listFiles()) {
				
                Icon img = new ImageIcon(image.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
            	Photo argazki= (Photo) img;
            	argazkiak.add(argazki);
            	
			}
		}

		return argazkiak;

	}


}
