package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.dto.CreateResourceRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@RunWith(MockitoJUnitRunner.class)
public class CreateResourceRequestValidatorTest {

    @InjectMocks
    private CreateResourceRequestValidator validator;

    @Test
    public void success() {

        CreateResourceRequest request = CreateResourceRequest.builder()
                .resourceName(randomAlphabetic(5))
                .resourceDescription(randomAlphabetic(5))
                .resourceTypeName(randomAlphabetic(5))
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void request_null() {

        validator.accept(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void resource_name_null() {

        CreateResourceRequest request = CreateResourceRequest.builder()
                .resourceName(null)
                .resourceDescription(randomAlphabetic(5))
                .resourceTypeName(randomAlphabetic(5))
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void resource_type_null() {

        CreateResourceRequest request = CreateResourceRequest.builder()
                .resourceName(randomAlphabetic(5))
                .resourceDescription(randomAlphabetic(5))
                .resourceTypeName(null)
                .build();

        validator.accept(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void resource_description_null() {

        CreateResourceRequest request = CreateResourceRequest.builder()
                .resourceName(randomAlphabetic(5))
                .resourceDescription(null)
                .resourceTypeName(randomAlphabetic(5))
                .build();

        validator.accept(request);
    }
}