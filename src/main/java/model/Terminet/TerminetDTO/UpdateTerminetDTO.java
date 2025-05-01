package model.Terminet.TerminetDTO;

import java.sql.Date;

public class UpdateTerminetDTO {
    private int id;
    private Date data;
    private String statusi;

    public UpdateTerminetDTO(int id, Date data, String statusi) {
        this.id = id;
        this.data = data;
        this.statusi = statusi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
