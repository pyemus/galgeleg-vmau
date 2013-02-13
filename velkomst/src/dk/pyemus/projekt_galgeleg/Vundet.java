package dk.pyemus.projekt_galgeleg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Vundet extends Activity implements View.OnClickListener{

	Button spilIgen, menu;
	TextView tid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vundet);
        spilIgen = (Button) findViewById(R.id.spilIgen);
        menu = (Button) findViewById(R.id.menu);
        spilIgen.setOnClickListener(this);
        menu.setOnClickListener(this);
        
        //Modtag tid fra HovedGalge
        Intent i = getIntent();
        Long brugttid = i.getExtras().getLong("brugttid_str");
        Long sek =brugttid/1000;
        String conv = sek.toString();
        tid= (TextView) findViewById(R.id.tid_base_vundet);
        tid.setText("Din tid: "+sek+"sekunder");
        
        //Få fat i dags dato
        SimpleDateFormat datoFormat = new SimpleDateFormat("dd/MM-yyyy");
        Date date = new Date();
        String dagsdato = datoFormat.format(date);

        
        
        //Gem fil via en filestream til app'emns datasti i data/data/projekt_galgeleg
        try { // catches IOException below
            final String tilFil = new String("Tid: "+sek+"sek, Dato: "+dagsdato+"\n");
           
            // ##### Write a file to the disk #####
            /* We have to use the openFileOutput()-method
             * the ActivityContext provides, to
             * protect your file from others and
             * This is done for security-reasons.
             * We chose MODE_WORLD_READABLE, because
             *  we have nothing to hide in our file */             
            FileOutputStream fOut = openFileOutput("highScore.txt",
                                                    MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut); 

            // Write the string to the file
            osw.write(tilFil);
            /* ensure that everything is
             * really written out and close */
            osw.flush();
            osw.close();

    }catch (IOException e) {
        e.printStackTrace();}
    }
    
    
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
		} else {
			System.out.println("Ikke håndteret");
		}
		return true;
	}
    


	@Override
	public void onClick(View v) {
		if(v==findViewById(R.id.spilIgen)){
//			Toast.makeText(this, "Ikke klar..vent ;)", Toast.LENGTH_LONG).show();
			startActivity(new Intent(this,HovedGalge.class));
			finish();		
		}
		else if(v==findViewById(R.id.menu)){
			startActivity(new Intent(this,velkomst.class));
			finish();
		}
		
		
	}


}

