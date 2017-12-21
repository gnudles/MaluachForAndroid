package besiyata.maluach;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import besiyata.YDate.YDateLanguage;

/**
 * Created by Orr Dvori on 8/11/2017.
 */

public class PsalmsActivity extends Activity {
    int current_chapter;
    Menu psalm_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.psalms_layout);
        Typeface font = Typeface.createFromAsset(getAssets(), "SILEOT.ttf");

        ((TextView) findViewById(R.id.textViewPsalms)).setTypeface(font);


        ((TextView) findViewById(R.id.textViewPsalms)).setMovementMethod(new ScrollingMovementMethod());

    }


    void setCurrentChapter(int a)
    {
        if (a>150)
            a=150;
        if (a<1)
            a=1;
        current_chapter = a;
        ((TextView) findViewById(R.id.textViewPsalms)).setText(getPsalmText(current_chapter), TextView.BufferType.SPANNABLE);
        ((TextView) findViewById(R.id.textViewPsalms)).scrollTo(0,0);
        //((Button) findViewById(R.id.psalm_chapter)).setText(YDateLanguage.getLanguageEngine(YDateLanguage.Language.HEBREW).getNumber(current_chapter));
        if (psalm_menu != null)
            (psalm_menu.findItem(R.id.psalm_chapter_menu_item)).setTitle(YDateLanguage.getLanguageEngine(YDateLanguage.Language.HEBREW).getNumber(current_chapter));


    }
    public void nextChapter(View v)
    {
        setCurrentChapter(current_chapter+1);
    }
    public void prevChapter(View v)
    {
        setCurrentChapter(current_chapter-1);
    }
    private void fixNumberPickerBug(NumberPicker np)
    {
        Field f = null;
        try {
            f =NumberPicker.class.getDeclaredField("mInputText");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);

        EditText et= null;
        try {
            et = (EditText)f.get(np);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        et.setFilters(new InputFilter[0]);
    }
    public void openSelector(View v)
    {
        AlertDialog.Builder h=new AlertDialog.Builder(this);
        final NumberPicker np=new NumberPicker(this);
        np.setMinValue(1);
        np.setMaxValue(150);
        np.setValue(current_chapter);
        np.setWrapSelectorWheel(false);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return YDateLanguage.getLanguageEngine(YDateLanguage.Language.HEBREW).getNumber(value);
            }
        });
        fixNumberPickerBug(np);
        h.setView(np);
        h.setPositiveButton(R.string.choose, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setCurrentChapter(np.getValue());
            }
        });
        //h.setMessage("בחר פרק");
        h.setTitle("תהלים");
        h.create().show();
    }
    public void zoomIn(View v)
    {
        float textSize= ((TextView) findViewById(R.id.textViewPsalms)).getTextSize();
        if (textSize<50) {
            ((TextView) findViewById(R.id.textViewPsalms)).setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize + 4);
        }
    }
    public void zoomOut(View v)
    {
        float textSize= ((TextView) findViewById(R.id.textViewPsalms)).getTextSize();
        if (textSize>18) {
            ((TextView) findViewById(R.id.textViewPsalms)).setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize - 4);
        }
    }
    Spanned getPsalmText(int chapter)
    {
        try {
            InputStream inputStream = getAssets().open("psalms/" + String.valueOf(chapter) + ".txt");
            byte [] b = new byte[inputStream.available()];
            inputStream.read(b);
            String outputHtml=new String(b).replace("-","־").replace("{","<small><font color='#0077bb'>").replace("}","</font></small>").
                    replace("(","<small><font color='#888888'>").replace(")","</font></small>");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(outputHtml,Html.FROM_HTML_MODE_COMPACT);
            }
            else
            {
                return Html.fromHtml(outputHtml);
            }
        }
        catch (IOException e)
        {
            return Html.fromHtml("");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.psalms_menu,menu);
        psalm_menu = menu;

        setCurrentChapter(1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.psalm_chapter_menu_item:
                openSelector(null);
                return true;
            case R.id.next_psalm_item:
                nextChapter(null);

                return true;
            case R.id.prev_psalm_item:
                prevChapter(null);
                return true;
            case R.id.scale_up_font_item:
                zoomIn(null);
                return true;
            case R.id.scale_down_font_item:
                zoomOut(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
