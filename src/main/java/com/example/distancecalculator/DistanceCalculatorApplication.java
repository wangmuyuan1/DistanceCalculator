package com.example.distancecalculator;


import com.example.distancecalculator.model.CustomerCoordinates;
import com.example.distancecalculator.service.DistanceCalculatorService;
import com.example.distancecalculator.util.Message;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

@SpringBootApplication
public class DistanceCalculatorApplication implements CommandLineRunner
{
    private static Logger log = LoggerFactory.getLogger(DistanceCalculatorApplication.class);

    @Autowired
    private DistanceCalculatorService service;

    public static void main(String[] args) throws IOException
    {
        SpringApplication.run(DistanceCalculatorApplication.class, args);
        System.exit(0);
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (args.length < 4)
        {
            throw new RuntimeException(Message.INSUFFICIENT_PARAMETERS.getText());
        }

        if (!NumberUtils.isNumber(args[0]))
        {
            throw new RuntimeException(MessageFormat.format(Message.INVALID_NUMBER.getText(), args[0]));
        }

        if (!NumberUtils.isNumber(args[1]))
        {
            throw new RuntimeException(MessageFormat.format(Message.INVALID_NUMBER.getText(), args[1]));
        }

        if (!NumberUtils.isNumber(args[2]))
        {
            throw new RuntimeException(MessageFormat.format(Message.INVALID_NUMBER.getText(), args[2]));
        }

        log.info(Message.CALCULATING.getText(), args[0], args[1], args[2], args[3]);
        Map<CustomerCoordinates, Double> map = service.calculate(Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2]), args[3]);

        log.info("Result Size {}", map.keySet().size());
        for (Map.Entry<CustomerCoordinates, Double> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + ", Distance = " + entry.getValue());
        }
    }
}
