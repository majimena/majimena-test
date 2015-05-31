package org.majimena.beans.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Spring's FormattingConversionServiceFactoryBean extension.
 * Automatically register to locate implements Converter object.
 */
public class AutoRegisterFormattingConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(AutoRegisterFormattingConversionServiceFactoryBean.class);

    private List<String> packageNames = new ArrayList<>();

    private Set<Converter<?, ?>> converters = new HashSet<>();

    public void setPackageNames(List<String> packageNames) {
        this.packageNames = packageNames;
    }

    @Override
    public void setApplicationContext(final ApplicationContext context) throws BeansException {
        // 指定されたパッケージに存在するコンバータは自動登録する
        Map<String, Converter> converterMap = context.getBeansOfType(Converter.class);
        for (Map.Entry<String, Converter> entry : converterMap.entrySet()) {
            Converter converter = entry.getValue();
            Package p = converter.getClass().getPackage();
            for (String name : packageNames) {
                if (p.getName().matches(name)) {
                    converters.add(converter);
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        // 先に手動設定されているコンバータやフォーマッタを登録
        super.afterPropertiesSet();
        // 自動登録するコンバータを追加登録
        ConversionServiceFactory.registerConverters(this.converters, getObject());
    }
}
