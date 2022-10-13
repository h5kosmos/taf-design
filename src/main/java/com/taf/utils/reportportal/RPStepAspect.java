package com.taf.utils.reportportal;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.aspect.StepAspect;
import com.epam.reportportal.aspect.StepNameUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * class to initiate logging for methods with annotation {@link com.epam.reportportal.annotations.Step} using SpringBootLoader
 */
@Log4j2
@Component
public class RPStepAspect extends StepAspect {

    @Override
    public void startNestedStep(JoinPoint joinPoint, Step step) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = StepNameUtils.getStepName(step, signature, joinPoint);
        log.info(name);
        super.startNestedStep(joinPoint, step);
    }
}
