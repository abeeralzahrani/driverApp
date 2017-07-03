package com.itshareplus.googlemapdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import object.Customer;
import object.SalesOrders;



public class invoice_details extends Fragment {


    private OnFragmentInteractionListener mListener;
    TextView name,phone,location,address,nameT,phoneT,locationT,addressT;
    Button deliveredButton;
    Context context;
    String docStatus ;
    public invoice_details() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.invoices_details_fragment, container, false);
        getActivity().setTitle("Invoice Detail  ");
        name = (TextView)view.findViewById(R.id.customer_name);
        phone=(TextView)view.findViewById(R.id.customer_phone);
        location=(TextView)view.findViewById(R.id.customer_location);
        address=(TextView)view.findViewById(R.id.customer_address);
        deliveredButton=(Button)view.findViewById(R.id.delivered_button);
        nameT=(TextView)view.findViewById(R.id.customer_name_title);
        phoneT=(TextView)view.findViewById(R.id.customer_phone_title);
        locationT=(TextView)view.findViewById(R.id.customer_location_title);
        addressT=(TextView)view.findViewById(R.id.customer_address_title);

        font f = new font(getActivity());
        f.ChangeFontToLight(name);
        f.ChangeFontToLight(phone);
        f.ChangeFontToLight(location);
        f.ChangeFontToLight(address);
        f.ChangeFontToBold(deliveredButton);
        f.ChangeFontToBold(nameT);
        f.ChangeFontToBold(phoneT);
        f.ChangeFontToBold(locationT);
        f.ChangeFontToBold(addressT);

        final SalesOrders salesOrder= (SalesOrders) getArguments().getSerializable("salesOrder");
        assert salesOrder != null;
        docStatus=salesOrder.getDocStatues();
        if(docStatus.equals("Delivered")){
           deliveredButton.setVisibility(View.GONE);
            TextView resultTV= (TextView)view.findViewById(R.id.deliveredTV);
            resultTV.setVisibility(View.VISIBLE);
            resultTV.setText(getResources().getString(R.string.note_invoiceDelivered));
           // deliveredButton.setText("This invoice is delivered ");
        }
        else {
            deliveredButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    background b = new background(getContext());
                    try {
                        String result = b.execute("SaveInvoice_DeliveryStatus", salesOrder.getSAPInvoiceNo() + "", "y").get();
                        System.out.println(result + "   delivery state ");
                        if (result.contains("Error")) {
                            Toast.makeText(getContext(), "some error accrues !!", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(getContext(), "The invoice closed successfully", Toast.LENGTH_SHORT).show();
                            //new SMSTask(context).execute("966556693340","نشكركم على تسوقكم معانا . لتقييم الخدمة اضغط ");
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        final Customer customer=salesOrder.getCustomer();
        name.setText(customer.getFull_name());
        phone.setText(customer.getPhone());
        address.setText(customer.getAddress());
       // location.setText(salesOrder.getLatitude()+" ,"+salesOrder.getLatitude());
        Button location_B = (Button) view.findViewById(R.id.locationB);

        if(salesOrder.getLongitude().equals("")&&salesOrder.getLongitude().equals("")){
            location.setText("لم يتم تحديد الموقع");
            location_B.setClickable(false);
           // Toast.makeText(context,"You have to assign the location of the customers to see the path ",Toast.LENGTH_LONG).show();

        }
        else {
           location.setText("تم تحديد الموقع ");

           assert location_B != null;
           location_B.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent map_intent = new Intent(getContext(), MapsActivity.class);
                   //To pass:
                   map_intent.putExtra("Permission", "findPath");
                   map_intent.putExtra("Latitude", salesOrder.getLatitude());
                   map_intent.putExtra("Longitude", salesOrder.getLongitude());
                   map_intent.putExtra("DocNum", salesOrder.getSAPInvoiceNo() + "");
                   startActivity(map_intent);

               }
           });
       }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}