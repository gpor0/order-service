package com.github.gpor0.order.integration.rest.mappers;

import com.github.gpor0.order.integration.rest.exceptions.RemoteServerException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.annotation.Priority;
import javax.ws.rs.core.Response;

@Priority(4000)
public class BeerApiExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException toThrowable(Response response) {
        int status = response.getStatus();

        RuntimeException re;
        switch (status) {
            case 500:
                re = new RemoteServerException();
                break;
            default:
                re = new RuntimeException("Unknown error");
        }
        return re;
    }
}
