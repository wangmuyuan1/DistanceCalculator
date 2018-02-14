package com.example.DistanceCalculator;

import com.example.distancecalculator.DistanceCalculatorApplication;
import com.example.distancecalculator.model.CustomerCoordinates;
import com.example.distancecalculator.service.DistanceCalculatorService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class DistanceCalculatorApplicationApplicationTest
{
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private static final double DELTA = 1e-6;

	@Tested
	private DistanceCalculatorApplication application;

	@Injectable
	private DistanceCalculatorService service;

	@Before
	public void setup()
	{
	}

	@Test
	public void testRun_OK() throws Exception
	{
		String lat = "53.339428";
		String lon = "-6.257664";
		String maxRange = "100";
		String url = "abc.txt";
		Map<CustomerCoordinates, Double> map = new HashMap<>();

		CustomerCoordinates customerCoordinates = new CustomerCoordinates();
		customerCoordinates.setUserId(12);
		customerCoordinates.setLatitude(54.734);
		customerCoordinates.setLongitude(-6.24);
		customerCoordinates.setName("dummyUser");
		map.put(customerCoordinates, 96.3);

		List<Double> lats = new ArrayList<>();
		List<Double> lons = new ArrayList<>();
		List<Double> maxRanges = new ArrayList<>();
		List<String> urls = new ArrayList<>();
		new Expectations()
		{
			{
				service.calculate(withCapture(lats), withCapture(lons), withCapture(maxRanges), withCapture(urls));
				result = map;
			}
		};

		application.run(lat, lon, maxRange, url);

		assertEquals(53.339428, lats.get(0), DELTA);
		assertEquals(-6.257664, lons.get(0), DELTA);
		assertEquals(100, maxRanges.get(0), DELTA);
		assertEquals(url, urls.get(0));
	}

	@Test
	public void testRun_InvalidLat() throws Exception
	{
		String lat = "53.339d28";
		String lon = "-6.257664";
		String maxRange = "100";
		String url = "abc.txt";

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("53.339d28 is invalid number.");
		application.run(lat, lon, maxRange, url);
	}

	@Test
	public void testRun_InvalidLon() throws Exception
	{
		String lat = "53.339428";
		String lon = "-6.257d64";
		String maxRange = "100";
		String url = "abc.txt";

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("-6.257d64 is invalid number.");
		application.run(lat, lon, maxRange, url);
	}

	@Test
	public void testRun_InvalidMaxRange() throws Exception
	{
		String lat = "53.339428";
		String lon = "-6.257664";
		String maxRange = "100k";
		String url = "abc.txt";

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("100k is invalid number.");
		application.run(lat, lon, maxRange, url);
	}

	@Test
	public void testRun_InsufficientParameter() throws Exception
	{
		String lat = "53.339428";
		String lon = "-6.257664";
		String maxRange = "100k";
		String url = "abc.txt";

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Insufficient Parameter. Example: java -jar DistanceCalculator-0.0.1-SNAPSHOT.jar 53.339428, -6.257664 100 c:\\projects\\gisfile1.txt");
		application.run(lat, lon, maxRange);
	}
}
