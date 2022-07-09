package com.nure.greeneryapp.rest.api;

import com.nure.greeneryapp.rest.model.Plant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlantsService {
    @GET("api/plants/organization/{id}")
        // TODO: auth?
    Call<List<Plant>> GetOrganizationPlants(
            @Path("id") String orgId // TODO: String?
    );

    @GET("api/plants/{id}")
        // TODO: auth? // TODO: is it right endpoint?
    Call<List<Plant>> GetPlantsByUser(
            @Path("id") String userId
    );
}
