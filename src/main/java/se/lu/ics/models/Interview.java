package se.lu.ics.models;


import java.time.LocalDateTime;


public class Interview {
    private String id;
    private Candidate candidate;
    private Recruitment recruitment;
    private LocalDateTime dateTime;
    private String location;
    private String interviewer;


    public Interview(String id, Candidate candidate, Recruitment recruitment,
                     LocalDateTime dateTime, String location, String interviewer) {
        this.id = id;
        this.candidate = candidate;
        this.recruitment = recruitment;
        this.dateTime = dateTime;
        this.location = location;
        this.interviewer = interviewer;
       
       
        if (candidate != null && !candidate.getInterviews().contains(this)) {
            candidate.addInterview(this);
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
       
        if (this.candidate != null && this.candidate.getInterviews().contains(this)) {
            this.candidate.removeInterview(this);
        }
       
        this.candidate = candidate;
       
       
        if (candidate != null && !candidate.getInterviews().contains(this)) {
            candidate.addInterview(this);
        }
    }


    public Recruitment getRecruitment() {
        return recruitment;
    }


    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getInterviewer() {
        return interviewer;
    }


    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }
}


