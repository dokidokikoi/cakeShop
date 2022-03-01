<template>
  <div class="container">
      <ShowGoods :goodsList="goods"/>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="page.pageNum"
        :page-size="page.pageSize"
        layout="prev, pager, next, jumper"
        background
        :total="total">
        </el-pagination>
  </div>
</template>

<script>
import ShowGoods from '@/components/goods/ShowGoods'

import { getGoods, getGoodsByTypeId } from '@/api/goods'

export default {
    name: 'CategoryGoods',
    data() {
        return {
            goods: [],
            page: {
                pageNum: 0,
                pageSize: 5,
                query: '',
                type: ''
            },
            total: 0,
        }
    },
    methods: {
        handleCurrentChange(newNum) {
            this.page.pageNum = newNum
            this.getData()
        },
        handleSizeChange(newSize) {
            this.page.pageSize = newSize
            this.getData()
        },
        getGoods() {
            getGoods(this.page).then(res => {
              this.goods = res.data.data
              this.total = res.data.total
            })
        },
        getGoodsByTypeId() {
            if (!this.$route.params.id) return
            getGoodsByTypeId(this.$route.params.id).then(res => {
                this.goods = res.data.data
                this.total = res.data.total
            })
        }
    },
    created() {
        this.getGoodsByTypeId()
    },
    components: {ShowGoods}
}
</script>

<style lang="less" scoped>
    .container {
        width: 70%;
        margin: auto;
    }
</style>