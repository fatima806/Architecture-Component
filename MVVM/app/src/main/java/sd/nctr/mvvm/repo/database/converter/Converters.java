package sd.nctr.mvvm.repo.database.converter;

import java.sql.Date;

import androidx.room.TypeConverter;

/**
 * Created by Fatima .
 */
public class Converters {
    @TypeConverter
    public static Date toDate(Long dateLong){
        return dateLong == null ? null: new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null ? null : date.getTime();
    }
}
