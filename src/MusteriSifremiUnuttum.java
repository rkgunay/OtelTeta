import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class MusteriSifremiUnuttum extends JFrame {

	
	
	private JPanel contentPane;
	private JTextField jtxt_unuttum_kadi;
	private JTextField jtxt_unuttum_email;

	
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriSifremiUnuttum frame = new MusteriSifremiUnuttum();
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
	public MusteriSifremiUnuttum() {
		setTitle("\u015Eifremi Unuttum");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Image img = new ImageIcon(this.getClass().getResource("/Logo1.png")).getImage();
		this.setIconImage(img);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel jlbl_kullanici_adi1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_kullanici_adi1.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_kullanici_adi1.setBounds(97, 72, 96, 13);
		panel.add(jlbl_kullanici_adi1);
		
		jtxt_unuttum_kadi = new JTextField();
		jtxt_unuttum_kadi.setColumns(10);
		jtxt_unuttum_kadi.setBounds(192, 69, 96, 19);
		panel.add(jtxt_unuttum_kadi);
		
		JLabel jlbl_kullanici_adi1_1 = new JLabel("Email:");
		jlbl_kullanici_adi1_1.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_kullanici_adi1_1.setBounds(97, 98, 96, 13);
		panel.add(jlbl_kullanici_adi1_1);
		
		jtxt_unuttum_email = new JTextField();
		jtxt_unuttum_email.setColumns(10);
		jtxt_unuttum_email.setBounds(192, 95, 96, 19);
		panel.add(jtxt_unuttum_email);
		
		JButton btn_sifre_gonder = new JButton("G\u00D6NDER");
		btn_sifre_gonder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = jtxt_unuttum_kadi.getText();
		        String email = jtxt_unuttum_email.getText();
		        boolean  result = VeritabaniFonksiyonlar.MusteriSifre(username, email);
		        
		        if(result == true){
		            try {
						Statement st = fonk.conn.createStatement();
						String query = "SELECT customer_password FROM customer WHERE customer_username='"+username+"' AND customer_email='"+email+"'";
						ResultSet rs = st.executeQuery(query);
						rs.beforeFirst();
						rs.next();
						String musteri_sifre =rs.getString(1);
						JOptionPane.showMessageDialog(null, "Þifreniz: "+ musteri_sifre, "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		        else{
		         
		            jtxt_unuttum_kadi.setText("");
		            jtxt_unuttum_email.setText("");
		            JOptionPane.showMessageDialog(null, "Kullanýcý adý veya email yanlýþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        }
			}
		});
		btn_sifre_gonder.setBounds(192, 124, 96, 21);
		panel.add(btn_sifre_gonder);
	}
}
