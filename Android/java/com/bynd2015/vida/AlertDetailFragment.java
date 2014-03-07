package com.bynd2015.vida;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment representing a single Alert detail screen.
 * This fragment is either contained in a {@link AlertListActivity}
 * in two-pane mode (on tablets) or a {@link AlertDetailActivity}
 * on handsets.
 */
public class AlertDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Alert mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlertDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_alert_detail, container, false);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            AsyncHttpClient client = new AsyncHttpClient();
            client.get("http://byndhack.herokuapp.com/events/"+getArguments().getInt(ARG_ITEM_ID), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray alertsArray = jsonResponse.getJSONArray("rows");

                        JSONObject jObj = alertsArray.optJSONObject(0);
                        mItem = new Alert(jObj.getDouble("radius"), jObj.getInt("cartodb_id"), jObj.getString("description"), jObj.getString("symptoms"), jObj.getString("prevent"), jObj.getString("help"));
                        getActivity().setTitle(mItem.getDescription());

                        if (mItem != null) {
                            ((TextView) rootView.findViewById(R.id.alert_description)).setText(mItem.getDescription());
                            ((TextView) rootView.findViewById(R.id.alert_symptoms)).setText(mItem.getSymptoms());
                            ((TextView) rootView.findViewById(R.id.alert_prevent)).setText(mItem.getPrevent());
                            ((TextView) rootView.findViewById(R.id.alert_help)).setText(mItem.getHelp());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return rootView;
    }
}
