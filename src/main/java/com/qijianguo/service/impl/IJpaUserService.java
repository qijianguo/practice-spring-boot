package com.qijianguo.service.impl;

import com.qijianguo.dao.UserTestRepository;
import com.qijianguo.dataobject.JpaUser;
import com.qijianguo.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Angus
 * @version 1.0
 * @Title: IUserService
 * @Description: TODO
 * @date 2019/2/14 13:56
 */
@Service
@Transactional
public class IJpaUserService implements JpaUserService {

    @Autowired
    private UserTestRepository userTestRepository;

    @Override
    public void save(JpaUser jpaUser) {
        JpaUser save = userTestRepository.save(jpaUser);
        System.out.println(save);
    }
}
