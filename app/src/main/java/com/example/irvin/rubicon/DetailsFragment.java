package com.example.irvin.rubicon;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment{

    ImageView image;
    TextView descriptionTextView;
    TextView titleTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        Bundle args = getArguments();
        String imageUrl = args.getString("imageUrl");
        String description = args.getString("description");
        String title = args.getString("title");
        String url = "http://image.tmdb.org/t/p/w185/";

        titleTextView = (TextView)view.findViewById(R.id.title);
        titleTextView.setText(title);

        image = (ImageView) view.findViewById(R.id.image);
        Picasso.with(getContext()).load(url + imageUrl).fit().centerInside().into(image);

        descriptionTextView = (TextView)view.findViewById(R.id.description);
        descriptionTextView.setText(description);

        // Inflate the layout for this fragment
        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
