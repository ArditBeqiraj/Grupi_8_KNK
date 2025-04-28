package model.Mjeket.MjeketDTO;

public class UpdateMjeketDTO {
    private int id;
    private String statusi;

    public UpdateMjeketDTO(int id,String statusi){
        this.id=id;
        this.statusi=statusi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
