package finalGame;
import java.awt.Color;
import acm.graphics.*;

public class SpaceShip extends GCompound{

	//instance variables
	private GPolygon spaceShip; 
	private GRect missiles;
	private GImage jet;
	private GRect bullet1; 
	private GLabel label;
	int score = 0;
	public SpaceShip()
	{
		//main spaceship body
		spaceShip = new GPolygon();
		spaceShip.addVertex(0,0);
		spaceShip.addVertex(20,-15);
		spaceShip.addVertex(30,-15);
		spaceShip.addVertex(40,-15);
		spaceShip.addVertex(48,0);
		spaceShip.addVertex(53,0);
		spaceShip.addVertex(60,-15);
		spaceShip.addVertex(70,-15);
		spaceShip.addVertex(80,-15);
		spaceShip.addVertex(100,0);
		spaceShip.addVertex(50,-80);
		spaceShip.addVertex(0,0);
		spaceShip.setFilled(true);
		spaceShip.setColor(Color.GRAY);
		add(spaceShip);
		
		//draw small missiles
		missiles = new GRect(3,50);
		missiles.setColor(Color.GREEN);
		missiles.setFilled(true);
		add(missiles,47.5,-80);
		missiles= new GRect(0.5,50);
		missiles.setColor(Color.GREEN);
		missiles.setFilled(true);
		add(missiles,49,-85);

		//adding image on body
		jet = new GImage("paint1.png");
		jet.setSize(120,100);
		add(jet,-4,-90);
	}
	
	//add score
	public GLabel addScore()
	{
		//set score label
		label = new GLabel("Score: " + score);
		add(label);
		label.setFont("SansSerif-BOLD-25");
		label.setColor(Color.WHITE);
		return label;
	}
	
	//update score
	public void updateScore()
	{
		//update score and score label
		score++;
		label.setLabel("Score: " + score);
	}	
}




