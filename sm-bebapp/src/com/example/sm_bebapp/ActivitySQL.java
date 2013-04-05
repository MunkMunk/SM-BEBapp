package com.example.sm_bebapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

 
public class ActivitySQL extends Activity {
	
	String phoneNumber, message;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        DatabaseHandler db = new DatabaseHandler(this);
        
        phoneNumber = getIntent().getExtras().getString("phoneNumber");
        message = getIntent().getExtras().getString("message");
        
        
        
        db.addContact(new Contact("rawr", phoneNumber));
        
        
        
        
        // Next Activity call
        Intent sendIntent = new Intent();
    	sendIntent.putExtra("phoneNumber", phoneNumber);
    	sendIntent.putExtra("message", message);
        
        sendIntent.setClassName("com.example.sm_bebapp", "com.example.sm_bebapp.sendSMSActivity");
    	sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(sendIntent);
        
    	// Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();       
 
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);
        
    	}
 
        /**
         * CRUD Operations
         * 
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));
 
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();       
 
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);
        
    	}
         */
    }
}
