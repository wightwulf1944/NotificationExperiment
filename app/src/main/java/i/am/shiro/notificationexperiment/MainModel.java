package i.am.shiro.notificationexperiment;

import android.app.NotificationChannel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.IdRes;

import java.util.UUID;

public class MainModel extends ViewModel {

    private MutableLiveData<String> importanceData = new MutableLiveData<>();

    private MutableLiveData<String> shouldVibrateData = new MutableLiveData<>();

    private MutableLiveData<String> shouldShowLightsData = new MutableLiveData<>();

    private NotificationChannel channel;

    private NotificationImportance importance;

    private boolean vibrateDefined;

    private boolean vibrateEnabled;

    public MainModel() {
        vibrateDefined = false;
        updateImportance(NotificationImportance.DEFAULT);
        updateNotificationChannel();
    }

    void onIncreaseClick() {
        if (importance.hasHigher()) {
            updateImportance(importance.higher());
            updateNotificationChannel();
        }
    }

    void onDecreaseClick() {
        if (importance.hasLower()) {
            updateImportance(importance.lower());
            updateNotificationChannel();
        }
    }

    void onRadioVibrateChanged(@IdRes int id) {
        vibrateDefined = id != R.id.radioVibrateDefault;
        switch (id) {
            case R.id.radioVibrateOff:
                vibrateEnabled = false;
                break;
            case R.id.radioVibrateOn:
                vibrateEnabled = true;
                break;
        }
        updateNotificationChannel();
    }

    LiveData<String> getImportanceData() {
        return importanceData;
    }

    MutableLiveData<String> getShouldVibrateData() {
        return shouldVibrateData;
    }

    MutableLiveData<String> getShouldShowLightsData() {
        return shouldShowLightsData;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    private void updateImportance(NotificationImportance importance) {
        this.importance = importance;
        importanceData.setValue(importance.toString());
    }

    private void updateNotificationChannel() {
        String id = UUID.randomUUID().toString();
        String name = "Test Channel";
        channel = new NotificationChannel(id, name, importance.toInt());
        if (vibrateDefined) channel.enableVibration(vibrateEnabled);
        shouldVibrateData.setValue(String.valueOf(channel.shouldVibrate()));
        shouldShowLightsData.setValue(String.valueOf(channel.shouldShowLights()));
    }
}
