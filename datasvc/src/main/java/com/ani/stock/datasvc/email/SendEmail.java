package com.ani.stock.datasvc.email;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface SendEmail {
	
	public void executeDelivery(String fromAddress, List<String> toAddresses, List<String> ccAddresses, 
			List<String> bccAddresses, String emailSubject, String emailbody, List<CommonsMultipartFile> file) 
					throws MessagingException, IOException;
	
			
			

}
