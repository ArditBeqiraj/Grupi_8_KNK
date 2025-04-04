package model.Sherbimet.SherbimetDTO;

public class UpdateSherbimDTO {
    private int sherbimi_id;
    private double cmimi;

    public UpdateSherbimDTO(int sherbimi_id, double cmimi) {
        this.sherbimi_id = sherbimi_id;
        this.cmimi = cmimi;
    }

    public int getSherbimi_id() {
        return sherbimi_id;
    }

    public void setSherbimi_id(int sherbimi_id) {
        this.sherbimi_id = sherbimi_id;
    }

    public double getCmimi() {
        return cmimi;
    }

    public void setCmimi(double cmimi) {
        this.cmimi = cmimi;
    }
}