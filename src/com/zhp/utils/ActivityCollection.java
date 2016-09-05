package com.zhp.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityCollection {
	private static List<Activity> listOfActivity = new ArrayList<Activity>();
	
	public static boolean addActivity(Activity activity){
		return listOfActivity.add(activity);
	}
	
	public static boolean removeActivity(Activity activity){
		
		return listOfActivity.remove(activity);
	}
	
	public static void finishAll(){
		for(Activity activity:listOfActivity){
			activity.finish();
		}
	}
}
