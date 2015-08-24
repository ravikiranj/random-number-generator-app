package rkj.randomnumbergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class RandomNumberActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private Random m_random;

    private Button m_generateButton;
    private TextView m_randomNumberResult;
    private TextView m_randomNumberRangeText;
    private SeekBar m_seekBar;

    private int m_currentSliderValue = 0;

    private static int MIN_LIMIT = 0;
    private static int MAX_LIMIT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);

        m_random = new Random();
        m_randomNumberRangeText = (TextView) findViewById(R.id.randomNumberRangeText);

        m_randomNumberResult = (TextView) findViewById(R.id.randomNumberResult);

        m_generateButton = (Button) findViewById(R.id.generateButton);
        m_generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _updateRandomNumberResult();
            }
        });

        m_seekBar = (SeekBar) findViewById(R.id.numberSeekBar);
        m_seekBar.setOnSeekBarChangeListener(this);
        m_seekBar.setProgress(m_seekBar.getMax());

        // Init random Number
        _updateRandomNumberResult();
    }

    private void _updateRandomNumberResult() {
        String randomStr = String.valueOf(randInt(MIN_LIMIT, m_currentSliderValue));
        m_randomNumberResult.setText(randomStr);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        m_currentSliderValue = _getScaledProgress(seekBar, MIN_LIMIT, MAX_LIMIT);
        m_randomNumberRangeText.setText(_getRangeText(MIN_LIMIT, m_currentSliderValue));
        _updateRandomNumberResult();
    }

    private int _getScaledProgress(SeekBar seekBar, int min, int max) {
        float progressPercent = seekBar.getProgress() / (float) seekBar.getMax();
        return (int) (min + (progressPercent) * (max - min));
    }

    private String _getRangeText(int min, int max) {
        return "Random number range " + min + " - " + max;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public int randInt(int min, int max) {
        return m_random.nextInt((max - min) + 1) + min;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random_number, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
