package phongson.com.model;

public class BinhLuan {
    String id,idUer,linkTinTuc, noidung;

    public BinhLuan(String id, String idUer, String linkTinTuc, String noidung) {
        this.id = id;
        this.idUer = idUer;
        this.linkTinTuc = linkTinTuc;
        this.noidung = noidung;
    }

    public BinhLuan() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUer() {
        return idUer;
    }

    public void setIdUer(String idUer) {
        this.idUer = idUer;
    }

    public String getLinkTinTuc() {
        return linkTinTuc;
    }

    public void setLinkTinTuc(String linkTinTuc) {
        this.linkTinTuc = linkTinTuc;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
