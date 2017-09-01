/*
 * Project    : RetailStoreApp
 * File       : FragmentScoped
 * Created on : 10/11/16 7:31 PM
 */
package com.vertaperic.store.util;

import com.vertaperic.store.app.AppComponent;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * In Dagger, an unscoped component cannot depend on a scoped component. As {@link AppComponent}
 * is a scoped as singleton, we need a custom scope to be used by all fragment components.
 * Additionally, a component with a specific scope cannot have a sub component with the same scope.
 *
 * @author Anny Patel
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {
}
