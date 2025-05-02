package model.Perdoruesi.PerdoruesiDTO;

public class LoginPerdoruesiDto {
    private String email;
    private String password;

    public LoginPerdoruesiDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
