package phongson.com.model;

public class TinTuc {
    String tieude,link,idTheloai,ngaydang,link_hinh,ngonbao,luotxen,luotbinhluan;

    public TinTuc(String tieude, String link, String idTheloai, String ngaydang, String link_hinh, String ngonbao, String luotxen, String luotbinhluan) {
        this.tieude = tieude;
        this.link = link;
        this.idTheloai = idTheloai;
        this.ngaydang = ngaydang;
        this.link_hinh = link_hinh;
        this.ngonbao = ngonbao;
        this.luotxen = luotxen;
        this.luotbinhluan = luotbinhluan;
    }

    public TinTuc() {
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIdTheloai() {
        return idTheloai;
    }

    public void setIdTheloai(String idTheloai) {
        this.idTheloai = idTheloai;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getLink_hinh() {
        return link_hinh;
    }

    public void setLink_hinh(String link_hinh) {
        this.link_hinh = link_hinh;
    }

    public String getNgonbao() {
        return ngonbao;
    }

    public void setNgonbao(String ngonbao) {
        this.ngonbao = ngonbao;
    }

    public String getLuotxen() {
        return luotxen;
    }

    public void setLuotxen(String luotxen) {
        this.luotxen = luotxen;
    }

    public String getLuotbinhluan() {
        return luotbinhluan;
    }

    public void setLuotbinhluan(String luotbinhluan) {
        this.luotbinhluan = luotbinhluan;
    }
}
