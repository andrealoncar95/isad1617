package isad.flickr.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.stream.Stream;

//import java.awt.BorderLayout;
//import java.awt.Dimension;
import javax.swing.*;

import isad.flickr.kudeatzaileak.DBkud;
import isad.flickr.kudeatzaileak.LoginDB;

public class LoginInterface extends JPanel{
	
	JPanel nagusia = new JPanel(new SpringLayout());
	//JPanel nagusiaSur = new JPanel(new BorderLayout());
	
	
	private JTextField erabiltzailea, pasahitza;
	
	private JLabel erabiltzailea1 = new JLabel();
	private JLabel pasahitza1 = new JLabel();
	
	private JLabel argazkia;
	
	private JButton login;
	
	public LoginInterface() throws IOException {
		super(new BorderLayout());
		
		erabiltzailea = new JTextField(15);
		pasahitza = new JPasswordField(15);
		login = new JButton();
		erabiltzailea1 = new JLabel();
		pasahitza1 = new JLabel();
		
		
		login.setText("LOGIN");
		pasahitza1.setText("Pasahitza");
		erabiltzailea1.setText("Erabiltzailea");
		
		
		add(nagusia, BorderLayout.SOUTH);
		
		nagusia.add(erabiltzailea1);
		nagusia.add(erabiltzailea);
		nagusia.add(pasahitza1);
		nagusia.add(pasahitza);
		nagusia.add(new JLabel(" "));
		nagusia.add(login);
		
		SpringUtilities.makeCompactGrid(nagusia, 3, 2, 10, 10, 2, 2);
		
		//Set up the picture label.
		
		ImageIcon argazkia2 = new ImageIcon("flickrLogo.gif");
		Image img = argazkia2.getImage();
		
		Dimension pantaila = Toolkit.getDefaultToolkit().getScreenSize();
    	img = img.getScaledInstance((int)pantaila.getWidth() / 2, (int)pantaila.getHeight() / 2, Image.SCALE_SMOOTH);
    	ImageIcon ikonoBerria = new ImageIcon(img);
    	argazkia = new JLabel(ikonoBerria);
		argazkia.setPreferredSize(new Dimension(177, 122));
		add(argazkia, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	private void loginActionListener() {
		login.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (!LoginDB.instantzia.konektatu(erabiltzailea.getText(), pasahitza.getText()).equals(null)) {
				
			}
			else {}
			
			
			
				
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
