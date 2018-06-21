package modul;

import java.util.Random;

public class Greetings {
	Random rand = new Random();
	
	GetGeneralPlayerInfo pl = new GetGeneralPlayerInfo();
	
	String plName = pl.playName();
	
	public String createGreeting() {
		String greeting = "Welcome back";
		Integer number = rand.nextInt(5); 
		switch(number){
		case 1:
			greeting = "Greetings Traveler";
			break;
		case 2:
			greeting = plName + " is back in da house!";
			break;
		case 3:
			greeting = "Nice to see you, " + plName;
			break;
		case 4: 
			greeting = "You're a wizard, " + plName;
			break;
		case 5:
			greeting = "Good, better, " + plName;
			break;
		}
		
		return greeting;
	}

}
