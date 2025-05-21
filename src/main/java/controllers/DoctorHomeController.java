package controllers;

import Database.DBConnector;
import Repository.MjeketRepository;
import app.Navigator;
import app.SessionManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Terminet.AppointmentView;
import model.Terminet.Terminet;
import services.LanguageManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoctorHomeController implements Initializable {
    @FXML
    private TableColumn<AppointmentView, String> colEmri;

    @FXML
    private TableColumn<AppointmentView, String> colStatusi;

    @FXML
    private TableColumn<AppointmentView, String> colMbiemri;

    @FXML
    private DatePicker datePicker;

    @FXML
    private FontAwesomeIconView language;

    @FXML
    private TableView<AppointmentView> tableTerminet;

    @FXML
    private Label totalAppointment;

    @FXML
    private Label totalPacient;

        MjeketRepository mjeketRepository = new MjeketRepository();
        Connection connection = DBConnector.getConnection();

        @FXML
        void handleLanguageToggle(MouseEvent event) throws IOException {
            Locale current = LanguageManager.getInstance().getLocale();
            if ("sq".equals(current.getLanguage())) {
                LanguageManager.getInstance().setLocale(new Locale("en"));
                Navigator.setCurrentLocale(new Locale("en"));
            } else {
                LanguageManager.getInstance().setLocale(new Locale("sq"));
                Navigator.setCurrentLocale(new Locale("sq"));
            }
            Navigator.reload();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colStatusi.setCellValueFactory(new PropertyValueFactory<>("statusi"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colMbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));


        datePicker.setOnAction(e -> loadTerminetPerDate(datePicker.getValue()));
            totalPacient();
            totalAppointment();
    }

    private void loadTerminetPerDate(LocalDate selectedDate) {
        if (selectedDate == null) return;

        String sql = """
        SELECT\s
            u.emri,
            u.mbiemri,
            t.statusi
        FROM terminet t
        JOIN pacientet p ON t.id_pacient = p.id
        JOIN perdoruesi u ON p.user_id = u.id
        WHERE DATE(t.data) = ?
          AND t.id_mjek = ?
    """;

        List<AppointmentView> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(selectedDate));
            stmt.setInt(2, SessionManager.getMjeku().getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new AppointmentView(
                        rs.getString("emri"),
                        rs.getString("mbiemri"),
                        rs.getString("statusi")
                ));
            }

            tableTerminet.setItems(FXCollections.observableList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void totalPacient() {
            totalPacient.setText(String.valueOf(mjeketRepository.totalPatientsfromDoctor()));
    }

    public void totalAppointment() {
            totalAppointment.setText(String.valueOf(mjeketRepository.totalAppointmentsfromDoctor()));
    }
}
