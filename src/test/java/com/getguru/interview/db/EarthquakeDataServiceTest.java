package com.getguru.interview.db;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Jalisa on 4/20/19.
 */
public class EarthquakeDataServiceTest {

    private EarthquakeDataService _dataService = new EarthquakeDataService();


    @Test
    public void testGetEarthquakeData() throws Exception{
        List<RawEarthquakeData> rawDataList = null;

        try{
            rawDataList =  _dataService.getEarthquakeData();

        }catch (Exception e){}

        String actual = rawDataList.get(0).getId();
        String expected = "us2000ai1z";

        Assert.assertEquals(expected, actual);
    }


}
