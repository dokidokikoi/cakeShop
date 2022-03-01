package com.harukaze.api.vo.param;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: GoodsPageParam
 * @Description:
 * @Author: doki
 * @Date: 22/12/2021 3:25 PM
 */
@Data
public class GoodsPageParam {
    private Integer pageNum;
    private Integer pageSize;
    private String query;
    private String type;

    public Integer getPageNum() {
        if (pageNum == null) {
            return 0;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize == 0) {
            return 5;
        }
        return pageSize;
    }

    public String getQuery() {
        if (!StrUtil.isBlank(query)) {
            return "%" + query + "%";
        }
        return null;
    }
}
