package finalGame;
import java.awt.Color;
import acm.util.RandomGenerator;
import acm.graphics.*;

public class Background extends GCompound{

	//instance variables
	private GOval stars;
	private int x,y,size;
	
	//add multiple stars in background 
	public Background()
	{
		for(int i = 0; i< 50; i++)
		{
			x = xPos();
			y = yPos();
			size = size();
			drawStars();
		}
	}
	
	//generate random y position for stars
	public int xPos()
	{
		RandomGenerator r = new RandomGenerator();
		int r1 = r.nextInt(0,1000);
		return r1; //return random number between 1 to 880
	}
	
	//generate oval size
	public int size()
	{
		RandomGenerator r = new RandomGenerator();
		int r1 = r.nextInt(1,3);
		return r1; //return random number between 1 to 880
	}
	
	//generate random y position for stars
	public int yPos()
	{
		RandomGenerator r = new RandomGenerator();
		int r1 = r.nextInt(-15,800);
		return r1; //return random number between 1 to 880
	}
	
	//draw star 
	public void drawStars()
	{
		stars = new GOval(x,y,size, size);
		stars.setFilled(true);
		stars.setColor(Color.white);
		add(stars);
	}
}
