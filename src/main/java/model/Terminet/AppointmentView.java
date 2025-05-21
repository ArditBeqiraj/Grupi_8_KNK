package model.Terminet;

public class AppointmentView {
    private String emri;
    private String mbiemri;
    private String statusi;

    public AppointmentView(String emri, String mbiemri, String statusi) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.statusi = statusi;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getStatusi() {
        return statusi;
    }
}