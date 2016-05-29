package com.dmallcott.expanding_collection.library;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by dmallcott on 28/05/2016.
 */
public class FrontContainerView extends CardView {

    TextView mTitle;
    TextView mLatitude;
    TextView mLongitude;
    ImageView mImage;

    public FrontContainerView(Context context) {
        this(context, null);
    }

    public FrontContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrontContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View rootView = inflate(context, R.layout.view_container_front, this);

        mTitle = (TextView) rootView.findViewById(R.id.view_container_front_title);
        mLatitude = (TextView) rootView.findViewById(R.id.view_container_front_lat);
        mLongitude = (TextView) rootView.findViewById(R.id.view_container_front_long);
        mImage = (ImageView) rootView.findViewById(R.id.view_container_front_image);

        init();
    }

    private void init() {
        mTitle.setText("London");
        mLatitude.setText("LAT N 51°");
        mLongitude.setText("LNG W 0.1°");
        if (isInEditMode()) mImage.setImageResource(R.drawable.london);
        else Glide.with(getContext()).load(R.drawable.london).into(mImage);
    }
}
