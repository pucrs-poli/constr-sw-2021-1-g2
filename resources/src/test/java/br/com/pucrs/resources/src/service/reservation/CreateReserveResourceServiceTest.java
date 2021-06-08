package br.com.pucrs.resources.src.service.reservation;

import br.com.pucrs.resources.src.domain.Reservation;
import br.com.pucrs.resources.src.domain.Resource;
import br.com.pucrs.resources.src.domain.ResourceType;
import br.com.pucrs.resources.src.domain.enumeration.Schedule;
import br.com.pucrs.resources.src.dto.ReserveResourceRequest;
import br.com.pucrs.resources.src.repository.ReservationRepository;
import br.com.pucrs.resources.src.repository.ResourceRepository;
import br.com.pucrs.resources.src.validator.ReserveResourceRequestValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class CreateReserveResourceServiceTest {

    @InjectMocks
    private CreateReserveResourceService service;

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReserveResourceRequestValidator validator;

    @Test
    public void success() {

        String resourceID = RandomStringUtils.randomNumeric(1);
        String resourceTypeID = RandomStringUtils.randomNumeric(1);
        String reservationID = RandomStringUtils.randomNumeric(1);

        ResourceType resourceType = ResourceType.builder()
                ._id(resourceTypeID)
                .name(RandomStringUtils.randomAlphabetic(5))
                .description(RandomStringUtils.randomAlphabetic(5))
                .build();

        Resource resource = Resource.builder()
                .name(RandomStringUtils.randomAlphabetic(5))
                ._id(resourceID)
                .resourceType(resourceType)
                .build();

        Reservation reservation = Reservation.builder()
                ._id(reservationID)
                .day(LocalDate.now())
                .lessonID(RandomStringUtils.randomAlphabetic(5))
                .reason(RandomStringUtils.randomAlphabetic(5))
                .schedule(Schedule.AB)
                .resource(resource)
                .build();

        Mockito.when(resourceRepository.findById(anyString())).thenReturn(of(resource));
        Mockito.when(reservationRepository.findAllByResource__id(anyString())).thenReturn(asList(reservation));
        Mockito.when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReserveResourceRequest request = ReserveResourceRequest.builder()
                .day(LocalDate.now())
                .lessonID(RandomStringUtils.randomAlphabetic(5))
                .reason(RandomStringUtils.randomAlphabetic(5))
                .schedule(Schedule.AB)
                .build();

        Reservation response = service.reserve(resourceID, request);

        Mockito.verify(resourceRepository).findById(anyString()); // quando é chamado outros metodos da service, deve especificar qual o metodo
        Mockito.verify(reservationRepository).findAllByResource__id(anyString()); // quando é chamado apenas uma vez na serviço, não precisa espeficar o método
        Mockito.verify(validator).accept(any(ReserveResourceRequest.class));
        Mockito.verify(reservationRepository, Mockito.times(1)).save(any(Reservation.class));

        Assert.assertNotNull(response);
        Assert.assertEquals(reservationID, response.get_id());
        Assert.assertEquals(reservation.getLessonID(), response.getLessonID());
        Assert.assertEquals(reservation.getReason(), response.getReason());
    }
}