package com.example.calculatormobileapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    double firstNum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button ac = findViewById(R.id.ac);
        Button del = findViewById(R.id.del);
        Button period = findViewById(R.id.period);
        Button add = findViewById(R.id.add);
        Button sub = findViewById(R.id.sub);
        Button mul = findViewById(R.id.mul);
        Button div = findViewById(R.id.div);
        Button equal = findViewById(R.id.equal);

        TextView screen = findViewById(R.id.screen);

        ac.setOnClickListener(view -> {
            firstNum = 0;
            screen.setText("0");
        });

        off.setOnClickListener(view -> screen.setVisibility(View.GONE));
        on.setOnClickListener(view -> {
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(findViewById(R.id.num0));
        nums.add(findViewById(R.id.num1));
        nums.add(findViewById(R.id.num2));
        nums.add(findViewById(R.id.num3));
        nums.add(findViewById(R.id.num4));
        nums.add(findViewById(R.id.num5));
        nums.add(findViewById(R.id.num6));
        nums.add(findViewById(R.id.num7));
        nums.add(findViewById(R.id.num8));
        nums.add(findViewById(R.id.num9));

        for (Button b : nums) {
            b.setOnClickListener(view -> {
                String currentText = screen.getText().toString();
                if (!currentText.equals("0")) {
                    screen.setText(currentText + b.getText().toString());
                } else {
                    screen.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> op = new ArrayList<>();
        op.add(findViewById(R.id.add));
        op.add(findViewById(R.id.sub));
        op.add(findViewById(R.id.mul));
        op.add(findViewById(R.id.div));

        for (Button b : op) {
            b.setOnClickListener(view -> {
                try {
                    firstNum = Double.parseDouble(screen.getText().toString());
                    operation = b.getText().toString();
                    screen.setText("0 ");
                } catch (NumberFormatException e) {
                    screen.setText("Error");
                }
            });
        }

        del.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length()>1) {
                screen.setText(num.substring(0, num.length()-1));
            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("0");
            }
        });

        period.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")) {
                screen.setText(screen.getText().toString() + ".");
            }
        });

        equal.setOnClickListener(view -> {
            try {
                double secondNum = Double.parseDouble(screen.getText().toString());
                double result;
                switch (operation) {
                    case "+":
                        result = firstNum + secondNum;
                        break;
                    case "-":
                        result = firstNum - secondNum;
                        break;
                    case "X":
                        result = firstNum * secondNum;
                        break;
                    case "/":
                        if (secondNum != 0) {
                            result = firstNum / secondNum;
                        } else {
                            screen.setText("Error");
                            return;
                        }
                        break;
                    default:
                        screen.setText("Error");
                        return;
                }
                screen.setText(String.valueOf(result));
            } catch (NumberFormatException e) {
                screen.setText("Error");
            }
        });
    }
}