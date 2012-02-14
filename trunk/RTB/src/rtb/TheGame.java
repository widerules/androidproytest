package rtb;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class TheGame {
	
	private Point balldir;
	private Point ballpos;
	private float radio;
	
	private Rect phoneres;
	
	public TheGame(){
		balldir = new Point(0, 0);
		ballpos = new Point(0, 0);
		radio = 20.0f;
		phoneres = new Rect(0, 0, 854, 450);
	}	
	
	public void update(long time){ /* Its not really that long*/
		ballpos.offset(balldir.x/50, balldir.y/50);
		if (!phoneres.contains(ballpos.x, ballpos.y)){
			if (ballpos.x+radio > phoneres.right) {
				ballpos.x = phoneres.right - (ballpos.x - phoneres.right);
				balldir.x *= -1; 
			}
			if (ballpos.x-radio < phoneres.left) {
				ballpos.x *= -1;
				balldir.x *= -1; 
			}
			if (ballpos.y-radio > phoneres.bottom) {
				ballpos.y = phoneres.bottom - (ballpos.y - phoneres.bottom);
				balldir.y *= -1; 
			}
			if (ballpos.y+radio < phoneres.top) {
				ballpos.y *= -1;
				balldir.y *= -1; 
			}
		}
	}
	
	public void draw(Canvas c){
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		c.drawCircle(ballpos.x, ballpos.y, radio, paint);
	}
	
	public void setDir(Point d){
		balldir = d;
	}
}
