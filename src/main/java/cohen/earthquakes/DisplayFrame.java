package cohen.earthquakes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DisplayFrame extends JFrame
{
    public DisplayFrame() throws IOException
    {
        MostRecentEarthquake earthquake = new MostRecentEarthquake();
        JTextField[] recentInfo = new JTextField[5];

        JPanel centerPanel = new JPanel(new GridLayout(5, 1));

        JTextField place = new JTextField();
        place.setName("Location");
        place.setText(earthquake.recentGetPlace());
        recentInfo[0] = place;

        JTextField coordinates = new JTextField();
        coordinates.setName("Coordinates");
        coordinates.setText(earthquake.recentGetCoordinates()[0] + ", " + earthquake.recentGetCoordinates()[1] + ", " + earthquake.recentGetCoordinates()[2]);
        recentInfo[1] = coordinates;

        JTextField time = new JTextField();
        time.setName("Date and Time");
        Date date = new Date(earthquake.recentGetTime());
        time.setText(String.valueOf(date));
        recentInfo[2] = time;

        JTextField magnitude = new JTextField();
        magnitude.setName("Magnitude");
        magnitude.setText(String.valueOf(earthquake.recentGetMag()));
        recentInfo[3] = magnitude;

        JTextField tsunamis = new JTextField();
        tsunamis.setName("Associated Tsunamis");
        tsunamis.setText(String.valueOf(earthquake.recentGetTsunami()));
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
    }

}