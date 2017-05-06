package com.cheapRide.controller;
import java.awt.print.Pageable;
/**
 * @author Agnes
 */
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cheapRide.model.HistoryModel;
import com.cheapRide.model.HistoryResponse;
import com.cheapRide.service.HistoryService;

@RestController
public class HistoryController {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
    public HistoryService historyService;
	@RequestMapping(value = "/getHistoryByUsername", method = RequestMethod.GET)
    public ArrayList<HistoryModel> getHistoryByUsername(@RequestParam (value="username") String username, @RequestParam (value="pageNumber") int pageNumber, @RequestParam (value="size") int size) {
    	logger.debug("Start => HistoryController => getHistoryByUsername  for user " + username);
        ArrayList<HistoryModel> returnString = null;
        try {
        	returnString = historyService.checkForUsername(username, pageNumber, size);
     
        } catch (Exception e) {
        	 logger.error("ERROR : HistoryController => getHistoryByUsername  for user " + username);
        }
        logger.debug("End : HistoryController => getHistoryByUsername  for user " + username);
        
        logger.debug("Output : "+returnString);
        
        return returnString;
    }
	@RequestMapping(value = "/getHistoryByProvider", method = RequestMethod.GET)
	public ArrayList<HistoryModel> getHistoryByProvider(@RequestParam (value="username") String username, @RequestParam (value="provider") String provider, @RequestParam (value="pageNumber") int pageNumber, @RequestParam (value="size") int size) {
    	logger.debug("Start => HistoryController => getHistoryByProvider  for user " + username);
        ArrayList<HistoryModel> returnString = null;
        try {
            returnString = historyService.checkForProvider(username,provider, pageNumber, size);
            //returnString = mapper.writeValueAsString(model);
        } catch (Exception e) {
        	 logger.error("ERROR : HistoryController => getHistoryByProvider  for user " + username);
        }
        logger.debug("End : HistoryController => getHistoryByProvider  for user " + username);
        
        logger.debug("Output : "+returnString);
        return returnString;
    }
	@RequestMapping(value = "/getHistoryByDate", method = RequestMethod.GET)
	public ArrayList<HistoryModel> getHistoryByDate(@RequestParam (value="username") String username, @RequestParam (value="from") String from,  @RequestParam (value="to") String to, @RequestParam (value="pageNumber") int pageNumber, @RequestParam (value="size") int size) {
    	logger.debug("Start => HistoryController => getHistoryByDate  for user " + username);
        ArrayList<HistoryModel> returnString = null;
        try {
            returnString = historyService.checkDate(username,from,to, pageNumber, size);
        } catch (Exception e) {
        	 logger.error("ERROR : HistoryController => getHistoryByDate  for user " + username);
        }
        logger.debug("End : HistoryController => getHistoryByDate  for user " + username);
        
        logger.debug("Output : "+returnString);
        return returnString;
    }
	@RequestMapping(value = "/setHistory", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody String setHistory(@RequestBody final HistoryModel model ){
    	logger.debug("Start => HistoryController => setHistory  for user " + model.getUsername());
        String returnString = null;
        try {
            returnString = historyService.checkAddHistory(model.getUsername(),model.getDate(),model.getPickup(),model.getDestination(),model.getFee(),model.getProvider());
        } catch (Exception e) {
        	 logger.error("ERROR : HistoryController => setHistory  for user " + model.getUsername());
        }
        logger.debug("End : HistoryController => setHistory  for user " + model.getUsername());
        return returnString;
    }
}
