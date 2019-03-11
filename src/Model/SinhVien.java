package Model;

public class SinhVien {
    private String taiKhoang;
    private String msv;
    private String matKhau;
    private String maMuon;


    public SinhVien(String taiKhoang, String msv, String matKhau,String maMuon) {
        this.taiKhoang = taiKhoang;
        this.msv = msv;
        this.matKhau = matKhau;
        this.maMuon = maMuon;
    }

    public String getMaMuon() {
        return maMuon;
    }

    public void setMaMuon(String maMuon) {
        this.maMuon = maMuon;
    }

    public String getTaiKhoang() {
        return taiKhoang;
    }

    public void setTaiKhoang(String taiKhoang) {
        this.taiKhoang = taiKhoang;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
