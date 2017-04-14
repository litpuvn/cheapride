package com.cheapRide.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheapRide.comparators.LyftETAComparator;
import com.cheapRide.comparators.LyftMinPriceComparator;
import com.cheapRide.comparators.UberETAComparator;
import com.cheapRide.comparators.UberMinPriceComparator;
import com.cheapRide.model.EstimateResponseModel;
import com.cheapRide.model.ResponseModel;
import com.cheapRide.model.lyft.ListLyftETAModel;
import com.cheapRide.model.lyft.ListLyftPriceModel;
import com.cheapRide.model.lyft.LyftETAModel;
import com.cheapRide.model.lyft.LyftPriceModel;
import com.cheapRide.model.uber.ListUberETAModel;
import com.cheapRide.model.uber.ListUberPriceModel;
import com.cheapRide.model.uber.UberETAModel;
import com.cheapRide.model.uber.UberPriceModel;
import com.cheapRide.service.RideEstimateService;
import com.cheapRide.service.UberEstmiateService;
import com.cheapRide.util.Contants;;

@Service
public class RideEstimateServiceImpl implements RideEstimateService {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RideEstimateServiceImpl.class);

	private static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	LyftEstimateService lyftEstimateService;
	
	@Autowired
	UberEstmiateService uberEstimateService;

	public String getEstimates(float originLat, float originLong, float destLat, float destLon, Map<String,String> options) {
		logger.debug("Start : RideEstimateServiceImpl => getEstimates  for origin lattitude" + originLat
				+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
		String returnVal = null;
		try {
			
			ListUberPriceModel listUberModel = uberEstimateService.getPriceEstmiate(""+originLat, ""+originLong,""+destLat, ""+destLon);
			ListLyftPriceModel listLyftModel = lyftEstimateService.getPriceEstmiate(""+originLat, ""+originLong,""+destLat, ""+destLon);
			ListUberETAModel   listUberETAModel = uberEstimateService.getETA(""+originLat, ""+originLong);
			ListLyftETAModel   listLyftETAModel = lyftEstimateService.getETA(""+originLat, ""+originLong);
			
			UberPriceModel uberPriceMoel = getCheapMinCostUber(listUberModel, options);
			UberETAModel	uberETAModel = getCheapMinETAUber(listUberETAModel, options);
			LyftPriceModel lyftPriceModel = getCheapMinCostLyft(listLyftModel, options);
			LyftETAModel	lyftETAModel = getCheapMinETALyft(listLyftETAModel, options);
			
			EstimateResponseModel estResModel = getEstResponse(uberPriceMoel, uberETAModel, lyftPriceModel, lyftETAModel);
			
			returnVal = mapper.writeValueAsString(estResModel);
			
		} catch (Exception exc) {
			logger.error("ERROR : RideEstimateServiceImpl => getEstimates  for origin lattitude" + originLat
					+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
					+ destLon);
			exc.printStackTrace();
		}
		logger.debug("End : RideEstimateServiceImpl => getEstimates  for origin lattitude" + originLat
				+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
		return returnVal;
	}




	private EstimateResponseModel getEstResponse(UberPriceModel uberPriceMoel, UberETAModel uberETAModel,
		LyftPriceModel lyftPriceModel, LyftETAModel lyftETAModel) {
		logger.debug("Start : RideEstimateServiceImpl => getEstResponse ");
		EstimateResponseModel estResModel = new EstimateResponseModel();
		ResponseModel uber = new ResponseModel();
		uber.setMaxVal(uberPriceMoel.getHigh_estimate());
		uber.setMinVal(uberPriceMoel.getLow_estimate());
		uber.setRideRequestId(uberPriceMoel.getProduct_id());
		uber.setTime(uberETAModel.getEstimate()+"");
		
		ResponseModel lyft = new ResponseModel();
		lyft.setMaxVal(lyftPriceModel.getEstimated_cost_cents_max());
		lyft.setMinVal(lyftPriceModel.getEstimated_cost_cents_min());
		lyft.setRideRequestId(lyftPriceModel.getRide_type());
		lyft.setTime(lyftETAModel.getEta_seconds()+"");
		
		estResModel.setUber(uber);
		estResModel.setLyft(lyft);
		logger.debug("End : RideEstimateServiceImpl => getEstResponse ");
		return estResModel;
	}




	private UberPriceModel getCheapMinCostUber(ListUberPriceModel listUberModel, Map<String, String> options) {
		logger.debug("Start : RideEstimateServiceImpl => getCheapMinCostUber ");
		String uberCarType = null;
		if(options != null)
			uberCarType = options.get(Contants.UBER_CART_TYPE);
		List<UberPriceModel> lisUberPriceModel = listUberModel.getPrices();
		UberPriceModel returnUberPriceModel = null;
		if(uberCarType == null){
			Collections.sort(lisUberPriceModel, new UberMinPriceComparator());
			returnUberPriceModel = lisUberPriceModel.get(0);
		}else{
			double minPrice = 10000.0;
			for(UberPriceModel model : lisUberPriceModel ){
				if(uberCarType.equalsIgnoreCase(model.getDisplay_name())){
					if(model.getLow_estimate() < minPrice){
						returnUberPriceModel = model;
						minPrice = model.getLow_estimate();
					}
				}
			}
		}
		logger.debug("End : RideEstimateServiceImpl => getCheapMinCostUber ");
		return returnUberPriceModel;
	}
	
	private UberETAModel getCheapMinETAUber(ListUberETAModel listUberModel, Map<String, String> options) {
		logger.debug("Start : RideEstimateServiceImpl => getCheapMinETAUber ");
		String uberCarType = null;
		if(options != null)
		 uberCarType = options.get(Contants.UBER_CART_TYPE);
		List<UberETAModel> listUberEtaModel = listUberModel.getTimes();
		UberETAModel returnUberEtaModel = null;
		if(uberCarType == null){
			Collections.sort(listUberEtaModel, new UberETAComparator());
			returnUberEtaModel = listUberEtaModel.get(0);
		}else{
			double minETA = 10000.0;
			for(UberETAModel model : listUberEtaModel ){
				if(uberCarType.equalsIgnoreCase(model.getDisplay_name())){
					if(model.getEstimate() < minETA){
						returnUberEtaModel = model;
						minETA = model.getEstimate();
					}
				}
			}
		}
		logger.debug("End : RideEstimateServiceImpl => getCheapMinETAUber ");
		return returnUberEtaModel;
	}

	
	private LyftPriceModel getCheapMinCostLyft(ListLyftPriceModel listLyftModel, Map<String, String> options) {
		logger.debug("Start : RideEstimateServiceImpl => getCheapMinCostLyft ");
		String lyftCarType = null;
		if(options != null)
		 lyftCarType = options.get(Contants.LYFT_CART_TYPE);
		List<LyftPriceModel> listLyftPriceModel = listLyftModel.getCost_estimates();
				LyftPriceModel returnLyftPriceModel = null;
		if(lyftCarType == null){
			Collections.sort(listLyftPriceModel, new LyftMinPriceComparator());
			returnLyftPriceModel = listLyftPriceModel.get(0);
		}else{
			double minPrice = 10000.0;
			for(LyftPriceModel model : listLyftPriceModel ){
				if(lyftCarType.equalsIgnoreCase(model.getDisplay_name())){
					if(model.getEstimated_cost_cents_min() < minPrice){
						returnLyftPriceModel = model;
						minPrice = model.getEstimated_cost_cents_min();
					}
				}
			}
		}
		logger.debug("End : RideEstimateServiceImpl => getCheapMinCostLyft ");
		return returnLyftPriceModel;
	}

	
	private LyftETAModel getCheapMinETALyft(ListLyftETAModel listLyftModel, Map<String, String> options) {
		logger.debug("Start : RideEstimateServiceImpl => getCheapMinETALyft ");
		String lyftCarType = null;
		if(options != null)
		 lyftCarType = options.get(Contants.LYFT_CART_TYPE);;
		List<LyftETAModel> listLyftETAModel = listLyftModel.getEta_estimates();
		LyftETAModel returnLyftETAModel = null;
		if(lyftCarType == null){
			Collections.sort(listLyftETAModel, new LyftETAComparator());
			returnLyftETAModel = listLyftETAModel.get(0);
		}else{
			double minETA = 10000.0;
			for(LyftETAModel model : listLyftETAModel ){
				if(lyftCarType.equalsIgnoreCase(model.getDisplay_name())){
					if(model.getEta_seconds() < minETA){
						returnLyftETAModel = model;
						minETA = model.getEta_seconds();
					}
				}
			}
		}
		logger.debug("End : RideEstimateServiceImpl => getCheapMinETALyft ");
		return returnLyftETAModel;
	}



	@Test
	public String getEstimatedTime(float originLat, float originLong, Map<String,String> options) {
		logger.debug("Start : RideEstimateServiceImpl => getEstimatedTime  for origin lattitude" + originLat
				+ " origin longitude " + originLong );
		String returnVal = null;
		try {
			
		} catch (Exception exc) {
			logger.error("ERROR : RideEstimateServiceImpl => getEstimatedTime  for origin lattitude" + originLat
					+ " origin longitude " + originLong);
		}
		logger.debug("Start : RideEstimateServiceImpl => getEstimatedTime  for origin lattitude" + originLat
				+ " origin longitude " + originLong );
		return returnVal;
	}


}
