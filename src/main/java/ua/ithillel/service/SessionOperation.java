package ua.ithillel.service;

import org.hibernate.Session;

@FunctionalInterface
public interface SessionOperation<T> {
    T execute(Session session);
}
