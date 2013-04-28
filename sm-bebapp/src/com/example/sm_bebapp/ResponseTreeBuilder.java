package com.example.sm_bebapp;

import java.util.Locale;

public class ResponseTreeBuilder {

	DatabaseHandler db; 
	
	
	public ResponseTreeBuilder(DatabaseHandler _db)
	{
		db = _db; 
	}
	
	public boolean PopulateResponses(String _treeID)
    {
    	if(_treeID.toLowerCase(Locale.getDefault()).contains("beb"))
    	{
    		PopulateBEBResponses(); 
    		return true; 
    	}
    	else if(_treeID.toLowerCase(Locale.getDefault()).contains("test"))
    	{
    		PopulateTestResponses(); 
    		return true; 
    	}
    	else 
    	{
    		return false; 
    	}
    	
    
    	
    }  
    
	public void PopulateBEBResponses()
    {
		AddResponse( "-1", "none", "none", "none", "0", "0",		"BEB> Game started");
    	AddResponse( "0", "none", "none", "none", "1a", "1a",		"What year is it?");
    	
    	AddResponse( "1a", "none", "none", "none", "2a", "2nr",		"Perfect, my calulations are spot on. I am in great need of your help! What is your name?");
    	
    	AddResponse( "2a", "3y", "3n", "3n", "3n", "3n",			"Nice to meet you ;name;. You can call me Y. Time is limited so I can't explain everything, but in short, I am a member of BEB. We're trying to get a picture of a time before the world started fermenting. ([name]'s Year) is a critical year in the past and I need you to help me! Can I trust you? ");
    	AddResponse( "2nr", "3y", "3n", "3n", "3n", "3n",			"OK, I understand you need to protect your privacy, I will call you X and you can call me Y, Time is limited so I can't explain everything, but in short, I am a member of BEB. We're trying to get a picture of a time before the world started fermenting. (X's Year) is a critical year in the past and I need you to help me! Can I trust you?");
  	
    	
    	AddResponse( "3y", "none", "none", "none", "4a", "4a",		"Good to hear. Quantum tunnelers do not miss. I have faith in your abilities, ;name;. We all have a part to play no matter what time period we are from. ");
    	AddResponse( "3n", "none", "none", "none", "4a", "4a",		"Not to worry. I was like you once, but quantum tunnelers do not miss. I have faith in your abilities, ;name;. We all have an important part to play no matter what time period we are from.");
    	
    	AddResponse( "4a", "none", "none", "none", "5a", "5a",		"Ok if you are to help me, First I will need to grow your brain. Yeast cells cluster when they grow during the making of beer, so you need to get social. I'm not asking much, just that you ask two people what it is they value most? Try to do this casually without raising too much suspicion. I will follow up later.");
    	
    	AddResponse( "5a", "none", "none", "none", "6a", "6a",		";name;, how did it go?  I hope you learned something about values in your era. We're trying to use this information to assemble a fermentation molecular sequence we can use to keep beer-drinking a viable option in our future. Can you please send me one or two words that represent the values of the people you spoke to?");
    	
    	AddResponse( "6a", "none", "none", "none", "7a", "7a",		";name;, you are a great help. Now I feel I can tell you about the Biomes, they are a group of beings whose purpose it is to make things split for expansion. They aren't exactly good or bad, but they do sometimes make things dissolve... not good for those of us who are trying to cluster! ");
    	
    	AddResponse( "7a", "none", "none", "none", "8a", "8a",		"Biomes are everywhere and look like normal people.  I need you to help me find them.  Tonight while you are out, someone is going to say something very strange.  Listen carefully for someone to say something about waste and consumption.");
    	
    	AddResponse( "8a", "none", "none", "none", "9a", "9a",		";name;, it's Y, things are not going well, the biomes seem to be working to keep us apart. The future seems to be fermenting, drowning in its own consumption. ");
    	
    	AddResponse( "9a", "none", "none", "none", "10a", "10a",	"With one center of attention, things can group. But there seems to be so many locuses of focuses. Are the Biomes winning?");
    	
    	AddResponse( "10a", "none", "none", "none", "11a", "11a",	"God, ;name;, it's crazy over here, the biomes are everywhere.  What have you heard?  I need you to send me the exact words right away.");
    	
    	AddResponse( "11a", "none", "none", "none", "12a", "12a",	"Oh ;name;, I thought I would never hear from you! You don't know how importantyour last text has been to our task.");

    	AddResponse( "12a", "none", "none", "none", "13a", "13a",	"Meet our comrades at Jimmy's No.43 to collect a reward");
    	
    	AddResponse( "13a", "none", "none", "none", "14a", "14a",	";name;, it's Y, I haven't heard from you in a while, what happened to you lastnight?  The Biomes did more damage than I thought. Pls txt me and let me know u are alright.");
    	
    	AddResponse( "14a", "none", "none", "none", "15a", "15a",	";name;,  Your body can suffer but your spirit can be free.  I think we both need another beer.  It's the yeast we could do.");
    	
    	AddResponse( "15a", "none", "none", "none", "16a", "16a",	"Stop, feel the direction of the wind. Stand for at least a minute and feel the direction change. Sooner or later, the tanks will appear. - Y");
    	
    	AddResponse( "16a", "none", "none", "none", "17a", "17a",	";name; ru here?  I need to tell u who I relly am, I'm not Y, Im \r\n <ERROR> [Code:114] Data_Corrupted </error>");
    	
    	AddResponse( "17a", "18y", "18a", "18a", "18a", "18a",		"Hello is this the number of ;name;?");
    	//Day 2
    	AddResponse( "18y", "19y", "19a", "19a", "19a", "19a",		"My name is Seed. I found your texts on a phone we confiscated from someone who called themselves Y who is now dead. Poisoned in waste. We need to assess your involvement. Did you know Y's real identity?");
    	AddResponse( "18a", "19y", "19a", "19a", "19a", "19a",		"My name is Seed. I found your number on a phone we confiscated from someone who called themselves Y who is now dead. Poisoned in waste. We need to assess your involvement. Did you know Y's real identity?");
    	
    	AddResponse( "19y", "none", "none", "none", "20a", "20nr",	"Thank you for your cooperation, ;name;. Y's been talking to a lot of people. Stand by for further notification.");
    	AddResponse( "19a", "none", "none", "none", "20a", "20nr",	"Further assessment is need to ascertain the validity of your answer. Stand by for notification.");
    	
    	AddResponse( "20a", "none", "none", "none", "21a", "21a",	";name;, It's Seed from Biome. We don't believe that you were involved inanything directly.");
    	AddResponse( "20nr", "none", "none", "none", "21a", "21a",	";name;, It's Seed from Biome. We don't believe that you were involved in anything directly, so there's no reason to be non-cooperative.");
    	
    	AddResponse( "21a", "none", "none", "none", "22a", "22a",	"(We know that you are motivated by drinking local, activating your imagination andbeing social.)  You need to wake up to reality, [name], we're not what Y said we are. We actually help people like you break out of the prison of their minds.");
    	
    	AddResponse( "22a", "none", "none", "none", "23a", "23a",		"Remembrance restores possibility to the past, making whathappened incomplete and completing what never was. Tonight I need you toteach someone a song from your childhood, sing it together, and ask thatperson to do the same for you. Both of you call us and sing us the songs together on our voicemeil and we will distribute the information. Then, meet us at Jimmy's for a toast to the future.");
    	
    	AddResponse( "23a", "END", "END", "END", "END", "END",			"BEB> end of first draft. text Restart to restart");
		
    	AddResponse( "END", "END", "END", "END", "END", "END",			"");
    }
	
	public void PopulateTestResponses()
    {
		AddResponse( "-1", "none", "none", "none", "p0", "p0",		"BEB> Game started");
		
		AddResponse( "p0", "none", "none", "none", "p1a", "p1nr",	"What is your name?");
		
		AddResponse( "p1a", "p2y", "p2n", "p2m", "p2a", "p2nr", 	"Nice to meet you ;name;! Do you like pizza?");			
    	AddResponse( "p1nr","p2y", "p2n", "p2m", "p2a", "p2nr", 	"I shall call you X. Do you like pizza?");
    	
    	AddResponse( "p2y", "none", "none", "none","p3a","p3nr",	"Me Too! Glad to see another pizza fan out there. What is your favorite kind of Pizza, ;name;?");
    	AddResponse( "p2n", "none", "none", "none","p3a","p3nr" , 	"To bad. More for me I guess. :) ;name;, If you had to pick a type of pizza what kind would it be?");
    	AddResponse( "p2m", "none", "none", "none","p3a","p3nr", 	"I guess I hate some kinds too. Like ones with fish. What is your favorite kind of Pizza, ;name;?");
    	AddResponse( "p2a","p2y", "p2n", "p2m", "p2a", "p2nr", 		"You didn't answer my question. Do you like pizza?");
    	AddResponse( "p2nr","none", "none", "none", "p0", "p0", 	"Are you dead?");
    	
    	AddResponse( "p3a","p5y", "p5n", "p5m", "p5a", "p5nr", 		"That is cool. I too am a fan of ;lastanswer;. Pizza also has lots of cheese. Are you Lactose intolerant?" );
    	AddResponse( "p3nr","0", "p4", "p4", "p4", "p4", 			"I suppose you are silent because you hate me. Pizza also has lots of cheese. Are you Lactose intolerant?");
    	
    	AddResponse( "p5y","p6y", "p6n", "p6m", "p6a", "p6nr", 		"I feel for you, ;name;, may brother was Lactose Intolerant. And he died in the great war. Milk sucks do you agree?" );
    	AddResponse( "p5n","p4", "p4", "p4", "p4", "p4", 			"Good, It would be a shame if you were. I don't know if I could be freinds with you. Don't make me choose between you and Icecream. I'll do it!" );
    	AddResponse( "p5m","p4", "p4", "p4", "p4", "p4", 			"Well there is only one way to find out my freind! And its a long and dangerous road. May the force be with you." );
    	AddResponse( "p5a","p4", "p4", "p4", "p4", "p4", 			"Your rants do not concern me. I am a free spirit!! You cannot keep me down, maaaaaaaaaaaaaaaan!!" );
    	AddResponse( "p5nr","p4", "p4", "p4", "p4", "p4", 			"I suppose you are silent because you hate me. Do you want to start the demo over?");
    	
    	AddResponse( "p6y","p4", "p4", "p4", "p4", "p4", 			"Yeah, cows are only good for beef.");
    	AddResponse( "p6n","p4", "p4", "p4", "p4", "p4", 			"How dare you mock me! I am the queen of Alpha centari 6!");
    	AddResponse( "p6m","p4", "p4", "p4", "p4", "p4", 			"Just agree with me. It will be much easier that way");
    	AddResponse( "p6a","p4", "p4", "p4", "p4", "p4", 			";lastanswer;, is not a good attitude to have, ;name;");
    	AddResponse( "p6nr","p4", "p4", "p4", "p4", "p4", 			"I suppose you are silent because you hate me. Do you want to start the demo over?");
  
    	AddResponse( "p4", "END", "END", "END", "END", "END", 		"BEB> Demo Finished. To resume, text BEB to this number. Thank you for playing.");		
    	
    	AddResponse( "END", "END", "END", "END", "END", "END", "");
    			
    }
	
    public void AddResponse(String _id, String _yesLink,String _noLink,String _maybeLink, String _anyLink, String _noRespLink,String  _text)
    {
    	Response response = new Response(); 
    	response.setId(				_id);
        response.setText(			_text);
        response.setYesLink(		_yesLink);
        response.setNoLink(			_noLink);
        response.setMaybeLink(		_maybeLink);
        response.setAnyLink(		_anyLink);
        response.setNoResponseLink(	_noRespLink);
        db.addResponse(response);
    }
	
	
}
