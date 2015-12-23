package spinno.com.munchoba.fragmentsformainscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import spinno.com.munchoba.Adapterclasses.Myadaptericon;
import spinno.com.munchoba.HorizontalListView;
import spinno.com.munchoba.R;
import spinno.com.munchoba.imageloading.ImageLoader;
import spinno.com.munchoba.parsingforapi.parsingforlikeunlike;


public class FitnessfeedAdapter extends BaseAdapter {
    public static ArrayList<String> id332 = new ArrayList<String>();
    public static ArrayList<String> commenttype2 = new ArrayList<String>();
    public static ArrayList<String> likes222 = new ArrayList<String>();
    public static ArrayList<String> foodtitle2 = new ArrayList<String>();
    public static ArrayList<String> title112 = new ArrayList<String>();
    public static ArrayList<String> title11profile2 = new ArrayList<String>();
    public static ArrayList<String> title11photos2 = new ArrayList<String>();
    public static ArrayList<String> created12 = new ArrayList<String>();
    public static ArrayList<String> calories112 = new ArrayList<String>();
    public static ArrayList<String> volume2 = new ArrayList<String>();
    public static ArrayList<String> volume_unit2 = new ArrayList<String>();
    public static ArrayList<String> food_id2 = new ArrayList<String>();
    public static ArrayList<String> total_cal2 = new ArrayList<String>();
    public static ArrayList<String> graph_image23 = new ArrayList<String>();
    public static ArrayList<String> graph_image223 = new ArrayList<String>();
    public static ArrayList<String> workout_time2 = new ArrayList<String>();
    public static ArrayList<String> workout_calories2 = new ArrayList<String>();
    public static ArrayList<String> mood2 = new ArrayList<String>();
    public static ArrayList<String> mood222 = new ArrayList<String>();
    public static ArrayList<String> typevideo2 = new ArrayList<String>();
    public static ArrayList<String> description112 = new ArrayList<String>();
    public static ArrayList<String> thumb2 = new ArrayList<String>();
    public static ArrayList<String> path2 = new ArrayList<String>();
    public static ArrayList<List<String>> thumbnailphoto2 = new ArrayList<>();
    public static ArrayList<ArrayList<String>> idphoto2 = new ArrayList<>();
    public static ArrayList<List<String>> captionphoto2 = new ArrayList<>();
    public static ArrayList<List<String>> imagephoto2 = new ArrayList<>();
    ImageLoader il;
    LayoutInflater inflater;
    Context ctc2;
    String profilename;





    public    FitnessfeedAdapter(Context ctc, ArrayList<String> id33, ArrayList<String> commenttype,
                                 ArrayList<String> created1, ArrayList<String> foodtitle,
                                 ArrayList<String> calories11, ArrayList<String> volume, ArrayList<String> volume_unit,
                                 ArrayList<String> food_id, ArrayList<String> total_cal, ArrayList<String> title11,
                                 ArrayList<String> workout_time, ArrayList<String> workout_calories, ArrayList<String> mood,
                                 ArrayList<String> title11profile, ArrayList<String> mood22, ArrayList<String> graph_image,
                                 ArrayList<String> graph_image2, ArrayList<String> typevideo, ArrayList<String> description11,
                                 ArrayList<String> thumb, ArrayList<String> path, ArrayList<String> title11photos,
                                 ArrayList<ArrayList<String>> idphotomain, ArrayList<List<String>> captionmain,
                                 ArrayList<List<String>> thumnailphotomain, ArrayList<List<String>> imagemain,
                                 ArrayList<String> likes22) {
        Log.e("ffff", "adapter method chla");
        this.ctc2=ctc;
     id332=id33;
        commenttype2=commenttype;
        created12=created1;
        foodtitle2=foodtitle;
        calories112=calories11;
        volume2=volume;
        volume_unit2=volume_unit;
        food_id2=food_id;
        total_cal2=total_cal;
        title112=title11;
        workout_time2=workout_time;
        workout_calories2=workout_calories;
        mood2=mood;
        mood222=mood22;
        title11profile2=title11profile;
        graph_image23=graph_image;
        graph_image223=graph_image2;
        typevideo2=typevideo;
        description112=description11;
        thumb2=thumb;
        path2=path;
        title11photos2=title11photos;
        idphoto2=idphotomain;
        captionphoto2=captionmain;
        thumbnailphoto2=thumnailphotomain;
        imagephoto2=imagemain;
        likes222=likes22;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctc2);
        profilename = prefs.getString("name", null);


        il=new ImageLoader(ctc.getApplicationContext());

        inflater = LayoutInflater.from(this.ctc2);
    }



    @Override
    public int getCount() {
        return id332.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView nameperson,createdtxt,caloriesno,Itemseaten,Nofcals,Noofcals2,volunit,emotiontext,titleemotion,calvalue,
                timevalue,titleval,tileforgraph;
        ImageView imgphoto,imgphoto22;
        LinearLayout llforemoticon,liked,unliked;
       HorizontalListView hlv;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
       if(commenttype2.get(position).equals("photos")) {
            convertView = inflater.inflate(R.layout.photoactivity, null);
            holder = new Holder();
            convertView.setTag(holder);
            holder.imgphoto = (ImageView)convertView.findViewById(R.id.imageofact);
           holder.nameperson = (TextView)convertView.findViewById(R.id.heading);
           holder.createdtxt = (TextView)convertView.findViewById(R.id.timetext);

           holder.hlv = (HorizontalListView)convertView.findViewById(R.id.hlvSimpleList);
           holder.createdtxt.setText(""+created12.get(position));
           holder.nameperson.setText("" + profilename);
           holder.liked = (LinearLayout)convertView.findViewById(R.id.liked);
           holder.unliked = (LinearLayout)convertView.findViewById(R.id.unliked);
           if(likes222.get(position).equals("0")){
               holder.liked.setVisibility(View.VISIBLE);
               holder.unliked.setVisibility(View.INVISIBLE);
           }
           else{
               holder.liked.setVisibility(View.INVISIBLE);
               holder.unliked.setVisibility(View.VISIBLE);
           }
           holder.liked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforlike(ctc2,commenttype2.get(position),id332.get(position));
                   holder.liked.setVisibility(View.INVISIBLE);
                   holder.unliked.setVisibility(View.VISIBLE);
                   likes222.add(position,"11");
               }
           });
           holder.unliked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforunlike(ctc2, commenttype2.get(position), id332.get(position));
                   likes222.add(position,"0");
                   holder.liked.setVisibility(View.VISIBLE);
                   holder.unliked.setVisibility(View.INVISIBLE);
               }
           });
           for(int h=0;h<thumbnailphoto2.get(position).size();h++) {
               if (thumbnailphoto2.get(position).size() < 2) {
                   holder.hlv.setVisibility(View.GONE);
                   holder.imgphoto.setVisibility(View.VISIBLE);
                   il.DisplayImage(thumbnailphoto2.get(position).get(0), holder.imgphoto);
               }
               else{
                   holder.imgphoto.setVisibility(View.GONE);
                   holder.hlv.setVisibility(View.VISIBLE);
                   Myadaptericon adp = new Myadaptericon(ctc2,thumbnailphoto2.get(position));
                   holder.hlv.setAdapter(adp);
               }
           }
        }
       else if(commenttype2.get(position).equals("food")) {
            convertView = inflater.inflate(R.layout.foodactivity, null);
            holder = new Holder();
            convertView.setTag(holder);
           holder.nameperson = (TextView)convertView.findViewById(R.id.heading);
           holder.Itemseaten = (TextView)convertView.findViewById(R.id.itemseaten);
           holder.caloriesno = (TextView)convertView.findViewById(R.id.calno);
           holder.createdtxt = (TextView)convertView.findViewById(R.id.timetext);
           holder.Nofcals = (TextView)convertView.findViewById(R.id.noofcals22);
           holder.Noofcals2 = (TextView)convertView.findViewById(R.id.noofcals23);
           holder.volunit = (TextView)convertView.findViewById(R.id.grams);
           holder.createdtxt.setText(""+created12.get(position));
           holder.nameperson.setText(""+profilename);
           holder.Itemseaten.setText("" + foodtitle2.get(position));
           holder.caloriesno.setText(""+total_cal2.get(position));
           holder.Nofcals.setText(""+volume2.get(position));
           holder.Noofcals2.setText(""+calories112.get(position));
           holder.volunit.setText(""+volume_unit2.get(position));
           holder.liked = (LinearLayout)convertView.findViewById(R.id.liked);
           holder.unliked = (LinearLayout)convertView.findViewById(R.id.unliked);
           if(likes222.get(position).equals("0")){
               holder.liked.setVisibility(View.VISIBLE);
               holder.unliked.setVisibility(View.INVISIBLE);
           }
           else{
               holder.liked.setVisibility(View.INVISIBLE);
               holder.unliked.setVisibility(View.VISIBLE);
           }
           holder.liked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforlike(ctc2,commenttype2.get(position),id332.get(position));
                   holder.liked.setVisibility(View.INVISIBLE);
                   holder.unliked.setVisibility(View.VISIBLE);
                   likes222.add(position,"11");
               }
           });
           holder.unliked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforunlike(ctc2,commenttype2.get(position),id332.get(position));
                   likes222.add(position,"0");
                   holder.liked.setVisibility(View.VISIBLE);
                   holder.unliked.setVisibility(View.INVISIBLE);
               }
           });
        }
        else if(commenttype2.get(position).equals("profile.status")) {
            convertView = inflater.inflate(R.layout.profilestatus, null);
            holder = new Holder();
            convertView.setTag(holder);
           holder.nameperson = (TextView)convertView.findViewById(R.id.heading);
           holder.createdtxt = (TextView)convertView.findViewById(R.id.timetext);
           holder.createdtxt.setText(""+created12.get(position));
           holder.nameperson.setText("" + profilename);
            holder.emotiontext = (TextView)convertView.findViewById(R.id.emotion);
           holder.titleemotion = (TextView)convertView.findViewById(R.id.titleemotion);
           holder.llforemoticon = (LinearLayout)convertView.findViewById(R.id.llforemo);
           holder.liked = (LinearLayout)convertView.findViewById(R.id.liked);
           holder.unliked = (LinearLayout)convertView.findViewById(R.id.unliked);
           if(likes222.get(position).equals("0")){
               holder.liked.setVisibility(View.VISIBLE);
               holder.unliked.setVisibility(View.INVISIBLE);
           }
           else{
               holder.liked.setVisibility(View.INVISIBLE);
               holder.unliked.setVisibility(View.VISIBLE);
           }
           holder.liked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforlike(ctc2,commenttype2.get(position),id332.get(position));
                   holder.liked.setVisibility(View.INVISIBLE);
                   holder.unliked.setVisibility(View.VISIBLE);
                   likes222.add(position,"11");
               }
           });
           holder.unliked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforunlike(ctc2,commenttype2.get(position),id332.get(position));
                   likes222.add(position,"0");
                   holder.liked.setVisibility(View.VISIBLE);
                   holder.unliked.setVisibility(View.INVISIBLE);
               }
           });
            holder.titleemotion.setText("" + title11profile2.get(position));
         //  Log.e("jjjjjjj", "" + mood2.get(position));
           holder.emotiontext.setText("" + mood222.get(position));
          // Log.e("edddd",""+holder.emotiontext.getText().toString().trim());
          if( holder.emotiontext.getText().toString().equals("null") || holder.emotiontext.getText().toString().isEmpty()){
               holder.llforemoticon.setVisibility(View.GONE);
           } else {
              holder.llforemoticon.setVisibility(View.VISIBLE);

           }
        }
       else if(commenttype2.get(position).equals("profile.status.workout")) {
           convertView = inflater.inflate(R.layout.profilestatusworkout, null);
           holder = new Holder();
           convertView.setTag(holder);
           holder.nameperson = (TextView)convertView.findViewById(R.id.heading);
           holder.createdtxt = (TextView)convertView.findViewById(R.id.timetext);
           holder.createdtxt.setText(""+created12.get(position));
           holder.nameperson.setText("" + profilename);
           holder.titleval = (TextView)convertView.findViewById(R.id.titlevalue);
           holder.calvalue = (TextView)convertView.findViewById(R.id.calvalue);
           holder.timevalue = (TextView)convertView.findViewById(R.id.timevalue);
           holder.liked = (LinearLayout)convertView.findViewById(R.id.liked);
           holder.unliked = (LinearLayout)convertView.findViewById(R.id.unliked);
           if(likes222.get(position).equals("0")){
               holder.liked.setVisibility(View.VISIBLE);
               holder.unliked.setVisibility(View.INVISIBLE);
           }
           else{
               holder.liked.setVisibility(View.INVISIBLE);
               holder.unliked.setVisibility(View.VISIBLE);
           }
           holder.liked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforlike(ctc2,commenttype2.get(position),id332.get(position));
                   holder.liked.setVisibility(View.INVISIBLE);
                   holder.unliked.setVisibility(View.VISIBLE);
                   likes222.add(position,"11");
               }
           });
           holder.unliked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforunlike(ctc2,commenttype2.get(position),id332.get(position));
                   likes222.add(position,"0");
                   holder.liked.setVisibility(View.VISIBLE);
                   holder.unliked.setVisibility(View.INVISIBLE);
               }
           });
           holder.calvalue.setText(""+workout_calories2.get(position));
           holder.timevalue.setText(""+workout_time2.get(position));
           holder.titleval.setText(""+title112.get(position));
           //Log.e("jjjjjjj",""+mood222.get(position));
         /* if(mood222.get(position).equals("aa")){
               holder.llforemoticon.setVisibility(View.GONE);
           }
           else{
               holder.llforemoticon.setVisibility(View.VISIBLE);
           }*/
       }
       else if(commenttype2.get(position).equals("exercise_graph")) {
           convertView = inflater.inflate(R.layout.itemlistforexercisegraph, null);
           holder = new Holder();
           convertView.setTag(holder);
           holder.nameperson = (TextView)convertView.findViewById(R.id.heading);
           holder.createdtxt = (TextView)convertView.findViewById(R.id.timetext);
           holder.imgphoto22 = (ImageView)convertView.findViewById(R.id.imageofact);
           holder.liked = (LinearLayout)convertView.findViewById(R.id.liked);
           holder.unliked = (LinearLayout)convertView.findViewById(R.id.unliked);
           if(likes222.get(position).equals("0")){
               holder.liked.setVisibility(View.VISIBLE);
               holder.unliked.setVisibility(View.INVISIBLE);
           }
           else{
               holder.liked.setVisibility(View.INVISIBLE);
               holder.unliked.setVisibility(View.VISIBLE);
           }
           holder.liked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforlike(ctc2,commenttype2.get(position),id332.get(position));
                   holder.liked.setVisibility(View.INVISIBLE);
                   holder.unliked.setVisibility(View.VISIBLE);
                   likes222.add(position,"11");
               }
           });
           holder.unliked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforunlike(ctc2,commenttype2.get(position),id332.get(position));
                   likes222.add(position,"0");
                   holder.liked.setVisibility(View.VISIBLE);
                   holder.unliked.setVisibility(View.INVISIBLE);
               }
           });
           holder.createdtxt.setText(""+created12.get(position));
           holder.nameperson.setText("" + profilename);
           holder.tileforgraph = (TextView)convertView.findViewById(R.id.titlegraph);
           holder.tileforgraph.setText("shared a exercise graph");
           il.DisplayImage(graph_image23.get(position), holder.imgphoto22);
       }
       else if(commenttype2.get(position).equals("food_graph")) {
           convertView = inflater.inflate(R.layout.itemlistforexercisegraph, null);
           holder = new Holder();
           convertView.setTag(holder);
           holder.nameperson = (TextView)convertView.findViewById(R.id.heading);
           holder.createdtxt = (TextView)convertView.findViewById(R.id.timetext);
           holder.imgphoto22 = (ImageView)convertView.findViewById(R.id.imageofact);
           holder.liked = (LinearLayout)convertView.findViewById(R.id.liked);
           holder.unliked = (LinearLayout)convertView.findViewById(R.id.unliked);
           if(likes222.get(position).equals("0")){
               holder.liked.setVisibility(View.VISIBLE);
               holder.unliked.setVisibility(View.INVISIBLE);
           }
           else{
               holder.liked.setVisibility(View.INVISIBLE);
               holder.unliked.setVisibility(View.VISIBLE);
           }
           holder.liked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforlike(ctc2,commenttype2.get(position),id332.get(position));
                   holder.liked.setVisibility(View.INVISIBLE);
                   holder.unliked.setVisibility(View.VISIBLE);
                   likes222.add(position,"11");
               }
           });
           holder.unliked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforunlike(ctc2,commenttype2.get(position),id332.get(position));
                   likes222.add(position,"0");
                   holder.liked.setVisibility(View.VISIBLE);
                   holder.unliked.setVisibility(View.INVISIBLE);
               }
           });
           holder.createdtxt.setText(""+created12.get(position));
           holder.nameperson.setText("" + profilename);
           holder.tileforgraph = (TextView)convertView.findViewById(R.id.titlegraph);
           holder.tileforgraph.setText("shared a food graph");
           il.DisplayImage(graph_image223.get(position), holder.imgphoto22);
       }
        else  {


               convertView = inflater.inflate(R.layout.itemlistlayoutforvideo, null);
               holder = new Holder();
               convertView.setTag(holder);
           holder.nameperson = (TextView)convertView.findViewById(R.id.heading);
           holder.createdtxt = (TextView)convertView.findViewById(R.id.timetext);
           holder.liked = (LinearLayout)convertView.findViewById(R.id.liked);
           holder.unliked = (LinearLayout)convertView.findViewById(R.id.unliked);
           Log.e("likes", "" + likes222.get(position));
           if(likes222.get(position).equals("0")){
               holder.liked.setVisibility(View.VISIBLE);
               holder.unliked.setVisibility(View.INVISIBLE);
           }
           else{
               holder.liked.setVisibility(View.INVISIBLE);
               holder.unliked.setVisibility(View.VISIBLE);
           }
           holder.liked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforlike(ctc2,commenttype2.get(position),id332.get(position));
                   holder.liked.setVisibility(View.INVISIBLE);
                   holder.unliked.setVisibility(View.VISIBLE);
                   likes222.add(position,"11");
               }
           });
           holder.unliked.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parsingforlikeunlike.parsingforunlike(ctc2,commenttype2.get(position),id332.get(position));
                   likes222.add(position, "0");
                   holder.liked.setVisibility(View.VISIBLE);
                   holder.unliked.setVisibility(View.INVISIBLE);
               }
           });
           holder.createdtxt.setText(""+created12.get(position));
           holder.nameperson.setText("" + profilename);

       }


        return convertView;
    }
}



