package com.qijianguo.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Angus
 * @version 1.0
 * @Title: LearnResouce
 * @Description: TODO
 * @date 2019/2/14 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearnResouce {

    private String author;

    private String title;

    private String url;
}
