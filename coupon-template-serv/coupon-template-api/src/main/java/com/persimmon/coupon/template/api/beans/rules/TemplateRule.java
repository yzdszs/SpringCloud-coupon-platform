package com.persimmon.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author persimmon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {

    /**
     * 模板可以享受的折扣
     */
    private Discount discount;

    /**
     * 每个人最多可以领劵数量
     */
    private Integer limitation;

    /**
     * 过期时间
     */
    private Long deadLine;
}
