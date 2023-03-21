package cohen.earthquakes;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MostRecentEarthquake
{
    private final Feature mostRecent;

    public MostRecentEarthquake() throws IOException
    {
        //URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
        URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");
        URLConnection urlConnection = url.openConnection();
        InputStream inStream = urlConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        Gson gson = new Gson();
        FeatureCollection featureCollection = gson.fromJson(reader, FeatureCollection.class);
        System.out.println(featureCollection.features[0].properties.place);
        mostRecent = featureCollection.features[0];
    }

    public String recentGetPlace()
    {
        return mostRecent.properties.place;
    }

    public double recentGetMag()
    {
        return mostRecent.properties.mag;
    }

    public long recentGetTime()
    {
        return mostRecent.properties.time;
    }

    public int recentGetTsunami()
    {
        return mostRecent.properties.tsunami;
    }

    public double[] recentGetCoordinates()
    {
        return mostRecent.geometry.coordinates;
    }
}
