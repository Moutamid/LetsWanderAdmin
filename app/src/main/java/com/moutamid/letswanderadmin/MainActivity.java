package com.moutamid.letswanderadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference databaseReference = Constants.databaseReference().child("Markers");

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from EditText fields
                String title = ((EditText) findViewById(R.id.editTextTitle)).getText().toString();
                Double latitude = Double.valueOf(String.valueOf(((EditText) findViewById(R.id.editTextLatitude)).getText()));
                Double longitude = Double.valueOf(String.valueOf(((EditText) findViewById(R.id.editTextLongitude)).getText()));
                String description = ((EditText) findViewById(R.id.editTextDescription)).getText().toString();
                boolean isStar = ((Switch) findViewById(R.id.switchIsStar)).isChecked();

                // Create a Location object
                addLocation location = new addLocation(latitude, longitude, title, description, isStar);

                // Push the location data to Firebase Realtime Database
                databaseReference.child(location.getMarkerId()).setValue(location);

                // Optionally, clear the input fields
                clearInputFields();
            }
        });

    }

    private void clearInputFields() {
        EditText editTextTitle = findViewById(R.id.editTextTitle);
        EditText editTextLatitude = findViewById(R.id.editTextLatitude);
        EditText editTextLongitude = findViewById(R.id.editTextLongitude);
        EditText editTextDescription = findViewById(R.id.editTextDescription);
        Switch switchIsStar = findViewById(R.id.switchIsStar);

        editTextTitle.setText("");
        editTextLatitude.setText("");
        editTextLongitude.setText("");
        editTextDescription.setText("");
        switchIsStar.setChecked(false);
    }


}