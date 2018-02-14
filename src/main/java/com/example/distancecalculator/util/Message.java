package com.example.distancecalculator.util;

public enum Message
{
    CALCULATING("Calculating Distance for given Latitude: {0}, Longitude: {1}, Max Distance {2}km, File Path {3}"),
    INSUFFICIENT_PARAMETERS("Insufficient Parameter. Example: java -jar DistanceCalculator-0.0.1-SNAPSHOT.jar 53.339428 -6.257664 100 c:\\projects\\gistfile1.txt"),
    INVALID_NUMBER("{0} is invalid number.");

    private String text;

    Message(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return this.text;
    }
}
