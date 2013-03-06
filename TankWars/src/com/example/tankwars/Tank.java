package com.example.tankwars;

/**
 * @author Edward Jezisek
 * Tank class to monitor a tanks location, turrets position and health
 * @version 1.0
 * Unit Testing: No
 * Date: March 6th, 2013
 * Fully functional Tank Class.
 * Still needs to incorporate hit points and needs to announce whether it's still alive.
 */
public class Tank {
	final double MOVE_AMMOUNT=1;
	final double DEGREE_AMMOUNT=1;
	final double GRAVITY=1;
	final int SCREEN_MAX=500;
	final int SCREEN_MIN=0;
	private int position;
	private boolean rotateLeft;
	private float bulletXPos;
	private float bulletYPos;
	private double power;
	private float degrees;
	enum WEAPON{GUN};
	
	/**
	 * This returns whether the tank is facing left or right.  Returns true if it's facing left, right otherwise.
	 * @return
	 * @author Edward Jezisek
	 */
	boolean getRotate()
	{
		return rotateLeft;
	}
	
	/**
	 * This returns the bullets X positions when time t is input.
	 * @param t
	 * @return 
	 */
	public float getBulletX(double t)
	{ 	
		if(rotateLeft)
			return (float)(bulletXPos+power/40*Math.cos(Math.toRadians(degrees))*t);
		else
			return (float)(float)(bulletXPos-power/40*Math.cos(Math.toRadians(degrees))*t);
	}
	
	/**
	 * This returns the bullets Y position when time t is input.
	 * @param t
	 * @return
	 * @author Edward Jezisek  
	 */
	public float getBulletY(double t)
	{
		float yPos=(float) (bulletYPos-power/40*Math.sin(Math.toRadians(degrees))*t+GRAVITY/100*t*t);
		return yPos;
	}
	
	/**
	 * This sets the initial bullet position of the tanks.
	 * @param xPos
	 * @param yPos
	 * @author Edward Jezisek
	 */
	public void setBulletPos(float xPos, float yPos)
	{
	
		bulletXPos=xPos;
		bulletYPos=yPos;
		if(bulletXPos<0)
			bulletXPos=0;
		if(bulletYPos<0)
			bulletYPos=0;
	}
	
	/**
	 * Creates a new tank at a given position and direction.
	 * @param position
	 * @param rotateLeft
	 * @author Edward Jezisek
	 */
	Tank(int position, boolean rotateLeft)
	{
		power=100;
		degrees=0;
		this.rotateLeft=rotateLeft;
		this.position=position;
	}

	/**
	 * Moves the tank left or right depending on the input: isRight.  If true it moves right, otherwise it moves left.
	 * @author Edward Jezisek
	 * @param isRight
	 */
	void move(boolean isRight)
		{
		if(isRight && canMove(MOVE_AMMOUNT, isRight))
			{
			position+=MOVE_AMMOUNT;
			}
		else if(!isRight && canMove(MOVE_AMMOUNT, isRight))
		{
			position-=MOVE_AMMOUNT;
		}
			
		}
	

	/**
	 * Ensures the tank is able to move.  Takes in the direction to move and the move ammount.
	 * @param moveAmmount
	 * @param isRight
	 * @return
	 */
	boolean canMove(double moveAmmount, boolean isRight)
	{
		if(isRight)
			return (position+moveAmmount<SCREEN_MAX);
		else
			return (position-moveAmmount>SCREEN_MIN);
	}
	
	
	/**
	 * Moves the tank's turret.
	 * @author Edward Jezisek
	 * @param isUp
	 */
	void turret_move(boolean isUp)
		{
		if(isUp && degrees<=(90-DEGREE_AMMOUNT))
			degrees+=DEGREE_AMMOUNT;
		else if(!isUp && degrees >=0+DEGREE_AMMOUNT)
			degrees-=DEGREE_AMMOUNT;
		}
	
	/**
	 * Returns the degrees of the turret.
	 * @return
	 */
	public float getDegrees()
	{
		return degrees;
	}
	
	/**
	 * Gets the tanks x position.
	 * @return
	 */
	public float getX() {
		// TODO Auto-generated method stub
		return position;
	}
}
