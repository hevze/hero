package com.hero.common.cache;

public interface CacheLoadable<T> {

    <T> T load();
}
