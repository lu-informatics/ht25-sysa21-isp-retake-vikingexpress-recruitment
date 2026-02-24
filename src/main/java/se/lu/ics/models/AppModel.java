package se.lu.ics.models;


import java.time.LocalDate;
import java.time.LocalDateTime;


public class AppModel {
    private RoleRegister roleRegister;
    private CandidateRegister candidateRegister;
    private RecruitmentRegister recruitmentRegister;
    private ApplicationRegister applicationRegister;
    private InterviewRegister interviewRegister;


    public AppModel() {
        this.roleRegister = new RoleRegister();
        this.candidateRegister = new CandidateRegister();
        this.recruitmentRegister = new RecruitmentRegister();
        this.applicationRegister = new ApplicationRegister();
        this.interviewRegister = new InterviewRegister();


        addTestData();
    }


    public RoleRegister getRoleRegister() {
        return roleRegister;
    }


    public CandidateRegister getCandidateRegister() {
        return candidateRegister;
    }


    public RecruitmentRegister getRecruitmentRegister() {
        return recruitmentRegister;
    }


    public ApplicationRegister getApplicationRegister() {
        return applicationRegister;
    }


    public InterviewRegister getInterviewRegister() {
        return interviewRegister;
    }


    private void addTestData() {
       
        Role role1 = new Role("R01", "Truck Driver",
            "Drive long-haul routes across Europe. Requires valid commercial driver's license.",
            "Operations");
        Role role2 = new Role("R02", "HR Administrator",
            "Support recruitment and employee relations. Experience with HR systems required.",
            "Human Resources");
        Role role3 = new Role("R03", "Chief Operations Officer",
            "Lead operational strategy and execution. 10+ years senior management experience required.",
            "Executive");
        Role role4 = new Role("R04", "Logistics Coordinator",
            "Coordinate shipments and manage logistics operations.",
            "Operations");
        Role role5 = new Role("R05", "Fleet Manager",
            "Oversee vehicle maintenance and fleet optimization.",
            "Operations");


        roleRegister.addRole(role1);
        roleRegister.addRole(role2);
        roleRegister.addRole(role3);
        roleRegister.addRole(role4);
        roleRegister.addRole(role5);


       
        Candidate cand1 = new Candidate("CAN-00001", "Erik Svensson", "erik.svensson@email.com", "070-1234567");
        Candidate cand2 = new Candidate("CAN-00002", "Anna Bergström", "anna.bergstrom@email.com", "070-2345678");
        Candidate cand3 = new Candidate("CAN-00003", "Mohammed Ali", "mohammed.ali@email.com", "070-3456789");
        Candidate cand4 = new Candidate("CAN-00004", "Sofia Larsson", "sofia.larsson@email.com", "070-4567890");
        Candidate cand5 = new Candidate("CAN-00005", "Lars Andersson", "lars.andersson@email.com", "070-5678901");
        Candidate cand6 = new Candidate("CAN-00006", "Maria Johansson", "maria.johansson@email.com", "070-6789012");
        Candidate cand7 = new Candidate("CAN-00007", "Ahmed Hassan", "ahmed.hassan@email.com", "070-7890123");
        Candidate cand8 = new Candidate("CAN-00008", "Emma Nilsson", "emma.nilsson@email.com", "070-8901234");
        Candidate cand9 = new Candidate("CAN-00009", "Johan Karlsson", "johan.karlsson@email.com", "070-9012345");
        Candidate cand10 = new Candidate("CAN-00010", "Linda Persson", "linda.persson@email.com", "070-0123456");
        Candidate cand11 = new Candidate("CAN-00011", "Peter Gustafsson", "peter.gustafsson@email.com", "070-1111111");
        Candidate cand12 = new Candidate("CAN-00012", "Karin Olsson", "karin.olsson@email.com", "070-2222222");


        candidateRegister.addCandidate(cand1);
        candidateRegister.addCandidate(cand2);
        candidateRegister.addCandidate(cand3);
        candidateRegister.addCandidate(cand4);
        candidateRegister.addCandidate(cand5);
        candidateRegister.addCandidate(cand6);
        candidateRegister.addCandidate(cand7);
        candidateRegister.addCandidate(cand8);
        candidateRegister.addCandidate(cand9);
        candidateRegister.addCandidate(cand10);
        candidateRegister.addCandidate(cand11);
        candidateRegister.addCandidate(cand12);
        candidateRegister.setCandidateCounter(13);


       
        Recruitment rec1 = new Recruitment("HR 2025/1", role1, LocalDate.of(2025, 1, 15), LocalDate.of(2025, 3, 1));
        Recruitment rec2 = new Recruitment("HR 2025/2", role2, LocalDate.of(2025, 1, 20), LocalDate.of(2025, 2, 28));
        Recruitment rec3 = new Recruitment("HR 2025/3", role3, LocalDate.of(2024, 12, 1), LocalDate.of(2025, 2, 15));
        Recruitment rec4 = new Recruitment("HR 2024/1", role1, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 4, 15));
        Recruitment rec5 = new Recruitment("HR 2024/2", role4, LocalDate.of(2024, 6, 1), LocalDate.of(2024, 7, 31));
        Recruitment rec6 = new Recruitment("HR 2024/3", role5, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 10, 31));


       
        rec4.setStatus("Filled");
        rec4.setOfferAcceptedDate(LocalDate.of(2024, 4, 25));
       
        rec5.setStatus("Filled");
        rec5.setOfferAcceptedDate(LocalDate.of(2024, 8, 10));


        recruitmentRegister.addRecruitment(rec1);
        recruitmentRegister.addRecruitment(rec2);
        recruitmentRegister.addRecruitment(rec3);
        recruitmentRegister.addRecruitment(rec4);
        recruitmentRegister.addRecruitment(rec5);
        recruitmentRegister.addRecruitment(rec6);
        recruitmentRegister.setCurrentYear(2025);
        recruitmentRegister.setYearlyCounter(4);


       
        Application app1 = new Application("APP-000001", cand1, rec1, LocalDate.of(2025, 1, 20));
        Application app2 = new Application("APP-000002", cand2, rec1, LocalDate.of(2025, 1, 22));
        Application app3 = new Application("APP-000003", cand3, rec1, LocalDate.of(2025, 1, 25));
        Application app4 = new Application("APP-000004", cand4, rec2, LocalDate.of(2025, 1, 25));
        Application app5 = new Application("APP-000005", cand5, rec2, LocalDate.of(2025, 1, 28));
        Application app6 = new Application("APP-000006", cand6, rec3, LocalDate.of(2024, 12, 5));
        Application app7 = new Application("APP-000007", cand7, rec3, LocalDate.of(2024, 12, 10));
        Application app8 = new Application("APP-000008", cand8, rec4, LocalDate.of(2024, 3, 5));
        Application app9 = new Application("APP-000009", cand9, rec4, LocalDate.of(2024, 3, 10));
        Application app10 = new Application("APP-000010", cand10, rec5, LocalDate.of(2024, 6, 5));
        Application app11 = new Application("APP-000011", cand11, rec5, LocalDate.of(2024, 6, 10));
        Application app12 = new Application("APP-000012", cand12, rec6, LocalDate.of(2024, 9, 5));
        Application app13 = new Application("APP-000013", cand1, rec4, LocalDate.of(2024, 3, 8));
        Application app14 = new Application("APP-000014", cand2, rec5, LocalDate.of(2024, 6, 8));
       
       
        app1.setRanking(1);
        app2.setRanking(2);
        app3.setRanking(3);
        app8.setRanking(1);
        app9.setRanking(2);
       
       
        app8.acceptOffer(LocalDate.of(2024, 4, 25));
        app10.acceptOffer(LocalDate.of(2024, 8, 10));


        applicationRegister.addApplication(app1);
        applicationRegister.addApplication(app2);
        applicationRegister.addApplication(app3);
        applicationRegister.addApplication(app4);
        applicationRegister.addApplication(app5);
        applicationRegister.addApplication(app6);
        applicationRegister.addApplication(app7);
        applicationRegister.addApplication(app8);
        applicationRegister.addApplication(app9);
        applicationRegister.addApplication(app10);
        applicationRegister.addApplication(app11);
        applicationRegister.addApplication(app12);
        applicationRegister.addApplication(app13);
        applicationRegister.addApplication(app14);
        applicationRegister.setApplicationCounter(15);


       
        Interview int1 = new Interview("INT-00001", cand1, rec1,
            LocalDateTime.of(2025, 2, 5, 10, 0), "Conference Room A", "Sara Johansson");
        Interview int2 = new Interview("INT-00002", cand2, rec1,
            LocalDateTime.of(2025, 2, 5, 14, 0), "Conference Room A", "Sara Johansson");
        Interview int3 = new Interview("INT-00003", cand3, rec1,
            LocalDateTime.of(2025, 2, 6, 10, 0), "Conference Room B", "Lars Eriksson");
        Interview int4 = new Interview("INT-00004", cand4, rec2,
            LocalDateTime.of(2025, 2, 10, 9, 0), "HR Office", "Karin Lundberg");
        Interview int5 = new Interview("INT-00005", cand5, rec2,
            LocalDateTime.of(2025, 2, 10, 13, 0), "HR Office", "Karin Lundberg");
        Interview int6 = new Interview("INT-00006", cand6, rec3,
            LocalDateTime.of(2025, 1, 15, 10, 0), "Executive Boardroom", "CEO Magnus Berg");
        Interview int7 = new Interview("INT-00007", cand8, rec4,
            LocalDateTime.of(2024, 4, 10, 10, 0), "Conference Room A", "Sara Johansson");
        Interview int8 = new Interview("INT-00008", cand8, rec4,
            LocalDateTime.of(2024, 4, 15, 14, 0), "Operations Office", "Lars Eriksson");
        Interview int9 = new Interview("INT-00009", cand9, rec4,
            LocalDateTime.of(2024, 4, 12, 10, 0), "Conference Room A", "Sara Johansson");
        Interview int10 = new Interview("INT-00010", cand10, rec5,
            LocalDateTime.of(2024, 7, 15, 10, 0), "Conference Room B", "Anders Nyström");
        Interview int11 = new Interview("INT-00011", cand10, rec5,
            LocalDateTime.of(2024, 7, 20, 14, 0), "Operations Office", "Lars Eriksson");
        Interview int12 = new Interview("INT-00012", cand11, rec5,
            LocalDateTime.of(2024, 7, 16, 10, 0), "Conference Room B", "Anders Nyström");


        interviewRegister.addInterview(int1);
        interviewRegister.addInterview(int2);
        interviewRegister.addInterview(int3);
        interviewRegister.addInterview(int4);
        interviewRegister.addInterview(int5);
        interviewRegister.addInterview(int6);
        interviewRegister.addInterview(int7);
        interviewRegister.addInterview(int8);
        interviewRegister.addInterview(int9);
        interviewRegister.addInterview(int10);
        interviewRegister.addInterview(int11);
        interviewRegister.addInterview(int12);
        interviewRegister.setInterviewCounter(13);
    }
}
