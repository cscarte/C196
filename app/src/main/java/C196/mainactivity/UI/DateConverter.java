package C196.mainactivity.UI;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConverter {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd h:mm a z", Locale.getDefault());

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    public static Long toTimestamp(Date date){
        return date == null ? null :date.getTime();
    }

    public static Long toTimestampString(String dateValue){
        try {
            Date date = DateConverter.dateFormat.parse(dateValue + TimeZone.getDefault().getDisplayName());
            return date.getTime();
        } catch (ParseException parseException){
            return 0L;
        }
    }

    public static long nowDate(){
        String currentDate = DateConverter.dateFormat.format(new Date());
        return toTimestampString(currentDate);
    }
}
