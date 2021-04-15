import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.lang.System.Logger;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OtelYemekEkle extends JFrame {

	private JPanel contentPane;
	private JTextField jtxt_yemek_tip;
	private JTextField jtxt_yemek_fiyat;
	private JTable yemek_tablo_yemek;

	/**
	 * Launch the application.
	 */
	VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();

	private JTextField jtxt_otel_id;

	
	public void yemekListele(String otelid) {
		try {
			String otel_id = otelid;
			Statement st = fonk.conn.createStatement();
			String query = "SELECT * FROM meal WHERE hotel_id="+otel_id;
			ResultSet rs = st.executeQuery(query);
			DefaultTableModel tm = (DefaultTableModel)yemek_tablo_yemek.getModel();
			tm.setRowCount(0);
	
			while (rs.next()) {
				   
				    String o[] = {rs.getString("meal_id".toString()), rs.getString("hotel_id"),
				    		rs.getString("meal_type"),rs.getString("meal_price")};
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
					OtelYemekEkle frame = new OtelYemekEkle();
					
					String otelid = frame.jtxt_otel_id.getText();
					frame.yemekListele(otelid);
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
	public OtelYemekEkle() {
		setTitle("Personel - Yemek G\u00FCncelleme");
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
		panel_1.setBounds(10, 10, 190, 235);
		panel.add(panel_1);
		
		JButton btn_yemek_ekle = new JButton("EKLE");
		btn_yemek_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String otelid= jtxt_otel_id.getText();
				String yemektip = jtxt_yemek_tip.getText();
				String yemekfiyat = jtxt_yemek_fiyat.getText();
			
				
				fonk.yemekEkle(otelid, yemektip, yemekfiyat);
				
				yemekListele(otelid);
				
				
				
			}
		});
		btn_yemek_ekle.setBounds(93, 145, 85, 21);
		panel_1.add(btn_yemek_ekle);
		
		JButton btn_yemek_duzenle = new JButton("D\u00DCZENLE");
		btn_yemek_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//oda listesini al
				int selectedrow = yemek_tablo_yemek.getSelectedRow();
				DefaultTableModel tmo = (DefaultTableModel)yemek_tablo_yemek.getModel();
				
				String yemektip = jtxt_yemek_tip.getText();
				String yemekfiyat = jtxt_yemek_fiyat.getText();
				String otelid = jtxt_otel_id.getText();
				
				
				   if(selectedrow==-1){
			            if(tmo.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tmo.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.

			        	fonk.yemekGuncelle(id, otelid, yemektip, yemekfiyat);
			            yemekListele(otelid);
			            
			     
			            JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			
				
				
			}
		});
		btn_yemek_duzenle.setBounds(93, 176, 85, 21);
		panel_1.add(btn_yemek_duzenle);
		
		JButton btn_yemek_sil = new JButton("S\u0130L");
		btn_yemek_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = yemek_tablo_yemek.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)yemek_tablo_yemek.getModel();
				
			      if(selectedrow==-1){
			            if(tm.getRowCount()==0){
			            	JOptionPane.showMessageDialog(null, "Liste boþ!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			            }else { JOptionPane.showMessageDialog(null, "Seçim yapýnýz!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);}
			        }else {
			        	
			        	int id = Integer.parseInt(tm.getValueAt(selectedrow,0).toString()); //tablodaki id deðeri string ama veritabanýnda int tipinde. burada tablodaki deðeri alýnýp int e çevirildi.
			            fonk.yemekSil(id);	
			            yemekListele(jtxt_otel_id.getText());
			        
			            JOptionPane.showMessageDialog(null, "Silme Baþarýlý!", "SÝSTEM MESAJI", JOptionPane.OK_OPTION);
			        
			        }
				
				
			}
		});
		btn_yemek_sil.setBounds(93, 207, 85, 21);
		panel_1.add(btn_yemek_sil);
		
		JLabel jlbl_yemek_tip = new JLabel("Yemek Tipi:");
		jlbl_yemek_tip.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_yemek_tip.setBounds(10, 71, 67, 13);
		panel_1.add(jlbl_yemek_tip);
		
		jtxt_yemek_tip = new JTextField();
		jtxt_yemek_tip.setColumns(10);
		jtxt_yemek_tip.setBounds(87, 68, 96, 19);
		panel_1.add(jtxt_yemek_tip);
		
		JLabel jlbl_yemek_fiyat = new JLabel("Yemek Fiyat\u0131:");
		jlbl_yemek_fiyat.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_yemek_fiyat.setBounds(10, 97, 77, 13);
		panel_1.add(jlbl_yemek_fiyat);
		
		jtxt_yemek_fiyat = new JTextField();
		jtxt_yemek_fiyat.setColumns(10);
		jtxt_yemek_fiyat.setBounds(87, 94, 96, 19);
		panel_1.add(jtxt_yemek_fiyat);
		
		JLabel jlbl_otel_id = new JLabel("Otel ID:");
		jlbl_otel_id.setHorizontalAlignment(SwingConstants.LEFT);
		jlbl_otel_id.setBounds(10, 13, 58, 13);
		panel_1.add(jlbl_otel_id);
		
		jtxt_otel_id = new JTextField();
		jtxt_otel_id.setEnabled(false);
		jtxt_otel_id.setEditable(false);
		jtxt_otel_id.setColumns(10);
		jtxt_otel_id.setBounds(87, 10, 96, 19);
		panel_1.add(jtxt_otel_id);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(210, 10, 356, 235);
		panel.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 356, 235);
		panel_2_1.add(scrollPane_1);
		
		yemek_tablo_yemek = new JTable();
		yemek_tablo_yemek.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = yemek_tablo_yemek.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel)yemek_tablo_yemek.getModel();
				jtxt_yemek_tip.setText(tm.getValueAt(selectedrow, 2).toString());
				jtxt_yemek_fiyat.setText(tm.getValueAt(selectedrow, 3).toString());
			
				
			}
		});
		yemek_tablo_yemek.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Yemek ID", "Otel ID", "Yemek Tipi", "Yemek Fiyat\u0131"
			}
		));
		scrollPane_1.setViewportView(yemek_tablo_yemek);
	}
	public JTextField setJtxt_otel_id(String otelid) {
		jtxt_otel_id.setText(otelid);
		return jtxt_otel_id;
	}
}
