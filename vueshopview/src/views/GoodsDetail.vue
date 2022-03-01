<template>
  <div class="container">
    <div class="detail">
        <el-carousel height="400px">
            <el-carousel-item v-for="(item,i) in imgs" :key="i">
                <div class="bannar" :style="`background-image: url(${item})`"></div>
            </el-carousel-item>
        </el-carousel>
        <div class="content">
            <h3 style="margin-top: 0">{{goods.name}}</h3>
            <span><span style="color: gray">分类:</span>{{type}}</span>
            <p style="color: gray">{{goods.intro}}</p>
            <p>￥{{goods.price}}</p>
            <div>
                <el-button type="primary">加入购物车</el-button>
            </div>
        </div>
    </div>

    <SubComment :goodsId="this.$route.params.id"/>
  </div>
</template>

<script>
import { getGoodsById } from '@/api/goods'

import SubComment from '@/components/comment/SubComment'

export default {
    name: 'Goodsdetail',
    data() {
        return {
            goods: {},
            imgs: []
        }
    },
    computed: {
        type() {
            let type = ''
            this.goods.types.forEach(e => {
                type += (e.name + ',')
            })
            return type
        }
    },
    methods: {
        getGoodsById() {
            getGoodsById(this.$route.params.id).then(res => {
                this.goods = res.data.data
                this.imgs.push(this.goods.cover)
                this.imgs.push(this.goods.image1)
                this.imgs.push(this.goods.image2)
            })
        }
    },
    created() {
        this.getGoodsById()
    },
    components: {SubComment}
}
</script>

<style lang="less" scoped>
    .container {
        width: 70%;
        margin: auto;
    }

    .detail {
        display: flex;
    }

    .content {
        padding: 10px 30px;
    }

    .el-carousel {
        /deep/ .el-carousel__container {
            width: 100%;
        }
        width: 500px;
    }

    .bannar {
        background-color: aqua;
        height: 100%;
        background-position: center;
        background-size: cover;
    }
</style>

