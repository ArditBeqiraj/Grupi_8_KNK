package model.Pacientet.PacientetDTO;

public class UpdatePacientetDTO {
    private int id;
    private String adresa;
    private String kontaktiEmergjent;

    public enum Type { adresa, kontaktiEmergjent}

    public UpdatePacientetDTO(int id, String update, Type type) {
        this.id = id;
        if (type == Type.adresa) {
            this.adresa = update;
        } else {
            this.kontaktiEmergjent = update;
        }
    }

    public int getPatientid() {
        return id;
    }

    public void setPacientId(int id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontaktiEmergjent() {
        return kontaktiEmergjent;
    }

    public void setKontaktiEmergjent(String kontaktiEmergjent) {
        this.kontaktiEmergjent = kontaktiEmergjent;
    }
}