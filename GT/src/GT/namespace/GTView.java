package GT.namespace;

import GT.namespace.R;
import GT.namespace.GTView.GTThread;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GTView extends SurfaceView implements SurfaceHolder.Callback {
	
	/* Declarar el juego */
	
	public GTView(Context context, AttributeSet attrs) {
		
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		// register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        
        // create thread only; it's started in surfaceCreated()
        // except if used in the layout editor.
        if (isInEditMode() == false) {
            thread = new GTThread(holder, context, new Handler(){});
        }
        
        setFocusable(true); // make sure we get key events
	}

	public class GTThread extends Thread {
		
		private Resources res;		
		private SurfaceHolder surface_holder;		
		private Handler handler;		
		private Context context;
		private boolean vive;
		
		public GTThread(SurfaceHolder sh, Context c, Handler h){
			
			surface_holder = sh;
			handler = h;
			context = c;
			res = context.getResources();
			
			/* Aqui se debe inicializar todo */
			
			vive = true;
		}

		// sigue siendo dentro de la clase RLHThread
		private void doDraw(Canvas canvas) {
			/* DIBUJA!!! */
        }		
			
		public void run(){
			
			long time_nuevo = System.currentTimeMillis();
			long time_viejo = 0l;
			long tiempo = 0l;
									
			while(vive){
				
				Canvas c = null;
				
				/* Actualiza el tiempo transcurrido */
				time_viejo = time_nuevo;
				time_nuevo = System.currentTimeMillis();
				tiempo = time_nuevo - time_viejo; // EN MILISEGUNDOS!!!!				
				if (tiempo == 0) tiempo = 1;
				
				/* AQUI SE DEBEN ACTUALIZAR TODAS LA VARIABLES DEL JUEGO DADO 
				 * QUE HAN PASADO tiempo/1000.0 segundos */
				
				/* ************************************************************
				 * CUANDO SE VAYA A MODIFICAR VARIABLES QUE AFECTEN LA FORMA 
				 * COMO SE DIBUJA SE DEBE HACER ASI:
				 *		synchronized (surface_holder) {
                 *			variable = nuevo valor;
            	 *		}
            	 * ESTO ES PARA QUE NO SE CAMBIE EL VALOR EN PLENO DIBUJO.
				 * ***********************************************************/
				
				try {
					/* VOY A DIBUJAR NO ME JODAN!!! */
                    c = surface_holder.lockCanvas(null);
                    synchronized (surface_holder) {
                    	c.drawColor(Color.BLACK);
                    	doDraw(c);
                    }
                } finally {
                    if (c != null) {
                        surface_holder.unlockCanvasAndPost(c);
                    }
                }
			}
		}
		
		public void setRunning(boolean b) {
            vive = b;
        }
	}
	
	private GTThread thread;
	
	public GTThread getThread() {
        return thread;
    }
	
	Point pI;
	Point pF;
	Point pD;
	
	public boolean onTouchEvent(MotionEvent event){
		
		float x = event.getX();
		float y = event.getY();
		
		if (event.getAction() == MotionEvent.ACTION_DOWN){			
			// DO SOMETHING
		}
		
		return true;
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		thread.setRunning(true);
        thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
		    try {
		        thread.join();
		        retry = false;
		
		    } catch (InterruptedException e) {
		    }
		}
	}
}
