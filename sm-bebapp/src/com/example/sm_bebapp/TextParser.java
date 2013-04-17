package com.example.sm_bebapp;


//This Class provides fucntions to parse Text 
public class TextParser {
	
	
	String yesList 		= "(yes|yeah|y|yup|yuppers)"; 
	String noList 		= "(no|nope|n|nah|noo|negative)"; 
	String maybeList 	= "(maybe|sometimes|not sure|can't tell|possibly)"; 
	
	
	
	public void TextParser()
	{
		
		
	}
	
	public String ParseMesssage(Player _player, String _message)
	{
		switch (ParseYesNo( _message)) 
		{
			case 0:  
			return "You Didn't say anything"; 
			case 1:  
			return "You said yes"; 
			case 2:  
			return "You said no"; 
			case 3:  
			return "You said maybe"; 
			case 4:  
			return "You said something"; 
			default: 
			return "You said something"; 
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
	
	

}
