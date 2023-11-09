package uz.pdp.doctor.service;

import java.util.UUID;

public interface BaseService<REQ, RES> {

    RES create(REQ req);

    RES delete(UUID id);

    RES getById(UUID id);

}
