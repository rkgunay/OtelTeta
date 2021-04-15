import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class OtelLoginPanel extends JFrame {

	private JPanel contentPane;
	private JTextField otel_kullanici_adi;
	private JPasswordField otel_sifre_alani;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtelLoginPanel frame = new OtelLoginPanel();
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
	
	public OtelLoginPanel() {
		setTitle("Personel Giri\u015Fi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Image img = new ImageIcon(this.getClass().getResource("/Logo1.png")).getImage();
		this.setIconImage(img);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(72, 52, 241, 115);
		panel.add(panel_1);
		
		JLabel jlbl_kullanici_adi1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_kullanici_adi1.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_kullanici_adi1.setBounds(10, 13, 96, 13);
		panel_1.add(jlbl_kullanici_adi1);
		
		otel_kullanici_adi = new JTextField();
		otel_kullanici_adi.setColumns(10);
		otel_kullanici_adi.setBounds(105, 10, 96, 19);
		panel_1.add(otel_kullanici_adi);
		
		JLabel jlbl_sifre1 = new JLabel("\u015Eifre:");
		jlbl_sifre1.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_sifre1.setBounds(10, 39, 58, 13);
		panel_1.add(jlbl_sifre1);
		
		JButton btn_giris1 = new JButton("G\u0130R\u0130\u015E");
		
		//Giriþ butonuna týkladýðýnda giriþ iþlemini gerçekleþtiren kod. 
		btn_giris1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtelPanel otel = new OtelPanel();
				String user_name = otel_kullanici_adi.getText();
		        String password = new String(otel_sifre_alani.getPassword());
		        boolean  result = VeritabaniFonksiyonlar.PersonelLogin(user_name, password);
		        if(result == true){
		        	try {
						Statement st = fonk.conn.createStatement();
						String query = "SELECT hotel_id FROM personel WHERE personel_username='"+user_name+"' AND personel_password='"+password+"'";
						ResultSet rs = st.executeQuery(query);
						rs.beforeFirst();
						rs.next();
						String otelid =rs.getString(1);
						
						
						 
						
						setVisible(false);
						otel.odaListele(otelid);
						otel.rezervasyonListele(otelid);
						otel.musteriListele(otelid);
						otel.setVisible(true);
						otel.setId_field(otelid);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	
		        }else{
		            otel_sifre_alani.setText("");
		            JOptionPane.showMessageDialog(null, "Kullanýcý adý veya þifre yanlýþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        }
				
				
			}
		});
		btn_giris1.setBounds(116, 84, 85, 21);
		panel_1.add(btn_giris1);
		
		
		otel_sifre_alani = new JPasswordField();
		
		//Þifre girme alanýnda enter tuþuna basýldýðýnda giriþ iþlemini gerçekleþtiren kod. 
		otel_sifre_alani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					OtelPanel otel = new OtelPanel();
					String user_name = otel_kullanici_adi.getText();
			        String password = new String(otel_sifre_alani.getPassword());
			        boolean  result = VeritabaniFonksiyonlar.PersonelLogin(user_name, password);
			        if(result == true){
			        	try {
							Statement st = fonk.conn.createStatement();
							String query = "SELECT hotel_id FROM personel WHERE personel_username='"+user_name+"' AND personel_password='"+password+"'";
							ResultSet rs = st.executeQuery(query);
							rs.beforeFirst();
							rs.next();
							String otelid =rs.getString(1);
							
							
							setVisible(false);
							otel.odaListele(otelid);
							otel.rezervasyonListele(otelid);
							otel.musteriListele(otelid);
							otel.setVisible(true);
							otel.setId_field(otelid);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		
			        }else{
			            otel_sifre_alani.setText("");
			            JOptionPane.showMessageDialog(null, "Kullanýcý adý veya þifre yanlýþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        }
				
				
				//Ýlk baþta aþaðýdaki gibi login yaparken otel id'sini de istiyordum. Sonra onu kaldýrýp sadece kullanýcý adý ve þifre ile logine izin verdim.
				//Tabi ki PersonelLogin fonksiyonunu da deðiþtirdim. 
			     /*	OtelPanel otel = new OtelPanel();
				String otelid = jtxt_otel_id.getText();
				String user_name = otel_kullanici_adi.getText();
		        String password = new String(otel_sifre_alani.getPassword());
		        boolean  result = VeritabaniFonksiyonlar.PersonelLogin(user_name, password, otelid);
		        if(result == true){
        			setVisible(false);
			        otel.setVisible(true);
			        otel.odaListele(otelid);
					otel.rezervasyonListele(otelid);
					otel.musteriListele(otelid);
					otel.yemekListele(otelid);
					otel.setId_field(otelid);
	
		        }else{
		            otel_sifre_alani.setText("");
		            JOptionPane.showMessageDialog(null, "Kullanýcý adý veya þifre yanlýþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        }		
			      */
			}
		});
		otel_sifre_alani.setBounds(105, 36, 96, 19);
		panel_1.add(otel_sifre_alani);
		
	}

	
}
