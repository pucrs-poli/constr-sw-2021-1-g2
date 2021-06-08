package br.com.pucrs.resources.src.web.controller;

import br.com.pucrs.resources.src.config.SwaggerConfig;
import br.com.pucrs.resources.src.domain.Resource;
import br.com.pucrs.resources.src.domain.ResourceType;
import br.com.pucrs.resources.src.dto.CreateResourceTypeRequest;
import br.com.pucrs.resources.src.dto.UpdateResourceTypeRequest;
import br.com.pucrs.resources.src.service.types.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Api(tags = SwaggerConfig.RESOURCE_TYPE)
@RestController
@RequestMapping("/resources-types")
@RequiredArgsConstructor
public class ResourceTypeController {

    private final FindResourceTypeService findResourceTypeService;

    private final CreateResourceTypeService createResourceTypeService;

    private final FindAllResourcesTypesService findAllResourcesTypesService;

    private final UpdateResourceTypeService updateResourceTypeService;

    private final FindAllResourcesByTypeService findAllResourcesByTypeService;

    private final DeleteResourceTypeService deleteResourceTypeService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Register a new resource type")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource type created successfully"),
            @ApiResponse(code = 400, message = "Error in the request sent by the client"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @PostMapping
    public ResourceType createResourceType(@RequestBody final CreateResourceTypeRequest request) {
        return createResourceTypeService.create(request);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Get resource type by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource type found successfully"),
            @ApiResponse(code = 404, message = "Resource type not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @GetMapping("/{id}")
    public ResourceType findResourceType(@PathVariable("id") final String id) {
        return findResourceTypeService.findByID(id);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "List all resources types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resources types found successfully"),
            @ApiResponse(code = 404, message = "Resource type not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @GetMapping
    public List<ResourceType> findAllResourcesTypes() {
        return findAllResourcesTypesService.find();
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Update resource type")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource type updated successfully"),
            @ApiResponse(code = 400, message = "Error in the request sent by the client"),
            @ApiResponse(code = 404, message = "Resource type not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @PutMapping("/{id}")
    public ResourceType updateResourceType(@PathVariable("id") final String id,
                                           @RequestBody final UpdateResourceTypeRequest request) {
        return updateResourceTypeService.update(id, request);
    }

    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Delete resource type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource type deleted successfully"),
            @ApiResponse(code = 404, message = "Resource type not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @DeleteMapping("/{id}")
    public void deleteResourceType(@PathVariable("id") final String id) {
        deleteResourceTypeService.delete(id);
    }


    @ResponseStatus(OK)
    @ApiOperation(value = "List all resources by its type id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resources found successfully"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @GetMapping("/{id}/resources")
    public List<Resource> findAllResourcesByTypeId(@PathVariable("id") final String id) {
        return findAllResourcesByTypeService.findById(id);
    }
}