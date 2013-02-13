package dk.pyemus.projekt_galgeleg;
//Ny codekommentar...er den mon med i SVN??

import java.io.IOException;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class velkomst extends Activity implements View.OnClickListener{
	public static String url ="";

	Button start, instil, exit, land, dlord;
//	private Drawable hangman;
//	private long t0;
//	private View grafikView;
	ImageView ani;
	protected boolean dialogSlut;
	GalgeLogik ny_logik=new GalgeLogik();
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.velkomst);
        start = (Button) findViewById(R.id.start);
        instil = (Button) findViewById(R.id.indstil);
        land = (Button) findViewById(R.id.land);
        exit = (Button) findViewById(R.id.exit);
        dlord = (Button) findViewById(R.id.dlord);
        start.setOnClickListener(this);
        instil.setOnClickListener(this);
        land.setOnClickListener(this);
        dlord.setOnClickListener(this);
        exit.setOnClickListener(this);
        ani=(ImageView)findViewById(R.id.animation);
        
        
        //Animation settet bruges til flere former for animationer (scale,rotate,translate)
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation t = new TranslateAnimation(0, 0,0, 200);
        t.setDuration(500);
        t.setFillAfter(true);
        t.setRepeatCount(-1);
        t.setRepeatMode(t.REVERSE);
        animationSet.addAnimation(t);

        
        RotateAnimation r = new RotateAnimation(0f, 15f,0,0); // HERE 
        //r.setStartOffset(1000);
        r.setDuration(300);
        r.setRepeatCount(-1);
        r.setRepeatMode(r.REVERSE);
        animationSet.addAnimation(r);
        ani.startAnimation(animationSet);
        


        
        
        
        
        //ani.setVisibility(0);

        
		// Indlæs res/drawable/forkert6.png (hangman billedet)
//		hangman = getResources().getDrawable(R.drawable.forkert6);
//		
//		grafikView = new View(this) { // anonym nedarving af View
//			@Override
//			protected void onDraw(Canvas c) {
//				tegnGrafik(c);
//			}
//		};
//		setContentView(grafikView);
    }
	
    /** Kaldes én gang for generelt at forberede menuen */
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 101, Menu.NONE, "Indstillinger").setIcon(android.R.drawable.ic_menu_preferences);
		menu.add(Menu.NONE, 102, Menu.NONE, "Hjælp").setIcon(android.R.drawable.ic_menu_help);
		menu.add(Menu.NONE, 103, Menu.NONE, "Om").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		return true;
	}
	
	/** Kaldes hver gang menuen skal vises */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu); // forbered systemets standardmenuer
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 101) {
			Intent i = new Intent(this, Indstillinger.class);
			startActivity(i);
			return true;
		} else if (item.getItemId() == 102) {
	        //set up dialog
	        Dialog dialog = new Dialog(this);
	        dialog.setContentView(R.layout.om_dialog);
	        dialog.setTitle(getString(R.string.titel_hjalp));
	        dialog.setCancelable(true);
	        

	        //set text viewet
	        TextView text = (TextView) dialog.findViewById(R.id.TextView01);
	        text.setText(getString(R.string.indhold_hjalp));

	        //set image view
	        ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
	        img.setImageResource(R.drawable.android_jul);

	        dialog.show();
			//			Toast.makeText(this, "Ikke klar endnuuu..plz wait", Toast.LENGTH_LONG).show();
		} else if (item.getItemId() == 103) {
	        //set up dialog
	        Dialog dialog = new Dialog(this);
	        dialog.setContentView(R.layout.om_dialog);
	        dialog.setTitle(getString(R.string.titel_om));
	        dialog.setCancelable(true);
	        

	        //set text viewet
	        TextView text = (TextView) dialog.findViewById(R.id.TextView01);
	        
	        text.setText(getString(R.string.indhold_om));

	        //set image view
	        ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
	        img.setImageResource(R.drawable.android_jul);

	        dialog.show();
//						Toast.makeText(this, "Ikke klar endnuuu..plz wait", Toast.LENGTH_LONG).show();
		} else {
			System.out.println("Ikke håndteret");
		}
		return true;
	}

    
    
//	void tegnGrafik(Canvas c) {
//		int t = (int) (System.currentTimeMillis() - t0) / 10; // millisekunder sekunder siden start
//		int x = t * 20 / 1000; // går fra 0 til 200
//		int y = t * 40 / 1000; // går fra 0 til 400
//		
//
//		c.rotate(t * 0.05f, x, y);  // rotér om (x,y)
//		hangman.setBounds(x, y, x + 50, y + 50 + (int) (10 * Math.sin(t * Math.PI / 1000)));
//		hangman.draw(c);
//		
//		if (t < 10000) {
//			grafikView.postInvalidateDelayed(10); // tegn igen om 1/100 sekund
//		} 
////		else {
////			finish(); // afslut aktiviteten hvis der er gået 10 sekunder
////		}
//	}
    
    


	@Override
	public void onClick(View v) {
		
		if(v==findViewById(R.id.start)){
			Intent i = new Intent(this, dk.pyemus.projekt_galgeleg.HovedGalge.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(i);
		}
		else if(v==findViewById(R.id.indstil)){
			Intent i = new Intent(this, dk.pyemus.projekt_galgeleg.HighScore.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(i);
			//Toast.makeText(this, "Ikke klar..vent ;)", Toast.LENGTH_LONG).show();
		}
		else if(v==findViewById(R.id.land)){
			Toast.makeText(this, "Ikke klar..vent ;)", Toast.LENGTH_LONG).show();
			
		}
		else if(v==findViewById(R.id.dlord)){
			//Toast.makeText(this, "Ikke klar..vent ;)", Toast.LENGTH_LONG).show();
			
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Download nye ord");
			alert.setMessage("Indtast en webside URL(uden http:// foran)");

			// Set EditText view til get user input 
			final EditText input = new EditText(this);
			input.setText("dr.dk");
			alert.setView(input);
			//final String url ="";	
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
				//public boolean dialogSlut=false;
			public void onClick(DialogInterface dialog, int whichButton) {
			  url = input.getText().toString();
			  url="http://"+url;
			  Toast.makeText(velkomst.this, "Du har valgt ord fra "+url, Toast.LENGTH_LONG).show();
			 //  dialogSlut =true;
			   
			   try {
				   ProgressDialog pd = ProgressDialog.show(velkomst.this, "Vent", "Et øjeblik, henter data...");
				ny_logik.vælgOrdFraDr();
				pd.dismiss();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   startGame();
//			  Intent j = new Intent(this, dk.pyemus.projekt_galgeleg.DownloadOrd.class);
//				j.putExtra("url_str", url);
//				this.startActivity(j);ih
			  

			  
			  
			  
			  }
			
			
			});

			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			  }
			  
			});

				
			alert.show();

			//Input box til website
			//Send med intent til Download Ord
			//Toast.makeText(this, value, Toast.LENGTH_LONG).show();
			
		}

		else if(v==findViewById(R.id.exit)){
//			int pid = android.os.Process.myPid();
//			android.os.Process.killProcess(pid); 
			moveTaskToBack(true);
		}
		
	}
	
	 public void startGame(){
		 
		 Intent i = new Intent(this, dk.pyemus.projekt_galgeleg.HovedGalge.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(i);
	}



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu., menu);
//        return true;
//    }
}

