package i.am.shiro.notificationexperiment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private MainModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(MainModel.class);

        setContentView(R.layout.activity_main);

        TextView textImportance = findViewById(R.id.textImportance);

        RadioGroup radioGroupVibrate = findViewById(R.id.radioGroupVibrate);
        radioGroupVibrate.setOnCheckedChangeListener((group, checkedId) -> viewModel.onRadioVibrateChanged(checkedId));

        RadioGroup radioGroupLights = findViewById(R.id.radioGroupLights);
        radioGroupLights.setOnCheckedChangeListener((group, checkedId) -> viewModel.onRadioLightsChanged(checkedId));

        View fabMinus = findViewById(R.id.fabMinus);
        fabMinus.setOnClickListener(v -> viewModel.onDecreaseClick());

        View fabPlus = findViewById(R.id.fabPlus);
        fabPlus.setOnClickListener(v -> viewModel.onIncreaseClick());

        View fabDoIt = findViewById(R.id.fabDoIt);
        fabDoIt.setOnClickListener(v -> doIt());

        viewModel.getImportanceData().observe(this, textImportance::setText);
    }

    private void doIt() {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager == null) throw new NullPointerException();

        String id = UUID.randomUUID().toString();
        String name = "Test Channel";
        NotificationChannel channel = new NotificationChannel(id, name, viewModel.getImportance().toInt());
        if (viewModel.isVibrateDefined()) channel.enableVibration(viewModel.isVibrateEnabled());
        if (viewModel.isLightsDefined()) channel.enableLights(viewModel.isLightsEnabled());
        notificationManager.createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(this, channel.getId())
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("This is a test notification title")
                .setContentText("This is a test notification text")
                .build();
        notificationManager.notify(1, notification);
    }
}
