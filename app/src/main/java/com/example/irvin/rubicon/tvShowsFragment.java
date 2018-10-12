package com.example.irvin.rubicon;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class tvShowsFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private TvShowAdapter mTvShowAdapter;
    private ArrayList<TvShow> mTvShowsList;
    private TvShow tvShow;
    private RequestQueue mRequestQueue;
    private boolean isVisible;
    private boolean isStarted;

    public interface OnItemClickedListener{
        void OnItemClicked();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.tvshows_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvShow = new TvShow();
        mTvShowsList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getContext());
        parseJSON("");

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                tvShow = mTvShowsList.get(position);
                Bundle args = new Bundle();
                args.putString("imageUrl", tvShow.getPoster_path());
                args.putString("description", tvShow.getOverview());
                args.putString("title", tvShow.getName());
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.setArguments(args);
                ft.replace(R.id.detailsFragmentFrame, detailsFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("tag1");
                ft.commit();
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void receiveURL(String input){
        Toast.makeText(getContext(), input, Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventUpdateFragment url) {
        if(isVisible && isStarted){
            parseJSON(url.url);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        isStarted = true;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        isStarted = false;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
    }

    private void parseJSON(String searchText){
        final String searchTxt = searchText;
        String url = "https://api.themoviedb.org/3/tv/top_rated?api_key=ccec82cbb8f8ca4cb4d62907a9c90955";
        if(searchText.length()>2){
            url = "https://api.themoviedb.org/3/search/tv?api_key=ccec82cbb8f8ca4cb4d62907a9c90955&query=" + searchText;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            //first clear list
                            mTvShowsList.clear();
                            if(searchTxt.length()>2){
                                for(int i=0; i<jsonArray.length(); i++){
                                    String tvShowJSON = jsonArray.getJSONObject(i).toString();
                                    TvShow tvShow = new Gson().fromJson(tvShowJSON, TvShow.class);
                                    mTvShowsList.add(tvShow);
                                }
                                mTvShowAdapter.notifyDataSetChanged();
                            }else{
                                for(int i=0; i<10; i++){
                                    String tvShowJSON = jsonArray.getJSONObject(i).toString();
                                    TvShow tvShow = new Gson().fromJson(tvShowJSON, TvShow.class);
                                    mTvShowsList.add(tvShow);
                                }
                                mTvShowAdapter = new TvShowAdapter(getContext(), mTvShowsList);
                                mRecyclerView.setAdapter(mTvShowAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}
