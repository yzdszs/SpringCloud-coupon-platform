package com.persimmon.coupon.template.dao.convetr;

import com.alibaba.fastjson.JSON;
import com.persimmon.coupon.template.api.beans.rules.TemplateRule;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author persimmon
 */
@Converter
public class RuleConverter implements AttributeConverter<TemplateRule, String> {
    @Override
    public String convertToDatabaseColumn(TemplateRule attribute) {
        return JSON.toJSONString(attribute);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String dbData) {
        return JSON.parseObject(dbData, TemplateRule.class);
    }
}
