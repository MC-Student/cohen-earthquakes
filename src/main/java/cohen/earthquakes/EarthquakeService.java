package cohen.earthquakes;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface EarthquakeService
{
    @GET("earthquakes/feed/v1.0/summary/all_hour.geojson")
    Observable<FeatureCollection> getLatestEarthquakes();
}
