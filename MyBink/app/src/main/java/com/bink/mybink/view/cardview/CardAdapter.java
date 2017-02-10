package com.bink.mybink.view.cardview;

import android.support.v7.widget.CardView;

/**
 * Created by kub on 10/02/2017.
 */

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
