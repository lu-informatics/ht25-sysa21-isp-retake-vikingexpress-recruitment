
package se.lu.ics.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import se.lu.ics.models.*;
import java.io.IOException;
import java.time.LocalDate;


public class ApplicationsForRecruitmentViewController {
    private Recruitment recruitment;
    private AppController appController;


    @FXML private Label labelRecruitmentInfo;
    @FXML private Label labelApplicantCount;
    @FXML private TableView<Application> tableViewApplications;
    @FXML private TableColumn<Application, String> colApplicationId;
    @FXML private TableColumn<Application, String> colCandidateName;
    @FXML private TableColumn<Application, LocalDate> colApplicationDate;
    @FXML private TableColumn<Application, Integer> colRanking;
    @FXML private TableColumn<Application, Boolean> colOfferAccepted;


    public void initialize() {
        colApplicationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCandidateName.setCellValueFactory(cellData -> {
            Candidate candidate = cellData.getValue().getCandidate();
            return new javafx.beans.property.SimpleStringProperty(
                candidate != null ? candidate.getName() : "N/A"
            );
        });
        colApplicationDate.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        colRanking.setCellValueFactory(new PropertyValueFactory<>("ranking"));
        colOfferAccepted.setCellValueFactory(new PropertyValueFactory<>("offerAccepted"));
    }


    @FXML
    public void handleEditApplication(ActionEvent event) {
        Application selected = tableViewApplications.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                appController.showApplicationDialog(selected);
                tableViewApplications.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) tableViewApplications.getScene().getWindow();
        stage.close();
    }


    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
       
        if (recruitment != null) {
            labelRecruitmentInfo.setText("Recruitment: " + recruitment.getId() +
                " - " + (recruitment.getRole() != null ? recruitment.getRole().getTitle() : "N/A"));
            labelApplicantCount.setText("Total Applicants: " + recruitment.getApplicantCount());
            tableViewApplications.setItems(recruitment.getApplications());
        }
    }


    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
