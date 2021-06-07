package br.com.pucrs.resources.src.web.controller;

import br.com.pucrs.resources.src.config.SwaggerConfig;
import br.com.pucrs.resources.src.domain.Reservation;
import br.com.pucrs.resources.src.domain.Resource;
import br.com.pucrs.resources.src.dto.CreateResourceRequest;
import br.com.pucrs.resources.src.dto.ReserveResourceRequest;
import br.com.pucrs.resources.src.dto.UpdateResourceRequest;
import br.com.pucrs.resources.src.service.reservation.*;
import br.com.pucrs.resources.src.service.resource.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Api(tags = SwaggerConfig.RESOURCE)
@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final FindResourceService findResourceService;

    private final CreateResourceService createResourceService;

    private final FindAllResourcesService findAllResourcesService;

    private final UpdateResourceService updateResourceService;

    private final DeleteResourceService deleteResourceService;

    private final CreateReserveResourceService createReserveResourceService;

    private final FindAllReserveResourceService findAllReserveResourceService;

    private final FindAllReserveResourceByResourceIDService findAllReserveResourceByResourceIDService;

    private final FindAllReserveResourceByLessonIDService findAllReserveResourceByLessonIDService;

    private final FindReservationByIDService findReservationByIDService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Register a new resource")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created successfully"),
            @ApiResponse(code = 400, message = "Error in the request sent by the client"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @PostMapping
    public Resource createResource(@RequestBody final CreateResourceRequest request) {
        return createResourceService.create(request);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    @ApiOperation(value = "Get resource by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource found successfully"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    public Resource findResource(@PathVariable("id") final String id) {
        return findResourceService.findByID(id);
    }

    @ResponseStatus(OK)
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource found successfully"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    public List<Resource> findAllResources() {
        return findAllResourcesService.find();
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Update resource")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource updated successfully"),
            @ApiResponse(code = 400, message = "Error in the request sent by the client"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @PutMapping("/{id}")
    public Resource updateResource(@PathVariable("id") final String id,
                                   @RequestBody final UpdateResourceRequest request) {
        return updateResourceService.update(id, request);
    }

    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Delete resource")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource deleted successfully"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable("id") final String id) {
        deleteResourceService.delete(id);
    }

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Register a new reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Reservation created successfully"),
            @ApiResponse(code = 400, message = "Error in the request sent by the client"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @PostMapping("/{id}/reservation")
    public Reservation reserveResource(@RequestBody final ReserveResourceRequest request, @PathVariable("id") final String id) {
        return createReserveResourceService.reserve(id, request);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "List all reservations for a specific resource")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservations found successfully"),
            @ApiResponse(code = 404, message = "Reservation not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @GetMapping("/reservations")
    public List<Reservation> reserveResource() {
        return findAllReserveResourceService.findAll();
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "List all reservations for a specific resource")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservations found successfully"),
            @ApiResponse(code = 404, message = "Reservation not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @GetMapping("/resource/{id}/reservations")
    public List<Reservation> ReservationsByResource(@PathVariable("id") final String id) {
        return findAllReserveResourceByResourceIDService.find(id);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "List all reservations for a specific lesson")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservations found successfully"),
            @ApiResponse(code = 404, message = "Reservation not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @GetMapping("/lesson/{id}/reservations")
    public List<Reservation> ReservationsByLesson(@PathVariable("id") final String id) {
        return findAllReserveResourceByLessonIDService.find(id);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Get resevation by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation found successfully"),
            @ApiResponse(code = 404, message = "Reservation not found"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @GetMapping("/{id}/reservations")
    public Reservation findById(@PathVariable("id") final String id) {
            return findReservationByIDService.find(id);
    }
}