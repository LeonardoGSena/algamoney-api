package com.sena.leonardo.algamoneyapi.api.event.listener;

import com.sena.leonardo.algamoneyapi.api.event.ResourceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class ResourceListener implements ApplicationListener<ResourceEvent> {
    @Override
    public void onApplicationEvent(ResourceEvent event) {
        HttpServletResponse response = event.getResponse();
        Long id = event.getId();

        addHeaderLocatrion(response, id);
    }

    private void addHeaderLocatrion(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
