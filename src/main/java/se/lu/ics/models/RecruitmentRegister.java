package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class RecruitmentRegister {
    private ObservableList<Recruitment> recruitments;
    private int yearlyCounter;
    private int currentYear;


    public RecruitmentRegister() {
        this.recruitments = FXCollections.observableArrayList();
        this.currentYear = LocalDate.now().getYear();
        this.yearlyCounter = 1;
    }


    public ObservableList<Recruitment> getRecruitments() {
        return FXCollections.unmodifiableObservableList(this.recruitments);
    }


    public void addRecruitment(Recruitment recruitment) {
        this.recruitments.add(recruitment);
    }


    public void removeRecruitment(Recruitment recruitment) {

    
        if (recruitment.getRole() != null) {
        recruitment.setRole(null);
        }

    
        this.recruitments.remove(recruitment);
}


    public Recruitment findRecruitmentById(String id) {
        for (Recruitment recruitment : recruitments) {
            if (recruitment.getId().equals(id)) {
                return recruitment;
            }
        }
        return null;
    }


    public String generateRecruitmentId() {
        int year = LocalDate.now().getYear();
       
       
        if (year != currentYear) {
            currentYear = year;
            yearlyCounter = 1;
        }
       
        String id = String.format("HR %d/%d", year, yearlyCounter);
        yearlyCounter++;
        return id;
    }


    public void setYearlyCounter(int counter) {
        this.yearlyCounter = counter;
    }


    public void setCurrentYear(int year) {
        this.currentYear = year;
    }


    public ObservableList<Recruitment> getRecruitmentsInPeriod(LocalDate startDate, LocalDate endDate) {
        ObservableList<Recruitment> recruitmentsInPeriod = FXCollections.observableArrayList();
        for (Recruitment recruitment : recruitments) {
            if (recruitment.isInPeriod(startDate, endDate)) {
                recruitmentsInPeriod.add(recruitment);
            }
        }
        return recruitmentsInPeriod;
    }


    public double getAverageDaysToAcceptance() {
        int count = 0;
        long totalDays = 0;
       
        for (Recruitment recruitment : recruitments) {
            if (recruitment.getOfferAcceptedDate() != null) {
                long days = ChronoUnit.DAYS.between(
                    recruitment.getPostingDate(),
                    recruitment.getOfferAcceptedDate()
                );
                totalDays += days;
                count++;
            }
        }
       
        return count > 0 ? (double) totalDays / count : 0.0;
    }
}
