package uz.pdp.doctor.service;


import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * @param <RES>>> base response
 * @param <REQ>> create or update request
 */

@Service
public interface BaseService<REQ, RES> {

    RES create(REQ req);

    RES delete(UUID id);

    RES getById(UUID id);

}
