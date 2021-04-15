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

public class OtelOdaEkle extends JFrame {

	private JPanel contentPane;
	private JTextField jtxt_oda_fiyat;
	private JTextField jtxt_oda_no;
	private JTextField jtxt_oda_tipi;
	private JTable oda_tablo_oda;

	/**
	 * Launch the application.
	 */
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	private JTextField otel_id;
	

	
	public void odaListele(String otel_id) {
		try {
			String otelid = otel_id;
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM room WHERE hotel_id="+otelid;
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)oda_tablo_oda.getModel();
			tm.setRowCount(0);
		
			while (rs.next()) {
				   
				    String o[] = {rs.getString("room_id".toString()), rs.getString("hotel_id"),
				    		rs.getString("room_no"),rs.getString("room_type"), rs.getString("room_price")};
				    
				    	tm.addRow(o);
				    	
				
				   
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
					OtelPanel panel = new OtelPanel();
					OtelOdaEkle frame = new OtelOdaEkle();
					frame.setVisible(true);
					String id = frame.otel_id.getText();
					frame.odaListele(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OtelOdaEkle() {
		setTitle("Personel - Oda G\u00FCncelleme");
		OtelPanel otelpanel = new OtelPanel();
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
		panel_1.setBounds(10, 10, 190, 256);
		panel.add(panel_1);
		
		JButton btn_oda_ekle = new JButton("EKLE");
		btn_oda_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String otelid= otel_id.getText();
				String odano = jtxt_oda_no.getText();
				String odatip = jtxt_oda_tipi.getText();
				String odafiyat = jtxt_oda_fiyat.getText();
				
				
				fonk.odaEkle(otelid, odano, odatip, odafiyat);
				odaListele(otelid);
	
				
				
			}
		});
		btn_oda_ekle.setBounds(93, 145, 85, 21);
		panel_1.add(btn_oda_ekle);
		
		jtxt_oda_fiyat = new JTextField();
		jtxt_oda_fiyat.setColumns(10);
		jtxt_oda_fiyat.setBounds(87, 116, 96, 19);
		panel_1.add(jtxt_oda_fiyat);
		
		JLabel jlbl_otel_email = new JLabel("Oda Fiyat\u0131:");
		jlbl_otel_email.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_email.setBounds(10, 119, 58, 13);
		panel_1.add(jlbl_otel_email);
		
		JButton btn_oda_duzenle = new JButton("D\u00DCZENLE");
		btn_oda_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtelPanel otelpanel = new OtelPanel();
				
				//oda listesini al
				int selectedrow = oda_tablo_oda.getSelectedRow();
				DefaultTableModel tmo = (DefaultTableModel)oda_tablo_oda.getModel();
				
				String odano = jtxt_oda_no.getText();
				String odatip = jtxt_oda_tipi.getText();
				String odafiyat = jtxt_oda_fiyat.getText();
				String otelid = otel_id.getText();
				
				   if(selectedrow==-1){
			            if(tmo.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tmo.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			           
			        	fonk.odaGuncelle(id, otelid, odano, odatip, odafiyat);
			            odaListele(otelid);
			            
			            
			            
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
			            odaListele(tm.getValueAt(selectedrow, 1).toString());
			            
			
			            
			            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			}
		});
		btn_oda_sil.setBounds(93, 207, 85, 21);
		panel_1.add(btn_oda_sil);
		
		JLabel jlbl_oda_no = new JLabel("Oda No:");
		jlbl_oda_no.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_oda_no.setBounds(10, 67, 58, 13);
		panel_1.add(jlbl_oda_no);
		
		jtxt_oda_no = new JTextField();
		jtxt_oda_no.setColumns(10);
		jtxt_oda_no.setBounds(87, 64, 96, 19);
		panel_1.add(jtxt_oda_no);
		
		JLabel jlbl_oda_tipi = new JLabel("Oda Tipi:");
		jlbl_oda_tipi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_oda_tipi.setBounds(10, 93, 58, 13);
		panel_1.add(jlbl_oda_tipi);
		
		jtxt_oda_tipi = new JTextField();
		jtxt_oda_tipi.setColumns(10);
		jtxt_oda_tipi.setBounds(87, 90, 96, 19);
		panel_1.add(jtxt_oda_tipi);
		
		otel_id = new JTextField();
		otel_id.setEnabled(false);
		otel_id.setEditable(false);
		otel_id.setBounds(87, 10, 96, 19);
		panel_1.add(otel_id);
		otel_id.setColumns(10);
		
		JLabel jlbl_otel_id = new JLabel("Otel ID:");
		jlbl_otel_id.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_id.setBounds(10, 13, 58, 13);
		panel_1.add(jlbl_otel_id);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(210, 10, 356, 256);
		panel.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 356, 256);
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
	public JTextField setOtel_id(String id) {
		otel_id.setText(id);
		return otel_id;
	}
}
