package com.software.ttsl.NewModule;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ttsl.R;
import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;

import static com.software.ttsl.NewModule.TrackCargoFragment.CONTAINER_NO;
import static com.software.ttsl.NewModule.TrackCargoFragment.HBL_NO;
import static com.software.ttsl.NewModule.TrackingConstantUtil.METHOD_NAME_CONTAINER_DETAILS;
import static com.software.ttsl.NewModule.TrackingConstantUtil.NAMESPACE;
import static com.software.ttsl.NewModule.TrackingConstantUtil.SOAP_ACTION_CONTAINER;
import static com.software.ttsl.NewModule.TrackingConstantUtil.URL;

public class ContainerDetailActivity extends AppCompatActivity {

    public static final String TAG = ContainerDetailActivity.class.getName();

    public static final String KEY_CONTAINER_NO = "containerno";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;



    @BindView(R.id.tv_message)
    TextView textViewMessage;

    @BindView(R.id.header_container)
    LinearLayout linearLayoutHeaderContainer;

    @BindView(R.id.hbl_container_details)
    LinearLayout linearLayoutHBLContainerDetails;

    String responsedata, reponseDump1;

    LinearLayout mLinearLayout = null;


    private List<ContainerModel.StringTag> containerHeaders;
    private List<ContainerModel.StringTag> containerValues;
    private List<ContainerModel.StringTag> detailsHeaders;
    private List<ContainerModel.StringTag> detailsvalues;
    private String containerNo;
    private int colorEvenRow;
    private int colorOddRow;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        colorEvenRow = getResources().getColor(R.color.colorRow);
        colorOddRow = getResources().getColor(R.color.colorWhite);
      //  containerNo = "ABCD1221217";
        intent = getIntent();
        containerNo = intent.getStringExtra(CONTAINER_NO);



        AsyncCall task = new AsyncCall();
        task.execute();

    }

    private void containerDetails() {

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_CONTAINER_DETAILS);
            Request.addProperty(KEY_CONTAINER_NO, containerNo);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);
            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.debug = true;
            transport.call(SOAP_ACTION_CONTAINER, soapEnvelope);
            SoapObject response = (SoapObject) soapEnvelope.getResponse();
            responsedata = response.toString();
            reponseDump1 = transport.responseDump;

            //Log.e("Data1",jarray.toString());
            Log.d(TAG, reponseDump1);
            Log.d(TAG, responsedata);

            } catch (Exception ex) {
            Log.e(TAG, "ERROR: " + ex.getMessage());


        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private class AsyncCall extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            progressBar.setVisibility(View.VISIBLE);
            containerDetails();
            return null;
        }

        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.GONE);
            Log.i(TAG, "onPostExecute");


            try {
                String xmlVerTag = reponseDump1.substring(reponseDump1.indexOf("<?"), reponseDump1.indexOf("?>") + 2);
                String xml = reponseDump1.replace("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body>", "").replace("</soap:Body></soap:Envelope>", "").replace("<ContainerDetailsResponse xmlns=\"http://www.freightsystems.com\"><ContainerDetailsResult>", "").replace("</ContainerDetailsResult></ContainerDetailsResponse>", "").replace(xmlVerTag, "");
                xml = xmlVerTag + "<ContainerTrack xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://www.freightsystems.com\">" + xml + "</ContainerTrack>";
                xml = xml.replace("<DetailsHeaders>", "<DetailsHeaders><string></string>");
                xml = xml.replace("<DetailsValues>", "<DetailsValues><string></string>");
                Log.d("XML VERSION TAG", xmlVerTag);
                Log.d("XML", xml);


                XmlToJson xmlToJson = new XmlToJson.Builder(xml).build();
                Gson gson = new Gson();
                ContainerModel model = null;
                try {
                    model = gson.fromJson(xmlToJson.toJson().toString(), ContainerModel.class);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.e(TAG,"Second Try"+ e.toString());
                }
                if (model != null) {
                    scrollView.setVisibility(View.VISIBLE);
                    textViewMessage.setVisibility(View.GONE);
                    containerHeaders = model.getContainertrack().getContainerheaders().getString();
                    containerValues = model.getContainertrack().getContainervalues().getString();
                    detailsHeaders = model.getContainertrack().getDetailsheaders().getString();
                    detailsvalues = model.getContainertrack().getDetailsvalues().getStrings();


                    setContainerHeaders();
                    setHBLDetails();


                    //Printing container header with values


                }


            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG,"First Try"+ e.toString());
                textViewMessage.setVisibility(View.VISIBLE);
            }

        }


        private void setContainerHeaders() {
            linearLayoutHeaderContainer.removeAllViews();
            for (int i = 0; i < containerHeaders.size(); i++) {
                final View view = getLayoutInflater().inflate(R.layout.row_container_header, mLinearLayout, true);
                TextView textView = (TextView) view.findViewById(R.id.container_header);
                TextView textView1 = (TextView) view.findViewById(R.id.container_details);
                textView.setText(containerHeaders.get(i).getContent());
                textView1.setText(containerValues.get(i).getContent());
                linearLayoutHeaderContainer.addView(view);
                Log.i(TAG, containerHeaders.get(i).getContent() + " - " + containerValues.get(i).getContent() + "\n");
            }
        }
        Intent intent;
        private void setHBLDetails() {


            linearLayoutHBLContainerDetails.removeAllViews();
            for (int i = 0; i < detailsHeaders.size(); i++) {
                final View view = getLayoutInflater().inflate(R.layout.row_container_details, mLinearLayout, true);
                TextView textView = (TextView) view.findViewById(R.id.container_details_header);
                TextView textView1 = (TextView) view.findViewById(R.id.container_details_value);
                textView.setText(detailsHeaders.get(i).getContent());
                textView1.setText(detailsvalues.get(i).getContent());
                textView1.setPaintFlags(textView1.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                textView1.setTag(detailsvalues.get(i).getContent());
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         intent = new Intent(ContainerDetailActivity.this,HBLDetailsActivity.class);
                         intent.putExtra(HBL_NO,(String)view.getTag());
                         startActivity(intent);
                    }
                });
                if (i % 2 == 0) {
                    view.setBackgroundColor(colorEvenRow);

                } else {
                    view.setBackgroundColor(colorOddRow);
                }
                if(i!=0) {
                    linearLayoutHBLContainerDetails.addView(view);
                }

            }

        }

    }

  /*  private void clickableSpan(String spannable){

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //what happens whe i click
                Toast.makeText(getApplicationContext(), "you just clicked on a Click Span!", Toast.LENGTH_LONG).show();
            }
        };
        spannableString.setSpan(clickableSpan, 45, 61, 0);
    }
*/



}
