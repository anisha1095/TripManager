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
    String tripId,s,d,b,time;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        SQLiteDatabase db=openOrCreateDatabase("TripExpense",MODE_APPEND,null);
        db.execSQL("create table if not exists trip1(TripID varchar primary key,Destination varchar,Source varchar,Startdate varchar,Enddate varchar," +
            "Budget varchar,Remaining varchar)");
        SharedPreferences sp=getSharedPreferences("Trip",0);
        String status=sp.getString("STATUS","NOT Initialized");

        if(status.equals("NOT Initialized")){
            Toast.makeText(ViewAllActivity.this, "No records in Database", Toast.LENGTH_SHORT).show();
        }
        else {
            String q = "select * from trip1";
            Cursor c = db.rawQuery(q, null);

            id = new ArrayList();
            source = new ArrayList();
            destination = new ArrayList();
            duration = new ArrayList();
            budget = new ArrayList();
            while (c.moveToNext()) {
                tripId = c.getString(0);
                id.add(tripId);
                s = c.getString(2);
                source.add(s);
                d = c.getString(1);
                destination.add(d);
                b = c.getString(5);
                budget.add(b);
                ArrayAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, id);
                setListAdapter(adapter);
            } //end of while loop
            db.close();
        }
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
            tv1.setText(id.get(position) +" "+ source.get(position) +"-"+ destination.get(position));
            tv2.setText("Budget:"+ budget.get(position));
            tripId=id.get(position).toString();
            return row;
        } //end of getView() method
    } //end of MyAdapter class

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(ViewAllActivity.this, "Working", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(ViewAllActivity.this,UpdateDeleteActivity.class);
        i.putExtra("ID",tripId);
        startActivity(i);
    }
} //end of DynamicArrayActivity class
