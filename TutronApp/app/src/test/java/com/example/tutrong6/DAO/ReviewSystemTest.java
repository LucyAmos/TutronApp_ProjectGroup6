package com.example.tutrong6.DAO;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import com.example.tutrong6.BEANS.Lesson;
import com.example.tutrong6.BEANS.Slot;
import com.example.tutrong6.BEANS.ReviewSystem;
import com.example.tutrong6.BEANS.Student;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
public class ReviewSystemTest {

        ReviewSystem review = new ReviewSystem();

        @Test
        public void testGetSet() {
            double expectedRating = 3.7;
            boolean expectedIsRatingAnonymous = true;
            Date expectedDate = new Date();
            boolean expectedTopicReviewed = false;
            String expectedReview = "This tutor was a great tutor! they really explain the topic in a way i could easily understand and apply to  other problems";

            review.setRating(expectedRating);
            review.setIs_rating_anonymous(expectedIsRatingAnonymous);
            review.setRating_date(expectedDate);
            review.setIs_topic_reviewed(expectedTopicReviewed);
            review.setReview(expectedReview);

            double actualRating = review.getRating();
            boolean actualIsRatingAnonymous = review.getIs_rating_anonymous();
            Date actualRatingDate = review.getRating_date();
            boolean actualIsTopicReviewed = review.getIs_topic_reviewed();
            String actualReview = review.getReview();

            assertEquals(expectedRating, actualRating, 0.01);
            assertEquals(expectedIsRatingAnonymous, actualIsRatingAnonymous);
            assertEquals(expectedDate, actualRatingDate);
            assertEquals(expectedTopicReviewed, actualIsTopicReviewed);
            assertEquals(expectedReview, actualReview);
        }
}
