package com.example.tutrong6.DAO;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.tutrong6.BEANS.Topic;

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

        int createdMax = Topic.getCREATION_MAX();

        // Verify that the value is expected
        assertEquals(20, createdMax);
    }
}

