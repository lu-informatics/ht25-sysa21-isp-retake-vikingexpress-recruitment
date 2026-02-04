package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class RoleRegister {
    private ObservableList<Role> roles;


    public RoleRegister() {
        this.roles = FXCollections.observableArrayList();
    }


    public ObservableList<Role> getRoles() {
        return FXCollections.unmodifiableObservableList(this.roles);
    }


    public void addRole(Role role) {
        this.roles.add(role);
    }


    public void removeRole(Role role) {
        this.roles.remove(role);
    }


    public Role findRoleById(String id) {
        for (Role role : roles) {
            if (role.getId().equals(id)) {
                return role;
            }
        }
        return null;
    }


    public Role getMostPopularRole() {
        if (roles.isEmpty()) {
            return null;
        }
       
        Role mostPopular = roles.get(0);
        int maxApplications = 0;
       
        for (Role role : roles) {
            int totalApplications = 0;
            for (Recruitment recruitment : role.getRecruitments()) {
                totalApplications += recruitment.getApplicantCount();
            }
            if (totalApplications > maxApplications) {
                maxApplications = totalApplications;
                mostPopular = role;
            }
        }
       
        return mostPopular;
    }
}
