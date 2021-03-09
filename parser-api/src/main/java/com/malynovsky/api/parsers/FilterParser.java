package com.malynovsky.api.parsers;

import java.util.function.Predicate;

public interface FilterParser<T> {

    void setFilter(Predicate<T> filter);
}
