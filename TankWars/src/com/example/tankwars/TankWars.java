package com.example.tankwars;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;


public class TankWars extends Activity {
private enum moveVal{moveLeft, moveRight, turrUp, turrDown, fire};
private Tank playerOne;
private Tank playerTwo;
private boolean playerOneActive=true;
private ImageView Environment;
private ImageView Directions[]=new ImageView[5];
private boolean deleteThreadRunning = false;
private boolean pause=false;
private boolean cancelDeleteThread = false;
private Handler handler = new Handler();
private Environment theEnvironment;

	/**
	 * Creates the environment and sets it up with the android device.
	 * March 6, 2013
	 */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create the first tank
    	playerOne=new Tank(100, true);
    	//Create the second tank
    	playerTwo=new Tank(400, false);
    	//Create the environment.
    	theEnvironment=new Environment(this, 500, 300);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_game_main);
        
        //Set the environment to the imageview
        Environment=(ImageView)findViewById(R.id.Environment);
        
        
        //Create all the directional image views.
    	int ctr=0;
    	Directions[ctr++]=(ImageView)findViewById(R.id.leftArrow);
    	Directions[ctr++]=(ImageView)findViewById(R.id.rightArrow);
    	Directions[ctr++]=(ImageView)findViewById(R.id.upArrow);
    	Directions[ctr++]=(ImageView)findViewById(R.id.downArrow);
    	Directions[ctr++]=(ImageView)findViewById(R.id.fire);
    	ctr=0;
    	
    	//Set each to it's onTouch listener(this allows it to receive touch events.)
    	Directions[ctr++].setOnTouchListener(move(moveVal.moveLeft));
    	Directions[ctr++].setOnTouchListener(move(moveVal.moveRight));
    	Directions[ctr++].setOnTouchListener(move(moveVal.turrUp));
    	Directions[ctr++].setOnTouchListener(move(moveVal.turrDown));
    	Directions[ctr++].setOnTouchListener(move(moveVal.fire));
    	
    	//Set the image bitmap.
        Environment.setImageBitmap(theEnvironment.getBitmap());
        
        //Draw the environment
        drawEnvironment();
        //Update the screen.
		Environment.invalidate();
 }

	/**
	 * Sets up the layout with the device.
	 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * Moves the tank right.
     * @param v
     */
    public void moveTankRight(View v)
    {
    	playerOne.move(true);
    }
    
    /**
     * This performs an action based on the action the user sent.
     * @param move
     * @return
     */
    private OnTouchListener move(final moveVal move)
    {
    	OnTouchListener moveR=new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.v("this is Working", "nope");
				if(pause)
					return false;
				switch(event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					{
					/**
					 * This starts the item, move references the button they pressed.
					 */
					startMoving(move);
					return true;
					}
				case MotionEvent.ACTION_UP:
					{
					cancelDeleteThread=true;
					return true;
					}
				}
				return false;
			}
    	};
		return moveR;
    	
    }
    
    /**
     * This starts moving the item.  It creates a new thread to allow the item to move.
     * @param move
     */
    private void startMoving(moveVal move)
    {
    	if(!deleteThreadRunning && !pause)
    	{
    		if(playerOneActive)
    			startMoveThread(move,playerOne);
    		else
    			startMoveThread(move, playerTwo);
    	}
    	else if(!pause)
    	{
    		deleteThreadRunning=false;
    		cancelDeleteThread=false;
    	}
    }

    /**
     * This draws the environment.
     */
    private void drawEnvironment()
    {
    	theEnvironment.refreshEnvironment();
    	theEnvironment.drawTank(playerOne);
    	theEnvironment.drawTank(playerTwo);
    }
    
    /**
     * This performs the action on the tank depending on what they pressed.
     * @param move
     * @param Player
     */
    private void startMoveThread(final moveVal move, final Tank Player) {
    	switch(move){
    	case fire:
    		//Fires the bullet.
    		fireBullet(Player);
    		//Switches the active player
			playerOneActive=(!playerOneActive);
			break;
    	default:
    		break;
    	}
    	
    	//Starts a new thread to allow movement.
        Thread r = new Thread() {
            @Override
            public void run() {

                try {
                    deleteThreadRunning = true;
                    while (!cancelDeleteThread) {

                        handler.post(new Runnable() {   
                            @Override
                            public void run() {
                            	switch(move)
                            	{
                            	//This moves the player right.
                            	case moveRight:
                            		Player.move(true);
                            		drawEnvironment();
                            		Environment.invalidate();
                            		break;
                            	//This moves the player left.
                            	case moveLeft:
                            		Player.move(false);
                            		drawEnvironment();
                            		Environment.invalidate();
                            		break;
                            	//This moves the turret down.
								case turrDown:
									Player.turret_move(false);
									drawEnvironment();
									Environment.invalidate();
									break;
								//This moves the turret up.
								case turrUp:
									Player.turret_move(true);
									drawEnvironment();
									Environment.invalidate();
									break;
								//This fires the turret.
								case fire:
									cancelDeleteThread=false;
									break;
								default:
									break;	
                            	}

                            }
                        });

                        try {
                        	//Sleep the thread for 50 ms every time.
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(
                                "Could not wait between char delete.", e);
                        }
                    }
                }
                finally
                {
                    deleteThreadRunning = false;
                    cancelDeleteThread = false;
                }
            }
        };

        // actually start the delete char thread
        r.start();
    }
    
    /**
     * This fires the bullet from players position.
     * @param Player
     */
    private void fireBullet(final Tank Player){
    //Ensure no other buttons can be pressed.
    pause=true;
    
    //Gets the bullets position.
    if(Player.getRotate())
    	Player.setBulletPos((float) (Player.getX()+28+28*Math.cos(Math.toRadians(Player.getDegrees()))), (float) (130+28*Math.sin(Math.toRadians(-Player.getDegrees()))));
    else
    	Player.setBulletPos((float) (Player.getX()+16-28*Math.cos(Math.toRadians(Player.getDegrees()))), (float) (130+28*Math.sin(Math.toRadians(-Player.getDegrees()))));

    //This starts a thread
    Thread thread=  new Thread(){
    	@Override
		public void run(){
       		try{
       			double t=0;
			    while(Player.getBulletY(t)<150-50/7)
			    {
			    final double fin=t;
			    handler.post(new Runnable() {   
			    @Override
			    public void run() {
			    // TODO Auto-generated method stub
			    	drawEnvironment();
			        theEnvironment.drawBullet(Player.getBulletX(fin), Player.getBulletY(fin));	
			        Environment.invalidate();
			    	}
			    });
			    
			    try{
			    	Thread.sleep(100);
			    	t+=2;
			    }
			    catch (InterruptedException e) {
			    	throw new RuntimeException(
			    			"Could not wait between char delete.", e);
			    }
			    
			    }    
			    
       		}
       		finally{
       			pause=false;
       			cancelDeleteThread = true;
       			
       		}
    	};
    }; thread.start();
    
    	}
}	    	   