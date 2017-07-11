package besiyata.maluach;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextPaint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

import besiyata.gp.EventHandler;
import besiyata.YDate.*;

/**
 * TODO: document your custom view class.
 */
public class YDateView extends View implements OnGestureListener {

    private EventHandler dateChanged;
    public  EventHandler dateChanged(){
        if (dateChanged == null)
            dateChanged = new EventHandler();
        return dateChanged;
    }
    private EventHandler dateClicked;
    public  EventHandler dateClicked(){
        if (dateClicked == null)
            dateClicked = new EventHandler();
        return dateClicked;
    }
    YDate mDateCursor;
    private GestureDetector mScrollHandler;
    private Scroller mScroller;
    private float mScrollY=0;
    private ValueAnimator mScrollAnimator;
    private TextPaint mTextPaint;
    private boolean mRightToLeft;
    private boolean mHebrewMonthAlign;
    private boolean mUseFullWeekDayNames;
    private int mFrameColor;
    private int mBackgroundColor;
    private int mSelectionColor;
    private int mSelectedCell;
    private int mZeroCellDay;
    private int mHdYearDay;
    private int mFirstDayCell;
    private int mLastMonthLength;
    private int mMonthLength;
    private int mTodayDay;


    public void setRTL(boolean RTL) {
        mRightToLeft = RTL;
        invalidate();
    }
    public void setHebrewMonthAlign(boolean heb)
    {
        mHebrewMonthAlign=heb;
        dateCursorUpdated();
    }

    public YDateView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public YDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public YDateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public YDate getDateCursor() {
        return mDateCursor;
    }
    private void updateSelection(int selection)
    {
        if (selection>=0 && mSelectedCell!=selection)
        {
            mDateCursor.seekBy(selection-mSelectedCell);
            dateCursorUpdated();
        }

    }
    public String getTitle()
    {
        if (mHebrewMonthAlign)
        {
            return mDateCursor.hd.monthName(true) + " " + Format.HebIntString(mDateCursor.hd.year(),true);
        }
        else
            return mDateCursor.gd.monthName(true) + " " + String.valueOf(mDateCursor.gd.year());
    }
    public String getYearTitle()
    {
        if (mHebrewMonthAlign)
        {
            return Format.HebIntString(mDateCursor.hd.year(),true);
        }
        else
            return String.valueOf(mDateCursor.gd.year());
    }
    public String getMonthTitle()
    {
        if (mHebrewMonthAlign)
        {
            return mDateCursor.hd.monthName(true);
        }
        else
            return mDateCursor.gd.monthName(true);
    }
    public void dateCursorUpdated() {
        int month_fisrt_day;
        int day_in_month;
        if (mHebrewMonthAlign)
        {
            month_fisrt_day=mDateCursor.hd.monthFirstDay();
            day_in_month=mDateCursor.hd.dayInMonth();
            mMonthLength=mDateCursor.hd.monthLength();
            mLastMonthLength=mDateCursor.hd.previousMonthLength();
        }
        else
        {
            month_fisrt_day=mDateCursor.gd.monthFirstDay();
            day_in_month=mDateCursor.gd.dayInMonth();
            mMonthLength=mDateCursor.gd.monthLength();
            mLastMonthLength=mDateCursor.gd.previousMonthLength();
        }
        mFirstDayCell=month_fisrt_day%7;
        if (mFirstDayCell<2)
            mFirstDayCell+=7;
        mZeroCellDay=month_fisrt_day-mFirstDayCell;
        mSelectedCell=day_in_month-1+mFirstDayCell;
        mHdYearDay=mDateCursor.hd.yearFirstDay();
        if (dateChanged!=null)dateChanged.trigger(this);
        invalidate();
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mScrollHandler = new GestureDetector(context,this);
        // Create a Scroller to handle the fling gesture.
        mScroller = new Scroller(getContext(), null, true);
        mScrollAnimator = ValueAnimator.ofFloat(0, 1);
        mScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tickScrollAnimation();
            }
        });
        mStyleDrawer = new YDateViewStyle();
        mTextPaint = new TextPaint(Paint.SUBPIXEL_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(0xff000000);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.YDateView, defStyle, 0);

        mRightToLeft = a.getBoolean(
                R.styleable.YDateView_rightToLeft,true);
        mHebrewMonthAlign = a.getBoolean(
                R.styleable.YDateView_hebrewMonth,true);
        mFrameColor = a.getColor(
                R.styleable.YDateView_frameColor,
                0x000000);
        mBackgroundColor = a.getColor(
                R.styleable.YDateView_backgroundColor,
                0xffffff);
        mSelectionColor = a.getColor(
                R.styleable.YDateView_selectionColor,
                0xff0000);

        mDateCursor = YDate.getNow();
        mDateCursor.setMaintainEvents(false);
        mTodayDay = mDateCursor.hd.daysSinceBeginning();
        dateCursorUpdated();

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        /*mExampleDimension = a.getDimension(
                R.styleable.YDateView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.YDateView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.YDateView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }*/

        a.recycle();
        if (getWidth()>0 && getHeight()>0)
        invalidateMeasurements();

        // Set up a default TextPaint object
        /*mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);*/

        // Update TextPaint and text measurements from attributes
        /*invalidateTextPaintAndMeasurements();*/
    }

    public int getSelectedCell() {
        return mSelectedCell;
    }

    private final int m_cellSpacing=2;
    private int m_cellWidth,m_cellHeight,m_BoardHeight;
    private int m_cellHeaderH;
    private int m_left,m_top;
    private String [] header_weekday;
    private void invalidateMeasurements() {
        int w=getWidth();
        int h=getHeight();

        m_cellWidth=(w-8*m_cellSpacing)/7;
        m_left=(w-6*m_cellSpacing-7*m_cellWidth)/2;


        m_cellHeaderH=h/10;
        m_cellHeight=(h-8*m_cellSpacing-m_cellHeaderH)/6;
        m_top=(h-m_cellHeaderH-6*m_cellHeight-6*m_cellSpacing)/2;
        m_BoardHeight=(m_cellHeight+m_cellSpacing)*6;
        int min_dimension=Math.min(m_cellHeight,m_cellWidth);
        if (min_dimension>12)
        {
            mTextPaint.setTextSize((float)min_dimension*0.5f);
        }
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();

        header_weekday=new String[7];
        if (mUseFullWeekDayNames) {
            for (int i = 0; i < 7; ++i)
                header_weekday[i] = getResources().getString(dayWeeksFull[i]);
        }
        else
        {
            for (int i = 0; i < 7; ++i)
                header_weekday[i] = getResources().getString(dayWeeksShort[i]);
        }
        mStyleDrawer.updateMeasurements(m_cellWidth,m_cellHeight,m_cellHeaderH,m_cellSpacing);

            //if (getWidth())
        /*mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;*/
    }

    protected final int cellToIndex(Point p)
    {
        if (mRightToLeft)
        {
            return (6-p.x)+p.y*7;
        }
        return p.x+p.y*7;
    }
    protected final void indexToCell(int i, Point p)
    {
        if (mRightToLeft)
        {
            p.set(6-i%7,i/7);
        }
        else {
            p.set(i % 7, i / 7);
        }
    }
    static final int[]  dayWeeksFull={
            R.string.week_day_full_sun,
            R.string.week_day_full_mon,
            R.string.week_day_full_tue,
            R.string.week_day_full_wed,
            R.string.week_day_full_thu,
            R.string.week_day_full_fri,
            R.string.week_day_full_sat};

    static final int[]  dayWeeksShort={
            R.string.week_day_short_sun,
            R.string.week_day_short_mon,
            R.string.week_day_short_tue,
            R.string.week_day_short_wed,
            R.string.week_day_short_thu,
            R.string.week_day_short_fri,
            R.string.week_day_short_sat};
    static class DrawAttributes
    {
        public boolean before;
        public boolean after;
        public boolean selected;
        public boolean isToday;
        public boolean jewishDay;
        public boolean evnet;
    }
    public interface StyleDraw
    {
        void updateMeasurements(int cellw,int cellh, int headerh, int space);
        void drawCell(Canvas c, int x, int y, String text, DrawAttributes attr);
        void drawHeader(Canvas c, int x, int y, String text);
    }
    StyleDraw mStyleDrawer;
    int getCellByPixels(int x,int y)
    {
        int x_start = m_left;
        int x_jump = (m_cellWidth + m_cellSpacing);
        int y_start = m_top + m_cellHeaderH + m_cellSpacing;
        int y_jump = m_cellHeight + m_cellSpacing;
        if (y >= y_start && x >= x_start)
        {
            y-=y_start;
            x-=x_start;
            if (y < ( 6 * y_jump ) && x < ( 7 * x_jump ) )
            {
                if ( y % y_jump < m_cellHeight && x % x_jump < m_cellWidth)
                {
                    return cellToIndex(new Point(x/x_jump,y/y_jump));
                }
            }
        }
        return -1;

    }
    private void setScrollOffset(float y)
    {
        mScrollY=y;
        invalidate();
    }

    private void tickScrollAnimation() {
        if (!mScroller.isFinished()) {
            //mScroller.computeScrollOffset();
            //setScrollOffset(mScroller.getCurrY());

        } else {

            if (mScrollY>0.51f*m_BoardHeight)
                mScrollY=(int)(m_BoardHeight+2*mScrollY+2)/3;
            else
                mScrollY=(int)(2*mScrollY)/3;
            setScrollOffset(mScrollY);
                //mScrollAnimator.cancel();
            }
    }
    private DrawAttributes attr = new DrawAttributes();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (getWidth()>0 && getHeight()>0) {


            int x_start, y_start;
            int wd;
            Point p= new Point();
            int cell_day;
            int day_num;
            int day_in_year;
            byte event;
            canvas.translate(0,-mScrollY);
            for (int i = 0; i < 6 * 7; ++i) {
                indexToCell(i, p);
                x_start = m_left + p.x * (m_cellWidth + m_cellSpacing);
                y_start = m_top + m_cellHeaderH + m_cellSpacing + p.y * (m_cellHeight + m_cellSpacing);
                cell_day=i-mFirstDayCell;
                day_num=(cell_day<0)?(mLastMonthLength+cell_day+1):
                        ((cell_day>=mMonthLength)?(cell_day-mMonthLength+1):cell_day+1);
                day_in_year=i+mZeroCellDay-mHdYearDay;
                attr.selected=(i==mSelectedCell);
                attr.isToday=(mZeroCellDay+i==mTodayDay);
                attr.before=(cell_day<0);
                attr.after=(cell_day>=mMonthLength);
                event=mDateCursor.getEvent(day_in_year);
                attr.evnet=event!=0;



                /*canvas.drawRect(x_start, y_start,
                        x_start + m_cellWidth, y_start + m_cellHeight, painter);
                        */


                String day_txt;

                if (mHebrewMonthAlign)
                {
                    day_txt=Format.HebIntString(day_num,false);
                }
                else
                {
                    day_txt=Integer.toString(day_num);
                }
                mStyleDrawer.drawCell(canvas,x_start-m_cellSpacing,y_start-m_cellSpacing,
                        day_txt,attr);
                //canvas.drawText(day_txt, x_start, y_start + m_cellHeight, mTextPaint);

            }
            canvas.translate(0,mScrollY);
            Paint painter = new Paint();
            painter.setColor(getDrawingCacheBackgroundColor());
            canvas.drawRect(0, 0, getWidth(), m_top + m_cellHeaderH+m_cellSpacing, painter);
            painter.setColor(mBackgroundColor);
            //draw header
            for (int j = 0; j < 7; ++j) {
                x_start = m_left + j * (m_cellWidth + m_cellSpacing);
                if (mRightToLeft)
                    wd=6-j;
                else
                    wd=j;
                mStyleDrawer.drawHeader(canvas,x_start-m_cellSpacing,m_top,
                        header_weekday[wd]);
                /*canvas.drawRect(x_start, m_top, x_start + m_cellWidth, m_top + m_cellHeaderH, painter);

                canvas.drawText(header_weekday[wd], x_start, m_top + m_cellHeaderH, mTextPaint);*/
            }
        }
        /*
        canvas.drawCircle(60,60,10,p);
        // Draw the text.
        canvas.drawText("hello",
                40 ,
                40 , p);
        canvas.drawRect(0,30,40,60, p);
*/


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        invalidateMeasurements();
    }

    @Override
    public boolean onTouchEvent( MotionEvent event)
    {
        boolean result = mScrollHandler.onTouchEvent(event);
        //if (!result)
        {
            System.out.println("touch event");
            int action = event.getActionMasked();

            if (mScrollY!=0 && !mScrollAnimator.isRunning() && action == MotionEvent.ACTION_UP)

            {
                //mScroller.fling(0, (int) mScrollY, 0, -(int)(6 * Math.signum(mScrollY)), 0, 0, 0, (int) mScrollY);
                mScrollAnimator.setDuration(500);
                mScrollAnimator.start();
            }

            switch(action) {
                case (MotionEvent.ACTION_DOWN) :
                    System.out.println("Action was DOWN");
                    return true;
                case (MotionEvent.ACTION_MOVE) :
                    System.out.println("Action was MOVE");
                    return true;
                case (MotionEvent.ACTION_UP) :
                    System.out.println("Action was UP");
                    return true;
                case (MotionEvent.ACTION_CANCEL) :
                    System.out.println("Action was CANCEL");
                    return true;
                case (MotionEvent.ACTION_OUTSIDE) :
                    System.out.println("Movement occurred outside bounds " +
                            "of current screen element");
                    return true;
                default :
                    return super.onTouchEvent(event);
            }

            //return true;
        }
        //return result;
    }


    @Override
    public boolean onDown(MotionEvent e) {
        System.out.println("onDown event");

        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        System.out.println("onFling");
        // TODO Auto-generated method stub
        /*if (mScrollY!=0)
        {
            int maxY,minY;
            if (mScrollY>0)
            {
                minY=0;
                maxY=m_BoardHeight;
            }
            else
            {
                minY=-m_BoardHeight;
                maxY=0;
            }
            if (Math.abs(velocityY)<4)
                velocityY=6 * (int) Math.signum(mScrollY);
            mScroller.fling(0,(int)mScrollY,0,(int)velocityY,0,0,minY,maxY);
            mScrollAnimator.setDuration(mScroller.getDuration());
            mScrollAnimator.start();

        }
*/

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        System.out.println("onShowPress");
        updateSelection(getCellByPixels((int)e.getX(),(int)e.getY()));
        if (dateClicked!=null)dateClicked.trigger(this);
        invalidate();

    }

    /**
     * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
     * current move {@link MotionEvent}. The distance in x and y is also supplied for
     * convenience.
     *
     * @param e1 The first down motion event that started the scrolling.
     * @param e2 The move motion event that triggered the current onScroll.
     * @param distanceX The distance along the X axis that has been scrolled since the last
     *              call to onScroll. This is NOT the distance between {@code e1}
     *              and {@code e2}.
     * @param distanceY The distance along the Y axis that has been scrolled since the last
     *              call to onScroll. This is NOT the distance between {@code e1}
     *              and {@code e2}.
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        System.out.println("onScroll "+ String.valueOf(distanceY));

        setScrollOffset(mScrollY+distanceY);
        System.out.println("mScrollY ="+ String.valueOf(mScrollY));
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        System.out.println("onSingleTapUp");
        updateSelection(getCellByPixels((int)e.getX(),(int)e.getY()));
        if (dateChanged!=null)dateChanged.trigger(this);
        invalidate();
        return true;
    }

}
