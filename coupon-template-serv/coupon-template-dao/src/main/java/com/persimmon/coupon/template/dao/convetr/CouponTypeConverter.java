package com.persimmon.coupon.template.dao.convetr;

import com.persimmon.coupon.template.api.enums.CouponType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author persimmon
 */
@Converter
public class CouponTypeConverter implements AttributeConverter<CouponType, String> {

    @Override
    public String convertToDatabaseColumn(CouponType attribute) {
        return attribute.getCode();
    }

    @Override
    public CouponType convertToEntityAttribute(String dbData) {
        return CouponType.convert(dbData);
    }
}
