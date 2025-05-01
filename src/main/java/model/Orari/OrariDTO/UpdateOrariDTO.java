package model.Orari.OrariDTO;

import java.sql.Timestamp;

public class UpdateOrariDTO {

    private int id;
    private Timestamp fillimi_ndrrimit;
    private Timestamp perfundimi_ndrrimit;

    public enum Type { FILLIMI, PERFUNDIMI }

    public UpdateOrariDTO(int id, Timestamp timestamp, Type type) {
        this.id = id;
        if (type == Type.FILLIMI) {
            this.fillimi_ndrrimit = timestamp;
        } else {
            this.perfundimi_ndrrimit = timestamp;
        }
    }

    public int getOrari_id() {
        return id;
    }

    public void setOrari_id(int id) {
        this.id = id;
    }

    public Timestamp getFillimi_ndrrimit() {
        return fillimi_ndrrimit;
    }

    public void setFillimi_ndrrimit(Timestamp fillimi_ndrrimit) {
        this.fillimi_ndrrimit = fillimi_ndrrimit;
    }

    public Timestamp getPerfundimi_ndrrimit() {
        return perfundimi_ndrrimit;
    }

    public void setPerfundimi_ndrrimit(Timestamp perfundimi_ndrrimit) {
        this.perfundimi_ndrrimit = perfundimi_ndrrimit;
    }
}
