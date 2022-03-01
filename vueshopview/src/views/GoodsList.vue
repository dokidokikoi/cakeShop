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

import { getGoods } from '@/api/goods'

export default {
    name: 'GoodsList',
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
        getHot() {
            getGoods(this.page).then(res => {
              this.goods = res.data.data
              this.total = res.data.total
            })
        },
        getCheep() {
            getGoods(this.page).then(res => {
              this.goods = res.data.data
              this.total = res.data.total
            })
        },
        getGoods() {
            getGoods(this.page).then(res => {
              this.goods = res.data.data
              this.total = res.data.total
            })
        },
        getData() {
            this.page.type = this.$route.params.type
            this.page.query = this.$route.query.query
            getGoods(this.page).then(res => {
              this.goods = res.data.data
              this.total = res.data.total
            })
        },
        handleCurrentChange(newNum) {
            this.page.pageNum = newNum
            this.getData()
        },
        handleSizeChange(newSize) {
            this.page.pageSize = newSize
            this.getData()
        }
    },
    created() {
        this.getData()
    },
    components: {ShowGoods}
}
</script>

<style lang="less" scoped>
    .container {
        width: 70%;
        margin: auto;
    }

    .el-pagination {
        display: block;
    }
</style>