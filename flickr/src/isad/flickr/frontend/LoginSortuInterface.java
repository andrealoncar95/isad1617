package isad.flickr.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
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

public class LoginSortuInterface extends JPanel{

	
	JPanel nagusia = new JPanel(new SpringLayout());
	JPanel nagusiaSur = new JPanel(new BorderLayout());
	
	
	private JTextField erabiltzailea, pasahitza, pasahitzaKonf, izenAbizenak, email;
	
	private JLabel erabiltzailea1 = new JLabel();
	private JLabel pasahitza1 = new JLabel();
	private JLabel pasahitzaKonf1 = new JLabel();
	private JLabel izenAbizenak1 = new JLabel();
	private JLabel email1 = new JLabel();
	
	private JLabel argazkia;
	
	private JButton sortu;
	public LoginSortuInterface(){
		
		super(new BorderLayout());

		//Textua sartzeko kutxak sortu
		erabiltzailea = new JTextField(15);
		pasahitza = new JPasswordField(15);
		pasahitzaKonf = new JPasswordField(15);
		izenAbizenak = new JTextField(15);
		email = new JTextField(15);
	
		erabiltzailea1 = new JLabel();
		pasahitza1 = new JLabel();
		pasahitzaKonf1 = new JLabel();
		izenAbizenak1 = new JLabel();
		email1 = new JLabel();
		
		erabiltzailea1.setText("erabiltzailea");
		pasahitza1.setText("Pasahitza");
		pasahitzaKonf1.setText("Pasahitza konfirmatu");
		izenAbizenak1.setText("Izen Abizenak");
		email1.setText("email-a");
		
		sortu = new JButton();
		
		sortu.setText("SORTU");
		
		add(nagusia, BorderLayout.CENTER);
		
		nagusia.add(erabiltzailea1);
		nagusia.add(erabiltzailea);
		nagusia.add(pasahitza1);
		nagusia.add(pasahitza);
		nagusia.add(pasahitzaKonf1);
		nagusia.add(pasahitzaKonf);
		nagusia.add(izenAbizenak1);
		nagusia.add(izenAbizenak);
		nagusia.add(email1);
		nagusia.add(email);
		
		SpringUtilities.makeCompactGrid(nagusia, 5, 2, 10, 10, 2, 2);
		
		add(nagusiaSur, BorderLayout.SOUTH);
		
		nagusiaSur.add(sortu, BorderLayout.EAST);
		
		
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
                createAndShowGUI();
            }
        });;

	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Erabiltzailearen datuak sartzeko formularioa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new LoginSortuInterface();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
