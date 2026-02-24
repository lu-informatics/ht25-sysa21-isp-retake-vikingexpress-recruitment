package se.lu.ics.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CandidateRegister {
    private ObservableList<Candidate> candidates;
    private int candidateCounter;


    public CandidateRegister() {
        this.candidates = FXCollections.observableArrayList();
        this.candidateCounter = 1;
    }


    public ObservableList<Candidate> getCandidates() {
        return FXCollections.unmodifiableObservableList(this.candidates);
    }


    public void addCandidate(Candidate candidate) {
        this.candidates.add(candidate);
    }


    public void removeCandidate(Candidate candidate) {
        this.candidates.remove(candidate);
    }


    public Candidate findCandidateById(String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equals(id)) {
                return candidate;
            }
        }
        return null;
    }


    public String generateCandidateId() {
        String id = String.format("CAN-%05d", candidateCounter);
        candidateCounter++;
        return id;
    }


    public void setCandidateCounter(int counter) {
        this.candidateCounter = counter;
    }
}


