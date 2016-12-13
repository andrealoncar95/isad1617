package isad.flickr.backend;

import java.awt.Image;
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
	}

	
	public File getDirektorioa(){
		return dir;
	}


}
