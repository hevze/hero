package com.hero.common.cache.guava;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class DefaultGuavaRemoveListener<K, V> implements RemovalListener<K, V> {

    @Override
    public void onRemoval(RemovalNotification<K, V> removalNotification) {

    }
}
