import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AdminLoginPanel extends JFrame {

	private JPanel contentPane;
	private JTextField jtxt_kullanici_adi;
	private JPasswordField sifre_alani;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginPanel frame = new AdminLoginPanel();
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
	
	//VeritabanýFonksiyonlar sýnýfýndaki fonksiyonlarý çaðýrabilmek için o sýnýfý bu sýnýfa tanýttýk. 
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	
	
	
	
	public AdminLoginPanel() {
		setTitle("Y\u00F6netici Giri\u015Fi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		Image img = new ImageIcon(this.getClass().getResource("/Logo1.png")).getImage();
		this.setIconImage(img);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(83, 64, 215, 105);
		panel_1.setLayout(null);
		
		JLabel jlbl_kullanici_adi = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_kullanici_adi.setBounds(5, 13, 85, 13);
		jlbl_kullanici_adi.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(jlbl_kullanici_adi);
		
		jtxt_kullanici_adi = new JTextField();
		jtxt_kullanici_adi.setBounds(109, 10, 96, 19);
		panel_1.add(jtxt_kullanici_adi);
		jtxt_kullanici_adi.setColumns(10);
		panel.add(panel_1);
		
		JLabel jlbl_sifre = new JLabel("\u015Eifre:");
		jlbl_sifre.setBounds(5, 36, 58, 13);
		jlbl_sifre.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(jlbl_sifre);
		
		JButton btn_giris = new JButton("G\u0130R\u0130\u015E");
		
		// Giriþ butonunun iþlevi kodlanmýþtýr. Týklandýðýnda þifre ve kullanýcý adý kontrol edilip doðruysa AdminPanel e yönlendirilecek. 
		btn_giris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = jtxt_kullanici_adi.getText();
		        String password = new String(sifre_alani.getPassword());
		        boolean  result = VeritabaniFonksiyonlar.Login(id, password);
		        if(result == true){
		            AdminPanel admin = new AdminPanel();
		            setVisible(false);
		            admin.setVisible(true);

		        }
		        else{
		         
		            sifre_alani.setText("");
		            JOptionPane.showMessageDialog(null, "Kullanýcý adý veya þifre yanlýþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        }
				
				
				
			}
		});
		btn_giris.setBounds(120, 79, 85, 21);
		panel_1.add(btn_giris);
		
		sifre_alani = new JPasswordField();
		
		//Þifremizi girdiðimiz yerde enter tuþuna bastýðýmýzda giriþ iþlemini gerçekleþtiriyor. 
		sifre_alani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = jtxt_kullanici_adi.getText();
		        String password = new String(sifre_alani.getPassword());
		        boolean  result = VeritabaniFonksiyonlar.Login(id, password);
		        if(result == true){
		            AdminPanel admin = new AdminPanel();
		            setVisible(false);
		            admin.setVisible(true);
		        }
		        else{
		         
		            sifre_alani.setText("");
		            JOptionPane.showMessageDialog(null, "Kullanýcý adý veya þifre yanlýþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        }
				
			}
		});
		sifre_alani.setBounds(109, 33, 96, 19);
		panel_1.add(sifre_alani);
	}
}
