package org.cloud.omnia.server.converters;

/**
 *
 * @param <T_IN> data to be converted.
 * @param <T_OUT> desired output data type.
 *
 * @author Tanmay Majumdar
 */
public interface IBaseConverterInterface<T_IN, T_OUT> {

    /**
     * Function to convert T_IN to T_OUT.
     * @param input data to be converted.
     * @return output of the data
     */
    T_OUT convert(T_IN input);

}
