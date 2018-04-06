package ictlab.app1.ListTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.view.MaterialListView;

import ictlab.app1.Booking.pickerDateTime;
import ictlab.app1.R;

public class ListTestActivity extends AppCompatActivity {

    MaterialListView materialListView;

    /*
    - When activity is created
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtestactivity);

        this.initializeViews();
        this.bindData();
    }

    /*
    - Initialize Material ListView.
     */
    private void initializeViews()
    {
        materialListView = findViewById(R.id.material_listview);
    }

    /*
    - Bind data to Material ListView.
     */
    private void bindData() {
        for (classRooms g : getData()) {
            this.createCard(g);
        }
    }
    /*
    - Create a CardView with title,description, image and image.
     */
    private void createCard(final classRooms g){
        Card card = new Card.Builder(this)

                .withProvider(new CardProvider())
                .setLayout(R.layout.material_basic_image_buttons_card_layout)
                .setBackgroundColor(getResources().getColor(R.color.hrRood))
                .setTitle(g.getName())
                .setTitleGravity(Gravity.START)
                .setTitleColor(getResources().getColor(R.color.mdtp_white))
                .setDescription(g.getDescription())
                .setDescriptionGravity(Gravity.START)
                .setDescriptionColor(getResources().getColor(R.color.mdtp_white))
                .addAction(R.id.left_text_button, new TextViewAction(this)
                        .setText("Book this room!")
                        .setTextResourceColor(R.color.mdtp_white)
                        .setListener(new OnActionClickListener() {
                            @Override
                            public void onActionClicked(View view, Card card) {
                                switch (g.getName()) {
                                    case "H4.308":
                                        Intent intent = new Intent(ListTestActivity.this, pickerDateTime.class);
                                        startActivity(intent);
                                        break;
                                    case "H4.318":
                                        Intent intent1 = new Intent(ListTestActivity.this, pickerDateTime.class);
                                        startActivity(intent1);
                                        break;
                                    case "H4.312":
                                        Intent intent2 = new Intent(ListTestActivity.this, pickerDateTime.class);
                                        startActivity(intent2);
                                        break;

                                }
                            }
                        }))
                .endConfig()
                .build();

        // Add Card to ListView
        materialListView.getAdapter().add(card);
    }

    /*
    - Our data source
     */
    private classRooms[] getData()
    {
        classRooms[] classrooms = new classRooms[3];

        classRooms g=new classRooms("H4.308",
                "Room H4.308 has a beamer, a whiteboard and windows that can open. The room has space for appr. 25 people.");
        classrooms[0]=g;

        g=new classRooms("H4.312",
                "Room H4.312 has a beamer and a whiteboard, but no windows that can open. The room has space for appr. 15 people."
                );
        classrooms[1]=g;

        g=new classRooms("H4.318",
                "Room H4.318 has a beamer, but no whiteboard, neither windows that can open. The room has space for appr. 10 people."
              );
        classrooms[2]=g;
        return classrooms;
    }
}
