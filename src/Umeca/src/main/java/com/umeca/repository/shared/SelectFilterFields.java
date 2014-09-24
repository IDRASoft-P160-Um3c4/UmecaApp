package com.umeca.repository.shared;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 5:14 PM
 */

public interface SelectFilterFields {
    public <T> List<Selection<?>> getFields(final Root<T> r);
    public <T> Expression<String> setFilterField(Root<T> r, String field);
}