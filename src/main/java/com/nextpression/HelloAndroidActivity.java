package com.nextpression;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HelloAndroidActivity extends Activity {

    private static String TAG = "veneer";

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.main);
//        int[] colors = new int[] {getResources().getColor(R.color.darkBlue),
//                getResources().getColor(R.color.cornFlowerBlue)};
        int[] colors = new int[]{Color.parseColor("#FF012853"), Color.parseColor("#ff708f96")};
        TextView runtime1 = (TextView) findViewById(R.id.runtimeView1);
        TextView runtime2 = (TextView) findViewById(R.id.runtimeView2);


        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, colors);
        runtime1.setBackgroundDrawable(gradientDrawable);
        runtime2.setBackgroundDrawable(gradientDrawable);


    }

}

