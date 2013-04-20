package com.example.sm_bebapp;

public class ResponseTreeBuilder {

	DatabaseHandler db; 
	
	
	public ResponseTreeBuilder(DatabaseHandler _db)
	{
		db = _db; 
	}
	
	public void PopulateResponses()
    {
    	AddResponse( "-1", "none", "none", "none", "0", "0",		"BEB> Game started");

    	//AddResponse( "0", "none", "none", "none", "1a", "1a",		"What year is it?");
    	AddResponse( "0", "none", "none", "none", "p1a", "p1nr",		"What is your name?");
    	
    	/*
    	AddResponse( "1a", "none", "none", "none", "2a", "2nr",		"Perfect, my calulations are spot on. I am in great need of your help! What is your name?");
    	//NR
    	AddResponse( "2a", "3y", "3n", "3n", "3n", "3n",			"Nice to meet you ;name;. You can call me Y. Time is limited so I can't explain everything, but in short, I am a member of BEB. We're trying to get a picture of a time before the world started fermenting. ([name]'s Year) is a critical year in the past and I need you to help me! Can I trust you? ");
    	AddResponse( "2nr", "3y", "3n", "3n", "3n", "3n",			"OK, I understand you need to protect your privacy, I will call you X and you can call me Y, Time is limited so I can't explain everything, but in short, I am a member of BEB. We're trying to get a picture of a time before the world started fermenting. (X's Year) is a critical year in the past and I need you to help me! Can I trust you?");
  	
    	
    	AddResponse( "3y", "none", "none", "none", "4a", "4a",		"Good to hear. Quantum tunnelers do not miss. I have faith in your abilities, ;name;. We all have a part to play no matter what time period we are from. ");
    	AddResponse( "3n", "none", "none", "none", "4a", "4a",		"Not to worry. I was like you once, but quantum tunnelers do not miss. I have faith in your abilities, ;name;. We all have an important part to play no matter what time period we are from.");
    	
    	AddResponse( "4a", "none", "none", "none", "5a", "5a",		"Ok if you are to help me, First I will need to grow your brain. Yeast cells cluster when they grow during the making of beer, so you need to get social. I'm not asking much, just that you ask two people what it is they value most? Try to do this casually without raising too much suspicion. I will follow up later.");
    	
    	AddResponse( "5a", "none", "none", "none", "6a", "6a",		";name;, how did it go?  I hope you learned something about values in your era. We're trying to use this information to assemble a fermentation molecular sequence we can use to keep beer-drinking a viable option in our future. Can you please send me one or two words that represent the values of the people you spoke to?");
    	
    	AddResponse( "6a", "none", "none", "none", "7a", "7a",		";name; you are a great help. Now I feel I can tell you about the Biomes, they are a group of beings whose purpose it is to make things split for expansion. They aren't exactly good or bad, but they do sometimes make things dissolve... not good for those of us who are trying to cluster! ");
    	
    	AddResponse( "7a", "none", "none", "none", "8a", "8a",		"Biomes are everywhere and look like normal people.  I need you to help me find them.  Tonight while you are out, someone is going to say something very strange.  Listen carefully for someone to say something about waste and consumption.");
    	
    	AddResponse( "8a", "none", "none", "none", "9a", "9a",		";name;, it's Y, things are not going well, the biomes seem to be working to keep us apart. The future seems to be fermenting, drowning in its own consumption. ");
    	
    	AddResponse( "9a", "none", "none", "none", "10a", "10a",	"With one center of attention, things can group. But there seems to be so many locuses of focuses. Are the Biomes winning?");
    	
    	AddResponse( "10a", "none", "none", "none", "11a", "11a",	"God, ;name;, it's crazy over here, the biomes are everywhere.  What have you heard?  I need you to send me the exact words right away.");
    	
    	AddResponse( "11a", "none", "none", "none", "d2", "d2",		"Oh ;name;, I thought I would never hear from you! You don't know how importantyour last text has been to our task.");

    	AddResponse( "d2", "p0", "p4", "p4", "p4", "p4",			"BEB> end of first draft. Would you like to start the second Demo?");
    	
    	AddResponse( "p0", "none", "none", "none", "p1a", "p1nr",			"What is your name?");
     	*/
    	AddResponse( "p1a", "p2y", "p2n", "p2m", "p2a", "p2nr", 	"Nice to meet you ;name;! Do you like pizza?");			
    	AddResponse( "p1nr","p2y", "p2n", "p2m", "p2a", "p2nr", 	"I shall call you X. Do you like pizza?");
    	
    	AddResponse( "p2y", "none", "none", "none","p3a","p3nr",	"Me Too! Glad to see another pizza fan out there. What is your favorite kind of Pizza, ;name;?");
    	AddResponse( "p2n", "none", "none", "none","p3a","p3nr" , 	"To bad. More for me I guess. :) ;name;, If you had to pick a type of pizza what kind would it be?");
    	AddResponse( "p2m", "none", "none", "none","p3a","p3nr", 	"I guess I hate some kinds too. Like ones with fish. What is your favorite kind of Pizza, ;name;?");
    	AddResponse( "p2a","p2y", "p2n", "p2m", "p2a", "p2nr", 		"You didn't answer my question. Do you like pizza?");
    	AddResponse( "p2nr","none", "none", "none", "p0", "p0", 	"Are you dead?");
    	
    	AddResponse( "p3a","p5y", "p5n", "p5m", "p5a", "p5nr", 		"That is cool. I too am a fan of ;lastanswer;. Pizza also has lots of cheese. Are you Lactose intolerant?" );
    	AddResponse( "p3nr","0", "p4", "p4", "p4", "p4", 			"I suppose you are silent because you hate me. Pizza also has lots of cheese. Are you Lactose intolerant?");
    	
    	AddResponse( "p5y","p6y", "p6n", "p6m", "p6a", "p6nr", 			"I feel for you, ;name;, may brother was Lactose Intolerant. And he died in the great war. Milk sucks do you agree?" );
    	AddResponse( "p5n","p4", "p4", "p4", "p4", "p4", 			"Good, It would be a shame if you were. I don't know if I could be freinds with you. Don't make me choose between you and Icecream. I'll do it!" );
    	AddResponse( "p5m","p4", "p4", "p4", "p4", "p4", 			"Well there is only one way to find out my freind! And its a long and dangerous road. May the force be with you." );
    	AddResponse( "p5a","p4", "p4", "p4", "p4", "p4", 			"Your rants do not concern me. I am a free spirit!! You cannot keep me down, maaaaaaaaaaaaaaaan!!" );
    	AddResponse( "p5nr","p4", "p4", "p4", "p4", "p4", 			"I suppose you are silent because you hate me. Do you want to start the demo over?");
    	
    	AddResponse( "p6y","p4", "p4", "p4", "p4", "p4", 			"Yeah, cows are only good for beef.");
    	AddResponse( "p6n","p4", "p4", "p4", "p4", "p4", 			"How dare you mock me! I am the queen of Alpha centari 6!");
    	AddResponse( "p6m","p4", "p4", "p4", "p4", "p4", 			"Just agree with me. It will be much easier that way");
    	AddResponse( "p6a","p4", "p4", "p4", "p4", "p4", 			";lastanswer;, is not a good attitude to have, ;name;");
    	AddResponse( "p6nr","p4", "p4", "p4", "p4", "p4", 			"I suppose you are silent because you hate me. Do you want to start the demo over?");
    	
//    	
    	AddResponse( "p4", "p0", "p4", "p4", "p4", "p4", "BEB> Demo Finished. To resume, text BEB to this number. Thank you for playing.");		
    	
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
