<template>
  <div class="container">
      <div class="order" v-for="order in orders" :key="order.id">
          <p><span style="color:gray">收件人姓名：</span>{{order.name}}</p>
          <p><span style="color:gray">收件人电话：</span>{{order.phone}}</p>
          <p><span style="color:gray">收件人地址：</span>{{order.address}}</p>
          <p><span style="color:gray">创建时间：</span>{{order.createDate | dateFormat}}</p>
          <p><span style="color:gray">总计：</span>￥{{order.total}}</p>
          <div>
              <span>状态：</span>
              <el-tag type="primary" v-if="order.status == 1">已付款</el-tag>
              <el-tag type="info" v-else-if="order.status == 0">待付款</el-tag>
              <el-tag type="warning" v-else>已取消</el-tag>
          </div>
          <div class="pay">
              <el-button type="primary" v-if="order.status == 0" @click="pay(order.id)">付款</el-button>
              <el-button type="warning" v-if="order.status != 3" @click="cancel(order.id)">取消订单</el-button>
              <el-button type="danger" @click="deleteOrder(order.id)">删除订单</el-button>
          </div>
          <ul>
              <li v-for="item in order.orderItemVos" :key="item.id">
                  <div class="goods">
                        <div class="img" :style="`background-image: url(${item.goods.cover})`" @click="toDetail(item.goods.id)"></div>
                        <div class="content">
                            <p>{{item.goods.name}}</p>
                            <p>￥{{item.goods.price}}</p>
                            <el-button type="primary" v-if="order.status == 1" @click="showPublish(item.id, item.goods.id)" style="position:relative; z-index: 10">评论</el-button>
                        </div>
                  </div>
                  <div class="publish" style="width:100%; margin-top: 20px" v-show="isShow == item.id">
                    <span style="text-align: left; font-size: 1.3em">发表评论</span>
                    <slot></slot>
                    <el-form :model="commentForm" :rules="rules" ref="commentForm">
                        <el-form-item label="评论" prop="content">
                            <el-input type="textarea" 
                            v-model="commentForm.content" 
                            :autosize="{ minRows: 4, maxRows: 4}"
                            placeholder="不写点什么吗..."></el-input>
                        </el-form-item>
                    </el-form>
                    <div style="display: flex;">
                        <el-button type="success" style="width: 100px" @click="recover">点击发表</el-button>
                    </div>
                </div>
              </li>
          </ul>
          
      </div>
  </div>
</template>

<script>
import { getOrderList, pay, cancelCurrentOrder, deleteCurrentOrder } from '@/api/order'
import { publish } from '@/api/comment'

export default {
    name: "Order",
    data() {
        return {
            orders: [],
            commentForm: {
                content: '',
                goodsId: ''
            },
            rules: {
                content: [
                    { required: true, message: '居然什么都不写...', trigger: 'blur' },
                ],
                'userVo.nickName': [
                    { required: true, message: '无名氏吗...', trigger: 'blur' },
                ],
                email: [
                    { message: '留个联系方式好不好', trigger: 'blur' },
                ]
            },
            isShow: ''
        }
    },
    methods: {
        getOrders() {
            getOrderList().then(res => {
                this.orders = res.data.data
            })
        },
        toDetail(id) {
            this.$router.push(`/detail/${id}`)
        },
        showPublish(id, goodsId) {
            this.commentForm.goodsId = goodsId
            this.isShow = id
        },
        recover() {
            publish(this.commentForm).then(() => {
                this.$message.success("发表成功")
            })
        },
        pay(id) {
            pay(id).then(() => {
                this.$message.success("付款成功")
                this.getOrders()
            })
        },
        cancel(id) {
            cancelCurrentOrder(id).then(() => {
                this.getOrders()
            })
        },
        deleteOrder(id) {
            this.$confirm('此操作将删除该订单, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                deleteCurrentOrder(id).then(() => {
                    this.getOrders()
                    this.$message.success("删除成功")
                })
            }).catch(err => {
                this.$message.info('已取消')
            })
        }
    },
    created() {
        this.getOrders()
    }
}
</script>

<style lang="less" scoped>
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 70%;
        margin: auto;
    }

    .order {
        background-color: #fff;
        padding: 10px;
        margin-bottom: 20px;
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
        border-radius: 4px;
    }

    .goods {
        padding: 10px;
        margin-top: 20px;
        background-color: rgb(243, 217, 234);
        border-radius: 4px;
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
        display: flex;

        &:hover {
            cursor: pointer;
        }
    }

    .content {
        padding: 0 20px;
    }

    .pay {
        display: flex;
        width: 100%;
        justify-content: flex-end;
    }

    .img {
        width: 40%;
        height: 150px;
        background-position: center;
        background-size: cover;
    }
</style>