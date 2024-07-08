package com.example.graduate_work_bachelor_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityMainPageAdmin extends AppCompatActivity {

    private Button btnRoutes, btnSchedule, btnUsers, btnTickets;
    private Button btnRoutesBus, btnRoutesTrain, btnRoutesAirplane;
    private LinearLayout routeSubMenu, scheduleSubMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_admin);
        LinearLayout openSearchTicket = findViewById(R.id.open_SearchTicket);
        LinearLayout openMyTickets = findViewById(R.id.open_MyTickets);
        btnRoutes = findViewById(R.id.btnRoutes);
        btnSchedule = findViewById(R.id.btnSchedule);
        btnUsers = findViewById(R.id.btnUsers);

        routeSubMenu = findViewById(R.id.routeSubMenu);
        scheduleSubMenu = findViewById(R.id.scheduleSubMenu);
        btnTickets = findViewById(R.id.btnTickets);
        btnRoutesBus = findViewById(R.id.btnRoutesBus);
        btnRoutesTrain = findViewById(R.id.btnRoutesTrain);
        btnRoutesAirplane = findViewById(R.id.btnRoutesAirplane);

        btnRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(routeSubMenu);
            }
        });

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility(scheduleSubMenu);
            }
        });

        btnRoutesBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRouteActivity("bus");
            }
        });

        btnRoutesTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRouteActivity("train");
            }
        });

        btnRoutesAirplane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRouteActivity("plane");
            }
        });
        openSearchTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainPageAdmin.this, ActivityMainPageUser.class);
                startActivity(intent);
            }
        });
        openMyTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainPageAdmin.this, ActivityMyTickets.class);
                startActivity(intent);
            }
        });
        btnTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainPageAdmin.this, TicketsAdminList.class);
                startActivity(intent);
            }
        });
    }

    private void toggleVisibility(final LinearLayout subMenu) {
        if (subMenu.getVisibility() == View.GONE) {
            subMenu.setVisibility(View.VISIBLE);
            subMenu.setAlpha(0f);
            subMenu.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(null);
        } else {
            subMenu.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            subMenu.setVisibility(View.GONE);
                        }
                    });
        }
    }

    private void openRouteActivity(String transportType) {
        Intent intent = new Intent(ActivityMainPageAdmin.this, ActivityRoute.class);
        intent.putExtra("TRANSPORT_TYPE", transportType);
        startActivity(intent);
    }
}
