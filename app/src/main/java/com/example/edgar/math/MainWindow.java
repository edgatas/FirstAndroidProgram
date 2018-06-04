package com.example.edgar.math;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import static android.view.View.*;

public class MainWindow extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private int[] numbers;
    private EditText text;
    private EditText answer;
    private Button generatorClick;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        numbers = new int[4];


        text = (EditText) findViewById(R.id.showMath);
        answer = (EditText) findViewById(R.id.answer);
        submit = (Button) findViewById(R.id.submitAnswer);
        generatorClick = (Button) findViewById(R.id.generate);

        generatorClick.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view) {
                numberGenerator();
            }
        });


        submit.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String result = answer.getText().toString();
                System.out.println(result);
                int convertAnswer = 0;

                try
                {
                    System.out.println("Trying to convert");
                    convertAnswer = Integer.parseInt(result);
                    System.out.println(numbers[3] + " == " + convertAnswer);
                    if (numbers[3] == convertAnswer)
                    {
                        answer.setText("");
                        answer.setBackgroundColor(Color.WHITE);
                        numberGenerator();
                    }
                    else
                    {
                        answer.setBackgroundColor(Color.YELLOW);
                    }
                }
                catch (Exception exp)
                {
                    answer.setBackgroundColor(Color.RED);
                }


            }
        });


    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    private void numberGenerator()
    {
        Random generator = new Random();

        numbers[3] = generator.nextInt(4);
        switch (numbers[3])
        {
            case 0:     // Addition
                numbers[0] = generator.nextInt(1000);
                numbers[1] = generator.nextInt(1000);
                numbers[3] = numbers[0] + numbers[1];
                text.setText(String.format("%d + %d", numbers[0], numbers[1]));
                //equation.Text = numbers[0].ToString() + " + " + numbers[1].ToString();
                break;

            case 1:     // Subtraction
                numbers[0] = generator.nextInt(1000);
                numbers[1] = generator.nextInt(1000);
                numbers[3] = numbers[0] - numbers[1];
                text.setText(String.format("%d - %d", numbers[0], numbers[1]));
                //equation.Text = numbers[0].ToString() + " - " + numbers[1].ToString();
                break;

            case 2:     // Multiplication
                numbers[0] = generator.nextInt(33);
                numbers[1] = generator.nextInt(33);
                numbers[3] = numbers[0] * numbers[1];
                text.setText(String.format("%d * %d", numbers[0], numbers[1]));
                //equation.Text = numbers[0].ToString() + " * " + numbers[1].ToString();
                break;

            case 3:     // Division
                numbers[0] = generator.nextInt(1000);
                numbers[1] = generator.nextInt(100);
                while (numbers[0] % numbers[1] != 0)
                {
                    numbers[0] = generator.nextInt(1000);
                    numbers[1] = generator.nextInt(98) + 2;
                }
                numbers[3] = numbers[0] / numbers[1];

                text.setText(String.format("%d / %d", numbers[0], numbers[1]));
                //equation.Text = numbers[0].ToString() + " / " + numbers[1].ToString();
                break;

        }
        System.out.println(numbers[3]);
    }
}
