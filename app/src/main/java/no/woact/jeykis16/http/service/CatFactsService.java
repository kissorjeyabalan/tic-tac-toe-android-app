package no.woact.jeykis16.http.service;

import no.woact.jeykis16.http.entity.CatFact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CatFactsService {
    @GET("fact")
    Call<CatFact> getRandomCatFact();
}
