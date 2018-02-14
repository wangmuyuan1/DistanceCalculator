package com.example.distancecalculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;

public class CustomerCoordinates
{
    private static int RADIUS = 6371;

    private double latitude;
    private double longitude;


    @JsonProperty("user_id")
    private int userId;
    private String name;

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    @JsonSetter("user_id")
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double distanceFrom(double lat, double lon)
    {
        double latDiff = toRadians(lat - latitude);
        double lonDiff = toRadians(lon - longitude);

        double a = sin(latDiff / 2) * sin(latDiff / 2) + cos(toRadians(lat)) * cos(toRadians(latitude)) * sin(lonDiff / 2) * sin(lonDiff / 2);
        double c = 2 * asin(sqrt(a));
        return RADIUS * c;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        CustomerCoordinates that = (CustomerCoordinates) o;

        if (Double.compare(that.latitude, latitude) != 0)
        {
            return false;
        }
        if (Double.compare(that.longitude, longitude) != 0)
        {
            return false;
        }
        if (userId != that.userId)
        {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + userId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "CustomerCoordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
