package besiyatasoft.maluach;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.graphics.Typeface;

import besiyatasoft.gp.EventHandler;


public class MainActivity extends Activity {
    EventHandler.Listener _showInfo = new EventHandler.Listener() {
        @Override
        public void process(Object sender) {
            showInfo();
        }
    };
    EventHandler.Listener _updateText = new EventHandler.Listener() {
        @Override
        public void process(Object sender) {
            updateText();
        }
    };
    protected void showInformation(YDate dateCursor)
    {
        String lstr=parasha(dateCursor);
        if (lstr.length()>0)
        {

            lstr+="\n";
        }
        String omer=SfiratOmer(dateCursor);
        lstr+=omer;
        if (omer.length()>0)
            lstr+="\n";
        if (dateCursor.hd.dayInMonth()==1 || dateCursor.hd.dayInMonth()==30)
            lstr+="ראש חדש\n";
        else
        {
            if (dateCursor.hd.dayInMonth()>=23 && dateCursor.hd.dayInWeek()==7)
                lstr+="מברכין החדש\n";
        }
        if (dateCursor.hd.dayInMonth()==16 && dateCursor.hd.monthID()==YDate.JewishDate.M_ID_TISHREI &&
                (dateCursor.hd.ShmitaOrdinal()-1)%7 == 0 )
        {
            lstr+="יום הקהל\n";
        }
        if (TorahReading.getShabbatBereshit(dateCursor.hd.yearLength(),dateCursor.hd.yearFirstDay())+15*7-4 == dateCursor.hd.daysSinceBeginning())
        {
            lstr+="סגולת פרשת המן שניים מקרא ואחד תרגום\n";
        }
        lstr+="שנה "+dateCursor.hd.ShmitaTitle()+"\n";
        int day_tkufa=dateCursor.hd.dayInTkufa();
        int day_mazal=dateCursor.hd.dayInMazal();
        lstr+="יום "+Integer.toString(day_tkufa+1)+" ל" +dateCursor.hd.TkufaName(true)+"\n";
        if (day_tkufa==0)
            lstr+="תחילת תקופה "+dateCursor.hd.TkufaBeginning(new TzDstManager())+"\n";
        if (day_tkufa==59 && dateCursor.hd.TkufaType()==YDate.JewishDate.M_ID_TISHREI)
            lstr+="מתחילים לומר תן טל ומטר בחו\"ל\n";
        if (dateCursor.hd.dayInYear()==36 ) // 7 in chesvan
            lstr+="מתחילים לומר תן טל ומטר בארץ ישראל\n";
        lstr+="יום "+Integer.toString(day_mazal+1)+" ל" +dateCursor.hd.MazalName(true)+"\n";
        if (day_tkufa!=0 && day_mazal == 0)
            lstr+="חילוף מזל "+dateCursor.hd.MazalBeginning(new TzDstManager())+"\n";
        lstr+="ברכת החמה בעוד "+Integer.toString((10227-dateCursor.hd.TkufotCycle())%10227)+" יום\n";
        lstr+="\nדף יומי "+ DailyLimud.getDafYomi(dateCursor.hd.daysSinceBeginning(), true);

        if (lstr.length()>0)
            showAlert(dateCursor.hd.dayString(true), lstr);
    }
    protected void showMolad(YDate dateCursor)
    {
        String lstr;
        lstr=dateCursor.hd.MoladString(new TzDstManager());
        showAlert("מולד הלבנה", lstr);
    }
    private void showAlert(String title,String text)
    {
        AlertDialog.Builder h=new AlertDialog.Builder(this);
        h.setMessage(text);
        h.setTitle(title);
        h.create().show();
    }
    protected void showLimud(YDate dateCursor)
    {
        showAlert("דף יומי", DailyLimud.getDafYomi(dateCursor.hd.daysSinceBeginning(), true));
    }
    protected String parasha(YDate dateCursor)
    {
        String il_parasha=TorahReading.GetTorahReading(dateCursor.hd,false,false);
        String diasp=TorahReading.GetTorahReading(dateCursor.hd,true,false);

        String lstr="";
        if ((diasp.length()==0 || !il_parasha.equals(diasp))&& il_parasha.length()>0)
            lstr+="באה\"ק ";
        lstr+=il_parasha;
        String shabat_str=TorahReading.parshiot4(dateCursor);
        if (shabat_str.length()>0)
            lstr="שבת "+shabat_str+", "+lstr;
        if (diasp.length()>0 && !il_parasha.equals(diasp))
            lstr+="\nבחו\"ל "+diasp;

        return lstr;
    }
    protected String SfiratOmer(YDate dateCursor)
    {
        int om=dateCursor.hd.sfiratHaomer();
        String lstr="";
        if (om>0)
        {
            lstr="היום "+Format.HebIntString(om,true) + " לעמר (מערב אתמול)";

        }
        om+=1;
        if (om<50 && om>0)
        {
            lstr+="\nבערב "+Format.HebIntString(om,true) + " לעמר";
        }
        return lstr;
    }
    private void showInfo()
    {
        showInformation(ydateView.getDateCursor());


    }
    private void updateText()
    {
        txtView.setText(ydateView.getDateCursor().hd.dayString(true));
        txtViewGreg.setText(ydateView.getDateCursor().gd.dayString(true));
        txtViewEvent.setText(ydateView.getDateCursor().yearEvents().getYearEventForDay(ydateView.getDateCursor().hd));
        //textViewTitle.setText(ydateView.getTitle());
        nextMonthBtn.setText(ydateView.getMonthTitle());
        nextYearBtn.setText(ydateView.getYearTitle());
    }

    public void nextMonthClick(View v)
    {
        YDate.STEP_TYPE st=(switchGregHeb.isChecked())?YDate.STEP_TYPE.HEB_MONTH_FORWARD:YDate.STEP_TYPE.GRE_MONTH_FORWARD;
        YDate yd=ydateView.getDateCursor();
        yd.step(st);
        ydateView.dateCursorUpdated();
    }
    public void previousMonthClick(View v)
    {
        YDate.STEP_TYPE st=(switchGregHeb.isChecked())?YDate.STEP_TYPE.HEB_MONTH_BACKWARD:YDate.STEP_TYPE.GRE_MONTH_BACKWARD;
        YDate yd=ydateView.getDateCursor();
        yd.step(st);
        ydateView.dateCursorUpdated();
    }
    public void nextYearClick(View v)
    {
        YDate.STEP_TYPE st=(switchGregHeb.isChecked())?YDate.STEP_TYPE.HEB_YEAR_FORWARD:YDate.STEP_TYPE.GRE_YEAR_FORWARD;
        YDate yd=ydateView.getDateCursor();
        yd.step(st);
        ydateView.dateCursorUpdated();
    }
    public void previousYearClick(View v)
    {
        YDate.STEP_TYPE st=(switchGregHeb.isChecked())?YDate.STEP_TYPE.HEB_YEAR_BACKWARD:YDate.STEP_TYPE.GRE_YEAR_BACKWARD;
        YDate yd=ydateView.getDateCursor();
        yd.step(st);
        ydateView.dateCursorUpdated();
    }
    public void setHebrewBoard(boolean hebrewBoard)
    {
        ydateView.setHebrewMonthAlign(hebrewBoard);
    }
    public void setRTL(boolean RTL)
    {
        ydateView.setRTL(RTL);
    }

    TextView txtView;
    TextView txtViewGreg;
    TextView txtViewEvent;
    //TextView textViewTitle;
    Switch switchGregHeb;
    Switch switchRTL;
    YDateView ydateView;
    Button nextMonthBtn;
    Button nextYearBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //super.attachBaseContext(MyContextWrapper.wrap(getBaseContext(),"he"));
        setContentView(R.layout.activity_main);

        txtViewGreg = (TextView) findViewById(R.id.textViewGreg);
        txtViewEvent = (TextView) findViewById(R.id.textViewEvent);
        //textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        txtView = (TextView) findViewById(R.id.textView);
        switchGregHeb = (Switch) findViewById(R.id.switchHebGreg);
        switchGregHeb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setHebrewBoard(isChecked);
            }
        });
        switchRTL = (Switch) findViewById(R.id.switchRTL);
        switchRTL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setRTL(isChecked);
            }
        });
        nextMonthBtn = (Button) findViewById(R.id.nextMonthBtn);
        nextMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonthClick(v);
            }
        });
        nextYearBtn = (Button) findViewById(R.id.nextYearBtn);
        nextYearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextYearClick(v);
            }
        });
		Typeface font = Typeface.createFromAsset(getAssets(), "SILEOT.ttf");
        txtView.setTypeface(font);

        ydateView = (YDateView) findViewById(R.id.ydateView);
        ydateView.dateClicked().addListener(_showInfo);
        ydateView.dateChanged().addListener(_updateText);
        _updateText.process(null);


    }

}
