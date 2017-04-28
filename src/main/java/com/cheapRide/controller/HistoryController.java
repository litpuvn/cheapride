package com.cheapRide.controller;
/**
 * @author Aggy
 */
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheapRide.model.HistoryModel;
import com.cheapRide.model.HistoryResponse;
import com.cheapRide.service.HistoryService;

@RestController
public class HistoryController {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
    public HistoryService historyService;
	@SuppressWarnings("unused")
	private static ObjectMapper mapper = new ObjectMapper();
	@RequestMapping(value = "/getHistoryByUsername", method = RequestMethod.GET)
    public ArrayList<HistoryModel> getHistoryByUsername(@RequestParam (value="username") String username) {
    	logger.debug("Start => HistoryController => getHistoryByUsername  for user " + username);
        //String returnString = null;
        ArrayList<HistoryModel> model = new ArrayList<HistoryModel>();
        try {
            model = historyService.checkForUsername(username);
            //returnString = mapper.writeValueAsString(model);
        } catch (Exception e) {
        	 logger.error("ERROR : HistoryController => getHistoryByUsername  for user " + username);
        }
        logger.debug("End : HistoryController => getHistoryByUsername  for user " + username);
        
        logger.debug("Output : "+model);
        return model;
    }
	@RequestMapping(value = "/getHistoryByProvider", method = RequestMethod.GET)
	public ArrayList<HistoryModel> getHistoryByProvider(@RequestParam (value="username") String username, @RequestParam (value="provider") String provider) {
    	logger.debug("Start => HistoryController => getHistoryByProvider  for user " + username);
        //String returnString = null;
        ArrayList<HistoryModel> model = new ArrayList<HistoryModel>();
        try {
            model = historyService.checkForProvider(username,provider);
            //returnString = mapper.writeValueAsString(model);
        } catch (Exception e) {
        	 logger.error("ERROR : HistoryController => getHistoryByProvider  for user " + username);
        }
        logger.debug("End : HistoryController => getHistoryByProvider  for user " + username);
        
        logger.debug("Output : "+model);
        return model;
    }
	@RequestMapping(value = "/getHistoryByDate", method = RequestMethod.GET)
	public ArrayList<HistoryModel> getHistoryByDate(@RequestParam (value="username") String username, @RequestParam (value="from_date") String fromDate,  @RequestParam (value="to_date") String toDate) {
    	logger.debug("Start => HistoryController => getHistoryByDate  for user " + username);
        //String returnString = null;
        ArrayList<HistoryModel> model = new ArrayList<HistoryModel>();
        try {
            model = historyService.checkDate(username,fromDate,toDate);
            //returnString = mapper.writeValueAsString(model);
        } catch (Exception e) {
        	 logger.error("ERROR : HistoryController => getHistoryByDate  for user " + username);
        }
        logger.debug("End : HistoryController => getHistoryByDate  for user " + username);
        
        logger.debug("Output : "+model);
        return model;
    }
	@RequestMapping(value = "/setHistory", method = RequestMethod.POST)
	public ResponseEntity<HistoryResponse> setHistory(@RequestParam (value="username") String username, @RequestParam (value="date") String date,  @RequestParam (value="pickup") String pickup,@RequestParam (value="destination") String destination, @RequestParam (value="fee") String fee, @RequestParam (value="provider") String provider ){
    	logger.debug("Start => HistoryController => setHistory  for user " + username);
        String returnString = null;
        @SuppressWarnings("unused")
		HistoryModel model = new HistoryModel();
        HistoryResponse response = new HistoryResponse();
        ResponseEntity<HistoryResponse> historyResponseEntity = new ResponseEntity<HistoryResponse>(null);
        try {
            model = historyService.checkAddHistory(username,date,pickup,destination,fee,provider);
            response.setMessage("history updated");
            historyResponseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
        	 logger.error("ERROR : HistoryController => setHistory  for user " + username);
        	 response.setMessage("history not updated");
        	 historyResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        logger.debug("End : HistoryController => setHistory  for user " + username);
        
        logger.debug("Output : "+returnString);
        return historyResponseEntity;
    }
}
