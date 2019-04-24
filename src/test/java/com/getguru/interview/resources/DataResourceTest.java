package com.getguru.interview.resources;


import com.getguru.interview.db.RawEarthquakeData;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Created by Jalisa on 4/20/19.
 */
public class DataResourceTest {

    private DataResource _dataResource = new DataResource();


    @Test
    public void testGetEarthquakes_NoFilter() throws Exception {
        List<Earthquake> earthquakes = null;

        try {
            earthquakes = _dataResource.getEarthquakes(Optional.ofNullable(null));


        }catch (Exception e) {}

        int actual = earthquakes.size();
        int expected = 50;

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testGetEarthquakes_Filter() throws Exception {
        List<Earthquake> earthquakes = null;
        String filter = "Mexico";

        try {
            earthquakes = _dataResource.getEarthquakes(Optional.of(filter));

        } catch (Exception e) {
        }

        int actual = earthquakes.size();
        int expected = 2;

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testGetEarthquakes_CaseSensitivity() throws Exception {
        List<Earthquake> earthquakes = null;
        String filter = "mexico";

        try {
            earthquakes = _dataResource.getEarthquakes(Optional.of(filter));

        } catch (Exception e) {
        }

        int actual = earthquakes.size();
        int expected = 2;

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testConvertToEarthquake() {
        RawEarthquakeData rawData = new RawEarthquakeData();
        rawData.setMagType("mb");
        rawData.setLongitude(-93.9526);
        rawData.setTime("2017-09-08T12:46:04.610Z");
        rawData.setLatitude(15.348);
        rawData.setPlace("72km SW of Tres Picos, Mexico");
        rawData.setType("earthquake");
        rawData.setMagnitude(5.2);
        rawData.setId("test001");

        Earthquake actual = _dataResource.convertToEarthquake(rawData);

        Assert.assertEquals(Earthquake.class, actual.getClass());
        Assert.assertEquals(Date.class, actual.getTime().getClass());


    }

    private List<RawEarthquakeData> getMockData() {
        List<RawEarthquakeData> list = new ArrayList<>();

        RawEarthquakeData rawData1 = new RawEarthquakeData();
        rawData1.setMagType("mb");
        rawData1.setLongitude(-93.9526);
        rawData1.setTime("2017-09-13T12:46:04.610Z");
        rawData1.setLatitude(15.348);
        rawData1.setPlace("72km SW of Tres Picos, Mexico");
        rawData1.setType("earthquake");
        rawData1.setMagnitude(3.2);
        rawData1.setId("test001");

        RawEarthquakeData rawData2 = new RawEarthquakeData();
        rawData2.setMagType("mb");
        rawData2.setLongitude(-93.9526);
        rawData2.setTime("2017-09-08T12:46:04.610Z");
        rawData2.setLatitude(15.348);
        rawData2.setPlace("72km SW of Tres Picos, Mexico");
        rawData2.setType("earthquake");
        rawData2.setMagnitude(5.2);
        rawData2.setId("test002");

        RawEarthquakeData rawData3 = new RawEarthquakeData();
        rawData3.setMagType("mb");
        rawData3.setLongitude(-93.9526);
        rawData3.setTime("2017-09-08T12:46:04.610Z");
        rawData3.setLatitude(15.348);
        rawData3.setPlace("72km SW of Tres Picos, Mexico");
        rawData3.setType("earthquake");
        rawData3.setMagnitude(3.2);
        rawData3.setId("test003");

        RawEarthquakeData rawData4 = new RawEarthquakeData();
        rawData4.setMagType("mb");
        rawData4.setLongitude(-93.9526);
        rawData4.setTime("2017-09-08T12:46:04.610Z");
        rawData4.setLatitude(15.348);
        rawData4.setPlace("72km SW of Tres Picos, Mexico");
        rawData4.setType("earthquake");
        rawData4.setMagnitude(2.2);
        rawData4.setId("test004");

        list.add(rawData1);
        list.add(rawData2);
        list.add(rawData3);
        list.add(rawData4);

        return list;
    }

}
