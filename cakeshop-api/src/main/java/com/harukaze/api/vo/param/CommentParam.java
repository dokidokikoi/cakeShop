package com.harukaze.api.vo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: CommentParam
 * @Description:
 * @Author: doki
 * @Date: 25/12/2021 1:08 PM
 */
@Data
public class CommentParam {
    private Long id;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    private String content;

    /**
     * 根评论
     */
    private Long parentId;

    /**
     * @用户
     */
    private Long toUid;

    /**
     * 评论商品
     */
    @NotNull(message = "评论商品内容不能为空")
    private Long goodsId;
}
