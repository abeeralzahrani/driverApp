package com.itshareplus.googlemapdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;
import object.Customer;
import object.SalesOrders;


public class search_invoices_Fragment extends Fragment implements DirectionFinderListener{
    private ProgressDialog progressDialog;
    ListView listView;
    Spinner DriverSpinner ;
    private String [] drivers;
    userSessionManeger session;
    String PositionTitle, empCode;
    String Search_type, Search_date, Search_city, search_view;
    int[] listOfSelectionInvoices;
    String name, phone, address, InvoiceNum, Latitude, Longitude,docStatus,driverID;
    invoice_adapter thadapter = null;
    public  SalesOrders salesOrder;
    public static ArrayList<SalesOrders> salesOrderse;
    public  static ArrayList<SalesOrders> sortedSalesOrders;
    Button submit;
    String result;
    public String DriverLocation="";
    GPSTracker gps;
    String  driverLocation;
    public static int sortIndex;
    public static int sortCount=0;
    public Context context;
    String assignDriver;
    TextView error;
    public search_invoices_Fragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        thadapter = new invoice_adapter(salesOrderse, getContext());
        listView.setAdapter(thadapter);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_search_invoices, container, false);
        context=getActivity();
        sortIndex=0;
        listView = (ListView) view.findViewById(R.id.listview);
      //  title=(TextView)view.findViewById(R.id.title);
        submit = (Button) view.findViewById(R.id.submit_button);
        DriverSpinner = (Spinner)view.findViewById(R.id.spinner_drivers);

        error=(TextView)view.findViewById(R.id.errorResult);

                setSpinnerValues();
        salesOrderse = new ArrayList<>();

        Search_type = (String) getArguments().getSerializable("Search_type");
        Search_date = (String) getArguments().getSerializable("Search_date");
        Search_city = (String) getArguments().getSerializable("Search_city");
        search_view = (String) getArguments().getSerializable("searchType");

        //***session declaration
        session = new userSessionManeger(getContext());
        if (session.checkLogin())
            getActivity().finish();
        //get user data from session
        HashMap<String, String> user = session.getUserDetails();
        PositionTitle = user.get(userSessionManeger.KEY_positionTitle);
        empCode = user.get(userSessionManeger.KEY_CODE);

        if (PositionTitle.contains("Supervisor")) {
            if (search_view.equals("assign_driver")) {
                getActivity().setTitle(getResources().getString(R.string.spinner_title));

                //title.setText(getResources().getString(R.string.spinner_title));
                DriverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView myText = (TextView) view;
                        assignDriver=myText.getText().toString();
                        assignDriver=assignDriver.substring(assignDriver.indexOf(',')+1);
                        System.out.println("Selected driver "+assignDriver);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
                try {
                    background b = new background(getContext());
                    result = b.execute("GetInvoices"+Search_type, Search_date, Search_city).get();
                    System.out.println(result+ "assigned invoices ");

                    getInfo(result);//Parsing json object and generate new sales orders

                      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               System.out.print(salesOrderse.get(position).getSAPInvoiceNo()+" Doc num ++++");
                               /*thadapter.drivers_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                   @Override
                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                       TextView myText = (TextView) view;
                                       String assignDriver=myText.getText().toString();
                                       //   salesOrder.setDriverID(assignDriver.substring(assignDriver.indexOf(',')+1));
                                       SalesOrders sObject=(SalesOrders)listView.getItemAtPosition(pos);
                                       System.out.println("Invoice posision "+assignDriver+" invoice Number "+sObject.getSAPInvoiceNo());
                                       //   System.out.println("Assgining invoice for "+i+" driver :"+assignDriver+" doc Num "+search_invoices_Fragment.salesOrderse.get(i).getSAPInvoiceNo());
                                       //sObject.getSAPInvoiceNo().setDriverID(assignDriver.substring(assignDriver.indexOf(',')+1));
                                   }

                                   @Override
                                   public void onNothingSelected(AdapterView<?> parent) {

                                   }
                               });*/
                           }
                       });

                    submit.setText(getResources().getString(R.string.assignButton));

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(assignDriver.equals(""))
                           System.out.println(thadapter.assignedInvoices.size()+"  array list size of invoices ");
                            ArrayList<SalesOrders> assignedInvoices=thadapter.assignedInvoices;
                            SalesOrders invoice;
                            for(int i=0;i<assignedInvoices.size();i++){
                               invoice=assignedInvoices.get(i);
                                background b = new background(getContext());


                                try {
                                    String invoiceNum = invoice.getSAPInvoiceNo() + "";
                                    result = b.execute("SaveInvoice_AssignedDriver", invoiceNum, assignDriver).get();
                                    if (result.contains("Error")) {
                                        Toast.makeText(getContext(), "There are error occurs !!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "There assignation is success ", Toast.LENGTH_SHORT).show();
                                        salesOrderse.remove(invoice);
                                    }

                                } catch (InterruptedException | ExecutionException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(salesOrderse.size()==0)//when all invoices is assigned to driver
                            {
                                error.setVisibility(View.VISIBLE);
                                error.setText(getResources().getString(R.string.no_invoices_message));
                                DriverSpinner.setVisibility(View.GONE);
                                listView.setVisibility(View.GONE);
                            }
                            else
                            {
                                DriverSpinner.setSelection(0);
                                thadapter = new invoice_adapter(salesOrderse, getContext());
                                listView.setAdapter(thadapter);
                            }
                        }
                    });
                } catch (InterruptedException | ExecutionException | JSONException e) {
                    e.printStackTrace();
                }
            } else if (search_view.equals("report")) {
                DriverSpinner.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);

                getActivity().setTitle(getResources().getString(R.string.report_title));
                //title.setText(getResources().getString(R.string.report_title));
                background b = new background(getContext());

                try {
                    if(Search_type.contains("Region")||Search_type.contains("City")) {
                        result = b.execute("GetDeliveredInvoices" + Search_type, Search_date, Search_city).get();
                            getInfo(result);//Parsing json object and generate new sales orders

                    }


                } catch (InterruptedException | ExecutionException | JSONException e) {///delete
                    e.printStackTrace();
                }
            }


        }//end of supervisor case

        if (PositionTitle.contains("Driver")) {

            getActivity().setTitle(getResources().getString(R.string.unsorted_invoices_title));
            DriverSpinner.setVisibility(View.GONE);
            //title.setText(getResources().getString(R.string.unsorted_invoices_title));
            try {
                submit.setText(getResources().getString(R.string.sortButton));
                submit.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean flag =true;
                        for(int i=0 ; i<salesOrderse.size();i++){
                            if(salesOrderse.get(i).getLatitude().equals(""))
                            {
                                flag=false;
                                break;
                            }
                        }
                        if(flag){ // all invoices have location assign
                            sortedSalesOrders=new ArrayList<SalesOrders>();
                            driverLocation= setDriverLocation(); //  first stage take the driver current location to compare with it
                            sortInvoices(driverLocation);
                        }
                        else {
                            Toast.makeText(getContext(),"you should assign the location for all invoices ",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                background b = new background(getContext());
               result = b.execute("GetInvoicesByShipDateAndDriverCode", Search_date, empCode).get();

                salesOrderse.clear();
                if (!result.contains("Error")) {
                    getInfo(result);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SalesOrders salesOrders = salesOrderse.get(position);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("salesOrder",salesOrders);
                            invoice_details customerInfoFragment= new invoice_details();
                            customerInfoFragment.setArguments(bundle);
                            FragmentManager manager=getActivity().getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.content,customerInfoFragment,customerInfoFragment.getTag()).commit();

                        }


                    });
                } else {
                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                }

            } catch (InterruptedException | ExecutionException | JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    private void setSpinnerValues() {
        background b =new background(context);
        String result="";

            try {
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
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,drivers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DriverSpinner.setAdapter(adapter);
    }

    public void sortInvoices(String originLocation ){

        for( int i = 0; salesOrderse.size() > i; i++){
            final int index=i;

            final String destinationLocation=salesOrderse.get(i).getLatitude()+","+salesOrderse.get(i).getLongitude();
            DirectionFinderListener directionFinderListener=new DirectionFinderListener() {
                @Override
                public void onDirectionFinderStart() {
                                    /*progressDialog = ProgressDialog.show(getContext(), "Please wait.",
                                            "Finding direction..!", true);*/
                }
                @Override
                public String onDirectionFinderSuccess(List<Route> routes) {
                    //  progressDialog.dismiss();
                    Double distance_int=0.0;
                    for (Route route : routes) {
                        String destination = route.distance.text;
                        System.out.println("Doc number "+salesOrderse.get(index).getSAPInvoiceNo());
                        System.out.println(route.duration.text+" location duration"+route.duration_MOU+" result +++++++"+index+"destination "+destinationLocation);
                        System.out.println(route.distance.text+" location distance result*********"+index);
                        //  SalesOrders s = search_invoices_Fragment.salesOrderse.get(invoicePosition);
                        System.out.println("duration "+route.duration.text+"/m");
                        //1 hour 4 mins
                        int duration_int;
                        if(route.duration.text.contains("hour")){
                            duration_int=Integer.parseInt(route.duration.text.substring(0,route.duration.text.indexOf('h')-1))*60;
                            if(route.duration.text.contains("mins")){
                                String sub=route.duration.text.substring(route.duration.text.indexOf('r')+1,route.duration.text.indexOf('m')-1);

                                System.out.println("str sub &&&&"+sub);
                                duration_int+=Integer.parseInt(route.duration.text.substring(route.duration.text.indexOf('r')+2,route.duration.text.indexOf('m')-1));}
                        }
                        else
                            duration_int=Integer.parseInt(route.duration.text.substring(0,route.duration.text.indexOf('m')-1));


                        if(destination.contains("km"))
                            distance_int=Double.parseDouble(route.distance.text.substring(0,route.distance.text.indexOf('k')-1));
                        else if(destination.contains("m"))
                            distance_int=Double.parseDouble(route.distance.text.substring(0,route.distance.text.indexOf('m')-1))/1000;
                        salesOrderse.get(index).getSortSalesOrders().setDuration(duration_int);
                        salesOrderse.get(index).getSortSalesOrders().setDistance(distance_int);
                        System.out.println("duration = " +salesOrderse.get(index).getSortSalesOrders().getDuration());
                        System.out.println("distance = " +salesOrderse.get(index).getSortSalesOrders().getDistance());
                        if(index==salesOrderse.size()-1){
                            saveInvoiceToArray();
                            sortIndex++;
                        }
                    }
                    return null;
                }
            };
            try {
            new DirectionFinder(directionFinderListener,originLocation,destinationLocation).execute(); //to find the direction and get the duration and distance
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveInvoiceToArray() {

        Collections.sort(salesOrderse);//sorting the arrayList based on the distance
        sortedSalesOrders.add(salesOrderse.get(0));//save the node
        salesOrderse.remove(0);
        if(salesOrderse.size()>1){
            System.out.println(salesOrderse.size()+" list size ");
            String originLocation= salesOrderse.get(0).getLatitude()+","+salesOrderse.get(0).getLongitude();
            sortInvoices(originLocation);
        }
        else if (salesOrderse.size()==1){
            sortedSalesOrders.add(salesOrderse.get(0));//save the node
            thadapter = new invoice_adapter(sortedSalesOrders, getContext(),"sort");
            listView.setAdapter(thadapter);
            Toast.makeText(getContext(),"The invoices are sorted successfully",Toast.LENGTH_SHORT).show();

            getActivity().setTitle(getResources().getString(R.string.sorted_invoices_title));
            //title.setText(getResources().getString(R.string.sorted_invoices_title));
            submit.setText(getResources().getString(R.string.start_drivingButton));
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendMessages();
                    Intent map_intent = new Intent(getContext(), MapsActivity.class);
                    //To pass:
                    map_intent.putExtra("Permission", "findPath");
                    map_intent.putExtra("Origin",driverLocation);
                    map_intent.putExtra("Latitude", sortedSalesOrders.get(0).getLatitude());
                    map_intent.putExtra("Longitude",sortedSalesOrders.get(0).getLongitude());
                    map_intent.putExtra("DocNum", sortedSalesOrders.get(0).getSAPInvoiceNo() + "");
                    startActivity(map_intent);


                }
            });
        }
    }

    private void sendMessages() {
        String message ;
    String phone=parstingPhone(sortedSalesOrders.get(0).getCustomer().getPhone());
        int duration =sortedSalesOrders.get(0).getSortSalesOrders().getDuration()+10;
        System.out.println("نحن في الطريق إليكم لتركيب الطلب \n" + "الوقت المستغرق للوصول :  "+duration+" دقيقة ");
        message="نحن في الطريق إليكم لتركيب الطلب \n" + "الوقت المستغرق للوصول :  "+duration+"دقيقة ";
       // new SMSTask(getContext()).execute("966556693340",message);
        duration=0;

        for(int i=1;i<sortedSalesOrders.size();i++){
            phone=parstingPhone(sortedSalesOrders.get(i).getCustomer().getPhone());
            for (int a=0;a<i;a++){
                duration+=sortedSalesOrders.get(a).getSortSalesOrders().getDuration();
            }

            duration+=sortedSalesOrders.get(i).getSortSalesOrders().getDuration()+10;
            String time =convertTime(duration);
            message="فريق التركيب سيقومون بالتركيب الشحنة لكم اليوم\n" + "المتوقع وصولهم خلال : "+time;
            System.out.println(message+" DocNum :"+sortedSalesOrders.get(i).getSAPInvoiceNo());
           // new SMSTask(getContext()).execute("966556693340",message);
        }

    }
    int hours =0;
    int min=0;
    private String convertTime(int duration) {
        String time="";

        if(duration>=60){
            hours++;
            duration-=60;
            convertTime(duration);
        }
        else if (duration<60)
            min=duration;
        if(hours==1)
            time = "ساعة ";
        else if (hours==2)
            time = "ساعتين ";
        else if (hours>2)
            time = hours+" ساعات ";
        if(!time.isEmpty())
            time+=" و";
        if(min!=0)
            time+=min+" دقيقة ";
        return time;
    }

    private String parstingPhone(String phone) {
        return "966"+phone.substring(1);
    }

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MAP_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                SalesOrders[] SortedSales=new SalesOrders[salesOrderse.size()];
                for(int a=0;a<salesOrderse.size();a++){
                   SortedSales[a]=salesOrderse.get(a);
                    System.out.println(salesOrderse.get(a).getSortSalesOrders().getDistance()+"  duration of invoices ++++++++ "+a);
                }
                for (SalesOrders SortedSale : SortedSales)
                    System.out.println(SortedSale + "      unsorted array");
                Arrays.sort(SortedSales, Collections.reverseOrder());


                for (SalesOrders SortedSale : SortedSales)
                    System.out.println(SortedSale + "      sorted array");

        }}


    }*/

    private void getInfo(String result1) throws JSONException {
        result1 = result1.substring(result1.indexOf('=') + 1, result1.indexOf(';'));
        if (!result1.contains("Error")) {

        JSONArray jsonarray = new JSONArray(result1);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            name = jsonobject.getString("U_CustName1");
            phone = jsonobject.getString("U_Phone1");
            address = jsonobject.getString("U_Address");
            InvoiceNum = jsonobject.getString("DocNum");
            Latitude = jsonobject.getString("Latitude");
            Longitude = jsonobject.getString("Longitude");
            docStatus=jsonobject.getString("DeliveryStatusDesc");
            //System.out.println(name + " phone " + phone);
            Customer customer = new Customer(name, phone, address);
            salesOrder = new SalesOrders(Integer.parseInt(InvoiceNum),docStatus,Latitude,Longitude, customer);
            salesOrderse.add(salesOrder);
        }
        listOfSelectionInvoices = new int[salesOrderse.size()];

        thadapter = new invoice_adapter(salesOrderse, getContext());

        listView.setAdapter(thadapter);
        }
        else {
            error.setVisibility(View.INVISIBLE);
            error.setText(result1);

        }
    }

    @Override
    public void onDirectionFinderStart() {

    }

    @Override
    public String onDirectionFinderSuccess(List<Route> route) {
        return null;
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public String setDriverLocation() {
        gps = new GPSTracker(getContext());

        double latitude,longitude;
        String result ="error";
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            result=latitude+","+longitude;
            if(result.equals("0,0")){
                setDriverLocation();
            }
            DriverLocation=result;
        } else {
            gps.showSettingsAlert();
        }
        return result;
    }


}

