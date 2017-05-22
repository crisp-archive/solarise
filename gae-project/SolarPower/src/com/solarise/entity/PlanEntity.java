package com.solarise.entity;

public class PlanEntity {
	public Long panelId;
	public Long number;
	public String address;
	public Double latitude;
	public Double longitude;
	public Double energyUse;
	public Double roofArea;
	public PlanEntity(){
		panelId=(long) 0;
		number=(long) 1;
		address="Queensland University of Technology";
		latitude=-27.45178;
		longitude=153.01673000000005;
		energyUse=(double) 0;
		roofArea=(double) 0;
	}
}
