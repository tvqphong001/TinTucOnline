package phongson.com.model;

public class ChuyenMuc {
    String idUser,tenchuyenmuc;

    public ChuyenMuc() {
    }

    public ChuyenMuc(String idUser, String tenchuyenmuc) {
        this.idUser = idUser;
        this.tenchuyenmuc = tenchuyenmuc;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTenchuyenmuc() {
        return tenchuyenmuc;
    }

    public void setTenchuyenmuc(String tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
    }
}
