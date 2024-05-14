package br.com.alura.aluraschool.model.form;

import lombok.Getter;

@Getter
public class FeedBackNPS {

    private String courseCode;

    private String courseName;

    private int netPromoterScore;

    private String assessment;

    public FeedBackNPS(String courseCode, String courseName, int netPromoterScore) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.netPromoterScore = netPromoterScore;
        this.assessment = calculateAssessment(netPromoterScore);
    }

    private String calculateAssessment(int nps) {

        if (nps >= 75 && nps <= 100) {
            return "Excellent";

        } else if (nps >= 50 && nps <= 74) {
            return "Very good";

        } else if (nps >= 0 && nps <= 49) {
            return "Reasonable";

        } else {
            return "Bad";
        }
    }
}
