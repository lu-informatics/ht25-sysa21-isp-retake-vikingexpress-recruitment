package se.lu.ics.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import se.lu.ics.models.*;
import java.time.LocalDate;


public class CandidateHistoryViewController {
    private Candidate candidate;


    @FXML private Label labelCandidateInfo;
    @FXML private TableView<Application> tableViewApplications;
    @FXML private TableColumn<Application, String> colRecruitmentId;
    @FXML private TableColumn<Application, String> colRole;
    @FXML private TableColumn<Application, LocalDate> colApplicationDate;
    @FXML private TableColumn<Application, Integer> colRanking;
    @FXML private TableColumn<Application, Boolean> colOfferAccepted;


    public void initialize() {
        colRecruitmentId.setCellValueFactory(cellData -> {
            Recruitment recruitment = cellData.getValue().getRecruitment();
            return new javafx.beans.property.SimpleStringProperty(
                recruitment != null ? recruitment.getId() : "N/A"
            );
        });
        colRole.setCellValueFactory(cellData -> {
            Recruitment recruitment = cellData.getValue().getRecruitment();
            return new javafx.beans.property.SimpleStringProperty(
                recruitment != null && recruitment.getRole() != null ?
                recruitment.getRole().getTitle() : "N/A"
            );
        });
        colApplicationDate.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        colRanking.setCellValueFactory(new PropertyValueFactory<>("ranking"));
        colOfferAccepted.setCellValueFactory(new PropertyValueFactory<>("offerAccepted"));
    }


    @FXML
    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) tableViewApplications.getScene().getWindow();
        stage.close();
    }


    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
       
        if (candidate != null) {
            labelCandidateInfo.setText("Candidate: " + candidate.getName() + " (" + candidate.getId() + ")");
            tableViewApplications.setItems(candidate.getApplications());
        }
    }
}
