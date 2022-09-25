package com.persimmon.coupon.calculation.template;


import com.persimmon.coupon.calculation.api.beans.ShoppingCart;

/**
 * 使用模板设计模式
 *
 * @author persimmon
 */
public interface RuleTemplate {

    /**
     * 计算优惠券
     *
     * @param settlement 订单信息
     * @return
     */
    ShoppingCart calculate(ShoppingCart settlement);
}
