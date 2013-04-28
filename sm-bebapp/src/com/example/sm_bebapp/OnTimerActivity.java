package com.example.sm_bebapp;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class OnTimerActivity extends Activity {
	
	String phoneNumber, message;
	DatabaseHandler db; 
	TextParser textParser; 
	
	private static final String PLAYERSTATE_ACTIVE 		= "active";
	private static final String PLAYERSTATE_PAUSED 		= "paused";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        db = new DatabaseHandler(this);
        textParser = new TextParser(db);
        
        Log.d(">> TIMER"  , "Time to update players");
        UpdatePlayers();
        StartNextTimer();
        finish(); 
    }
    
    
    //------- loop through all players and parse their last answers and update them. 
    public void UpdatePlayers()
    {
    	Log.d(">> UPDATING PLAYERS"  , "Getting player list...");
    	String outMessage = ""; 
        List<Player> players = db.getAllPlayers(); 
        
        for (Player p : players) 
        {
        	if(p.getState().equals(PLAYERSTATE_ACTIVE))
        	{
        		outMessage = textParser.ParseMesssage(p, p.getLastAnswer());
        		p.setLastAnswer("|none|"); //clear last answer
        		db.updatePlayer(p); 
        		SendOutSMS( p.getPhoneNumber(), outMessage);
        	}
        }
    	
    }
    
    public void StartNextTimer()
    {
    	AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	Intent intent = new Intent(this, TimerReciever.class);
    	PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    	Calendar time = Calendar.getInstance();
    	time.setTimeInMillis(System.currentTimeMillis());
    	time.add(Calendar.SECOND, 15);
    	alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
    }

    
  //------- Magic send SMS function 
    public void SendOutSMS(String _phoneNumber, String _outMessage)
    {
    	 // Next Activity call
        Intent sendIntent = new Intent();
    	sendIntent.putExtra("phoneNumber", _phoneNumber);
    	sendIntent.putExtra("message", _outMessage);
        
        sendIntent.setClassName("com.example.sm_bebapp", "com.example.sm_bebapp.SendSMSActivity");
    	sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(sendIntent);
    	Log.d(">> MESSAGE OUT"  , "To " + _phoneNumber + " : \"" +_outMessage +"\"");
    }
}
