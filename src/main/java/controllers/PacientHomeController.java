package controllers;

import Database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.MjekuSpecializimi.MjekuSpecializimi;
import model.Perdoruesi.Perdoruesi;
import model.Terminet.Terminet;
import app.SessionManager;
import java.sql.*;
import java.time.Instant;


public class PacientHomeController {
    @FXML private AnchorPane myProfile_form;
    @FXML private Label lblEmri, lblMbiemri, lblEmail, lblTelefoni,lblMjekuFamiljar;
    @FXML private AnchorPane home_form;
    @FXML private AnchorPane zgjedhMjekun_form;
    @FXML private AnchorPane rezervoTermin_form;
    @FXML private AnchorPane anuloTermin_form;
    @FXML private TableView<Terminet> tabelaTerminet;
    @FXML private TableColumn<Terminet, String> colMjeku;
    @FXML private TableColumn<Terminet, String> colData;
    @FXML private TableColumn<Terminet, String> colStatusi;
    @FXML private TableColumn<Terminet, String> colShenime;

    @FXML private TableView<MjekuSpecializimi> tabelaMjekeve;
    @FXML private TableColumn<MjekuSpecializimi, Integer> colId;
    @FXML private TableColumn<MjekuSpecializimi, String> colEmri;
    @FXML private TableColumn<MjekuSpecializimi, String> colMbiemri;
    @FXML private TableColumn<MjekuSpecializimi, String> colSpecializimi;

    @FXML private ComboBox<MjekuSpecializimi> comboMjeku;
    @FXML private DatePicker datePicker;
    @FXML private TextArea textShenime;

    @FXML private TableView<Terminet> tabelaAnuloTerminet;
    @FXML private TableColumn<Terminet, String> colMjekuAnulo;
    @FXML private TableColumn<Terminet, String> colDataAnulo;
    @FXML private TableColumn<Terminet, String> colStatusiAnulo;
    private int pacientId;

    public void initialize() {
        /*if (SessionManager.getPerdoruesi() == null) {
            System.out.println("Error: Nuk ka përdorues të kyçur në session!");
            return;
        }*/
        // ID statike per testim
        if (SessionManager.getPerdoruesi() == null) {
            Perdoruesi testPerdoruesi = new Perdoruesi(
                    1,
                    "Test",
                    "Pacient",
                    "testpacient@example.com",
                    "044112233",
                    "Pacient",
                    "salt123",
                    "hashedpass123",
                    Timestamp.from(Instant.now())
            );
            testPerdoruesi.setId(1);
            testPerdoruesi.setEmail("testpacient@example.com");
            testPerdoruesi.setEmri("Test");

            SessionManager.setPerdoruesi(testPerdoruesi);
            System.out.println("Vendosa përdoruesin test në session për testim.");
        }


        //pacientId = SessionManager.getPerdoruesi().getId();
        pacientId = 1;
        shfaqTerminet();
        shfaqMjeket();
        mbushComboMjeket();
        shfaqTerminetAnulim();
        shfaqTeDhenatEPacientit();
    }

    @FXML
    void switchform(ActionEvent event) {
        home_form.setVisible(false);
        zgjedhMjekun_form.setVisible(false);
        rezervoTermin_form.setVisible(false);
        anuloTermin_form.setVisible(false);
        myProfile_form.setVisible(false);

        Button source = (Button) event.getSource();

        switch (source.getId()) {
            case "home_btn" -> home_form.setVisible(true);
            case "zgjedhMjekun_btn" -> zgjedhMjekun_form.setVisible(true);
            case "rezervoTermin_btn" -> rezervoTermin_form.setVisible(true);
            case "anuloTermin_btn" -> anuloTermin_form.setVisible(true);
            case "myProfile_btn" -> myProfile_form.setVisible(true);
        }
    }
    public void shfaqTerminet() {
        ObservableList<Terminet> lista = FXCollections.observableArrayList();
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT t.data, t.statusi, t.shenime, m.emri, m.mbiemri FROM Terminet t JOIN Mjeket m ON t.id_mjek = m.id WHERE t.id_pacient = ?")) {
            stmt.setInt(1, pacientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String emriMjekut = rs.getString("emri") + " " + rs.getString("mbiemri");
                Date data = rs.getDate("data");
                String statusi = rs.getString("statusi");
                String shenime = rs.getString("shenime");
                lista.add(new Terminet(emriMjekut,data,statusi,shenime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colMjeku.setCellValueFactory(new PropertyValueFactory<>("mjeku"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colStatusi.setCellValueFactory(new PropertyValueFactory<>("statusi"));
        colShenime.setCellValueFactory(new PropertyValueFactory<>("shenime"));

        tabelaTerminet.setItems(lista);
    }