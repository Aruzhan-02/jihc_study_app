package com.example.jihc_study.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.jihc_study.R;
import com.example.jihc_study.authentication.LoginPage;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    CardView focusTimer, lessonFiles, schedule, homeWorks, quizSchedule, teachersList;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        focusTimer = (CardView) findViewById(R.id.focus_timer);
        lessonFiles = (CardView) findViewById(R.id.lesson_files);
        schedule = (CardView) findViewById(R.id.schedule);
        homeWorks = (CardView) findViewById(R.id.homeworks);
        quizSchedule = (CardView) findViewById(R.id.quiz_schedules);
        teachersList = (CardView) findViewById(R.id.teachers);


        focusTimer.setOnClickListener(this);
        lessonFiles.setOnClickListener(this);
        schedule.setOnClickListener(this);
        homeWorks.setOnClickListener(this);
        quizSchedule.setOnClickListener(this);
        teachersList.setOnClickListener(this);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar(). setDisplayShowTitleEnabled(false);

        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_schedule:
                Intent intent = new Intent(Dashboard.this,SchedulePage.class);
                startActivity(intent);
                break;

            case R.id.nav_hws:
                Intent intent1 = new Intent(Dashboard.this, Homeworks.class);
                startActivity(intent1);
                break;

            case R.id.nav_files:
                Intent intent2 = new Intent(Dashboard.this, Lesson_files.class);
                startActivity(intent2);
                break;

            case R.id.nav_timer:
                Intent intent3 = new Intent(Dashboard.this, Focus_timer.class);
                startActivity(intent3);
                break;

            case R.id.nav_quiz:
                Intent intent4 = new Intent(Dashboard.this, Quiz_schedules.class);
                startActivity(intent4);
                break;

            case R.id.nav_teachers:
                Intent intent5 = new Intent(Dashboard.this, TeachersList.class);
                startActivity(intent5);
                break;

            case R.id.nav_profile:
                Intent intent6 = new Intent(Dashboard.this, ProfilePage.class);
                startActivity(intent6);
                break;

            case R.id.nav_logout:
                Intent intent7 = new Intent(Dashboard.this, LoginPage.class);
                startActivity(intent7);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.schedule:
                intent = new Intent(this, SchedulePage.class);
                startActivity(intent);
                break;
            case R.id.homeworks:
                intent = new Intent(this, Homeworks.class);
                startActivity(intent);
                break;
            case R.id.lesson_files:
                intent = new Intent(this, Lesson_files.class);
                startActivity(intent);
                break;
            case R.id.focus_timer:
                intent = new Intent(this, Focus_timer.class);
                startActivity(intent);
                break;
            case R.id.quiz_schedules:
                intent = new Intent(this, Quiz_schedules.class);
                startActivity(intent);
                break;
            case R.id.teachers:
                intent = new Intent(this, TeachersList.class);
                startActivity(intent);
                break;
        }
    }
}