package com.harukaze.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.api.entity.Comment;
import com.harukaze.api.entity.Order;
import com.harukaze.api.entity.OrderItem;
import com.harukaze.api.mapper.CommentMapper;
import com.harukaze.api.mapper.OrderItemMapper;
import com.harukaze.api.mapper.OrderMapper;
import com.harukaze.api.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.service.UserService;
import com.harukaze.api.util.UserThreadLocal;
import com.harukaze.api.vo.CommentVo;
import com.harukaze.api.vo.Result;
import com.harukaze.api.vo.UserVo;
import com.harukaze.api.vo.param.CommentParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-12-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    UserService userService;

    @Override
    public Result findGoodsComment(Long id) {
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getGoodsId, id).eq(Comment::getLevel, 1).orderByDesc(Comment::getCreateDate));
        List<CommentVo> commentVos = copyList(comments);
        return Result.succ(commentVos, commentVos.size());
    }

    @Override
    public Result saveGoodsComment(CommentParam commentParam) {
        Comment comment = new Comment();
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        comment.setGoodsId(commentParam.getGoodsId());
        comment.setParentId(commentParam.getParentId());
        comment.setUserId(UserThreadLocal.get().getId());
        comment.setToUid(commentParam.getToUid());
        if (comment.getParentId() == null) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        commentMapper.insert(comment);
        return Result.succ(null, 0);
    }

    @Override
    public Result publishGoodsComment(CommentParam commentParam) {
        List<Order> orders = orderMapper.selectList(new LambdaQueryWrapper<Order>().eq(Order::getUserId, UserThreadLocal.get().getId()).eq(Order::getStatus, 1));
        List<OrderItem> orderItems = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems1 = orderItemMapper.selectList(new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, order.getId()));
            orderItems.addAll(orderItems1);
        }
        for (OrderItem orderItem : orderItems) {
            if (commentParam.getGoodsId().equals(orderItem.getGoodsId())) {
                return saveGoodsComment(commentParam);
            }
        }
        return Result.fail("请先购买此商品");
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentVo commentVo = copy(comment);
            commentVos.add(commentVo);
        }
        return commentVos;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        if (comment.getToUid() != null) {
            commentVo.setToUser(userService.findUserById(comment.getToUid()));
        }
        UserVo userById = userService.getUserById(comment.getUserId());
        commentVo.setUser(userById);
        if (comment.getLevel() == 1) {
            commentVo.setChildren(findChildrenByParentId(comment.getId()));
        }
        return commentVo;
    }

    private List<CommentVo> findChildrenByParentId(Long id) {
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getParentId, id));
        return copyList(comments);
    }
}
