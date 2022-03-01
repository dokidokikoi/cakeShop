<template>
  <div class="container">
    <el-carousel height="400px">
      <el-carousel-item v-for="item in imgs" :key="item">
        <div class="bannar" :style="`background-image: url(${item})`"></div>
      </el-carousel-item>
    </el-carousel>
    <div class="bar">
      <h3>热销</h3>
      <router-link to="/goods/hot">更多></router-link>
    </div>
    <ShowGoods :goodsList="hotGoods"/>

    <div class="bar">
      <h3>实惠</h3>
      <router-link to="/goods/cheep">更多></router-link>
    </div>
    <ShowGoods :goodsList="cheepGoods"/>

    <div class="bar">
      <h3>新品</h3>
      <router-link to="/goods/new">更多></router-link>
    </div>
    <ShowGoods :goodsList="newGoods"/>
  </div>
</template>

<script>
import ShowGoods from '@/components/goods/ShowGoods'

import { getGoods } from '@/api/goods'

export default {
    name: 'Index',
    data() {
        return {
            hotGoods: [],
            cheepGoods: [],
            newGoods: [],
            page: {
                pageNum: 0,
                pageSize: 5,
                query: '',
                type: ''
            },
            total: 0,
            imgs: [
              'http://localhost:8080/picture/bannar1.jpg',
              'http://localhost:8080/picture/bannar2.jpg',
              'http://localhost:8080/picture/bannar3.jpg',

            ]
        }
    },
    methods: {
        getGoods() {
          this.page.type = 'hot'
          getGoods(this.page).then(res => {
            this.hotGoods = res.data.data
          })

          this.page.type = 'cheep'
          getGoods(this.page).then(res => {
            this.cheepGoods = res.data.data
          })

          this.page.type = 'new'
          getGoods(this.page).then(res => {
            this.newGoods = res.data.data
          })
        }
    },
    created() {
        this.getGoods()
    },
    components: { ShowGoods }
}
</script>

<style lang="less" scoped>
  .container {
    width: 80%;
    margin: auto;

    .bannar {
      background-color: aqua;
      height: 100%;
      background-position: center;
      background-size: cover;
    }

    .bar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-top: 40px;
      margin-bottom: 10px;
      width: 100%;

      h3 {
        margin: 0;
      }
    }
  }
</style>