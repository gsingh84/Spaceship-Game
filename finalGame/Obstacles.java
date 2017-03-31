package finalGame;
import java.awt.Color;
import acm.graphics.*;

public class Obstacles extends GCompound{

	//instance variable
	//array
	private GOval[] ufo1 = new GOval[3];
	
	//draw UFO
	public Obstacles()
	{
		
		ufo1[0] = new GOval(120,30);
		ufo1[0].setFilled(true);
		ufo1[0].setColor(Color.ORANGE);
		add(ufo1[0]);
		
		ufo1[1] = new GOval(70,50);
		ufo1[1].setColor(Color.WHITE);
		add(ufo1[1],22,-22);
		
		ufo1[1] = new GOval(30,30);
		ufo1[1].setFilled(true);
		ufo1[1].setColor(Color.WHITE);
		add(ufo1[1],42,-12);
		
		ufo1[2] = new GOval(120,30);
		ufo1[2].setFilled(true);
		ufo1[2].setColor(Color.ORANGE);
		add(ufo1[2]);
	}
}
