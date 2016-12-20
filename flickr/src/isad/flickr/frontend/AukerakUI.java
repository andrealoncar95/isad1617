package isad.flickr.frontend;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class AukerakUI extends JFrame {

	JButton argazkiakIgo = new JButton("Argazkiak igo edozein karpetan");
	
	
	public AukerakUI(Properties properties) {
		super("Aukeratu");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		botoiakPrestatu(properties);
		
		Container panela = this.getContentPane();
		BoxLayout bl = new BoxLayout(panela, BoxLayout.Y_AXIS);
		panela.setLayout(bl);
		setSize(400,400);
		panela.add(argazkiakIgo);
		pack();
		setVisible(true);
	}
	
	
	private void botoiakPrestatu(Properties properties) {
		argazkiakIgo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArgazkienTaula aT = null;
				SwingUtilities.getWindowAncestor(getContentPane()).dispose();

				try {
					aT = new ArgazkienTaula(properties);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				aT.setVisible(true);
				
			}
		});	
	}


}
