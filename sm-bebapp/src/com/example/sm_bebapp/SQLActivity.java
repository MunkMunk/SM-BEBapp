package com.example.sm_bebapp;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

 
public class SQLActivity extends Activity {
	
	String phoneNumber, message;
	DatabaseHandler db; 
	TextParser textParser; 
	ResponseTreeBuilder rtBuilder; 
	private static final String COMMAND_PREFIX = "CMD"+" ";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        db = new DatabaseHandler(this);
        textParser = new TextParser(db);
        rtBuilder = new ResponseTreeBuilder(db);
        
        phoneNumber = getIntent().getExtras().getString("phoneNumber");
        message = getIntent().getExtras().getString("message");
        
        Log.d(">> NEW MESSAGE"  , "From " + phoneNumber + " : \"" + message +"\"");

    	if(CheckCommand("", message))//Check Admin Commands first. If message is a command, skip story update. 
    	{
    		ParseCommands(phoneNumber, message);
    	}
    	else 
    	{
    		ParseMessage(phoneNumber,  message); //then parse story 
    	}
        
        
    	// Reading all contacts
        printAllPlayers();
        //printAllResponses(); 
        finish();
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
    	Log.d(">> MESSAGE OU"  , "To " + phoneNumber + " : \"" +_outMessage +"\"");
    }
    
    
    public void ParseCommands(String _phoneNumber, String _message) 
    {
    	
    	String[] args = ParseCommand(_message);//splits the message on - to get the args
    	
    	//-------Clear All Players from the Player table
    	if(CheckCommand("delete all players", args[0]))
        {
    		db.ClearPlayerTable(); 	
         	SendOutSMS(_phoneNumber, "BEB> Player table cleared"); 
         	
        }
    	//-------Delete a player 
    	else if(CheckCommand("delete player", args[0]))
        {
    
    		String phoneNumber =""; 
    		Boolean DeletePlayer = false; 
    		
    		if(args.length >= 2)
    		{
    			if(textParser.ParseWord("me", args[1]))
    			{
    				phoneNumber = textParser.ParseNumber(_phoneNumber); 
    				DeletePlayer = true; 
    			}
    			else 
    			{
    				phoneNumber = textParser.ParseNumber(args[1]);
    				DeletePlayer = true; 
    			}
    		}
    		else 
    		{
    			SendOutSMS(_phoneNumber, "BEB> ERROR \r\n You must specify a phone number for player.");
    			DeletePlayer = false; 
    		}
    		
    		if(DeletePlayer)
    		{
	    		Player player = null;
	    	    player = db.getPlayer(phoneNumber); //find the player
	    	    if(player == null) //If the player does not exist
	    	    {
	    	    	SendOutSMS(_phoneNumber, "BEB> Player ["+phoneNumber+"] not in the player list");
	    	    }
	    	    else  // otherwise remove the player. 
	    	    {
	    	        db.deletePlayer(player);
	    	        SendOutSMS(_phoneNumber, "BEB> Player ["+phoneNumber+"] removed from the player list.");
	    	    }
    		}
        }
    	//-------Show All PLayers 
    	else if(CheckCommand("show all players", args[0]))
        {
    		 SendOutSMS(phoneNumber, "BEB> PLAYER TABLE \r\n" + getAllPlayers());
    		 
        }
    	//-------Show a PLayer 
    	else if(CheckCommand("show player", args[0]))
        {
    		String phoneNumber =""; 
    		Boolean ShowPlayer = false; 
    		
    		if(args.length >= 2)
    		{
    			if(textParser.ParseWord("me", args[1])) //if "me" is used for player number it will use your number
    			{
    				phoneNumber = textParser.ParseNumber(_phoneNumber); 
    				ShowPlayer = true; 
    			}
    			else 
    			{
    				phoneNumber = textParser.ParseNumber(args[1]);
    				ShowPlayer = true; 
    			}
    		}
    		else 
    		{
    			SendOutSMS(_phoneNumber, "BEB> ERROR \r\n You must specify a phone number for player.");
    			ShowPlayer = false; 
    		}
    		
    		if(ShowPlayer)
    		{
	    		Player player = null;
	    	    player = db.getPlayer(phoneNumber); //find the player
	    	    if(player == null) //If the player does not exist
	    	    {
	    	    	SendOutSMS(_phoneNumber, "BEB> Player ["+phoneNumber+"] not in the player list.");
	    	    }
	    	    else  // otherwise print the player. 
	    	    {
	    	        SendOutSMS(_phoneNumber, player.toShortString());
	    	    }
    		}
    		 
        }
    	//-------Build All Responses 
    	else if(CheckCommand("build response table", args[0]))
        {
    		db.ClearResponseTable();
    		if(rtBuilder.PopulateResponses(args[1]))
    		{
    			SendOutSMS(phoneNumber, "BEB> Response table Built"); 
    		}
    		else 
    		{
    			SendOutSMS(phoneNumber, "BEB> ERROR \r\n "+args[1]+"not correct id for a table. Try \"beb\" or \"test\"");
    		}
    		
        }
    	//-------Show All Responses 
    	else if(CheckCommand("show response table", args[0]))
        {
    		printAllResponses(); 
    		//SendOutSMS(phoneNumber, "BEB> PLAYER TABLE \r\n" + getAllResponses());
        }
    	else 
    	{
    		
    		SendOutSMS(phoneNumber, "BEB> Bad Command. Try Again.");
    	}
	} 
    
    public boolean CheckCommand(String _word, String _message)
	{
		String word = COMMAND_PREFIX + _word;
		word = word.toLowerCase(Locale.getDefault()); 
		if (_message.toLowerCase(Locale.getDefault()).startsWith(word))
		{
			return true;
		}
		return false; 
	}
    
    //Given a command with arguments "CMD set story -[player #] -[story location] parses the arguments  after the -. arg[0] is the command. ard[n] are the vars
    public String[] ParseCommand(String _message)
    {
    	String[] args = _message.split(" -");
    	return args; 
    	
    }
    
    
    public void ParseMessage(String _phoneNumber, String _message)
    {
    	Player player = null;
    	player = db.getPlayer(_phoneNumber); //find the player
    	if(player == null)//&& _message.equals("BEB") //If the player does not exist, make a new one
    	{
    		
    		SendOutSMS(phoneNumber, "Welcome to BEB");
    		player = new Player();
            player.setName(				 "____");
            player.setPhoneNumber(	phoneNumber);
            player.setLastAnswer(		message);
            player.setStoryLocation(		"0");
            player.setOldStoryLocation(		"0");
            player.setState(		   "active");
            
            db.addPlayer(player); 
            
            
            Response firstrResponse = db.getResponse("0");
            SendOutSMS(phoneNumber, firstrResponse.getText());
            
    	}
    	else  // otherwise update last message and story
    	{
    		//Story parsing here
            String outMessage = textParser.ParseMesssage(player, _message);
            SendOutSMS(phoneNumber, outMessage);
    	}

    }
    
    public String getAllPlayers()
    {
    	List<Player> players = db.getAllContacts();       
    	String playersString = "";
        for (Player cn : players) 
        {
        	playersString += ">" + cn.toShortString(); 
        	playersString += "\r\n"; 
        }
        return playersString;
    }
    
    public String getAllResponses()
    {
    	List<Response> responses = db.getAllResponses();    
    	String responseString = "";
    	for (Response r : responses) 
        {
    		responseString += ">" + r.toShortString(); 
    		responseString += "\r\n"; 
        }
        return responseString;
    }
    
    public void printAllPlayers()
    {
    	Log.d(">>PLAYER_TABLE ", "Reading all players.."); 
    	List<Player> players = db.getAllContacts();       
         for (Player p : players) 
         {// Writing Players to log
             String log = p.toShortString();
             Log.d("Player: ", log);
         }
    }
    
    public void printAllResponses()
    {
    	Log.d(">> RESPONSE_TABLE ", "Reading all responses..");  
    	List<Response> responses = db.getAllResponses();       
         for (Response r : responses) 
         {// Writing Responses to log
             String log = r.toShortString(); 
             Log.d("Response: ", log);
         }
    }
    
    
    
    
   
}
