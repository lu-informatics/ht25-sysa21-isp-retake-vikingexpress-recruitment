package se.lu.ics.models;


import java.time.LocalDate;


public class Application {
    private String id;
    private Candidate candidate;
    private Recruitment recruitment;
    private LocalDate applicationDate;
    private int ranking;
    private boolean offerAccepted;
    private LocalDate offerAcceptedDate;


    public Application(String id, Candidate candidate, Recruitment recruitment, LocalDate applicationDate) {
        this.id = id;
        this.candidate = candidate;
        this.recruitment = recruitment;
        this.applicationDate = applicationDate;
        this.ranking = 0;
        this.offerAccepted = false;
       
       
        if (candidate != null && !candidate.getApplications().contains(this)) {
            candidate.addApplication(this);
        }
        if (recruitment != null && !recruitment.getApplications().contains(this)) {
            recruitment.addApplication(this);
        }
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Candidate getCandidate() {
        return candidate;
    }


    public void setCandidate(Candidate candidate) {
       
        if (this.candidate != null && this.candidate.getApplications().contains(this)) {
            this.candidate.removeApplication(this);
        }
       
        this.candidate = candidate;
       
       
        if (candidate != null && !candidate.getApplications().contains(this)) {
            candidate.addApplication(this);
        }
    }


    public Recruitment getRecruitment() {
        return recruitment;
    }


    public void setRecruitment(Recruitment recruitment) {
       
        if (this.recruitment != null && this.recruitment.getApplications().contains(this)) {
            this.recruitment.removeApplication(this);
        }
       
        this.recruitment = recruitment;
       
       
        if (recruitment != null && !recruitment.getApplications().contains(this)) {
            recruitment.addApplication(this);
        }
    }


    public LocalDate getApplicationDate() {
        return applicationDate;
    }


    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }


    public int getRanking() {
        return ranking;
    }


    public void setRanking(int ranking) {
        this.ranking = ranking;
    }


    public boolean isOfferAccepted() {
        return offerAccepted;
    }


    public void setOfferAccepted(boolean offerAccepted) {
        this.offerAccepted = offerAccepted;
    }


    public LocalDate getOfferAcceptedDate() {
        return offerAcceptedDate;
    }


    public void setOfferAcceptedDate(LocalDate offerAcceptedDate) {
        this.offerAcceptedDate = offerAcceptedDate;
    }


    public void acceptOffer(LocalDate acceptanceDate) {
        this.offerAccepted = true;
        this.offerAcceptedDate = acceptanceDate;
        if (recruitment != null) {
            recruitment.setOfferAcceptedDate(acceptanceDate);
            recruitment.setStatus("Filled");
        }
    }
}
