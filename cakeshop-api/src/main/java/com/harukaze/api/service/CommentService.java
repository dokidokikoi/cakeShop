package com.harukaze.api.service;

import com.harukaze.api.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.param.CommentParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
public interface CommentService extends IService<Comment> {

    Result findGoodsComment(Long id);

    Result saveGoodsComment(CommentParam commentParam);

    Result publishGoodsComment(CommentParam commentParam);
}
