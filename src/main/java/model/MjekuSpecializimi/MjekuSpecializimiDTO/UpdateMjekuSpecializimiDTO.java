package model.MjekuSpecializimi.MjekuSpecializimiDTO;

public class UpdateMjekuSpecializimiDTO {
    private int id;
    private int specializimi_id;

    public UpdateMjekuSpecializimiDTO(int id,int specializimi_id){
        this.id=id;
        this.specializimi_id=specializimi_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpecializimi_id() {
        return specializimi_id;
    }

    public void setSpecializimi_id(int specializimi_id) {
        this.specializimi_id = specializimi_id;
    }
}
