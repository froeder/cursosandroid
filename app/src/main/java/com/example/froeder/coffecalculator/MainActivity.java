package com.example.froeder.coffecalculator;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb ;
    EditText txtCafe , txtCodigo;
    TextView txtBanco, txtTeste ;

    Button btnClick , btnClickLer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(MainActivity.this);
        txtCafe = (EditText) findViewById(R.id.txtCafe);
        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtBanco = (TextView) findViewById(R.id.textViewBanco);
        btnClick = (Button) findViewById(R.id.btnCalcular);
        btnClickLer = (Button) findViewById(R.id.btnLer) ;
        btnClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ClickMe();
            }
        });

        btnClickLer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ClickMeLer();
            }
        });
    }

    private void ClickMe(){
        String textoCafe = txtCafe.getText().toString();
        int cafe = Integer.parseInt(textoCafe);
        //txtTeste.setText(getText(cafe));
        Boolean result = myDb.insertData(textoCafe);
        if(result == true){
            Toast.makeText(MainActivity.this, "Dado inserido" , Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Dado não inserido" , Toast.LENGTH_SHORT).show();
        }
    }

    private void ClickMeLer(){
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if(res != null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("ID: "+res.getString(0)+"\n");
                stringBuffer.append("NAME: "+res.getString(1)+"\n");
            }
            txtBanco.setText(stringBuffer.toString());
            Toast.makeText(MainActivity.this, "Todos dados lidos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this , "Falha no erro", Toast.LENGTH_SHORT).show();
        }
    }

}
