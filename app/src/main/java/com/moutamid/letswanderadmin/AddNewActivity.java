package com.moutamid.letswanderadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class AddNewActivity extends AppCompatActivity {

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = ((EditText) findViewById(R.id.editTextTitle)).getText().toString();
                String latitudeStr = ((EditText) findViewById(R.id.editTextLatitude)).getText().toString();
                String longitudeStr = ((EditText) findViewById(R.id.editTextLongitude)).getText().toString();
                String description = ((EditText) findViewById(R.id.editTextDescription)).getText().toString();
                boolean isStar = ((Switch) findViewById(R.id.switchIsStar)).isChecked();

                if (title.isEmpty() || latitudeStr.isEmpty() || longitudeStr.isEmpty() || description.isEmpty()) {
                    Toast.makeText(AddNewActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        double latitude = Double.parseDouble(latitudeStr);
                        double longitude = Double.parseDouble(longitudeStr);


                        DatabaseReference markersRef = Constants.databaseReference().child("Markers");

                        DatabaseReference newLocationRef = markersRef.push();
                        String locationKey = newLocationRef.getKey();
                        addLocation location = new addLocation(locationKey, latitude, longitude, title, description, isStar);

                        newLocationRef.setValue(location);

                        clearInputFields();

                        Toast.makeText(AddNewActivity.this, "Data submitted successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddNewActivity.this, MainActivity.class));
                    } catch (NumberFormatException e) {
                        // Handle invalid latitude or longitude format
                        Toast.makeText(AddNewActivity.this, "Invalid latitude or longitude format", Toast.LENGTH_SHORT).show();
                    }
                }
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
