package modul;

import java.util.Random;

/**
 * Chooses a random greeting for the player
 * 
 * @author GiBr03
 *
 */
public class Greetings {
	Random rand = new Random();

	GetGeneralPlayerInfo pl = new GetGeneralPlayerInfo();

	String plName = pl.playName();

	// Chooses a random greeting
	public String createGreeting() {
		String greeting = "Willkommen zurück";
		Integer number = rand.nextInt(8);
		switch (number) {
		case 1:
			greeting = "Grüsse Reisender";
			break;
		case 2:
			greeting = plName + " is back in da house!";
			break;
		case 3:
			greeting = "Nett dich zu sehen, " + plName;
			break;
		case 4:
			greeting = "You're a wizard, " + plName;
			break;
		case 5:
			greeting = "Gut, besser, " + plName;
			break;
		case 6:
			greeting = "Bonjour " + plName;
		case 7:
			greeting = "Voulez vous un Baguette " + plName + " ?";
		}

		return greeting;
	}

}
