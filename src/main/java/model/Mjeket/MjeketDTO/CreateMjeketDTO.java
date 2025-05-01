package model.Mjeket.MjeketDTO;

public class CreateMjeketDTO {
    private String emri;
    private String mbiemri;
    private String nr_licences;
    private String nr_telefoni;
    private String email;
    private String specializimi_kryesor;
    public CreateMjeketDTO(String emri, String mbiemri, String nr_licences, String nr_telefoni, String email, String specializimi_kryesor) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.nr_licences = nr_licences;
        this.nr_telefoni = nr_telefoni;
        this.email = email;
        this.specializimi_kryesor = specializimi_kryesor;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getNr_licences() {
        return nr_licences;
    }

    public String getNr_telefoni() {
        return nr_telefoni;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecializimi_kryesor() {
        return specializimi_kryesor;
    }

}
