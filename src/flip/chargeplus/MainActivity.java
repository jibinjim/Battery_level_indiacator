package flip.chargeplus;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	ImageView image;
	ImageView stat;
	TextView textBatteryLevel;
	TextView status;
	private String getPlugTypeString(int plugged) {
		String plugType = "Unknown";

		switch (plugged) {
		case BatteryManager.BATTERY_PLUGGED_AC:
			plugType = "AC";
			break;
		case BatteryManager.BATTERY_PLUGGED_USB:
			plugType = "USB";
			break;
		}

		return plugType;
	}
	private String getHealthString(int health) {
		String healthString = "Unknown";

		switch (health) {
		case BatteryManager.BATTERY_HEALTH_DEAD:
			healthString = "Dead";
			break;
		case BatteryManager.BATTERY_HEALTH_GOOD:
			healthString = "Good";
			break;
		case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
			healthString = "Over Voltage";
			break;
		case BatteryManager.BATTERY_HEALTH_OVERHEAT:
			healthString = "Over Heat";
			break;
		case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
			healthString = "Failure";
			break;
		}

		return healthString;
	}
	
	private String getStatusString(int status) {
		String statusString = "Unknown";

		switch (status) {
		case BatteryManager.BATTERY_STATUS_CHARGING:
			statusString = "Charging";
			break;
		case BatteryManager.BATTERY_STATUS_DISCHARGING:
			statusString = "Discharging";
			break;
		case BatteryManager.BATTERY_STATUS_FULL:
			statusString = "Full";
			break;
		case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
			statusString = "Not Charging";
			break;
		}

		return statusString;
	}
	private void setBatteryLevelText(String text) {
		textBatteryLevel.setText(text);
	}
	private void setStatus(String text) {
		status.setText(text);
	}
	
	private void registerBatteryLevelReceiver() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

		registerReceiver(battery_receiver, filter);
		
		
		
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		textBatteryLevel = (TextView) findViewById(R.id.info);
		status = (TextView) findViewById(R.id.status);
		 
        registerBatteryLevelReceiver();
        

	}
	private BroadcastReceiver battery_receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			boolean isPresent = intent.getBooleanExtra("present", false);
			String technology = intent.getStringExtra("technology");
			int plugged = intent.getIntExtra("plugged", -1);
			int scale = intent.getIntExtra("scale", -1);
			int health = intent.getIntExtra("health", 0);
			int status = intent.getIntExtra("status", 0);
			int rawlevel = intent.getIntExtra("level", -1);
			int voltage = intent.getIntExtra("voltage", 0);
			int temperature = intent.getIntExtra("temperature", 0);
			int level = 0;

			Bundle bundle = intent.getExtras();

			Log.i("BatteryLevel", bundle.toString());

			if (isPresent) {
				if (rawlevel >= 0 && scale > 0) {
					level = (rawlevel * 100) / scale;
				}
				String state = getStatusString(status);
				setStatus(state);
				image = (ImageView) findViewById(R.id.testimage);
				stat = (ImageView) findViewById(R.id.stat);
				if(state.equals("Charging")){
				
				image.setBackgroundResource(R.drawable.charging);
				}
				else if(state.equals("Discharging")) { image.setBackgroundResource(R.drawable.not_charging);}
				//My Addition
				
				if(level<=10){
					stat.setBackgroundResource(R.drawable.a);
				}
				 if(level>10 && level<=20) {
					stat.setBackgroundResource(R.drawable.b);
				
				}
				 if(level>20 && level<=25) {
					stat.setBackgroundResource(R.drawable.c);
				}
				 if(level>25 && level<=30) {
					stat.setBackgroundResource(R.drawable.d);
				}
				 if(level>30 && level<=40) {
					stat.setBackgroundResource(R.drawable.e);
				}
				 if(level>40 && level<=50) {
					stat.setBackgroundResource(R.drawable.f);
				}
				 if(level>50 && level<=60) {
					stat.setBackgroundResource(R.drawable.g);
				}
				 if(level>60 && level<=70) {
					stat.setBackgroundResource(R.drawable.h);
				}
				 if(level>70 && level<=75) {
					stat.setBackgroundResource(R.drawable.i);
				}
				 if(level>75 && level<=80) {
					stat.setBackgroundResource(R.drawable.j);
				}
				 if(level>80 && level<=90) {
					stat.setBackgroundResource(R.drawable.k);
				}
				 if(level>90 && level<=95) {
					stat.setBackgroundResource(R.drawable.l);
				}
				 if(level>95 && level<=100) {
						stat.setBackgroundResource(R.drawable.m);
					
					}
				String info = level +"%" ;
				setBatteryLevelText(info);}
				//My Addition End
				/*String info = "Battery Level: " + level + "%\n";
				info += ("Technology: " + technology + "\n");
				info += ("Plugged: " + getPlugTypeString(plugged) + "\n");
				info += ("Health: " + getHealthString(health) + "\n");
				info += ("Status: " + getStatusString(status) + "\n");
				info += ("Voltage: " + voltage + "\n");
				info += ("Temperature: " + temperature + "\n");

				setBatteryLevelText(info + "\n\n" + bundle.toString());
			*/
			else {
				setBatteryLevelText("Battery not present!!!");
						}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
