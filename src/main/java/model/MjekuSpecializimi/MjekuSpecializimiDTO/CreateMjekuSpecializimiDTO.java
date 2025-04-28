package model.MjekuSpecializimi.MjekuSpecializimiDTO;

public class CreateMjekuSpecializimiDTO {
    private int mjeku_id;
    private int specializimi_id;

    public CreateMjekuSpecializimiDTO(int mjeku_id, int specializimi_id) {
        this.mjeku_id = mjeku_id;
        this.specializimi_id = specializimi_id;
    }

    public int getMjeku_id() {
        return mjeku_id;
    }

    public void setMjeku_id(int mjeku_id) {
        this.mjeku_id = mjeku_id;
    }

    public int getSpecializimi_id() {
        return specializimi_id;
    }

    public void setSpecializimi_id(int specializimi_id) {
        this.specializimi_id = specializimi_id;
    }
}
