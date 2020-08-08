package org.cloud.omnia.server.converters;

public interface BaseConverterInterface<T_IN, T_OUT> {

    T_OUT convert(T_IN input);

}
