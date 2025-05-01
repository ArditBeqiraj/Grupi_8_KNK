package model.Mjeket.MjeketDTO;

public class UpdateMjeketDTO {
    private int id;
    private String nr_telefoni;
    private String email;

    public UpdateMjeketDTO(int id, String nr_telefoni, String email){
        this.id=id;
        this.nr_telefoni = nr_telefoni;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNr_telefoni() {
        return nr_telefoni;
    }

    public void setNr_telefoni(String nr_telefoni) {
        this.nr_telefoni = nr_telefoni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
