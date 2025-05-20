package controllers;

import Repository.PacientetRepository;
import app.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pacientet.Pacientet;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ListaPacientController {

    @FXML
    private TableView<Pacientet> tableView;

    @FXML private TableColumn<Pacientet, Integer> pacientID1;
    @FXML private TableColumn<Pacientet, String> emri1;
    @FXML private TableColumn<Pacientet, String> mbiemri1;
    @FXML private TableColumn<Pacientet, String> email1;
    @FXML private TableColumn<Pacientet, String> telefoni1;
    @FXML private TableColumn<Pacientet, LocalDate> datelindja;
    @FXML private TableColumn<Pacientet, String> gjinia;
    @FXML private TableColumn<Pacientet, String> adresa;
    @FXML private TableColumn<Pacientet, String> kontakti;

    @FXML
    private TextField searchField;

    private PacientetRepository pacientetRepository;

    @FXML
    public void initialize() {
        pacientetRepository = new PacientetRepository();

        pacientID1.setCellValueFactory(new PropertyValueFactory<>("id"));
        emri1.setCellValueFactory(new PropertyValueFactory<>("emri"));
        mbiemri1.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefoni1.setCellValueFactory(new PropertyValueFactory<>("telefoni"));
        datelindja.setCellValueFactory(new PropertyValueFactory<>("datelindja"));
        gjinia.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
        adresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        kontakti.setCellValueFactory(new PropertyValueFactory<>("kontakti_emergjent"));

        loadTableData();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterList(newValue));
    }

    private void loadTableData() {
        List<Pacientet> pacientetList = pacientetRepository.getAllWithPerdoruesiByDoctorId(SessionManager.getPerdoruesi().getId());
        ObservableList<Pacientet> observableList = FXCollections.observableArrayList(pacientetList);
        tableView.setItems(observableList);
    }

    private void filterList(String filter) {
        if (filter == null || filter.isBlank()) {
            loadTableData();
            return;
        }

        List<Pacientet> filtered = pacientetRepository.getAllWithPerdoruesiByDoctorId(SessionManager.getPerdoruesi().getId()).stream()
                .filter(p -> p.getEmri().toLowerCase().contains(filter.toLowerCase())
                        || p.getMbiemri().toLowerCase().contains(filter.toLowerCase())
                        || p.getEmail().toLowerCase().contains(filter.toLowerCase())
                        || p.getTelefoni().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());

        tableView.setItems(FXCollections.observableArrayList(filtered));
    }
}
