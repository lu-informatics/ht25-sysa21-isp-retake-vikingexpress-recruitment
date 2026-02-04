package se.lu.ics.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import se.lu.ics.models.*;


public class RoleDialogController {
    private AppModel appModel;
    private Role role;


    @FXML private TextField textFieldId;
    @FXML private TextField textFieldTitle;
    @FXML private TextArea textAreaDescription;
    @FXML private TextField textFieldDepartment;
    @FXML private Label labelResponse;


    @FXML
    public void handleSave(ActionEvent event) {
        if (validateInput()) {
            if (role == null) {
                // Create new role
                String id = textFieldId.getText();
                String title = textFieldTitle.getText();
                String description = textAreaDescription.getText();
                String department = textFieldDepartment.getText();
               
                Role newRole = new Role(id, title, description, department);
                appModel.getRoleRegister().addRole(newRole);
               
                labelResponse.setText("Role created successfully.");
                labelResponse.setVisible(true);
               
                clearFields();
            } else {
                // Update existing role
                role.setTitle(textFieldTitle.getText());
                role.setDescription(textAreaDescription.getText());
                role.setDepartment(textFieldDepartment.getText());
               
                labelResponse.setText("Role updated successfully.");
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
        if (textFieldId.getText().isEmpty()) {
            labelResponse.setText("Please enter a role ID.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (textFieldTitle.getText().isEmpty()) {
            labelResponse.setText("Please enter a role title.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (textAreaDescription.getText().isEmpty()) {
            labelResponse.setText("Please enter a description.");
            labelResponse.setVisible(true);
            return false;
        }
       
        if (textFieldDepartment.getText().isEmpty()) {
            labelResponse.setText("Please enter a department.");
            labelResponse.setVisible(true);
            return false;
        }
       
        return true;
    }


    private void clearFields() {
        textFieldId.clear();
        textFieldTitle.clear();
        textAreaDescription.clear();
        textFieldDepartment.clear();
    }


    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }


    public void setRole(Role role) {
        this.role = role;
       
        if (role != null) {
            textFieldId.setText(role.getId());
            textFieldId.setEditable(false);
            textFieldTitle.setText(role.getTitle());
            textAreaDescription.setText(role.getDescription());
            textFieldDepartment.setText(role.getDepartment());
        }
    }
}
