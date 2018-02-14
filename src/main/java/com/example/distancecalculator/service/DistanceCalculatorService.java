package com.example.distancecalculator.service;

import com.example.distancecalculator.model.CustomerCoordinates;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DistanceCalculatorService
{
    @Autowired
    private FileService fileService;

    public Map<CustomerCoordinates, Double> calculate(double baseLat, double baseLon, double maxDistance, String srcFileName)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> lines = fileService.getFile(srcFileName);

        List<CustomerCoordinates> customerCoordinatesList = lines.stream().map(e -> {
            try
            {
                return objectMapper.readValue(e, CustomerCoordinates.class);
            }
            catch (IOException e1)
            {
                throw new RuntimeException(e1);
            }
        }).collect(Collectors.toList());

        Map<CustomerCoordinates, Double> map = new HashMap<>();

        for (CustomerCoordinates customerCoordinates : customerCoordinatesList)
        {
            double distance = customerCoordinates.distanceFrom(baseLat, baseLon);

            if (distance <= maxDistance)
            {
                map.put(customerCoordinates, distance);
            }
        }

        return map;
    }


}
