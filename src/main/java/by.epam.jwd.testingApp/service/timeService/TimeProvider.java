package by.epam.jwd.testingApp.service.timeService;

import java.sql.Date;

public class TimeProvider {
    private static TimeProvider instance;

    private TimeProvider(){}
    public static TimeProvider newInstance() {
        TimeProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (TimeProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TimeProvider();
                }
            }
        }
        return localInstance;
    }

    public Date getSQLDate(){
         return new Date(System.currentTimeMillis());
    }
}
