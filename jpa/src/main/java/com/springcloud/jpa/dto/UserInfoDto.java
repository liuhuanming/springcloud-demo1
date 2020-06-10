package com.springcloud.jpa.dto;

import com.springcloud.jpa.entity.RoleEntity;
import com.springcloud.jpa.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-10 22:50
 */
@Data
public class UserInfoDto extends UserEntity implements Serializable {
    private static final long serialVersionUID = 6322334719410631624L;

    private Set<RoleEntity> roleEntitySet;
}
