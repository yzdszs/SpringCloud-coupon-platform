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
public class Discount {

    /**
     * 折扣
     * <p>
     * 满减 - 减掉的钱数
     * 折扣 - 90 = 9折， 95 = 95折
     */
    private Long quota;

    /**
     * 最低达到多少消费才能用
     */
    private Long threshold;
}
