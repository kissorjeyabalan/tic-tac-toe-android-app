package no.woact.jeykis16.http;

import no.woact.jeykis16.http.service.CatFactsService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebHelper {
    private static final String CATFACTS_BASE_URL = "https://catfact.ninja/";
    private static CatFactsService CATFACTS_INSTANCE;

    public static CatFactsService getCatFactsService() {
        if (WebHelper.CATFACTS_INSTANCE == null) {
            WebHelper.CATFACTS_INSTANCE = getClient(CATFACTS_BASE_URL).create(CatFactsService.class);
        }
        return WebHelper.CATFACTS_INSTANCE;
    }

    private static Retrofit getClient(String baseUrl) {
     return new Retrofit.Builder()
             .baseUrl(baseUrl)
             .addConverterFactory(GsonConverterFactory.create())
             .build();
    }

}
