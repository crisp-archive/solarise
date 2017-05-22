package com.solarise.test;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import com.solarise.entity.PlanEntity;
import com.solarise.entity.UserEntity;

public class EntityTest {
	UserEntity ue;
	PlanEntity pe;
	@Before
	public void setUp() throws Exception {
		ue=new UserEntity();
		pe=new PlanEntity();
	}

	@Test
	public void PlanEntityInitID() {
		Long id=(long) 0;
		assertEquals(pe.panelId,id);
	}
	
	@Test
	public void PlanEntityInitAddress(){
		assertEquals(pe.address,"Queensland University of Technology");
	}
	
	@Test
	public void PlanEntityInitEnergyUse(){
		Double use=0.0;
		assertEquals(pe.energyUse,use);
	}
	
	@Test
	public void PlanEntityInitLat(){
		Double lat=-27.45178;
		assertEquals(pe.latitude,lat);
	}
	
	@Test
	public void PlanEntityInitLng(){
		Double lng=153.01673000000005;
		assertEquals(pe.longitude,lng);
	}
	
	@Test
	public void PlanEntityInitNumber(){
		Long number=(long)1;
		assertEquals(pe.number,number);
	}
	
	@Test
	public void PlanEntityRoofArea(){
		Double area=(double)0;
		assertEquals(pe.roofArea,area);
	}
	
	@Test
	public void UserEntityMD5Convert() throws NoSuchAlgorithmException{
		MessageDigest alg=MessageDigest.getInstance("MD5");
		alg.update("123".getBytes());
		byte[] digesta=alg.digest();
		String password=ue.byte2str(digesta);
		assertEquals(password,"20:2C:B9:62:AC:59:07:5B:96:4B:07:15:2D:23:4B:70");
	}

}
