package com.dcu.redmonj9.whichprep.activities;

import com.dcu.redmonj9.whichprep.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.*;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class FacebookTestShareActivity extends FragmentActivity implements OnClickListener {

	CallbackManager callbackManager;
	ShareDialog shareDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facbook_test_share);
		FacebookSdk.sdkInitialize(getApplicationContext());
		callbackManager = CallbackManager.Factory.create();
		shareDialog = new ShareDialog(this);
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    callbackManager.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void onClick(View v) {
		ShareLinkContent linkContent = new ShareLinkContent.Builder()
				.setContentTitle("Hello Facebook")
				.setContentDescription("The 'Hello Facebook' sample  showcases simple Facebook integration")
				.setContentUrl(Uri.parse("http://developers.facebook.com/android"))
				.build();
		shareDialog.show(linkContent);
	}

}