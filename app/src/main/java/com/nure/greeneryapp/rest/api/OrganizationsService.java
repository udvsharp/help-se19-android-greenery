package com.nure.greeneryapp.rest.api;

import com.nure.greeneryapp.rest.model.OgranizationInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

// TODO: empty page with organization info for regular user
public interface OrganizationsService {
    @GET("api/organizations/id/{id}")
    Call<OgranizationInfo> GetOrganization(
            @Path("id") String id
    );
}
