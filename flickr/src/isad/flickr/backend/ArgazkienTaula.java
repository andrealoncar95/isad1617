package isad.flickr.backend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.flickr4java.flickr.FlickrException;

public class ArgazkienTaula extends JFrame {
	JButton Igo = new JButton("Igo");
	MyTableModel modeloa;

	public ArgazkienTaula(Properties properties) throws Exception {
		super("Zure argazkien datuak eta igo nahi badituzu:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTable table = new JTable(new MyTableModel(properties));
		table.setRowHeight(75);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(Igo, BorderLayout.SOUTH);	
		pack();
		setVisible(true);
		Igo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloa = (MyTableModel) table.getModel();
				try {
					try {
						modeloa.igo();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (FlickrException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
	}
}

