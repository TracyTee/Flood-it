package com.ho23a.flood_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {
    Spinner levelSpinner;
    String colorSchemeLabel;
    // TODO implement setting color scheme

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setLevelSpinner();

        ((Button) findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void save() {
//        // TODO change MainActivity to WelcomeActivity
//        colorSchemeLabel = "";
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra(SETTINGS_LABEL, settings);
//        startActivity(intent);
    }


    private void setLevelSpinner() {
        levelSpinner = (Spinner) findViewById(R.id.levelSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapter);

        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLevel(
                        (String) parent.getItemAtPosition(position)
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setLevel(String levelLabel) {
        switch(levelLabel) {
            case "Select Level":
                Settings.getIntsance().setLevel(Level.DEFAULT);
                break;
            case "Easy":
                Settings.getIntsance().setLevel(Level.EASY);
                break;
            case "Medium":
                Settings.getIntsance().setLevel(Level.MEDIUM);
                break;
            case "Hard":
                Settings.getIntsance().setLevel(Level.HARD);
                break;
            default:
                break;
        }
    }

}
