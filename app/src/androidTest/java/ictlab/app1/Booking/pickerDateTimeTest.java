package ictlab.app1.Booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import android.test.ActivityInstrumentationTestCase2;

import ictlab.app1.R;

import static org.junit.Assert.*;

public class pickerDateTimeTest extends ActivityInstrumentationTestCase2<pickerDateTime>{


    public pickerDateTimeTest(String pkg, Class<pickerDateTime> activityClass) {
        super("ictlab.app1.booking", pickerDateTime.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        pickerDateTime pickerDateTime = getActivity();


    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onClick() {
    }
}