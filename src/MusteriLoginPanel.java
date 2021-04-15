import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MusteriLoginPanel extends JFrame {

	private JPanel contentPane;
	private JTextField jtxt_kullanici_adi;
	private JPasswordField jtxt_sifre;
	private JTextField jtxt_kayit_kadi;
	private JPasswordField jtxt_kayit_sifre;
	private JTextField jtxt_kayit_ad;
	private JTextField jtxt_kayit_email;
	private JTextField jtxt_kayit_adres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriLoginPanel frame = new MusteriLoginPanel();
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
	
	public MusteriLoginPanel() {
		setTitle("M\u00FC\u015Fteri Giri\u015Fi");
		
		VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image img = new ImageIcon(this.getClass().getResource("/Logo1.png")).getImage();
		this.setIconImage(img);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 586, 363);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 10, 227, 343);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel jlbl_kullanici_adi = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_kullanici_adi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_kullanici_adi.setBounds(10, 124, 85, 13);
		panel_1.add(jlbl_kullanici_adi);
		
		JLabel jlbl_sifre = new JLabel("\u015Eifre:");
		jlbl_sifre.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_sifre.setBounds(10, 147, 58, 13);
		panel_1.add(jlbl_sifre);
		
		jtxt_kullanici_adi = new JTextField();
		jtxt_kullanici_adi.setColumns(10);
		jtxt_kullanici_adi.setBounds(114, 121, 96, 19);
		panel_1.add(jtxt_kullanici_adi);
		
		jtxt_sifre = new JPasswordField();
		jtxt_sifre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = jtxt_kullanici_adi.getText();
		        String password = new String(jtxt_sifre.getPassword());
		        boolean  result = VeritabaniFonksiyonlar.MusteriLogin(id, password);
		        if(result == true){
		            try {
						MusteriPanel musteri = new MusteriPanel();
						setVisible(false);
						musteri.f.setVisible(true);
						musteri.otelListele();
						
						
						
						Statement st = fonk.conn.createStatement();
						String query = "SELECT customer_id FROM customer WHERE customer_username='"+id+"' AND customer_password='"+password+"'";
						ResultSet rs = st.executeQuery(query);
						rs.beforeFirst();
						rs.next();
						String musteri_id =rs.getString(1);
						musteri.setJtxt_musteri_id(musteri_id);
						
						musteri.rezervasyonListele(musteri_id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		        else{
		         
		            jtxt_sifre.setText("");
		            JOptionPane.showMessageDialog(null, "Kullanýcý adý veya þifre yanlýþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        }
			}
		});
		jtxt_sifre.setBounds(114, 144, 96, 19);
		panel_1.add(jtxt_sifre);
		
		JButton btn_giris = new JButton("G\u0130R\u0130\u015E");
		btn_giris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = jtxt_kullanici_adi.getText();
		        String password = new String(jtxt_sifre.getPassword());
		        boolean  result = VeritabaniFonksiyonlar.MusteriLogin(id, password);
		        if(result == true){
		            try {
						MusteriPanel musteri = new MusteriPanel();
						setVisible(false);
						musteri.f.setVisible(true);
						musteri.otelListele();
						
						
						
						Statement st = fonk.conn.createStatement();
						String query = "SELECT customer_id FROM customer WHERE customer_username='"+id+"' AND customer_password='"+password+"'";
						ResultSet rs = st.executeQuery(query);
						rs.beforeFirst();
						rs.next();
						String musteri_id =rs.getString(1);
						musteri.setJtxt_musteri_id(musteri_id);
						
						musteri.rezervasyonListele(musteri_id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		        else{
		         
		            jtxt_sifre.setText("");
		            JOptionPane.showMessageDialog(null, "Kullanýcý adý veya þifre yanlýþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        }
				
			}
		});
		btn_giris.setBounds(125, 190, 85, 21);
		panel_1.add(btn_giris);
		
		JLabel lblNewLabel = new JLabel("\u00DCye Giri\u015Fi  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(114, 10, 96, 13);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u015Eifremi Unuttum ");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 10));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 10));
		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(null);
		btnNewButton.setIconTextGap(0);
		btnNewButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusteriSifremiUnuttum musteri = new MusteriSifremiUnuttum();
				musteri.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(114, 167, 96, 13);
		panel_1.add(btnNewButton);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBounds(247, 10, 329, 343);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblyeOl = new JLabel("Kay\u0131t Ol ");
		lblyeOl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblyeOl.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblyeOl.setBounds(223, 10, 96, 13);
		panel_1_1.add(lblyeOl);
		
		JLabel jlbl_kullanici_adi_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_kullanici_adi_1.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_kullanici_adi_1.setBounds(47, 53, 85, 13);
		panel_1_1.add(jlbl_kullanici_adi_1);
		
		jtxt_kayit_kadi = new JTextField();
		jtxt_kayit_kadi.setColumns(10);
		jtxt_kayit_kadi.setBounds(151, 50, 96, 19);
		panel_1_1.add(jtxt_kayit_kadi);
		
		JLabel jlbl_sifre_1 = new JLabel("\u015Eifre:");
		jlbl_sifre_1.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_sifre_1.setBounds(47, 82, 58, 13);
		panel_1_1.add(jlbl_sifre_1);
		
		jtxt_kayit_sifre = new JPasswordField();
		jtxt_kayit_sifre.setBounds(151, 79, 96, 19);
		panel_1_1.add(jtxt_kayit_sifre);
		
		JLabel jlbl_musteri_ad = new JLabel("Ad:");
		jlbl_musteri_ad.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_ad.setBounds(47, 108, 58, 13);
		panel_1_1.add(jlbl_musteri_ad);
		
		jtxt_kayit_ad = new JTextField();
		jtxt_kayit_ad.setColumns(10);
		jtxt_kayit_ad.setBounds(151, 105, 96, 19);
		panel_1_1.add(jtxt_kayit_ad);
		
		JLabel jlbl_musteri_email = new JLabel("Email:");
		jlbl_musteri_email.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_email.setBounds(47, 134, 58, 13);
		panel_1_1.add(jlbl_musteri_email);
		
		jtxt_kayit_email = new JTextField();
		jtxt_kayit_email.setColumns(10);
		jtxt_kayit_email.setBounds(151, 131, 96, 19);
		panel_1_1.add(jtxt_kayit_email);
		
		jtxt_kayit_adres = new JTextField();
		jtxt_kayit_adres.setColumns(10);
		jtxt_kayit_adres.setBounds(151, 157, 96, 19);
		panel_1_1.add(jtxt_kayit_adres);
		
		JLabel jlbl_musteri_adres = new JLabel("Adres:");
		jlbl_musteri_adres.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_musteri_adres.setBounds(47, 160, 58, 13);
		panel_1_1.add(jlbl_musteri_adres);
		
		JButton btn_giris_1 = new JButton("KAYIT OL");
		btn_giris_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String boskontrolu = jtxt_kayit_kadi.getText();
				String boskontrolu2 = jtxt_kayit_sifre.getText();
				if(boskontrolu.isEmpty()==false&&boskontrolu2.isEmpty()==false) {
					String kadi = jtxt_kayit_kadi.getText();
					String sifre = new String(jtxt_kayit_sifre.getPassword()).toString();
					String ad = jtxt_kayit_ad.getText();
					String email = jtxt_kayit_email.getText();
					String adres = jtxt_kayit_adres.getText();
					String otelid = "Yok";
					
					fonk.musteriEkle(otelid, ad, email, adres, kadi, sifre);
					
					jtxt_kayit_kadi.setText("");
					jtxt_kayit_sifre.setText("");
					jtxt_kayit_ad.setText("");
					jtxt_kayit_email.setText("");
					jtxt_kayit_adres.setText("");
					
					JOptionPane.showMessageDialog(null, "Kayýt Baþarýlý", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
				}else {
					JOptionPane.showMessageDialog(null, "Kullanýcý adý ve þifre alanlarýný doldurunuz.", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
				}
				
			}
		});
		btn_giris_1.setBounds(151, 186, 96, 21);
		panel_1_1.add(btn_giris_1);
	}
}
