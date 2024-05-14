package br.com.alura.aluraschool.constants;

import org.springframework.stereotype.Component;

@Component
public interface AluraSchoolConstants {

    class EmailSend {

        public static final String SUBJECTS = "Feedback Unsatisfactoriness";
    }

    class FeedbackRating {

        public static final int PROMOTERS = 9;

        public static final int DETRACTORS = 6;

        public static int NUMBER_OF_ENROLLMENTS = 4;
    }

    class Utils {

        public static final double PERCENTAGE = 100.0;

        public static final int INITIAL_VALUE = 0;
    }
}
