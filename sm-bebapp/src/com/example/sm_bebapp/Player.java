package com.example.sm_bebapp;

public class Player {
	 
    //private variables
    String name;
    String phone_number;
    String last_answer; 
    String story_location; 
 
    // Empty constructor
    public Player(){
 
    }
    // constructor
    public Player(String name, String _phone_number){
        this.name = name;
        this.phone_number = _phone_number;
    }
 
    // getting name
    public String getName(){
        return this.name;
    }
    // setting name
    public void setName(String _name){
        this.name = _name;
    }
 
    // getting phone number
    public String getPhoneNumber(){
        return this.phone_number;
    }
    // setting phone number
    public void setPhoneNumber(String _phone_number){
        this.phone_number = _phone_number;
    }
    
    //getting last answer
    public String getLastAnswer(){
        return this.last_answer;
    }
    // setting last answer
    public void setLastAnswer(String _last_answer){
        this.last_answer = _last_answer;
    }
    
  //getting last answer
    public String getStoryLocation(){
        return this.story_location;
    }
    // setting last answer
    public void setStoryLocation(String _story_location){
        this.story_location = _story_location;
    }
    
    public String toShortString()
    {
    	return getName() + " " + getPhoneNumber() + " [" + getStoryLocation() +"]\r\n\"" + getLastAnswer() + "\"";
    	
    }
    
}
