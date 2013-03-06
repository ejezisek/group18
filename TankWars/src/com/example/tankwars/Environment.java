package com.example.tankwars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
/**
 * Environment which draws all the tanks, the bullets, etc..
 * @author Edward Jezisek
 * @version 1.0
 * Unit Testing: No
 * March 6, 2013
 * Future plans:  Make the environment 3 dimensional and improve the overall graphics.
 *
 */
public class Environment{
Bitmap bitmap;
Paint paint;
final int tankWidth=50;
final int tankHeight=25;
final Bitmap.Config conf = Bitmap.Config.ARGB_8888;
private Bitmap curr;
private Bitmap tankRight;
private Bitmap tankLeft;
private Bitmap turretLeft;
private Bitmap turretRight;
private Bitmap bullet;
private Canvas canvas;
private Context context;


public Environment(Context context, int screenWidth, int screenHeight) {
		this.context=context;
		paint=new Paint();
		paint.setAntiAlias(true);
        curr = Bitmap.createBitmap( screenWidth, screenHeight, conf );
        canvas = new Canvas( curr );
        createTankBitmap();
        refreshEnvironment();
		// TODO Auto-generated constructor stub
	}


public void createTankBitmap()
{
	//This creates the right tank bitmap.
	tankRight=BitmapFactory.decodeResource(context.getResources(), R.drawable.tank);
	tankRight=Bitmaps.resizeBitmap(tankRight, tankWidth, tankHeight);
	
	//This creates  the right turret.
	turretRight=BitmapFactory.decodeResource(context.getResources(), R.drawable.turret);
	turretRight=Bitmaps.resizeBitmap(turretRight, tankWidth/2, tankHeight/5);
	Matrix m=new Matrix();
	m.preScale(-1, 1);
	
	//This rotates the right tank bitmap and makes it the left tank.
	tankLeft=Bitmap.createBitmap(tankRight, 0, 0, tankRight.getWidth(), tankRight.getHeight(),m, false);
	tankLeft.setDensity(DisplayMetrics.DENSITY_DEFAULT);
	
	//This rotates the right turret bitmap and makes the left turret.
	turretLeft=Bitmap.createBitmap(turretRight, 0, 0, turretRight.getWidth(), turretRight.getHeight(),m, false);
	turretLeft.setDensity(DisplayMetrics.DENSITY_DEFAULT);
	
	bullet=BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
	bullet=Bitmaps.resizeBitmap(bullet, bullet.getHeight()/7, bullet.getWidth()/7);
}
public void refreshEnvironment()
{
	paint.setColor(Color.CYAN);
    canvas.drawRect(0, 150, 500, 0, paint);
	paint.setColor(Color.GREEN);
    canvas.drawRect(0, 300, 500, 150, paint);

}

public void drawTank(Tank aTank)
{	
	if(aTank.getRotate())
		canvas.drawBitmap(tankLeft, aTank.getX(), 150-tankHeight, paint);
	else
		canvas.drawBitmap(tankRight, aTank.getX(), 150-tankHeight, paint);
	drawTurret(aTank);
	}
public void drawTurret(Tank aTank)
{
	Matrix matrix=new Matrix();

	if(aTank.getRotate()){
		float xPos=aTank.getX()+tankLeft.getWidth()-20;
		float yPos=(float) (150-.75*tankHeight);
		matrix.setRotate(-aTank.getDegrees(), 0, 0);
		matrix.postTranslate(xPos, yPos);
		canvas.drawBitmap(turretLeft, matrix, paint);
	}
	else
	{
		float xPos=aTank.getX()-5;
		float yPos=(float) (150-.75*tankHeight);
		matrix.setRotate(aTank.getDegrees(), turretRight.getWidth(), 0);
		matrix.postTranslate(xPos, yPos);
		canvas.drawBitmap(turretRight, matrix, paint);
	}
}

public Bitmap getBitmap()
{
	return curr;
}

public void drawBullet(float xPos, float yPos)
{
	canvas.drawBitmap(bullet, xPos, yPos, paint);
}

}
