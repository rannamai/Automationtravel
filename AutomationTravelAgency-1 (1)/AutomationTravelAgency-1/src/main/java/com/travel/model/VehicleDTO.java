package com.travel.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.travel.entity.Driver;

@Component
public class VehicleDTO {
	
	
	private int vehicleNo;
	
	@NotNull
	private String vehicleName;
	
	@Min(4)
	private int seatingCapacity;
	
	@NotNull
	private String vehicleType;
	
	@Min(5)
	private float farePerKM;
	
	private Driver driver;
	
	public VehicleDTO() {
		super();
	}

	
	public VehicleDTO(int vehicleNo, @NotNull String vehicleName, @Min(4) int seatingCapacity,
			@NotNull String vehicleType, @Min(5) float farePerKM, Driver driver) {
		super();
		this.vehicleNo = vehicleNo;
		this.vehicleName = vehicleName;
		this.seatingCapacity = seatingCapacity;
		this.vehicleType = vehicleType;
		this.farePerKM = farePerKM;
		this.driver = driver;
	}


	public int getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(int vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public float getFarePerKM() {
		return farePerKM;
	}

	public void setFarePerKM(float farePerKM) {
		this.farePerKM = farePerKM;
	}

	
	public Driver getDriver() {
		return driver;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}


	@Override
	public String toString() {
		return "VehicleDTO [vehicleNo=" + vehicleNo + ", vehicleName=" + vehicleName + ", seatingCapacity="
				+ seatingCapacity + ", vehicleType=" + vehicleType + ", farePerKM=" + farePerKM + ", driver=" + driver
				+ "]";
	}
 
}
