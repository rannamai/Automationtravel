package com.travel.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Route;
import com.travel.exception.RouteNotFoundException;
import com.travel.model.RouteDTO;
import com.travel.repo.RouteRepository;
import com.travel.service.RouteService;
import com.travel.util.ConversionClass;

@Service
public class RouteServiceImpl implements RouteService {

	private static Logger logRoute = LoggerFactory.getLogger(RouteServiceImpl.class);
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private ConversionClass converter;

	@Override
	public Route addRoute(RouteDTO routeDto) {
		
		return routeRepository.save(converter.convertToRouteEntity(routeDto));
	}

	

	@Override
	public String deleteRoute(int routeId) throws RouteNotFoundException {
		
		Optional<Route> Route = routeRepository.findById(routeId);
		String message=null;
		if(Route.isPresent()) {
			routeRepository.deleteById(routeId);
			message="Deleted Successfully";
			logRoute.info(message);
			
		}else {
			message="Route Not Found";
			logRoute.error(message);
			throw new RouteNotFoundException(message);
		}
		return message;
		
	}

	@Override
	public RouteDTO viewRoute(int routeId) throws RouteNotFoundException {
		Optional<Route> route = routeRepository.findById(routeId);
		RouteDTO dto=null;
		Route rou = null;
		if(route.isPresent()) {
			rou =route.get();
			 dto=converter.convertToRouteDTO(rou);
			
		}
		else {
			String message="Route Not found";
			logRoute.error(message);
			throw new RouteNotFoundException(message);
		}
		return dto;
		
	}

	@Override
	public Route modifyRoute(RouteDTO routeDTO) throws RouteNotFoundException {
		Optional<Route> route = routeRepository.findById(routeDTO.getRouteId());
		Route routeRecord=null;
		if(route.isPresent()) {
			 routeRecord= route.get();
			routeRepository.save(converter.convertToRouteEntity(routeDTO));
			
		}
		else {
			logRoute.error("Route Not Found");
			throw new RouteNotFoundException("Route Not Found");
		}
		

		return routeRecord;
		
	}



	
	

}
