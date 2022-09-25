package com.persimmon.coupon.template.service;

import com.persimmon.coupon.template.api.beans.CouponTemplateInfo;
import com.persimmon.coupon.template.api.beans.PagedCouponTemplateInfo;
import com.persimmon.coupon.template.api.beans.TemplateSearchParams;
import com.persimmon.coupon.template.api.enums.CouponType;
import com.persimmon.coupon.template.converter.CouponTemplateConverter;
import com.persimmon.coupon.template.dao.CouponTemplateDao;
import com.persimmon.coupon.template.dao.convetr.CouponTypeConverter;
import com.persimmon.coupon.template.dao.entity.CouponTemplate;
import com.persimmon.coupon.template.service.intf.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author persimmon
 */
@Slf4j
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private final CouponTemplateDao templateDao;

    @Autowired
    public CouponTemplateServiceImpl(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        Long shopId = request.getShopId();
        if (shopId != null) {
            Integer count = templateDao.countByShopIdAndAvailable(shopId, true);
            if (count > 100) {
                log.error("the totals of coupon template exceeds maximum number");
                throw new UnsupportedOperationException("exceeded the maximum of coupon templates that you can create");
            }
        }
        CouponTemplate couponTemplate = CouponTemplate.builder()
                .name(request.getName())
                .description(request.getDesc())
                .category(CouponType.convert(request.getType()))
                .available(true)
                .shopId(shopId)
                .rule(request.getRule())
                .build();
        couponTemplate = templateDao.save(couponTemplate);
        return CouponTemplateConverter.convertToTemplateInfo(couponTemplate);

    }

    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        CouponTemplate source = templateDao.findById(templateId)
                .orElseThrow(() -> new IllegalArgumentException("invalid template ID"));

        CouponTemplate target = new CouponTemplate();
        BeanUtils.copyProperties(source, target);

        target.setAvailable(true);
        target.setId(null);

        templateDao.save(target);
        return CouponTemplateConverter.convertToTemplateInfo(target);
    }

    @Override
    public PagedCouponTemplateInfo search(TemplateSearchParams request) {
        CouponTemplate couponTemplate = CouponTemplate.builder()
                .shopId(request.getShopId())
                .category(CouponType.convert(request.getType()))
                .available(request.getAvailable())
                .name(request.getName())
                .build();

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getPageSize());
        Page<CouponTemplate> result = templateDao.findAll(Example.of(couponTemplate), pageRequest);
        List<CouponTemplateInfo> couponTemplateInfos = result.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toList());

        return PagedCouponTemplateInfo.builder()
                .templates(couponTemplateInfos)
                .page(request.getPage())
                .total(result.getTotalElements())
                .build();
    }

    @Override
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        Optional<CouponTemplate> templateDaoById = templateDao.findById(id);
        return templateDaoById.map(CouponTemplateConverter::convertToTemplateInfo).orElse(null);
    }


    //todo 为什么加事务
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteTemplate(Long id) {
        int rows = templateDao.makeCouponUnavailable(id);
        if (rows == 0) {
            throw new IllegalArgumentException("Template Not Found: " + id);
        }

    }

    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        List<CouponTemplate> couponTemplates = templateDao.findAllById(ids);
        return couponTemplates.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }
}
