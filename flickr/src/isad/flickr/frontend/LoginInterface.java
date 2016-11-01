package isad.flickr.frontend;

import java.awt.*;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
import javax.swing.*;

/*import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
*/

public class LoginInterface extends JPanel{
	
	JPanel nagusia = new JPanel(new SpringLayout());
	JPanel nagusiaSur = new JPanel(new BorderLayout());
	
	
	private JTextField erabiltzailea, pasahitza;
	
	private JLabel erabiltzailea1 = new JLabel();
	private JLabel pasahitza1 = new JLabel();
	
	private JLabel argazkia;
	
	private JButton login;
	
	public LoginInterface() {
		super(new BorderLayout());
		
		erabiltzailea = new JTextField(15);
		pasahitza = new JPasswordField(15);
		login = new JButton();
		erabiltzailea1 = new JLabel();
		pasahitza1 = new JLabel();
		
		
		login.setText("LOGIN");
		pasahitza1.setText("Pasahitza");
		erabiltzailea1.setText("Erabiltzailea");
		
		add(nagusia, BorderLayout.CENTER);
		
		nagusia.add(erabiltzailea1);
		nagusia.add(erabiltzailea);
		nagusia.add(pasahitza1);
		nagusia.add(pasahitza);
		
		SpringUtilities.makeCompactGrid(nagusia, 2, 2, 10, 10, 2, 2);
		
		add(nagusiaSur, BorderLayout.SOUTH);
		
		nagusiaSur.add(login, BorderLayout.EAST);
		
		//Set up the picture label.
		argazkia = new JLabel(createImageIcon("images/flickrLogo.gif"));
		
		argazkia.setPreferredSize(new Dimension(177, 122));
		
		add(argazkia, BorderLayout.NORTH);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

	}
	
	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = LoginInterface.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
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
	
	/*public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });;

	}*/
	
	
	

}
