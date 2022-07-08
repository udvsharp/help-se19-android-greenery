package com.nure.greeneryapp.rest.api;

import com.nure.greeneryapp.rest.model.Species;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpeciesService {
    @GET("api/species/average/{name}")
    Call<Integer> GetAverageBySpecies(
            @Path("name") String speciesName
    );

    @GET("api/species/{id}")
    Call<Species> GetSpeciesById(
            @Path("id") Integer speciesId
    );

    @GET("api/species/{name}")
    Call<Species> GetSpeciesByName(
            @Path("name") String name
    );
}
