package com.example.zoobrasov;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Animale extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {


    TextView[] heading = new TextView[20];
    TextView[] arealSiHabitat = new TextView[20];
    TextView[] durataDeViata = new TextView[20];
    TextView[] dimensiune = new TextView[20];
    ImageButton[] arrow = new ImageButton[20];
    LinearLayout[] hiddenView = new LinearLayout[20];
    CardView[] cardView = new CardView[20];

    BottomNavigationView bottomNavigationView;
    ArrayList<AnimalHelperClass> animalList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animale);
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setOnItemSelectedListener(this);


        // Take animal data from database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("animals");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Pentru fiecare copil din baza de date, adaugă datele în ArrayList
                    AnimalHelperClass post = snapshot.getValue(AnimalHelperClass.class);
                    animalList.add(post);
                }
                setValuesToAnimals();
                setFunctionalitiesToCardViews();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        reference.addValueEventListener(postListener);

    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        TextView tv = findViewById(R.id.mamiferetext);
        TextView tv1 = findViewById(R.id.pasaritext);
        TextView tv2 = findViewById(R.id.pestitext);
        TextView tv3 = findViewById(R.id.reptiletext);

        ScrollView sv = findViewById(R.id.scrollview);
        switch (id) {
            case R.id.mamifer:
                sv.scrollTo(0, tv.getTop());
                break;

            case R.id.bird:
                sv.scrollTo(0, tv1.getTop());

                break;

            case R.id.fish:
                sv.scrollTo(0, tv2.getTop());

                break;

            case R.id.reptila:
                sv.scrollTo(0, tv3.getTop());

                break;

        }
        return false;
    }

    public void setValuesToAnimals() {
        heading[0] = findViewById(R.id.heading);
        heading[0].setText(animalList.get(0).name);
        arealSiHabitat[0] = findViewById(R.id.arealSiHabitat);
        arealSiHabitat[0].setText(animalList.get(0).arealSiHabitat);
        dimensiune[0] = findViewById(R.id.dimensiune);
        dimensiune[0].setText(animalList.get(0).dimensiuni);
        durataDeViata[0] = findViewById(R.id.durataDeViata);
        durataDeViata[0].setText(animalList.get(0).durataDeViata);

        int count = 2;

        for (int i = 2; i <= 20; i++) {
            if(i==6) {count = 23;}
            if(i==11) {count = 39;}
            int headingId = getResources().getIdentifier("heading" + count,
                    "id", getPackageName());
            int arealSiHabitatId = getResources().getIdentifier("arealSiHabitat" + i,
                    "id", getPackageName());
            int dimensiuneId = getResources().getIdentifier("dimensiune" + i,
                    "id", getPackageName());
            int durataDeViataId = getResources().getIdentifier("durataDeViata" + i,
                    "id", getPackageName());

            heading[i - 1] = findViewById(headingId);
            heading[i - 1].setText(animalList.get(i - 1).name);
            arealSiHabitat[i - 1] = findViewById(arealSiHabitatId);
            arealSiHabitat[i - 1].setText(animalList.get(i - 1).arealSiHabitat);
            dimensiune[i - 1] = findViewById(dimensiuneId);
            dimensiune[i - 1].setText(animalList.get(i - 1).dimensiuni);
            durataDeViata[i - 1] = findViewById(durataDeViataId);
            durataDeViata[i - 1].setText(animalList.get(i - 1).durataDeViata);
            count++;
        }
    }

    private void setFunctionalitiesToCardViews() {

        cardView[0] = findViewById(R.id.base_cardview);
        hiddenView[0] = findViewById(R.id.hidden_view);
        arrow[0] = findViewById(R.id.arrow_button);
        hiddenView[0].getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        arrow[0].setOnClickListener(view -> {

            if (hiddenView[0].getVisibility() == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(cardView[0], new AutoTransition());
                hiddenView[0].setVisibility(View.GONE);
                arrow[0].setImageResource(R.drawable.ic_baseline_expand_more_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView[0], new AutoTransition());
                hiddenView[0].setVisibility(View.VISIBLE);
                arrow[0].setImageResource(R.drawable.ic_baseline_expand_less_24);
            }
        });

        int count=2;
        for(int i=2;i <= 20;i++) {
            if (i == 6) { count = 23; }
            if (i == 11) { count = 39; }
            int arrowId = getResources().getIdentifier("arrow_button" + count,
                    "id", getPackageName());
            int hiddenViewId = getResources().getIdentifier("hidden_view" + count,
                    "id", getPackageName());
            int cardViewId = getResources().getIdentifier("base_cardview" + count,
                    "id", getPackageName());
            cardView[i - 1] = findViewById(cardViewId);
            hiddenView[i - 1] = findViewById(hiddenViewId);
            arrow[i - 1] = findViewById(arrowId);


            int finalI = i;

            arrow[finalI - 1].setOnClickListener(view -> {

                if (hiddenView[finalI - 1].getVisibility() == View.VISIBLE) {

                    TransitionManager.beginDelayedTransition(cardView[finalI - 1], new AutoTransition());
                    hiddenView[finalI - 1].setVisibility(View.GONE);
                    arrow[finalI - 1].setImageResource(R.drawable.ic_baseline_expand_more_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardView[finalI - 1], new AutoTransition());
                    hiddenView[finalI - 1].setVisibility(View.VISIBLE);
                    arrow[finalI - 1].setImageResource(R.drawable.ic_baseline_expand_less_24);
                }
            });

            count++;
        }
    }
}
