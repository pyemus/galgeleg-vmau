package dk.pyemus.projekt_galgeleg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HighScore extends Activity {

	// Button spilIgen, menu;
	TextView highscoren;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
		highscoren = (TextView) findViewById(R.id.score);

		try {
			// ##### Read the file back in #####

			/*
			 * We have to use the openFileInput()-method the ActivityContext
			 * provides. Again for security reasons with openFileInput(...)
			 */
			FileInputStream fIn = openFileInput("highScore.txt");
			InputStreamReader isr = new InputStreamReader(fIn);
			/*
			 * Prepare a char-Array that will hold the chars we read back in.
			 */
			char[] inputBuffer = new char[1000];// tilFil.length()
			// Fill the Buffer with data from the file
			isr.read(inputBuffer);
			// Transform the chars to a String
			String readString = new String(inputBuffer);
			highscoren.setText(readString);
			// Check if we read back the same chars that we had written out
			// boolean isTheSame = tilFil.equals(readString);

			// WOHOO lets Celebrate =)
			// Log.i("File Reading stuff", "success = " + isTheSame);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
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

	
	public void tilbage(View view) {
		finish();
	}

	public void reset_score(View view) {

		try { // catches IOException below
			final String tilFil = new String("\n");

			FileOutputStream fOut = openFileOutput("highScore.txt", MODE_WORLD_READABLE);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);

			osw.write(tilFil);
			osw.flush();
			osw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		reload();
		Toast.makeText(this, "Scoren er resat", Toast.LENGTH_SHORT).show();
	}

	public void reload() {

		Intent intent = getIntent();
		overridePendingTransition(0, 0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();

		overridePendingTransition(0, 0);
		startActivity(intent);
	}

}
