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

Xampp kurulmalı. PhpMyAdmin'e giriş yapıldıktan sonra proje dosyasında verdiğim 
**beta_test.sql** dosyası içe aktarılmalıdır.. Program 3 farklı uygulamadan açılır. 
Admin, Personel ve Müşteri için farklı giriş uygulaması vardır. Bu dosyaları dist klasöründe bulabilirsiniz.
Admin uygulamasından "**admin**" kullanıcı adı ve "**admin**" şifresiyle giriş yapabilirsiniz. 
.jar uzantılı açılmıyorsa .bat uzantılı dosyadan deneyebilirsiniz.  
Veritabanı bağlantısında sorun yaşıyorsanız benim kullandığım bağlantı bilgileriyle 
sizin localhost'unuzdaki bağlantı bilgileri farklı olabilir. 
Veritabanı sınıfındaki bağlantı bilgilerinizi kendi bilgilerinizle eşleştirmeniz gerekebilir. 

# Genel Arayüz Tasarımı


# Düzeltilmesi Gereken Hatalar ve Geliştirilebilecek Yerler

1.Sistem aynı kullanıcı adıyla farklı müşteri ve personel hesapları açmaya izin veriyor. 
Eğer başka birisiyle kullanıcı adı ve şifre aynı olursa başka birinin hesabına giriliyor. 
Email bilgisi için de çatışma durumu söz konusu olabilir. 

2.Yönetici Otel Güncelleme sayfasında otel ekledikten sonra yönetici ana sayfasına geri döndüğünde 
otel cb_box' ındaki liste güncellenmiyor. Güncelleme alınabilmesi için yöneticinin çıkıp tekrar giriş yapması gerekiyor. 

3.Ücret kısımlarına sayısal olmayan değerler girilebiliyor. 
Bu da toplam hesaplanmaya çalışıldığında hata yaratıyor. textfield'lara veri tipi kontrolü gerekiyor.
Aynı şekilde girilen değerin String'e çevrilerek veritabanına atılması gerekiyor. 

4.Optimize edilebilecek yerler var. Mesala bazı sayfalara VeritabanıFonksiyonları sınıfını birden çok kez tanıttım. 
Kodun başında bir kez tanıtmam yeterdi. Daha "DRY" bir kod yazılabilirdi. 

5.Veritabanından çektiğim verileri bir sınıfın objesi olarak üretip o şekilde kullanabilirdim.
Kod daha esnek ve güncellenmesi daha kolay olurdu. 

6.Arayüz esnek değil. Program arayüzü, kullanılan sistemin çözünürlüğüne göre daha büyük ya da daha küçük gözükebiliyor. 
En büyük çözünürlüğe sahip arayüz 800, 600 büyüklüğünde. 
Düşük çözünürlüklü bilgisayarlarda sıkıntı yaratmasa da yüksek çözünürlüklü bilgisayarlarda kullanıcı memnuniyetini düşürür. 
Aksi bir senaryoda programın arayüzünü yüksek çözünürlükte yapsaydım,
düşük çözünürlüklü bilgisayarlarda ekrandan taşma sorunu yaşanırdı. 



