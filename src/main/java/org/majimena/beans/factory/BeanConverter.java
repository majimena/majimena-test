package org.majimena.beans.factory;

/**
 * custom Bean Converter interface.
 * @param <F> original conversion bean object
 * @param <T> destination conversion bean object
 */
public interface BeanConverter<F, T> {

    T convert(F original);

}
