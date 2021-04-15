import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.WindowFocusListener;

public class AdminPanel extends JFrame {

	private JPanel contentPane;
	private JTextField jtxt_musteri_email;
	private JTextField jtxt_musteri_adres;
	private JTextField jtxt_oda_fiyat;
	private JTextField jtxt_oda_tip;
	private JTextField jtxt_oda_no;
	private JTextField jtxt_toplam;
	private JTable admin_tablo_rezervasyon;
	private JTable admin_tablo_oda;
	private JTable admin_tablo_musteri;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
					frame.setVisible(true);
		
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	private JTextField jtxt_musteri_ad;
	private JTable yemek_tablo;
	private JTextField jtxt_yemek;
	private JTextField jtxt_yemek_ucret;
	private JTextField gizli_oda_id;
	private JTextField gizli_musteri_id;
	private JComboBox cb_otel_ad;
	
	public String kelimeKes(String kesilecek, int sira) {
			String kesSelectedItem[] = kesilecek.split("/");
			String idSelected = kesSelectedItem[sira];
			return idSelected;
		
	}
	
	
	//rezervasyon listeleme fonksiyonu
	public void rezervasyonListele(String selectedItem) {
		try {
			String idSelected = kelimeKes(selectedItem, 0);
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM reservation WHERE hotel_id="+idSelected;
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)admin_tablo_rezervasyon.getModel();
			tm.setRowCount(0);
		
			
			while (rs.next()) {
					
				    String o[] = {rs.getString("reservation_id"),rs.getString("customer_name"), rs.getString("customer_email"),
				    		rs.getString("customer_adress"),rs.getString("hotel_name"), rs.getString("date_in"),
				    		rs.getString("date_out"),rs.getString("room_no"),rs.getString("room_type"),rs.getString("room_price"),
				    				rs.getString("meal_selection"),rs.getString("meal_price"), rs.getString("total_price")};
				    
			
				    tm.addRow(o);
				    
			/*	    
			otelin id'sini oda tablosundaki hotel id kýsmýndan alýyordum burada. Sonrasýnda rezervasyon listesinden seçip
			düzenlemeye kalkýnca oda  tablosunda her hangi bir þey seçili olmadýðýnda otel id sini alamayýp hata veriyordu.
			onun yerine þimdi otel adý kýsmýnda yazan id den alýyor. Ýþler karýþmýyor. 

			if(otelid.equals(idSelected)){ 
					tm.addRow(o);
				    	
			} */
				   
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	//veritabanýndan müþterileri alýp tabloya aktaran fonksiyon
	public void musteriListele(String selectedItem) {
		try {
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM customer ";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)admin_tablo_musteri.getModel();
			tm.setRowCount(0);
			String idSelected = kelimeKes(selectedItem, 0);
		
			while (rs.next()) {
				   
				    String o[] = {rs.getString("customer_id".toString()), rs.getString("hotel_id"),
				    		rs.getString("customer_name"),rs.getString("customer_email"), rs.getString("customer_adress")};
				    
				    
				    
				    if(o[1].equals(idSelected)){
				    	tm.addRow(o);
				    	
				    }
				   
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	
	
	// veritabanýndan oda listesini alýp tabloya aktaran fonksiyon
	public void odaListele(String selectedItem) {
		try {
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM room ";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)admin_tablo_oda.getModel();
			tm.setRowCount(0);
			String idSelected = kelimeKes(selectedItem, 0);
			
			while (rs.next()) {
				   
				    String o[] = {rs.getString("room_id".toString()), rs.getString("hotel_id"),
				    		rs.getString("room_no"),rs.getString("room_type"), rs.getString("room_price")};
				    
				    
				    
				    if(o[1].equals(idSelected)){
				    	tm.addRow(o);
				    	
				    }
				   
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	//veritabanýndan yemek listesini alýr.
	
	public void yemekListele(String selectedItem) {
		try {
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM meal ";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)yemek_tablo.getModel();
			tm.setRowCount(0);
			String idSelected = kelimeKes(selectedItem, 0);
			
			while (rs.next()) {
				   
					String otel_id = rs.getString("hotel_id");
				    String o[] = {rs.getString("meal_id".toString()), rs.getString("meal_type"),
				    		rs.getString("meal_price")};
				    
				    
				    if(otel_id.equals(idSelected)){
				    	tm.addRow(o);
				  
				    	
				    }
				   
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public AdminPanel() {
		setTitle("Y\u00F6netici - Ana Sayfa");
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Image img = new ImageIcon(this.getClass().getResource("/Logo1.png")).getImage();
		this.setIconImage(img);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnOtelEkle = new JButton("Otel Ekle");
		btnOtelEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminOtelEkle otel = new AdminOtelEkle();
				otel.otelListele();
				otel.setVisible(true);
			}
			
		});
		btnOtelEkle.setBounds(0, 0, 117, 21);
		panel.add(btnOtelEkle);
		
		JButton btnOdaEkle = new JButton("Oda Ekle");
		btnOdaEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminOdaEkle oda = new AdminOdaEkle();
				oda.setVisible(true);
				oda.otelListele();
			}
		});
		btnOdaEkle.setBounds(116, 0, 117, 21);
		panel.add(btnOdaEkle);
		
		JButton btnMusteriEkle = new JButton("M\u00FC\u015Fteri Ekle");
		btnMusteriEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMusteriEkle musteri = new AdminMusteriEkle();
				musteri.setVisible(true);
				musteri.otelListele();
			}
		});
		btnMusteriEkle.setBounds(232, 0, 117, 21);
		panel.add(btnMusteriEkle);
		
		JButton btnPersonelEkle = new JButton("Personel Ekle");
		btnPersonelEkle.setPreferredSize(new Dimension(117, 21));
		btnPersonelEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPersonelEkle personel= new AdminPersonelEkle();
				personel.setVisible(true);
				personel.otelListele();
			}
		});
		btnPersonelEkle.setBounds(348, 0, 117, 21);
		panel.add(btnPersonelEkle);
		
		JButton btnCikis = new JButton("\u00C7IKI\u015E");
		btnCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Çýkmak Ýstediðinize Emin Misiniz?", "UYARI",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    setVisible(false);
				    AdminLoginPanel admin = new AdminLoginPanel();
				    admin.setVisible(true);
				} else {
				    
				}
				
				
			}
		});
		btnCikis.setBounds(691, 0, 85, 21);
		panel.add(btnCikis);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 31, 410, 312);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel jlbl_musteri_ad = new JLabel("Ad:");
		jlbl_musteri_ad.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_ad.setBounds(10, 71, 58, 13);
		panel_1.add(jlbl_musteri_ad);
		
		JLabel jlbl_otel_adi = new JLabel("Otel Ad\u0131:");
		jlbl_otel_adi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_adi.setBounds(10, 13, 58, 13);
		panel_1.add(jlbl_otel_adi);
		
		jtxt_musteri_email = new JTextField();
		jtxt_musteri_email.setEnabled(false);
		jtxt_musteri_email.setColumns(10);
		jtxt_musteri_email.setBounds(87, 94, 108, 19);
		panel_1.add(jtxt_musteri_email);
		
		JLabel jlbl_musteri_email = new JLabel("Email:");
		jlbl_musteri_email.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_email.setBounds(10, 97, 58, 13);
		panel_1.add(jlbl_musteri_email);
		
		JLabel jlbl_musteri_adres = new JLabel("Adres:");
		jlbl_musteri_adres.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_adres.setBounds(10, 123, 58, 13);
		panel_1.add(jlbl_musteri_adres);
		
		jtxt_musteri_adres = new JTextField();
		jtxt_musteri_adres.setEnabled(false);
		jtxt_musteri_adres.setColumns(10);
		jtxt_musteri_adres.setBounds(87, 120, 108, 19);
		panel_1.add(jtxt_musteri_adres);
		
		JLabel jlbl_cikis = new JLabel("\u00C7\u0131k\u0131\u015F Tarihi:");
		jlbl_cikis.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_cikis.setBounds(218, 39, 67, 13);
		panel_1.add(jlbl_cikis);
		
		JLabel jlbl_giris = new JLabel("Giri\u015F Tarihi:");
		jlbl_giris.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_giris.setBounds(218, 13, 67, 13);
		panel_1.add(jlbl_giris);
		
		JLabel jlbl_oda_no = new JLabel("Oda No:");
		jlbl_oda_no.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_oda_no.setBounds(218, 65, 67, 13);
		panel_1.add(jlbl_oda_no);
		
		JLabel jlbl_oda_tipi = new JLabel("Oda Tipi:");
		jlbl_oda_tipi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_oda_tipi.setBounds(218, 91, 67, 13);
		panel_1.add(jlbl_oda_tipi);
		
		JLabel jlbl_otel_email = new JLabel("Oda \u00DCcreti:");
		jlbl_otel_email.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_email.setBounds(218, 117, 67, 13);
		panel_1.add(jlbl_otel_email);
		
		jtxt_oda_fiyat = new JTextField();
		jtxt_oda_fiyat.setEnabled(false);
		jtxt_oda_fiyat.setColumns(10);
		jtxt_oda_fiyat.setBounds(295, 114, 96, 19);
		panel_1.add(jtxt_oda_fiyat);
		
		jtxt_oda_tip = new JTextField();
		jtxt_oda_tip.setEnabled(false);
		jtxt_oda_tip.setColumns(10);
		jtxt_oda_tip.setBounds(295, 88, 96, 19);
		panel_1.add(jtxt_oda_tip);
		
		jtxt_oda_no = new JTextField();
		jtxt_oda_no.setEnabled(false);
		jtxt_oda_no.setColumns(10);
		jtxt_oda_no.setBounds(295, 62, 96, 19);
		panel_1.add(jtxt_oda_no);
		
		JLabel jlbl_yemek = new JLabel("Yemek Tercihi:");
		jlbl_yemek.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_yemek.setBounds(10, 153, 96, 13);
		panel_1.add(jlbl_yemek);
		
		JLabel jlbl_toplam = new JLabel("Toplam:");
		jlbl_toplam.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_toplam.setBounds(218, 140, 67, 26);
		panel_1.add(jlbl_toplam);
		
		jtxt_toplam = new JTextField();
		jtxt_toplam.setColumns(10);
		jtxt_toplam.setBounds(295, 143, 96, 19);
		panel_1.add(jtxt_toplam);
		
	cb_otel_ad = new JComboBox();


		
		
		cb_otel_ad.addMouseMotionListener(new MouseMotionAdapter() {
			boolean isEnabled = true;
			@Override
			public void mouseMoved(MouseEvent e) {
				if (isEnabled) {
					try { 
			    		String sorgu = "SELECT hotel_name, hotel_id FROM hotel";
			    		VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
			    		fonk.pst = fonk.conn.prepareStatement(sorgu);
			    		ResultSet rs= fonk.pst.executeQuery();
			    		
			    	
			    		while (rs.next())
			    		{      
			    			String name = rs.getString("hotel_name");
			    			String id = rs.getString("hotel_id");
			    			String cblistesi = id+"/"+name;
			    			cb_otel_ad.addItem(cblistesi);
			    			
			    		}//end while
			    		isEnabled = false;
			    		
			    	
			    		
			    		} catch (Exception ex) {
			             
			    		}
					
					
				}
			
			}
		});
		cb_otel_ad.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				
			}
		});
		cb_otel_ad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tm = (DefaultTableModel)admin_tablo_musteri.getModel();
				tm.setRowCount(0);
				DefaultTableModel tmo = (DefaultTableModel)admin_tablo_oda.getModel();
				tmo.setRowCount(0);
				DefaultTableModel tmy = (DefaultTableModel)yemek_tablo.getModel();
				tmy.setRowCount(0);
				DefaultTableModel tmr = (DefaultTableModel)admin_tablo_rezervasyon.getModel();
				tmr.setRowCount(0);
			}
			
		});
			
		cb_otel_ad.setModel(new DefaultComboBoxModel(new String[] {"Otel Se\u00E7in"}));
		cb_otel_ad.setBounds(87, 10, 108, 19);
		panel_1.add(cb_otel_ad);
		
		JDateChooser date_giris = new JDateChooser();
		date_giris.setDateFormatString("dd/MM/yyyy");
		date_giris.setBounds(295, 7, 96, 19);
		panel_1.add(date_giris);
		
		JDateChooser date_cikis = new JDateChooser();
		date_cikis.setDateFormatString("dd/MM/yyyy");
		date_cikis.setBounds(295, 33, 96, 19);
		panel_1.add(date_cikis);
		
	
		
		
		
		JButton btn_reservasyon_ekle = new JButton("EKLE");
		btn_reservasyon_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			/*		int musteriSecim = admin_tablo_musteri.getSelectedRow();
					DefaultTableModel tm = (DefaultTableModel)admin_tablo_musteri.getModel();
					
					int odaSecim = admin_tablo_oda.getSelectedRow();
					DefaultTableModel tmo = (DefaultTableModel)admin_tablo_oda.getModel();
					
*/
					
					String ham_id = cb_otel_ad.getSelectedItem().toString();
												
					
					//	String musteri_id = tm.getValueAt(musteriSecim, 0).toString();
					
						String musteri_id = gizli_musteri_id.getText();
						String musteriadi = jtxt_musteri_ad.getText();
						String musteriemaili = jtxt_musteri_email.getText();
						String  musteriadresi= jtxt_musteri_adres.getText();
						String otel_id =kelimeKes(ham_id, 0);
						String otel_ad =kelimeKes(ham_id,1);
						
					//  String giris = date_giris.getDate().toGMTString();
					//	String cikis =date_cikis.getDate().toGMTString();
						
					//****** Þu tarih formatý çok uðraþtýrdý. Burada kullanýcýnýn girdiði tarihleri aþaðýdaki formatta string e çevirip veritabanýna ekliyor.
					//****** ayný þekilde date_giris ve date_giris alanlarýnýn da formatýný bu þekilde belirttim.
					//****** ayrýca rezervasyon tablosuna týklandýðýnda da bu formata çevirip date alanlarýna koyuyor. 
						
						
						SimpleDateFormat sdfgiris = new SimpleDateFormat("dd/MM/yyyy");
						String giris = sdfgiris.format(date_giris.getDate()).toString();
						String cikis = sdfgiris.format(date_cikis.getDate()).toString();

					//		String oda_id =tmo.getValueAt(odaSecim, 0).toString();
						
						String oda_id = gizli_oda_id.getText();
						String odano =jtxt_oda_no.getText();
						String odatip= jtxt_oda_tip.getText();
						String odafiyat = jtxt_oda_fiyat.getText();
						String yemek = jtxt_yemek.getText();
						String yemekucret = jtxt_yemek_ucret.getText();
						String toplam = jtxt_toplam.getText();
						
						
						fonk.rezervasyonEkle(musteri_id, musteriadi, musteriemaili, musteriadresi, otel_id, otel_ad, giris,cikis,oda_id,  odano, odatip, odafiyat, yemek, yemekucret, toplam);
						
						String selectedrow = cb_otel_ad.getSelectedItem().toString();
						rezervasyonListele(selectedrow);
						
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Hatalý Giriþ"+ex, "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
				}
			
				
					
				
				}
				
			}
		);
		btn_reservasyon_ekle.setBounds(10, 204, 85, 21);
		panel_1.add(btn_reservasyon_ekle);
		
		JButton btn_rezervasyon_duzenle = new JButton("D\u00DCZENLE");
		btn_rezervasyon_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ham_id = cb_otel_ad.getSelectedItem().toString();
				String musteri_id = gizli_musteri_id.getText();
				String musteriadi = jtxt_musteri_ad.getText();
				String musteriemaili = jtxt_musteri_email.getText();
				String  musteriadresi= jtxt_musteri_adres.getText();
				String otel_id =kelimeKes(ham_id, 0);
				String otel_ad =kelimeKes(ham_id,1);
				
				SimpleDateFormat sdfgiris = new SimpleDateFormat("dd/MM/yyyy");
				
				String giris = sdfgiris.format(date_giris.getDate()).toString();
				String cikis = sdfgiris.format(date_cikis.getDate()).toString();
				
				String oda_id = gizli_oda_id.getText();
				String odano =jtxt_oda_no.getText();
				String odatip= jtxt_oda_tip.getText();
				String odafiyat = jtxt_oda_fiyat.getText();
				String yemek = jtxt_yemek.getText();
				String yemekucret = jtxt_yemek_ucret.getText();
				String toplam = jtxt_toplam.getText();
				
				int selectedRow = admin_tablo_rezervasyon.getSelectedRow();
				DefaultTableModel tmo = (DefaultTableModel)admin_tablo_rezervasyon.getModel();
				if(selectedRow==-1){
			            if(tmo.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	int resid = Integer.parseInt(tmo.getValueAt(selectedRow, 0).toString());
			        	
			        	fonk.rezervasyonDuzenle(resid,musteri_id, musteriadi, musteriemaili, musteriadresi, otel_id, otel_ad, giris,cikis,oda_id,  odano, odatip, odafiyat, yemek, yemekucret, toplam);

			        	JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        	
			        	String selectedrow = cb_otel_ad.getSelectedItem().toString();
			        	rezervasyonListele(selectedrow);
			        
			        }
				
				
			}
		});
		btn_rezervasyon_duzenle.setBounds(10, 235, 85, 21);
		panel_1.add(btn_rezervasyon_duzenle);
		
		JButton btn_rezervasyon_sil = new JButton("S\u0130L");
		btn_rezervasyon_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = admin_tablo_rezervasyon.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)admin_tablo_rezervasyon.getModel();
				
			      if(selectedrow==-1){
			            if(tm.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int resid = Integer.parseInt(tm.getValueAt(selectedrow, 0).toString());
			            fonk.rezervasyonSil(resid);	
			           
			            String seciliotel = cb_otel_ad.getSelectedItem().toString();
			            rezervasyonListele(seciliotel);
			            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
			}
		});
		btn_rezervasyon_sil.setBounds(10, 266, 85, 21);
		panel_1.add(btn_rezervasyon_sil);
		
	

		
		
		
		
		JButton btn_toplam = new JButton("TOPLAM");
		btn_toplam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String syemek = jtxt_yemek_ucret.getText();
				String soda = jtxt_oda_fiyat.getText();
				if(syemek.isEmpty()) {
					Float foda = Float.valueOf(soda);
					jtxt_toplam.setText(foda.toString());
				}else {
					Float fyemek = Float.valueOf(syemek);
					Float foda = Float.valueOf(soda);
					Float ftoplam = fyemek + foda;
					jtxt_toplam.setText((ftoplam).toString());
				}
			}
		});
		btn_toplam.setBounds(306, 172, 85, 21);
		panel_1.add(btn_toplam);
		
		JButton btn_listele = new JButton("L\u0130STELE");
		btn_listele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(cb_otel_ad.getSelectedIndex()!=0) {
				String selectedrow = cb_otel_ad.getSelectedItem().toString();
				odaListele(selectedrow);
				musteriListele(selectedrow);
				yemekListele(selectedrow);
				rezervasyonListele(selectedrow);				
			}else {
				 JOptionPane.showMessageDialog(null, "Otel Seçimi Yapýnýz", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			}
				
				
			}
		});
		
		btn_listele.setBounds(110, 37, 85, 21);
		panel_1.add(btn_listele);
		
		

		
		jtxt_musteri_ad = new JTextField();
		jtxt_musteri_ad.setEnabled(false);
		jtxt_musteri_ad.setColumns(10);
		jtxt_musteri_ad.setBounds(87, 68, 108, 19);
		panel_1.add(jtxt_musteri_ad);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(193, 204, 198, 98);
		panel_1.add(scrollPane_3);
		
		yemek_tablo = new JTable();
		yemek_tablo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = yemek_tablo.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)yemek_tablo.getModel();
				jtxt_yemek.enable(true);
				jtxt_yemek_ucret.enable(true);
				jtxt_yemek.setText(tm.getValueAt(selectedrow, 1).toString());
				jtxt_yemek_ucret.setText(tm.getValueAt(selectedrow, 2).toString());
				
				
				
			}
		});
		yemek_tablo.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		yemek_tablo.setForeground(Color.BLACK);
		yemek_tablo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Yemek ID", "Yemek Tipi", "Yemek Fiyat\u0131"
			}
		));
		scrollPane_3.setViewportView(yemek_tablo);
		
		jtxt_yemek = new JTextField();
		jtxt_yemek.setEnabled(false);
		jtxt_yemek.setColumns(10);
		jtxt_yemek.setBounds(110, 149, 85, 19);
		panel_1.add(jtxt_yemek);
		
		jtxt_yemek_ucret = new JTextField();
		jtxt_yemek_ucret.setEnabled(false);
		jtxt_yemek_ucret.setColumns(10);
		jtxt_yemek_ucret.setBounds(110, 175, 85, 19);
		panel_1.add(jtxt_yemek_ucret);
		
		JLabel jlbl_yemek_ucret = new JLabel("Yemek \u00DCcreti:");
		jlbl_yemek_ucret.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_yemek_ucret.setBounds(10, 179, 96, 13);
		panel_1.add(jlbl_yemek_ucret);
		
		gizli_oda_id = new JTextField();
		gizli_oda_id.setEnabled(false);
		gizli_oda_id.setEditable(false);
		gizli_oda_id.setVisible(false);

		gizli_oda_id.setBounds(176, 205, 7, 19);
		panel_1.add(gizli_oda_id);
		gizli_oda_id.setColumns(10);
		
		gizli_musteri_id = new JTextField();
		gizli_musteri_id.setEnabled(false);
		gizli_musteri_id.setEditable(false);
		gizli_musteri_id.setVisible(false);
		gizli_musteri_id.setColumns(10);
		gizli_musteri_id.setBounds(176, 236, 7, 19);
		panel_1.add(gizli_musteri_id);
		
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(429, 31, 337, 148);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 10, 337, 138);
		panel_2.add(scrollPane_1);
		
		admin_tablo_oda = new JTable();
		admin_tablo_oda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = admin_tablo_oda.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)admin_tablo_oda.getModel();
				jtxt_oda_no.enable(true);
				jtxt_oda_tip.enable(true);
				jtxt_oda_fiyat.enable(true);
				jtxt_oda_no.setText(tm.getValueAt(selectedrow, 2).toString());
				jtxt_oda_tip.setText(tm.getValueAt(selectedrow, 3).toString());
				jtxt_oda_fiyat.setText(tm.getValueAt(selectedrow, 4).toString()); 
				gizli_oda_id.setText(tm.getValueAt(selectedrow, 0).toString());
				
				
			}
		});
		admin_tablo_oda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Oda ID", "Otel ID", "Oda No", "Oda Tipi", "Oda Fiyat\u0131"
			}
		));
		scrollPane_1.setViewportView(admin_tablo_oda);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(0, 353, 766, 190);
		panel.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 766, 190);
		panel_2_1.add(scrollPane);
		
		admin_tablo_rezervasyon = new JTable();
		admin_tablo_rezervasyon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = admin_tablo_rezervasyon.getSelectedRow();
				DefaultTableModel tmr = (DefaultTableModel)admin_tablo_rezervasyon.getModel();
				
			try {
					String dateValue1 = tmr.getValueAt(selectedrow,5).toString(); // What ever column
					java.util.Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateValue1);
					date_giris.setDate(date1);
				
					String dateValue2 = tmr.getValueAt(selectedrow,6).toString(); // What ever column
					java.util.Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateValue2);
					date_cikis.setDate(date2);
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	

				jtxt_musteri_ad.enable(true);
				jtxt_musteri_email.enable(true);
				jtxt_musteri_adres.enable(true);
				jtxt_oda_no.enable(true);
				jtxt_oda_tip.enable(true);
				jtxt_oda_fiyat.enable(true);
				jtxt_yemek.enable(true);
				jtxt_yemek_ucret.enable(true);
		
				jtxt_musteri_ad.setText(tmr.getValueAt(selectedrow, 1).toString());
				jtxt_musteri_email.setText(tmr.getValueAt(selectedrow, 2).toString());
				jtxt_musteri_adres.setText(tmr.getValueAt(selectedrow, 3).toString());
	
				jtxt_oda_no.setText(tmr.getValueAt(selectedrow, 7).toString());
				jtxt_oda_tip.setText(tmr.getValueAt(selectedrow, 8).toString());
				jtxt_oda_fiyat.setText(tmr.getValueAt(selectedrow, 9).toString());
				jtxt_yemek.setText(tmr.getValueAt(selectedrow, 10).toString());
				jtxt_yemek_ucret.setText(tmr.getValueAt(selectedrow, 11).toString());
				jtxt_toplam.setText(tmr.getValueAt(selectedrow, 12).toString());
				
				
				String reservid = tmr.getValueAt(selectedrow, 0).toString();
				try {
					Statement st = fonk.conn.createStatement();
					String query = "SELECT customer_id, room_id FROM reservation WHERE reservation_id="+reservid;
					ResultSet rs = st.executeQuery(query);
					
					while (rs.next()) {
						String c = rs.getString("customer_id");
						String r = rs.getString("room_id");
						gizli_musteri_id.setText(c);
						gizli_oda_id.setText(r);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		admin_tablo_rezervasyon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Rezervasyon ID", "Ad", "Email", "Adres", "Otel Ad\u0131", "Giri\u015F Tarihi", "\u00C7\u0131k\u0131\u015F Tarihi", "Oda No", "Oda Tipi", "Oda Fiyat\u0131", "Yemek Tercihi", "Yemek Fiyat\u0131", "Toplam"
			}
		));
		scrollPane.setViewportView(admin_tablo_rezervasyon);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(430, 189, 336, 154);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 336, 144);
		panel_3.add(scrollPane_2);
		
		admin_tablo_musteri = new JTable();
		admin_tablo_musteri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = admin_tablo_musteri.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)admin_tablo_musteri.getModel();
				
				jtxt_musteri_ad.enable(true);
				jtxt_musteri_email.enable(true);
				jtxt_musteri_adres.enable(true);
				gizli_musteri_id.setText(tm.getValueAt(selectedrow, 0).toString());
				jtxt_musteri_ad.setText(tm.getValueAt(selectedrow, 2).toString());
				jtxt_musteri_email.setText(tm.getValueAt(selectedrow, 3).toString());
				jtxt_musteri_adres.setText(tm.getValueAt(selectedrow, 4).toString());
			}
		});
		admin_tablo_musteri.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00FC\u015Fteri ID", "Otel ID", "Ad\u0131", "Email", "Adres"
			}
		));
		scrollPane_2.setViewportView(admin_tablo_musteri);
		
		JButton btnYemekEkle = new JButton("Yemek Tercihi Ekle");
		btnYemekEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminYemekEkle frame = new AdminYemekEkle();
				frame.otelListele();
				frame.setVisible(true);
			
			}
		});
		btnYemekEkle.setBounds(465, 0, 160, 21);
		panel.add(btnYemekEkle);
	}
	public void setCb_otel_ad() {
		try { 
    		String sorgu = "SELECT hotel_name, hotel_id FROM hotel";
    		VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
    		fonk.pst = fonk.conn.prepareStatement(sorgu);
    		ResultSet rs= fonk.pst.executeQuery();
    		cb_otel_ad.removeAllItems();
    		cb_otel_ad.addItem("Otel sSeçin");
    		while (rs.next())
    		{      
    			String name = rs.getString("hotel_name");
    			String id = rs.getString("hotel_id");
    			String cblistesi = id+"/"+name;
    			
    			cb_otel_ad.addItem(cblistesi);
    			
    		}//end while
    		
    	
    		
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
		
	}
}
