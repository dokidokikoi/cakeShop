<template>
  <ul class="container">
    <li class="card" v-for="item in histories" :key="item.id" @click="toDetail(item.goods.id)">
      <p style="margin: 0"><svg-icon icon-class="time"></svg-icon>{{item.createDate | dateFormat}}</p>
      <div class="content">
        <div class="img" :style="`background-image: url(${item.goods.cover});`"></div>
        <div style="margin: 0 20px;">
          <p>{{item.goods.name}}</p>
          <p>￥{{item.goods.price}}</p>
        </div>
      </div>
    </li>
  </ul>
</template>

<script>
import { getHistories } from '@/api/history'

export default {
    name: 'History',
    data() {
      return {
        histories: []
      }
    },
    methods: {
      getHistories() {
        getHistories().then(res => {
          this.histories = res.data.data
        })
      },
      toDetail(id) {
        this.$router.push(`/detail/${id}`)
      }
    },
    created() {
      this.getHistories()
    }
}
</script>

<style lang="less" scoped>
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  li:hover {
    cursor: pointer;
  }

  .container {
    width: 70%;
    margin: auto;
  }

  .card {
    background-color: #fff;
    padding: 10px;
    margin-bottom: 20px;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    border-radius: 4px;
  }

  .img {
     height: 250px; 
     width: 50%;
     background-position: center;
     background-size: cover;
  }
  
  .content {
    display: flex;
  }
</style>