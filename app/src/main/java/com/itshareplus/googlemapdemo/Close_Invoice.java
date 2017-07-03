package com.itshareplus.googlemapdemo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

import object.SalesOrders;

public class Close_Invoice extends AppCompatActivity implements View.OnClickListener {
   //UI assets
    TextView title,errorTV;
    Button closeInvoiceButton;
    CheckBox refusedCheckBox ;
    EditText SurveyCodeET ;

    //variables
    int currentInvoiceIndex,totalNumberOfInvoices;
    SalesOrders currentSalesOrder;
    SalesOrders nextSalesOrder;


    String CloseInvoiceCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close__invoice);

        title= (TextView) findViewById(R.id.title);
        errorTV=(TextView)findViewById(R.id.ErrorTV);
        closeInvoiceButton= (Button) findViewById(R.id.closeInvoiceButton);
        refusedCheckBox=(CheckBox)findViewById(R.id.refusedCheckBox);
        SurveyCodeET= (EditText)findViewById(R.id.SurveyCodeET);
        closeInvoiceButton.setOnClickListener(this);
        currentInvoiceIndex=search_invoices_Fragment.sortCount+1;
        totalNumberOfInvoices = search_invoices_Fragment.sortIndex+1;
        title.setText("Invoice Number "+currentInvoiceIndex+"/"+totalNumberOfInvoices);
        currentSalesOrder= search_invoices_Fragment.sortedSalesOrders.get(search_invoices_Fragment.sortCount);
        String docNumber =currentSalesOrder.getSAPInvoiceNo()+"";

        background b = new background(getApplicationContext());

        try {
            String result=b.execute("GetInvoiceDeliveryCompletionCode",docNumber).get();
            result=result.substring(result.indexOf('"')+1,result.indexOf(';')-1);
            System.out.println(result+"      close invoice code"+!result.contains("Error"));

            if(!result.contains("Error"))
            {System.out.println("runn ");
               /* JSONObject jsonobject = new JSONObject(result);
                CloseInvoiceCode=jsonobject.getString("GetInvoiceDeliveryCompletionCodeResult");*/
                CloseInvoiceCode=result;
                System.out.println(result+" runn ");
            }
            else
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
       if(ValidationInput()){

           errorTV.setVisibility(View.GONE);
           //send SMS message to the next customer with the expected time
           if(currentInvoiceIndex==totalNumberOfInvoices){
               Toast.makeText(this,getResources().getString(R.string.finish_trip_message),Toast.LENGTH_LONG).show();
               Intent main_intent = new Intent(this, MainActivity_nav.class);
               startActivity(main_intent);
           }
           else{
               search_invoices_Fragment.sortCount++;

               nextSalesOrder=search_invoices_Fragment.sortedSalesOrders.get(search_invoices_Fragment.sortCount);
               System.out.println("index "+currentInvoiceIndex+":"+search_invoices_Fragment.sortCount);

               String CustomerPhone="966"+nextSalesOrder.getCustomer().getPhone().substring(1);
               // CustomerPhone="966"+currentSalesOrder.getCustomer().getPhone().substring(1);// before release
               int duration=nextSalesOrder.getSortSalesOrders().getDuration()+10;
               String message="نحن في الطريق إليكم لتركيب الطلب\n" + "الوقت المستغرق للوصول :  "+duration+"دقيقة ";
               // new SMSTask(getBaseContext()).execute("966556693340","نشكركم على تسوقكم معانا . لتقييم الخدمة اضغط ");
               System.out.println("current customer phone "+CustomerPhone);
               System.out.println("current time "+nextSalesOrder.getSortSalesOrders().getDuration());
               String origin = currentSalesOrder.getLatitude()+","+currentSalesOrder.getLongitude();

               Intent map_intent = new Intent(this, MapsActivity.class);
               //To pass:
               map_intent.putExtra("Permission", "findPath");
               map_intent.putExtra("Origin",currentSalesOrder.getLatitude()+","+currentSalesOrder.getLongitude());
               map_intent.putExtra("Latitude", nextSalesOrder.getLatitude());
               map_intent.putExtra("Longitude",nextSalesOrder.getLongitude());
               map_intent.putExtra("DocNum", nextSalesOrder.getSAPInvoiceNo() + "");
               System.out.println("next Doc ^^ "+nextSalesOrder.getSAPInvoiceNo()+"  current Doc "+currentSalesOrder.getSAPInvoiceNo());
               System.out.println("origin location ^^ "+origin+"  Dination "+nextSalesOrder.getLatitude()+","+nextSalesOrder.getLongitude());
               startActivity(map_intent);

           }

       }

    }
//more validation to check the code is correct
    private boolean ValidationInput() {
        boolean result=true;
        String code=SurveyCodeET.getText().toString();
        if(refusedCheckBox.isChecked())
            result=true;
        else if ((code.equals("Enter the code from the customer ")||code.isEmpty())&&!refusedCheckBox.isChecked()) {
            result=false;
            errorTV.setVisibility(View.VISIBLE);
            errorTV.setText(getResources().getString(R.string.input_error_close_invoice));

        }
        else if(!code.equals("Enter the code from the customer ")||!code.isEmpty()) {
            if (code.equals(CloseInvoiceCode)) {//correct code result
                result = true;
            } else if (!code.equals(CloseInvoiceCode)) {
                result = false;
                errorTV.setVisibility(View.VISIBLE);
                errorTV.setText(getResources().getString(R.string.input_error_close_invoice_code));
            }
        }
        return result;
    }
}
