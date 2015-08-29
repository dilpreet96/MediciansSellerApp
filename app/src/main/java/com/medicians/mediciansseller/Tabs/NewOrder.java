package com.medicians.mediciansseller.Tabs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.medicians.mediciansseller.Adapter.ContentAdapter;

import com.medicians.mediciansseller.AppController;
import com.medicians.mediciansseller.Models.Content;
import com.medicians.mediciansseller.Models.NewOrderModel;
import com.medicians.mediciansseller.NewOrderDetails;
import com.medicians.mediciansseller.PopulateList;
import com.medicians.mediciansseller.R;
import com.medicians.mediciansseller.Services.ServerRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilpreet on 10/8/15.
 */
public class NewOrder extends Fragment {


    ListView listView;
    public  static ContentAdapter contentAdapter;
    public static List<NewOrderModel> list;
    NewOrderModel newOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.global,null);


        listView=(ListView)view.findViewById(R.id.contentList);
        list=new ArrayList<>();
        contentAdapter=new ContentAdapter(getActivity(),list,10);
        listView.setAdapter(contentAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newOrder = list.get(position);
                startActivity(new Intent(getActivity(), NewOrderDetails.class));
            }
        });

        return view;
    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser)
        {

            PopulateList populateList=new PopulateList(getActivity(),"http://medicians.herokuapp.com/sellerorder/1/new",10);
            populateList.getData();


        }

    }
}
