package phongson.com.model;

public class User {
    String id,ten,email;

    public User() {
    }

    public User(String id, String ten, String email) {
        this.id = id;
        this.ten = ten;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
