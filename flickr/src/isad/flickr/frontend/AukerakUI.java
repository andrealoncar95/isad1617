package isad.flickr.frontend;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import isad.flickr.backend.ArgazkienTaula;

public class AukerakUI extends JFrame {

	JButton argazkiakIgo = new JButton("Argazkiak igo");
	
	
	public AukerakUI(Properties properties) {
		super("Aukeratu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		botoiakPrestatu(properties);
		
		Container panela = this.getContentPane();
		BoxLayout bl = new BoxLayout(panela, BoxLayout.Y_AXIS);
		panela.setLayout(bl);
		panela.add(argazkiakIgo);
		setVisible(true);
	}
	
	
	private void botoiakPrestatu(Properties properties) {
		argazkiakIgo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArgazkienTaula aT = null;
				try {
					aT = new ArgazkienTaula(properties);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				aT.setVisible(true);
				
			}
		});
	}


	/*public static void main(String[] args) {
		
			AukerakUI pantailaNagusia = new AukerakUI();
			pantailaNagusia.setSize(300, 200);
			pantailaNagusia.pack();
			pantailaNagusia.setVisible(true);
			
	}
*/
}
