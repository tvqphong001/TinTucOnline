package phongson.com.model;

public class LichSuDoc {
    String idUser,linkTinTuc,hinhanh,tieude,nguontin;

    public LichSuDoc(String idUser, String linkTinTuc, String hinhanh, String tieude, String nguontin) {
        this.idUser = idUser;
        this.linkTinTuc = linkTinTuc;
        this.hinhanh = hinhanh;
        this.tieude = tieude;
        this.nguontin = nguontin;
    }

    public LichSuDoc() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLinkTinTuc() {
        return linkTinTuc;
    }

    public void setLinkTinTuc(String linkTinTuc) {
        this.linkTinTuc = linkTinTuc;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNguontin() {
        return nguontin;
    }

    public void setNguontin(String nguontin) {
        this.nguontin = nguontin;
    }
}
