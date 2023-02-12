/* Daniel Brown
 * GPA Calculator
 * 2023-02-12
 */

package com.example.gpa_brownd13_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private boolean clearForms = false; // toggle for ComputeGPU or ClearForms action

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set text watchers for all fields to handle edits after compute
        EditText eText1 = (EditText) findViewById(R.id.textInput1);
        eText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (clearForms) setComputeGPA(); // Only update state/button once as needed
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditText eText2 = (EditText) findViewById(R.id.textInput2);
        eText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (clearForms) setComputeGPA();
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditText eText3 = (EditText) findViewById(R.id.textInput3);
        eText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (clearForms) setComputeGPA();
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditText eText4 = (EditText) findViewById(R.id.textInput4);
        eText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (clearForms) setComputeGPA();
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditText eText5 = (EditText) findViewById(R.id.textInput5);
        eText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (clearForms) setComputeGPA();
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
    }

    // Handle orientation changes while running.
    /* This ended up not being required at all.
     * Initial attempt to handle orientation change with full redraw
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main );
        }
    }*/

    public void compute_GPA(View view){
        float grade = 0;
        // Create Layout object for dynamically setting background color
        final LinearLayout linLayoutMain = findViewById(R.id.llMainAct);
        if (clearForms) {     // clear forms and revert to initial state
            EditText eText = findViewById(R.id.textInput1);
            eText.setText("");
            eText = findViewById(R.id.textInput2);
            eText.setText("");
            eText = findViewById(R.id.textInput3);
            eText.setText("");
            eText = findViewById(R.id.textInput4);
            eText.setText("");
            eText = findViewById(R.id.textInput5);
            eText.setText("");
            setComputeGPA();  // Set state for next round of input and compute
            TextView textView = (TextView) findViewById(R.id.GPAcalc);
            textView.setText(R.string.GPAcalc);
            linLayoutMain.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {  // Compute GPA and update display
            try {
                // Sum grades from all input fields and compute average
                // Parse text input, and check for empty in helper methods
                grade += getFloatFromEditText(R.id.textInput1);
                grade += getFloatFromEditText(R.id.textInput2);
                grade += getFloatFromEditText(R.id.textInput3);
                grade += getFloatFromEditText(R.id.textInput4);
                grade += getFloatFromEditText(R.id.textInput5);
                grade /= 5;

                // Display GPA and update background color
                TextView textView = (TextView) findViewById(R.id.GPAcalc);
                textView.setText("" + grade);
                if (grade >= 80) {
                    linLayoutMain.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
                } else if (grade >= 61) {
                    linLayoutMain.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
                } else {
                    linLayoutMain.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                }
                Button calcButton = findViewById(R.id.computeGPA);
                calcButton.setText(R.string.clearForm);
                clearForms = true;

            } catch (Exception ex) {
                Toast.makeText(this, "Please fill out all grade fields", Toast.LENGTH_SHORT).show();
                //TextView textView = (TextView) findViewById(R.id.GPA);
                //textView.setText("GPA");
            }
        }
    }

    private float getFloatFromEditText(int ti) {
        EditText eText = findViewById(ti);
        if (isEmpty(eText)) throw new RuntimeException("empty");
        return Float.parseFloat("" + eText.getText());
    }
    private boolean isEmpty(EditText et) {
        return et.getText().toString().trim().length() == 0;
    }
    private void setComputeGPA(){
        clearForms = false;
        Button calcButton = findViewById(R.id.computeGPA);
        calcButton.setText(R.string.computeGPA);
    }
}