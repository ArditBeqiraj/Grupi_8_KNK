package model.Pacientet.PacientetDTO;

public class UpdatePacientetDTO {
    private int id;
    private String adresa;
    private String kontakti_emergjent;

    public enum Type { adresa, kontakti_emergjent }

    public UpdatePacientetDTO(int id, String update, Type type) {
        this.id = id;
        if (type == Type.adresa) {
            this.adresa = update;
        } else {
            this.kontakti_emergjent = update;
        }
    }

    public int getPacient_id() {
        return id;
    }

    public void setPacient_id(int id) {
        this.id = id;
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