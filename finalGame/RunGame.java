package finalGame;
import java.awt.Color;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acm.graphics.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class RunGame extends GraphicsProgram{

	//add stars in background 
	Background stars = new Background();
	//add moving stars in background 
	Background mStars = new Background();
	//add spaceship
	SpaceShip spaceShip = new SpaceShip();
	//add UFOs
	Obstacles ufo = new Obstacles();
	Obstacles ufo2 = new Obstacles();
	//add Enemy Bullets 
	EnemyBullet en_Bullet = new EnemyBullet();
	EnemyBullet en_Bullet2 = new EnemyBullet();
	
	//instance variables
	private GLabel addScore;
	private GLabel gameOver;
	private int xPos, yPos, key, x, y;
	private int speed = 10;
	//array list for adding bullets
	static ArrayList bullet;
	
	//set APPLET size and background color
	public void init()
	{
		x = 450;
		y = 750;
		setSize(1000,800);
		setBackground(Color.BLACK);
		addKeyListeners();
	}
	
	public void run()
	{
		//set background
		setBackground();
		//draw stars
		add(stars);
		//draw moving stars
		add(mStars);
		//draw spaceship
		add(spaceShip,x,y);
		//add score 
		addScore = spaceShip.addScore();
		//draw score
		add(addScore,5,25);
		//assign arrayList
		bullet = new ArrayList();
		//start game when user click
		waitForClick();
		//call method
		addUfos();
	}
	
	//move spaceship with arrow keys
	public void keyPressed(KeyEvent press)
    {
		//Collect key press
		key = press.getKeyCode();
		//get spaceShip position
		xPos = (int)spaceShip.getX();
		yPos = (int)spaceShip.getY();
		
		//move spaceship with arrow keys
		if (key == KeyEvent.VK_LEFT && spaceShip.getX() > 10)
		{
			spaceShip.move(-speed, 0);
		}
		else if (key == KeyEvent.VK_RIGHT && spaceShip.getX() < 890)
		{
			spaceShip.move(speed, 0);
		}
		if (key == KeyEvent.VK_DOWN && spaceShip.getY() < 800)
		{
			spaceShip.move(0, speed);
		}
		else if (key == KeyEvent.VK_UP && spaceShip.getY() > 250)
		{
			spaceShip.move(0, -speed);
		}
		//fire bullets with space bar key
		else if(key == KeyEvent.VK_SPACE && spaceShip.isVisible())
		{
			//call fire method when user press space
			fire(xPos,yPos); 
			pause(10);//pause time
		}
		
	}
	
	//draw and move UFOs
	public void addUfos()
	{
		
		//draw UFOs and random X position
		add(ufo,randomX(),-50);
		add(ufo2,randomX(), -50);
		//draw enemy bullets 
		add(en_Bullet,ufo.getX() + 40,ufo.getY() + 25);
		add(en_Bullet2,ufo2.getX(), ufo2.getY());
		
		//variables for checking if first UFO finished
		boolean visible = true;
		//count UFO hits
		int ufoHits = 0;
		int ufo2Hits = 0;
		//count spacehip hits
		int hit = 0;
		//move UFOs
		double yMove = 0.5;
		double xMove = 0;
		double y = 0.5;
		double x = 0;
		//invoke randomMove method
		double rMove = randomMove();
		
		while(true)
		{
			pause(5);//pause time
			//fill arrayList with bullets
			ArrayList bullets = getBullets();
			for(int i=0; i<bullets.size(); i++)
			{
				//get bullets
				GRect f = (GRect) bullets.get(i);
				//move bullets
				f.move(0, -1.5);
				
				//remove bullets after across the screen
				if(f.getY() < 10)
				{
					remove(f);//remove bullets
				}
				//count UFO hits if bullet intersects with UFO
				if(f.getBounds().intersects(ufo.getBounds()))
				{
					ufoHits += 1;
					remove(f);
					//update score
					spaceShip.updateScore();
					//remove UFO if UFO hits more than or equals 150
					if(ufoHits >= 150)
					{
						remove(en_Bullet);//remove bullets
						remove(ufo);
						visible = false;
					}
				}
				//count UFO2 hits if bullet intersects with UFO2
				else if(f.getBounds().intersects(ufo2.getBounds()))
				{
					ufo2Hits += 1;
					remove(f);
					//update score
					spaceShip.updateScore();
					//remove UFO2 if UFO2 hits more than or equals 150
					if(ufo2Hits >= 150)
					{
						remove(en_Bullet2);
						remove(ufo2);
						playMore();//call method that calls this function again
						break;//break loop
					}
				}
			}
			//move stars
			double j = 0.1;
			if(stars.getY() > 800)
			{
				//set stars location  
				stars.setLocation(0,-800);
			}
			stars.move(0, j);
			//----------------------
			//Move UFO 1
			ufo.move(xMove, yMove);
			//move UFO on Y if its less than 10 
			if(ufo.getY() < 10)
			{
				xMove = 0;
				yMove = 0.5;
			}	
			//move UFO randomly on X after or equals 80
			else if(ufo.getY() >= 80)
			{
				ufo.move(0, -2);
				yMove = 0;
				xMove = rMove;
			}
			//move UFO left if hits right wall
			else if(ufo.getX() >= 880)
			{
				xMove = -1;
			}
			//move UFO right if hits left wall
			else if(ufo.getX() == 0)
			{
				xMove = 1;
			}
			//move UFO 2 if first UFO finished
			if(visible == false)
			{
				ufo2.move(x, y);
				//move UFO2 on Y if its less than 10 
				if(ufo2.getY() < 10)
				{
					xMove = 0;
					yMove = 0.5;
				}
				//move UFO2 randomly on X after or equals 80
				else if(ufo2.getY() >= 80)
				{
					ufo2.move(0, -2);
					y = 0;
					x = rMove;
				}
				//move UFO2 left if hits right wall
				else if(ufo2.getX() >= 880)
				{
					x = -1;
				}
				//move UFO2 right if hits left wall
				else if(ufo2.getX() == 0)
				{
					x = 1;
				}
			}
			//move UFO Bullet
			if(ufo.getY() > 0)
			{
				en_Bullet.move(0,3);
				en_Bullet.setVisible(true);
			}
			if(en_Bullet.getY() > 750)
			{
				en_Bullet.setVisible(true);
				//add enemy bullet at UFO location
				en_Bullet.setLocation(ufo.getX() + 40, ufo.getY() + 25);
			}
			//Detect collision if spaceship hits with enemy bullet
			if(spaceShip.getBounds().intersects(en_Bullet.getBounds()))
			{ 
				//count hits
				hit += 1;
				//hide enemy bullets if it hits with spaceship
				en_Bullet.setVisible(false);
				//game will over if hits more than or equals 165
				if(hit >= 165)
				{
					//show game over message
					gameOver();
					//remove spaceship
					spaceShip.setVisible(false);
					//add blast
					GImage blast = blast(spaceShip.getX(),spaceShip.getY()-60);
					pause(250);
					remove(blast);
					break;//break loop
				}
			}
			//move UFO 2 bullet
			if(ufo2.getY() > 0)
			{
				en_Bullet2.move(0,3);
				en_Bullet2.setVisible(true);
			}
			if(en_Bullet2.getY() > 750)
			{
				en_Bullet2.setVisible(true);
				//set UFO 2 bullet location
				en_Bullet2.setLocation(ufo2.getX() + 40, ufo2.getY() + 25);
			}
			
			//remove spaceship if hit three or four times by enemy bullet
			if(spaceShip.getBounds().intersects(en_Bullet2.getBounds()))
			{ 
				//count hits
				hit += 1;
				en_Bullet2.setVisible(false);
				/*game will over if spaceship hits three or four times with 
				UFOs bullet*/
				if(hit >= 165)
				{
					//show game over message
					gameOver();
					//hide spaceship
					spaceShip.setVisible(false);
					//draw blast
					GImage blast = blast(spaceShip.getX(),spaceShip.getY()-60);
					pause(250);
					remove(blast);
					break;// break loop
				}
			}
		}
	}
	
	//generate random x position for UFO
	public int randomX()
	{
		RandomGenerator r = new RandomGenerator();
		int r1 = r.nextInt(1,880);
		return r1; //return random number between 1 to 880
	}
	
	//generate random move for UFO
	public double randomMove()
	{
		//array used to generate 1 or -1
		double[] num = {1,-1}; 
		RandomGenerator r = new RandomGenerator();
		int r1 = r.nextInt(num.length);
		double rNum = (num[r1]);
		return rNum; //return random number 1 or -1
	}
	
	//draw spaceship bullets 
	public void fire(int x, int y)
	{
		x = x + 50;
		y = y - 105;
		GRect addBullet = new GRect(x,y,3,30);
		addBullet.setFilled(true);
		addBullet.setColor(Color.GREEN);
		add(addBullet);
		//add bullets to array list
		bullet.add(addBullet);
	}
	
	//return bullets
	public static ArrayList getBullets()
	{
		return bullet;
	}
	
	//add background image
	public GImage setBackground()
	{
		GImage background = new GImage("universe.jpg");
		background.setSize(1000,800);
		add(background);
		return background;
	}
	
	//adding blast image
	public GImage blast(double x, double y)
	{
		GImage blast = new GImage("blast.gif");
		blast.setSize(150,150);
		add(blast,x,y);
		return blast;
	}
	
	//call this method if player still alive in game
	public void playMore()
	{
		addUfos();//call UFOs method
	}
	
	//Game over message
	public void gameOver()
	{
		//set score label
		gameOver = new GLabel("Game Over");
		add(gameOver,340,400);
		gameOver.setFont("SansSerif-BOLD-60");
		gameOver.setColor(Color.RED);
	}
	
}
