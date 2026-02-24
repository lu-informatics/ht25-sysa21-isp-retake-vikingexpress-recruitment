package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;


public class Recruitment {
    private String id;
    private Role role;
    private LocalDate postingDate;
    private LocalDate applicationDeadline;
    private String status;
    private LocalDate offerAcceptedDate;
    private ObservableList<Application> applications;


    public Recruitment(String id, Role role, LocalDate postingDate, LocalDate applicationDeadline) {
        this.id = id;
        this.role = role;
        this.postingDate = postingDate;
        this.applicationDeadline = applicationDeadline;
        this.status = "Open";
        this.applications = FXCollections.observableArrayList();
       
       
        if (role != null && !role.getRecruitments().contains(this)) {
            role.addRecruitment(this);
        }
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
       
        if (this.role != null && this.role.getRecruitments().contains(this)) {
            this.role.removeRecruitment(this);
        }
       
        this.role = role;
       
       
        if (role != null && !role.getRecruitments().contains(this)) {
            role.addRecruitment(this);
        }
    }


    public LocalDate getPostingDate() {
        return postingDate;
    }


    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }


    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }


    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDate getOfferAcceptedDate() {
        return offerAcceptedDate;
    }


    public void setOfferAcceptedDate(LocalDate offerAcceptedDate) {
        this.offerAcceptedDate = offerAcceptedDate;
    }


    public void addApplication(Application application) {
        if (!this.applications.contains(application)) {
            this.applications.add(application);
            if (application.getRecruitment() != this) {
                application.setRecruitment(this);
            }
        }
    }


    public void removeApplication(Application application) {
        this.applications.remove(application);
    }


    public ObservableList<Application> getApplications() {
        return FXCollections.unmodifiableObservableList(applications);
    }


    public int getApplicantCount() {
        return applications.size();
    }


    public boolean isOngoing() {
        return status.equals("Open") && LocalDate.now().isBefore(applicationDeadline);
    }


    public boolean isInPeriod(LocalDate startDate, LocalDate endDate) {
        return !postingDate.isAfter(endDate) && !applicationDeadline.isBefore(startDate);
    }


    @Override
    public String toString() {
        return id + " - " + (role != null ? role.getTitle() : "No role");
    }
}
