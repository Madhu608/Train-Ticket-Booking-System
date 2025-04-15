package com.IndianRailway.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.IndianRailway.entities.MyBooking;
import com.IndianRailway.entities.Passenger;
import com.IndianRailway.entities.User;
import com.IndianRailway.repository.PassengerRepository;
import com.IndianRailway.repository.UserRepository;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JReportService {
	
	  @Autowired
	    private PassengerRepository passengerRepo;
	  
	  
	  @Autowired
		private UserRepository userRepo;
	  
//	  
//	    public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
//	        List<Passenger> address = passengerRepo.findAll();
//	        
//	        //Get file and compile it
//	        File file = ResourceUtils.getFile("classpath:Address1.jrxml");
//	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);
//	        Map<String, Object> parameters = new HashMap<>();
//	        parameters.put("createdBy", "Simplifying Tech");
//	        
//	        //Fill Jasper report
//	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//	        
//	        //Export report
//	        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
//	    }

	  
	  
	  
	  

  public void exportJasperReport(HttpServletResponse response,String email,Long id) throws JRException, IOException {
	        
	   User user = userRepo.findByEmailId(email);
	    List<Passenger> passengerList = passengerRepo.findPassengersByEmailId(user.getId(), id);
	  
	  
	  //Get file and compile it
	        File file = ResourceUtils.getFile("classpath:Address1.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        
	        
	        // Create a new list and add both passengers and bookTicketList to it
//	        List<Object> data = new ArrayList<>();
//	        data.addAll(passengers);
//	        data.addAll(bookTicketList); 
	        
	     	        
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(passengerList);
	        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Simplifying Tech");
//        parameters.put("passengers", passengerList);
//	        parameters.put("bookTicketList", bookTicketList);

	        //Fill Jasper report
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource) ;

	        //Export report
	        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	    }
	  
	  
	  }	  
	 
