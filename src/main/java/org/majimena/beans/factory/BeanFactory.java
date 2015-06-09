package org.majimena.beans.factory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean Factory. Bean conversion and creation factory.
 */
public class BeanFactory implements InitializingBean {

    private static ConversionService conversionService;

    public void setConversionService(ConversionService conversionService) {
        BeanFactory.conversionService = conversionService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (conversionService == null) {
            throw new IllegalStateException("ConversionService must not be null.");
        }
    }

    public static <F, T> List<T> create(List<F> originals, Class<T> clazz) {
        List<T> converted = new ArrayList<>();
        for (F original : originals) {
            converted.add(create(original, clazz));
        }
        return converted;
    }

    public static <F, T> List<T> create(List<F> originals, BeanConverter<F, T> converter) {
        List<T> converted = new ArrayList<>();
        for (F original : originals) {
            converted.add(create(original, converter));
        }
        return converted;
    }

    public static <F, T> T create(F original, Class<T> clazz) {
        if (original == null) {
            return null;
        }
        if (!conversionService.canConvert(original.getClass(), clazz)) {
            throw new IllegalArgumentException("Cannot convert object. Conversion source class ["
                + original.getClass().getName() + "] destination class [" + clazz.getName() + "]");
        }
        T converted = conversionService.convert(original, clazz);
        return converted;
    }

    public static <F, T> T create(F original, BeanConverter<F, T> converter) {
        if (original == null) {
            return null;
        }
        T converted = converter.convert(original);
        return converted;
    }

    public static <F, T> T create(F original, T destination) {
        if (original == null) {
            return null;
        }
        BeanUtils.copyProperties(original, destination);
        return destination;
    }

}
