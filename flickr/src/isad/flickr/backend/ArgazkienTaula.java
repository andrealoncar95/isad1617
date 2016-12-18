package isad.flickr.backend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.flickr4java.flickr.FlickrException;

import isad.flickr.frontend.MezuaBadago;
import isad.flickr.frontend.MezuaEzDago;
import isad.flickr.kudeatzaileak.BilatuKud;

public class ArgazkienTaula extends JFrame {
	MyTableModel modeloa;
	JPanel botoiPanela = new JPanel(new BorderLayout());
	JButton Igo = new JButton("Igo");
	JButton argazkiakBilatu = new JButton("Bilatu");

	public ArgazkienTaula(Properties properties) throws Exception {
		super("Zure argazkien datuak eta igo nahi badituzu:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTable table = new JTable(new MyTableModel(properties));
		table.setRowHeight(75);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.NORTH);
		
		botoiPanela.add(argazkiakBilatu, BorderLayout.EAST);
		botoiPanela.add(Igo, BorderLayout.WEST);
		//botoiPanela.setVisible(true);
		getContentPane().add(botoiPanela, BorderLayout.SOUTH);
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
		
		argazkiakBilatu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloa = (MyTableModel) table.getModel();
				try {
					if(modeloa.bilatuArgazkia(table.getSelectedRow())){
						new MezuaBadago();
					}
					else new MezuaEzDago();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
	}
}

