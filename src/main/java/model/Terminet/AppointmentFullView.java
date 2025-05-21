package model.Terminet;

import java.sql.Date;

public class AppointmentFullView {
    private String emri;
    private String mbiemri;
    private Date data;
    private String statusi;
    private String dhoma;
    private String shenime;

    public AppointmentFullView(String emri, String mbiemri, Date data, String statusi, String dhoma, String shenime) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.data = data;
        this.statusi = statusi;
        this.dhoma = dhoma;
        this.shenime = shenime;
    }

    // Getters
    public String getEmri() { return emri; }
    public String getMbiemri() { return mbiemri; }
    public Date getData() { return data; }
    public String getStatusi() { return statusi; }
    public String getDhoma() { return dhoma; }
    public String getShenime() { return shenime; }
}
