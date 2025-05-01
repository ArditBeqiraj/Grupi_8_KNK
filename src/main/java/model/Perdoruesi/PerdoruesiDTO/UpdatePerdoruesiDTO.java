package model.Perdoruesi.PerdoruesiDTO;

public class UpdatePerdoruesiDTO {
    private int id;
    private String email;
    private String telefoni;
    private String password;

    public enum Type { EMAIL, TELEFONI, PASSWORD }

    public UpdatePerdoruesiDTO(int id, String update, Type type) {
        this.id = id;
        if (type == Type.EMAIL) {
            this.email = update;
        } else if (type == Type.TELEFONI){
            this.telefoni = update;
        } else {
            this.password = update;
        }
    }

    public int getUser_id() {
        return id;
    }

    public void setUser_id(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
