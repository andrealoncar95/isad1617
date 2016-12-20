package isad.flickr.frontend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.flickr4java.flickr.FlickrException;

import isad.flickr.backend.MyTableModel;

public class ArgazkienTaula extends JFrame {
	MyTableModel modeloa;
	JTable table;
	JPanel botoiPanela1 = new JPanel(new BorderLayout());
	JPanel listPaneNorte = new JPanel();
	JPanel listPaneSur = new JPanel();
	
	JButton igo = new JButton("Argazkiak igo");
	JButton argazkiakBilatu = new JButton("Bilatu");
	
	JButton argazkiakPC = new JButton("Argazkiak aukeratu");


	public ArgazkienTaula() throws Exception {
		super("Zure argazkien datuak eta igo nahi badituzu:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable(new MyTableModel());
		table.setRowHeight(75);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		listPaneNorte.setLayout(new BoxLayout(listPaneNorte, BoxLayout.PAGE_AXIS));
		listPaneSur.setLayout(new BoxLayout(listPaneSur, BoxLayout.PAGE_AXIS));
		
		listPaneNorte.add(argazkiakPC);
		listPaneSur.add(igo);
		listPaneSur.add(argazkiakBilatu);

		
		botoiPanela1.add(listPaneSur, BorderLayout.SOUTH);
		botoiPanela1.add(listPaneNorte, BorderLayout.NORTH);
		getContentPane().add(botoiPanela1, BorderLayout.WEST);
		pack();
		setVisible(true);
		igoActionListener();
		bilatuActionListener();
		argazkiakPCActionListener();
	}
	
	private void igoActionListener() {
		igo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloa = (MyTableModel) table.getModel();
				try {
					modeloa.igo();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		}
	
	private void bilatuActionListener() {
		argazkiakBilatu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloa = (MyTableModel) table.getModel();
				try {
					if (table.getSelectedRow() == -1){
						int lastRow = table.convertRowIndexToView(modeloa.getRowCount() - 1);
						table.setRowSelectionInterval(lastRow, lastRow);
					}
					else{
						if(modeloa.bilatuArgazkia(table.getSelectedRow())){
							new MezuaBadago();
						}
						else new MezuaEzDago();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		}
	
	private void argazkiakPCActionListener() {
		argazkiakPC.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					ArgazkienTaula aT = null;
					SwingUtilities.getWindowAncestor(getContentPane()).dispose();
					try {
						table.setVisible(false);
						aT = new ArgazkienTaula();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					aT.setVisible(true);
			}});
		}
}

