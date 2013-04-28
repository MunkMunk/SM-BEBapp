package com.example.sm_bebapp;

import android.util.Log;


//This Class provides fucntions to parse Text 
public class TextParser {
	
	
	String yesList 		= "(yes|yeah|y|yup|yep|yuppers|true|correct|sure)"; 
	String noList 		= "(no|nope|n|nah|noo|negative|false|wrong)"; 
	String maybeList 	= "(maybe|sometimes|not sure|can't tell|possibly|I don't know|kinda|kind of|kindof|sorta|sortof|sort of)"; 
	
	
	
	
	DatabaseHandler db; 
	
	public TextParser(DatabaseHandler _db)
	{
		db = _db; 
	}
	
	public String ParseMesssage(Player _player, String _message)
	{
		String storyId = _player.getStoryLocation();
		Response currentResponse = db.getResponse(storyId);
		
		if(currentResponse != null)
		{
			switch (ParseYesNo( _message)) 
			{				
				case 0:  
					return updateAnswer( _player, _message, currentResponse, currentResponse.getNoResponseLink() , "No Response");
				case 1:  
					return updateAnswer( _player, _message, currentResponse, currentResponse.getYesLink() , "Yes");
				case 2:  
					return updateAnswer( _player, _message, currentResponse, currentResponse.getNoLink() , "No");
				case 3:  
					return updateAnswer( _player, _message, currentResponse, currentResponse.getMaybeLink() , "Maybe");
				case 4:  
					return updateAnswer( _player, _message, currentResponse, currentResponse.getAnyLink() , "Any");
				default: 
					 Log.d(">> ERROR!"  , " Could not parse Yes/No on message. Dump> Message: " + _message + " Player " + _player.toShortString());
					return "BEB> Error! could not parse Yes No. If you are seing this error something really bad happened I don't know what."; 
			}
		}
		else
		{
			_player.setStoryLocation("0");
			db.updatePlayer(_player);
			Log.d(">> ERROR!"  , " Player does not have a response Id. Dump> Message: " + _message + " Player " + _player.toShortString());
			return "BEB> Error! You are no longer on the dialog tree. Resetting location to the start";
		}
		
		
	}
	
	//Parses the message for yes or nos or maybes
	public int ParseYesNo(String _message)
	{
		if (_message.equals("|none|"))
		{
			return 0; //"none"; 
		}
		else if (ParseWord(yesList,_message))
		{
			return 1; //"yes";
		}
		else if (ParseWord(noList,_message))
		{
			return 2; //"no";
		}
		else if (ParseWord(maybeList,_message))
		{
			return 3; //"maybe"; 
		}
		else 
		{
			return 4; //"any";
		}
	}
	
	//searches for a word or a list of words in the message. returns true or false if a word is found. Lists are formatted as (foobar|foobaz|...)
	public boolean ParseWord(String _word, String _message)
	{
		if (_message.matches("(?i).*\\b"+_word+"\\b.*"))
		{
			return true;
		}
		return false; 
	}
	
	public String updateAnswer(Player _player, String _message, Response _currentResponse,  String _responseLink, String _responseType)
	{
	    //---------- Checks to see if special player data need to be stored based on the response id. SO UGLY!! 
		if(_currentResponse.getId().equals("0"))//Store the players name. 
		{
			_player.setName(BuildName(_message)); 
		}
		else if(_currentResponse.getId().equals("1a"))
		{
			_player.setName(BuildName(_message)); 
		}
		
		if(_responseLink.equals("none"))
	    {
	    	_responseLink =  _currentResponse.getAnyLink();
	    }
		
		Response outResponse = db.getResponse(_responseLink);
		if(outResponse != null)
		{
			_player.setStoryLocation(outResponse.getId());
    		_player.setLastAnswer( _message);
    		_player.setOldStoryLocation(_currentResponse.getId());
			db.updatePlayer(_player);
			return  BuildResponse(_player, _message, outResponse);
			//return 		outResponse.getText(); //parse text here
		}
		else 
		{
			Log.d(">> ERROR!"  , " Response " +_currentResponse.getId()+" has no out link for " + _responseType + ". Dump> " + _currentResponse.toShortString());
			return "BEB> ERROR! Response " +_currentResponse.getId()+" has no out link for " + _responseType + ".";
		}
		
	}
	
	
	//Parses the out response for the key words between ; ; and replaces them appropreatly. 
	public String BuildResponse(Player _player, String _message, Response _outResponse)
	{
		String[] resposnseArgs = _outResponse.getText().split(";");
		String response = "";
		
		for (String r : resposnseArgs) {
			if(r.equals("name"))
			{
				r = _player.getName(); 
			}
			else if(r.equals("lastanswer"))
			{
				r = _message.toLowerCase(); //To lower case makes it less obvious that its the same exact thing the player typed. 
			}
			
			response += r;
		}
		return response; 
	}
	
	//Takes a message to be stored as a name and rebuilds it with proper case. "garruS VAKarian" => "Garrus Vakarian"
	public String BuildName(String _message)
	{
		
		
		String[] nameArgs = _message.split(" ");
		String name = ""; 
		for (String n : nameArgs) 
		{
			if (n.length() == 0)
			{
				//Empty do nothing
			}
			else if (n.length() == 1)
			{
				name += n.toUpperCase() + " "; //String of length 1
			}
			else 
			{
				name += n.substring(0,1).toUpperCase() + n.substring(1).toLowerCase() + " "; 
			}
			
		}
		Log.d(">> BUILD NAME"  , "Built new name" +_message+ " -> "+name);
		return name.trim();
	}
	
	public String FormatPhoneNumber( String _phonenumber )
	{
		String phonenumber = _phonenumber;//.split("+")[0]; //remove + 
		String[] phonenumberchunks = phonenumber.split("-");//ex: 505-573-3247
		phonenumber = "";
		for (String n : phonenumberchunks) 
		{
			 phonenumber += n; 
		}
		
		if(phonenumber.length() == 10)//ex: 15055733247
		{
			return "+1" + phonenumber; //ex: +15055733247
		}
		else if(phonenumber.length() == 11 && phonenumber.startsWith("1"))//ex: 15055733247
		{
			return "+" + phonenumber; //ex: +15055733247
		}
		else if (phonenumber.length() == 12 && phonenumber.startsWith("+"))//ex: +5055733247
		{
			return phonenumber;//ex: +5055733247
		}
		else
		{
			return "ERROR! "+phonenumber+" is not a phone number."; 
		}
		
		
	
	}

	

}
