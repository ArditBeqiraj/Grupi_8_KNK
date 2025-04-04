package model.Pacientet.PacientetDTO;

public class UpdatePacientetDTO {
    private int pacient_id;
    private String adresa;
    private String kontakti_emergjent;

    public enum Type { adresa, kontakti_emergjent }

    public UpdatePacientetDTO(int pacient_id, String update, Type type) {
        this.pacient_id = pacient_id;
        if (type == Type.adresa) {
            this.adresa = update;
        } else {
            this.kontakti_emergjent = update;
        }
    }

    public int getPacient_id() {
        return pacient_id;
    }

    public void setPacient_id(int pacient_id) {
        this.pacient_id = pacient_id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontakti_emergjent() {
        return kontakti_emergjent;
    }

    public void setKontakti_emergjent(String kontakti_emergjent) {
        this.kontakti_emergjent = kontakti_emergjent;
    }
}