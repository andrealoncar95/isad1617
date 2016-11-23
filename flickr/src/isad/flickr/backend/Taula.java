package isad.flickr.backend;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Taula extends JFrame {

	public Taula() {
		super("Nire taula grafikoa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTable table = new JTable(new MyTableModel());
		JScrollPane scrollPane = new JScrollPane(table);
		setContentPane(scrollPane);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Taula();
	}
}

