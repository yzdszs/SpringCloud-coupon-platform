package com.persimmon.coupon.template.converter;

import com.persimmon.coupon.template.api.beans.CouponTemplateInfo;
import com.persimmon.coupon.template.dao.entity.CouponTemplate;

/**
 * @author persimmon
 */
public class CouponTemplateConverter {

    public static CouponTemplateInfo convertToTemplateInfo(CouponTemplate template) {
        return CouponTemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .desc(template.getDescription())
                .type(template.getCategory().getCode())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();
    }
}
