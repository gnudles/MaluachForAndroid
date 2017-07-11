package besiyata.YDate;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import besiyata.YDate.YDate.*;
/**
 * Created by Orr Dvori on 7/11/2017.
 */

public class NativeTzProvider implements YDate.TimeZoneProvider {
    @Override
    public float getOffset(Date d) {


        return (Calendar.getInstance().getTimeZone().getOffset(d.getTime())/1000L)/3600.0f;
    }
}
