package com.qijianguo.dao;

import com.qijianguo.dataobject.UserDo;
import com.qijianguo.dataobject.UserDoExample;
import java.util.List;

public interface UserDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Feb 16 11:27:31 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Feb 16 11:27:31 CST 2019
     */
    int insert(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Feb 16 11:27:31 CST 2019
     */
    int insertSelective(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Feb 16 11:27:31 CST 2019
     */
    List<UserDo> selectByExample(UserDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Feb 16 11:27:31 CST 2019
     */
    UserDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Feb 16 11:27:31 CST 2019
     */
    int updateByPrimaryKeySelective(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Feb 16 11:27:31 CST 2019
     */
    int updateByPrimaryKey(UserDo record);

    UserDo selectByTelephone(String telephone);


}