package modul;

import modul.ReadPlayerData;
/**
 * This class should get the player stats out of the db TODO: make this class do
 * something usefull
 * 
 * @author GiBr03
 *
 */
public class GetStats {
	ReadPlayerData rpd = new ReadPlayerData();
	public int getPoints() {
		return Integer.parseInt(rpd.getPlayerInfo("pointsreceived"));
	}

	public int getPointsTotal() {
		return Integer.parseInt(rpd.getPlayerInfo("pointstotal"));
	}

}
