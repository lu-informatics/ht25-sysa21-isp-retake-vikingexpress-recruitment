package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ApplicationRegister {
    private ObservableList<Application> applications;
    private int applicationCounter;


    public ApplicationRegister() {
        this.applications = FXCollections.observableArrayList();
        this.applicationCounter = 1;
    }


    public ObservableList<Application> getApplications() {
        return FXCollections.unmodifiableObservableList(this.applications);
    }


    public void addApplication(Application application) {
        this.applications.add(application);
    }


    public void removeApplication(Application application) {
    if (application.getCandidate() != null) {
    application.getCandidate().removeApplication(application);
    }
    if (application.getRecruitment() != null) {
    application.getRecruitment().removeApplication(application);
    }
    this.applications.remove(application);
    }


    public void removeApplicationsByCandidate(Candidate candidate) {
    ObservableList<Application> toRemove = FXCollections.observableArrayList();
    for (Application app : applications) {
        if (app.getCandidate() != null && app.getCandidate().equals(candidate)) {
            toRemove.add(app);
        }
    }
    for (Application app : toRemove) {
        if (app.getRecruitment() != null) {
            app.getRecruitment().removeApplication(app);
        }
        applications.remove(app);
    }
    }
public void removeApplicationsByRecruitment(Recruitment recruitment) {
    ObservableList<Application> toRemove = FXCollections.observableArrayList();
    for (Application app : applications) {
        if (app.getRecruitment() != null && app.getRecruitment().equals(recruitment)) {
            toRemove.add(app);
        }
    }
    for (Application app : toRemove) {
        removeApplication(app);
    }
}



    public Application findApplicationById(String id) {
        for (Application application : applications) {
            if (application.getId().equals(id)) {
                return application;
            }
        }
        return null;
    }


    public String generateApplicationId() {
        String id = String.format("APP-%06d", applicationCounter);
        applicationCounter++;
        return id;
    }


    public void setApplicationCounter(int counter) {
        this.applicationCounter = counter;
    }
}


