package isad.flickr.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.stream.Stream;

//import java.awt.BorderLayout;
//import java.awt.Dimension;
import javax.swing.*;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.util.IOUtilities;

import isad.flickr.kudeatzaileak.DBkud;
import isad.flickr.kudeatzaileak.LoginKud;

public class LoginInterface extends JPanel{
	private Properties properties = null;
	JPanel nagusia = new JPanel(new SpringLayout());
	//JPanel nagusiaSur = new JPanel(new BorderLayout());
	
	
	private JTextField apiKey, secret;
	
	private JLabel apiKey1 = new JLabel();
	private JLabel secret1 = new JLabel();
	
	private JLabel argazkia;
	
	private JButton login;
	
	public LoginInterface() throws IOException {
		super(new BorderLayout());
		
		apiKey = new JTextField(15);
		secret = new JPasswordField(15);
		login = new JButton();
		apiKey1 = new JLabel();
		secret1 = new JLabel();
		
		
		login.setText("LOGIN");
		secret1.setText("Secret");
		apiKey1.setText("Api key");
		
		
		add(nagusia, BorderLayout.SOUTH);
		
		nagusia.add(apiKey1);
		nagusia.add(apiKey);
		nagusia.add(secret1);
		nagusia.add(secret);
		nagusia.add(new JLabel(" "));
		nagusia.add(login);
		
		SpringUtilities.makeCompactGrid(nagusia, 3, 2, 10, 10, 2, 2);
		
		//Set up the picture label.
		ImageIcon argazkia2 = new ImageIcon("images/logo-flickr.gif");
		Image img = argazkia2.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		
    	ImageIcon ikonoBerria = new ImageIcon(img);
    	argazkia = new JLabel(ikonoBerria);
		argazkia.setPreferredSize(new Dimension(177, 122));
		add(argazkia, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		loginActionListener();
	}
	
	private void loginActionListener() {
		login.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				 InputStream in = null;
			        try {
			            in = new FileInputStream("setup.properties");
			            properties = new Properties();
			            properties.load(in);
			        } finally {
			            IOUtilities.close(in);
			        }
			       
				
				
//				if ((properties.getProperty("token").length() < 2) || ((apiKey.getText().equals("") || secret.getText().equals("")))){
//					new MezuaLogin();
//					new LoginInterface();
//				}
//				else{
					LoginKud.instantzia.konektatu(apiKey.getText(), secret.getText());
					SwingUtilities.getWindowAncestor(nagusia).dispose();
					AukerakUI aU;
					aU = new AukerakUI();
					aU.setVisible(true);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FlickrException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
	}});

}
	

	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
	 * @throws IOException 
     */
	
	
    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("Erabiltzailea eta pasahitza sartzeko formularioa");
        //Create and set up the content pane.
        JComponent newContentPane = new LoginInterface();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });;

	}
	
	
	

}
