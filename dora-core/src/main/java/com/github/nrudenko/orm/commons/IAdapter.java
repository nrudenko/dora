package com.github.nrudenko.orm.commons;

public interface IAdapter<F, T> {
    T from(F from);

    F to(T to);
}
