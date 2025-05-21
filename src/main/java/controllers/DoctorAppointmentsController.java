package controllers;

import Repository.TerminetRepository;
import app.SessionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Terminet.AppointmentFullView;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorAppointmentsController {
    TerminetRepository terminetRepository = new TerminetRepository();
    @FXML
    private TextField filterField;
    @FXML
    private TableView<AppointmentFullView> tableTerminet;
    @FXML private TableColumn<AppointmentFullView, String> colEmri;
    @FXML private TableColumn<AppointmentFullView, String> colMbiemri;
    @FXML private TableColumn<AppointmentFullView, Date> colData;
    @FXML private TableColumn<AppointmentFullView, String> colStatusi;
    @FXML private TableColumn<AppointmentFullView, String> colShenime;

    public void initialize() {
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colMbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colStatusi.setCellValueFactory(new PropertyValueFactory<>("statusi"));
        colShenime.setCellValueFactory(new PropertyValueFactory<>("shenime"));

        loadAppointments();
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList(newValue);
        });

    }

    private void loadAppointments() {
        List<AppointmentFullView> appointments = terminetRepository.getAllAppointmentsByMjekId(SessionManager.getMjeku().getId());
        tableTerminet.setItems(FXCollections.observableArrayList(appointments));
    }

    private void filterList(String filter) {
        if (filter == null || filter.isBlank()) {
            loadAppointments();
            return;
        }

        List<AppointmentFullView> allAppointments = terminetRepository.getAllAppointmentsByMjekId(SessionManager.getPerdoruesi().getId());

        List<AppointmentFullView> filtered = allAppointments.stream()
                .filter(a ->
                        a.getEmri().toLowerCase().contains(filter.toLowerCase()) ||
                                a.getMbiemri().toLowerCase().contains(filter.toLowerCase()) ||
                                a.getStatusi().toLowerCase().contains(filter.toLowerCase()) ||
                                (a.getShenime() != null && a.getShenime().toLowerCase().contains(filter.toLowerCase())) ||
                                a.getData().toString().contains(filter)
                )
                .collect(Collectors.toList());

        tableTerminet.setItems(FXCollections.observableArrayList(filtered));
    }


}
