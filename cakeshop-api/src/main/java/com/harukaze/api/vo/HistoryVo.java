package com.harukaze.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.harukaze.api.entity.Goods;
import lombok.Data;

/**
 * @PackageName: com.harukaze.api.vo
 * @ClassName: HistoryVo
 * @Description:
 * @Author: doki
 * @Date: 25/12/2021 11:08 AM
 */
@Data
public class HistoryVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private GoodsVo goods;

    private Long createDate;
}
