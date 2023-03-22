package cohen.earthquakes;

import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DisplayFrame extends JFrame
{
    public DisplayFrame() throws IOException
    {

        JTextField[] recentInfo = new JTextField[5];

        JPanel centerPanel = new JPanel(new GridLayout(5, 1));

        JTextField place = new JTextField();
        place.setName("Location");
        recentInfo[0] = place;

        JTextField coordinates = new JTextField();
        coordinates.setName("Coordinates");
        recentInfo[1] = coordinates;

        JTextField time = new JTextField();
        time.setName("Date and Time");
        recentInfo[2] = time;

        JTextField magnitude = new JTextField();
        magnitude.setName("Magnitude");
        recentInfo[3] = magnitude;

        JTextField tsunamis = new JTextField();
        tsunamis.setName("Associated Tsunamis");
        recentInfo[4] = tsunamis;

        for (JTextField jTextField : recentInfo)
        {
            jTextField.setHorizontalAlignment(JLabel.CENTER);
            jTextField.setEditable(false);
            centerPanel.add(jTextField);
        }

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setSize(400, 600);
        setTitle("Most Recent Earthquake Information");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        EarthquakeService service = retrofit.create(EarthquakeService.class);

        Disposable disposable = Observable.interval(0,30, TimeUnit.SECONDS)
                .flatMap((Long) -> service.getLatestEarthquakes())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        featureCollection -> {
                            MostRecentEarthquake earthquake = new MostRecentEarthquake(featureCollection.features[0]);
                            place.setText(earthquake.recentGetPlace());
                            coordinates.setText(earthquake.recentGetCoordinates()[0] + ", " + earthquake.recentGetCoordinates()[1] + ", " + earthquake.recentGetCoordinates()[2]);
                            Date date = new Date(earthquake.recentGetTime());
                            time.setText(String.valueOf(date));
                            magnitude.setText(String.valueOf(earthquake.recentGetMag()));
                            tsunamis.setText(String.valueOf(earthquake.recentGetTsunami()));
                        }
                        ,
                        Throwable::printStackTrace
                );
    }
}