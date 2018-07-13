package phongson.com.model;

public class ChuyenMuc {
    String idUser,tenchuyenmuc;

    int seleted;

    public ChuyenMuc(String idUser, String tenchuyenmuc, int seleted) {
        this.idUser = idUser;
        this.tenchuyenmuc = tenchuyenmuc;
        this.seleted = seleted;
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

    public int getSeleted() {
        return seleted;
    }

    public void setSeleted(int seleted) {
        this.seleted = seleted;
    }
}
