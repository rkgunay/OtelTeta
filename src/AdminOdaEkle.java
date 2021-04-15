import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.lang.System.Logger;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminOdaEkle extends JFrame {

	private JPanel contentPane;
	private JTextField jtxt_otel_adi;
	private JTextField jtxt_oda_fiyat;
	private JTable otel_tablo_oda;
	private JTextField jtxt_oda_no;
	private JTextField jtxt_oda_tipi;
	private JTable oda_tablo_oda;

	/**
	 * Launch the application.
	 */
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	
	public void otelListele(){
		
		try {
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM hotel";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)otel_tablo_oda.getModel();
			tm.setRowCount(0);
			

			while (rs.next()) {
			   //System.out.println(rs.getString(1));
			    String o[] = {rs.getString("hotel_id".toString()), rs.getString("hotel_name"),
			    		rs.getString("hotel_adress"),rs.getString("hotel_contact_no"), rs.getString("hotel_email")};
			    tm.addRow(o);
			   
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void odaListele() {
		try {
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM room ";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)oda_tablo_oda.getModel();
			tm.setRowCount(0);
			int selectedrow= otel_tablo_oda.getSelectedRow();	
			DefaultTableModel tmo = (DefaultTableModel)otel_tablo_oda.getModel();
			while (rs.next()) {
				   
				    String o[] = {rs.getString("room_id".toString()), rs.getString("hotel_id"),
				    		rs.getString("room_no"),rs.getString("room_type"), rs.getString("room_price")};
				    
				    
				    
				    if(o[1].equals(tmo.getValueAt(selectedrow, 0).toString())){
				    	tm.addRow(o);
				    	
				    }
				   
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminOdaEkle frame = new AdminOdaEkle();
					frame.setVisible(true);
					frame.otelListele();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminOdaEkle() {
		setTitle("Y\u00F6netici - Oda G\u00FCncelleme");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Image img = new ImageIcon(this.getClass().getResource("/Logo1.png")).getImage();
		this.setIconImage(img);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 10, 190, 235);
		panel.add(panel_1);
		
		JLabel jlbl_otel_adi = new JLabel("Otel Ad\u0131:");
		jlbl_otel_adi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_adi.setBounds(5, 11, 58, 13);
		panel_1.add(jlbl_otel_adi);
		
		jtxt_otel_adi = new JTextField();
		jtxt_otel_adi.setSelectionColor(SystemColor.textHighlight);
		jtxt_otel_adi.setEnabled(false);
		jtxt_otel_adi.setColumns(10);
		jtxt_otel_adi.setBounds(82, 8, 96, 19);
		panel_1.add(jtxt_otel_adi);
		
		JButton btn_oda_ekle = new JButton("EKLE");
		btn_oda_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow= otel_tablo_oda.getSelectedRow();	
				DefaultTableModel tmo = (DefaultTableModel)otel_tablo_oda.getModel();
				String otelid= tmo.getValueAt(selectedrow, 0).toString();
				String odano = jtxt_oda_no.getText();
				String odatip = jtxt_oda_tipi.getText();
				String odafiyat = jtxt_oda_fiyat.getText();
				
				
				fonk.odaEkle(otelid, odano, odatip, odafiyat);
				
				odaListele();
				
			}
		});
		btn_oda_ekle.setBounds(93, 145, 85, 21);
		panel_1.add(btn_oda_ekle);
		
		jtxt_oda_fiyat = new JTextField();
		jtxt_oda_fiyat.setColumns(10);
		jtxt_oda_fiyat.setBounds(82, 86, 96, 19);
		panel_1.add(jtxt_oda_fiyat);
		
		JLabel jlbl_otel_email = new JLabel("Oda Fiyat\u0131:");
		jlbl_otel_email.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_email.setBounds(5, 89, 58, 13);
		panel_1.add(jlbl_otel_email);
		
		JButton btn_oda_duzenle = new JButton("D\u00DCZENLE");
		btn_oda_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//oda listesini al
				int selectedrow = oda_tablo_oda.getSelectedRow();
				DefaultTableModel tmo = (DefaultTableModel)oda_tablo_oda.getModel();
				
				String odano = jtxt_oda_no.getText();
				String odatip = jtxt_oda_tipi.getText();
				String odafiyat = jtxt_oda_fiyat.getText();
				
				
				   if(selectedrow==-1){
			            if(tmo.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tmo.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			            String otelid = tmo.getValueAt(selectedrow, 1).toString();
			        	fonk.odaGuncelle(id, otelid, odano, odatip, odafiyat);
			            odaListele();
			            JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			
				
				
			}
		});
		btn_oda_duzenle.setBounds(93, 176, 85, 21);
		panel_1.add(btn_oda_duzenle);
		
		JButton btn_oda_sil = new JButton("S\u0130L");
		btn_oda_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = oda_tablo_oda.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)oda_tablo_oda.getModel();
				
			      if(selectedrow==-1){
			            if(tm.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tm.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			            fonk.odaSil(id);	
			            odaListele();
			            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			}
		});
		btn_oda_sil.setBounds(93, 207, 85, 21);
		panel_1.add(btn_oda_sil);
		
		JLabel jlbl_oda_no = new JLabel("Oda No:");
		jlbl_oda_no.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_oda_no.setBounds(5, 37, 58, 13);
		panel_1.add(jlbl_oda_no);
		
		jtxt_oda_no = new JTextField();
		jtxt_oda_no.setColumns(10);
		jtxt_oda_no.setBounds(82, 34, 96, 19);
		panel_1.add(jtxt_oda_no);
		
		JLabel jlbl_oda_tipi = new JLabel("Oda Tipi:");
		jlbl_oda_tipi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_oda_tipi.setBounds(5, 63, 58, 13);
		panel_1.add(jlbl_oda_tipi);
		
		jtxt_oda_tipi = new JTextField();
		jtxt_oda_tipi.setColumns(10);
		jtxt_oda_tipi.setBounds(82, 60, 96, 19);
		panel_1.add(jtxt_oda_tipi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(210, 10, 356, 123);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
	
			}
		});
		scrollPane.setBounds(0, 0, 356, 123);
		panel_2.add(scrollPane);
		
		otel_tablo_oda = new JTable();
		otel_tablo_oda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedrow= otel_tablo_oda.getSelectedRow();	
				DefaultTableModel tm = (DefaultTableModel)otel_tablo_oda.getModel();
				jtxt_otel_adi.setText(tm.getValueAt(selectedrow,1 ).toString());
				odaListele();
				
			}
		});
		otel_tablo_oda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Otel ID", "Otel Ad\u0131", "Adresi", "Telefon No", "Email"
			}
		));
		scrollPane.setViewportView(otel_tablo_oda);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(210, 143, 356, 123);
		panel.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 356, 123);
		panel_2_1.add(scrollPane_1);
		
		oda_tablo_oda = new JTable();
		oda_tablo_oda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = oda_tablo_oda.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)oda_tablo_oda.getModel();
				jtxt_oda_no.setText(tm.getValueAt(selectedrow, 2).toString());
				jtxt_oda_tipi.setText(tm.getValueAt(selectedrow, 3).toString());
				jtxt_oda_fiyat.setText(tm.getValueAt(selectedrow, 4).toString());
				
			}
		});
		oda_tablo_oda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Oda ID", "Otel ID", "Oda No", "Oda Tipi", "Oda Fiyat\u0131"
			}
		));
		scrollPane_1.setViewportView(oda_tablo_oda);
	}
}
