package io.devfactory.specialroute.service;

import io.devfactory.specialroute.exception.NoSpecialRouteFound;
import io.devfactory.specialroute.model.SpecialRoute;
import io.devfactory.specialroute.repository.SpecialRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpecialRouteService {

    private final SpecialRouteRepository specialRouteRepository;

    public SpecialRoute getSpecialRoute(String serviceName) {
        return specialRouteRepository.findByServiceName(serviceName)
                .orElseThrow(NoSpecialRouteFound::new);
    }

    public void saveSpecialRoute(SpecialRoute route){
        specialRouteRepository.save(route);
    }

    public void updateSpecialRoute(SpecialRoute route){
        specialRouteRepository.save(route);
    }

    public void deleteSpecialRoute(SpecialRoute route){
        specialRouteRepository.delete(route);
    }

}
