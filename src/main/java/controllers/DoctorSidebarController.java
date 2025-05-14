package controllers;

import javafx.fxml.FXML;
import services.SceneManager;

public class DoctorSidebarController {
    @FXML
    private void handleDoctorProfile() throws Exception{
        SceneManager.load("/app/DoctorProfile.fxml");
    }

    @FXML
    private void handleStaffHome() throws Exception{
        SceneManager.load("/app/DoctorHome.fxml");
    }
}
