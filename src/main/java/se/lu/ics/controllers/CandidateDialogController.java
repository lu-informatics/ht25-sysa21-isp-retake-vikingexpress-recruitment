package se.lu.ics.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import se.lu.ics.models.*;


public class CandidateDialogController {
    private AppModel appModel;
    private Candidate candidate;


    @FXML private TextField textFieldId;
    @FXML private TextField textFieldName;
    @FXML private TextField textFieldEmail;
    @FXML private TextField textFieldPhone;
    @FXML private Label labelResponse;


    @FXML
    public void handleSave(ActionEvent event) {
        if (validateInput()) {
            if (candidate == null) {
                // Create new candidate
                String id = appModel.getCandidateRegister().generateCandidateId();
                String name = textFieldName.getText();
                String email = textFieldEmail.getText();
                String phone = textFieldPhone.getText();
               
                Candidate newCandidate = new Candidate(id, name, email, phone);
                appModel.getCandidateRegister().addCandidate(newCandidate);
               
                labelResponse.setText("Candidate created with ID: " + id);
                labelResponse.setVisible(true);
               
                clearFields();
            } else {
                // Update existing candidate
                candidate.setName(textFieldName.getText());
                candidate.setEmail(textFieldEmail.getText());
                candidate.setPhone(textFieldPhone.getText());
               
                labelResponse.setText("Candidate updated successfully.");
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
        if (textFieldName.getText().isEmpty()) {
            labelResponse.setText("Please enter candidate name.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (textFieldEmail.getText().isEmpty()) {
            labelResponse.setText("Please enter email address.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (textFieldPhone.getText().isEmpty()) {
            labelResponse.setText("Please enter phone number.");
            labelResponse.setVisible(true);
            return false;
        }
       
        return true;
    }


    private void clearFields() {
        textFieldId.clear();
        textFieldName.clear();
        textFieldEmail.clear();
        textFieldPhone.clear();
    }


    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }


    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
       
        if (candidate != null) {
            textFieldId.setText(candidate.getId());
            textFieldId.setEditable(false);
            textFieldName.setText(candidate.getName());
            textFieldEmail.setText(candidate.getEmail());
            textFieldPhone.setText(candidate.getPhone());
        } else {
            textFieldId.setText("Auto-generated");
            textFieldId.setEditable(false);
        }
    }
}
