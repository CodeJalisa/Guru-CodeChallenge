package com.getguru.interview.resources;

import com.getguru.interview.db.EarthquakeDataService;
import com.getguru.interview.db.RawEarthquakeData;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public class DataResource {

  private static final Logger logger = Logger.getLogger(DataResource.class);

  @SuppressWarnings("unused")
  private EarthquakeDataService _dataService = new EarthquakeDataService();  
  
  @GET
  public List<Earthquake> getEarthquakes(@QueryParam("filter") Optional<String> filter){
    logger.info("DataResource :: getEarthquakes :: requesting earthquake data with filter - " + filter.toString());
    List<Earthquake> earthquakeList = new ArrayList<>();
    try{
      List<RawEarthquakeData> rawDataList = _dataService.getEarthquakeData();

      if(filter.isPresent()){
        rawDataList = rawDataList.stream().filter(earthquake ->
                earthquake.getPlace().toLowerCase().contains(filter.get().toLowerCase()))
                .collect(Collectors.toList());
      }

      rawDataList.forEach(rawData -> {
        Earthquake earthquake = convertToEarthquake(rawData);
        earthquakeList.add(earthquake);
      });

      // sorting by time first since its secondary
      earthquakeList.sort(Comparator.comparing(Earthquake::getTime));
      earthquakeList.sort(Comparator.comparing(Earthquake::getMagnitude).reversed());

    }catch (Exception e){
      logger.info("DataResource :: getEarthquakes :: request failed \n" + e);
    }

    return earthquakeList;
  }

  public boolean ping(){
    boolean healthy = false;
    try{
      healthy =_dataService.getEarthquakeData().size() == 50;
    }catch (Exception e ){}

    return healthy;
  }


  public Earthquake convertToEarthquake(RawEarthquakeData rawData) {
    Earthquake earthquake = new Earthquake();
    earthquake.setDepth(rawData.getDepth());
    earthquake.setId(rawData.getId());
    earthquake.setLatitude(rawData.getLatitude());
    earthquake.setLongitude(rawData.getLongitude());
    earthquake.setMagnitude(rawData.getMagnitude());
    earthquake.setMagType(rawData.getMagType());
    earthquake.setType(rawData.getType());
    earthquake.setPlace(rawData.getPlace());
    try{
      earthquake.setTime(DatatypeConverter.parseDateTime(rawData.getTime()).getTime());
    }catch (Exception e){
      logger.info("Error while formatting Data \n" + e);
    }

    return earthquake;
  }



}