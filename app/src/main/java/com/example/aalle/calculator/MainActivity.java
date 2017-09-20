package com.example.aalle.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Integer firstValue;
    Integer secondValue;
    String operation;
    String lastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void clickOnButton(View view) {
        Button button = (Button) view;
        switch (button.getText().toString()) {
            case "C":
                firstValue = null;
                secondValue = null;
                operation = null;
                editText.setText("");
                break;
            case "<-":
                if (!editText.getText().toString().equals("")) {
                    editText.setText(editText.getText().toString().substring(0,editText.getText().length()-1));
                }
                if (secondValue != null) {
                    secondValue = secondValue/10;
                    if (secondValue == 0) {
                        secondValue = null;
                    }
                }
                break;
            case "+":
                if (valueIsNotNullAndNotOperation()) {
                    firstValue = Integer.valueOf(editText.getText().toString());
                    operation = "+";
                    editText.setText(editText.getText().toString() + button.getText());
                }
                break;
            case "-":
                if (valueIsNotNullAndNotOperation()) {
                    firstValue = Integer.valueOf(editText.getText().toString());
                    operation = "-";
                    editText.setText(editText.getText().toString() + button.getText());
                }
                break;
            case "*":
                if (valueIsNotNullAndNotOperation()) {
                    firstValue = Integer.valueOf(editText.getText().toString());
                    operation = "*";
                    editText.setText(editText.getText().toString() + button.getText());
                }
                break;
            case "/":
                if (valueIsNotNullAndNotOperation()) {
                    firstValue = Integer.valueOf(editText.getText().toString());
                    operation = "/";
                    editText.setText(editText.getText().toString() + button.getText());
                }
                break;
            case "=":
                if (operation != null) {
                    switch (operation) {
                        case "+":
                            editText.setText(String.valueOf((firstValue + secondValue)));
                            break;
                        case "-":
                            editText.setText(String.valueOf((firstValue - secondValue)));
                            break;
                        case "*":
                            editText.setText(String.valueOf((firstValue * secondValue)));
                            break;
                        case "/":
                            if (secondValue != 0) {
                                editText.setText(String.valueOf((firstValue / secondValue)));
                            } else {
                                editText.setText("На ноль делить нельзя");
                            }
                            break;
                        default:
                            break;
                    }
                    firstValue = null;
                    secondValue = null;
                    operation = null;
                    lastResult = editText.getText().toString();
                }
                break;
            default:
                if (operation != null) {
                    if (secondValue != null)
                        secondValue = Integer.valueOf((String.valueOf(secondValue) + button.getText().toString()));
                    else secondValue = Integer.valueOf(button.getText().toString());
                }
                editText.setText(editText.getText().toString() + button.getText());
                break;
        }
    }

    public boolean valueIsNotNullAndNotOperation(){
        if (!editText.getText().toString().equals("")){
            String currentOperation = editText.getText().toString().substring(editText.getText().length()-1,editText.getText().length());
            if (currentOperation != "+" && currentOperation != "-" && currentOperation != "/" && currentOperation != "*"){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void changeActivity(View view){
        Intent intent = new Intent(this, TwoActivity.class);
        if (lastResult == null) {
            lastResult = "Вычислений не производилось";
        }
        intent.putExtra("lastResult", lastResult);
        startActivity(intent);
    }
}
