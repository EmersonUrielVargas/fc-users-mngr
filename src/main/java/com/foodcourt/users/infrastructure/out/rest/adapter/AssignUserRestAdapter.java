package com.foodcourt.users.infrastructure.out.rest.adapter;

import com.foodcourt.users.domain.spi.IAssignmentUserPersistencePort;
import com.foodcourt.users.infrastructure.out.rest.client.ICourtRestClient;
import com.foodcourt.users.infrastructure.out.rest.dto.request.AssignEmployeeRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignUserRestAdapter implements IAssignmentUserPersistencePort {

    private final ICourtRestClient courtRestClient;

    @Override
    public void saveAssignment(Long idUser) {
        AssignEmployeeRequestDto assignUserRq = new AssignEmployeeRequestDto(idUser);
        courtRestClient.assignEmployee(assignUserRq);
    }
}
