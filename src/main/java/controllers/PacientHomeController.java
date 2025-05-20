package controllers;

import Database.DBConnectoor;
import Database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MjekuSpecializimi.MjekuSpecializimi;
import model.Perdoruesi.Perdoruesi;
import model.Terminet.Terminet;
import app.SessionManager;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;


public class PacientHomeController {
    @FXML
    private AnchorPane myProfile_form;
    @FXML
    private Label lblEmri, lblMbiemri, lblEmail, lblTelefoni, lblMjekuFamiljar;
    @FXML
    private AnchorPane home_form;
    @FXML
    private AnchorPane zgjedhMjekun_form;
    @FXML
    private AnchorPane rezervoTermin_form;
    @FXML
    private AnchorPane anuloTermin_form;
    @FXML
    private TableView<Terminet> tabelaTerminet;
    @FXML
    private TableColumn<Terminet, String> colMjeku;
    @FXML
    private TableColumn<Terminet, String> colData;
    @FXML
    private TableColumn<Terminet, String> colStatusi;
    @FXML
    private TableColumn<Terminet, String> colShenime;

    @FXML
    private TableView<MjekuSpecializimi> tabelaMjekeve;
    @FXML
    private TableColumn<MjekuSpecializimi, Integer> colId;
    @FXML
    private TableColumn<MjekuSpecializimi, String> colEmri;
    @FXML
    private TableColumn<MjekuSpecializimi, String> colMbiemri;
    @FXML
    private TableColumn<MjekuSpecializimi, String> colSpecializimi;

    @FXML
    private ComboBox<MjekuSpecializimi> comboMjeku;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea textShenime;

    @FXML
    private TableView<Terminet> tabelaAnuloTerminet;
    @FXML
    private TableColumn<Terminet, String> colMjekuAnulo;
    @FXML
    private TableColumn<Terminet, String> colDataAnulo;
    @FXML
    private TableColumn<Terminet, String> colStatusiAnulo;
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


        pacientId = SessionManager.getPerdoruesi().getId();
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
        try (Connection connection = DBConnectoor.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT t.data, t.statusi, t.shenime, m.emri, m.mbiemri FROM Terminet t JOIN Mjeket m ON t.id_mjek = m.id WHERE t.id_pacient = ?")) {
            stmt.setInt(1, pacientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String emriMjekut = rs.getString("emri") + " " + rs.getString("mbiemri");
                Date data = rs.getDate("data");
                String statusi = rs.getString("statusi");
                String shenime = rs.getString("shenime");
                lista.add(new Terminet(emriMjekut, data, statusi, shenime));
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

    public void shfaqMjeket() {
        String query = """
    SELECT m.id, m.emri, m.mbiemri, COALESCE(s.emri_specializimit, m.specializimi_kryesor) AS specializimi
    FROM Mjeket m
    LEFT JOIN MjekuSpecializimi ms ON m.id = ms.mjeku_id
    LEFT JOIN Specializimet s ON ms.specializimi_id = s.id
""";
        ObservableList<MjekuSpecializimi> lista = FXCollections.observableArrayList();
        try (Connection conn = DBConnectoor.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new MjekuSpecializimi(
                        rs.getInt("id"),
                        rs.getString("emri"),
                        rs.getString("mbiemri"),
                        rs.getString("specializimi")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colMbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        colSpecializimi.setCellValueFactory(new PropertyValueFactory<>("specializimi"));

        tabelaMjekeve.setItems(lista);

    }

    public void mbushComboMjeket() {
        ObservableList<MjekuSpecializimi> mjeketList = FXCollections.observableArrayList();

        String query = """
    SELECT m.id, m.emri, m.mbiemri, COALESCE(s.emri_specializimit, m.specializimi_kryesor) AS specializimi
    FROM Mjeket m
    LEFT JOIN MjekuSpecializimi ms ON m.id = ms.mjeku_id
    LEFT JOIN Specializimet s ON ms.specializimi_id = s.id
""";

        try (Connection conn = DBConnectoor.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String emri = rs.getString("emri");
                String mbiemri = rs.getString("mbiemri");
                String specializimi = rs.getString("specializimi");
                mjeketList.add(new MjekuSpecializimi(id, emri, mbiemri, specializimi));
            }

            comboMjeku.setItems(mjeketList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void rezervoTermin() {
        MjekuSpecializimi mjeku = comboMjeku.getSelectionModel().getSelectedItem();
        LocalDate data = datePicker.getValue();
        String shenime = textShenime.getText();
        if (data.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "data duhet te jete ne te ardhmen.");
            alert.showAndWait();
            return;
        }
        if (mjeku == null || data == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ju lutem plotesoni te gjitha fushat!");
            alert.showAndWait();
            return;
        }

        try (Connection conn = DBConnectoor.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Terminet (id_pacient, id_mjek, id_salle, data, statusi, shenime) VALUES (?, ?, ?, ?, 'Skeduluar', ?)")) {
            stmt.setInt(1, pacientId);
            stmt.setInt(2, mjeku.getId());
            stmt.setInt(3, 1); // id_salle placeholder
            stmt.setDate(4, Date.valueOf(data));
            stmt.setString(5, shenime);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        shfaqTerminet();
        shfaqTerminetAnulim();
        comboMjeku.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        textShenime.clear();
    }

    public void shfaqTerminetAnulim() {
        tabelaAnuloTerminet.setItems(tabelaTerminet.getItems());
        colMjekuAnulo.setCellValueFactory(colMjeku.getCellValueFactory());
        colDataAnulo.setCellValueFactory(colData.getCellValueFactory());
        colStatusiAnulo.setCellValueFactory(colStatusi.getCellValueFactory());
    }

    @FXML
    public void anuloTermin() {
        Terminet selected = tabelaAnuloTerminet.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Connection conn = DBConnectoor.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Terminet SET statusi = 'Anuluar' WHERE id_pacient = ? AND data = ? AND shenime = ?")) {
            stmt.setInt(1, pacientId);
            stmt.setDate(2, selected.getData());
            stmt.setString(3, selected.getShenime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        shfaqTerminet();
        shfaqTerminetAnulim();
    }

    @FXML
    public void shkyqu(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void ruajMjekunFamiljar() {
        MjekuSpecializimi mjekuZgjedhur = tabelaMjekeve.getSelectionModel().getSelectedItem();

        if (mjekuZgjedhur == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Zgjedh mjekun");
            alert.setHeaderText(null);
            alert.setContentText("Ju lutem zgjedhni një mjek nga tabela.");
            alert.showAndWait();
            return;
        }

        try (Connection conn = DBConnectoor.getConnection()) {
            // Mund të kesh një kolonë `mjeku_familjar_id` në tabelën Pacientet
            String sql = "UPDATE Pacientet SET mjeku_familjar_id = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mjekuZgjedhur.getId());
            stmt.setInt(2, pacientId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukses");
                alert.setHeaderText(null);
                alert.setContentText("Mjeku familjar u ruajt me sukses!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Gabim");
                alert.setHeaderText(null);
                alert.setContentText("Nuk u ruajt mjeku familjar.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void shfaqTeDhenatEPacientit() {
        Connection conn = DBConnectoor.getConnection();
        String query = """
        
                SELECT p.emri, p.mbiemri, p.email, p.telefoni
        FROM Perdoruesi p
        JOIN Pacientet pac ON pac.user_id = p.id
        WHERE p.id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, 1);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Te dhena u gjeten.");
                lblEmri.setText(rs.getString("emri"));
                lblMbiemri.setText(rs.getString("mbiemri"));
                lblEmail.setText(rs.getString("email"));
                lblTelefoni.setText(rs.getString("telefoni"));
                lblMjekuFamiljar.setText(merrMjekunFamiljar());

            } else {
                System.out.println("Nuk u gjetën të dhëna për pacientin.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String merrMjekunFamiljar() {
        String emriMjekut = "Nuk është zgjedhur";
        String sql = "SELECT m.emri, m.mbiemri " +
                "FROM Pacientet p " +
                "JOIN Mjeket m ON p.mjeku_familjar_id = m.id " +
                "WHERE p.user_id = ?";

        try (Connection conn = DBConnectoor.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, 1);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emriMjekut = rs.getString("emri") + " " + rs.getString("mbiemri");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emriMjekut;
    }


    }