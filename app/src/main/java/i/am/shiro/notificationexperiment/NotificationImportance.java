package i.am.shiro.notificationexperiment;

import android.app.NotificationManager;

public enum NotificationImportance {

    NONE(NotificationManager.IMPORTANCE_NONE),
    MIN(NotificationManager.IMPORTANCE_MIN),
    LOW(NotificationManager.IMPORTANCE_LOW),
    DEFAULT(NotificationManager.IMPORTANCE_DEFAULT),
    HIGH(NotificationManager.IMPORTANCE_HIGH),
    MAX(NotificationManager.IMPORTANCE_MAX);

    private int intValue;

    NotificationImportance(int intValue) {
        this.intValue = intValue;
    }

    public boolean hasHigher() {
        return ordinal() < values().length - 1;
    }

    public boolean hasLower() {
        return ordinal() > 0;
    }

    public NotificationImportance higher() {
        return values()[ordinal() + 1];
    }

    public NotificationImportance lower() {
        return values()[ordinal() - 1];
    }

    public int toInt() {
        return intValue;
    }
}
