package dk.pyemus.projekt_galgeleg;

import java.util.ArrayList;


import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


@SuppressWarnings("deprecation")
public class HovedGalge extends Activity implements View.OnClickListener, SensorEventListener{
	
	ImageView galge;
	//Button start;
	TextView test, forkerte;
	Button bogst[] = new Button[29];
	public String zeword, ordet;
//	public static String word;
	public ArrayList<String> forkert_bogst;
	GalgeLogik ny_logik=new GalgeLogik();
	boolean knap_trykket =false, sidsteGaetBesked=true;
	int forkerteBogstaver=0, korrekteBogstaver=0;
	Chronometer mChronometer;
	velkomst ny_vel =new velkomst();
	
	String[] senesteMålinger = new String[15];
	SensorManager sensorManager;


	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        galge = (ImageView) findViewById(R.id.galgen);
        //start = (Button) findViewById(R.id.startknap);
        //start.setOnClickListener(this);
        test = (TextView) findViewById(R.id.test);
        forkerte = (TextView) findViewById(R.id.forkerte);
        //Button findz
        //bogst[i] = (Button) findViewById(R.id.Button[i]);
        bogst[0] = (Button) findViewById(R.id.Button1);
        bogst[1] = (Button) findViewById(R.id.Button2);
        bogst[2] = (Button) findViewById(R.id.Button3);
        bogst[3] = (Button) findViewById(R.id.Button4);
        bogst[4] = (Button) findViewById(R.id.Button5);
        bogst[5] = (Button) findViewById(R.id.Button6);
        bogst[6] = (Button) findViewById(R.id.Button7);
        bogst[7] = (Button) findViewById(R.id.Button8);
        bogst[8] = (Button) findViewById(R.id.Button9);
        bogst[9] = (Button) findViewById(R.id.Button10);
        bogst[10] = (Button) findViewById(R.id.Button11);
        bogst[11] = (Button) findViewById(R.id.Button12);
        bogst[12] = (Button) findViewById(R.id.Button13);
        bogst[13] = (Button) findViewById(R.id.Button14);
        bogst[14] = (Button) findViewById(R.id.Button15);
        bogst[15] = (Button) findViewById(R.id.Button16);
        bogst[16] = (Button) findViewById(R.id.Button17);
        bogst[17] = (Button) findViewById(R.id.Button18);
        bogst[18] = (Button) findViewById(R.id.Button19);
        bogst[19] = (Button) findViewById(R.id.Button20);
        bogst[20] = (Button) findViewById(R.id.Button21);
        bogst[21] = (Button) findViewById(R.id.Button22);
        bogst[22] = (Button) findViewById(R.id.Button23);
        bogst[23] = (Button) findViewById(R.id.Button24);
        bogst[24] = (Button) findViewById(R.id.Button25);
        bogst[25] = (Button) findViewById(R.id.Button26);
        bogst[26] = (Button) findViewById(R.id.Button27);
        bogst[27] = (Button) findViewById(R.id.Button28);
        bogst[28] = (Button) findViewById(R.id.Button29);
        
        
		galge.setImageResource(R.drawable.galge);
		galge.setVisibility(View.VISIBLE);
		//Finder uret
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		
//        for(int i=0;i<28;i++){
//        	
//        }
        //SÆtter onClickListeneren på alle knapper
        for(int j=0; j<29; j++){
            bogst[j].setOnClickListener(this);
            bogst[j].getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
            bogst[j].setEnabled(true);
        }
        
//        ordetderskalgaettes();
//        ordetderskalgaettes();
		ny_logik.nulstil();
        //Sætter det usynelige ord
		zeword = ny_logik.synligtOrd;
		test.setText(zeword);
		ordet=ny_logik.ordet;
//		Toast.makeText(this, ordet, Toast.LENGTH_LONG).show();
		//test.setText("Se Mig!");
        
		//Start uret
		mChronometer.start();
		
		//SEnsor
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		//Hold skærmen i live under spillet
		getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );

		
		
    }
//    public String hentordetderskalgaettes(){
//    	sendordet=ordet;
//    	return sendordet;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 101, Menu.NONE, "Indstillinger").setIcon(android.R.drawable.ic_menu_preferences);
		menu.add(Menu.NONE, 102, Menu.NONE, "Hjælp").setIcon(android.R.drawable.ic_menu_help);
		menu.add(Menu.NONE, 103, Menu.NONE, "Om").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
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
//			Toast.makeText(this, "Ikk0e klar endnuuu..plz wait", Toast.LENGTH_LONG).show();
		} else {
			System.out.println("Ikke håndteret");
		}
		return true;
	}

    
    
    
    void update(){
		ny_logik.opdaterSynligtOrd();
		if(ny_logik.spilletErVundet){
			//Stop uret
			mChronometer.stop();
			Intent i = new Intent(this, dk.pyemus.projekt_galgeleg.Vundet.class);
			Long brugttid = SystemClock.elapsedRealtime() - mChronometer.getBase();
			//Send tid med intent til Vundet.java klassen
			i.putExtra("brugttid_str", brugttid);
			this.startActivity(i);
		}
//		else if (ny_logik.spilletErTabt){
//			Intent i = new Intent(this, dk.pyemus.projekt_galgeleg.Tabt.class);
//			this.startActivity(i);
//			//Stop uret
//			mChronometer.stop();
//		}
		else if (forkerteBogstaver==1){
			galge.setImageResource(R.drawable.forkert1);
			galge.setVisibility(View.VISIBLE);
		}
		else if (forkerteBogstaver==2){
			galge.setImageResource(R.drawable.forkert2);
			galge.setVisibility(View.VISIBLE);
		}
		else if (forkerteBogstaver==3){
			galge.setImageResource(R.drawable.forkert3);
			galge.setVisibility(View.VISIBLE);
		}
		else if (forkerteBogstaver==4){
			galge.setImageResource(R.drawable.forkert4);
			galge.setVisibility(View.VISIBLE);
		}
		else if (forkerteBogstaver==5){
			galge.setImageResource(R.drawable.forkert5);
			galge.setVisibility(View.VISIBLE);
		}
		else if (forkerteBogstaver==6){
			galge.setImageResource(R.drawable.forkert6);
			galge.setVisibility(View.VISIBLE);
			if(sidsteGaetBesked){
			Toast.makeText(this, "Du må kun gætte forkert 1 gang mere!", Toast.LENGTH_LONG).show();
			sidsteGaetBesked=false;
			}
		}
		else if (forkerteBogstaver==7){
			//Stop uret
			mChronometer.stop();
			
			Intent j = new Intent(this, dk.pyemus.projekt_galgeleg.Tabt.class);
			Long brugttid = SystemClock.elapsedRealtime() - mChronometer.getBase();
			j.putExtra("brugttid_str", brugttid);
			j.putExtra("gaetOrdet", ordet);
//			String conv = brugttid.toString();
//			Toast.makeText(this, conv, Toast.LENGTH_LONG).show();
			this.startActivity(j);
			
		}
    }
    
    



	public void onClick(View view) {
		
		//test.setText("trykket");
//		galge.setImageResource(R.drawable.forkert6);
//		galge.setVisibility(View.VISIBLE);
		//start.setVisibility(view.INVISIBLE);
		//*********Knapper disp*********
//		final GalgeLogik c1;
//		c1 = dk.pyemus.projekt_galgeleg.;
//		zeword = c1.ordet;
		


		 
		
		//test.setText("Se mig!");
//********************************************************************
//***************CHECKER HVILKEN KNAP DER ER TRYKKET PÅ***************
//********************************************************************
		if(view==findViewById(R.id.Button1)){
//			if(bogst[0].isClickable()==false){	
				ny_logik.gaetBogstav("q");
				ny_logik.opdaterSynligtOrd();
				if(!ny_logik.ordet.contains("q")){
					forkerteBogstaver++;
				}
				zeword = ny_logik.synligtOrd;
				test.setText(zeword);
				//Sætter knappen til rød=allerede brugt
				bogst[0].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
				bogst[0].setEnabled(false);
				update();
//				bogst[0].setClickable(true);
//			}
//			else{
//				Toast.makeText(this, "Prøv et andet bogstav", Toast.LENGTH_SHORT).show();
//			}
		}
		else if (view == findViewById(R.id.Button2)){
			ny_logik.gaetBogstav("w");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("w")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			test.setText(zeword);
			bogst[1].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[1].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button3)){
			ny_logik.gaetBogstav("e");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("e")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			test.setText(zeword);
			bogst[2].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[2].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button4)){
			ny_logik.gaetBogstav("r");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("r")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[3].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[3].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button5)){
			ny_logik.gaetBogstav("t");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("t")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[4].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[4].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button6)){
			ny_logik.gaetBogstav("y");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("y")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[5].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[5].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button7)){
			ny_logik.gaetBogstav("u");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("u")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[6].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[6].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button8)){
			ny_logik.gaetBogstav("i");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("i")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[7].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[7].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button9)){
			ny_logik.gaetBogstav("o");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("o")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[8].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[8].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button10)){
			ny_logik.gaetBogstav("p");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("p")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[9].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[9].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button11)){
			ny_logik.gaetBogstav("å");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("å")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[10].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[10].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button12)){
			ny_logik.gaetBogstav("a");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("a")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[11].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[11].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button13)){
			ny_logik.gaetBogstav("s");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("s")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[12].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[12].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button14)){
			ny_logik.gaetBogstav("d");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("d")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[13].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[13].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button15)){
			ny_logik.gaetBogstav("f");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("f")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[14].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[14].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button16)){
			ny_logik.gaetBogstav("g");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("g")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[15].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[15].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button17)){
			ny_logik.gaetBogstav("h");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("h")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[16].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[16].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button18)){
			ny_logik.gaetBogstav("j");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("j")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[17].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[17].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button19)){
			ny_logik.gaetBogstav("k");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("k")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[18].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[18].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button20)){
			ny_logik.gaetBogstav("l");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("l")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[19].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[19].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button21)){
			ny_logik.gaetBogstav("æ");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("æ")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[20].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[20].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button22)){
			ny_logik.gaetBogstav("ø");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("ø")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[21].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[21].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button23)){
			ny_logik.gaetBogstav("z");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("z")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[22].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[22].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button24)){
			ny_logik.gaetBogstav("x");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("x")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[23].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[23].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button25)){
			ny_logik.gaetBogstav("c");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("c")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[24].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[24].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button26)){
			ny_logik.gaetBogstav("v");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("v")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[25].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[25].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button27)){
			ny_logik.gaetBogstav("b");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("b")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[26].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[26].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button28)){
			ny_logik.gaetBogstav("n");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("n")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[27].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[27].setEnabled(false);
			update();
		}
		else if (view == findViewById(R.id.Button29)){
			ny_logik.gaetBogstav("m");
			ny_logik.opdaterSynligtOrd();
			if(!ny_logik.ordet.contains("m")){
				forkerteBogstaver++;
			}
			zeword = ny_logik.synligtOrd;
			forkert_bogst = ny_logik.brugteBogstaver;
			test.setText(zeword);
			bogst[28].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			bogst[28].setEnabled(false);
			update();
		}
//********************************************************************
//*********************SLUT PÅ KNAPCHECK******************************		
//********************************************************************			

	}
	public void reset_knap(View view){
		restart();
	}
	
	public void restart(){
        for(int j=0; j<29; j++){
            bogst[j].getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
            bogst[j].setEnabled(true);
        }
		ny_logik.nulstil();
        //Sætter det usynelige ord
		zeword = ny_logik.synligtOrd;
		test.setText(zeword);
		galge.setImageResource(R.drawable.galge);
		forkerteBogstaver=0;
		mChronometer.setBase(SystemClock.elapsedRealtime());
	}

	//********************************************************************
	//*********************SLUT RESET METODE******************************
	//*********************START SENSOR METODER***************************
	//********************************************************************	
	
	@Override
	protected void onResume() {
		super.onResume();
		int hyppighed = 250000; // 4 gange i sekundet

		for (Sensor sensor : sensorManager.getSensorList(Sensor.TYPE_ALL)) {
			sensorManager.registerListener(this, sensor, hyppighed);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		int sensortype = e.sensor.getType();

		if (sensortype == Sensor.TYPE_ACCELEROMETER) {
			// Tjek om det er 3 * normal tyngdeaccelerationen - se
			// http://da.wikipedia.org/wiki/Tyngdeacceleration
			double sum = Math.abs(e.values[0]) + Math.abs(e.values[1])
					+ Math.abs(e.values[2]);
			if (sum > 3 * SensorManager.GRAVITY_EARTH) {
				Toast.makeText(this, "Spillet er genstartet", Toast.LENGTH_SHORT).show();
				restart();
			}
		}

	}
	//********************************************************************
	//*********************SLUT PÅ SENSORERNE*****************************		
	//********************************************************************	





}
