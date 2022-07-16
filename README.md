# OtelTeta

İnsanların otellere rezervasyon yapabileceği bir masaüstü uygulamasıdır.

# Proje Tanımı

Otel Teta, otel rezervasyon işlemlerimizi gerçekleştirebileceğimiz bir masaüstü uygulamasıdır. 
3 farklı kullanıcı tipi vardır. Yönetici(Admin), en yetkili kullanıcıdır. Yeni oteller tanımlayabilir,
tanımladığı otellere personel, müşteri, yemek tipi ve oda ekleyebilir, güncelleyebilir 
ve rezervasyon işlemlerini gerçekleştirebilir. Personel tipi kullanıcılar ise 
sadece kayıtlı olduğu otelin bilgilerini görebilir ve değiştirebilirler.
Müşteri tipi kullanıcı otellere rezervasyon işlemleri yapabilir. 
Kendi kişisel bilgilerini veya rezervasyon bilgilerini güncelleyebilirler. 


# Geliştirme Ortamı ve Kullanılan Teknolojiler

Veritabanı için MySQL dili kullanılmıştır. Programın kodlanması Java dili ile yapılmıştır. 
Geliştirme ortamı olarak Eclipse kullanılmıştır. 
Program için JCalendar, MySQL Driver kütüphaneleri projeye dahil edilmiştir. 
Localhost'ta çalışır. Veritabanı dosyasını projeye ekledim.

# Kurulum

Xampp kurulmalı. PhpMyAdmin'e giriş yapıldıktan sonra beta_test adında veritabanı oluşturulmalıdır.
Sonra bu veritabanına proje dosyasında verdiğim **dbdosyasi.sql** dosyası içe aktarılmalıdır.. Program 3 farklı uygulamadan açılır. 
Admin, Personel ve Müşteri için farklı giriş uygulaması vardır. Bu dosyaları dist klasöründe bulabilirsiniz.
Admin uygulamasından "**admin**" kullanıcı adı ve "**admin**" şifresiyle giriş yapabilirsiniz. 
.jar uzantılı açılmıyorsa .bat uzantılı dosyadan deneyebilirsiniz.  
Veritabanı bağlantısında sorun yaşıyorsanız benim kullandığım bağlantı bilgileriyle 
sizin localhost'unuzdaki bağlantı bilgileri farklı olabilir. 
Veritabanı sınıfındaki bağlantı bilgilerinizi kendi bilgilerinizle eşleştirmeniz gerekebilir. 


# Genel Arayüz Tasarımı

Yönetici giriş yaptıktan sonra bu sayfaya yönlendirilir.  <br/> 
<img src="https://github.com/rkgunay/OtelTeta/blob/main/ekranGoruntuleri/yonetici.png" width="600">  <br/> <br/>


Personel giriş yaptıktan sonra bu sayfaya yönlendirilir.  <br/> 
<img src="https://github.com/rkgunay/OtelTeta/blob/main/ekranGoruntuleri/personel.png" width="600">  <br/> <br/>


Müşteri giriş yaptıktan sonra bu sayfaya yönlendirilir.  <br/> 
<img src="https://github.com/rkgunay/OtelTeta/blob/main/ekranGoruntuleri/musteri.png" width="600">  <br/> <br/>








