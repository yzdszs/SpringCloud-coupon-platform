package com.persimmon.coupon.template.service.intf;

import com.persimmon.coupon.template.api.beans.CouponTemplateInfo;
import com.persimmon.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.persimmon.coupon.template.api.beans.TemplateSearchParams;

import java.util.Collection;
import java.util.Map;

/**
 * @author persimmon
 */
public interface CouponTemplateService {

    /**
     * 创建优惠券模板
     *
     * @param request
     * @return
     */
    CouponTemplateInfo createTemplate(CouponTemplateInfo request);

    /**
     * 复制优惠券模板
     *
     * @param templateId
     * @return
     */
    CouponTemplateInfo cloneTemplate(Long templateId);

    /**
     * 模板查询（分页）
     *
     * @param request
     * @return
     */
    PagedCouponTemplateInfo search(TemplateSearchParams request);

    /**
     * 通过模板ID查询优惠券模板
     *
     * @param id
     * @return
     */
    CouponTemplateInfo loadTemplateInfo(Long id);

    /**
     * 让优惠券模板无效
     *
     * @param id
     */
    void deleteTemplate(Long id);

    /**
     * 批量查询
     *
     * @param ids
     * @return
     */
    Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids);
}
