package se.lu.ics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import se.lu.ics.models.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainViewController {
    private AppModel appModel;
    private AppController appController;

    // Recruitment Tab
    @FXML private TableView<Recruitment> tableViewRecruitments;
    @FXML private TableColumn<Recruitment, String> colRecruitmentId;
    @FXML private TableColumn<Recruitment, String> colRecruitmentRole;
    @FXML private TableColumn<Recruitment, LocalDate> colRecruitmentPosting;
    @FXML private TableColumn<Recruitment, LocalDate> colRecruitmentDeadline;
    @FXML private TableColumn<Recruitment, String> colRecruitmentStatus;
    @FXML private Label labelRecruitmentResponse;

    // Role Tab
    @FXML private TableView<Role> tableViewRoles;
    @FXML private TableColumn<Role, String> colRoleId;
    @FXML private TableColumn<Role, String> colRoleTitle;
    @FXML private TableColumn<Role, String> colRoleDepartment;
    @FXML private TableColumn<Role, String> colRoleDescription;
    @FXML private TableColumn<Role, Integer> colRoleOngoingRecruitments;
    @FXML private Label labelRoleResponse;

    // Candidate Tab
    @FXML private TableView<Candidate> tableViewCandidates;
    @FXML private TableColumn<Candidate, String> colCandidateId;
    @FXML private TableColumn<Candidate, String> colCandidateName;
    @FXML private TableColumn<Candidate, String> colCandidateEmail;
    @FXML private TableColumn<Candidate, String> colCandidatePhone;
    @FXML private Label labelCandidateResponse;

    // Interview Tab
    @FXML private TableView<Interview> tableViewInterviews;
    @FXML private TableColumn<Interview, String> colInterviewId;
    @FXML private TableColumn<Interview, String> colInterviewCandidate;
    @FXML private TableColumn<Interview, String> colInterviewRecruitment;
    @FXML private TableColumn<Interview, LocalDateTime> colInterviewDateTime;
    @FXML private TableColumn<Interview, String> colInterviewLocation;
    @FXML private TableColumn<Interview, String> colInterviewInterviewer;
    @FXML private Label labelInterviewResponse;

    // Application Tab
    @FXML private TableView<Application> tableViewApplications;
    @FXML private TableColumn<Application, String> colApplicationId;
    @FXML private TableColumn<Application, String> colApplicationCandidate;
    @FXML private TableColumn<Application, String> colApplicationRecruitment;
    @FXML private TableColumn<Application, LocalDate> colApplicationDate;
    @FXML private TableColumn<Application, Integer> colApplicationRanking;
    @FXML private Label labelApplicationResponse;

    public void initialize() {
        setupRecruitmentTable();
        setupRoleTable();
        setupCandidateTable();
        setupInterviewTable();
        setupApplicationTable();
    }

    private void setupRecruitmentTable() {
        colRecruitmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRecruitmentRole.setCellValueFactory(cellData -> {
            Role role = cellData.getValue().getRole();
            return new javafx.beans.property.SimpleStringProperty(
                role != null ? role.getTitle() : "N/A"
            );
        });
        colRecruitmentPosting.setCellValueFactory(new PropertyValueFactory<>("postingDate"));
        colRecruitmentDeadline.setCellValueFactory(new PropertyValueFactory<>("applicationDeadline"));
        colRecruitmentStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void setupRoleTable() {
        colRoleId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRoleTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colRoleDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colRoleDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colRoleOngoingRecruitments.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleObjectProperty<>(
                cellData.getValue().getOngoingRecruitmentsCount()
            )
        );
    }

    private void setupCandidateTable() {
        colCandidateId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCandidateName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCandidateEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCandidatePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void setupInterviewTable() {
        colInterviewId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colInterviewCandidate.setCellValueFactory(cellData -> {
            Candidate candidate = cellData.getValue().getCandidate();
            return new javafx.beans.property.SimpleStringProperty(
                candidate != null ? candidate.getName() : "N/A"
            );
        });
        colInterviewRecruitment.setCellValueFactory(cellData -> {
            Recruitment recruitment = cellData.getValue().getRecruitment();
            return new javafx.beans.property.SimpleStringProperty(
                recruitment != null ? recruitment.getId() : "N/A"
            );
        });
        colInterviewDateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        colInterviewLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colInterviewInterviewer.setCellValueFactory(new PropertyValueFactory<>("interviewer"));

        colInterviewDateTime.setCellFactory(column -> new TableCell<Interview, LocalDateTime>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.format(formatter));
                }
            }
        });
    }

    private void setupApplicationTable() {
        colApplicationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colApplicationCandidate.setCellValueFactory(cellData -> {
            Candidate candidate = cellData.getValue().getCandidate();
            return new javafx.beans.property.SimpleStringProperty(
                candidate != null ? candidate.getName() : "N/A"
            );
        });
        colApplicationRecruitment.setCellValueFactory(cellData -> {
            Recruitment recruitment = cellData.getValue().getRecruitment();
            return new javafx.beans.property.SimpleStringProperty(
                recruitment != null ? recruitment.getId() : "N/A"
            );
        });
        colApplicationDate.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        colApplicationRanking.setCellValueFactory(new PropertyValueFactory<>("ranking"));
    }

    // Recruitment handlers
    @FXML
    public void handleAddRecruitment(ActionEvent event) {
        try {
            appController.showRecruitmentDialog(null);
            labelRecruitmentResponse.setText("Recruitment added successfully.");
            labelRecruitmentResponse.setVisible(true);
        } catch (IOException e) {
            labelRecruitmentResponse.setText("Error adding recruitment.");
            labelRecruitmentResponse.setVisible(true);
        }
        tableViewRoles.refresh();
    }

    @FXML
    public void handleEditRecruitment(ActionEvent event) {
        Recruitment selected = tableViewRecruitments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                appController.showRecruitmentDialog(selected);
                tableViewRecruitments.refresh();
                labelRecruitmentResponse.setText("Recruitment updated successfully.");
                labelRecruitmentResponse.setVisible(true);
            } catch (IOException e) {
                labelRecruitmentResponse.setText("Error editing recruitment.");
                labelRecruitmentResponse.setVisible(true);
            }
        } else {
            labelRecruitmentResponse.setText("Please select a recruitment to edit.");
            labelRecruitmentResponse.setVisible(true);
        }
        tableViewRoles.refresh();
    }

    @FXML
    public void handleDeleteRecruitment(ActionEvent event) {
        Recruitment selected = tableViewRecruitments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            appModel.getInterviewRegister().removeInterviewsByRecruitment(selected);
            appModel.getApplicationRegister().removeApplicationsByRecruitment(selected);
            appModel.getRecruitmentRegister().removeRecruitment(selected);
            labelRecruitmentResponse.setText("Recruitment deleted successfully.");
            labelRecruitmentResponse.setVisible(true);
        } else {
            labelRecruitmentResponse.setText("Please select a recruitment to delete.");
            labelRecruitmentResponse.setVisible(true);
        }
        tableViewRoles.refresh();
    }

    @FXML
    public void handleViewApplicants(ActionEvent event) {
        Recruitment selected = tableViewRecruitments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                appController.showApplicationsForRecruitmentView(selected);
            } catch (IOException e) {
                labelRecruitmentResponse.setText("Error viewing applicants.");
                labelRecruitmentResponse.setVisible(true);
            }
        } else {
            labelRecruitmentResponse.setText("Please select a recruitment.");
            labelRecruitmentResponse.setVisible(true);
        }
    }

    // Role handlers
    @FXML
    public void handleAddRole(ActionEvent event) {
        try {
            appController.showRoleDialog(null);
            labelRoleResponse.setText("Role added successfully.");
            labelRoleResponse.setVisible(true);
        } catch (IOException e) {
            labelRoleResponse.setText("Error adding role.");
            labelRoleResponse.setVisible(true);
        }
    }

    @FXML
    public void handleEditRole(ActionEvent event) {
        Role selected = tableViewRoles.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                appController.showRoleDialog(selected);
                tableViewRoles.refresh();
                labelRoleResponse.setText("Role updated successfully.");
                labelRoleResponse.setVisible(true);
            } catch (IOException e) {
                labelRoleResponse.setText("Error editing role.");
                labelRoleResponse.setVisible(true);
            }
        } else {
            labelRoleResponse.setText("Please select a role to edit.");
            labelRoleResponse.setVisible(true);
        }
    }

    @FXML
    public void handleDeleteRole(ActionEvent event) {
        Role selected = tableViewRoles.getSelectionModel().getSelectedItem();
        if (selected != null) {
            List<Recruitment> recruitmentsToRemove = new ArrayList<>(selected.getRecruitments());
            for (Recruitment recruitment : recruitmentsToRemove) {
                appModel.getInterviewRegister().removeInterviewsByRecruitment(recruitment);
                appModel.getApplicationRegister().removeApplicationsByRecruitment(recruitment);
                appModel.getRecruitmentRegister().removeRecruitment(recruitment);
            }
            appModel.getRoleRegister().removeRole(selected);
            labelRoleResponse.setText("Role deleted successfully.");
            labelRoleResponse.setVisible(true);
            tableViewRoles.refresh();
            tableViewRecruitments.refresh();
            tableViewApplications.refresh();
            tableViewInterviews.refresh();
        } else {
            labelRoleResponse.setText("Please select a role to delete.");
            labelRoleResponse.setVisible(true);
        }
    }

    // Candidate handlers
    @FXML
    public void handleAddCandidate(ActionEvent event) {
        try {
            appController.showCandidateDialog(null);
            labelCandidateResponse.setText("Candidate added successfully.");
            labelCandidateResponse.setVisible(true);
        } catch (IOException e) {
            labelCandidateResponse.setText("Error adding candidate.");
            labelCandidateResponse.setVisible(true);
        }
    }

    @FXML
    public void handleEditCandidate(ActionEvent event) {
        Candidate selected = tableViewCandidates.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                appController.showCandidateDialog(selected);
                tableViewCandidates.refresh();
                labelCandidateResponse.setText("Candidate updated successfully.");
                labelCandidateResponse.setVisible(true);
            } catch (IOException e) {
                labelCandidateResponse.setText("Error editing candidate.");
                labelCandidateResponse.setVisible(true);
            }
        } else {
            labelCandidateResponse.setText("Please select a candidate to edit.");
            labelCandidateResponse.setVisible(true);
        }
    }

    @FXML
    public void handleDeleteCandidate(ActionEvent event) {
        Candidate selected = tableViewCandidates.getSelectionModel().getSelectedItem();
        if (selected != null) {
            appModel.getInterviewRegister().removeInterviewsByCandidate(selected);
            appModel.getApplicationRegister().removeApplicationsByCandidate(selected);
            appModel.getCandidateRegister().removeCandidate(selected);
            labelCandidateResponse.setText("Candidate deleted successfully.");
            labelCandidateResponse.setVisible(true);
        } else {
            labelCandidateResponse.setText("Please select a candidate to delete.");
            labelCandidateResponse.setVisible(true);
        }
    }

    @FXML
    public void handleViewCandidateHistory(ActionEvent event) {
        Candidate selected = tableViewCandidates.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                appController.showCandidateHistoryView(selected);
            } catch (IOException e) {
                labelCandidateResponse.setText("Error viewing candidate history.");
                labelCandidateResponse.setVisible(true);
            }
        } else {
            labelCandidateResponse.setText("Please select a candidate.");
            labelCandidateResponse.setVisible(true);
        }
    }

    // Interview handlers
    @FXML
    public void handleBookInterview(ActionEvent event) {
        try {
            appController.showInterviewDialog(null);
            labelInterviewResponse.setText("Interview booked successfully.");
            labelInterviewResponse.setVisible(true);
        } catch (IOException e) {
            labelInterviewResponse.setText("Error booking interview.");
            labelInterviewResponse.setVisible(true);
        }
    }

    @FXML
    public void handleEditInterview(ActionEvent event) {
        Interview selected = tableViewInterviews.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                appController.showInterviewDialog(selected);
                tableViewInterviews.refresh();
                labelInterviewResponse.setText("Interview updated successfully.");
                labelInterviewResponse.setVisible(true);
            } catch (IOException e) {
                labelInterviewResponse.setText("Error editing interview.");
                labelInterviewResponse.setVisible(true);
            }
        } else {
            labelInterviewResponse.setText("Please select an interview to edit.");
            labelInterviewResponse.setVisible(true);
        }
    }

    @FXML
    public void handleDeleteInterview(ActionEvent event) {
        Interview selected = tableViewInterviews.getSelectionModel().getSelectedItem();
        if (selected != null) {
            appModel.getInterviewRegister().removeInterview(selected);
            labelInterviewResponse.setText("Interview deleted successfully.");
            labelInterviewResponse.setVisible(true);
        } else {
            labelInterviewResponse.setText("Please select an interview to delete.");
            labelInterviewResponse.setVisible(true);
        }
    }

    // Application handlers
    @FXML
    public void handleAddApplication(ActionEvent event) {
        try {
            appController.showApplicationDialog(null);
            labelApplicationResponse.setText("Application added successfully.");
            labelApplicationResponse.setVisible(true);
        } catch (IOException e) {
            labelApplicationResponse.setText("Error adding application.");
            labelApplicationResponse.setVisible(true);
        }
    }

    @FXML
    public void handleEditApplication(ActionEvent event) {
        Application selected = tableViewApplications.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                appController.showApplicationDialog(selected);
                tableViewApplications.refresh();
                tableViewRecruitments.refresh();
                labelApplicationResponse.setText("Application updated successfully.");
                labelApplicationResponse.setVisible(true);
            } catch (IOException e) {
                labelApplicationResponse.setText("Error editing application.");
                labelApplicationResponse.setVisible(true);
            }
        } else {
            labelApplicationResponse.setText("Please select an application to edit.");
            labelApplicationResponse.setVisible(true);
        }
    }

    @FXML
    public void handleDeleteApplication(ActionEvent event) {
        Application selected = tableViewApplications.getSelectionModel().getSelectedItem();
        if (selected != null) {
            appModel.getApplicationRegister().removeApplication(selected);
            labelApplicationResponse.setText("Application deleted successfully.");
            labelApplicationResponse.setVisible(true);
        } else {
            labelApplicationResponse.setText("Please select an application to delete.");
            labelApplicationResponse.setVisible(true);
        }
    }

    @FXML
    public void handleViewStatistics(ActionEvent event) {
        try {
            appController.showStatisticsView();
        } catch (IOException e) {
            labelRecruitmentResponse.setText("Error viewing statistics.");
            labelRecruitmentResponse.setVisible(true);
        }
    }

    private void populateTables() {
        tableViewRecruitments.setItems(appModel.getRecruitmentRegister().getRecruitments());
        tableViewRoles.setItems(appModel.getRoleRegister().getRoles());
        tableViewCandidates.setItems(appModel.getCandidateRegister().getCandidates());
        tableViewInterviews.setItems(appModel.getInterviewRegister().getInterviews());
        tableViewApplications.setItems(appModel.getApplicationRegister().getApplications());
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
        populateTables();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
