package dk.pyemus.projekt_galgeleg;

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

public class Tabt extends Activity implements View.OnClickListener{

	Button spilIgen2, menu2;
	TextView tid, visOrdet;
//	GalgeLogik ny_logik=new GalgeLogik();
	String ordet;
//	HovedGalge hovedgalge = new HovedGalge();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabt);
        spilIgen2 = (Button) findViewById(R.id.spilIgen2);
        menu2 = (Button) findViewById(R.id.menu2);
        spilIgen2.setOnClickListener(this);
        menu2.setOnClickListener(this);
       
        //Modtag tid fra HovedGalge
        Intent i = getIntent();
        Long brugttid = i.getExtras().getLong("brugttid_str");
        Long sek =brugttid/1000;
        String conv = sek.toString();
        tid= (TextView) findViewById(R.id.tid_base);
        tid.setText("Din tid: "+sek+"sekunder");
//        Toast.makeText(this, conv, Toast.LENGTH_LONG).show();
        
        //Modtag ordet fra hovedgalge
        String ordet = i.getExtras().getString("gaetOrdet");
        visOrdet=(TextView)findViewById(R.id.visordet);
        visOrdet.setText("Ordet var: "+ordet);
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
		if(v==findViewById(R.id.spilIgen2)){
//			Toast.makeText(this, "Ikke klar..vent ;)", Toast.LENGTH_LONG).show();
			startActivity(new Intent(this,HovedGalge.class));
			finish();
		}
		else if(v==findViewById(R.id.menu2)){
			startActivity(new Intent(this,velkomst.class));
			finish();
		}
		
		
	}


}

