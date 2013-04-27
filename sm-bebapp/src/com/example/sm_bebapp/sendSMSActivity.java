package com.example.sm_bebapp;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;


public class sendSMSActivity extends Activity {

	String phoneNumber, message;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        phoneNumber = getIntent().getExtras().getString("phoneNumber");
        message = getIntent().getExtras().getString("message");
        
        splitMessage(phoneNumber,message);
        
        finish();
    }
    
    private void splitMessage(String _phoneNumber, String _message)
    {
    	
    	if(_message.length() < 150)
    	{
    		sendSMS(_phoneNumber, _message);
    	}
    	else
    	{
    		String[] words =  _message.split(" "); 
    		String newMessage = ""; 
    		for (String w : words)
        	{
    			if (newMessage.length() + w.length() > 155)
    			{
    				sendSMS(_phoneNumber, newMessage);
    				newMessage = ""; 
    			}
    			newMessage += w + " "; 
        	}
    		if(_message.length() > 0)
    		{
    			sendSMS(_phoneNumber, newMessage);
    		}
    	}
    	
    	/*
    	String newMessage  = _message.replaceAll("(.{30})", "$1|");
        //System.out.println(newMessage);
        String[] newMessages = newMessage.split("\\|");
    	for (String n : newMessages)
    	{
    		sendSMS(phoneNumber, n);
    	}
    	*/
    	
     
        
    	
    }
    
    //---sends an SMS message to another device---
    private void sendSMS(String phoneNumber, String message)
    {        
        PendingIntent pi = PendingIntent.getActivity(this, 0,
            new Intent(this, MainActivity.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);        
    } 
	
}
