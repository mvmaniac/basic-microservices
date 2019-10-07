package io.devfactory.specialroute.controller;

import io.devfactory.specialroute.model.SpecialRoute;
import io.devfactory.specialroute.service.SpecialRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1/special-route")
@RestController
public class SpecialRouteController {

    private final SpecialRouteService specialRouteService;

    @GetMapping("/{serviceName}")
    public SpecialRoute getSpecialRoute(@PathVariable("serviceName") String serviceName) {
        return specialRouteService.getSpecialRoute(serviceName);
    }

}
