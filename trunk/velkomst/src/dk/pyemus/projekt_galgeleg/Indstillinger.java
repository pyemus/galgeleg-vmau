package dk.pyemus.projekt_galgeleg;

//import dk.nordfalk.android.elementer.R;
import dk.pyemus.projekt_galgeleg.*;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class Indstillinger extends PreferenceActivity {
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		CharSequence[] entries = { "One", "Two", "Three" };
//		CharSequence[] entryValues = { "1", "2", "3" };
//		ListPreference lp = (ListPreference)findPreference(R.id.prefz);
//		lp.setEntries(entries);
//		lp.setEntryValues(entryValues);

		addPreferencesFromResource(R.layout.indstillinger);
	}

}
