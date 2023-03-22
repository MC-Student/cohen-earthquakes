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

    public MostRecentEarthquake(Feature mostRecent) throws IOException
    {
        this.mostRecent = mostRecent;
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
