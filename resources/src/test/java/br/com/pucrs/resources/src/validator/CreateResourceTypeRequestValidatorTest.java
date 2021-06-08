package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.dto.CreateResourceRequest;
import br.com.pucrs.resources.src.dto.CreateResourceTypeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@RunWith(MockitoJUnitRunner.class)
public class CreateResourceTypeRequestValidatorTest {

    @InjectMocks
    private CreateResourceTypeRequestValidator validator;

    @Test
    public void success() {

        CreateResourceTypeRequest request = CreateResourceTypeRequest.builder()
                .name(randomAlphabetic(5))
                .description(randomAlphabetic(5))
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void request_null() {

        validator.accept(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void resource_name_null() {

        CreateResourceTypeRequest request = CreateResourceTypeRequest.builder()
                .name(null)
                .description(randomAlphabetic(5))
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void resource_description_null() {
        CreateResourceTypeRequest request = CreateResourceTypeRequest.builder()
                .name(randomAlphabetic(5))
                .description(null)
                .build();

        validator.accept(request);
    }
}