package com.example.appdatphongkhachsan.ultil;

public class Server {
    public static String localhost = "192.168.1.29"; //"192.168.1.4";
    public static String DuongDanKhachSan = "http://"+ localhost + ":80/serverappks/getkhachsan.php";
    public static String DuongDanLoaiPhong = "http://"+ localhost + ":80/serverappks/getloaiphong.php";
    public static String DuongDanViTriPhong = "http://"+ localhost + ":80/serverappks/getvitriphong.php";
    public static String DuongDanPhong = "http://"+ localhost + ":80/serverappks/getphong.php";
    public static String DuongDanInsertKhachHang = "http://"+ localhost + ":80/serverappks/insert_thongtinkhachhang.php";
    public static String DuongDanDatPhong = "http://"+ localhost + ":80/serverappks/insert_datphong.php";
    public static String DuongDanLichSuPhong = "http://"+ localhost + ":80/serverappks/getdatphong.php";
}
