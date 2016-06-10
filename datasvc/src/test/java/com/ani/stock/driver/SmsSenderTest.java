package com.ani.stock.driver;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;


//class not intended for production but merely to test api functionality
public class SmsSenderTest {
	
	
	public static final String ACCOUNT_SID = "AC123";
    public static final String AUTH_TOKEN = "456bef";

    public static void main(String[] args) throws TwilioRestException {

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        Account account = client.getAccount();

        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", "+16103932013")); 
        params.add(new BasicNameValuePair("From", "+16109442027")); 
        params.add(new BasicNameValuePair("Body", "Where's Wallace?"));
        Message sms = messageFactory.create(params);
    }

}
