package com.example.tankwars;

public class Tank {
	final int MOVE_AMMOUNT=5;
	final int SCREEN_MAX=1000;
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
	boolean canMove(int moveAmmount, boolean isRight)
	{
		if(isRight)
			return (position+moveAmmount<SCREEN_MAX);
		else
			return (position-moveAmmount>SCREEN_MAX);
	}
	void turret_move(boolean direction)
		{
		
		}
}
