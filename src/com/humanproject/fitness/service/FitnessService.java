package com.humanproject.fitness.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;

public class FitnessService extends IntentService {
	private Handler mHandler;
	public FitnessService(String name) {
		super(name);
		mHandler = new Handler();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String dataString = intent.getDataString();		
	}

}
