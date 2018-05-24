package ictlab.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Pellito on 18-5-2018.
 */

public class helpClass extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    public void faq1ButonOnClick(View view) {
        setContentView(R.layout.faq1);
    }

    public void faq2ButonOnClick(View view) {
        setContentView(R.layout.faq2);
    }

    public void faq3ButonOnClick(View view) {
        setContentView(R.layout.faq3);
    }


}
