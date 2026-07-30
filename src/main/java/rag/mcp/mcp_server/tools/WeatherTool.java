package rag.mcp.mcp_server.tools;


import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.Objects;

@Component
public class WeatherTool {

    private static final Logger log = LoggerFactory.getLogger(WeatherTool.class);

    private final RestClient geoClient;
    private final RestClient weatherClient;
    private final String apiKey;

    public WeatherTool(
            RestClient.Builder geoClient,
            RestClient.Builder weatherClient,
            @Value("${weather.open-weather.api-key}") String apiKey) {
        this.geoClient = geoClient.baseUrl("http://api.openweathermap.org").build();
        this.weatherClient = weatherClient.baseUrl("https://api.openweathermap.org").build();
        this.apiKey = apiKey;
    }

    public record WeatherRequest(
            String city,
            String state,
            String countryCode
    ){}

    public record Weather(
            int id,
            String main,
            String description,
            String icon
    ) {
    }
    public record MainWeather(
            double temp,
            double feels_like,
            double temp_min,
            double temp_max,
            int pressure,
            int humidity,
            int sea_level,
            int grnd_level
    ) {
    }

    public record WeatherResponse(
            List<Weather> weather,
            MainWeather main,
            String name
    ) {
    }

    private record Coordinates(
            String name,
            Double lat,
            Double lon,
            String country,
            String state
    ){}


    public record WeatherToolResponse(
            String name,
            String description,
            Double temp,
            Double feels_like,
            Integer humidity

    ){}

    @Tool(
            name = "getWeather",
            description = "Returns current weather of requested city"
    )
    public WeatherToolResponse getWeather(@NonNull WeatherRequest request){

        String location = String.join(",",
                request.city(),
                request.state(),
                Objects.requireNonNullElse(request.countryCode(), "US")
                );

        List<Coordinates> geoResponse = geoClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/geo/1.0/direct")
                                .queryParam("q", location)
                                .queryParam("limit", 1)
                                .queryParam("appid", apiKey)
                                .build()
                )
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if(geoResponse == null || geoResponse.isEmpty()){
            throw new RuntimeException("Unable to find coordinates for: " + location);
        }

        Coordinates coordinates = geoResponse.getFirst();

        WeatherResponse weatherResponse =  weatherClient.get()
                .uri(
                        uriBuilder ->
                                uriBuilder.path("data/2.5/weather")
                                        .queryParam("lat", coordinates.lat())
                                        .queryParam("lon", coordinates.lon())
                                        .queryParam("appid", apiKey)
                                        .queryParam("units","imperial")
                                        .build()).retrieve()
                .body(WeatherResponse.class);

        if(weatherResponse == null){
            throw new RuntimeException("No weather data available for: " + location);
        }

        return new WeatherToolResponse(
                weatherResponse.name(),
                weatherResponse.weather().getFirst().description(),
                weatherResponse.main().temp(),
                weatherResponse.main().feels_like(),
                weatherResponse.main().humidity()
        );


    }

}
