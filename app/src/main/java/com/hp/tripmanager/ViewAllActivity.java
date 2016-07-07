package com.hp.tripmanager;

import android.app.Activity;
        import android.app.ListActivity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TableLayout;
        import android.widget.TableRow;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;


public class ViewAllActivity extends ListActivity
{
    ArrayList id;
    ArrayList source;
    ArrayList destination;
    ArrayList duration;
    ArrayList budget;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        SQLiteDatabase db=openOrCreateDatabase("TripExpense",MODE_APPEND,null);
        String q="select * from trip1";
        Cursor c=db.rawQuery(q,null);
        String tripId,s,d,b,time;
        id=new ArrayList();
        source=new ArrayList();
        destination=new ArrayList();
        duration=new ArrayList();
        budget=new ArrayList();
        SharedPreferences sp=getSharedPreferences("Trip",0);
        String message=sp.getString("STATUS","Not initialized");

        if(message.equals("Not initialized"))
        {
            while (c.moveToNext())
            {
                String start,end;
                tripId= c.getString(0);
                id.add(tripId);
                s=c.getString(1);
                source.add(s);
                d=c.getString(2);
                destination.add(d);
                b=c.getString(5);
                budget.add(b);

                //calculating the duration
                start=c.getString(3);
                end=c.getString(4);
                String st[]=start.split("/");
                String fin[]=end.split("/");
                int difference=Integer.parseInt(fin[2])-Integer.parseInt(st[2]);
                time= String.valueOf(difference);
                duration.add(time);
            } //end of while loop
            db.close();

            ArrayAdapter adapter=null;
            adapter=new MyAdapter(this,android.R.layout.simple_list_item_1,id);
            setListAdapter(adapter);

            SharedPreferences.Editor editor=sp.edit();
            editor.putString("STATUS","Initialized");
            editor.commit();
        } //end of if block
        else
        {
            Toast.makeText(this,"Already exists",Toast.LENGTH_SHORT).show();
            ArrayAdapter adapter=null;
            adapter=new MyAdapter(this,android.R.layout.simple_list_item_1,id);
            setListAdapter(adapter);
        } //end of else block
    } //end of onCreate() method

    class MyAdapter extends ArrayAdapter
    {
        public MyAdapter(Context context, int resource, ArrayList objects)
        {
            super(context, resource,objects);
        } //end of MyAdapter constructor

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = li.inflate(R.layout.activity_view_all, parent, false);
            TextView tv1 = (TextView) row.findViewById(R.id.textView1);
            TextView tv2 = (TextView) row.findViewById(R.id.textView2);
            tv1.setText("Trip ID:" + id+"   From:"+source+" To:"+destination);
            tv2.setText("Duration:"+duration+"  Budget:"+budget);
            return row;
        } //end of getView() method
    } //end of MyAdapter class
} //end of DynamicArrayActivity class
