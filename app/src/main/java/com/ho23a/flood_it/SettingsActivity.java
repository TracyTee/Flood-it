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
    String levelLabel;
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
                save();
                finish();
            }
        });
    }

    public static final String SETTINGS_LABEL = "com.ho23a.flood_it.SETTINGS";
    private void save() {
        // TODO change MainActivity to WelcomeActivity
        colorSchemeLabel = "";
        Settings settings = new Settings(colorSchemeLabel, levelLabel);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(SETTINGS_LABEL, settings);
        startActivity(intent);
    }


    private void setLevelSpinner() {
        levelSpinner = (Spinner) findViewById(R.id.levelSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        levelSpinner.setAdapter(adapter);

        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLevelLabel(
                        (String) parent.getItemAtPosition(position)
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setLevelLabel(String levelLabel) {
        this.levelLabel = levelLabel;
    }

}
