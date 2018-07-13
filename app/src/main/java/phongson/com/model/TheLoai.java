package phongson.com.model;

public class TheLoai {
    String id,ten,linkTheloai,nguonbao;

    public TheLoai() {
    }

    public TheLoai(String id, String ten, String linkTheloai, String nguonbao) {
        this.id = id;
        this.ten = ten;
        this.linkTheloai = linkTheloai;
        this.nguonbao = nguonbao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLinkTheloai() {
        return linkTheloai;
    }

    public void setLinkTheloai(String linkTheloai) {
        this.linkTheloai = linkTheloai;
    }

    public String getNguonbao() {
        return nguonbao;
    }

    public void setNguonbao(String nguonbao) {
        this.nguonbao = nguonbao;
    }
}
