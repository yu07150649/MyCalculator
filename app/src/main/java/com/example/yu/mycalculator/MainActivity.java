package com.example.yu.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editT;
    private RadioButton radioMan;
    private RadioButton radioWoman;
    private Button button;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editT = (EditText) findViewById(R.id.weight);
        radioMan = (RadioButton) findViewById(R.id.man);
        radioWoman = (RadioButton) findViewById(R.id.woman);
        button = (Button) findViewById(R.id.cal);
        tv1 = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void showMessage(String messages){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(messages);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    //计算标准身高
    public double evaluateHeight(Double weight, String sex){
        double height;
        if(sex=="男"){
            height = 170-(62-weight)/0.6;
        }else{
            height = 158-(52-weight)/0.6;
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1 :
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void registerEvent(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editT.getText().toString().trim().equals("")){
                    if(radioMan.isChecked()||radioWoman.isChecked()){
                        Double weight = Double.parseDouble(editT.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        if(radioMan.isChecked()){
                            sb.append("男性标准身高为：");
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米");
                        }
                        if(radioWoman.isChecked()){
                            sb.append("女性标准身高为：");
                            double result = evaluateHeight(weight,"女");
                            sb.append((int)result+"厘米");
                        }
                        tv1.setText(sb.toString());
                    }else{
                        showMessage("请选择性别");
                    }
                }else{
                    showMessage("请输入体重");
                }
            }
        });

    }
}
