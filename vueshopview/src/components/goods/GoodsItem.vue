<template>
  <li v-if="goods.stock > 0 && goods.status != 0">
      <div class="content">
          <div class="bgimg" :style="`background-image: url(${goods.cover})`">
              <div class="menu">
                   <div class="icon" @click="toCar">
                       <svg-icon icon-class="car" style="font-size: 1.3em;"></svg-icon>
                   </div>
                   <div class="icon" @click="toDetail">
                       <svg-icon icon-class="eye" style="font-size: 1.3em;"></svg-icon>
                   </div>
                   <div class="icon">
                       <svg-icon icon-class="share" style="font-size: 1.3em;"></svg-icon>
                   </div>
              </div>
          </div>
          <div class="bar">
              <span style="color: red">¥ {{goods.price}}</span>
              <span style="color: gray">销量{{goods.sale}}</span>
          </div>
          <p>{{goods.intro | ellipsis(50)}}</p>
      </div>
  </li>
</template>

<script>
import { saveCar } from '@/api/car'

export default {
    name: "GoodsItem",
    data() {
        return {
            info: {
                userId: this.$store.state.user.id,
                goodsId: this.goods.id
            }
        }
    },
    props: ["goods"],
    methods: {
        toDetail() {
            this.$router.push(`/detail/${this.goods.id}`)
        },
        toCar() {
            saveCar(this.info).then(() => {
                this.$bus.$emit('getCarCounts')
            })
        }
    }
}
</script>

<style lang="less" scoped>
    li {
        padding: 10px;
        margin: 0;
        width: 33.3%;
        height: 350px;
        float: left;
        box-sizing: border-box;
    }

    .content {
        height: 100%;

        p {
            margin: 0;
        }
    }

    .bgimg {
        height: 70%;
        background-color: blue;
        margin-bottom: 10px;
        position: relative;
        overflow: hidden;
        background-position: center;
        background-size: cover;

        .menu {
            width: 100%;
            position: absolute;
            bottom: 0;
            display: flex;
            justify-content: center;
            transform: translateY(55px);
            transition: .3s linear;
            z-index: 2;

            .icon {
                padding: 10px;
                margin: 10px;
                background-color: #000;
                border-radius: 4px;
            }
        }
    }

    .bar {
        display: flex;
        justify-content: space-between;
    }

    li:hover {
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 20%);
        cursor: pointer;

        .menu {
            transform: translateY(0);
        }
    }
</style>