import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VeritabaniFonksiyonlar {
	static Connection conn = null;
    Statement sta = null;
    static PreparedStatement pst= null;
    
	// ******** FONKSÝYONLAR KISMI BAÞLANGIÇ *********//
	
	
    
    //Admin login fonksiyonunu tanýmladýk.
    
    public static boolean Login(String id, String password){
        String sorgu = "Select * from admin where admin_name= ? and admin_password= ?";
       
        try {
            pst = conn.prepareStatement(sorgu);
            pst.setString(1,id);
            pst.setString(2, password);
            ResultSet rs= pst.executeQuery();
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
     
    }
    
    
    //personel login fonksiyonunu tanýmladýk.
    
    public static boolean PersonelLogin(String id, String password){
        String sorgu = "Select * from personel where personel_username= ? and personel_password=?"; 
       
        try {
            pst = conn.prepareStatement(sorgu);
            pst.setString(1,id);
            pst.setString(2, password);
            ResultSet rs= pst.executeQuery();
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
     
    }
  
    
    
    //musteri login fonksiyonu
    public static boolean MusteriLogin(String id, String password){
        String sorgu = "Select * from customer where customer_username= ? and customer_password=?"; 
       
        try {
            pst = conn.prepareStatement(sorgu);
            pst.setString(1,id);
            pst.setString(2, password);
            ResultSet rs= pst.executeQuery();
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
     
    }
    
    //Müþteriye þifre göndermek için kullanýcý adý email doðrulama fonksiyonu
    public static boolean MusteriSifre(String id, String email){
        String sorgu = "Select * from customer where customer_username= ? and customer_email=?"; 
       
        try {
            pst = conn.prepareStatement(sorgu);
            pst.setString(1,id);
            pst.setString(2, email);
            ResultSet rs= pst.executeQuery();
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
     
    }
    
    //otel ekleme fonksiyonu
    public void otelEkle(String otelad, String oteladres, String oteltelno, String otelemail) {
    	String sorgu = "INSERT into hotel(hotel_name, hotel_adress, hotel_contact_no, hotel_email) VALUES (?,?,?,?)";
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelad);
			pst.setString(2, oteladres);
			pst.setString(3, oteltelno);
			pst.setString(4, otelemail);
			pst.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    
    //otel güncelleme fonksiyonu
    
    public void otelGuncelle(int id, String otelad, String oteladres, String oteltelno, String otelemail){
        String sorgu = "UPDATE hotel SET  hotel_name=?,hotel_adress=?,hotel_contact_no=?, hotel_email=? WHERE hotel_id=?";
        
        try {
            pst = conn.prepareStatement(sorgu);
            pst.setString(1, otelad);
            pst.setString(2, oteladres);
            pst.setString(3, oteltelno);
            pst.setString(4, otelemail);
            pst.setInt(5, id);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
    }
    
    //otel silme fonksiyonu için optimizasyon fonksiyonu 
    public void sorguSecim(String sorgu, int otelid) {
    	 try {
             pst= conn.prepareStatement(sorgu);
             pst.setInt(1,otelid);
             pst.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    //otel silme fonksiyonu 
    public void otelSil(int id){
        String sorgu = "DELETE FROM hotel WHERE hotel_id=?";
        String sorguOda = "DELETE FROM room WHERE hotel_id=?";
        String sorguMusteri = "DELETE FROM customer WHERE hotel_id=?";
        String sorguYemek =  "DELETE FROM meal WHERE hotel_id=?";
        String sorguRezervasyon =  "DELETE FROM reservation WHERE hotel_id=?";
        String sorguPersonel =  "DELETE FROM personel WHERE hotel_id=?";
      
        sorguSecim(sorgu, id);
        sorguSecim(sorguOda, id);
        sorguSecim(sorguMusteri,id);
        sorguSecim(sorguYemek, id);
        sorguSecim(sorguRezervasyon,id);
        sorguSecim(sorguPersonel, id);
        
        /* yukarýdaki fonksiyonla aþaðýdaki iþlemleri optimize ettim. 
        try {
            pst= conn.prepareStatement(sorgu);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pst= conn.prepareStatement(sorguOda);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pst= conn.prepareStatement(sorguMusteri);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pst= conn.prepareStatement(sorguYemek);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pst= conn.prepareStatement(sorguRezervasyon);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pst= conn.prepareStatement(sorguPersonel);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }  */
        
    
    }
    
    
    //oda ekleme fonksiyonu
    
    public void odaEkle(String otelid, String odano, String odatip, String odafiyat) {
    	String sorgu = "INSERT into room (hotel_id, room_no, room_type, room_price)VALUES(?,?,?,?)";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelid);
			pst.setString(2, odano);
			pst.setString(3, odatip);
			pst.setString(4, odafiyat);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    
    //oda güncelleme fonksiyonu
    
    public void odaGuncelle(int odaid, String otelid, String odano, String odatip, String odafiyat) {
    	String sorgu = "UPDATE  room SET hotel_id=?, room_no=?, room_type=?, room_price=? WHERE room_id=?";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelid);
			pst.setString(2, odano);
			pst.setString(3, odatip);
			pst.setString(4, odafiyat);
			pst.setInt(5, odaid);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    
    //oda silme fonksiyonu
    
    public void odaSil(int id){
        String sorgu = "DELETE FROM room WHERE room_id=?";
        try {
            pst= conn.prepareStatement(sorgu);
            pst.setInt(1,id);
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    //musteri ekleme
    
    public void musteriEkle(String otelid, String musteriadi, String musteriemaili, String musteriadresi, String musterikadi, String musterisifre) {
    	String sorgu = "INSERT into customer (hotel_id, customer_name, customer_email, customer_adress, customer_username, customer_password)VALUES(?,?,?,?,?,?)";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelid);
			pst.setString(2, musteriadi);
			pst.setString(3, musteriemaili);
			pst.setString(4, musteriadresi);
			pst.setString(5, musterikadi);
			pst.setString(6, musterisifre);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    //musteri güncelleme
    
    public void musteriGuncelle(int musteriid, String otelid, String musteriadi, String musteriemaili, String musteriadresi, String musterikadi, String musterisifre) {
    	String sorgu = "UPDATE  customer SET hotel_id=?, customer_name=?, customer_email=?, customer_adress=?, customer_username=?, customer_password=? WHERE customer_id=?";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelid);
			pst.setString(2, musteriadi);
			pst.setString(3, musteriemaili);
			pst.setString(4, musteriadresi);
			pst.setString(5, musterikadi);
			pst.setString(6, musterisifre);
			pst.setInt(7, musteriid);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    
    //musteri silme
    
    public void musteriSil(int id){
        String sorgu = "DELETE FROM customer WHERE customer_id=?";
        try {
            pst= conn.prepareStatement(sorgu);
            pst.setInt(1,id);
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    //personel ekleme
    
    public void personelEkle(String otelid, String personelkadi, String personelsifre, String personeladi) {
    	String sorgu = "INSERT into personel (hotel_id, personel_username, personel_password, personel_name)VALUES(?,?,?,?)";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelid);
			pst.setString(2, personelkadi);
			pst.setString(3, personelsifre);
			pst.setString(4, personeladi);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    // personel düzenleme
    
    public void personelGuncelle(int personelid, String otelid, String personelkadi, String personelsifre, String personeladi) {
    	String sorgu = "UPDATE  personel SET hotel_id=?, personel_username=?, personel_password=?, personel_name=? WHERE personel_id=?";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelid);
			pst.setString(2, personelkadi);
			pst.setString(3, personelsifre);
			pst.setString(4, personeladi);
			pst.setInt(5, personelid);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    //personel silme
    
    public void personelSil(int id){
        String sorgu = "DELETE FROM personel WHERE personel_id=?";
        try {
            pst= conn.prepareStatement(sorgu);
            pst.setInt(1,id);
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
   //yemek ekleme
    
    public void yemekEkle(String otelid, String yemektip, String yemekfiyat) {
    	String sorgu = "INSERT into meal (hotel_id, meal_type, meal_price)VALUES(?,?,?)";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelid);
			pst.setString(2, yemektip);
			pst.setString(3, yemekfiyat);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    // yemek düzenleme
    
    public void yemekGuncelle(int yemekid, String otelid, String yemektip, String yemekfiyat) {
    	String sorgu = "UPDATE  meal SET hotel_id=?, meal_type=?, meal_price=? WHERE meal_id=?";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1, otelid);
			pst.setString(2, yemektip);
			pst.setString(3, yemekfiyat);
			pst.setInt(4, yemekid);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    //personel silme
    
    public void yemekSil(int id){
        String sorgu = "DELETE FROM meal WHERE meal_id=?";
        try {
            pst= conn.prepareStatement(sorgu);
            pst.setInt(1,id);
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    
    //rezervasyoon ekleme
    
    public void rezervasyonEkle(String cusid, String cusna, String cusem, String cusad, String hotid, String hotna, 
    							String datin, String datout, String romid, String romno,  String romty, String rompr,
    							String mese,String mepr, String total) {
    	
    	String sorgu = "INSERT INTO reservation( customer_id, customer_name, customer_email, customer_adress, hotel_id, hotel_name, date_in, date_out, room_id, room_no, room_type, room_price, meal_selection,meal_price, total_price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	try {
			pst = conn.prepareStatement(sorgu);
			pst.setString(1 ,cusid);
			pst.setString(2 ,cusna );
			pst.setString(3 ,cusem );
			pst.setString(4 ,cusad );
			pst.setString(5 ,hotid );
			pst.setString(6 ,hotna );
			pst.setString(7 ,datin );
			pst.setString(8 ,datout );
			pst.setString(9 ,romid );
			pst.setString(10 ,romno );
			pst.setString(11 ,romty );
			pst.setString(12 ,rompr );
			pst.setString(13 ,mese);
			pst.setString(14, mepr);
			pst.setString(15, total);
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			 Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    
    //rezervasyon düzenleme fonksiyonu
    public void rezervasyonDuzenle(int resid, String cusid, String cusna, String cusem, String cusad, String hotid, String hotna, 
			String datin, String datout, String romid, String romno,  String romty, String rompr,
			String mese,String mepr, String total) {

    		String sorgu = "UPDATE reservation SET customer_id=?, customer_name=?, customer_email=?, customer_adress=?, hotel_id=?, hotel_name=?, date_in=?, date_out=?, "
    			+ "room_id=?, room_no=?, room_type=?, room_price=?, meal_selection=?,meal_price=?, total_price=? WHERE reservation_id=?";
    		
    		try {
    			pst = conn.prepareStatement(sorgu);
    			pst.setString(1 ,cusid);
    			pst.setString(2 ,cusna );
    			pst.setString(3 ,cusem );
    			pst.setString(4 ,cusad );
    			pst.setString(5 ,hotid );
    			pst.setString(6 ,hotna );
    			pst.setString(7 ,datin );
    			pst.setString(8 ,datout );
    			pst.setString(9 ,romid );
    			pst.setString(10 ,romno );
    			pst.setString(11 ,romty );
    			pst.setString(12 ,rompr );
    			pst.setString(13 ,mese);
    			pst.setString(14, mepr);
    			pst.setString(15, total);
    			pst.setInt(16, resid);
    			pst.executeUpdate();

    		} catch (SQLException ex) {
    			// TODO Auto-generated catch block
    			Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
    		}
}
    //rezervasyon silme fonksiyonu
    public void rezervasyonSil(int id){
        String sorgu = "DELETE FROM reservation WHERE reservation_id=?";
        try {
            pst= conn.prepareStatement(sorgu);
            pst.setInt(1,id);
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    


    

	// ******** FONKSÝYONLAR KISMI BÝTÝÞ *********//
    
    
    //Veritabaný baðlantýsý yapýldý.Mysql sürücüsü eklendi.Veritabaný sýnýfýndan host, port, db_name bilgileri alýnarak veritabanýnýn localhost adresi verildi. 
    
    public VeritabaniFonksiyonlar() {
	        String url = "jdbc:mysql://"+Veritabani.host+":"+Veritabani.port+"/"+Veritabani.db_name+"?useUnicode=true&characterEncoding=UTF-8";
	        
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection(url,Veritabani.id,Veritabani.password);
	            System.out.println("Veritabanýna baðlanýldý. ");
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
	            System.out.println("Driver çalýþmadý :/");
	        } catch (SQLException ex) {
	            Logger.getLogger(VeritabaniFonksiyonlar.class.getName()).log(Level.SEVERE, null, ex);
	            System.out.println("Connection çalýþmadý :/");
	        }
	        
	        
	    }
	
	
	public static void main(String[] args) {
		VeritabaniFonksiyonlar fonk = new VeritabaniFonksiyonlar();

	}
}
