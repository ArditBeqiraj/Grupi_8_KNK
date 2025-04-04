package model.Inventari.InventariDTO;

public class UpdateInventariDTO {
    private int produkt_id;
    private int sasia;

    public UpdateInventariDTO(int produkt_id, int sasia) {
        this.produkt_id = produkt_id;
        this.sasia = sasia;
    }

    public int getProdukt_id() {
        return produkt_id;
    }

    public void setProdukt_id(int produkt_id) {
        this.produkt_id = produkt_id;
    }

    public int getSasia() {
        return sasia;
    }

    public void setSasia(int sasia) {
        this.sasia = sasia;
    }
}
