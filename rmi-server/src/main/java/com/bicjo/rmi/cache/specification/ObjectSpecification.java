package com.bicjo.rmi.cache.specification;

public interface ObjectSpecification<T> {

	boolean match(T object);

}
