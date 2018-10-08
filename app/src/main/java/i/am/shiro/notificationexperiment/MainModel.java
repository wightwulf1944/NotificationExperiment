package i.am.shiro.notificationexperiment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.IdRes;

public class MainModel extends ViewModel {

    private MutableLiveData<String> importanceData = new MutableLiveData<>();

    private NotificationImportance importance;

    private boolean vibrateDefined;

    private boolean vibrateEnabled;

    private boolean lightsDefined;

    private boolean lightsEnabled;

    public MainModel() {
        vibrateDefined = false;
        setImportance(NotificationImportance.DEFAULT);
    }

    void onIncreaseClick() {
        if (importance.hasHigher()) {
            setImportance(importance.higher());
        }
    }

    void onDecreaseClick() {
        if (importance.hasLower()) {
            setImportance(importance.lower());
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
    }

    void onRadioLightsChanged(@IdRes int id) {
        lightsDefined = id != R.id.radioLightsDefault;
        switch (id) {
            case R.id.radioLightsOff:
                lightsEnabled = false;
                break;
            case R.id.radioLightsOn:
                lightsEnabled = true;
                break;
        }
    }

    LiveData<String> getImportanceData() {
        return importanceData;
    }

    NotificationImportance getImportance() {
        return importance;
    }

    boolean isVibrateDefined() {
        return vibrateDefined;
    }

    boolean isVibrateEnabled() {
        return vibrateEnabled;
    }

    boolean isLightsDefined() {
        return lightsDefined;
    }

    boolean isLightsEnabled() {
        return lightsEnabled;
    }

    private void setImportance(NotificationImportance importance) {
        this.importance = importance;
        importanceData.setValue(importance.toString());
    }
}
