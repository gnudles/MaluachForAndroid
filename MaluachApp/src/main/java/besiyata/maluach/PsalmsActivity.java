package besiyata.maluach;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import besiyata.YDate.YDateLanguage;

/**
 * Created by Orr Dvori on 8/11/2017.
 */

public class PsalmsActivity extends Activity {
    int current_chapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //super.attachBaseContext(MyContextWrapper.wrap(getBaseContext(),"he"));
        setContentView(R.layout.psalms_layout);
        Typeface font = Typeface.createFromAsset(getAssets(), "SILEOT.ttf");

        ((TextView) findViewById(R.id.textViewPsalms)).setTypeface(font);
        setCurrentChapter(1);

        ((TextView) findViewById(R.id.textViewPsalms)).setMovementMethod(new ScrollingMovementMethod());
    }
    void setCurrentChapter(int a)
    {
        if (a>150)
            a=150;
        if (a<1)
            a=1;
        current_chapter = a;
        ((TextView) findViewById(R.id.textViewPsalms)).setText(getPsalmText(current_chapter));
        ((TextView) findViewById(R.id.textViewPsalms)).scrollTo(0,0);
        ((TextView) findViewById(R.id.psalm_chapter)).setText(YDateLanguage.getLanguageEngine(YDateLanguage.Language.ENGLISH).getNumber(current_chapter));
    }
    public void nextChapter(View v)
    {
        setCurrentChapter(current_chapter+1);
    }
    public void zoomIn(View v)
    {
        float textSize= ((TextView) findViewById(R.id.textViewPsalms)).getTextSize();
        if (textSize<40) {
            ((TextView) findViewById(R.id.textViewPsalms)).setTextSize(textSize + 4);
        }
    }
    public void zoomOut(View v)
    {
        float textSize= ((TextView) findViewById(R.id.textViewPsalms)).getTextSize();
        if (textSize>14) {
            ((TextView) findViewById(R.id.textViewPsalms)).setTextSize(textSize - 4);
        }
    }
    String getPsalmText(int chapter)
    {
        try {
            InputStream inputStream = getAssets().open("psalms/" + String.valueOf(chapter) + ".txt");
            byte [] b = new byte[inputStream.available()];
            inputStream.read(b);
            return new String(b).replace('{','(').replace('}',')');
        }
        catch (IOException e)
        {
            return "";
        }
    }
}
