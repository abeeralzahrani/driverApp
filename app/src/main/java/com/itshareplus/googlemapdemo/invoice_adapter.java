package com.itshareplus.googlemapdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import object.SalesOrders;


/**
 * Created by AZahrani on 4/13/2017.
 */

public class invoice_adapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

    private ArrayList<SalesOrders> salesOrders;
    private Context context;
    private SalesOrders salesOrder;
    private String [] drivers;
    private String assignDriver;
    public Spinner [] drivers_spinner;
    public CheckBox checkBox;
    String userRule;
    int position;
    private userSessionManeger session;
    String sort;
    ArrayList<SalesOrders> assignedInvoices=new ArrayList<>();


    public invoice_adapter(ArrayList<SalesOrders> salesOrder, Context context) {
        this.salesOrders = salesOrder;
        this.context = context;

        session =new userSessionManeger(context);
        sort="";
        //get user data from session
        HashMap<String,String> user = session.getUserDetails();
        userRule=user.get(userSessionManeger.KEY_positionTitle);
    }

    public invoice_adapter(ArrayList<SalesOrders> sortedSalesOrders, Context context, String sort) {
        this.salesOrders = sortedSalesOrders;
        this.context = context;

        session =new userSessionManeger(context);

        //get user data from session
        HashMap<String,String> user = session.getUserDetails();
        userRule=user.get(userSessionManeger.KEY_positionTitle);
        this.sort=sort;
    }

    @Override
    public int getCount() {return salesOrders.size();}

    @Override
    public Object getItem(int position) {return salesOrders.get(position);}

   @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position= buttonView.getId();
        System.out.println("value of check boox "+isChecked+" invoice Number "+position);
        if(isChecked){
           assignedInvoices.add(salesOrders.get(position));
        }
        else {
            assignedInvoices.remove(salesOrders.get(position));
        }
    }

    /*  @Override
     public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { //set the driver to the invoices from the dropdown list
         TextView myText = (TextView) view;
         assignDriver=myText.getText().toString();

      //   salesOrder.setDriverID(assignDriver.substring(assignDriver.indexOf(',')+1));
         System.out.println("Invoice posision "+position+" invoice Number "+salesOrder.getSAPInvoiceNo());
         System.out.println("Assgining invoice for "+i+" driver :"+assignDriver+" doc Num "+search_invoices_Fragment.salesOrderse.get(i).getSAPInvoiceNo());
         salesOrder.setDriverID(assignDriver.substring(assignDriver.indexOf(',')+1));

         //Toast.makeText(context,salesOrder.getDriverID(),Toast.LENGTH_LONG).show();
     }

     @Override
     public void onNothingSelected(AdapterView<?> adapterView) {

     }
 */
    static class invoiceHolder{
        public TextView invoiceNum_text;
        public TextView location_text;
        public TextView customer_phone;
        public ImageView icon_locationStates;
        public Button locationButton;

    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        this.position=position;
        View v=convertView;
        //invoice_adapter
        invoiceHolder holder=new invoiceHolder();
        LayoutInflater inflater = LayoutInflater.from(context);
        View row =inflater.inflate(R.layout.invoices,parent,false);

       // drivers_spinner[position]=new Spinner(context);
        checkBox= (CheckBox)row.findViewById(R.id.checkBox);
        TableRow checkBoxRow= (TableRow)row.findViewById(R.id.check_box_row);
        holder.icon_locationStates=(ImageView)row.findViewById(R.id.icon_locationStates);
        holder.locationButton=(Button)row.findViewById(R.id.locationButton);
        Activity searchContext= new search_invoices_Fragment().getActivity();
        checkBox.setOnCheckedChangeListener(this);
        //holder.checkBox.setOnClickListener(()context);
        if(sort.equals("sort")){
            holder.locationButton.setVisibility(View.GONE);
        }
        salesOrder =salesOrders.get(position);
        if(userRule.contains(" Supervisor")){
            holder.icon_locationStates.setVisibility(View.GONE);
            holder.locationButton.setVisibility(View.GONE);
            checkBoxRow.setVisibility(View.VISIBLE);

            checkBox.setId(position);
            /*b =new background(context);
            String result="";
            if(position==0){// to get the driver list only ones
            try {
                System.out.println(position+"  invoices ");
                result= b.execute("GetDriversList").get();
                result=result.substring(result.indexOf('=')+1,result.indexOf(';'));
                JSONArray jsonarray = new JSONArray(result);
                String driverName,driverID;

                drivers=new String[jsonarray.length()];
                drivers[0]="chose the driver";//list of drivers we have
                for (int i =1; i < jsonarray.length(); i++) {

                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    driverName= jsonobject.getString("EmployeeName");
                    driverID=jsonobject.getString("EmployeeCode");
                    drivers[i]=driverName+" ,"+driverID;
                }


            } catch (InterruptedException | ExecutionException | JSONException e) {
                e.printStackTrace();
            }}

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,drivers);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            drivers_spinner[position].setAdapter(adapter);*/

        }//end the Supervisor rule
        else{  //driver rule

          if(!salesOrder.getLatitude().equals("")&&!salesOrder.getLongitude().equals("")) {
              holder.icon_locationStates.setImageDrawable(context.getResources().getDrawable(R.drawable.circle_green_icon));
          }
            else
                holder.icon_locationStates.setImageDrawable(context.getResources().getDrawable(R.drawable.circle_red_icon));
                holder.locationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent map_intent=new Intent(context,MapsActivity.class);
                        //To pass:
                        map_intent.putExtra("Permission", "setCustomerLocation");
                        map_intent.putExtra("Latitude", salesOrders.get(position).getLatitude());
                        map_intent.putExtra("Longitude", salesOrders.get(position).getLongitude());
                        map_intent.putExtra("DocNum", salesOrders.get(position).getSAPInvoiceNo()+"");
                        map_intent.putExtra("invoicePosition", position);
                        map_intent.putExtra("position", position+"");

                        context.startActivity(map_intent);
                    }
                });
        }


        holder.invoiceNum_text =(TextView)row.findViewById(R.id.text);
        holder.location_text=(TextView)row.findViewById(R.id.textView);
        holder.customer_phone=(TextView)row.findViewById(R.id.customerPhone);


        String InvoiceNO =salesOrder.getSAPInvoiceNo()+"";
        String customerName=salesOrder.getCustomer().getFull_name();
        holder.invoiceNum_text.setText(InvoiceNO);
        holder.location_text.setText(customerName);
        String customerPhone=salesOrder.getCustomer().getPhone();
        System.out.println("Customer number: "+customerPhone);
        holder.customer_phone.setText(customerPhone);

        return row;

    }



}
