//package ictlab.app1;
//
///**
// * Created by edgar on 5-3-2018.
// */
//
//import android.os.AsyncTask;
//import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
//import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
//import com.google.api.client.util.DateTime;
//
//import com.google.api.services.calendar.model.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import ictlab.app1.R;
///**
// * An asynchronous task that handles the Google Calendar API call.
// * Placing the API calls in their own task ensures the UI stays responsive.
// */
//
///**
// * Created by miguel on 5/29/15.
// */
//
//public class apiAsyncTask extends AsyncTask<Void, Void, Void> {
//    private calendarClass mcalendarClass;
//
//    /**
//     * Constructor.
//     * @param activity calendarClass that spawned this task.
//     */
//    apiAsyncTask(calendarClass activity) {
//        this.mcalendarClass = activity;
//    }
//
//    /**
//     * Background task to call Google Calendar API.
//     * @param params no parameters needed for this task.
//     */
//    @Override
//    protected Void doInBackground(Void... params) {
//        try {
//            mcalendarClass.clearResultsText();
//            mcalendarClass.updateResultsText(getDataFromApi());
//
//        } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
//            mcalendarClass.showGooglePlayServicesAvailabilityErrorDialog(
//                    availabilityException.getConnectionStatusCode());
//
//        } catch (UserRecoverableAuthIOException userRecoverableException) {
//            mcalendarClass.startActivityForResult(
//                    userRecoverableException.getIntent(),
//                    calendarClass.REQUEST_AUTHORIZATION);
//
//        } catch (IOException e) {
//            mcalendarClass.updateStatus("The following error occurred: " +
//                    e.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * Fetch a list of the next 10 events from the primary calendar.
//     * @return List of Strings describing returned events.
//     * @throws IOException
//     */
//    private List<String> getDataFromApi() throws IOException {
//        // List the next 10 events from the primary calendar.
//        DateTime now = new DateTime(System.currentTimeMillis());
//        List<String> eventStrings = new ArrayList<String>();
//        Events events = mcalendarClass.mService.events().list("primary")
//                .setMaxResults(10)
//                .setTimeMin(now)
//                .setOrderBy("startTime")
//                .setSingleEvents(true)
//                .execute();
//        List<Event> items = events.getItems();
//
//        for (Event event : items) {
//            DateTime start = event.getStart().getDateTime();
//            if (start == null) {
//                // All-day events don't have start times, so just use
//                // the start date.
//                start = event.getStart().getDate();
//            }
//            eventStrings.add(
//                    String.format("%s (%s)", event.getSummary(), start));
//        }
//        return eventStrings;
//    }
//
//}
