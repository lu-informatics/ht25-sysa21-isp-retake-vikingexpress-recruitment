package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Candidate {
    private String id;
    private String name;
    private String email;
    private String phone;
    private ObservableList<Application> applications;
    private ObservableList<Interview> interviews;


    public Candidate(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.applications = FXCollections.observableArrayList();
        this.interviews = FXCollections.observableArrayList();
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void addApplication(Application application) {
        if (!this.applications.contains(application)) {
            this.applications.add(application);
        }
    }


    public void removeApplication(Application application) {
        this.applications.remove(application);
    }


    public ObservableList<Application> getApplications() {
        return FXCollections.unmodifiableObservableList(applications);
    }


    public void addInterview(Interview interview) {
        if (!this.interviews.contains(interview)) {
            this.interviews.add(interview);
        }
    }


    public void removeInterview(Interview interview) {
        this.interviews.remove(interview);
    }


    public ObservableList<Interview> getInterviews() {
        return FXCollections.unmodifiableObservableList(interviews);
    }


    @Override
    public String toString() {
        return id + " - " + name;
    }
}




