package com.itshareplus.googlemapdemo;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import object.SalesOrders;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Home_Fragment extends Fragment implements  View.OnClickListener, AdapterView.OnItemSelectedListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private OnFragmentInteractionListener mListener;
    private ArrayList<SalesOrders> salesOrderse;
    TextView textView;
    TextView text;
    TextView searchTypeTitle,dateTile,searchDepend;
    Button submit_button,searchButton;
    ListView listView;
    Spinner spinner_city;
    ImageView calender_image;
    EditText date_et;
    String Search_date,Search_city,Search_depend;
    int [] listOfSelectionInvoices ;
    String [] spinnerItems;
    RadioButton regionRB,cityRB,assignDriversRB,reportRB;
    String PositionTitle,empCode;
    userSessionManeger session;
    String searchType="";//if it is driver so the search is just one type


    public Home_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //getContext().getSupportActionBar().setDisplayHomeAsUpEnable(true);
        // Inflate the layout for this fragment
        Search_date=Search_city=Search_depend="";
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle(getResources().getString(R.string.search_page));
        PositionTitle=(String) getActivity().getIntent().getSerializableExtra("PositionTitle");
        TableLayout tableLayout =(TableLayout)view.findViewById(R.id.table);
        TableRow third =(TableRow)view.findViewById(R.id.third_row);
        TableRow fourth =(TableRow)view.findViewById(R.id.fourth_row);
        submit_button=(Button)view.findViewById(R.id.submit_button);
        TableRow searchTypeRow=(TableRow)view.findViewById(R.id.search_title_Row);
        TableRow firstRadioRow=(TableRow)view.findViewById(R.id.Radio_first_row);
        TableRow secondRadioRow=(TableRow)view.findViewById(R.id.Radio_secound_row);
        TableRow fifth_row=(TableRow)view.findViewById(R.id.fifth_row);
       // searchTitle=(TextView)view.findViewById(R.id.titleSearch);
        searchTypeTitle=(TextView)view.findViewById(R.id.search_type);
        dateTile=(TextView)view.findViewById(R.id.date_tv);
        searchDepend=(TextView)view.findViewById(R.id.searchDepend);
        session =new userSessionManeger(getContext());
        if(session.checkLogin())
           getActivity().finish();
        //get user data from session
        HashMap<String,String> user = session.getUserDetails();
        PositionTitle=user.get(userSessionManeger.KEY_positionTitle);
        empCode=user.get(userSessionManeger.KEY_CODE);
        System.out.println("Driver out "+PositionTitle+empCode);
        if(PositionTitle.contains("Driver")){

            assert tableLayout != null;
            assert third != null;
            tableLayout.removeView(third);
            assert fourth != null;
            assert searchTypeRow != null;
            tableLayout.removeView(searchTypeRow);
            tableLayout.removeView(fourth);
            assert firstRadioRow != null;
            tableLayout.removeView(firstRadioRow);
            assert secondRadioRow != null;
            tableLayout.removeView(secondRadioRow);
            tableLayout.removeView(fifth_row);
        }
        text =(TextView)view.findViewById(R.id.text);
        textView=(TextView)view.findViewById(R.id.textView);

        calender_image=(ImageView)view.findViewById(R.id.calender_image);
        if (calender_image != null) {
            calender_image.setOnClickListener(this);
        }
        date_et=(EditText)view.findViewById(R.id.date_et);
        date_et.setOnClickListener(this);
        if(PositionTitle.contains("Supervisor")) {
            spinner_city = (Spinner) view.findViewById(R.id.spinner_city);
            assert spinner_city != null;
            spinner_city.setOnItemSelectedListener(this);
            regionRB = (RadioButton) view.findViewById(R.id.region_radioButton);
            cityRB = (RadioButton) view.findViewById(R.id.city_radioButton);
            assignDriversRB=(RadioButton)view.findViewById(R.id.assign_driversRadio);
            reportRB=(RadioButton)view.findViewById(R.id.report_radio);
            regionRB.setOnClickListener(this);
            reportRB.setOnClickListener(this);
            cityRB.setOnClickListener(this);
            assignDriversRB.setOnClickListener(this);


        }//the supervisor case*/
        // checkBox=(CheckBox) findViewById(R.id.checkBox);

        salesOrderse=new ArrayList<SalesOrders>();
        searchButton=(Button)view.findViewById(R.id.search_button);
        assert searchButton != null;
        searchButton.setOnClickListener(this);
        //  listView=(ListView)findViewById(R.id.listview);
return view;
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.region_radioButton:
                if (checked){
                    //  Toast.makeText(this,"checked region",Toast.LENGTH_LONG).show();
                    setSpinnerValue("Region");
                    Search_depend="ByDateAndDeliveryRegion";
                    cityRB.setChecked(false);}
                break;
            case R.id.city_radioButton:
                if (checked){
                    // Toast.makeText(this,"checked city",Toast.LENGTH_LONG).show();
                    setSpinnerValue("City");
                    Search_depend="ByDateAndDeliveryCity";
                    regionRB.setChecked(false);
                }
                break;
            case R.id.report_radio:
                searchType="report";
                assignDriversRB.setChecked(false);

                break;
            case R.id.assign_driversRadio:
                searchType="assign_driver";
                reportRB.setChecked(false);
                break;
        }
    }
    //when the user chose the type of search
    private void setSpinnerValue(String type) {

        background b =new background(getContext());
        String result="";
        try {
            if(type.equals("City")){
                result= b.execute("GetDeliveryCities").get();}
            else if(type.equals("Region"))
            {
                result= b.execute("GetDeliveryRegions").get();
            }

            result=result.substring(result.indexOf('=')+1,result.indexOf(';'));
            JSONArray jsonarray = new JSONArray(result);
            String city;
            spinnerItems=new String[jsonarray.length()];
            for (int i = 0; i < jsonarray.length(); i++) {

                JSONObject jsonobject = jsonarray.getJSONObject(i);
                city= jsonobject.getString(type);
                spinnerItems[i]=city;
            }


        } catch (InterruptedException | JSONException | ExecutionException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city.setAdapter(adapter);

    }
/*

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int pos = listView.getPositionForView(compoundButton);
        if (pos != ListView.INVALID_POSITION) {
            SalesOrders s = salesOrderse.get(pos);
            s.setSelected(b);

            if (b){
                listOfSelectionInvoices[pos] = s.getSAPInvoiceNo();
                System.out.print(listOfSelectionInvoices[pos]+"    content of the list ");}
            else
                listOfSelectionInvoices[pos] = 0;
        }

    }*/

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case  R.id.calender_image :
            case R.id.date_et:

                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.search_button:
                /*Intent search_invoices = new Intent(getApplication().getApplicationContext(), search_invoices.class);
                search_invoices.putExtra("Search_type",Search_type);
                search_invoices.putExtra("Search_date",Search_date);
                search_invoices.putExtra("Search_city",Search_city);
                search_invoices.putExtra("searchType",searchType);
                startActivity(search_invoices)v;*/
if(inputValidation()) {
    Bundle bundle = new Bundle();
    bundle.putSerializable("Search_type", Search_depend);
    bundle.putSerializable("Search_date", Search_date);
    bundle.putSerializable("Search_city", Search_city);
    bundle.putSerializable("searchType", searchType);
    search_invoices_Fragment search_invoices = new search_invoices_Fragment();
    search_invoices.setArguments(bundle);
    FragmentManager manager = getActivity().getSupportFragmentManager();
    manager.beginTransaction().replace(R.id.content, search_invoices, search_invoices.getTag()).commit();
}

                break;
                case R.id.region_radioButton:
                    boolean checked = ((RadioButton) view).isChecked();
                    if (checked){
                        //  Toast.makeText(this,"checked region",Toast.LENGTH_LONG).show();
                        setSpinnerValue("Region");
                        Search_depend="ByDateAndDeliveryRegion";
                        cityRB.setChecked(false);}
                    break;
                case R.id.city_radioButton:
                     checked = ((RadioButton) view).isChecked();
                    if (checked){
                        // Toast.makeText(this,"checked city",Toast.LENGTH_LONG).show();
                        setSpinnerValue("City");
                        Search_depend="ByDateAndDeliveryCity";
                        regionRB.setChecked(false);
                    }
                    break;
                case R.id.report_radio:
                    searchType="report";
                    assignDriversRB.setChecked(false);

                    break;
                case R.id.assign_driversRadio:
                    searchType="assign_driver";
                    reportRB.setChecked(false);
                    break;


        }

    }
//searchTitle,searchTypeTitle,dateTile,searchDepend
    private boolean inputValidation() {
        boolean b=true;
        if(PositionTitle.contains("Supervisor")){
        if(Search_depend.equals("")||searchType.equals("")){
            b=false;
            if(Search_depend.equals("")){
                SetError(searchDepend);
            }
        if(searchType.equals(""))
            SetError(searchTypeTitle);
        }
        }
        if(Search_date.equals("")){
                SetError(dateTile);
                b=false;
            }

        return b;
    }

    private void SetError(TextView textView) {
        textView.setError("required !");
    }


    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date_et.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
            String day=dayOfMonth+"",month=monthOfYear+"";
            if(monthOfYear<=9)
                month="0"+monthOfYear;
            if (dayOfMonth<=9)
                day="0"+dayOfMonth;
            Search_date=year+month+day;
        }

    };


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText = (TextView) view;
        Search_city=myText.getText().toString();
        //Toast.makeText(this,myText.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
