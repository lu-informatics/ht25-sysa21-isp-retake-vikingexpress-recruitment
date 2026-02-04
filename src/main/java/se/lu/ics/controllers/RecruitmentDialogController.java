package se.lu.ics.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import se.lu.ics.models.*;
import java.time.LocalDate;


public class RecruitmentDialogController {
    private AppModel appModel;
    private Recruitment recruitment;


    @FXML private TextField textFieldId;
    @FXML private ComboBox<Role> comboBoxRole;
    @FXML private DatePicker datePickerPosting;
    @FXML private DatePicker datePickerDeadline;
    @FXML private ComboBox<String> comboBoxStatus;
    @FXML private Label labelResponse;


    public void initialize() {
        comboBoxStatus.getItems().addAll("Open", "Closed", "Filled");
    }


    @FXML
    public void handleSave(ActionEvent event) {
        if (validateInput()) {
            if (recruitment == null) {
                // Create new recruitment
                String id = appModel.getRecruitmentRegister().generateRecruitmentId();
                Role role = comboBoxRole.getValue();
                LocalDate postingDate = datePickerPosting.getValue();
                LocalDate deadline = datePickerDeadline.getValue();
               
                Recruitment newRecruitment = new Recruitment(id, role, postingDate, deadline);
                newRecruitment.setStatus(comboBoxStatus.getValue());
               
                appModel.getRecruitmentRegister().addRecruitment(newRecruitment);
               
                labelResponse.setText("Recruitment created with ID: " + id);
                labelResponse.setVisible(true);
               
                clearFields();
            } else {
                // Update existing recruitment
                recruitment.setRole(comboBoxRole.getValue());
                recruitment.setPostingDate(datePickerPosting.getValue());
                recruitment.setApplicationDeadline(datePickerDeadline.getValue());
                recruitment.setStatus(comboBoxStatus.getValue());
               
                labelResponse.setText("Recruitment updated successfully.");
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
        if (comboBoxRole.getValue() == null) {
            labelResponse.setText("Please select a role.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (datePickerPosting.getValue() == null) {
            labelResponse.setText("Please select a posting date.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (datePickerDeadline.getValue() == null) {
            labelResponse.setText("Please select an application deadline.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (datePickerDeadline.getValue().isBefore(LocalDate.now())) {
            labelResponse.setText("Application deadline cannot be in the past.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (datePickerDeadline.getValue().isBefore(datePickerPosting.getValue())) {
            labelResponse.setText("Deadline must be after posting date.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (comboBoxStatus.getValue() == null || comboBoxStatus.getValue().isEmpty()) {
            labelResponse.setText("Please select a status.");
            labelResponse.setVisible(true);
            return false;
        }
       
        return true;
    }


    private void clearFields() {
        textFieldId.clear();
        comboBoxRole.setValue(null);
        datePickerPosting.setValue(null);
        datePickerDeadline.setValue(null);
        comboBoxStatus.setValue("Open");
    }


    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        comboBoxRole.setItems(appModel.getRoleRegister().getRoles());
    }


    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
       
        if (recruitment != null) {
            textFieldId.setText(recruitment.getId());
            textFieldId.setEditable(false);
            comboBoxRole.setValue(recruitment.getRole());
            datePickerPosting.setValue(recruitment.getPostingDate());
            datePickerDeadline.setValue(recruitment.getApplicationDeadline());
            comboBoxStatus.setValue(recruitment.getStatus());
        } else {
            textFieldId.setText("Auto-generated");
            textFieldId.setEditable(false);
            comboBoxStatus.setValue("Open");
        }
    }
}
