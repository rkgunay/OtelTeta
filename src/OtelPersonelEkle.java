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

public class OtelPersonelEkle extends JFrame {
	private JPanel contentPane;
	private JTextField jtxt_personel_adi;
	private JTextField jtxt_personel_kadi;
	private JTextField jtxt_personel_sifre;
	private JTable personel_tablo_personel;

	/**
	 * Launch the application.
	 */
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();

	private JTextField jtxt_otel_id;
	
	public void personelListele(String otelid) {
		try {
			String otel_id = otelid;
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM personel WHERE hotel_id="+otel_id;
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)personel_tablo_personel.getModel();
			tm.setRowCount(0);
			while (rs.next()) {
				   
				    String o[] = {rs.getString("personel_id".toString()), rs.getString("hotel_id"),
				    		rs.getString("personel_username"),rs.getString("personel_password"), rs.getString("personel_name")};
				    
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
					OtelPersonelEkle frame = new OtelPersonelEkle();
					frame.setVisible(true);
					String otelid = frame.jtxt_otel_id.getText();
					frame.personelListele(otelid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OtelPersonelEkle() {
		setTitle("Personel - Personel G\u00FCncelleme");
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
		panel_1.setBounds(10, 10, 190, 256);
		panel.add(panel_1);
		
		JButton btn_personel_ekle = new JButton("EKLE");
		btn_personel_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtelPanel panel = new OtelPanel();
				String otelid= jtxt_otel_id.getText();
				String personelkadi = jtxt_personel_kadi.getText();
				String personelsifre = jtxt_personel_sifre.getText();
				String  personeladi= jtxt_personel_adi.getText();
				
				fonk.personelEkle(otelid, personelkadi, personelsifre, personeladi);
				personelListele(otelid);
				
				
				JOptionPane.showMessageDialog(null, "Ekleme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			}
		});
		
		btn_personel_ekle.setBounds(93, 145, 85, 21);
		panel_1.add(btn_personel_ekle);
		
		jtxt_personel_adi = new JTextField();
		jtxt_personel_adi.setColumns(10);
		jtxt_personel_adi.setBounds(82, 116, 96, 19);
		panel_1.add(jtxt_personel_adi);
		
		JLabel jlbl_personel_adi = new JLabel("Ad:");
		jlbl_personel_adi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_personel_adi.setBounds(5, 119, 58, 13);
		panel_1.add(jlbl_personel_adi);
		
		JButton btn_personel_duzenle = new JButton("D\u00DCZENLE");
		btn_personel_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//oda listesini al
				int selectedrow = personel_tablo_personel.getSelectedRow();
				DefaultTableModel tmo = (DefaultTableModel)personel_tablo_personel.getModel();
				
				String personelkadi = jtxt_personel_kadi.getText();
				String personelsifre= jtxt_personel_sifre.getText();
				String personeladi = jtxt_personel_adi.getText();
				OtelPanel panel = new OtelPanel();
				String otelid = jtxt_otel_id.getText();
				   if(selectedrow==-1){
			            if(tmo.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tmo.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			            
			        	fonk.personelGuncelle(id, otelid, personelkadi, personelsifre, personeladi);
			            personelListele(otelid);
			            JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			
				
				
			}
		});
		btn_personel_duzenle.setBounds(93, 176, 85, 21);
		panel_1.add(btn_personel_duzenle);
		
		JButton btn_personel_sil = new JButton("S\u0130L");
		btn_personel_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = personel_tablo_personel.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)personel_tablo_personel.getModel();
				OtelPanel panel = new OtelPanel();
			      if(selectedrow==-1){
			            if(tm.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tm.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			            fonk.personelSil(id);	
			            personelListele(jtxt_otel_id.getText());
			            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			}
		});
		btn_personel_sil.setBounds(93, 207, 85, 21);
		panel_1.add(btn_personel_sil);
		
		JLabel jlbl_personel_kadi = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		jlbl_personel_kadi.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_personel_kadi.setBounds(5, 67, 58, 13);
		panel_1.add(jlbl_personel_kadi);
		
		jtxt_personel_kadi = new JTextField();
		jtxt_personel_kadi.setColumns(10);
		jtxt_personel_kadi.setBounds(82, 64, 96, 19);
		panel_1.add(jtxt_personel_kadi);
		
		JLabel jlbl_personel_sifre = new JLabel("\u015Eifre:");
		jlbl_personel_sifre.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_personel_sifre.setBounds(5, 93, 58, 13);
		panel_1.add(jlbl_personel_sifre);
		
		jtxt_personel_sifre = new JTextField();
		jtxt_personel_sifre.setColumns(10);
		jtxt_personel_sifre.setBounds(82, 90, 96, 19);
		panel_1.add(jtxt_personel_sifre);
		
		jtxt_otel_id = new JTextField();
		jtxt_otel_id.setEnabled(false);
		jtxt_otel_id.setEditable(false);
		jtxt_otel_id.setColumns(10);
		jtxt_otel_id.setBounds(82, 10, 96, 19);
		panel_1.add(jtxt_otel_id);
		
		JLabel jlbl_otel_id = new JLabel("Otel ID:");
		jlbl_otel_id.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_id.setBounds(5, 13, 58, 13);
		panel_1.add(jlbl_otel_id);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(210, 10, 356, 256);
		panel.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 356, 256);
		panel_2_1.add(scrollPane_1);
		
		personel_tablo_personel = new JTable();
		personel_tablo_personel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = personel_tablo_personel.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)personel_tablo_personel.getModel();
				jtxt_personel_kadi.setText(tm.getValueAt(selectedrow, 2).toString());
				jtxt_personel_sifre.setText(tm.getValueAt(selectedrow, 3).toString());
				jtxt_personel_adi.setText(tm.getValueAt(selectedrow, 4).toString());
				
			}
		});
		personel_tablo_personel.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Personel ID", "Otel ID", "Kullan\u0131c\u0131 Ad\u0131", "\u015Eifre", "Ad\u0131"
			}
		));
		scrollPane_1.setViewportView(personel_tablo_personel);
	}

	public JTextField setJtxt_otel_id(String id) {
		jtxt_otel_id.setText(id);
		return jtxt_otel_id;
	}
}
