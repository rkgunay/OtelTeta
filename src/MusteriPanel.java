import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;  
public class MusteriPanel {  
JFrame f;  
private JTextField jtxt_otel_email;
private JTextField jtxt_otel_tel;
private JTextField jtxt_otel_adres;
private JTextField jtxt_otel_ad;
private JTable otel_tablo;
private JTextField jtxt_oda_no;
private JTextField jtxt_oda_tip;
private JTextField jtxt_oda_fiyat;
private JTable oda_tablo;
private JTextField jtxt_ad;
private JTextField jtxt_email;
private JTextField jtxt_yemek_tip;
private JTextField jtxt_yemek_fiyat;
private JTable yemek_tablo;
private JTextField jtxt_toplam;
private JTable rezervasyon_tablo;
private JTextField jtxt_adres;

VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
private JTextField jtxt_musteri_id;
private JTextField gizli_otel_id;
private JTextField gizli_oda_id;

public void rezervasyonListele(String id) {
	try {
		Statement st = fonk.conn.createStatement();
		String query = "SELECT * FROM reservation WHERE customer_id='"+id+"'";
		ResultSet rs = st.executeQuery(query);
		DefaultTableModel tm = (DefaultTableModel)rezervasyon_tablo.getModel();
		tm.setRowCount(0);
	
		
		while (rs.next()) {
				
			    String o[] = {rs.getString("reservation_id"),rs.getString("customer_name"), rs.getString("customer_email"),
			    		rs.getString("customer_adress"),rs.getString("hotel_name"), rs.getString("date_in"),
			    		rs.getString("date_out"),rs.getString("room_no"),rs.getString("room_type"),rs.getString("room_price"),
			    				rs.getString("meal_selection"),rs.getString("meal_price"), rs.getString("total_price")};
			    
		
			    tm.addRow(o);
			   
			}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}



public void otelListele() {
	try { 
		String sorgu = "SELECT * FROM hotel";
		fonk.pst = fonk.conn.prepareStatement(sorgu);
		ResultSet rs= fonk.pst.executeQuery();
		DefaultTableModel tm = (DefaultTableModel)otel_tablo.getModel();
		tm.setRowCount(0);
		while (rs.next())
		{      
			
			 String o[] = {rs.getString("hotel_id".toString()), rs.getString("hotel_name"),
			    		rs.getString("hotel_adress"), rs.getString("hotel_contact_no"), rs.getString("hotel_email")};
				tm.addRow(o);
		}

		
	
		
		} catch (Exception ex) {
         ex.printStackTrace();
		}
}

public void odaListele() {
	try {
		Statement st = fonk.conn.createStatement();
		String query = "SELECT * FROM room ";
		ResultSet rs = st.executeQuery(query);
		DefaultTableModel tm = (DefaultTableModel)oda_tablo.getModel();
		tm.setRowCount(0);
		int selectedrow= otel_tablo.getSelectedRow();	
		DefaultTableModel tmo = (DefaultTableModel)otel_tablo.getModel();
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

public void yemekListele() {
	try {
		Statement st = fonk.conn.createStatement();
		String query = "SELECT * FROM meal ";
		ResultSet rs = st.executeQuery(query);
		DefaultTableModel tm = (DefaultTableModel)yemek_tablo.getModel();
		tm.setRowCount(0);
		int selectedrow= otel_tablo.getSelectedRow();
		
		DefaultTableModel tmo = (DefaultTableModel)otel_tablo.getModel();
		while (rs.next()) {
			   String hotelid = rs.getString("hotel_id");
			    String o[] = {rs.getString("meal_id".toString()),
			    		rs.getString("meal_type"),rs.getString("meal_price")};
			    
			    
			    
			    if(hotelid.equals(tmo.getValueAt(selectedrow, 0).toString())){
			    	tm.addRow(o);
			    	
			    }
			   
			}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}


MusteriPanel(){  
	
    f=new JFrame();  
    f.setTitle("M\u00FC\u015Fteri - Ana Sayfa");
    
    Image img = new ImageIcon(this.getClass().getResource("/Logo1.png")).getImage();
	f.setIconImage(img);

    JPanel p1=new JPanel();    
    JPanel p2=new JPanel();  
    JPanel p3=new JPanel();  
    JTabbedPane tp=new JTabbedPane();  
    tp.setBounds(0,30,800,510);  
    tp.add("Adým 1",p1);  
    p1.setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 785, 483);
    p1.add(panel);
    panel.setLayout(null);
    
    JLabel jlbl_otel_email = new JLabel("Otel Email:");
    jlbl_otel_email.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_otel_email.setBounds(10, 91, 58, 13);
    panel.add(jlbl_otel_email);
    
    jtxt_otel_email = new JTextField();
    jtxt_otel_email.setEnabled(false);
    jtxt_otel_email.setColumns(10);
    jtxt_otel_email.setBounds(87, 88, 96, 19);
    panel.add(jtxt_otel_email);
    
    jtxt_otel_tel = new JTextField();
    jtxt_otel_tel.setEnabled(false);
    jtxt_otel_tel.setColumns(10);
    jtxt_otel_tel.setBounds(87, 62, 96, 19);
    panel.add(jtxt_otel_tel);
    
    JLabel jlbl_otel_telefon = new JLabel("Otel Tel No:");
    jlbl_otel_telefon.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_otel_telefon.setBounds(10, 65, 58, 13);
    panel.add(jlbl_otel_telefon);
    
    JLabel jlbl_otel_adresi = new JLabel("Otel Adresi:");
    jlbl_otel_adresi.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_otel_adresi.setBounds(10, 39, 58, 13);
    panel.add(jlbl_otel_adresi);
    
    jtxt_otel_adres = new JTextField();
    jtxt_otel_adres.setEnabled(false);
    jtxt_otel_adres.setColumns(10);
    jtxt_otel_adres.setBounds(87, 36, 96, 19);
    panel.add(jtxt_otel_adres);
    
    jtxt_otel_ad = new JTextField();
    jtxt_otel_ad.setEnabled(false);
    jtxt_otel_ad.setColumns(10);
    jtxt_otel_ad.setBounds(87, 10, 96, 19);
    panel.add(jtxt_otel_ad);
    
    JLabel jlbl_otel_adi = new JLabel("Otel Ad\u0131:");
    jlbl_otel_adi.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_otel_adi.setBounds(10, 13, 58, 13);
    panel.add(jlbl_otel_adi);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(193, 12, 572, 187);
    panel.add(scrollPane);
    
    otel_tablo = new JTable();
    otel_tablo.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int selectedrow = otel_tablo.getSelectedRow();
			DefaultTableModel tm = (DefaultTableModel)otel_tablo.getModel();
    		jtxt_otel_ad.setText(tm.getValueAt(selectedrow, 1).toString());
    		jtxt_otel_adres.setText(tm.getValueAt(selectedrow, 2).toString());
    		jtxt_otel_tel.setText(tm.getValueAt(selectedrow, 3).toString());
    		jtxt_otel_email.setText(tm.getValueAt(selectedrow, 4).toString());
    		gizli_otel_id.setText(tm.getValueAt(selectedrow, 0).toString());
    		
    		jtxt_oda_no.setText("");
    		jtxt_oda_tip.setText("");
    		jtxt_oda_fiyat.setText("");
    		jtxt_yemek_tip.setText("");
    		jtxt_yemek_fiyat.setText("");
			odaListele();
			yemekListele();
    	}
    });
    otel_tablo.setModel(new DefaultTableModel(
    	new Object[][] {
    	},
    	new String[] {
    		"Otel ID", "Otel Ad\u0131", "Adresi", "Tel No", "Email"
    	}
    ));
    scrollPane.setViewportView(otel_tablo);
    
    JLabel jlbl_oda_no = new JLabel("Oda No:");
    jlbl_oda_no.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_oda_no.setBounds(10, 152, 58, 13);
    panel.add(jlbl_oda_no);
    
    jtxt_oda_no = new JTextField();
    jtxt_oda_no.setEnabled(false);
    jtxt_oda_no.setColumns(10);
    jtxt_oda_no.setBounds(87, 149, 96, 19);
    panel.add(jtxt_oda_no);
    
    JLabel jlbl_oda_tipi = new JLabel("Oda Tipi:");
    jlbl_oda_tipi.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_oda_tipi.setBounds(10, 178, 58, 13);
    panel.add(jlbl_oda_tipi);
    
    jtxt_oda_tip = new JTextField();
    jtxt_oda_tip.setEnabled(false);
    jtxt_oda_tip.setColumns(10);
    jtxt_oda_tip.setBounds(87, 175, 96, 19);
    panel.add(jtxt_oda_tip);
    
    jtxt_oda_fiyat = new JTextField();
    jtxt_oda_fiyat.setEnabled(false);
    jtxt_oda_fiyat.setColumns(10);
    jtxt_oda_fiyat.setBounds(87, 201, 96, 19);
    panel.add(jtxt_oda_fiyat);
    
    JLabel jlbl_otel_email_1 = new JLabel("Oda Fiyat\u0131:");
    jlbl_otel_email_1.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_otel_email_1.setBounds(10, 204, 58, 13);
    panel.add(jlbl_otel_email_1);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(193, 209, 314, 182);
    panel.add(scrollPane_1);
    
    oda_tablo = new JTable();
    oda_tablo.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int selectedrow = oda_tablo.getSelectedRow();
			DefaultTableModel tm = (DefaultTableModel)oda_tablo.getModel();
			
			jtxt_oda_no.setText(tm.getValueAt(selectedrow, 2).toString());
			jtxt_oda_tip.setText(tm.getValueAt(selectedrow, 3).toString());
			jtxt_oda_fiyat.setText(tm.getValueAt(selectedrow, 4).toString());
			gizli_oda_id.setText(tm.getValueAt(selectedrow, 0).toString());
			jtxt_toplam.setText("");
    	}
    });
    oda_tablo.setModel(new DefaultTableModel(
    	new Object[][] {
    	},
    	new String[] {
    		"Oda ID", "Otel ID", "Oda No", "Oda Tipi", "Oda Fiyat\u0131"
    	}
    ));
    scrollPane_1.setViewportView(oda_tablo);
    
    JLabel jlbl_yemek_tip_1 = new JLabel("Yemek Tipi:");
    jlbl_yemek_tip_1.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_yemek_tip_1.setBounds(10, 262, 67, 13);
    panel.add(jlbl_yemek_tip_1);
    
    jtxt_yemek_tip = new JTextField();
    jtxt_yemek_tip.setEnabled(false);
    jtxt_yemek_tip.setColumns(10);
    jtxt_yemek_tip.setBounds(87, 259, 96, 19);
    panel.add(jtxt_yemek_tip);
    
    jtxt_yemek_fiyat = new JTextField();
    jtxt_yemek_fiyat.setEnabled(false);
    jtxt_yemek_fiyat.setColumns(10);
    jtxt_yemek_fiyat.setBounds(87, 285, 96, 19);
    panel.add(jtxt_yemek_fiyat);
    
    JLabel jlbl_yemek_fiyat_1 = new JLabel("Yemek Fiyat\u0131:");
    jlbl_yemek_fiyat_1.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_yemek_fiyat_1.setBounds(10, 288, 77, 13);
    panel.add(jlbl_yemek_fiyat_1);
    
    JScrollPane scrollPane_3 = new JScrollPane();
    scrollPane_3.setBounds(517, 211, 248, 180);
    panel.add(scrollPane_3);
    
    yemek_tablo = new JTable();
    yemek_tablo.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int selectedrow = yemek_tablo.getSelectedRow();
			DefaultTableModel tm = (DefaultTableModel)yemek_tablo.getModel();
			
			jtxt_yemek_tip.setText(tm.getValueAt(selectedrow, 1).toString());
			jtxt_yemek_fiyat.setText(tm.getValueAt(selectedrow, 2).toString());
			
			jtxt_toplam.setText("");
    	}
    });
    yemek_tablo.setModel(new DefaultTableModel(
    	new Object[][] {
    	},
    	new String[] {
    		"Yemek ID", "Yemek Tipi", "Yemek Fiyat\u0131"
    	}
    ));
    scrollPane_3.setViewportView(yemek_tablo);
    
    JLabel jlbl_yemek_fiyat_1_1 = new JLabel("Toplam:");
    jlbl_yemek_fiyat_1_1.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_yemek_fiyat_1_1.setBounds(10, 397, 77, 13);
    panel.add(jlbl_yemek_fiyat_1_1);
    
    jtxt_toplam = new JTextField();
    jtxt_toplam.setEnabled(false);
    jtxt_toplam.setColumns(10);
    jtxt_toplam.setBounds(87, 394, 96, 19);
    panel.add(jtxt_toplam);
    
    JButton btnNewButton = new JButton("TOPLAM");
    btnNewButton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {

			String syemek = jtxt_yemek_fiyat.getText();
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
    btnNewButton.setBounds(100, 423, 85, 21);
    panel.add(btnNewButton);
    
    JButton btnTemizle = new JButton("TEM\u0130ZLE");
    btnTemizle.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		jtxt_yemek_tip.setText("");
    		jtxt_yemek_fiyat.setText("");
    	}
    });
    btnTemizle.setBounds(98, 314, 85, 21);
    panel.add(btnTemizle);
    tp.add("Adým 2",p2);  
    p2.setLayout(null);
    
    JPanel panel_1 = new JPanel();
    panel_1.setBounds(0, 0, 785, 483);
    p2.add(panel_1);
    panel_1.setLayout(null);
    
    JLabel jlbl_yemek_tip = new JLabel("Ad:");
    jlbl_yemek_tip.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_yemek_tip.setBounds(110, 47, 67, 13);
    panel_1.add(jlbl_yemek_tip);
    
    jtxt_ad = new JTextField();
    jtxt_ad.setColumns(10);
    jtxt_ad.setBounds(187, 44, 96, 19);
    panel_1.add(jtxt_ad);
    
    jtxt_email = new JTextField();
    jtxt_email.setColumns(10);
    jtxt_email.setBounds(187, 70, 96, 19);
    panel_1.add(jtxt_email);
    
    JLabel jlbl_yemek_fiyat = new JLabel("Email:");
    jlbl_yemek_fiyat.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_yemek_fiyat.setBounds(110, 73, 77, 13);
    panel_1.add(jlbl_yemek_fiyat);
    
    JDateChooser date_giris = new JDateChooser();
    date_giris.setDateFormatString("dd/MM/yyyy");
    date_giris.setBounds(419, 44, 96, 19);
    panel_1.add(date_giris);
    
    JDateChooser date_cikis = new JDateChooser();
    date_cikis.setDateFormatString("dd/MM/yyyy");
    date_cikis.setBounds(419, 70, 96, 19);
    panel_1.add(date_cikis);
    
    
    
    JScrollPane scrollPane_4 = new JScrollPane();
    scrollPane_4.setBounds(10, 230, 764, 243);
    panel_1.add(scrollPane_4);
    
    rezervasyon_tablo = new JTable();
    rezervasyon_tablo.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int selectedrow = rezervasyon_tablo.getSelectedRow();
			DefaultTableModel tmr = (DefaultTableModel)rezervasyon_tablo.getModel();
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
    	
			jtxt_ad.setText(tmr.getValueAt(selectedrow, 1).toString());
			jtxt_email.setText(tmr.getValueAt(selectedrow, 2).toString());
			jtxt_adres.setText(tmr.getValueAt(selectedrow, 3).toString());
			jtxt_otel_ad.setText(tmr.getValueAt(selectedrow, 4).toString());
			jtxt_oda_no.setText(tmr.getValueAt(selectedrow, 7).toString());
			jtxt_oda_tip.setText(tmr.getValueAt(selectedrow, 8).toString());
			jtxt_oda_fiyat.setText(tmr.getValueAt(selectedrow, 9).toString());
			jtxt_yemek_tip.setText(tmr.getValueAt(selectedrow, 10).toString());
			jtxt_yemek_fiyat.setText(tmr.getValueAt(selectedrow, 11).toString());
			jtxt_toplam.setText(tmr.getValueAt(selectedrow, 12).toString());
    		
			try {
				Statement st = fonk.conn.createStatement();
				String sorgu = "SELECT hotel_id,room_id FROM reservation WHERE reservation_id='"+tmr.getValueAt(selectedrow, 0)+"'";
				ResultSet rso = st.executeQuery(sorgu);
				
				rso.beforeFirst();
				rso.next();
				String hotel_id = rso.getString(1);
				String room_id = rso.getString(2);
				
				//System.out.println(hotel_id + "  "+ room_id); *** yukarýdaki kodun kontrolü
			
				gizli_otel_id.setText(hotel_id);
				gizli_oda_id.setText(room_id);
				
				
				String query = "SELECT hotel_adress, hotel_contact_no, hotel_email FROM hotel WHERE hotel_id='"+hotel_id+"'";
				ResultSet rs = st.executeQuery(query);
				
				while (rs.next()) {
					String adres = rs.getString("hotel_adress");
					String tel = rs.getString("hotel_contact_no");
					String email = rs.getString("hotel_email");
				
					jtxt_otel_adres.setText(adres);
					jtxt_otel_tel.setText(tel);
					jtxt_otel_email.setText(email);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	}
    });
	rezervasyon_tablo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Rezervasyon ID", "Ad", "Email", "Adres", "Otel Ad\u0131", "Giri\u015F Tarihi", "\u00C7\u0131k\u0131\u015F Tarihi", "Oda No", "Oda Tipi", "Oda Fiyat\u0131", "Yemek Tercihi", "Yemek Fiyat\u0131", "Toplam"
			}
		));
    
    
    
    
    scrollPane_4.setViewportView(rezervasyon_tablo);
    
    JLabel jlbl_yemek_fiyat_2 = new JLabel("Adres:");
    jlbl_yemek_fiyat_2.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_yemek_fiyat_2.setBounds(110, 102, 77, 13);
    panel_1.add(jlbl_yemek_fiyat_2);
    
    jtxt_adres = new JTextField();
    jtxt_adres.setColumns(10);
    jtxt_adres.setBounds(187, 99, 96, 19);
    panel_1.add(jtxt_adres);
    
    JLabel jlbl_giris = new JLabel("Giri\u015F Tarihi:");
    jlbl_giris.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_giris.setBounds(342, 44, 67, 13);
    panel_1.add(jlbl_giris);
    
  
    
    JLabel jlbl_cikis = new JLabel("\u00C7\u0131k\u0131\u015F Tarihi:");
    jlbl_cikis.setHorizontalAlignment(SwingConstants.LEFT);
    jlbl_cikis.setBounds(342, 70, 67, 13);
    panel_1.add(jlbl_cikis);
    
    JButton btn_reservasyon_ekle = new JButton("EKLE");

    btn_reservasyon_ekle.setBounds(576, 47, 96, 21);
    panel_1.add(btn_reservasyon_ekle);
    
    JButton btn_rezervasyon_duzenle = new JButton("D\u00DCZENLE");
    btn_rezervasyon_duzenle.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	
			String musteri_id = jtxt_musteri_id.getText();
			String musteriadi = jtxt_ad.getText();
			String musteriemaili = jtxt_email.getText();
			String musteriadresi= jtxt_adres.getText();
			String otel_id = gizli_otel_id.getText();
			String otel_ad =jtxt_otel_ad.getText();

			
			SimpleDateFormat sdfgiris = new SimpleDateFormat("dd/MM/yyyy");
			String giris = sdfgiris.format(date_giris.getDate()).toString();
			String cikis =sdfgiris.format(date_cikis.getDate()).toString();

			String oda_id = gizli_oda_id.getText();
			
			String odano =jtxt_oda_no.getText();
			String odatip=jtxt_oda_tip.getText();
			String odafiyat = jtxt_oda_fiyat.getText();
			String yemek = jtxt_yemek_tip.getText();
			String yemekucret = jtxt_yemek_fiyat.getText();
			String toplam = jtxt_toplam.getText();
			
			
			int selectedRow2 = rezervasyon_tablo.getSelectedRow();
			DefaultTableModel tmo = (DefaultTableModel)rezervasyon_tablo.getModel();
			if(selectedRow2==-1){
		            if(tmo.getRowCount()==0){
		            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
		        }else {
		        	int resid = Integer.parseInt(tmo.getValueAt(selectedRow2, 0).toString());
		        	
		        	fonk.rezervasyonDuzenle(resid,musteri_id, musteriadi, musteriemaili, musteriadresi, otel_id, otel_ad, giris,cikis,oda_id,  odano, odatip, odafiyat, yemek, yemekucret, toplam);

		        	JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        	
		        	rezervasyonListele(musteri_id);
		        
		        }
    		
    	}
    });
    btn_rezervasyon_duzenle.setBounds(576, 78, 96, 21);
    panel_1.add(btn_rezervasyon_duzenle);
    
    JButton btn_rezervasyon_sil = new JButton("S\u0130L");
    btn_rezervasyon_sil.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		int selectedrow = rezervasyon_tablo.getSelectedRow();
			DefaultTableModel tm = (DefaultTableModel)rezervasyon_tablo.getModel();
			
		      if(selectedrow==-1){
		            if(tm.getRowCount()==0){
		            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
		        }else {
		        	
		        	int resid = Integer.parseInt(tm.getValueAt(selectedrow, 0).toString());
		            fonk.rezervasyonSil(resid);	
		           
		            String musteri_id = jtxt_musteri_id.getText();
		            rezervasyonListele(musteri_id);
		            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        
		        }
    	}
    });
    btn_rezervasyon_sil.setBounds(576, 109, 96, 21);
    panel_1.add(btn_rezervasyon_sil);
    
    JButton btn_bilgilerimdenGetir = new JButton("Bilgilerimden Getir");
    btn_bilgilerimdenGetir.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String id = jtxt_musteri_id.getText();
    		try {
    			Statement st = fonk.conn.createStatement();
    			String query = "SELECT * FROM customer WHERE customer_id= '"+id+"'";
    			ResultSet rs = st.executeQuery(query);
    		
    			while (rs.next()) {
    				   
    				    String o[] = { rs.getString("customer_name"),rs.getString("customer_email"), rs.getString("customer_adress")};
    				    
    				    jtxt_ad.setText(o[0]);
    				    jtxt_email.setText(o[1]);
    				    jtxt_adres.setText(o[2]);
    				    	
    				    
    				   
    				}
    		} catch (SQLException ex) {
    			// TODO Auto-generated catch block
    			ex.printStackTrace();
    		}
    	}
    });
    btn_bilgilerimdenGetir.setBounds(110, 125, 173, 21);
    panel_1.add(btn_bilgilerimdenGetir);
    
    JLabel lblNewLabel = new JLabel("REZERVASYONLARIM");
    lblNewLabel.setBounds(10, 199, 210, 21);
    panel_1.add(lblNewLabel);

    
    f.getContentPane().add(tp);  
    f.setSize(800,600);  
    f.getContentPane().setLayout(null);  
    
    JButton btnCikis = new JButton("\u00C7IKI\u015E");
    btnCikis.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if (JOptionPane.showConfirmDialog(null, "Çýkmak Ýstediðinize Emin Misiniz?", "UYARI",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    f.setVisible(false);
			    MusteriLoginPanel musteri = new MusteriLoginPanel();
			    musteri.setVisible(true);
			} else {
			    
			}
    	}
    });
    btnCikis.setBounds(691, 10, 85, 21);
    f.getContentPane().add(btnCikis);
    
    jtxt_musteri_id = new JTextField();
    jtxt_musteri_id.setEnabled(false);
    jtxt_musteri_id.setEditable(false);
    jtxt_musteri_id.setColumns(10);
    jtxt_musteri_id.setBounds(656, 10, 25, 21);
    f.getContentPane().add(jtxt_musteri_id);
    
    JButton btnBilgilerimiGncelle = new JButton("B\u0130LG\u0130LER\u0130M\u0130 G\u00DCNCELLE");
    btnBilgilerimiGncelle.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		MusteriBilgilerimiGuncelle guncel = new MusteriBilgilerimiGuncelle();
    		String id = jtxt_musteri_id.getText();
    		guncel.musteriListele(id);
    		guncel.setVisible(true);
    		guncel.setMusteri_bilgileri_tablo();
    		
    	}
    });
    btnBilgilerimiGncelle.setBounds(436, 10, 210, 21);
    f.getContentPane().add(btnBilgilerimiGncelle);
    
    gizli_otel_id = new JTextField();
    gizli_otel_id.setEnabled(false);
    gizli_otel_id.setEditable(false);
    gizli_otel_id.setVisible(false);
    gizli_otel_id.setBounds(305, 11, 11, 9);
    f.getContentPane().add(gizli_otel_id);
    gizli_otel_id.setColumns(10);
    
    gizli_oda_id = new JTextField();
    gizli_oda_id.setVisible(false);
    gizli_oda_id.setEnabled(false);
    gizli_oda_id.setEditable(false);
    gizli_oda_id.setColumns(10);
    gizli_oda_id.setBounds(327, 11, 11, 9);
    f.getContentPane().add(gizli_oda_id);
    
    btn_reservasyon_ekle.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
    		
    						
    						String musteri_id = jtxt_musteri_id.getText();
    						String musteriadi = jtxt_ad.getText();
    						String musteriemaili = jtxt_email.getText();
    						String musteriadresi= jtxt_adres.getText();
    						String otel_id = gizli_otel_id.getText();
    						String otel_ad =jtxt_otel_ad.getText();
    			
    						
    						SimpleDateFormat sdfgiris = new SimpleDateFormat("dd/MM/yyyy");
    						String giris = sdfgiris.format(date_giris.getDate()).toString();
    						String cikis =sdfgiris.format(date_cikis.getDate()).toString();

    						String oda_id = gizli_oda_id.getText();
    						
    						
    						String odano =jtxt_oda_no.getText();
    						String odatip=jtxt_oda_tip.getText();
    						String odafiyat = jtxt_oda_fiyat.getText();
    						String yemek = jtxt_yemek_tip.getText();
    						String yemekucret = jtxt_yemek_fiyat.getText();
    						String toplam = jtxt_toplam.getText();
    						
    						fonk.rezervasyonEkle(musteri_id, musteriadi, musteriemaili, musteriadresi, otel_id, otel_ad, giris,cikis, oda_id,  odano, odatip, odafiyat, yemek, yemekucret, toplam);
    						
    						rezervasyonListele(musteri_id);
    						
    				} catch (Exception ex) {
    					// TODO Auto-generated catch block
    					JOptionPane.showMessageDialog(null, "Hatalý Giriþ"+ex, "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
    				}
    	}
    });
    
    
    
    
    
    
    
    
    
    
    
    

    f.setVisible(true);  
}  
public static void main(String[] args) {  
    MusteriPanel panel = new MusteriPanel();  
    panel.otelListele();
    
}	
	public JTextField setJtxt_musteri_id(String id) {
		jtxt_musteri_id.setText(id);
		return jtxt_musteri_id;
	}
	public JTextField getJtxt_musteri_id() {
		return jtxt_musteri_id;
	}
}  