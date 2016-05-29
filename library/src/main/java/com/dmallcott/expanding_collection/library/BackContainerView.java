package com.dmallcott.expanding_collection.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by dmallcott on 28/05/2016.
 */
public class BackContainerView extends RelativeLayout {

    private static final int ANIMATION_DISTANCE = 200;
    private static final int ANIMATION_DURATION = 400;
    private static final float ANIMATION_SCALE = 0.8f;

    View mRoot;
    TextView mNumber;
    AppCompatRatingBar mRating;
    FrontContainerView mFrontContainer;

    AnimatorSet mAnimatorSet;
    boolean isAnimating;
    boolean isInformationShowing;

    public BackContainerView(Context context) {
        this(context, null);
    }

    public BackContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRoot = inflate(context, R.layout.view_container_back, this);

        mNumber = (TextView) mRoot.findViewById(R.id.view_container_back_number);
        mRating = (AppCompatRatingBar) mRoot.findViewById(R.id.view_container_back_rating);
        mFrontContainer = (FrontContainerView) mRoot.findViewById(R.id.view_container_back_front);

        init();
    }

    private void init() {
        mAnimatorSet = new AnimatorSet();
        isInformationShowing = false;
        isAnimating = false;

        mNumber.setText("NO. 7981126");
        mRating.setRating(4.2f);

        mFrontContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInformationShowing) hideInformation(); else showInformation();
            }
        });
    }

    private void showInformation() {
        if (isAnimating) return;

        ValueAnimator translationAnimator = ValueAnimator.ofInt(0, ANIMATION_DISTANCE);
        translationAnimator.setDuration(ANIMATION_DURATION);
        translationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                mFrontContainer.setTranslationY(-value);
            }
        });

        ValueAnimator scalingAnimator = ValueAnimator.ofFloat(1f, ANIMATION_SCALE);
        scalingAnimator.setDuration(ANIMATION_DURATION);
        scalingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                mFrontContainer.setScaleX(value);
                mFrontContainer.setScaleY(value);
            }
        });

        mAnimatorSet.playTogether(translationAnimator, scalingAnimator);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
                isInformationShowing = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimatorSet.start();
    }

    private void hideInformation() {
        if (isAnimating) return;

        ValueAnimator translationAnimator = ValueAnimator.ofInt(ANIMATION_DISTANCE, 0);
        translationAnimator.setDuration(ANIMATION_DURATION);
        translationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                mFrontContainer.setTranslationY(-value);
            }
        });

        ValueAnimator scalingAnimator = ValueAnimator.ofFloat(ANIMATION_SCALE, 1f);
        scalingAnimator.setDuration(ANIMATION_DURATION);
        scalingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                mFrontContainer.setScaleX(value);
                mFrontContainer.setScaleY(value);
            }
        });

        mAnimatorSet.playTogether(translationAnimator, scalingAnimator);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
                isInformationShowing = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimatorSet.start();
    }
}
