package com.example.DistanceCalculator.service;

import com.example.distancecalculator.model.CustomerCoordinates;
import com.example.distancecalculator.service.DistanceCalculatorService;
import com.example.distancecalculator.service.FileService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class DistanceCalculatorServiceTest
{
    private static final double DELTA = 1e-6;

    @Tested
    private DistanceCalculatorService service;

    @Injectable
    private FileService fileService;

    @Before
    public void setup()
    {
    }

    @Test
    public void test_customerWithinRange()
    {
        List<String> fullList = getFullCustomerCoordinatesList();
        CustomerCoordinates expectedCustomer = new CustomerCoordinates();
        expectedCustomer.setLatitude(54.080556);
        expectedCustomer.setLongitude(-6.361944);
        expectedCustomer.setUserId(23);
        expectedCustomer.setName("Eoin Gallagher");

        new Expectations()
        {
            {
                fileService.getFile("abc.txt");
                result = fullList;
            }
        };

        Map<CustomerCoordinates, Double> resultMap = service.calculate(53.339428, -6.257664, 100, "abc.txt");

        assertEquals(16, resultMap.size());
        assertEquals(82.69492611639981, resultMap.get(expectedCustomer), DELTA);
    }

    private List<String> getFullCustomerCoordinatesList()
    {
        List<String> fullCustomerCoordinatesList = new ArrayList<>();
        fullCustomerCoordinatesList.add("{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"51.8856167\", \"user_id\": 2, \"name\": \"Ian McArdle\", \"longitude\": \"-10.4240951\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"52.3191841\", \"user_id\": 3, \"name\": \"Jack Enright\", \"longitude\": \"-8.5072391\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.807778\", \"user_id\": 28, \"name\": \"Charlie Halligan\", \"longitude\": \"-7.714444\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.4692815\", \"user_id\": 7, \"name\": \"Frank Kehoe\", \"longitude\": \"-9.436036\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"54.0894797\", \"user_id\": 8, \"name\": \"Eoin Ahearn\", \"longitude\": \"-6.18671\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.038056\", \"user_id\": 26, \"name\": \"Stephen McArdle\", \"longitude\": \"-7.653889\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"54.1225\", \"user_id\": 27, \"name\": \"Enid Gallagher\", \"longitude\": \"-8.143333\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.1229599\", \"user_id\": 6, \"name\": \"Theresa Enright\", \"longitude\": \"-6.2705202\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"52.2559432\", \"user_id\": 9, \"name\": \"Jack Dempsey\", \"longitude\": \"-7.1048927\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"52.240382\", \"user_id\": 10, \"name\": \"Georgina Gallagher\", \"longitude\": \"-6.972413\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.2451022\", \"user_id\": 4, \"name\": \"Ian Kehoe\", \"longitude\": \"-6.238335\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.1302756\", \"user_id\": 5, \"name\": \"Nora Dempsey\", \"longitude\": \"-6.2397222\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.008769\", \"user_id\": 11, \"name\": \"Richard Finnegan\", \"longitude\": \"-6.1056711\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.1489345\", \"user_id\": 31, \"name\": \"Alan Behan\", \"longitude\": \"-6.8422408\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53\", \"user_id\": 13, \"name\": \"Olive Ahearn\", \"longitude\": \"-7\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"51.999447\", \"user_id\": 14, \"name\": \"Helen Cahill\", \"longitude\": \"-9.742744\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"52.966\", \"user_id\": 15, \"name\": \"Michael Ahearn\", \"longitude\": \"-6.463\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"52.366037\", \"user_id\": 16, \"name\": \"Ian Larkin\", \"longitude\": \"-8.179118\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"54.180238\", \"user_id\": 17, \"name\": \"Patricia Cahill\", \"longitude\": \"-5.920898\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.0033946\", \"user_id\": 39, \"name\": \"Lisa Ahearn\", \"longitude\": \"-6.3877505\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"52.228056\", \"user_id\": 18, \"name\": \"Bob Larkin\", \"longitude\": \"-7.915833\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"54.133333\", \"user_id\": 24, \"name\": \"Rose Enright\", \"longitude\": \"-6.433333\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"55.033\", \"user_id\": 19, \"name\": \"Enid Cahill\", \"longitude\": \"-8.112\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.521111\", \"user_id\": 20, \"name\": \"Enid Enright\", \"longitude\": \"-9.831111\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"51.802\", \"user_id\": 21, \"name\": \"David Ahearn\", \"longitude\": \"-9.442\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"54.374208\", \"user_id\": 22, \"name\": \"Charlie McArdle\", \"longitude\": \"-8.371639\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.74452\", \"user_id\": 29, \"name\": \"Oliver Ahearn\", \"longitude\": \"-7.11167\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"53.761389\", \"user_id\": 30, \"name\": \"Nick Enright\", \"longitude\": \"-7.2875\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"54.080556\", \"user_id\": 23, \"name\": \"Eoin Gallagher\", \"longitude\": \"-6.361944\"}");
        fullCustomerCoordinatesList.add("{\"latitude\": \"52.833502\", \"user_id\": 25, \"name\": \"David Behan\", \"longitude\": \"-8.522366\"}");
        return fullCustomerCoordinatesList;
    }
}
