package com.qijianguo.dao;

import com.qijianguo.dataobject.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Angus
 * @version 1.0
 * @Title: UserTestRepository
 * @Description: TODO
 * @date 2019/2/14 13:56
 */
public interface UserTestRepository extends JpaRepository<JpaUser, Integer> {

}
