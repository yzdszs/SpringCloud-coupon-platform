package com.persimmon.coupon.template.controller;

import com.alibaba.fastjson.JSON;
import com.persimmon.coupon.template.api.beans.CouponTemplateInfo;
import com.persimmon.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.persimmon.coupon.template.api.beans.TemplateSearchParams;
import com.persimmon.coupon.template.service.intf.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

/**
 * @author persimmon
 */
@Slf4j
@RestController
@RequestMapping("/template")
public class CouponTemplateController {

    private final CouponTemplateService couponTemplateService;

    @Autowired
    public CouponTemplateController(CouponTemplateService couponTemplateService) {
        this.couponTemplateService = couponTemplateService;
    }

    /**
     * 创建优惠券
     *
     * @param request
     * @return
     */
    @PostMapping("/addTemplate")
    public CouponTemplateInfo addTemplate(@Valid @RequestBody CouponTemplateInfo request) {
        log.info("Create coupon template: data={}", request);
        return couponTemplateService.createTemplate(request);
    }

    /**
     * 克隆优惠券
     *
     * @param templateId
     * @return
     */
    @PostMapping("/cloneTemplate")
    public CouponTemplateInfo cloneTemplate(@RequestParam("id") Long templateId) {
        log.info("Clone coupon template: data={}", templateId);
        return couponTemplateService.cloneTemplate(templateId);
    }

    /**
     * 读取优惠券
     *
     * @param id
     * @return
     */
    @GetMapping("/getTemplate")
    public CouponTemplateInfo getTemplate(@RequestParam("id") Long id) {
        log.info("Load template, id={}", id);
        return couponTemplateService.loadTemplateInfo(id);
    }

    /**
     * 批量获取
     *
     * @param ids
     * @return
     */
    @GetMapping("/getBatch")
    public Map<Long, CouponTemplateInfo> getTemplateInBatch(@RequestParam("ids") Collection<Long> ids) {
        log.info("getTemplateInBatch: {}", JSON.toJSONString(ids));
        return couponTemplateService.getTemplateInfoMap(ids);
    }

    /**
     * 搜索模板
     *
     * @param request
     * @return
     */
    @PostMapping("/search")
    public PagedCouponTemplateInfo search(@Valid @RequestBody TemplateSearchParams request) {
        log.info("search templates, payload={}", request);
        return couponTemplateService.search(request);
    }

    /**
     * 优惠券无效化
     *
     * @param id
     */
    @DeleteMapping("/deleteTemplate")
    public void deleteTemplate(@RequestParam("id") Long id) {
        log.info("Load template, id={}", id);
        couponTemplateService.deleteTemplate(id);
    }

}
