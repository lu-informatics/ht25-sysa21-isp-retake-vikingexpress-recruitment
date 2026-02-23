package se.lu.ics.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import se.lu.ics.models.*;
import java.time.LocalDate;


public class ApplicationDialogController {
    private AppModel appModel;
    private Application application;


    @FXML private TextField textFieldId;
    @FXML private ComboBox<Candidate> comboBoxCandidate;
    @FXML private ComboBox<Recruitment> comboBoxRecruitment;
    @FXML private DatePicker datePickerApplication;
    @FXML private TextField textFieldRanking;
    @FXML private CheckBox checkBoxOfferAccepted;
    @FXML private DatePicker datePickerOfferAccepted;
    @FXML private Label labelResponse;


    @FXML
    public void handleSave(ActionEvent event) {
        if (validateInput()) {
            if (application == null) {
                String id = appModel.getApplicationRegister().generateApplicationId();
                Candidate candidate = comboBoxCandidate.getValue();
                Recruitment recruitment = comboBoxRecruitment.getValue();
                LocalDate applicationDate = datePickerApplication.getValue();
               
                Application newApplication = new Application(id, candidate, recruitment, applicationDate);
               
                if (!textFieldRanking.getText().isEmpty()) {
    try {
        int ranking = Integer.parseInt(textFieldRanking.getText().trim());
        if (ranking < 1) {
            labelResponse.setText("Ranking must be a positive integer.");
            labelResponse.setVisible(true);
            return;
        }
        newApplication.setRanking(ranking);
    } catch (NumberFormatException e) {
        labelResponse.setText("Invalid ranking value.");
        labelResponse.setVisible(true);
        return;
    }
}
               
                if (checkBoxOfferAccepted.isSelected() && datePickerOfferAccepted.getValue() != null) {
                    newApplication.acceptOffer(datePickerOfferAccepted.getValue());
                }
               
                appModel.getApplicationRegister().addApplication(newApplication);
               
                labelResponse.setText("Application created with ID: " + id);
                labelResponse.setVisible(true);
                clearFields();
            } else {
                application.setCandidate(comboBoxCandidate.getValue());
                application.setRecruitment(comboBoxRecruitment.getValue());
                application.setApplicationDate(datePickerApplication.getValue());
               
             if (!textFieldRanking.getText().isEmpty()) {
    try {
        int ranking = Integer.parseInt(textFieldRanking.getText().trim());
        if (ranking < 1) {
            labelResponse.setText("Ranking must be a positive integer.");
            labelResponse.setVisible(true);
            return;
        }
        application.setRanking(ranking);
    } catch (NumberFormatException e) {
        labelResponse.setText("Invalid ranking value.");
        labelResponse.setVisible(true);
        return;
    }
}
                if (checkBoxOfferAccepted.isSelected() && datePickerOfferAccepted.getValue() != null) {
                    application.acceptOffer(datePickerOfferAccepted.getValue());
                } else {
                    application.setOfferAccepted(false);
                    application.setOfferAcceptedDate(null);
                }
               
                labelResponse.setText("Application updated successfully.");
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
        if (datePickerApplication.getValue() == null) {
            labelResponse.setText("Please select application date.");
            labelResponse.setVisible(true);
            return false;
        }
        if (checkBoxOfferAccepted.isSelected() && datePickerOfferAccepted.getValue() == null) {
            labelResponse.setText("Please select offer acceptance date.");
            labelResponse.setVisible(true);
            return false;
        }
        return true;
    }


    private void clearFields() {
        textFieldId.clear();
        comboBoxCandidate.setValue(null);
        comboBoxRecruitment.setValue(null);
        datePickerApplication.setValue(null);
        textFieldRanking.clear();
        checkBoxOfferAccepted.setSelected(false);
        datePickerOfferAccepted.setValue(null);
    }


    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        comboBoxCandidate.setItems(appModel.getCandidateRegister().getCandidates());
        comboBoxRecruitment.setItems(appModel.getRecruitmentRegister().getRecruitments());
    }


    public void setApplication(Application application) {
        this.application = application;
       
        if (application != null) {
            textFieldId.setText(application.getId());
            textFieldId.setEditable(false);
            comboBoxCandidate.setValue(application.getCandidate());
            comboBoxRecruitment.setValue(application.getRecruitment());
            datePickerApplication.setValue(application.getApplicationDate());
            textFieldRanking.setText(application.getRanking() > 0 ? String.valueOf(application.getRanking()) : "");
            checkBoxOfferAccepted.setSelected(application.isOfferAccepted());
            datePickerOfferAccepted.setValue(application.getOfferAcceptedDate());
        } else {
            textFieldId.setText("Auto-generated");
            textFieldId.setEditable(false);
        }
    }
}
