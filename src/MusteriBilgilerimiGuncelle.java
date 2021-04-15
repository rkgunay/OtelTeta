import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MusteriBilgilerimiGuncelle extends JFrame {
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	private JPanel contentPane;
	private JTextField jtxt_kullanici_adi;
	private JPasswordField jtxt_sifre;
	private JTextField jtxt_ad;
	private JTextField jtxt_email;
	private JTextField jtxt_adres;
	private JTable musteri_bilgileri_tablo;

	public void musteriListele(String id) {
		try {
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM customer WHERE customer_id= '"+id+"'";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)musteri_bilgileri_tablo.getModel();
			tm.setRowCount(0);
		
			while (rs.next()) {
				   
				    String o[] = {rs.getString("customer_id"), rs.getString("customer_username".toString()), rs.getString("customer_password"),
				    		rs.getString("customer_name"),rs.getString("customer_email"), rs.getString("customer_adress")};
				    
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
					MusteriBilgilerimiGuncelle frame = new MusteriBilgilerimiGuncelle();
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
	public MusteriBilgilerimiGuncelle() {
		setTitle("M\u00FC\u015Fteri - Ki\u015Fisel Bilgileri G\u00FCncelleme");
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image img = new ImageIcon(this.getClass().getResource("/Logo1.png")).getImage();
		this.setIconImage(img);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 386, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		jtxt_kullanici_adi = new JTextField();
		jtxt_kullanici_adi.setColumns(10);
		jtxt_kullanici_adi.setBounds(114, 10, 96, 19);
		panel.add(jtxt_kullanici_adi);
		
		JLabel jlbl_kullanici_adi_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_kullanici_adi_1.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_kullanici_adi_1.setBounds(10, 13, 85, 13);
		panel.add(jlbl_kullanici_adi_1);
		
		JLabel jlbl_sifre_1 = new JLabel("\u015Eifre:");
		jlbl_sifre_1.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_sifre_1.setBounds(10, 42, 58, 13);
		panel.add(jlbl_sifre_1);
		
		jtxt_sifre = new JPasswordField();
		jtxt_sifre.setBounds(114, 39, 96, 19);
		panel.add(jtxt_sifre);
		
		jtxt_ad = new JTextField();
		jtxt_ad.setColumns(10);
		jtxt_ad.setBounds(114, 65, 96, 19);
		panel.add(jtxt_ad);
		
		JLabel jlbl_musteri_ad = new JLabel("Ad:");
		jlbl_musteri_ad.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_ad.setBounds(10, 68, 58, 13);
		panel.add(jlbl_musteri_ad);
		
		JLabel jlbl_musteri_email = new JLabel("Email:");
		jlbl_musteri_email.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_email.setBounds(10, 94, 58, 13);
		panel.add(jlbl_musteri_email);
		
		jtxt_email = new JTextField();
		jtxt_email.setColumns(10);
		jtxt_email.setBounds(114, 91, 96, 19);
		panel.add(jtxt_email);
		
		jtxt_adres = new JTextField();
		jtxt_adres.setColumns(10);
		jtxt_adres.setBounds(114, 117, 96, 19);
		panel.add(jtxt_adres);
		
		JLabel jlbl_musteri_adres = new JLabel("Adres:");
		jlbl_musteri_adres.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_adres.setBounds(10, 120, 58, 13);
		panel.add(jlbl_musteri_adres);
		
		JButton btnTemizle = new JButton("TEM\u0130ZLE");
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtxt_kullanici_adi.setText("");
				jtxt_sifre.setText("");
				jtxt_ad.setText("");
				jtxt_email.setText("");
				jtxt_adres.setText("");
			}
		});
		btnTemizle.setBounds(10, 159, 85, 21);
		panel.add(btnTemizle);
		
		JButton btnGncelle = new JButton("G\u00DCNCELLE");
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String boskontrolu = jtxt_kullanici_adi.getText();
				String boskontrolu2 = jtxt_sifre.getText();
				
				
				if(boskontrolu.isEmpty()==false&&boskontrolu2.isEmpty()==false) {
					String kadi = jtxt_kullanici_adi.getText();
					String sifre = new String(jtxt_sifre.getPassword());
					String ad = jtxt_ad.getText();
					String email = jtxt_email.getText();
					String adres = jtxt_adres.getText();
					String otelid = "Yok";
					
					int selectedRow = musteri_bilgileri_tablo.getSelectedRow();
					DefaultTableModel tm = (DefaultTableModel)musteri_bilgileri_tablo.getModel();
					
					int id = Integer.parseInt(tm.getValueAt(selectedRow, 0).toString());
					
					fonk.musteriGuncelle(id  ,otelid, ad, email, adres, kadi, sifre);
					
					musteriListele(tm.getValueAt(selectedRow, 0).toString());
					
					JOptionPane.showMessageDialog(null, "Güncelleme Baþarýlý", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
				}else {
					JOptionPane.showMessageDialog(null, "Kullanýcý adý ve þifre alanlarýný doldurunuz.", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
				}
			}
		});
		btnGncelle.setBounds(105, 159, 105, 21);
		panel.add(btnGncelle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setFocusable(false);
		scrollPane.setEnabled(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 202, 366, 51);
		panel.add(scrollPane);
		
		musteri_bilgileri_tablo = new JTable();
		musteri_bilgileri_tablo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = musteri_bilgileri_tablo.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)musteri_bilgileri_tablo.getModel();
				jtxt_kullanici_adi.setText(tm.getValueAt(selectedRow, 1).toString());
				jtxt_sifre.setText(tm.getValueAt(selectedRow, 2).toString());
				jtxt_ad.setText(tm.getValueAt(selectedRow, 3).toString());
				jtxt_email.setText(tm.getValueAt(selectedRow, 4).toString());
				jtxt_adres.setText(tm.getValueAt(selectedRow, 5).toString());
			}
		});
		musteri_bilgileri_tablo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musteri_bilgileri_tablo.setFocusable(false);
		musteri_bilgileri_tablo.setFocusTraversalKeysEnabled(false);
		musteri_bilgileri_tablo.setAutoscrolls(false);
		scrollPane.setViewportView(musteri_bilgileri_tablo);
		musteri_bilgileri_tablo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Kullan\u0131c\u0131 Ad\u0131", "\u015Eifre", "Ad", "Email", "Adres"
			}
		));
	}
	public JTable setMusteri_bilgileri_tablo() {
		musteri_bilgileri_tablo.setRowSelectionInterval(0, 0);
		return null;
	}
}
