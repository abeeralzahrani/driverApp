package com.itshareplus.googlemapdemo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by AZeaage on 4/5/2017.
 */

class CallSoap {

    String WSDL_TARGET_NAMESPACE="http://tempuri.org/";
    SoapObject request;
    String SOAP_ADDRESS = "http://hr.alkaffary.com:664/AlKaffaryMobileService.svc";
    String SOAP_ACTION;
    String OPERATION_NAME;
    private final String tokenKey="0DE4EA23-6420-43C2-B853-18E8D6B32837";
    String GetDeliveredInvoicesByDeliveryDateAndDeliveryCity( String deliveryDate, String deliveryCity){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetDeliveredInvoicesByDeliveryDateAndDeliveryCity";
        String OPERATION_NAME="GetDeliveredInvoicesByDeliveryDateAndDeliveryCity";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("deliveryDate",deliveryDate);
        request.addProperty("deliveryCity",deliveryCity);

        return connectToService();

    }
    String GetDeliveredInvoicesByDeliveryDateAndDeliveryRegion(String deliveryDate, String deliveryRegion){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetDeliveredInvoicesByDeliveryDateAndDeliveryRegion";
        String OPERATION_NAME="GetDeliveredInvoicesByDeliveryDateAndDeliveryRegion";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("deliveryDate",deliveryDate);
        request.addProperty("deliveryRegion",deliveryRegion);

        return connectToService();
    }
    public String AuthenticateEmployee(String empCode, String loginPassword){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/AuthenticateEmployee";
        String OPERATION_NAME="AuthenticateEmployee";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("empCode",empCode);
        request.addProperty("loginPassword",loginPassword);

        return connectToService();
    }
    String GetDeliveryCities(){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetDeliveryCities";
        String OPERATION_NAME="GetDeliveryCities";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);

        return connectToService();
    }
    String GetDriversList(){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetDriversList";
        String OPERATION_NAME="GetDriversList";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);

        return connectToService();
    }
    String GetDeliveryRegions(){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetDeliveryRegions";
        String OPERATION_NAME="GetDeliveryRegions";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);

        return connectToService();
    }
    String SaveInvoice_AssignedDriver(String docNum, String assignedDriver){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/SaveInvoice_AssignedDriver";
        String OPERATION_NAME="SaveInvoice_AssignedDriver";
        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
       System.out.println("Driver Name "+ assignedDriver+" doc num"+docNum);
        request.addProperty("tokenKey",tokenKey);
         PropertyInfo pi ;
        int Doc=Integer.parseInt(docNum);
        pi = new PropertyInfo();
        pi.setName("docNum");
        pi.setValue(Doc);
        pi.setType(Integer.class);
        request.addProperty(pi);
        request.addProperty("assignedDriver",assignedDriver);
        return connectToService();
    }
    public String SaveInvoice_DeliveryStatus( String docNum, String deliveryStatus){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/SaveInvoice_DeliveryStatus";
        String OPERATION_NAME="SaveInvoice_DeliveryStatus";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        int DocNum= Integer.parseInt(docNum);
        request.addProperty("tokenKey",tokenKey);
        PropertyInfo pi ;
        pi = new PropertyInfo();
        pi.setName("docNum");
        pi.setValue(DocNum);
        pi.setType(Integer.class);
        request.addProperty(pi);
        request.addProperty("deliveryStatus",deliveryStatus);
        return connectToService();
    }
    String SaveInvoice_LatLong(String docNum, String latitude, String longitude)
    {
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/SaveInvoice_LatLong";
        String OPERATION_NAME="SaveInvoice_LatLong";
        int DocNum= Integer.parseInt(docNum);
        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        PropertyInfo pi ;
        pi = new PropertyInfo();
        pi.setName("docNum");
        pi.setValue(DocNum);
        pi.setType(Integer.class);
        request.addProperty(pi);
        request.addProperty("latitude",latitude);
        request.addProperty("longitude",longitude);
        return connectToService();
    }
    public String GetInvoicesByShipDate( String shipDate){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetInvoicesByShipDate";
        String OPERATION_NAME="GetInvoicesByShipDate";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("shipDate",shipDate);
        return connectToService();
    }
    public String GetInvoicesByShipDateAndDriverCode( String shipDate, String driverCode){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetInvoicesByShipDateAndDriverCode";
        String OPERATION_NAME="GetInvoicesByShipDateAndDriverCode";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("shipDate",shipDate);
        request.addProperty("driverCode", driverCode);
        return connectToService();
    }
    public String GetInvoicesByShipDateAndDeliveryCity( String shipDate, String deliveryCity){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetInvoicesByShipDateAndDeliveryCity";
        String OPERATION_NAME="GetInvoicesByShipDateAndDeliveryCity";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("shipDate",shipDate);
        request.addProperty("deliveryCity", deliveryCity);
        return connectToService();
    }
    public String GetInvoicesByShipDateAndDeliveryRegion( String shipDate, String deliveryRegion){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetInvoicesByShipDateAndDeliveryRegion";
        String OPERATION_NAME="GetInvoicesByShipDateAndDeliveryRegion";
        System.out.println("Date "+shipDate+" deliveryRegion"+ deliveryRegion);
        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("shipDate",shipDate);
        request.addProperty("deliveryRegion", deliveryRegion);
        return connectToService();
    }
    public String GetBranchesList(String tokenKey){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetBranchesList";
        String OPERATION_NAME="GetBranchesList";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);

        return connectToService();
    }

    public String GetBranchesByCity( String city) {
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetBranchesByCity";
        OPERATION_NAME="GetBranchesByCity";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("city",city);
        return connectToService();
    }

    public String GetCompanyInfo(String tokenKey){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetCompanyInfo";
        String OPERATION_NAME="GetCompanyInfo";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);

        return connectToService();
    }
    public String GetInvoiceDeliveryCompletionCode(String docNum){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetInvoiceDeliveryCompletionCode";
        String OPERATION_NAME="GetInvoiceDeliveryCompletionCode";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        request.addProperty("tokenKey",tokenKey);
        PropertyInfo pi ;
        pi = new PropertyInfo();
        pi.setName("docNum");
        pi.setValue(docNum);
        pi.setType(Integer.class);
        request.addProperty(pi);
        return connectToService();
    }
    public String RegisterUser(String tokenKey, String fullName, String email, String loginPassword, String phone1
            , String phone2 , String address , String city , String gpsURL )
    {
         SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/RegisterUser";
         OPERATION_NAME="RegisterUser";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
       // PropertyInfo PI = new PropertyInfo();
        //set attributes
     //   System.out.println(fullName+" "+email+phone1+phone2+loginPassword+city+address+gpsURL+"*******&&&&&&&&");
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("fullName",fullName);
        request.addProperty("email",email);
        request.addProperty("loginPassword",loginPassword);
        request.addProperty("phone1",phone1);
        request.addProperty("phone2",phone2);
        request.addProperty("address",address);
        request.addProperty("city",city);
        request.addProperty("gpsURL",gpsURL);

        return connectToService();

    }
   public String AuthenticateCustomer(String tokenKey, String email, String loginPassword){
       SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/AuthenticateCustomer";
       OPERATION_NAME="AuthenticateCustomer";

       request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
       //set attributes
       request.addProperty("tokenKey",tokenKey);
       request.addProperty("email",email);
       request.addProperty("loginPassword",loginPassword);
       return connectToService();

   }
   public String GetProductsList(String tokenKey, String productGroupCode, String producOriginCode, String salePriceFrom, String salePriceTo){
        SOAP_ACTION="http://tempuri.org/IAlKaffaryMobileService/GetProductsList";
        OPERATION_NAME="GetProductsList";

        request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        //set attributes
        request.addProperty("tokenKey",tokenKey);
        request.addProperty("productGroupCode",productGroupCode);
        request.addProperty("producOriginCode",producOriginCode);
        request.addProperty("salePriceFrom",100.0);//need to be change
        request.addProperty("salePriceTo",100000.0);
        return connectToService();

   }
    private String connectToService(){
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.implicitTypes = true;//new add
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        String response =null;

        try {

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.debug=true;
            // httpTransport.setXmlVersionTag("<?xmlx version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");

            httpTransport.call(SOAP_ACTION,envelope);
            //response=httpTransport.responseDump;
            //SoapPrimitive response1= (SoapPrimitive)envelope.getResponse();
            SoapObject resultsReq=(SoapObject)envelope.bodyIn;
            response=resultsReq.toString();
        } catch (XmlPullParserException | IOException e)
        {
            e.printStackTrace();
            response= "" + e;
        }
        System.out.println("response ********  "+response);
        return response;
    }
}
