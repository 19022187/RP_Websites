package sg.edu.rp.c346.id19022187.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spn1;
    Spinner spn2;
    Button btnGo;
    ArrayList<String> alSubCat;
    ArrayAdapter<String> aaSubCat;

    String[][] sites = {
            {
                    "https://www.rp.edu.sg/",
                    "https://www.rp.edu.sg/student-life",
            },
            {
                    "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                    "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",
            }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn1 = findViewById(R.id.spinnerCat);
        spn2 = findViewById(R.id.spinnerSubCat);
        btnGo = findViewById(R.id.buttonGo);

        alSubCat = new ArrayList<>();
        String[] strCat = getResources().getStringArray(R.array.subcategory_rp);
        alSubCat.addAll(Arrays.asList(strCat));

        aaSubCat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alSubCat);
        spn2.setAdapter(aaSubCat);

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alSubCat.clear();
                switch (position){
                    case 0:
                        String[] evenNumbers = getResources().getStringArray(R.array.subcategory_rp);
                        alSubCat.addAll(Arrays.asList(evenNumbers));
                        spn2.setSelection(2);
                        aaSubCat.notifyDataSetChanged();
                        break;
                    case 1:
                        String[] oddNumbers = getResources().getStringArray(R.array.subcategory_soi);
                        alSubCat.addAll(Arrays.asList(oddNumbers));
                        spn2.setSelection(0);
                        aaSubCat.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = sites[spn1.getSelectedItemPosition()][spn2.getSelectedItemPosition()];

                Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
                myIntent.putExtra("url", url);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        int menuPos = spn1.getSelectedItemPosition();
        int submenuPos = spn2.getSelectedItemPosition();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putInt("menu_position", menuPos);
        prefEdit.putInt("submenu_position", submenuPos);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        int menuPos = prefs.getInt("menu_position", 0);
        int submenuPos = prefs.getInt("submenu_position", 0);

        spn1.setSelection(menuPos);

        alSubCat.clear();
        if (menuPos == 0){
            String[] strSubMenu = getResources().getStringArray(R.array.subcategory_rp);
            alSubCat.addAll(Arrays.asList(strSubMenu));
        }
        else if (menuPos == 1){
            String[] strSubMenu = getResources().getStringArray(R.array.subcategory_soi);
            alSubCat.addAll(Arrays.asList(strSubMenu));
        }
        spn2.setSelection(submenuPos);
        aaSubCat.notifyDataSetChanged();
    }
}