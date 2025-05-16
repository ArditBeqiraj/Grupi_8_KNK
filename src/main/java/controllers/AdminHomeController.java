package controllers;

import Database.DBConnector;
import Repository.PacientetRepository;
import Repository.PerdoruesiRepository;
import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.chart.BarChart;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Pacientet.Pacientet;
import model.Perdoruesi.Perdoruesi;
import model.Perdoruesi.PerdoruesiDTO.CreatePerdoruesiDTO;
import services.PasswordHasher;
import services.PerdoruesiService;

public class AdminHomeController implements Initializable {

    @FXML
    private BarChart<?, ?> home_chart;

    @FXML
    private Label totalEmployees;

    @FXML
    private Label totalPacient;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button shkyqu;

    @FXML
    private TextField shtoStaf_Email;

    @FXML
    private TextField shtoStaf_Emri;

    @FXML
    private AnchorPane shtoStaf_Forma;

    @FXML
    private Button shtoStaf_Fshij;

    @FXML
    private TextField shtoStaf_Mbiemri;

    @FXML
    private Button shtoStaf_Pastro;

    @FXML
    private Button shtoStaf_Perditeso;

    @FXML
    private ComboBox<String> shtoStaf_Roli;

    @FXML
    private Button shtoStaf_Shto;

    @FXML
    private TableView<Perdoruesi> shtoStaf_Tabela;

    @FXML
    private TextField shtoStaf_Telefoni;

    @FXML
    private Button shtoStaf_btn;

    @FXML
    private Button shtoStaf_btn1;

    @FXML
    private TableColumn<Perdoruesi, String> shtoStaf_col_Email;

    @FXML
    private TableColumn<Perdoruesi, String> shtoStaf_col_Krijuar;

    @FXML
    private TableColumn<Perdoruesi, String> shtoStaf_col_Mbiemri;

    @FXML
    private TableColumn<Perdoruesi, String> shtoStaf_col_Roli;

    @FXML
    private TableColumn<Perdoruesi, String> shtoStaf_col_Telefoni;

    @FXML
    private TableColumn<Perdoruesi, String> shtoStaf_col_emri;

    @FXML
    private TableColumn<Perdoruesi, String> shtoStaf_col_userID;

    @FXML
    private TextField shtoStaf_search;

    @FXML
    private TextField shtoStaf_UserID;

    @FXML
    private AnchorPane shtoStaf_Forma1;

    private PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
    private PerdoruesiService perdoruesiService = new PerdoruesiService();

    @FXML
    private void handleLogOut(ActionEvent me) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmimi");
        alert.setHeaderText(null);
        alert.setContentText("A jeni të sigurt se doni të shkyçeni?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            perdoruesiService.LogOut();
            Navigator.navigate(me, Navigator.LOGIN_PAGE, "Login");
        }
    }

    public void totalEmployees() {
        totalEmployees.setText(String.valueOf(perdoruesiRepository.totalEmployees()));
    }

    public void totalPacientet() {
        totalPacient.setText(String.valueOf(perdoruesiRepository.totalPacient()));
    }


    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }
    Date date = new Date();
    private Timestamp timestamp= new Timestamp(date.getTime());

    public void shtoStafUpdate() throws SQLException {
        if (shtoStaf_Emri.getText().isEmpty() ||
                shtoStaf_Mbiemri.getText().isEmpty() ||
                shtoStaf_Email.getText().isEmpty() ||
                shtoStaf_Telefoni.getText().isEmpty() ||
                shtoStaf_Roli.getValue() == null || shtoStaf_Roli.getValue().isEmpty()) {

            showErrorAlert("Të gjitha fushat duhet të mbushen.");
            return;
        }

        if (!isValidEmail(shtoStaf_Email.getText())) {
            showErrorAlert("Ju lutemi futni një email të vlefshëm.");
            return;
        }

        Perdoruesi perdoruesi = new Perdoruesi();
        perdoruesi.setId(Integer.parseInt(shtoStaf_UserID.getText()));
        perdoruesi.setEmri(shtoStaf_Emri.getText());
        perdoruesi.setMbiemri(shtoStaf_Mbiemri.getText());
        perdoruesi.setEmail(shtoStaf_Email.getText());
        perdoruesi.setTelefoni(shtoStaf_Telefoni.getText());
        perdoruesi.setRoli(shtoStaf_Roli.getValue());

        boolean updated = perdoruesiService.updateStaf(perdoruesi);

        if (updated) {
            shtoStafLista = addStaf();
            shtoStaf_Tabela.setItems(shtoStafLista);
            showInfoAlert("Përdoruesi u përditësua me sukses.");
            ResetData();
        } else {
            showErrorAlert("Përdoruesi me emailin: \"" + perdoruesi.getEmail() + "\" ekziston.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    public void shtoStafDelete() {
        PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
        if (shtoStaf_UserID.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText(null);
            alert.setContentText("Selektoni nje perdorues per te fshire");
            alert.showAndWait();
        } else {

            int userId = Integer.valueOf(shtoStaf_UserID.getText());
            perdoruesiRepository.delete(Integer.valueOf(shtoStaf_UserID.getText()));
            shtoStafLista.removeIf(p -> p.getId() == userId);
            shtoStaf_Tabela.setItems(shtoStafLista);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("Perdoruesi u fshi me sukses");
            alert.showAndWait();
            ResetData();
        }
    }

    public void shtoNeDatabaz() throws SQLException {
        String salt = PasswordHasher.generateSalt();
        String password = PasswordHasher.generateSaltedHash(shtoStaf_Emri.getText() + shtoStaf_Mbiemri.getText(), salt);
        CreatePerdoruesiDTO createPerdoruesiDTO = new CreatePerdoruesiDTO(shtoStaf_Emri.getText(), shtoStaf_Mbiemri.getText(), shtoStaf_Email.getText(), shtoStaf_Telefoni.getText(), shtoStaf_Roli.getValue(), salt, password, timestamp);
        PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
        if (shtoStaf_Emri.getText().isEmpty() || shtoStaf_Mbiemri.getText().isEmpty() || shtoStaf_Email.getText().isEmpty() || shtoStaf_Telefoni.getText().isEmpty() || shtoStaf_Roli.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText(null);
            alert.setContentText("Te gjitha fushat duhet te mbushen");
            alert.showAndWait();
        } else {
            String check = "SELECT * FROM perdoruesi WHERE email = '" + shtoStaf_Email.getText() + "'";
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(check);

            if (resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Gabim");
                alert.setHeaderText(null);
                alert.setContentText("Perdoruesi me emailin: \"" + shtoStaf_Email.getText() + " \"egziston");
                alert.showAndWait();
            } else if (!this.isValidEmail(shtoStaf_Email.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Gabim");
                alert.setHeaderText(null);
                alert.setContentText("Ju lutemi futni një email të vlefshëm.");
                alert.showAndWait();
            } else {
                Perdoruesi perdoruesi = perdoruesiRepository.create(createPerdoruesiDTO);
                shtoStafLista.add(perdoruesi);
                shtoStaf_Tabela.setItems(shtoStafLista);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacion");
                alert.setHeaderText(null);
                alert.setContentText("Perdoruesi u ruajt me sukses");
                alert.showAndWait();
            }
        }
    }

    public void ResetData () {
        shtoStaf_UserID.setText("");
        shtoStaf_Emri.setText("");
        shtoStaf_Emri.setEditable(true);
        shtoStaf_Mbiemri.setText("");
        shtoStaf_Mbiemri.setEditable(true);
        shtoStaf_Email.setText("");
        shtoStaf_Telefoni.setText("");
        shtoStaf_Roli.getSelectionModel().clearSelection();
    }

    public ObservableList<Perdoruesi> addStaf () throws SQLException {
        ObservableList<Perdoruesi> StafLista = FXCollections.observableArrayList();
        PerdoruesiRepository perdoruesiRepository = new PerdoruesiRepository();
        ArrayList<Perdoruesi> arrayStaf = perdoruesiRepository.getAllStaf();

        for (Perdoruesi p : arrayStaf) {
            StafLista.add(p);
        }

        return StafLista;
    }

    private ObservableList<Perdoruesi> shtoStafLista;
    public void ShtoStafListaData() throws SQLException {
        shtoStafLista = addStaf();

        shtoStaf_col_userID.setCellValueFactory(new PropertyValueFactory<>("id"));
        shtoStaf_col_emri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        shtoStaf_col_Mbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        shtoStaf_col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        shtoStaf_col_Telefoni.setCellValueFactory(new PropertyValueFactory<>("telefoni"));
        shtoStaf_col_Roli.setCellValueFactory(new PropertyValueFactory<>("roli"));
        shtoStaf_col_Krijuar.setCellValueFactory(new PropertyValueFactory<>("krijuar"));

        shtoStaf_Tabela.setItems(shtoStafLista);
    }

    public void addEmployeeSelect() {
        Perdoruesi perdoruesi = shtoStaf_Tabela.getSelectionModel().getSelectedItem();
        int num = shtoStaf_Tabela.getSelectionModel().getSelectedIndex();

        if ((num -1) < -1) {return;}

        shtoStaf_UserID.setText(String.valueOf(perdoruesi.getId()));
        shtoStaf_Emri.setText(perdoruesi.getEmri());
        shtoStaf_Emri.setEditable(false);
        shtoStaf_Mbiemri.setText(perdoruesi.getMbiemri());
        shtoStaf_Mbiemri.setEditable(false);
        shtoStaf_Email.setText(perdoruesi.getEmail());
        shtoStaf_Telefoni.setText(perdoruesi.getTelefoni());
        shtoStaf_Roli.setValue(perdoruesi.getRoli());
    }

    public void switchform(ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource() == home_btn) {
            home_form.setVisible(true);
            shtoStaf_Forma.setVisible(false);
            shtoStaf_Forma1.setVisible(false);
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2b303c, #525968)");
            shtoStaf_btn.setStyle("-fx-background-color: transparent");
            shtoStaf_btn1.setStyle("-fx-background-color: transparent");
            totalEmployees();
            totalPacientet();
            homeChart();
        } else if (actionEvent.getSource() == shtoStaf_btn) {
            home_form.setVisible(false);
            shtoStaf_Forma.setVisible(true);
            shtoStaf_Forma1.setVisible(false);
            shtoStaf_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2b303c, #525969)");
            home_btn.setStyle("-fx-background-color: transparent");
            shtoStaf_btn1.setStyle("-fx-background-color: transparent");
            shtoStaf_Roli.getItems().addAll("Doktor", "Moter", "Praktikant");
            ShtoStafListaData();
            addEmployeeSearch();
        } else if (actionEvent.getSource() == shtoStaf_btn1) {
            home_form.setVisible(false);
            shtoStaf_Forma.setVisible(false);
            shtoStaf_Forma1.setVisible(true);
            shtoStaf_btn.setStyle("-fx-background-color: transparent");
            shtoStaf_btn1.setStyle("-fx-background-color:linear-gradient(to bottom right, #2b303c, #525969)");
            home_btn.setStyle("-fx-background-color: transparent");
            shtoStaf_Roli.getItems().addAll("Doktor", "Moter", "Praktikant");
            ShtoStafListaData();
            addEmployeeSearch1();
        }
    }

    private double x = 0;
    private double y = 0;

    public void shkyqu () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmimi");
        alert.setHeaderText(null);
        alert.setContentText("A jeni te sigurt se doni te shkyqeni?");
        Optional<ButtonType> option = alert.showAndWait();

        try {
            if (option.get().equals(ButtonType.OK)) {

                shkyqu.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXML_document.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mbyll() {
        System.exit(0);
    }

    public void minimizo() {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }









    @FXML
    private TextField shtoStaf_search1;

    @FXML
    private TableView<Pacientet> shtoStaf_Tabela1;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_pacientID1;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_emri1;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_Mbiemri1;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_Email1;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_Telefoni1;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_Datelindja;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_Gjinia;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_Adresa;

    @FXML
    private TableColumn<Pacientet, String> shtoStaf_col_Kontakti;

    public ObservableList<Pacientet> addPacient() {
        try {
            return perdoruesiService.fetchAllPacientet();
        } catch (SQLException e) {
            e.printStackTrace();
            // Show error alert or handle gracefully
            return FXCollections.observableArrayList();
        }
    }


    private ObservableList<Pacientet> shtoPacientlista;
    public void ShtoPacientListaData() throws SQLException {
        shtoPacientlista = addPacient();

        shtoStaf_col_pacientID1.setCellValueFactory(new PropertyValueFactory<>("id"));
        shtoStaf_col_emri1.setCellValueFactory(new PropertyValueFactory<>("emri"));
        shtoStaf_col_Mbiemri1.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        shtoStaf_col_Email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        shtoStaf_col_Telefoni1.setCellValueFactory(new PropertyValueFactory<>("telefoni"));
        shtoStaf_col_Datelindja.setCellValueFactory(new PropertyValueFactory<>("datelindja"));
        shtoStaf_col_Gjinia.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
        shtoStaf_col_Adresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        shtoStaf_col_Kontakti.setCellValueFactory(new PropertyValueFactory<>("kontakti_emergjent"));

        shtoStaf_Tabela1.setItems(shtoPacientlista);
    }

    public void addEmployeeSearch() {
        FilteredList<Perdoruesi> filter = new FilteredList<>(shtoStafLista, e -> true);

        shtoStaf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (String.valueOf(predicateEmployeeData.getId()).contains(searchKey)) {
                    return true;
                }

                String emri = predicateEmployeeData.getEmri();
                if (emri != null && emri.toLowerCase().contains(searchKey)) {
                    return true;
                }

                String mbiemri = predicateEmployeeData.getMbiemri();
                if (mbiemri != null && mbiemri.toLowerCase().contains(searchKey)) {
                    return true;
                }

                String email = predicateEmployeeData.getEmail();
                if (email != null && email.toLowerCase().contains(searchKey)) {
                    return true;
                }

                String telefoni = predicateEmployeeData.getTelefoni();
                if (telefoni != null && telefoni.toLowerCase().contains(searchKey)) {
                    return true;
                }

                String roli = predicateEmployeeData.getRoli();
                if (roli != null && roli.toLowerCase().contains(searchKey)) {
                    return true;
                }

                Timestamp krijuar = predicateEmployeeData.getKrijuar();
                if (krijuar != null && krijuar.toString().toLowerCase().contains(searchKey)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Perdoruesi> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(shtoStaf_Tabela.comparatorProperty());
        shtoStaf_Tabela.setItems(sortList);
    }

    public void addEmployeeSearch1() {
        FilteredList<Pacientet> filter = new FilteredList<>(shtoPacientlista, e -> true);

        shtoStaf_search1.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(PacientData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (String.valueOf(PacientData.getId()).toLowerCase().contains(searchKey)) return true;
                if (PacientData.getEmri() != null && PacientData.getEmri().toLowerCase().contains(searchKey)) return true;
                if (PacientData.getMbiemri() != null && PacientData.getMbiemri().toLowerCase().contains(searchKey)) return true;
                if (PacientData.getEmail() != null && PacientData.getEmail().toLowerCase().contains(searchKey)) return true;
                if (PacientData.getTelefoni() != null && PacientData.getTelefoni().toLowerCase().contains(searchKey)) return true;
                if (PacientData.getDatelindja() != null && PacientData.getDatelindja().toString().toLowerCase().contains(searchKey)) return true;
                if (PacientData.getGjinia() != null && PacientData.getGjinia().toLowerCase().contains(searchKey)) return true;
                if (PacientData.getAdresa() != null && PacientData.getAdresa().toLowerCase().contains(searchKey)) return true;
                if (PacientData.getKontakti_emergjent() != null && PacientData.getKontakti_emergjent().toLowerCase().contains(searchKey)) return true;

                return false;
            });
        });

        SortedList<Pacientet> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(shtoStaf_Tabela1.comparatorProperty());
        shtoStaf_Tabela1.setItems(sortList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2b303c, #525968)");
            ShtoStafListaData();
            ShtoPacientListaData();
            addEmployeeSearch();
            addEmployeeSearch1();
            totalEmployees();
            totalPacientet();
            homeChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        shtoStaf_Roli.getItems().addAll("Doktor", "Moter", "Praktikant");
    }

    public void homeChart() {
        home_chart.getData().clear();
        XYChart.Series chart = perdoruesiRepository.homeChart();
        home_chart.getData().add(chart);
    }
}
