import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;

public class OtelMusteriEkle extends JFrame {
	private JPanel contentPane;
	private JTextField jtxt_musteri_adres;
	private JTextField jtxt_musteri_adi;
	private JTextField jtxt_musteri_email;
	private JTable musteri_tablo_musteri;

	/**
	 * Launch the application.
	 */
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	private JTextField jtxt_otel_id;
	private JTextField jtxt_kullanici_adi;
	private JPasswordField jtxt_sifre;
	

	
	public void musteriListele(String otelid) {
		try {
			String otel_id = otelid;
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM customer WHERE hotel_id="+otel_id;
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)musteri_tablo_musteri.getModel();
			tm.setRowCount(0);
			while (rs.next()) {
				   
				    String o[] = {rs.getString("customer_id".toString()), rs.getString("hotel_id"),
				    		rs.getString("customer_name"),rs.getString("customer_email"), rs.getString("customer_adress"), 
				    		rs.getString("customer_username"), rs.getString("customer_password")};
				    
				    tm.addRow(o);
				    	
				    
				  
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtelMusteriEkle frame = new OtelMusteriEkle();
					OtelPanel panel = new OtelPanel();
					frame.setVisible(true);
					String otelid = frame.jtxt_otel_id.getText();
					frame.musteriListele(otelid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OtelMusteriEkle() {
		setTitle("Personel - M\u00FC\u015Fteri G\u00FCncelleme");
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
		panel_1.setBounds(10, 10, 190, 333);
		panel.add(panel_1);
		
		JButton btn_oda_ekle = new JButton("EKLE");
		btn_oda_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtelPanel panel = new OtelPanel();
				

				String otelid= jtxt_otel_id.getText();
				String musteriadi = jtxt_musteri_adi.getText();
				String musteriemaili = jtxt_musteri_email.getText();
				String  musteriadresi= jtxt_musteri_adres.getText();
				String musterikadi = jtxt_kullanici_adi.getText();
				String musterisifre = new String(jtxt_sifre.getPassword()).toString();
				
				fonk.musteriEkle(otelid, musteriadi, musteriemaili, musteriadresi, musterikadi, musterisifre);
				
				musteriListele(otelid);
		
			}
		});
		btn_oda_ekle.setBounds(98, 240, 85, 21);
		panel_1.add(btn_oda_ekle);
		
		jtxt_musteri_adres = new JTextField();
		jtxt_musteri_adres.setColumns(10);
		jtxt_musteri_adres.setBounds(87, 116, 96, 19);
		panel_1.add(jtxt_musteri_adres);
		
		JLabel jlbl_musteri_adres = new JLabel("Adres:");
		jlbl_musteri_adres.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_adres.setBounds(10, 119, 58, 13);
		panel_1.add(jlbl_musteri_adres);
		
		JButton btn_oda_duzenle = new JButton("D\u00DCZENLE");
		btn_oda_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//musteri listesini al
				int selectedrow = musteri_tablo_musteri.getSelectedRow();
				DefaultTableModel tmo = (DefaultTableModel)musteri_tablo_musteri.getModel();
				
				String musteriad = jtxt_musteri_adi.getText();
				String musteriemail = jtxt_musteri_email.getText();
				String musteriadres = jtxt_musteri_adres.getText();
				String musterikadi = jtxt_kullanici_adi.getText();
				String musterisifre = new String(jtxt_sifre.getPassword()).toString();
				
				OtelPanel panel = new OtelPanel();
				String otelid = jtxt_otel_id.getText();
				
				
				   if(selectedrow==-1){
			            if(tmo.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tmo.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			          
			        	fonk.musteriGuncelle(id, otelid, musteriad, musteriemail, musteriadres, musterikadi, musterisifre);
			        	
			            musteriListele(otelid);
			          
			            
			            JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			
				
				
			}
		});
		btn_oda_duzenle.setBounds(98, 271, 85, 21);
		panel_1.add(btn_oda_duzenle);
		
		JButton btn_oda_sil = new JButton("S\u0130L");
		btn_oda_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = musteri_tablo_musteri.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)musteri_tablo_musteri.getModel();
				
			      if(selectedrow==-1){
			            if(tm.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tm.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			            fonk.musteriSil(id);	
			       
			            musteriListele(jtxt_otel_id.getText());
			       
			            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			}
		});
		btn_oda_sil.setBounds(98, 302, 85, 21);
		panel_1.add(btn_oda_sil);
		
		JLabel jlbl_musteri_ad = new JLabel("Ad:");
		jlbl_musteri_ad.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_ad.setBounds(10, 67, 58, 13);
		panel_1.add(jlbl_musteri_ad);
		
		jtxt_musteri_adi = new JTextField();
		jtxt_musteri_adi.setColumns(10);
		jtxt_musteri_adi.setBounds(87, 64, 96, 19);
		panel_1.add(jtxt_musteri_adi);
		
		JLabel jlbl_musteri_email = new JLabel("Email:");
		jlbl_musteri_email.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_email.setBounds(10, 93, 58, 13);
		panel_1.add(jlbl_musteri_email);
		
		jtxt_musteri_email = new JTextField();
		jtxt_musteri_email.setColumns(10);
		jtxt_musteri_email.setBounds(87, 90, 96, 19);
		panel_1.add(jtxt_musteri_email);
		
		jtxt_otel_id = new JTextField();
		jtxt_otel_id.setEnabled(false);
		jtxt_otel_id.setEditable(false);
		jtxt_otel_id.setColumns(10);
		jtxt_otel_id.setBounds(87, 10, 96, 19);
		panel_1.add(jtxt_otel_id);
		
		JLabel jlbl_otel_id = new JLabel("Otel ID:");
		jlbl_otel_id.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_id.setBounds(10, 13, 58, 13);
		panel_1.add(jlbl_otel_id);
		
		JLabel jlbl_kullanici_adi = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_kullanici_adi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_kullanici_adi.setBounds(10, 148, 77, 13);
		panel_1.add(jlbl_kullanici_adi);
		
		jtxt_kullanici_adi = new JTextField();
		jtxt_kullanici_adi.setColumns(10);
		jtxt_kullanici_adi.setBounds(87, 145, 96, 19);
		panel_1.add(jtxt_kullanici_adi);
		
		jtxt_sifre = new JPasswordField();
		jtxt_sifre.setBounds(87, 174, 96, 19);
		panel_1.add(jtxt_sifre);
		
		JLabel jlbl_sifre = new JLabel("\u015Eifre:");
		jlbl_sifre.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_sifre.setBounds(10, 177, 58, 13);
		panel_1.add(jlbl_sifre);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(210, 10, 356, 333);
		panel.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 356, 333);
		panel_2_1.add(scrollPane_1);
		
		musteri_tablo_musteri = new JTable();
		musteri_tablo_musteri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = musteri_tablo_musteri.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)musteri_tablo_musteri.getModel();
				jtxt_musteri_adi.setText(tm.getValueAt(selectedrow, 2).toString());
				jtxt_musteri_email.setText(tm.getValueAt(selectedrow, 3).toString());
				jtxt_musteri_adres.setText(tm.getValueAt(selectedrow, 4).toString());
				jtxt_kullanici_adi.setText(tm.getValueAt(selectedrow, 5).toString());
				jtxt_sifre.setText(tm.getValueAt(selectedrow, 6).toString());
			}
		});
		musteri_tablo_musteri.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00FC\u015Fteri ID", "Otel ID", "Ad\u0131", "Email", "Adres", "Kullan\u0131c\u0131 Ad\u0131", "\u015Eifre"
			}
		));
		scrollPane_1.setViewportView(musteri_tablo_musteri);
	}

	public JTextField setJtxt_otel_id(String id) {
		jtxt_otel_id.setText(id);
		return jtxt_otel_id;
	}
}
