package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Fee;
import com.nakqeeb.sms.entity.PaidStatusEnum;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Hidden
public interface FeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findByStudentId(Long studentId);
    List<Fee> findByPaidStatus(PaidStatusEnum paidStatus);
    List<Fee> findByDueDateBeforeAndPaidStatus(Date date, PaidStatusEnum paidStatus);
}