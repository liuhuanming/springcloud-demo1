package com.springcloud.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-14 22:06
 */
@Data
@Entity
@Table(name = "b_user_role", schema = "blog", catalog = "")
public class UserRoleEntity {
    @Id
    private Long userId;
    private Long roleId;
}
