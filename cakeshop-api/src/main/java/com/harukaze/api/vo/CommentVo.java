package com.harukaze.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @PackageName: com.harukaze.api.vo
 * @ClassName: CommentVo
 * @Description:
 * @Author: doki
 * @Date: 25/12/2021 11:45 AM
 */
@Data
public class CommentVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * @用户
     */
    private UserVo toUser;

    /**
     * 评论作者
     */
    private UserVo user;

    private List<CommentVo> children;
}
