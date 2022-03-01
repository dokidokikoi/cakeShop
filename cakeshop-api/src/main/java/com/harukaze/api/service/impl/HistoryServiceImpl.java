package com.harukaze.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.api.entity.History;
import com.harukaze.api.mapper.HistoryMapper;
import com.harukaze.api.service.GoodsService;
import com.harukaze.api.service.HistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.api.util.UserThreadLocal;
import com.harukaze.api.vo.HistoryVo;
import com.harukaze.api.vo.Result;
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
 * @since 2021-12-25
 */
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {
    @Autowired
    HistoryMapper historyMapper;

    @Autowired
    GoodsService goodsService;

    @Override
    public Result getHistoryList() {
        List<History> histories = historyMapper.selectList(new LambdaQueryWrapper<History>().eq(History::getUserId, UserThreadLocal.get().getId()).orderByDesc(History::getCreateDate));
        List<HistoryVo> historyVoList = copyList(histories);

        return Result.succ(historyVoList, historyVoList.size());
    }

    private List<HistoryVo> copyList(List<History> histories) {
        List<HistoryVo> historyVoList = new ArrayList<>();
        for (History history : histories) {
            HistoryVo historyVo = copy(history);
            historyVoList.add(historyVo);
        }
        return historyVoList;
    }

    private HistoryVo copy(History history) {
        HistoryVo historyVo = new HistoryVo();
        historyVo.setCreateDate(history.getCreateDate());
        historyVo.setGoods(goodsService.getGoodsById(history.getGoodsId()));
        return historyVo;
    }
}
