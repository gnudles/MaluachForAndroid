/* This is free and unencumbered software released into the public domain.
 *
 * THIS SOFTWARE IS PROVIDED THE CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE CONTRIBUTORS BE LIABLE FOR ANY 
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; BUSINESS
 * INTERRUPTION; OR ANY SPIRITUAL DAMAGE) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package kapandaria.YDate;
import kapandaria.YDate.YDate.JewishDate;
import java.util.Arrays;
public class YDateAnnual
{
    static final int A_EV_ROSH_HASHANA_A = 1;
    static final int A_EV_ROSH_HASHANA_B = 2;
    static final int A_EV_TZOM_GEDALIA = 3;
    static final int A_EV_EREV_YOM_KIPPUR = 4;
    static final int A_EV_YOM_KIPPUR = 5;
    static final int A_EV_EREV_SUKKOT = 6;
    static final int A_EV_SUKKOT = 7;
    /**
     * Second day of galuyot of Sukkot.
     */
    static final int A_EV_SDOG_SUKKOT = 8;
    static final int A_EV_SUKKOT_HOL_HAMOED = 9;
    static final int A_EV_HOSHANA_RABBAH = 10;
    static final int A_EV_SHEMINI_ATZERET = 11;
    static final int A_EV_SIMCHAT_TORAH = 12;
    static final int A_EV_SHEMINI_ATZERET_SIMCHAT_TORAH = 13;
    static final int A_EV_HANUKKAH_ONE_CANDLE = 14;
    static final int A_EV_HANUKKAH_TWO_CANDLES = 15;
    static final int A_EV_HANUKKAH_THREE_CANDLES = 16;
    static final int A_EV_HANUKKAH_FOUR_CANDLES = 17;
    static final int A_EV_HANUKKAH_FIVE_CANDLES = 18;
    static final int A_EV_HANUKKAH_SIX_CANDLES = 19;
    static final int A_EV_HANUKKAH_SEVEN_CANDLES = 20;
    static final int A_EV_HANUKKAH_EIGHT_CANDLES = 21;
    static final int A_EV_TENTH_OF_TEVET = 22;
    static final int A_EV_FIFTEEN_SHVAT = 23;
    static final int A_EV_TAANIT_ESTHER = 24;
    static final int A_EV_PURIM = 25;
    static final int A_EV_SHUSHAN_PURIM = 26;
    static final int A_EV_PURIM_KATAN = 27;
    static final int A_EV_EREV_PESACH = 28;
    static final int A_EV_PESACH = 29;//Passover
    /**
     * Sheni Shel Pesach (second yom-tov day of galuyot - diaspora)
     * this is NOT Pesach Sheni!
     */
    static final int A_EV_SDOG_PESACH = 30;
    static final int A_EV_PESACH_HOL_HAMOED = 31;
    static final int A_EV_SHVII_PESACH = 32;
    static final int A_EV_SHVII_SDOG_PESACH = 33;
    static final int A_EV_PESACH_SHENI = 34;
    static final int A_EV_RASHBI_THIRTY_THREE = 35;
    static final int A_EV_EREV_SHAVUOT = 36;
    static final int A_EV_SHAVUOT = 37;
    static final int A_EV_SDOG_SHAVUOT = 38;
    static final int A_EV_ISRU_HAG = 39;
    static final int A_EV_TZOM_SEVENTEEN_TAMMUZ = 40;
    static final int A_EV_TZOM_NINE_AV = 41;
    static final int A_EV_FIFTEEN_AV = 42;
    static final int A_EV_EREV_ROSH_HASHANA = 43;
    static final int A_EV_SIMCHAT_COHEN = 44;
    /**
     *     holocaust day. decided in 27 Nissan 1951 (5711). if on friday, move it backward. if on sunday after 1997 move it afterward.
     */
    static final int A_EV_HOLOCAUST_DAY = 45;
    /**
     * memorial day. in 4 Iyar 1951 (5711).
     */
    static final int A_EV_MEMORIAL_DAY_FALLEN = 46;
    static final int A_EV_INDEPENDENTS_DAY = 47;
    static final int A_EV_JERUSALEMS_DAY = 48;
    /**
     * family day. in 30 Shevat since 1973 (5733).
     */
    static final int A_EV_FAMILY_DAY = 49;
    static final int A_EV_ISAAC_RABIN_DAY = 50;
    static final int A_EV_TAANIT_GZEROT_408_409 = 51;


    
    public final static String[] events_str =
    {
        "",//0 -reserved for none
        "א' ראש השנה",
        "ב' ראש השנה",//2
        "צום גדליה",//3
        "ערב יום כיפור",//4
        "יום כיפור",//5
        "ערב סוכות",
        "סוכות",
        "יו\"ט שני של סוכות",//Sukkot II
        "חול המועד סוכות",
        "הושענא רבה",//10
        "שמיני עצרת",
        "שמחת תורה",
        "שמיני עצרת שמחת תורה",
        "א' חנוכה",//14
        "ב' חנוכה",
        "ג' חנוכה",//16
        "ד' חנוכה",
        "ה' חנוכה",
        "ו' חנוכה",
        "ז' חנוכה",
        "ח' חנוכה",//21
        "צום י' בטבת",
        "ט\"ו בשבט",
        "תענית אסתר",//24
        "פורים",//25
        "שושן פורים",
        "פורים קטן",
        "ערב פסח",//28
        "פסח",
        "יו\"ט שני של פסח",//Pesach II
        "חול המועד פסח",//31
        "שביעי של פסח",
        "שמיני של פסח",
        "פסח שני",
        "ל\"ג לעמר הילולא דרשב\"י",//35
        "ערב שבועות",
        "שבועות",
        "יו\"ט שני של שבועות",//38//Shavuot II
        "אסרו חג",
        "צום י\"ז בתמוז",//40
        "צום ט' באב",
        "ט\"ו באב",
        "ערב ראש השנה",
        "יום שמחת כהן",//44
        "יום השואה",//45
        "יום הזיכרון",
        "יום העצמאות",//47
        "יום ירושלים",
        "יום המשפחה",
        "יום הזכרון ליצחק רבין",//50
        "תענית ת\"ח ת\"ט",
    };

    public static final short EV_NONE=0;
    public static final short EV_YOM_TOV=1;
    public static final short EV_HOL_HAMOED=2;
    public static final short EV_ISRU_HAG=3;
    public static final short EV_EREV_YOM_TOV=4;
    public static final short EV_MIRACLE=5;
    public static final short EV_CHASIDIC=6;
    public static final short EV_GOOD_DAYS=7;
    public static final short EV_TYPE_MASK=7;
    public static final short EV_NATIONAL=8;
    public static final short EV_TZOM=16;
    public static final short EV_REGALIM=32;
    public static final short EV_MEMORIAL=64;
    public static final short EV_HORBAN=128;
    static final short[] events_type =
    {
        EV_NONE,//0 -reserved for none
        EV_YOM_TOV,
        EV_YOM_TOV,//2
        EV_TZOM|EV_HORBAN,//3
        EV_EREV_YOM_TOV,//4
        EV_TZOM|EV_YOM_TOV,//5
        EV_EREV_YOM_TOV,
        EV_YOM_TOV|EV_REGALIM,
        EV_YOM_TOV|EV_REGALIM,//Sukkot II
        EV_HOL_HAMOED|EV_REGALIM,
        EV_HOL_HAMOED|EV_REGALIM,//10
        EV_YOM_TOV|EV_REGALIM,
        EV_YOM_TOV|EV_REGALIM,
        EV_YOM_TOV|EV_REGALIM,
        EV_MIRACLE,
        EV_MIRACLE,
        EV_MIRACLE,//16
        EV_MIRACLE,
        EV_MIRACLE,
        EV_MIRACLE,
        EV_MIRACLE,
        EV_MIRACLE,//21
        EV_TZOM|EV_HORBAN,
        EV_GOOD_DAYS,
        EV_TZOM|EV_MIRACLE,
        EV_MIRACLE,//25
        EV_MIRACLE,
        EV_MIRACLE,
        EV_EREV_YOM_TOV,//28
        EV_YOM_TOV|EV_REGALIM,
        EV_YOM_TOV|EV_REGALIM,//Pesach II
        EV_HOL_HAMOED|EV_REGALIM,//31
        EV_YOM_TOV|EV_REGALIM,
        EV_YOM_TOV|EV_REGALIM,
        EV_REGALIM,
        EV_GOOD_DAYS,//35
        EV_EREV_YOM_TOV,
        EV_YOM_TOV|EV_REGALIM,
        EV_YOM_TOV|EV_REGALIM,//38//Shavuot II
        EV_ISRU_HAG,
        EV_TZOM|EV_HORBAN,//40
        EV_TZOM|EV_HORBAN,
        EV_GOOD_DAYS,
        EV_EREV_YOM_TOV,
        EV_GOOD_DAYS,//44 - Simhat Cohen
        EV_NATIONAL|EV_MEMORIAL,//45
        EV_NATIONAL|EV_MEMORIAL,
        EV_NATIONAL,//47
        EV_NATIONAL,
        EV_NATIONAL,
        EV_NATIONAL|EV_MEMORIAL,//50
        EV_TZOM|EV_MEMORIAL,//51
    };


    static final byte [][] event_db= 
    {// month_id,day,array index,# of days,jump/dhia(if #_days==1)
        {JewishDate.M_ID_TISHREI,1,A_EV_ROSH_HASHANA_A,2,1},//two days of rosh hashana
        {JewishDate.M_ID_TISHREI,3,A_EV_TZOM_GEDALIA,1,1},//zom gdalia, dhia
        {JewishDate.M_ID_TISHREI,9,A_EV_EREV_YOM_KIPPUR,2,1},//yom kippur
        {JewishDate.M_ID_TISHREI,11,A_EV_SIMCHAT_COHEN,1,0},//yom Simhat Cohen
        {JewishDate.M_ID_TISHREI,14,A_EV_EREV_SUKKOT,2,1},//Erev Sukkot+Sukkot
        {JewishDate.M_ID_TISHREI,16,A_EV_SUKKOT_HOL_HAMOED,5,0},//hol hamoed sukkot
        {JewishDate.M_ID_TISHREI,21,A_EV_HOSHANA_RABBAH,1,0},//hoshana raba
        {JewishDate.M_ID_TISHREI,22,A_EV_SHEMINI_ATZERET_SIMCHAT_TORAH,1,0},//shmini azeret simhat_tora
        {JewishDate.M_ID_TISHREI,23,A_EV_ISRU_HAG,1,0},//isru hag
        {JewishDate.M_ID_KISLEV,25,A_EV_HANUKKAH_ONE_CANDLE,8,1},//Chanukah
        {JewishDate.M_ID_TEVET,10,A_EV_TENTH_OF_TEVET,1,0},//Tzom Asara B'Tevet
        {JewishDate.M_ID_SHEVAT,15,A_EV_FIFTEEN_SHVAT,1,0},//Tu B'Shvat
        {JewishDate.M_ID_ADAR,13,A_EV_TAANIT_ESTHER,1,-2},//taanit ester, dhia
        {JewishDate.M_ID_ADAR,14,A_EV_PURIM,2,1},//Purim+Shushan
        {JewishDate.M_ID_ADAR_I,14,A_EV_PURIM_KATAN,2,0},//Purim katan - two days
        {JewishDate.M_ID_ADAR_II,13,A_EV_TAANIT_ESTHER,1,-2},//taanit ester, dhia
        {JewishDate.M_ID_ADAR_II,14,A_EV_PURIM,2,1},//Purim+Shushan
        {JewishDate.M_ID_NISAN,14,A_EV_EREV_PESACH,2,1},//Erev Pesah+Pesah
        {JewishDate.M_ID_NISAN,16,A_EV_PESACH_HOL_HAMOED,5,0},//Hol Ha'moed Pesah
        {JewishDate.M_ID_NISAN,21,A_EV_SHVII_PESACH,1,0},//Shvi'i Pesah
        {JewishDate.M_ID_NISAN,22,A_EV_ISRU_HAG,1,0},//isru hag
        {JewishDate.M_ID_IYAR,14,A_EV_PESACH_SHENI,1,0},//Pesah Sheni
        {JewishDate.M_ID_IYAR,18,A_EV_RASHBI_THIRTY_THREE,1,0},//Lag Ba'Omer
        {JewishDate.M_ID_SIVAN,5,A_EV_EREV_SHAVUOT,2,1},//Erev Shavu'ot+Shavu'ot
        {JewishDate.M_ID_SIVAN,7,A_EV_ISRU_HAG,1,0},//isru hag
        {JewishDate.M_ID_TAMMUZ,17,A_EV_TZOM_SEVENTEEN_TAMMUZ,1,1},//Tzom 17 Tamuz, dhia
        {JewishDate.M_ID_AV,9,A_EV_TZOM_NINE_AV,1,1},//Tzom 9 Av, dhia
        {JewishDate.M_ID_AV,15,A_EV_FIFTEEN_AV,1,0},//15 Av
        {JewishDate.M_ID_ELUL,29,A_EV_EREV_ROSH_HASHANA,1,0},//Erev Rosh Hashana
        {JewishDate.M_ID_SIVAN,20,A_EV_TAANIT_GZEROT_408_409,1,0},//5408-5409 memorial 
//TODO: maybe add event since year parameter. for 5408 memorial.
    };
    static final byte [][] event_db_diaspora= 
    {// month_id,day,array index,# of days,jump/dhia(if #_days==1)
        {JewishDate.M_ID_TISHREI,16,8,1,0},//sukkot II
        {JewishDate.M_ID_TISHREI,22,11,1,0},//shmini azeret
        {JewishDate.M_ID_TISHREI,23,12,1,0},//simhat_tora
        {JewishDate.M_ID_NISAN,16,30,1,0},//Pesah II
        {JewishDate.M_ID_NISAN,22,33,1,0},//Shmi'ni Pesah
        {JewishDate.M_ID_SIVAN,7,38,1,0},//Shavu'ot II
    };

    
    private byte [] current_year_events;
    private boolean diaspora;
    private int year;
    private int year_length;
    public int year()
    {
        return this.year;
    }
    public int yearLength()
    {
        return this.year_length;
    }
    public boolean diaspora()
    {
        return this.diaspora;
    }
    public String getYearEventForDay(JewishDate d, YDateLanguage language)
    {
        //TODO: replace events_str with language
        //return language.getEventToken(current_year_events[d.dayInYear()]);
        return events_str[current_year_events[d.dayInYear()]];
    }
    public String getYearEventForDayRejection(JewishDate d, YDateLanguage language)
    {
        //TODO: replace events_str with language
        //return language.getEventToken(current_year_events[d.dayInYear()]);
        String out=events_str[current_year_events[d.dayInYear()]];
        short rej= isRejected(d);
        if (rej!=0)
            out+=" ("+language.getRejection(rej)+")";
        return out;
    }
    public short getYearEventTypeForDay(JewishDate d)
    {
        return events_type[current_year_events[d.dayInYear()]];
    }
    static public short getEventType(int event_id)
    {
        return events_type[event_id];
    }
    public byte [] getYearEvents()
    {
        return current_year_events;
    }
    private static byte [] cloneArray(byte [] arr)
    {
        byte [] new_arr=new byte[arr.length];
        for (int i=0;i<arr.length;++i)
        {
            new_arr[i]=arr[i];
        }
        return new_arr;
    }
    public YDateAnnual(int year, int year_length, int year_first_day,boolean diaspora)
    {
        this.year=year;
        this.year_length=year_length;
        this.diaspora=diaspora;
        int year_ld_t= JewishDate.ld_year_type(year_length,year_first_day%7+1);
        initialize_year(diaspora,year_ld_t,year_length,year_first_day);
        current_year_events=cloneArray(annual_events[diaspora?1:0][year_ld_t-1]);
        addNationalEvents(current_year_events,year, year_length, year_first_day);
    }
    private static void addNationalEvents(byte [] year_events,int year, int year_length, int year_first_day)
    {
        //Holocaust day
        if (year >= 5718)//1958
        {
            int day_in_year = YDate.JewishDate.calculateDayInYearByMonthId(year_length, JewishDate.M_ID_NISAN, 27);
            int dayweek = (day_in_year + year_first_day) % 7;
            if (dayweek == YDate.FRIDAY)//friday
            {
                day_in_year--;
            }
            else if (dayweek == YDate.SUNDAY)//sunday
            {

                day_in_year++;
            }
            year_events[day_in_year] = 45;
        }
        //Yom Azma'ut and Yom HaZikaron
        if (year >= 5708)//1948
        {
            int day_in_year = YDate.JewishDate.calculateDayInYearByMonthId(year_length, JewishDate.M_ID_IYAR, 5);
            int dayweek = (day_in_year + year_first_day) % 7;

            if (dayweek == YDate.SATURDAY)//on saturday
            {
                day_in_year -= 2;

            }
            else if (dayweek == YDate.FRIDAY)//on friday
            {
                day_in_year -= 1;

            }
            else if (dayweek == YDate.MONDAY && year >= 5764)//on monday (2004) then Yom HaZikaron is on sunday, and that's no good...
            {
                day_in_year += 1;

            }
            year_events[day_in_year - 1] = 46;//Yom HaZikaron
            year_events[day_in_year] = 47;//Yom Azma'ut
        }
        //Jerusalem day
        if (year >= 5728)//1968
	{
            int day_in_year = YDate.JewishDate.calculateDayInYearByMonthId(year_length, JewishDate.M_ID_IYAR, 28);
            year_events[day_in_year] = 48;
	}
        //Family day
        if (year >= 5733)//1973
	{
            int day_in_year = YDate.JewishDate.calculateDayInYearByMonthId(year_length, JewishDate.M_ID_SHEVAT, 30);
            year_events[day_in_year] = 49;
	}
        //Rabin's Day   
        if (year >= 5758)//cheshvan 1997
        {
            int day_in_year = YDate.JewishDate.calculateDayInYearByMonthId(year_length, JewishDate.M_ID_CHESHVAN, 12);
            int dayweek = (day_in_year + year_first_day) % 7;
            if (dayweek == YDate.FRIDAY)
            {
                day_in_year--;
            }
            year_events[day_in_year] = 50;
        }
    }
    static final byte [][][] annual_events = new byte [2][JewishDate.N_YEAR_TYPES][];//[diaspora][year_type][day_in_year]
    static final short [][] annual_events_dhia = new short [JewishDate.N_YEAR_TYPES][4];//[year_type][5]->[day_in_year]
    public static String getEventForDay(JewishDate d, boolean diaspora, YDateLanguage language)
    {
        //TODO: make this depended on language...
        //return language.getEventToken(getEvents(d, diaspora)[d.dayInYear()]);
        return events_str[getEvents(d, diaspora)[d.dayInYear()]];
    }
    public static byte [] getEvents(JewishDate d,boolean diaspora)
    {
        int year_ld_t= JewishDate.ld_year_type(d.yearLength(),d.yearFirstDay()%7+1);
        return initialize_year(diaspora,year_ld_t,d.yearLength(),d.yearFirstDay());
    }
    public static final short PRECEDE = 512;
    public static final short LATE = 1024;
    public static short isRejected(JewishDate d)
    {
        int year_ld_t= JewishDate.ld_year_type(d.yearLength(),d.yearFirstDay()%7+1);
        short []dhia=annual_events_dhia[year_ld_t-1];
        if (annual_events[0][year_ld_t-1]!=null)
        {
            int diy=d.dayInYear();
            for (short i: dhia)
            {
                if ((i&~(PRECEDE|LATE))==diy)
                {
                    return (short)(i&(PRECEDE|LATE));
                }
            }
        }
        return 0;
    }
    public static byte [] getEvents(int year_length, int year_first_day,boolean diaspora)
    {
        int year_ld_t= JewishDate.ld_year_type(year_length,year_first_day%7+1);
        return initialize_year(diaspora,year_ld_t,year_length,year_first_day);
    }
    static private void ArrayReplace(short[] arr, short find, short put)
    {
        for (int i=0;i<arr.length;++i)
        {
            if (arr[i]==find)
            {
                arr[i]=put;
                return;
            }
        }
    }
    /*static private <T> void ArrayReplace(T[] arr, T find, T put)
    {
        for (int i=0;i<arr.length;++i)
        {
            if (arr[i].equals(find))
            {
                arr[i]=put;
                return;
            }
        }
    }*/
    private static void expandDB(int year_length,int year_first_day,final byte [][] evdb, byte [] year_events, short [] dhia)
    {
        boolean leap=year_length>355;
        final int IDX_M_ID = 0;
        final int IDX_DAY = 1;
        final int IDX_IDX = 2;
        final int IDX_LEN = 3;
        final int IDX_JMP = 4;
        for (int ev = 0; ev < evdb.length; ++ev)
        {
            int m_id = evdb[ev][IDX_M_ID];
            if (m_id == JewishDate.M_ID_ADAR && leap)
            {
                continue;
            }
            if ((m_id == JewishDate.M_ID_ADAR_I || m_id == JewishDate.M_ID_ADAR_II) && !leap)
            {
                continue;
            }
            int diy = JewishDate.calculateDayInYearByMonthId(year_length, m_id, evdb[ev][IDX_DAY]);
            if (evdb[ev][IDX_LEN] == 1)
            {
                if (evdb[ev][IDX_JMP] != 0)// dhia
                {
                    if ((year_first_day + diy) % 7 == YDate.SATURDAY)
                    {
                        diy += evdb[ev][IDX_JMP];
                        if (dhia!=null)
                        {
                            ArrayReplace(dhia,(short)-1,(short)(diy|(evdb[ev][IDX_JMP]>0 ?LATE:PRECEDE)));
                        }
                    }
                }
                year_events[diy] = evdb[ev][IDX_IDX];
            }
            else
            {
                byte idx = evdb[ev][IDX_IDX];
                for (int l = 0; l < evdb[ev][IDX_LEN]; ++l)
                {
                    year_events[diy + l] = idx;
                    idx += evdb[ev][IDX_JMP];
                }
            }
        }
    }
    private static byte[] initialize_year(boolean diaspora,int year_ld_t,int year_length,int year_first_day)
    {
        if (annual_events[diaspora?1:0][year_ld_t-1]==null)
        {
            annual_events[diaspora?1:0][year_ld_t-1]=new byte [year_length];
            Arrays.fill(annual_events_dhia[year_ld_t-1], (short)-1);
            expandDB(year_length,year_first_day,event_db, annual_events[diaspora?1:0][year_ld_t-1],annual_events_dhia[year_ld_t-1]);
            if (diaspora)
                expandDB(year_length,year_first_day,event_db_diaspora, annual_events[diaspora?1:0][year_ld_t-1],null);
        }
        return annual_events[diaspora?1:0][year_ld_t-1];
    }

    //bad days to start new things from ha'ari
/*
nisan: 7,9,11,16,21,24
iyar: 5,7,15,22
sivan: 1,6,9,26
tammuz: 14,15,17,20,29
av: 9,10,19,20,22,27
elul:9,17,28,29
tishrei: 6,10,28
cheshvan: 7,11,15,21
kislev: 1,8
tevet: 1,2,4,6,7,11,17,20,24,25,26,27
shevat: 9,17,18,24,25,26
adar I,II: 3,15,17,18,28
hamilinzki כ סיון גזירות תח תט
*/
}
