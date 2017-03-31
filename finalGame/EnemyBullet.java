package finalGame;
import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GOval;

public class EnemyBullet extends GCompound{

	//instance variables
	private GOval en_Bullet;
	
	//draw enemy bullets
	public EnemyBullet()
	{
		en_Bullet = new GOval(10,10);
		add(en_Bullet);
		en_Bullet.setFilled(true);
		en_Bullet.setColor(Color.RED);
	}
}
