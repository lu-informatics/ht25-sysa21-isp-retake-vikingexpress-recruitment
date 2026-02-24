package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Role {
    private String id;
    private String title;
    private String description;
    private String department;
    private ObservableList<Recruitment> recruitments;


    public Role(String id, String title, String description, String department) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.department = department;
        this.recruitments = FXCollections.observableArrayList();
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getDepartment() {
        return department;
    }


    public void setDepartment(String department) {
        this.department = department;
    }


    public void addRecruitment(Recruitment recruitment) {
        if (!this.recruitments.contains(recruitment)) {
            this.recruitments.add(recruitment);
        }
    }


    public void removeRecruitment(Recruitment recruitment) {
        this.recruitments.remove(recruitment);
    }


    public ObservableList<Recruitment> getRecruitments() {
        return FXCollections.unmodifiableObservableList(recruitments);
    }


    public int getOngoingRecruitmentsCount() {
        int count = 0;
        for (Recruitment r : recruitments) {
            if (r.isOngoing()) {
                count++;
            }
        }
        return count;
    }


    @Override
    public String toString() {
        return id + " - " + title;
    }
}
