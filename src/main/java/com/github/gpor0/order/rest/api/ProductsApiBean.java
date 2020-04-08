package com.github.gpor0.order.rest.api;

import com.github.gpor0.beer.rest.api.ApiException;
import com.github.gpor0.beer.rest.api.BeersApi;
import com.github.gpor0.beer.rest.api.model.Beer;
import com.github.gpor0.order.rest.api.model.Product;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ProductsApiBean implements ProductsApi {

    @Inject
    @RestClient
    protected BeersApi beersApi;

    @Inject
    protected ProductMapper productMapper;

    @Context
    private UriInfo uri;

    @Override
    public Response getProducts(String company) {
        try {
            List<Beer> beers = beersApi.getBeers(company);
            List<Product> products = beers.stream().map(productMapper::map).collect(Collectors.toList());

            return Response.ok(products).header("X-Total-Count", products.size()).header("X-Complete", "true").build();

        } catch (ApiException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
