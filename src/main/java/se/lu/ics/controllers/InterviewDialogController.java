package se.lu.ics.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import se.lu.ics.models.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class InterviewDialogController {
    private AppModel appModel;
    private Interview interview;


    @FXML private TextField textFieldId;
    @FXML private ComboBox<Candidate> comboBoxCandidate;
    @FXML private ComboBox<Recruitment> comboBoxRecruitment;
    @FXML private DatePicker datePicker;
    @FXML private TextField textFieldTime;
    @FXML private TextField textFieldLocation;
    @FXML private TextField textFieldInterviewer;
    @FXML private Label labelResponse;


    @FXML
    public void handleSave(ActionEvent event) {
        if (validateInput()) {
            try {
                LocalTime time = LocalTime.parse(textFieldTime.getText());
                LocalDateTime dateTime = LocalDateTime.of(datePicker.getValue(), time);
               
                if (interview == null) {
                    String id = appModel.getInterviewRegister().generateInterviewId();
                    Candidate candidate = comboBoxCandidate.getValue();
                    Recruitment recruitment = comboBoxRecruitment.getValue();
                    String location = textFieldLocation.getText();
                    String interviewer = textFieldInterviewer.getText();
                   
                    Interview newInterview = new Interview(id, candidate, recruitment,
                                                          dateTime, location, interviewer);
                    appModel.getInterviewRegister().addInterview(newInterview);
                   
                    labelResponse.setText("Interview booked with ID: " + id);
                    labelResponse.setVisible(true);
                    clearFields();
                } else {
                    interview.setCandidate(comboBoxCandidate.getValue());
                    interview.setRecruitment(comboBoxRecruitment.getValue());
                    interview.setDateTime(dateTime);
                    interview.setLocation(textFieldLocation.getText());
                    interview.setInterviewer(textFieldInterviewer.getText());
                   
                    labelResponse.setText("Interview updated successfully.");
                    labelResponse.setVisible(true);
                }
            } catch (Exception e) {
                labelResponse.setText("Invalid time format. Use HH:mm (e.g., 14:30)");
                labelResponse.setVisible(true);
            }
        }
    }


    @FXML
    public void handleCancel(ActionEvent event) {
        Stage stage = (Stage) labelResponse.getScene().getWindow();
        stage.close();
    }


    private boolean validateInput() {
        if (comboBoxCandidate.getValue() == null) {
            labelResponse.setText("Please select a candidate.");
            labelResponse.setVisible(true);
            return false;
        }
        if (comboBoxRecruitment.getValue() == null) {
            labelResponse.setText("Please select a recruitment.");
            labelResponse.setVisible(true);
            return false;
        }
        if (datePicker.getValue() == null) {
            labelResponse.setText("Please select a date.");
            labelResponse.setVisible(true);
            return false;
        }
        if (textFieldTime.getText().isEmpty()) {
            labelResponse.setText("Please enter a time.");
            labelResponse.setVisible(true);
            return false;
        }
        if (textFieldLocation.getText().isEmpty()) {
            labelResponse.setText("Please enter a location.");
            labelResponse.setVisible(true);
            return false;
        }
        if (textFieldInterviewer.getText().isEmpty()) {
            labelResponse.setText("Please enter interviewer name.");
            labelResponse.setVisible(true);
            return false;
        }
        return true;
    }


    private void clearFields() {
        textFieldId.clear();
        comboBoxCandidate.setValue(null);
        comboBoxRecruitment.setValue(null);
        datePicker.setValue(null);
        textFieldTime.clear();
        textFieldLocation.clear();
        textFieldInterviewer.clear();
    }


    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        comboBoxCandidate.setItems(appModel.getCandidateRegister().getCandidates());
        comboBoxRecruitment.setItems(appModel.getRecruitmentRegister().getRecruitments());
    }


    public void setInterview(Interview interview) {
        this.interview = interview;
       
        if (interview != null) {
            textFieldId.setText(interview.getId());
            textFieldId.setEditable(false);
            comboBoxCandidate.setValue(interview.getCandidate());
            comboBoxRecruitment.setValue(interview.getRecruitment());
            datePicker.setValue(interview.getDateTime().toLocalDate());
            textFieldTime.setText(interview.getDateTime().toLocalTime().toString());
            textFieldLocation.setText(interview.getLocation());
            textFieldInterviewer.setText(interview.getInterviewer());
        } else {
            textFieldId.setText("Auto-generated");
            textFieldId.setEditable(false);
        }
    }
}
