package model.Perdoruesi.PerdoruesiDTO;

public class PerdoruesiChangePasswordDto {
    private String oldPassword;
    private String newPassword;

    public PerdoruesiChangePasswordDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
