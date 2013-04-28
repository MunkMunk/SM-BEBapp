package com.example.sm_bebapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class OnTimerActivity extends Activity {
	
	String phoneNumber, message;
	DatabaseHandler db; 
	TextParser textParser; 
	ResponseTreeBuilder rtBuilder; 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    
    
    
    
    
    
    
    
    public void SendOutSMS(String _phoneNumber, String _outMessage)
    {
    	 // Next Activity call
        Intent sendIntent = new Intent();
    	sendIntent.putExtra("phoneNumber", _phoneNumber);
    	sendIntent.putExtra("message", _outMessage);
        
        sendIntent.setClassName("com.example.sm_bebapp", "com.example.sm_bebapp.SendSMSActivity");
    	sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(sendIntent);
    	Log.d(">> MESSAGE OUT"  , "To " + phoneNumber + " : \"" +_outMessage +"\"");
    }
}
