package com.itshareplus.googlemapdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.itshareplus.googlemapdemo.R;

import java.util.ArrayList;

import objects.SalesOrders;


/**
 * Created by AZahrani on 4/13/2017.
 */

public class customerinfo extends BaseAdapter {

    private ArrayList<SalesOrders> salesOrders;
    private Context context;
    private SalesOrders salesOrder;
    public static boolean flag =false;

    public static int[] checkedInvoices;


    public customerinfo(ArrayList<SalesOrders> salesOrder, Context context) {
        this.salesOrders = salesOrder;
        this.context = context;
        checkedInvoices=new int[getCount()];
       // mCheckStates = new SparseBooleanArray(salesOrders.size());
    }
    @Override
    public int getCount() {return salesOrders.size();}

    @Override
    public Object getItem(int position) {return salesOrders.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class customerHolder{
        public TextView invoiceNum_text;
        public TextView location_text;
        public CheckBox checkBox;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        //invoice_adapter
        customerHolder holder=new customerHolder();
        LayoutInflater inflater = LayoutInflater.from(context);
        View row =inflater.inflate(R.layout.invoices,parent,false);
        holder.checkBox= (CheckBox) row.findViewById(R.id.checkBox);
        if(!flag){
        holder.checkBox.setOnCheckedChangeListener((MainActivity)context);}
        else{
            holder.checkBox.setVisibility(row.INVISIBLE);
            System.out.println("enable false ");
        }
        //this.position=position;
        holder.invoiceNum_text =(TextView)row.findViewById(R.id.text);
        holder.location_text=(TextView)row.findViewById(R.id.textView);
        salesOrder =salesOrders.get(position);
        String InvoiceNO =salesOrder.getSAPInvoiceNo()+"";
        String customerName=salesOrder.getCustomer().getFull_name();
        holder.invoiceNum_text.setText(InvoiceNO);
        holder.location_text.setText(customerName);
        return row;
    }



}
