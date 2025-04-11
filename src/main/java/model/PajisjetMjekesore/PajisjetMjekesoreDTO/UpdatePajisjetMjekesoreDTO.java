package model.PajisjetMjekesore.PajisjetMjekesoreDTO;

public class UpdatePajisjetMjekesoreDTO {
    private int pajisja_id;
    private String emri_pajisjes;
    private String pershkrimi;
    private Integer sasia;
    private String gjendja;
    private Integer salla_id;

    public UpdatePajisjetMjekesoreDTO(int pajisja_id, String emri_pajisjes, String pershkrimi, Integer sasia, String gjendja, Integer salla_id) {
        this.pajisja_id = pajisja_id;
        this.emri_pajisjes = emri_pajisjes;
        this.pershkrimi = pershkrimi;
        this.sasia = sasia;
        this.gjendja = gjendja;
        this.salla_id = salla_id;
    }

    public int getPajisja_id() {
        return pajisja_id;
    }

    public String getEmri_pajisjes() {
        return emri_pajisjes;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public Integer getSasia() {
        return sasia;
    }

    public String getGjendja() {
        return gjendja;
    }

    public Integer getSalla_id() {
        return salla_id;
    }

    public void setPajisja_id(int pajisja_id) {
        this.pajisja_id = pajisja_id;
    }

    public void setEmri_pajisjes(String emri_pajisjes) {
        this.emri_pajisjes = emri_pajisjes;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public void setSasia(Integer sasia) {
        this.sasia = sasia;
    }

    public void setGjendja(String gjendja) {
        this.gjendja = gjendja;
    }

    public void setSalla_id(Integer salla_id) {
        this.salla_id = salla_id;
    }
}
