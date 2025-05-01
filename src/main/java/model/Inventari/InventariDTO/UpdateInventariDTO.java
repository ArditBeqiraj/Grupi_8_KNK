package model.Inventari.InventariDTO;

public class UpdateInventariDTO {
    private int id;
    private int sasia;

    public UpdateInventariDTO(int id, int sasia) {
        this.id = id;
        this.sasia = sasia;
    }

    public int getProdukt_id() {
        return id;
    }

    public void setProdukt_id(int id) {
        this.id = id;
    }

    public int getSasia() {
        return sasia;
    }

    public void setSasia(int sasia) {
        this.sasia = sasia;
    }
}
