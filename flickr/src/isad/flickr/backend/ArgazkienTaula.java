package isad.flickr.backend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ArgazkienTaula extends JFrame {
	JButton Igo = new JButton("Igo");
	MyTableModel modeloa = new MyTableModel();

	public ArgazkienTaula() {
		super("Nire taula grafikoa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTable table = new JTable(new MyTableModel());
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
			}});
	}
	
	public static void main(String[] args) {
		new ArgazkienTaula();
	}
}

