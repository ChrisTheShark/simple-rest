package com.dyer.frameworks.domain;

import javax.persistence.*;

/**
 * Base <code>class</code> represents an Entity.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long identifier;

    @Version
    private Long version;

    protected BaseEntity() {
        this.identifier = null;
    }

}
