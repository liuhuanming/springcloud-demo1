package com.springcloud.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-31 09:48
 */
@Entity
@Table(name = "b_role", schema = "blog", catalog = "")
public class RoleEntity {
    private long id;
    private String name;
    private Long order;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "order")
    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, order);
    }
}
