package com.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kalkulator.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private TextView Screen;
    private TextView HistoryText;
    private Button AC, Pow, Sqrt, Div, Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Multiple, Minus, Plus, Del, Point, Result;
    private String input = "";
    List<List<String>> historyAll = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        Screen = findViewById(R.id.screen);
        HistoryText = findViewById(R.id.history);
        AC = findViewById(R.id.ac);
        Pow = findViewById(R.id.power);
        Sqrt = findViewById(R.id.square);
        Div = findViewById(R.id.div);
        Multiple = findViewById(R.id.multiply);
        Minus = findViewById(R.id.minus);
        Plus = findViewById(R.id.plus);
        Zero = findViewById(R.id.zero);
        One = findViewById(R.id.one);
        Two = findViewById(R.id.two);
        Three = findViewById(R.id.three);
        Four = findViewById(R.id.four);
        Five = findViewById(R.id.five);
        Six = findViewById(R.id.six);
        Seven = findViewById(R.id.seven);
        Eight = findViewById(R.id.eight);
        Nine = findViewById(R.id.nine);
        Del = findViewById(R.id.del);
        Point = findViewById(R.id.point);
        Result = findViewById(R.id.result);
    }

    public void ButtonClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data) {
            case "AC":
                input = "";
                break;
            case "9":
                input += "9";
                break;
            case "8":
                input += "8";
                break;
            case "7":
                input += "7";
                break;
            case "6":
                input += "6";
                break;
            case "5":
                input += "5";
                break;
            case "4":
                input += "4";
                break;
            case "3":
                input += "3";
                break;
            case "2":
                input += "2";
                break;
            case "1":
                input += "1";
                break;
            case "0":
                input += "0";
                break;
            case ".":
                input += ".";
                break;
            case "=":
                List<String> history = new ArrayList<>();
                history.add(input);
                history.add("=");
                Solve();
                history.add(input);
                if (historyAll.size() > 9) {
                    historyAll.remove(0);
                    historyAll.add(history);
                } else
                    historyAll.add(history);
                break;
            case "Del":
                String newText = input.substring(0, input.length() - 1);
                input = newText;
                break;
            default:
                if (input == null) {
                    input = "";
                } else if (data.equals("+") || data.equals("-") || data.equals("/") || data.equals("*") || data.equals("^") || data.equals("\u221a")) {
                    if (data.equals("\u221a")) {
                        SolveSqrt();
                    } else {
                        Solve();
                        input += data;
                    }
                }
        }
        Screen.setText(input);
        History();
    }

    private void Solve() {
        if (input.split("\\*").length == 2) {
            String number[] = input.split("\\*");
            double multiple = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
            input = multiple + "";
        } else if (input.split("/").length == 2) {
            String number[] = input.split("/");
            double divide = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
            input = divide + "";
        } else if (input.split("\\^").length == 2) {
            String number[] = input.split("\\^");
            double power = Math.pow(Double.parseDouble(number[0]), Double.parseDouble(number[1]));
            input = power + "";
        } else if (input.split("\\+").length == 2) {
            String number[] = input.split("\\+");
            double sum = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
            input = sum + "";
        } else if (input.split("\\-").length == 2) {
            String number[] = input.split("\\-");
            double subtraction = 0;
            if (number.length == 2) {
                subtraction = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
            } else if (number.length == 3) {
                subtraction = -Double.parseDouble(number[1]) - Double.parseDouble(number[3]);
            }
            input = subtraction + "";
        }
        // usuwanie ostatniego 0 z liczb typu int
        String n[] = input.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                input = n[0];
            }
        }
        Screen.setText(input);
    }

    private void SolveSqrt() {
        if (input.split("\\u221a").length == 1) {
            String number[] = input.split("\\u221a");
            double sqrt = Math.sqrt(Double.parseDouble(number[0]));
            input = sqrt + "";
        }
        String n[] = input.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                input = n[0];
            }
        }
        Screen.setText(input);
    }

    private void History() {
        String text = "";
        for (List<String> i : historyAll) {
            for (String j : i) {
                text += j;
            }
            text += "\n";
        }
        HistoryText.setMovementMethod(new ScrollingMovementMethod());
        HistoryText.setText(text);
    }
}