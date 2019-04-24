package com.getguru.interview.db;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getguru.interview.resources.DataResource;
import org.apache.log4j.Logger;


public class EarthquakeDataService {

  private static final Logger logger = Logger.getLogger(EarthquakeDataService.class);

  @SuppressWarnings("unused")
  private ObjectMapper _objectMapper = new ObjectMapper();
  
  public List<RawEarthquakeData> getEarthquakeData() throws Exception{
    /* 
     * XXX HINT: data can be loaded from a local resource called `seismic.json` 
     * into an InputStream with the following code 
     * InputStream is = getClass().getResourceAsStream("earthquake.json");
     */
    
    /* 
     * XXX HINT: see http://www.studytrails.com/java/json/java-jackson-serialization-list/ 
     * for information on how you can use ObjectMapper to deserialize the list of data from the file
     */

    InputStream is = getClass().getResourceAsStream("earthquake.json");
    List<RawEarthquakeData> rawDataList =  new ArrayList<>();

    try{
       rawDataList = _objectMapper.readValue( is,new TypeReference<List<RawEarthquakeData>>(){});

    }catch (Exception e){
      logger.info("EarthquakeDataService :: getEarthquakeData :: request failed \n" + e);
    }

    return rawDataList;
  }


}