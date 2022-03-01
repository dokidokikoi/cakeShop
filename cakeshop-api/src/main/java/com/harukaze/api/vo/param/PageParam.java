package com.harukaze.api.vo.param;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * @PackageName: com.harukaze.api.vo.param
 * @ClassName: PageParam
 * @Description:
 * @Author: doki
 * @Date: 22/12/2021 3:19 PM
 */
@Data
public class PageParam {
    private Integer pageNum;
    private Integer pageSize;

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
}
