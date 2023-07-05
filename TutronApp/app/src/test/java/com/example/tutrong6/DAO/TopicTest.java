package com.example.tutrong6.DAO;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import com.example.tutrong6.BEANS.Administrator;
import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.BEANS.Topic;

import java.util.Date;

public class TopicTest {

    Topic topic = new Topic();

    @Test
    public void getSetTopic(){

        //Step 1

        topic.setID(1);
        topic.setDescription("test topic");
        topic.setName("JohnsonJohnson");
        topic.setYears_of_experience(5);
        topic.setDescription(" this is a test ");
        topic.setIs_offered(true);

        assertEquals(1, topic.getID());
        assertEquals("JohnsonJohnson",topic.getName());
        assertEquals(" this is a test ",topic.getDescription());
        assertEquals(5,topic.getYears_of_experience());
        assertEquals(true, topic.getIs_offered());

    }

    @Test
    public void testMaxOffering() {

        int offeringMax = Topic.getOFFERING_MAX();

        // Verify that the value is expected
        assertEquals(5, offeringMax);

    }

    @Test
    public void testCreatedMax() {

        int createdMax = Topic.getCREATED_MAX();

        // Verify that the value is expected
        assertEquals(20, createdMax);
    }
}

