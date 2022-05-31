package com.travel.service;

import com.travel.entity.Admin;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.AdminDTO;

public interface AdminService {
	

	
	public Admin registerAdmin( AdminDTO adminDto)throws UserNameAlreadyExistsException;
    public Admin editProfile(AdminDTO adminDtO) throws UserNotFoundException;
    public AdminDTO viewProfile(int userId)throws UserNotFoundException;
    public  String deleteAdmin(int userId)throws UserNotFoundException;
	
	//	public Vehicle addVehicle(VehicleDTO vehicleDto);
//	public String deleteVehicle(int vehicleNo)throws VehicleNotFoundException;
//	public Vehicle modifyVehicle(VehicleDTO vehicleDto)throws VehicleNotFoundException;
//	public VehicleDTO viewVehicle(int vehicleNo)throws VehicleNotFoundException;
//	
//	public Route addRoute(RouteDTO routeDto);
//	public Route modifyRoute( RouteDTO routeDTO)throws RouteNotFoundException;
//	public String deleteRoute(int routeId)throws RouteNotFoundException;
//	public RouteDTO viewRoute(int routeId) throws RouteNotFoundException;
//	
//	public Driver addDriver(DriverDTO driverDto);
//	public String deleteDriver(int driverNo)throws DriverNotFoundException;
//	public DriverDTO viewDriver(int driverNo)throws DriverNotFoundException;
//	public Driver modifyDriver (DriverDTO driverDto)throws DriverNotFoundException;
//	
//	 public BookingDTO viewBooking(int bookingId)throws BookingNotFoundException;
//	 
	
	
	}
