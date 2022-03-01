package com.harukaze.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
     * 根评论
     */
    private Long parentId;

    /**
     * @用户
     */
    private Long toUid;

    /**
     * 评论等级
     */
    private Integer level;

    /**
     * 评论作者
     */
    private Long userId;

    /**
     * 评论商品
     */
    private Long goodsId;


}
