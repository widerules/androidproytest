package GT.namespace;

import GT.namespace.R;
import GT.namespace.GTView;
import GT.namespace.GTView.GTThread;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class GTActivity extends Activity implements View.OnClickListener {
	
	/* Este hilo es el encargado de mantener el loop del juego */
	private GTThread gtThread;	
	
	/* En esta vista es donde se dibujara todo */
	private GTView gtview;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* Quita el titulo de la pantalla */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        /* FULL SCREN!!! */
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/* Carga el latout (descrito en main.xml) */
		setContentView(R.layout.main);
		/* LANDSCAPE ONLY */
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		/* Crea la vista */
		gtview = (GTView)findViewById(R.id.GTView);
		
		/* Obtiene una referencia al hilo que esta dentro de la vista*/
        gtThread = gtview.getThread();
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}