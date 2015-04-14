package com.dcu.redmonj9.whichprep.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ClearDataDialog extends DialogFragment{

	private SharedPreferences settings;
	
	public ClearDataDialog(SharedPreferences settings) {
		this.settings = settings;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Are sure you want to clear your high scores?")
			.setPositiveButton("Clear", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					settings.edit().clear().commit();
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				}
			});
		return builder.create();
	}
	
}
