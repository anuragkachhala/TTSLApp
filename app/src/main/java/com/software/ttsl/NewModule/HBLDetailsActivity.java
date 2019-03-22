package com.software.ttsl.NewModule;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

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

import static com.software.ttsl.NewModule.TrackCargoFragment.HBL_NO;
import static com.software.ttsl.NewModule.TrackingConstantUtil.METHOD_NAME_HBL_DETAILS;
import static com.software.ttsl.NewModule.TrackingConstantUtil.NAMESPACE;
import static com.software.ttsl.NewModule.TrackingConstantUtil.SOAP_ACTION_HBL;
import static com.software.ttsl.NewModule.TrackingConstantUtil.URL;

public class HBLDetailsActivity extends AppCompatActivity {

    public static final String TAG = HBLDetailsActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.hbl_details_container)
    LinearLayout linearLayoutHBLDetailContainer;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.tv_message)
    TextView textViewMessage;

    @BindView(R.id.hbl_milestone_details)
    LinearLayout linearLayoutHBLMileStoneContainer;

    LinearLayout mLinearLayout = null;

    private Intent intent;
    String responsedata, reponseDump1;
    TextView textViewLabel;
    TextView textViewValue;
    private String hblNo;
    private List<HBLModel.StringTag> detailsHeader;
    private List<HBLModel.StringTag> detailsValues;
    private List<HBLModel.StringTag> mileStoneHeader;
    private List<HBLModel.StringTag> mileStoneValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hbldetails);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        intent = getIntent();
        hblNo = intent.getStringExtra(HBL_NO);

        AsyncCall task = new AsyncCall();
        task.execute();

    }

    private void containerDetails() {
        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_HBL_DETAILS);
            Request.addProperty("hblno", hblNo);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;

            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.debug = true;
            transport.call(SOAP_ACTION_HBL, soapEnvelope);
            SoapObject response = (SoapObject) soapEnvelope.getResponse();

            responsedata = response.toString();
            reponseDump1 = transport.responseDump;

            //Log.e("Data1",jarray.toString());
            Log.d("Data", reponseDump1);
            Log.d("Data1", responsedata);





        } catch (Exception ex) {
            Log.e(TAG, "ERROR: " + ex.getMessage());
        }

    }

    private void setHBLDetails() {

        linearLayoutHBLDetailContainer.removeAllViews();
        for (int i = 0; i < detailsHeader.size(); i++) {
            final View view = getLayoutInflater().inflate(R.layout.row_hbl_details, mLinearLayout, true);
            textViewLabel = (TextView) view.findViewById(R.id.hbl_label);
            textViewValue = (TextView) view.findViewById(R.id.hbl_value);
            textViewLabel.setText(detailsHeader.get(i).getContent());
            textViewValue.setText(checkNullValue(detailsValues.get(i).getContent()));
            linearLayoutHBLDetailContainer.addView(view);
        }

    }

    private void setHBLMileStoneDetails() {
        linearLayoutHBLMileStoneContainer.removeAllViews();
        for (int i = 0; i < mileStoneHeader.size(); i++) {
            final View view = getLayoutInflater().inflate(R.layout.row_hbl_milestone, mLinearLayout, true);
            textViewLabel = (TextView) view.findViewById(R.id.hbl_milestone_label);
            textViewValue = (TextView) view.findViewById(R.id.hbl_milestone_value);
            textViewLabel.setText(mileStoneHeader.get(i).getContent());
            textViewValue.setText(checkNullValue(mileStoneValue.get(i).getContent()));
            linearLayoutHBLMileStoneContainer.addView(view);


        }

    }


    private String checkNullValue(String value) {
        return value != null ? value : "NA";
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
                String xml = reponseDump1.replace("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body>", "").replace("</soap:Body></soap:Envelope>", "").replace("<HBLDetailsResponse xmlns=\"http://www.freightsystems.com\"><HBLDetailsResult>", "").replace("</HBLDetailsResult></HBLDetailsResponse>", "").replace(xmlVerTag, "");
                xml = xmlVerTag + "<TracknTrace xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://www.freightsystems.com\">" + xml + "</TracknTrace>";

                Log.d("XML VERSION TAG", xmlVerTag);
                Log.d("XML", xml);


                XmlToJson xmlToJson = new XmlToJson.Builder(xml).build();
                Gson gson = new Gson();
                //Log.e("DATAs", xmlToJson.toJson().toString());
                HBLModel model = null;
                try {
                    model = gson.fromJson(xmlToJson.toJson().toString(), HBLModel.class);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    textViewMessage.setVisibility(View.GONE);
                }
                if (model != null) {
                    scrollView.setVisibility(View.VISIBLE);
                    textViewMessage.setVisibility(View.GONE);
                    detailsHeader = model.getTracknTrace().getDetailsheaders().getString();
                    detailsValues = model.getTracknTrace().getDetailsValues().getString();
                    mileStoneHeader = model.getTracknTrace().getMilestoneHeaders().getString();
                    mileStoneValue = model.getTracknTrace().getMilestoneValues().getString();


                    setHBLDetails();
                    setHBLMileStoneDetails();
                    //Printing container header with values
                    for (int i = 0; i < detailsHeader.size(); i++) {
                        // text.append(detailsHeader.get(i).getContent() + " - " + detailsValues.get(i).getContent() + "\n");
                        Log.i("DATA", detailsHeader.get(i).getContent() + " - " + detailsValues.get(i).getContent() + "\n");
                    }

                    for (int i = 0; i < mileStoneHeader.size(); i++) {
                        //text.append(mileStoneHeader.get(i).getContent() + " - " + mileStoneValue.get(i).getContent() + "\n");
                        Log.i("DATA", detailsHeader.get(i).getContent() + " - " + detailsValues.get(i).getContent() + "\n");
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
                textViewMessage.setVisibility(View.VISIBLE);
            }

        }

    }

}
