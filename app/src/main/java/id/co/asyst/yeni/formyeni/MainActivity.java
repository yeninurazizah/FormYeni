package id.co.asyst.yeni.formyeni;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import id.co.asyst.yeni.formyeni.utility.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    //edit text
    Button buttonTombol;
    EditText firstEditText;
    String firstString;
    TextView firstTextView;

    //radio group
    RadioGroup genderRadioGroup;
    String selectGender = "Male";

    //checkbox
    String nasigoreng = "";
    String mieayam = "";
    String baksosapi = "";
    CheckBox nasGor, baksoSapi, mieAyam;
    ArrayList<String> listFood = new ArrayList<String>();
    String foods = "";

    //spinner
    Spinner citySpinner;
    String selectedCity;
    ArrayList<String> listCity = new ArrayList<String>();

    //switch dan toggle
    Switch switchFirst;
    ToggleButton toggleButtonFirst;

    /*EditText editAngka1;
    EditText editAngka2;
    Button btnTambah;
    Button btnKurang;
    Button btnKali;
    Button btnBagi;
    TextView textViewHasil;
    String x, y;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_first);

        //edit text
        buttonTombol = findViewById(R.id.button_tombol);
        firstEditText = findViewById(R.id.edit_text1);
        firstTextView = findViewById(R.id.first_text);

        //radio button
        genderRadioGroup = findViewById(R.id.radio_gender);
        genderRadioGroup.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_male)).setChecked(true);

        //checkbox
        buttonTombol.setOnClickListener(this);
        nasGor = findViewById(R.id.nasgor);
        baksoSapi = findViewById(R.id.bakso);
        mieAyam = findViewById(R.id.mieayam);

        nasGor.setOnCheckedChangeListener(this);
        baksoSapi.setOnCheckedChangeListener(this);
        mieAyam.setOnCheckedChangeListener(this);

        //spinner - pake array
        citySpinner = findViewById(R.id.spinner);
        citySpinner.setOnItemSelectedListener(this);

        listCity.add("Jember");
        listCity.add("Malang");
        listCity.add("Blitar");
        listCity.add("Banyuwangi");

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listCity);
        citySpinner.setAdapter(cityAdapter);

        //switch and toggle - sama kayak checkbox
        switchFirst = findViewById(R.id.switch_first);
        toggleButtonFirst = findViewById(R.id.togglebutton_first);

        switchFirst.setOnCheckedChangeListener(this);
        toggleButtonFirst.setOnCheckedChangeListener(this);


       /* editAngka1 = findViewById(R.id.angka1);
        editAngka2 = findViewById(R.id.angka2);
        btnTambah = findViewById(R.id.btn_tambah);
        btnKurang = findViewById(R.id.btn_kurang);
        btnKali = findViewById(R.id.btn_kali);
        btnBagi = findViewById(R.id.btn_bagi);
        textViewHasil = findViewById(R.id.text_view_hasil);

        btnTambah.setOnClickListener(this);
        btnKurang.setOnClickListener(this);
        btnKali.setOnClickListener(this);
        btnBagi.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_tombol:
                firstString = firstEditText.getText().toString();
                if (!TextUtils.isEmpty(firstString)) {

                    foods = "";
                    for (int i = 0; i < listFood.size(); i++) {
                        foods = foods + " " + listFood.get(i);
                    }

//                    firstTextView.setText("Welcome "  + firstString + ", \nYou Are " + selectGender + ", \nYour favorite foods are " +foods+", \nYour city is "+selectedCity);

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Confirmation")
                            .setCancelable(false)
                            .setMessage("Are You Sure ?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                                    String result = "Welcome " + firstString + ", \nYou Are " + selectGender + ", \nYour favorite foods are " + foods + ", \nYour city is " + selectedCity;
                                    intent.putExtra(Constant.KEY_RESULT, result);
                                    startActivity(intent);
                                    finish();

                                }
                            })
                            .setNegativeButton("NO", null).show();

//membuat toast
//                    Toast.makeText(getApplicationContext(), "Welcome " + firstString + "" + selectGender, Toast.LENGTH_SHORT).show();
                }
                break;
        }
        /*int id = v.getId();
        switch (id) {

            case R.id.btn_tambah:
                hasilTambah();
            break;
            case R.id.btn_kurang:
                hasilKurang();
            break;
            case R.id.btn_kali:
                hasilKali();
                break;
            case R.id.btn_bagi:
                hasilBagi();
                break;
        }*/
    }

    //radio group
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_male:
                selectGender = "Male";
                break;
            case R.id.radio_female:
                selectGender = "Female";
                break;
        }
    }

    //checkbox
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.nasgor:
                if (isChecked) {
                    listFood.add("Nasi Goreng");
//                    nasigoreng = "Nasi Goreng";
                } else {
                    listFood.remove("Nasi Goreng");
//                    nasigoreng="";
                }

                break;
            case R.id.mieayam:
                if (isChecked) {
                    listFood.add("Mie Ayam");
//                    mieayam = "Mie Ayam";
                } else {
                    listFood.remove("Mie Ayam");
//                    mieayam="";
                }
                break;
            case R.id.bakso:
                if (isChecked) {
                    listFood.add("Bakso Sapi");
//                    baksosapi = "Bakso Sapi";
                } else {
                    listFood.remove("Bakso Sapi");
//                    baksosapi="";
                }
                break;

            case R.id.switch_first:
                Log.d("MainAct testSwitch", String.valueOf(isChecked));
                break;

            case R.id.togglebutton_first:
                Log.d("MainAct testToggle", String.valueOf(isChecked));
                break;
        }
    }

    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCity = citySpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //on nothing, digunakan kalo tidak jadi dipilih
    }

/*    private void hasilTambah() {
        x = editAngka1.getText().toString();
        y = editAngka2.getText().toString();
      if (TextUtils.isEmpty(x)){
          editAngka1.setError("Angka pertama belum diisi");
      }else if(TextUtils.isEmpty(y)){
          editAngka2.setError("Angka kedua belum diisi");
      }else{
          int x = Integer.parseInt(editAngka1.getText().toString());
          int y = Integer.parseInt(editAngka2.getText().toString());
          int hasil = x + y;
          String hasilTambah = Integer.toString(hasil);
          textViewHasil.setText(x +" + " +y+ " = " + hasilTambah);
      }
    }
    private void hasilKurang() {
        x = editAngka1.getText().toString();
        y = editAngka2.getText().toString();
        if (TextUtils.isEmpty(x)){
            editAngka1.setError("Angka pertama belum diisi");
        }else if(TextUtils.isEmpty(y)){
            editAngka2.setError("Angka kedua belum diisi");
        }else{
            int x = Integer.parseInt(editAngka1.getText().toString());
            int y = Integer.parseInt(editAngka2.getText().toString());
            int hasil = x - y;
            String hasilKurang = Integer.toString(hasil);
            textViewHasil.setText(x +" - " +y+ " = " +hasilKurang);
        }
    }
    private void hasilKali() {
        x = editAngka1.getText().toString();
        y = editAngka2.getText().toString();
        if (TextUtils.isEmpty(x)){
            editAngka1.setError("Angka pertama belum diisi");
        }else if(TextUtils.isEmpty(y)){
            editAngka2.setError("Angka kedua belum diisi");
        }else{
            int x = Integer.parseInt(editAngka1.getText().toString());
            int y = Integer.parseInt(editAngka2.getText().toString());
            int hasil = x * y;
            String hasilKali = Integer.toString(hasil);
            textViewHasil.setText(x +" * " +y+ " = " +hasilKali);
        }
    }
    private void hasilBagi() {
        x = editAngka1.getText().toString();
        y = editAngka2.getText().toString();
        if (TextUtils.isEmpty(x)){
            editAngka1.setError("Angka pertama belum diisi");
        }else if(TextUtils.isEmpty(y)){
            editAngka2.setError("Angka kedua belum diisi");
        }else{
            int x = Integer.parseInt(editAngka1.getText().toString());
            int y = Integer.parseInt(editAngka2.getText().toString());
            if (y==0){
                editAngka2.setError("Angka kedua tidak boleh 0");
            }else{
                int hasil = x / y;
                String hasilBagi = Integer.toString(hasil);
                textViewHasil.setText(x +" / " +y+ " = " +hasilBagi);
            }

        }
    }*/
}

