package com.foodcourt.users.infrastructure.out.rest.client;
import com.foodcourt.users.infrastructure.configuration.FeignClientConfig;
import com.foodcourt.users.infrastructure.out.rest.dto.request.AssignEmployeeRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="court-mngr", url = "${external.api.court-mngr.url}", configuration = FeignClientConfig.class)
public interface ICourtRestClient {

    @PostMapping(value = "v1/restaurant/assignment", consumes = MediaType.APPLICATION_JSON_VALUE)
    void assignEmployee(@RequestBody AssignEmployeeRequestDto assignEmployee);

}
