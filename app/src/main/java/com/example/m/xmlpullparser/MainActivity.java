package com.example.m.xmlpullparser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String url = "http://api.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=ff2485070e13592527b9e2ee86779d74";
Button button ;
    TextView textView;
    String macieesdaek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handlerek("http://api.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=ff2485070e13592527b9e2ee86779d74");

            }
        });
    }

    public class Handlerek  {
        private String city;
        String  url;
        XmlPullParserFactory factory;
        Handlerek(String url){
            this.url=url;
            new Watek().execute();

        }
        public void parseXmlAndStoreIt(XmlPullParser parser) {
            int event ;
            String name1="";
            try {
                event = parser.getEventType();
                while (event!=XmlPullParser.END_DOCUMENT){
                    String name = parser.getName();
                    switch (event){
                        case XmlPullParser.START_TAG  :
                           if(name.equalsIgnoreCase("city")){
                            setCity(parser.getAttributeValue(null,"name"));

                        }
                        else  if(name.equalsIgnoreCase("temperature")){
                            setTemp(parser.getAttributeValue(null,"value"));

                        }

                            break;
                        case XmlPullParser.TEXT:
                            name1 = parser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            Log.d("Uwaga",name1);
                            if(name.equalsIgnoreCase("country")){
                                setCounty(name1);

                            }
                            break;

                    }

                    event=parser.next();

                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




        }










        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }


        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getCoord() {
            return coord;
        }

        public void setCoord(String coord) {
            this.coord = coord;
        }

        String temp;
        String coord;
        String staral;
        String county;

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public class Watek extends AsyncTask<Void,Void,String>{
            String link="http://api.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=ff2485070e13592527b9e2ee86779d74";
            @Override
            protected void onPostExecute(String  aVoid) {
                super.onPostExecute(aVoid);
                Log.d("Uwaga","11");
                textView.setText(getCity()+"   "+getCoord()+"   "+getTemp()+"   "+getCounty());

            }

            @Override
            protected String doInBackground(Void... params) {
                try {
                    String maciellll;
                    String adada;
                    URL url2 = new URL(link);
                    HttpURLConnection connection = (HttpURLConnection)url2.openConnection();
                    connection.setDoInput(true);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream is =connection.getInputStream();
                    Log.d("Uwaga","13131");
                    factory = XmlPullParserFactory.newInstance();
                    Log.d("Uwaga","1313221");
                    XmlPullParser parser = factory.newPullParser();
                    Log.d("Uwaga","13313131131");
                    parser.setInput(is,null);
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
                    parseXmlAndStoreIt(parser);
                    is.close();
                } catch (MalformedURLException e) {
                    Log.d("Uwaga","13131");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("Uwaga","131222222222222231");
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    Log.d("Uwaga","131222222222222222231");
                    e.printStackTrace();
                }


                return null;
            }





        }
    }


















}