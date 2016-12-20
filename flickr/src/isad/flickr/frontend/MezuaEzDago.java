package isad.flickr.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class MezuaEzDago extends JFrame {
	public MezuaEzDago() {
		super("mezua ez dago");
		getContentPane().setLayout(new BorderLayout());
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("Bilatzen ari zaren argazkia ez dago oraindik igota");
		panel.add(label1);
		panel.setBorder(new LineBorder(Color.BLACK));
		frame.add(panel, new GridBagConstraints());
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
}