package com.github.nrudenko.dora.commons;

public interface IAdapter<F, T> {
    T from(F from);

    F to(T to);
}
