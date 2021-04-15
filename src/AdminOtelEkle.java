import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminOtelEkle extends JFrame {
	
	
	
	private JPanel contentPane;
	private JTextField jtxt_otel_adi;
	private JTextField jtxt_otel_adresi;
	private JTextField jtxt_otel_telefon;
	private JTextField jtxt_otel_email;
	private JTable otel_tablo;
	

	/**
	 *                 the application.
	 */
	
	
	
	public void otelListele(){
		try {
			VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM hotel";
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)otel_tablo.getModel();
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
	
	
	
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					AdminOtelEkle frame = new AdminOtelEkle();
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
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();
	AdminPanel admin = new AdminPanel();
	
	public AdminOtelEkle() {
		
		setTitle("Y\u00F6netici - Otel G\u00FCncelleme");
		
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 190, 235);
		panel_1.setLayout(null);
		
		JLabel jlbl_otel_adi = new JLabel("Otel Ad\u0131:");
		jlbl_otel_adi.setBounds(5, 11, 58, 13);
		jlbl_otel_adi.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(jlbl_otel_adi);
		
		jtxt_otel_adi = new JTextField();
		jtxt_otel_adi.setBounds(82, 8, 96, 19);
		jtxt_otel_adi.setColumns(10);
		panel_1.add(jtxt_otel_adi);
		
		JButton btn_otel_ekle = new JButton("EKLE");
		btn_otel_ekle.setBounds(93, 145, 85, 21);
		btn_otel_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String otelad = jtxt_otel_adi.getText();
				String oteladres = jtxt_otel_adresi.getText();
				String oteltelno = jtxt_otel_telefon.getText();
				String otelemail = jtxt_otel_email.getText();
				
				fonk.otelEkle(otelad, oteladres, oteltelno, otelemail);
				
				jtxt_otel_adi.setText("");
				jtxt_otel_adresi.setText("");
				jtxt_otel_telefon.setText("");
				jtxt_otel_email.setText("");
				
				otelListele();
			}
		});
		
		
		
		
		
		
		panel_1.add(btn_otel_ekle);
		
		jtxt_otel_adresi = new JTextField();
		jtxt_otel_adresi.setBounds(82, 34, 96, 19);
		jtxt_otel_adresi.setColumns(10);
		panel_1.add(jtxt_otel_adresi);
		
		JLabel jlbl_otel_adresi = new JLabel("Otel Adresi:");
		jlbl_otel_adresi.setBounds(5, 37, 58, 13);
		jlbl_otel_adresi.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(jlbl_otel_adresi);
		
		jtxt_otel_telefon = new JTextField();
		jtxt_otel_telefon.setBounds(82, 60, 96, 19);
		jtxt_otel_telefon.setColumns(10);
		panel_1.add(jtxt_otel_telefon);
		
		JLabel jlbl_otel_telefon = new JLabel("Otel Tel No:");
		jlbl_otel_telefon.setBounds(5, 63, 58, 13);
		jlbl_otel_telefon.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(jlbl_otel_telefon);
		
		jtxt_otel_email = new JTextField();
		jtxt_otel_email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String otelad = jtxt_otel_adi.getText();
					String oteladres = jtxt_otel_adresi.getText();
					String oteltelno = jtxt_otel_telefon.getText();
					String otelemail = jtxt_otel_email.getText();
					
					fonk.otelEkle(otelad, oteladres, oteltelno, otelemail);
					
					jtxt_otel_adi.setText("");
					jtxt_otel_adresi.setText("");
					jtxt_otel_telefon.setText("");
					jtxt_otel_email.setText("");
					
					otelListele();
			
			}});
		jtxt_otel_email.setBounds(82, 86, 96, 19);
		jtxt_otel_email.setColumns(10);
		panel_1.add(jtxt_otel_email);
		
		JLabel jlbl_otel_email = new JLabel("Otel Email:");
		jlbl_otel_email.setBounds(5, 89, 58, 13);
		jlbl_otel_email.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(jlbl_otel_email);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(209, 18, 354, 225);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		panel.setLayout(null);
		panel.add(panel_1);
		
		JButton btn_otel_duzenle = new JButton("D\u00DCZENLE");
		btn_otel_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String otelad = jtxt_otel_adi.getText();
				String oteladres = jtxt_otel_adresi.getText();
				String oteltelno = jtxt_otel_telefon.getText();
				String otelemail = jtxt_otel_email.getText();
				
				
				int selectedrow= otel_tablo.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)otel_tablo.getModel();
		        if(selectedrow==-1){
		            if(tm.getRowCount()==0){
		            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
		        }else {
		        	
		        	int id = Integer.parseInt(tm.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
		            fonk.otelGuncelle(id, otelad, oteladres, oteltelno, otelemail);
		            otelListele();
		            
		            JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        
		        }
				
			}
		});
		
		
		btn_otel_duzenle.setBounds(93, 176, 85, 21);
		panel_1.add(btn_otel_duzenle);
		
		JButton btn_otel_sil = new JButton("S\u0130L");
		btn_otel_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow= otel_tablo.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)otel_tablo.getModel();
		        if(selectedrow==-1){
		            if(tm.getRowCount()==0){
		            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
		        }else {
		        	
		        	int id = Integer.parseInt(tm.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
		            fonk.otelSil(id);
		            otelListele();
		            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
		        
		        }
				
				
			}
		});
		btn_otel_sil.setBounds(93, 207, 85, 21);
		panel_1.add(btn_otel_sil);
		panel.add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		otel_tablo = new JTable();
		otel_tablo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow= otel_tablo.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)otel_tablo.getModel();
				
				
				jtxt_otel_adi.setText(tm.getValueAt(selectedrow,1).toString());
				jtxt_otel_adresi.setText(tm.getValueAt(selectedrow,2).toString());
				jtxt_otel_telefon.setText(tm.getValueAt(selectedrow,3).toString());
				jtxt_otel_email.setText(tm.getValueAt(selectedrow,4).toString());
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
		
		
			
	}
	
	
}
