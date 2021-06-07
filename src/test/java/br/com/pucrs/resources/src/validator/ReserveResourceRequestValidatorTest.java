package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.domain.enumeration.Schedule;
import br.com.pucrs.resources.src.dto.ReserveResourceRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@RunWith(MockitoJUnitRunner.class)
public class ReserveResourceRequestValidatorTest {

    @InjectMocks
    private ReserveResourceRequestValidator validator;

    @Test
    public void success() {

        ReserveResourceRequest request = ReserveResourceRequest.builder()
                .day(LocalDate.now())
                .lessonID(randomAlphanumeric(10))
                .reason(randomAlphabetic(20))
                .schedule(Schedule.AB)
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void request_null() {

        validator.accept(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void day_null() {

        ReserveResourceRequest request = ReserveResourceRequest.builder()
                .day(null)
                .lessonID(randomAlphanumeric(10))
                .reason(randomAlphabetic(20))
                .schedule(Schedule.AB)
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lesson_id_null() {

        ReserveResourceRequest request = ReserveResourceRequest.builder()
                .day(LocalDate.now())
                .lessonID(null)
                .reason(randomAlphabetic(20))
                .schedule(Schedule.AB)
                .build();


        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void reason_null() {

        ReserveResourceRequest request = ReserveResourceRequest.builder()
                .day(LocalDate.now())
                .lessonID(randomAlphanumeric(10))
                .reason(null)
                .schedule(Schedule.AB)
                .build();


        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void schedule_null() {

        ReserveResourceRequest request = ReserveResourceRequest.builder()
                .day(LocalDate.now())
                .lessonID(randomAlphanumeric(10))
                .reason(randomAlphabetic(20))
                .schedule(null)
                .build();

        validator.accept(request);
    }
}

