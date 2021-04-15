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

public class AdminMusteriEkle extends JFrame {
	private JPanel contentPane;
	private JTextField jtxt_otel_adi;
	private JTextField jtxt_musteri_adres;
	private JTable otel_tablo_musteri;
	private JTextField jtxt_musteri_adi;
	private JTextField jtxt_musteri_email;
	private JTable musteri_tablo_musteri;

	/**
	 * Launch the application.
	 */
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	private JTextField jtxt_kullanici_adi;
	private JPasswordField jtxt_sifre;
	
	//otelleri tabloya çekme fonksiyonu
	public void otelListele(){
		
		try {
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM hotel";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)otel_tablo_musteri.getModel();
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
	
	//musterileri tabloya çekme fonksiyonu 
	public void musteriListele() {
		try {
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM customer ";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)musteri_tablo_musteri.getModel();
			tm.setRowCount(0);
			int selectedrow= otel_tablo_musteri.getSelectedRow();	
			DefaultTableModel tmo = (DefaultTableModel)otel_tablo_musteri.getModel();
			while (rs.next()) {
				   
				    String o[] = {rs.getString("customer_id".toString()), rs.getString("hotel_id"),
				    		rs.getString("customer_name"),rs.getString("customer_email"), rs.getString("customer_adress")
				    		,rs.getString("customer_username"), rs.getString("customer_password")};
				    
				    
				    
				    if(o[1].equals(tmo.getValueAt(selectedrow, 0).toString())){
				    	tm.addRow(o);
				    	
				    }
				   
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
					AdminMusteriEkle frame = new AdminMusteriEkle();
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
	public AdminMusteriEkle() {
		setTitle("Y\u00F6netici - M\u00FC\u015Fteri G\u00FCncelleme");
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
		
		JButton btn_musteri_ekle = new JButton("EKLE");
		
		//musteri ekleme tuþuna týklandýðýnda müþteri ekleme iþlemini yapýlmýþtýr. Ayný zamanda tablo güncellenmesi amacýyla musteriListele() fonksiyonu çaðýrýlmýþtýr.
		btn_musteri_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow= otel_tablo_musteri.getSelectedRow();	
				DefaultTableModel tmo = (DefaultTableModel)otel_tablo_musteri.getModel();
				String otelid= tmo.getValueAt(selectedrow, 0).toString();
				String musteriadi = jtxt_musteri_adi.getText();
				String musteriemaili = jtxt_musteri_email.getText();
				String  musteriadresi= jtxt_musteri_adres.getText();
				String musterikadi = jtxt_kullanici_adi.getText();
				String musterisifre = new String(jtxt_sifre.getPassword());
				
				fonk.musteriEkle(otelid, musteriadi, musteriemaili, musteriadresi, musterikadi, musterisifre);
				
				musteriListele();
				
			}
		});
		btn_musteri_ekle.setBounds(93, 200, 85, 21);
		panel_1.add(btn_musteri_ekle);
		
		jtxt_musteri_adres = new JTextField();
		jtxt_musteri_adres.setColumns(10);
		jtxt_musteri_adres.setBounds(82, 86, 96, 19);
		panel_1.add(jtxt_musteri_adres);
		
		JLabel jlbl_musteri_adres = new JLabel("Adres:");
		jlbl_musteri_adres.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_adres.setBounds(5, 89, 58, 13);
		panel_1.add(jlbl_musteri_adres);
		
		JButton btn_musteri_duzenle = new JButton("D\u00DCZENLE");
		
		
		btn_musteri_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//oda listesini al
				int selectedrow = musteri_tablo_musteri.getSelectedRow();
				DefaultTableModel tmo = (DefaultTableModel)musteri_tablo_musteri.getModel();
				
				String musteriad = jtxt_musteri_adi.getText();
				String musteriemail = jtxt_musteri_email.getText();
				String musteriadres = jtxt_musteri_adres.getText();
				String musterikadi = jtxt_kullanici_adi.getText();
				String musterisifre = new String(jtxt_sifre.getPassword()).toString();
	
				
				   if(selectedrow==-1){
			            if(tmo.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tmo.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			            String otelid = tmo.getValueAt(selectedrow, 1).toString();
			        	fonk.musteriGuncelle(id, otelid, musteriad, musteriemail, musteriadres, musterikadi, musterisifre);
			            musteriListele();
			            JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			
				
				
			}
		});
		btn_musteri_duzenle.setBounds(93, 231, 85, 21);
		panel_1.add(btn_musteri_duzenle);
		
		JButton btn_musteri_sil = new JButton("S\u0130L");
		btn_musteri_sil.addActionListener(new ActionListener() {
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
			            musteriListele();
			            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			}
		});
		btn_musteri_sil.setBounds(93, 262, 85, 21);
		panel_1.add(btn_musteri_sil);
		
		JLabel jlbl_musteri_ad = new JLabel("Ad:");
		jlbl_musteri_ad.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_ad.setBounds(5, 37, 58, 13);
		panel_1.add(jlbl_musteri_ad);
		
		jtxt_musteri_adi = new JTextField();
		jtxt_musteri_adi.setColumns(10);
		jtxt_musteri_adi.setBounds(82, 34, 96, 19);
		panel_1.add(jtxt_musteri_adi);
		
		JLabel jlbl_musteri_email = new JLabel("Email:");
		jlbl_musteri_email.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_email.setBounds(5, 63, 58, 13);
		panel_1.add(jlbl_musteri_email);
		
		jtxt_musteri_email = new JTextField();
		jtxt_musteri_email.setColumns(10);
		jtxt_musteri_email.setBounds(82, 60, 96, 19);
		panel_1.add(jtxt_musteri_email);
		
		jtxt_kullanici_adi = new JTextField();
		jtxt_kullanici_adi.setColumns(10);
		jtxt_kullanici_adi.setBounds(82, 115, 96, 19);
		panel_1.add(jtxt_kullanici_adi);
		
		JLabel jlbl_kullanici_adi = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_kullanici_adi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_kullanici_adi.setBounds(5, 118, 77, 13);
		panel_1.add(jlbl_kullanici_adi);
		
		JLabel jlbl_sifre = new JLabel("\u015Eifre:");
		jlbl_sifre.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_sifre.setBounds(5, 147, 58, 13);
		panel_1.add(jlbl_sifre);
		
		jtxt_sifre = new JPasswordField();
		jtxt_sifre.setBounds(82, 144, 96, 19);
		panel_1.add(jtxt_sifre);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(210, 10, 356, 163);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
	
			}
		});
		scrollPane.setBounds(0, 0, 356, 163);
		panel_2.add(scrollPane);
		
		otel_tablo_musteri = new JTable();
		otel_tablo_musteri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedrow= otel_tablo_musteri.getSelectedRow();	
				DefaultTableModel tm = (DefaultTableModel)otel_tablo_musteri.getModel();
				jtxt_otel_adi.setText(tm.getValueAt(selectedrow,1 ).toString());
				musteriListele();
				
			}
		});
		otel_tablo_musteri.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Otel ID", "Otel Ad\u0131", "Adresi", "Telefon No", "Email"
			}
		));
		scrollPane.setViewportView(otel_tablo_musteri);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(210, 180, 356, 163);
		panel.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 356, 163);
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

}
