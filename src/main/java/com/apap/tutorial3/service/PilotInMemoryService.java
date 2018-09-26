package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.PilotModel;
@Service
public class PilotInMemoryService implements PilotService {
private List<PilotModel> archivePilot;
public PilotInMemoryService() {
	archivePilot = new ArrayList<>();
}

@Override
public void addPilot (PilotModel pilot) {
	archivePilot.add(pilot);
}

public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
	 for(PilotModel pilot : archivePilot) {
	        if(pilot.getLicenseNumber().equals(licenseNumber)) {
	            return pilot;
	        }
	    }
	    return null;
}

public PilotModel getPilotDetailById(String id) {
	 for(PilotModel pilot : archivePilot) {
	        if(pilot.getId().equals(id)) {
	            return pilot;
	        }
	    }
	    return null;
}

public void deletePilot(PilotModel pilot) {
	archivePilot.remove(pilot);
}

@Override
public List<PilotModel> getPilotList() {
	// TODO Auto-generated method stub
	return archivePilot;
}
}
