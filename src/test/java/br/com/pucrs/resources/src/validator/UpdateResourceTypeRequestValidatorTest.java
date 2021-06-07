package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.dto.UpdateResourceTypeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@RunWith(MockitoJUnitRunner.class)
public class UpdateResourceTypeRequestValidatorTest {

    @InjectMocks
    private UpdateResourceTypeRequestValidator validator;

    @Test
    public void success() {

        UpdateResourceTypeRequest request = UpdateResourceTypeRequest.builder()
                .name(randomAlphabetic(5))
                .description(randomAlphabetic(5))
                .description(randomAlphabetic(5))
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void request_null() {

        validator.accept(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void name_null() {

        UpdateResourceTypeRequest request = UpdateResourceTypeRequest.builder()
                .name(null)
                .description(randomAlphabetic(5))
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void description_null() {

        UpdateResourceTypeRequest request = UpdateResourceTypeRequest.builder()
                .name(randomAlphabetic(5))
                .description(null)
                .build();

        validator.accept(request);
    }

}

