package besiyata.maluach;
import besiyata.maluach.YDateView.DrawAttributes;
import besiyata.maluach.YDateView.StyleDraw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by orr on 04/02/17.
 */

public class YDateViewStyle implements StyleDraw {
    Path mCellPath;
    Path mCellPathDark;
    Path mCellPathLight;
    Path mSelectionPath;
    Bitmap mCellBitmap;
    Bitmap mCellBitmapSelected;
    Bitmap mCellBitmapGray;
    Paint mTransparentPaint;
    Paint mCellPaint;
    int mSpacing;
    int mHSpacing;
    int mCellW;
    int mCellH;
    int mHeaderH;
    int mCellMin;
    boolean mDrawText;
    float font_size;
    float font_ascent;
    float circle_offset_x;
    float circle_offset_y;
    YDateViewStyle()
    {

        mTransparentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTransparentPaint.setColor(0xffffffff);
        mTransparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCellPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.SUBPIXEL_TEXT_FLAG);

    }
    private void initCellPath()
    {
        mCellPath= new Path();
        mCellPath.moveTo(mHSpacing,mHSpacing);
        mCellPath.lineTo(mCellW+mHSpacing,mHSpacing);
        mCellPath.lineTo(mCellW+mHSpacing,mCellH+mHSpacing);
        mCellPath.lineTo(mCellMin*0.2f+mHSpacing,mCellH+mHSpacing);
        mCellPath.lineTo(0+mHSpacing,mCellH-mCellMin*0.2f+mHSpacing);
        mCellPath.lineTo(mHSpacing,mHSpacing);

        mCellPathDark= new Path();
        mCellPathDark.moveTo(mHSpacing,mHSpacing);
        mCellPathDark.lineTo(mCellW+mHSpacing,mHSpacing);
        mCellPathDark.lineTo(mCellW+mHSpacing,mCellH*0.4f+mHSpacing);
        mCellPathDark.quadTo(mCellW*0.5f+mHSpacing,mCellH*0.6f+mHSpacing,mHSpacing,mCellH*0.4f+mHSpacing);
        mCellPathDark.lineTo(mHSpacing,mHSpacing);

        mCellPathLight= new Path();
        mCellPathLight.moveTo(mHSpacing,mCellH*0.4f+mHSpacing);
        mCellPathLight.lineTo(mHSpacing,mCellH+mHSpacing);
        mCellPathLight.lineTo(mHSpacing+mCellW,mCellH+mHSpacing);
        mCellPathLight.lineTo(mHSpacing+mCellW,mCellH*0.4f+mHSpacing);
    }
    private void initSelectionPath()
    {
        mCellW+=2*mHSpacing;
        mCellH+=2*mHSpacing;
        mSelectionPath= new Path();
        int min_cell_dim=Math.min(mCellW,mCellH);
        float corner = min_cell_dim*0.1f;
        mSelectionPath.moveTo(corner,0);
        mSelectionPath.lineTo(mCellW-corner,0);
        mSelectionPath.quadTo(mCellW,0,mCellW,corner);
        mSelectionPath.lineTo(mCellW,mCellH-corner);
        mSelectionPath.quadTo(mCellW,mCellH,mCellW-corner,mCellH);
        mSelectionPath.lineTo(corner,mCellH);
        mSelectionPath.quadTo(0,mCellH,0,mCellH-corner);
        mSelectionPath.lineTo(0,corner);
        mSelectionPath.quadTo(0,0,corner,0);

    }
    public void updateMeasurements(int cellw,int cellh, int headerh, int space)
    {
        mSpacing=space;
        mHSpacing=space/2;
        mCellW=cellw;
        mCellH=cellh;
        mHeaderH=headerh;
        mCellMin=Math.min(mCellH,mCellW);
        circle_offset_x=3;
        circle_offset_y=3;

        if (mCellMin>12)
        {
            font_size=(float)mCellMin*0.5f;
            mTransparentPaint.setTextSize(font_size);
            Paint.FontMetrics fontMetrics = mTransparentPaint.getFontMetrics();
            font_ascent=-fontMetrics.ascent;
            mDrawText=true;
        }
        else
            mDrawText=false;
        initCellPath();
        initSelectionPath();

        mCellBitmap = Bitmap.createBitmap(cellw+2*mHSpacing, cellh+2*mHSpacing, Bitmap.Config.ARGB_8888);
        mCellBitmapSelected = Bitmap.createBitmap(cellw+2*mHSpacing, cellh+2*mHSpacing, Bitmap.Config.ARGB_8888);
        mCellBitmapGray = Bitmap.createBitmap(cellw+2*mHSpacing, cellh+2*mHSpacing, Bitmap.Config.ARGB_8888);
        Canvas cell_canvas =new Canvas(mCellBitmap);

        mCellPaint.setColor(0xff49a3ff);
        cell_canvas.drawPath(mCellPathLight,mCellPaint);
        mCellPaint.setColor(0xff3993dd);
        cell_canvas.drawPath(mCellPathDark,mCellPaint);

        cell_canvas.drawCircle(circle_offset_x,circle_offset_y,3.0f,mTransparentPaint);

        cell_canvas.setBitmap(mCellBitmapSelected);
        mCellPaint.setColor(0xffff8019);
        cell_canvas.drawPath(mSelectionPath,mCellPaint);
        mCellPaint.setColor(0xff49a3ff);
        cell_canvas.drawPath(mCellPathLight,mCellPaint);
        mCellPaint.setColor(0xff3993dd);
        cell_canvas.drawPath(mCellPathDark,mCellPaint);

        cell_canvas.drawCircle(circle_offset_x,circle_offset_y,3.0f,mTransparentPaint);
        //cell_canvas.drawRect(mHSpacing,mHSpacing,cellw+mHSpacing,cellh+mHSpacing,cell_paint);
        
        cell_canvas.setBitmap(mCellBitmapGray);
        mCellPaint.setColor(0xffaaaaaa);
        cell_canvas.drawPath(mCellPath,mCellPaint);

    }

    public void drawHeader(Canvas c, int x, int y,String txt)
    {
        x += mSpacing;
        mCellPaint.setColor(0xff3993dd);
        c.drawRect(x,y,x+mCellW-mSpacing,y+mHeaderH,mCellPaint);
        float txt_w=mTransparentPaint.measureText(txt);
        float text_pos_x=x+(mCellW-2*mSpacing-txt_w)/2.0f;
        float text_pos_y=y+(mHeaderH+(font_ascent+font_size)/2.0f)/2.0f;
        mCellPaint.setColor(0xff000000);
        c.drawText(txt, text_pos_x, text_pos_y, mCellPaint);
    }
    public void drawCell(Canvas c, int x, int y,String txt, DrawAttributes attr)
    {
        Bitmap toDraw;
        float txt_w=mTransparentPaint.measureText(txt);
        float text_pos_x=x+mSpacing+(mCellW-txt_w)/2.0f;
        float text_pos_y=y+mSpacing+(mCellH+(font_ascent+font_size)/2.0f)/2.0f;
        x += mSpacing/2;
        y += mSpacing/2;
        if (attr.before || attr.after) {
            toDraw = mCellBitmapGray;
        }
        else {
            if (attr.selected)
            {
                toDraw = mCellBitmapSelected;
                
            }
            else {
                toDraw = mCellBitmap;
            }
        }

        c.drawBitmap(toDraw,x,y,null);
        if (attr.evnet) {
            mCellPaint.setColor(0xffee7700);
            c.drawCircle(x+circle_offset_x, y+circle_offset_y, 2, mCellPaint);
        }

        int text_color=0xff000000;
        if (attr.isToday)
            text_color=0xffffde00;
        mCellPaint.setColor(text_color);
        c.drawText(txt, text_pos_x, text_pos_y, mCellPaint);
    }
}
