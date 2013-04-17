package com.example.sm_bebapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

 
public class SQLActivity extends Activity {
	
	String phoneNumber, message;
	DatabaseHandler db; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        db = new DatabaseHandler(this);
        
        phoneNumber = getIntent().getExtras().getString("phoneNumber");
        message = getIntent().getExtras().getString("message");
        
        Log.d(">> NEW MESSAGE"  , "From " + phoneNumber + " : \"" + message +"\"");

        if(CheckDBCommands(phoneNumber, message)) //Check Admin Commands first. If message is a command, skip story update. 
        {
        	ParseMessage(phoneNumber,  message); //then parse story 
        }
        
       
       
        
      
        
       
        
        
       
        
    	// Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Player> players = db.getAllContacts();       
 
        for (Player cn : players) {
            String log = "player n:" + cn.getName() + " #: " + cn.getPhoneNumber() + " la: \"" + cn.getLastAnswer() + "\" sl: " + cn.getStoryLocation();
                // Writing Contacts to log
        Log.d("Name: ", log);
        
        finish();
        
        
    	}
 
        
       
        
        
        /**
         * CRUD Operations
         * 
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Player("Ravi", "9100000000"));
        db.addContact(new Player("Srinivas", "9199999999"));
        db.addContact(new Player("Tommy", "9522222222"));
        db.addContact(new Player("Karthik", "9533333333"));
 
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Player> contacts = db.getAllContacts();       
 
        for (Player cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);
        
    	}
         */
    }
    
    public void SendOutSMS(String _phoneNumber, String _outMessage)
    {
    	 // Next Activity call
        Intent sendIntent = new Intent();
    	sendIntent.putExtra("phoneNumber", _phoneNumber);
    	sendIntent.putExtra("message", _outMessage);
        
        sendIntent.setClassName("com.example.sm_bebapp", "com.example.sm_bebapp.sendSMSActivity");
    	sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(sendIntent);
    }
    
    
    public boolean CheckDBCommands(String _phoneNumber, String _message) 
    {
    	 if(_message.equals("delete"))
         {
         	db.ClearTable(); 	
         	SendOutSMS(_phoneNumber, "Table Cleared"); 
         	return false;
         }
    	 else 
    	 {
    		 return true;
    	 }
	} 
    
    public void ParseMessage(String _phoneNumber, String _message)
    {
    	Player player = null;
    	player = db.getPlayer(_phoneNumber); //find the player
    	if(player == null)//&& _message.equals("BEB") //If the player does not exist, make a new one
    	{
    		player = new Player();
            player.setName(				 "rawr");
            player.setPhoneNumber(	phoneNumber);
            player.setLastAnswer(		message);
            player.setStoryLocation(		"0");
            db.addPlayer(player); 
            SendOutSMS(phoneNumber, "Welcome to BEB");
    	}
    	else  // otherwise update last message and story
    	{
            player.setLastAnswer(		message);
            player.setStoryLocation(		"0");
            db.updatePlayer(player);
            SendOutSMS(phoneNumber, "Hi you said: \"" + message + "\"");
    	}
    	
    	
    	//Story parsing here
    	
    	
    	 // DB calls here!!
        
    }
   
}
