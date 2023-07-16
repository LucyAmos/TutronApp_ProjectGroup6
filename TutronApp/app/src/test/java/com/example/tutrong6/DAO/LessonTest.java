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


public class LessonTest {

    Lesson lesson = new Lesson();
    @Test
    public void testGetStatusEnum() {
        Lesson.Status pendingStatus = Lesson.Status.PENDING;
        Lesson.Status approvedStatus = Lesson.Status.APPROVED;
        Lesson.Status rejectedStatus = Lesson.Status.REJECTED;

        int expectedPendingID = 1;
        int expectedApprovedID = 2;
        int expectedRejectedID = 3;

        int actualPendingID = Lesson.getstatusIDByEnum(pendingStatus);
        int actualApprovedID = Lesson.getstatusIDByEnum(approvedStatus);
        int actualRejectedID = Lesson.getstatusIDByEnum(rejectedStatus);

        assertEquals(expectedPendingID, actualPendingID);
        assertEquals(expectedApprovedID, actualApprovedID);
        assertEquals(expectedRejectedID, actualRejectedID);
    }
    @Test
    public void testGettersAndSetters() {

        //Step 1: initialize variables
        int LessonID = 15;
        int expectedStudentID = 12;
        int expectedTutorID = 13;
        int expectedTopicID = 42;
        Lesson.Status expectedStatus = Lesson.Status.APPROVED;
        Date expectedDate = new Date();
        Slot expectedSlot = new Slot();
        double expectedPrice = 100.0;
        ReviewSystem expectedReviewSystem = new ReviewSystem();


        //Step 2: Set variables
        lesson.setID(LessonID);
        lesson.setStudentID(expectedStudentID);
        lesson.setTutorID(expectedTutorID);
        lesson.setTopicID(expectedTopicID);
        lesson.setStatus(expectedStatus);
        lesson.setDate_appointment(expectedDate);
        lesson.setSlot(expectedSlot);
        lesson.setPrice(expectedPrice);
        lesson.setReview_system(expectedReviewSystem);

        //Step 3: get values
        int actualID = lesson.getID();
        int actualStudentID = lesson.getStudentID();
        int actualTutorID = lesson.getTutorID();
        int actualTopicID = lesson.getTopicID();
        Lesson.Status actualStatus = lesson.getStatus();
        Date actualDate = lesson.getDate_appointment();
        Slot actualSlot = lesson.getSlot();
        double actualPrice = lesson.getPrice();
        ReviewSystem actualReviewSystem = lesson.getReview_system();

        //Step 4: Assert variables
        assertEquals(LessonID, actualID);
        assertEquals(expectedStudentID, actualStudentID);
        assertEquals(expectedTutorID, actualTutorID);
        assertEquals(expectedTopicID, actualTopicID);
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedDate, actualDate);
        assertEquals(expectedSlot, actualSlot);
        assertEquals(expectedPrice, actualPrice, 0.01);
        assertEquals(expectedReviewSystem, actualReviewSystem);
    }

}
