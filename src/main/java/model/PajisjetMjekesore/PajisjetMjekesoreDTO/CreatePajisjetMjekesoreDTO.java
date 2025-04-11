package model.PajisjetMjekesore.PajisjetMjekesoreDTO;

public class CreatePajisjetMjekesoreDTO {
    private String emri_pajisjes;
    private String pershkrimi;
    private int sasia;
    private String gjendja;
    private Integer salla_id;

    public CreatePajisjetMjekesoreDTO(String emri_pajisjes, String pershkrimi, int sasia, String gjendja, Integer salla_id) {
        this.emri_pajisjes = emri_pajisjes;
        this.pershkrimi = pershkrimi;
        this.sasia = sasia;
        this.gjendja = gjendja;
        this.salla_id = salla_id;
    }

    public String getEmri_pajisjes() {
        return emri_pajisjes;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public int getSasia() {
        return sasia;
    }

    public String getGjendja() {
        return gjendja;
    }

    public Integer getSalla_id() {
        return salla_id;
    }
}
