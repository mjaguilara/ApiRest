package com.examen.demo.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.examen.demo.entity.Phone;


@Repository
@Transactional
public interface PhoneRepository  extends CrudRepository<Phone, Long> {

}
