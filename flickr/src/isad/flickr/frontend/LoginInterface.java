package isad.flickr.frontend;


import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class LoginInterface extends JFrame{
	
	//JPanel nagusia = new JPanel();
	JPanel nagusia = new JPanel(new SpringLayout());
	JPanel nagusiaSur = new JPanel(new BorderLayout());

	
	
	private JTextField erabiltzailea, pasahitza;
	private JLabel erabiltzailea1 = new JLabel();
	private JLabel pasahitza1 = new JLabel();
	
	private JButton login;
	
	public LoginInterface() {
		this.setResizable(false);
		setTitle("Erabiltzailea eta pasahitza sartzeko formularioa");
		
		getContentPane().setLayout(new BorderLayout());
		
		erabiltzailea = new JTextField(15);
		pasahitza = new JPasswordField(15);
		login = new JButton();
		erabiltzailea1 = new JLabel();
		pasahitza1 = new JLabel();
		
		
		login.setText("LOGIN");
		pasahitza1.setText("Pasahitza");
		erabiltzailea1.setText("Erabiltzailea");
		
		getContentPane().add(nagusia, BorderLayout.CENTER);
		
		nagusia.add(erabiltzailea1);
		nagusia.add(erabiltzailea);
		nagusia.add(pasahitza1);
		nagusia.add(pasahitza);
		
		SpringUtilities.makeCompactGrid(nagusia, 2, 2, 10, 10, 2, 2);
		
		getContentPane().add(nagusiaSur, BorderLayout.SOUTH);
		
		nagusiaSur.add(login, BorderLayout.EAST);
		
		
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void bistaratu() {
		//setSize(275, 125);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoginInterface nagusia = new LoginInterface();
		nagusia.bistaratu();

	}
	
	

}
