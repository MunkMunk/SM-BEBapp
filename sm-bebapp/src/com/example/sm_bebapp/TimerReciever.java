package com.example.sm_bebapp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class TimerReciever extends BroadcastReceiver { 
	
	
	@Override
    public void onReceive(Context context, Intent intent) {
       //Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();
		
        //SMS send here/ 
		 // Next Activity call
//        Intent sendIntent = new Intent();
//    	sendIntent.putExtra("phoneNumber", "+15055733247");
//    	sendIntent.putExtra("message", "Test");
//        
//        sendIntent.setClassName("com.example.sm_bebapp", "com.example.sm_bebapp.sendSMSActivity");
//    	sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    	context.startActivity(sendIntent);
    	//Log.d(">> MESSAGE OU"  , "To " + phoneNumber + " : \"" +_outMessage +"\"");
    }
	
	public void SendOutSMS(String _phoneNumber, String _outMessage)
    {
    	
    }

	
	
}