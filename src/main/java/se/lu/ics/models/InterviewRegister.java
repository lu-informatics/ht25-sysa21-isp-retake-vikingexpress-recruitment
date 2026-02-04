package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class InterviewRegister {
    private ObservableList<Interview> interviews;
    private int interviewCounter;


    public InterviewRegister() {
        this.interviews = FXCollections.observableArrayList();
        this.interviewCounter = 1;
    }


    public ObservableList<Interview> getInterviews() {
        return FXCollections.unmodifiableObservableList(this.interviews);
    }


    public void addInterview(Interview interview) {
        this.interviews.add(interview);
    }


    public void removeInterview(Interview interview) {
        this.interviews.remove(interview);
    }


    public Interview findInterviewById(String id) {
        for (Interview interview : interviews) {
            if (interview.getId().equals(id)) {
                return interview;
            }
        }
        return null;
    }


    public String generateInterviewId() {
        String id = String.format("INT-%05d", interviewCounter);
        interviewCounter++;
        return id;
    }


    public void setInterviewCounter(int counter) {
        this.interviewCounter = counter;
    }


    public double getAverageInterviewsPerOffer() {
       
        int totalInterviews = 0;
        int offersAccepted = 0;
       
       
        ObservableList<Recruitment> recruitmentsWithOffers = FXCollections.observableArrayList();
       
        for (Interview interview : interviews) {
            Recruitment recruitment = interview.getRecruitment();
            if (recruitment != null && recruitment.getOfferAcceptedDate() != null) {
                if (!recruitmentsWithOffers.contains(recruitment)) {
                    recruitmentsWithOffers.add(recruitment);
                }
            }
        }
       
       
        for (Recruitment recruitment : recruitmentsWithOffers) {
            int interviewsForRecruitment = 0;
            for (Interview interview : interviews) {
                if (interview.getRecruitment().equals(recruitment)) {
                    interviewsForRecruitment++;
                }
            }
            totalInterviews += interviewsForRecruitment;
            offersAccepted++;
        }
       
        return offersAccepted > 0 ? (double) totalInterviews / offersAccepted : 0.0;
    }
}
