package com.persimmon.coupon.customer.dao;


import com.persimmon.coupon.customer.dao.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponDao extends JpaRepository<Coupon, Long> {

    long countByUserIdAndTemplateId(Long userId, Long templateId);

}
