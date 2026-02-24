package se.lu.ics.controllers;


import java.io.IOException;
import java.net.URL;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.lu.ics.models.*;


public class AppController {
    private final Stage primaryStage;
    private final AppModel appModel;


    public AppController(Stage primaryStage, AppModel appModel) {
        this.primaryStage = primaryStage;
        this.appModel = appModel;
    }


    public void showMainView() throws IOException {
        URL url = getClass().getResource("/fxml/MainView.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load());


        MainViewController controller = loader.getController();
        controller.setAppController(this);
        controller.setAppModel(appModel);


        primaryStage.setScene(scene);
        primaryStage.setTitle("VikingExpress Recruitment System");
        primaryStage.show();
    }


    public void showRecruitmentDialog(Recruitment recruitment) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RecruitmentDialog.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));


        RecruitmentDialogController controller = loader.getController();
        controller.setAppModel(appModel);
        controller.setRecruitment(recruitment);


        modalStage.setTitle(recruitment == null ? "Add Recruitment" : "Edit Recruitment");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }


    public void showRoleDialog(Role role) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RoleDialog.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));


        RoleDialogController controller = loader.getController();
        controller.setAppModel(appModel);
        controller.setRole(role);


        modalStage.setTitle(role == null ? "Add Role" : "Edit Role");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }


    public void showCandidateDialog(Candidate candidate) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CandidateDialog.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));


        CandidateDialogController controller = loader.getController();
        controller.setAppModel(appModel);
        controller.setCandidate(candidate);


        modalStage.setTitle(candidate == null ? "Add Candidate" : "Edit Candidate");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }


    public void showInterviewDialog(Interview interview) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InterviewDialog.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));


        InterviewDialogController controller = loader.getController();
        controller.setAppModel(appModel);
        controller.setInterview(interview);


        modalStage.setTitle(interview == null ? "Book Interview" : "Edit Interview");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }


    public void showApplicationDialog(Application application) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ApplicationDialog.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));


        ApplicationDialogController controller = loader.getController();
        controller.setAppModel(appModel);
        controller.setApplication(application);


        modalStage.setTitle(application == null ? "Add Application" : "Edit Application");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }


    public void showApplicationsForRecruitmentView(Recruitment recruitment) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ApplicationsForRecruitmentView.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));


        ApplicationsForRecruitmentViewController controller = loader.getController();
        controller.setRecruitment(recruitment);
        controller.setAppController(this);


        modalStage.setTitle("Applications for " + recruitment.getId());
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }


    public void showCandidateHistoryView(Candidate candidate) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CandidateHistoryView.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));


        CandidateHistoryViewController controller = loader.getController();
        controller.setCandidate(candidate);


        modalStage.setTitle("Application History for " + candidate.getName());
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }


    public void showStatisticsView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StatisticsView.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));


        StatisticsViewController controller = loader.getController();
        controller.setAppModel(appModel);


        modalStage.setTitle("Statistics");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }
}
