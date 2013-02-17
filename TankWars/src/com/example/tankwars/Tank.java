package com.example.tankwars;

//////////////////////////////////////////////////////////////////////////////////////////////
//Project Name   : Tank Wars                                                                //
//Author(s)      : Edward Jezisek                                                           //
//Purpose        : Create a tank class to monitor the tanks location and health.            //
//Version Number : Version 0.0                                                              //
//Unit Testing	 : No                                                                       //
//Date           : 2/12/2013                                                                //
//Input          : None.                                                                    //
//Dependencies   : An environment is required for the tank to move around in.               //
//////////////////////////////////////////////////////////////////////////////////////////////
//Enhancements	 : First Created Version                                                    //
//Version x      : No previous Version.                                                     //
//Date           : None                                                                     //
//////////////////////////////////////////////////////////////////////////////////////////////

public class Tank {
	final int MOVE_AMMOUNT=5;
	final int SCREEN_MAX=1000;
	final int SCREEN_MIN=0;
	private int position;
	private int turret_angle;
	private int energy;
	private int health;
	private int power;
	enum WEAPON{GUN};
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//Project Name   : Tank Wars                                                                //
	//Author(s)      : Edward Jezisek                                                           //
	//Purpose        : The purpose is to create an environment that the tank can move around in.//
	//Version Number : Version 0.0                                                              //
	//Unit Testing	 : No                                                                       //
	//Date           : 2/12/2013                                                                //
	//Input          : isRight, if true, the tank moves right, otherwise it moves left          // 
	//Dependencies   : MOVE_AMMOUNT, determines how much to move the tank.                      //
	//////////////////////////////////////////////////////////////////////////////////////////////
	//Enhancements	 : First Created Version                                                    //
	//Version x      : No previous Version.                                                     //
	//Date           : None                                                                     //
	//////////////////////////////////////////////////////////////////////////////////////////////
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//Project Name   : Tank Wars                                                                //
	//Author(s)      : Edward Jezisek                                                           //
	//Purpose        : The purpose is to create an environment that the tank can move around in.//
	//Version Number : Version 0.0                                                              //
	//Unit Testing	 : No                                                                       //
	//Date           : 2/12/2013                                                                //
	//Input          : moveAmmount, decides how much to move the Tank.                          // 
	//				 : isRight, checks whether the tank moves left or right                     //
	//Dependencies   : SCREEN_MAX, SCREEN_MIN, Uses these to check if the tanks goes off screen.//
	//////////////////////////////////////////////////////////////////////////////////////////////
	//Enhancements	 : First Created Version                                                    //
	//Version x      : No previous Version.                                                     //
	//Date           : None                                                                     //
	//////////////////////////////////////////////////////////////////////////////////////////////
	boolean canMove(int moveAmmount, boolean isRight)
	{
		if(isRight)
			return (position+moveAmmount<SCREEN_MAX);
		else
			return (position-moveAmmount>SCREEN_MIN);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//Project Name   : Tank Wars                                                                //
	//Author(s)      : Edward Jezisek                                                           //
	//Purpose        : The purpose is to create an environment that the tank can move around in.//
	//Version Number : Version 0.0                                                              //
	//Unit Testing	 : No                                                                       //
	//Date           : 2/12/2013                                                                //
	//Input          : moveAmmount, decides how much to move the Turrets angle.                 // 
	//				 : direction, checks which direction to move the turret.                    //
	//Dependencies   : None.                                                                    //
	//////////////////////////////////////////////////////////////////////////////////////////////
	//Enhancements	 : First Created Version                                                    //
	//Version x      : No previous Version.                                                     //
	//Date           : None                                                                     //
	//////////////////////////////////////////////////////////////////////////////////////////////
	void turret_move(int moveAmmount, boolean direction)
		{
		
		}
}
