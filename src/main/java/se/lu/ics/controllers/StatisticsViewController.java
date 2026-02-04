package se.lu.ics.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import se.lu.ics.models.*;
import javafx.collections.ObservableList;
import java.time.LocalDate;


public class StatisticsViewController {
    private AppModel appModel;


    @FXML private Label labelAverageDaysToAcceptance;
    @FXML private Label labelAverageInterviewsPerOffer;
    @FXML private Label labelMostPopularRole;
    @FXML private DatePicker datePickerStartDate;
    @FXML private DatePicker datePickerEndDate;
    @FXML private TableView<Recruitment> tableViewRecruitmentsInPeriod;
    @FXML private TableColumn<Recruitment, String> colRecruitmentId;
    @FXML private TableColumn<Recruitment, String> colRole;
    @FXML private TableColumn<Recruitment, String> colStatus;
    @FXML private Label labelResponse;


    public void initialize() {
        colRecruitmentId.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));
        colRole.setCellValueFactory(cellData -> {
            Role role = cellData.getValue().getRole();
            return new javafx.beans.property.SimpleStringProperty(
                role != null ? role.getTitle() : "N/A"
            );
        });
        colStatus.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));
    }


    @FXML
    public void handleSearchPeriod(ActionEvent event) {
        if (datePickerStartDate.getValue() == null || datePickerEndDate.getValue() == null) {
            labelResponse.setText("Please select both start and end dates.");
            labelResponse.setVisible(true);
            return;
        }
       
        LocalDate startDate = datePickerStartDate.getValue();
        LocalDate endDate = datePickerEndDate.getValue();
       
        if (endDate.isBefore(startDate)) {
            labelResponse.setText("End date must be after start date.");
            labelResponse.setVisible(true);
            return;
        }
       
        ObservableList<Recruitment> recruitmentsInPeriod =
            appModel.getRecruitmentRegister().getRecruitmentsInPeriod(startDate, endDate);
        tableViewRecruitmentsInPeriod.setItems(recruitmentsInPeriod);
       
        labelResponse.setText("Found " + recruitmentsInPeriod.size() + " recruitments in selected period.");
        labelResponse.setVisible(true);
    }


    @FXML
    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) labelResponse.getScene().getWindow();
        stage.close();
    }


    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        updateStatistics();
    }


    private void updateStatistics() {
        // Calculate average days to acceptance
        double avgDays = appModel.getRecruitmentRegister().getAverageDaysToAcceptance();
        labelAverageDaysToAcceptance.setText(String.format("Average Days to Acceptance: %.1f", avgDays));
       
        // Calculate average interviews per offer
        double avgInterviews = appModel.getInterviewRegister().getAverageInterviewsPerOffer();
        labelAverageInterviewsPerOffer.setText(String.format("Average Interviews per Offer: %.1f", avgInterviews));
       
        // Find most popular role
        Role mostPopular = appModel.getRoleRegister().getMostPopularRole();
        if (mostPopular != null) {
            labelMostPopularRole.setText("Most Popular Role: " + mostPopular.getTitle() + " (" + mostPopular.getId() + ")");
        } else {
            labelMostPopularRole.setText("Most Popular Role: N/A");
        }
    }
}
