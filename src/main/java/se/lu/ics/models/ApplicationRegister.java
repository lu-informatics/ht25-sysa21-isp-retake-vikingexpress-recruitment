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
        this.applications.remove(application);
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


