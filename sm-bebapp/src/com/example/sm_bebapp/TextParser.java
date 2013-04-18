package com.example.sm_bebapp;

import android.util.Log;


//This Class provides fucntions to parse Text 
public class TextParser {
	
	
	String yesList 		= "(yes|yeah|y|yup|yuppers)"; 
	String noList 		= "(no|nope|n|nah|noo|negative)"; 
	String maybeList 	= "(maybe|sometimes|not sure|can't tell|possibly)"; 
	
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
		if (_message.equals(""))
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
	    if(_responseLink.equals("none"))
	    {
	    	_responseLink =  _currentResponse.getAnyLink();
	    }
		
		Response outResponse = db.getResponse(_responseLink);
		if(outResponse != null)
		{
			_player.setStoryLocation(outResponse.getId());
    		_player.setLastAnswer( _message);
			db.updatePlayer(_player);
			return outResponse.getText(); //parse text here
		}
		else 
		{
			Log.d(">> ERROR!"  , " Response " +_currentResponse.getId()+" has no out link for " + _responseType + ". Dump> " + _currentResponse.toShortString());
			return "BEB> ERROR! Response " +_currentResponse.getId()+" has no out link for " + _responseType + ".";
		}
		
	}

}
