package com.example.mohit.chipz;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.TaskStackBuilder;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Vibrator;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    FloatingActionsMenu floatingActionsMenu ;
    FloatingActionButton one , sendsmsButton;
    String message = "";
    TextView fluoride ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();

        updateLocation();

        try {
            scrapper();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fluoride = (TextView)findViewById(R.id.fluoride);
        floatingActionsMenu =(FloatingActionsMenu)findViewById(R.id.floatingaction);
        one = (FloatingActionButton)findViewById(R.id.action1);
        sendsmsButton =(FloatingActionButton)findViewById(R.id.action2);
        sendsmsButton.setIcon(R.drawable.ic_message_black_24dp);

        fluoride.setOnClickListener(this);
        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {

            }

            @Override
            public void onMenuCollapsed() {

            }
        });

        one.setIcon(R.drawable.add_location_black_54x54);
        one.setOnClickListener(this);
        sendsmsButton.setOnClickListener(this);

    }

    public void requestPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 121);
    }

    public String updateLocation() {
        try {
            PlaceDetectionClient placeDetectionClient = Places.getPlaceDetectionClient(MainActivity.this, null);
            Task<PlaceLikelihoodBufferResponse> task = placeDetectionClient.getCurrentPlace(null);
            task.addOnSuccessListener(new OnSuccessListener<PlaceLikelihoodBufferResponse>() {
                @Override
                public void onSuccess(PlaceLikelihoodBufferResponse placeLikelihoods) {
                    for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
                        Place place = placeLikelihood.getPlace();
                        String[] address = place.getAddress().toString().split(",");
                        break;
                    }
                }
            });
        } catch (SecurityException e) {
        }
        return "";
    }

    public void scrapper ()  {

        /*ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(12,componentName)
                .setRequ

                000iiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPeriodic(6000*60*60*24)
                .build();

        JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if(resultCode == JobScheduler.RESULT_SUCCESS)
        {
            Log.i(String.valueOf(getApplicationContext()),"Job Scheduled");
        }
        else
        {
            Log.i(String.valueOf(getApplicationContext()),"Job Not Scheduled");
        }*/

        String in = "{\n" +
                "   \"BALOD\":[{\n" +
                "      \"BALOD\":[{\n" +
                "         \"DEWARBHAT\":[{\n" +
                "            \"DEWAR BHAT\":[{\n" +
                "               \"GASTITOLA\":[{\n" +
                "                  \"Arsenic\":{\n" +
                "\t\t\t\t\t \"permissable\":\"0\",\n" +
                "                     \"actual\":\"1\"\n" +
                "                  },\n" +
                "                  \"Fluoride\":{\n" +
                "\t\t\t\t\t \"permissable\":\"1\",\n" +
                "                     \"actual\":\"3\"\n" +
                "                  },\n" +
                "                  \"Iron\":{\n" +
                "                     \"permissable\":\"0\",\n" +
                "                     \"actual\":\"0\"\n" +
                "                  },\n" +
                "                  \"Salinity\":{\n" +
                "\t\t\t\t     \"permissable\":\"0\",\n" +
                "                     \"actual\":\"0\"\n" +
                "                  },\n" +
                "                  \"Nitrate\":{\n" +
                "\n" +
                "\t\t\t\t     \"permissable\":\"0\",\n" +
                "                     \"actual\":\"0\"\n" +
                "                  }\n" +
                "               }]\n" +
                "            }]\n" +
                "         }]\n" +
                "      }]\n" +
                "   }]\n" +
                "}";
        /*
        String in = "\n{" +
                "\t\"district\" : {\n" +
                "\t\t\"blocks\" : {\n" +
                "\t\t\t\"panchayat\" : {\n" +
                "\t\t\t\t\"villages\" : {\n" +
                "\t\t\t\t\t\"habitation\" : {\n" +
                "\t\t\t\t\t\t\"arsenic\" : {\n" +
                "\t\t\t\t\t\t\t\"permissable\" : 1,\n" +
                "\t\t\t\t\t\t\t\"actual\" : 0\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"fluoride\" : {\n" +
                "\t\t\t\t\t\t\t\"permissable\" : 2,\n" +
                "\t\t\t\t\t\t\t\"actual\" : 0\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"iron\" : {\"permissable\" : 3,\n" +
                "\t\t\t\t\t\t\t\"actual\" : 0\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"salinity\" : {\n" +
                "\t\t\t\t\t\t\t\"permissable\" : 3,\n" +
                "\t\t\t\t\t\t\t\"actual\" : 0\t\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"nitrate\" : {\n" +
                "\t\t\t\t\t\t\t\"permissable\" : 5,\n" +
                "\t\t\t\t\t\t\t\"actual\" : 0\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\n" +
                "\t}]\n" +
                "}";
                */

        if(in  != null)
        {
            try {



                message ="";
                JSONObject district = new JSONObject(in);
                //Log.i(String.valueOf(this),district.toString());
                JSONArray districtArray = district.getJSONArray("BALOD");
                message += "In the district :  BALOD "  +"\n";
                for (int i = 0; i < districtArray.length(); i++)
                {
                    JSONObject blocks = districtArray.getJSONObject(i);
                    Log.i(String.valueOf(this),blocks.toString());
                    JSONArray blockArray = blocks.getJSONArray("BALOD");
                    message += "In the block : BALOD"  +"\n";
                    for(int j=0;j<blockArray.length() ; j++)
                    {
                        JSONObject panchayat = blockArray.getJSONObject(j);
                        message += "In the panchayat : DEWARBHAT" +"\n";
                        Log.i(String.valueOf(this),panchayat.toString());
                        JSONArray panchayatArray = panchayat.getJSONArray("DEWARBHAT");
                        for (int k=0;k<panchayatArray.length();k++)
                        {
                            JSONObject villages = panchayatArray.getJSONObject(k);
                            message += "In the villages  : DEWAR BHAT" +"\n";
                            JSONArray villageArray = villages.getJSONArray("DEWAR BHAT");
                            for(int l=0;l<villageArray.length() ; l++)
                            {
                                JSONObject habitation = villageArray.getJSONObject(l);
                                message += "In the habitation : GASTITOLA " +"\n";
                                JSONArray habitationArray = habitation.getJSONArray("GASTITOLA");
                                for(int m=0 ; m < habitationArray.length() ; m++)
                                {
                                    JSONObject minerals = habitationArray.getJSONObject(m);
                                    System.out.println(minerals);
                                    //message += "In the minerals : " + minerals +"\n";
                                    Log.i(String.valueOf(getApplicationContext()),minerals.toString());
                                    JSONObject arsenic = minerals.getJSONObject("Arsenic");
                                    int permissable_arsenic = Integer.parseInt(arsenic.getString("permissable"));
                                    int actual_arsenic = Integer.parseInt(arsenic.getString("actual"));

                                    if(actual_arsenic >= permissable_arsenic)
                                    {
                                        message += "Arsenic actual quantity is more than or equal to permissable with " + (actual_arsenic-permissable_arsenic) +"\n";
                                        NotificationArea(message);
                                    }


                                    JSONObject fluroide = minerals.getJSONObject("Fluoride");
                                    int permissable_fluoride= Integer.parseInt(arsenic.getString("permissable"));
                                    int actual_fluoride = Integer.parseInt(arsenic.getString("actual"));

                                    if(actual_fluoride >= permissable_fluoride)
                                    {
                                        message += "Fluoride actual quantity is more than or equal to permissable with " + (actual_fluoride-permissable_fluoride)+"\n";
                                    }
                                    JSONObject iron= minerals.getJSONObject("Iron");
                                    int permissable_iron = Integer.parseInt(arsenic.getString("permissable"));
                                    int actual_iron = Integer.parseInt(arsenic.getString("actual"));
                                    if(actual_iron >= permissable_iron)
                                    {
                                        message += "Iron actual quantity is more than or equal to permissable with " + (actual_iron-permissable_iron)+"\n";
                                    }

                                    JSONObject salinity = minerals.getJSONObject("Salinity");
                                    int permissable_salinity = Integer.parseInt(arsenic.getString("permissable"));
                                    int actual_salinity = Integer.parseInt(arsenic.getString("actual"));

                                    if(actual_salinity >= permissable_salinity)
                                    {
                                        message += "Salinity actual quantity is more than or equal to permissable with " + (actual_salinity-permissable_salinity)+"\n";
                                    }
                                    JSONObject nitrate = minerals.getJSONObject("Nitrate");
                                    int permissable_nitrate = Integer.parseInt(arsenic.getString("permissable"));
                                    int actual_nitrate = Integer.parseInt(arsenic.getString("actual"));

                                    if(actual_nitrate >= permissable_nitrate)
                                    {
                                        message += "Nitrate actual quantity is more than or equal to permissable with " + (actual_nitrate-permissable_nitrate) +"\n";
                                    }
                                    System.out.println(message);



                                }
                            }
                        }
                    }
                }
            }
            catch (Exception e )
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View v) {

        if(v==one)
        {

            startActivity(new Intent(this , MapsActivity.class));
            fluoride.setTextColor(Color.parseColor("#f44336"));
            fluoride.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            (findViewById(R.id.dot)).setVisibility(View.VISIBLE);
        }
        else if(v==sendsmsButton)
        {
            try {
                smsImplicit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(v==fluoride)
        {
            startActivity(new Intent(this , Information.class));
        }
    }

    public void smsImplicit()
    {
        //String message = "26 contaminated habitats in the district of Raipur with specs as follows:\n" +
               // "~ 63 hand pumps with excess Iron";
        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address","8098476044");
        smsIntent.putExtra("sms_body",message);
        startActivity(smsIntent);
        Toast.makeText(getApplicationContext(),"Use Send Button to send the text ",Toast.LENGTH_SHORT).show();
        NotificationArea(message);

    }
    public void NotificationArea (String message){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_message_black_24dp);
        mBuilder.setContentTitle("Alert ! Water is UNSAFE ");
        mBuilder.setContentText("Send messages to doctors and engineers");

        Intent resultIntent = new Intent(this , MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(123 , mBuilder.build());
        String KEY_TEXT_REPLY = "key_text_reply";

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("REPLY")
                .build();

        PendingIntent pendingReplyIntent = PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingReplyIntent);
    }

    public class SMS extends Thread {

        @Override
        public void run() {
            super.run();
            try {
                SendSMS();
            } catch (Exception e) {
            }
        }

        private void SendSMS() throws Exception {
            // This URL is used for sending messages
            String myURI = "https://api.bulksms.com/v1/messages";

            // change these values to match your own account
            String myUsername = "mohit";
            String myPassword = "U_nW6rdliJmD0Y#Yxz*SGNTfb2n*9";

            // the details of the message we want to send
            String myData = "{to: \"+919987946739\", body: \"i\"}";

            // build the request based on the supplied settings
            URL url = new URL(myURI);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);

            // supply the credentials
            String authStr = myUsername + ":" + myPassword;
            String authEncoded = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
            }
            request.setRequestProperty("Authorization", "Basic " + authEncoded);

            // we want to use HTTP POST
            request.setRequestMethod("POST");
            request.setRequestProperty( "Content-Type", "application/json");

            // write the data to the request
            OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
            out.write(myData);
            out.close();

            // try ... catch to handle errors nicely
            try {
                // make the call to the API
                InputStream response = request.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(response));
                String replyText;
                while ((replyText = in.readLine()) != null) {
                    System.out.println(replyText);
                }
                in.close();
            } catch (IOException ex) {
                System.out.println("An error occurred:" + ex.getMessage());
                BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
                // print the detail that comes with the error
                String replyText;
                while ((replyText = in.readLine()) != null) {
                    System.out.println(replyText);
                }
                in.close();
            }
            request.disconnect();
        }
    }

}


