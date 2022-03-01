<template>
  <ul class="blog_list">
    <span v-show="false">{{userId}}</span>
    <li class="blog_item" v-for="item in carList" :key="item.id">
      <router-link to="/" class="mask"></router-link>
        <div class="blog">
            <div class="cover" :style="`background-image: url(${item.goods.cover})`"></div>
            <div class="content">
                <div class="avatar">
                    <div class="blog_date" style="font-size:13px; margin-left:10px; margin-bottom:5px">
                        <svg-icon icon-class="time" style="font-size: 1.2em;"></svg-icon>
                        <span>{{item.createDate | dateFormat}}</span>
                    </div>
                </div>
                <h3>{{item.goods.name}}</h3>
                <div style="z-index:100; position: relative">
                  <el-button type="danger" size="mini" @click="deleteCar(item.id)">删除</el-button>
                </div>
            </div>
        </div>
    </li>
    <el-button type="primary" @click.native="saveOrder" v-show="carList.length != 0">提交订单</el-button>
  </ul>
</template>

<script>
import { getCar, deleteCar } from '@/api/car'
import { saveOrder } from '@/api/order'

export default {
    name: 'Car',
    data() {
      return {
        carList: [],
      }
    },
    computed: {
      userId() {
        if (this.$store.state.user.id) {
          this.getCar()
        }
        return this.$store.state.user.id
      }
    },
    methods: {
      getCar() {
        getCar(this.$store.state.user.id).then(res => {
          this.carList = res.data.data
        })
      },
      deleteCar(id) {
        this.$confirm('此操作将删除该商品, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deleteCar(id).then(() => {
                      this.$message.success("删除成功")
                      this.$bus.$emit("getCarCounts")
                      this.getCar()
                      this.$bus.$emin('getCarCounts')
                    })
                }).catch(err => {
                    this.$message.info('已取消')
                })
      },
      saveOrder() {
        let fade = ''
        this.carList.forEach(e => {
          if (e.goods.status == 0 || e.goods.stock <= 0) {
            fade += `${e.goods.name},`
          }
        })
        if (fade) {
          this.$alert(`<b style='color:red'>${fade}</b>已售无,将移出购物车列表`, '提示', {
            dangerouslyUseHTMLString: true,
            confirmButtonText: '确定',
            callback: action => {
              if (action === 'confirm') {
                saveOrder(this.$store.state.user.id).then(() => {
                  this.$message.success("提交成功")
                  this.getCar()
                  this.$bus.$emit('getCarCounts')
                })
              }
            }
          });
        } else {
          saveOrder(this.$store.state.user.id).then(() => {
            this.$message.success("提交成功")
            this.getCar()
            this.$bus.$emit('getCarCounts')
          })
        }
      }

    },
    created() {
      if (this.$store.state.user.id) {
        this.getCar()
      }
    },
    mounted() {
      
    }
}
</script>

<style scoped lang="less">
  ul {
    list-style-type: none;
    width: 700px;
    margin: auto;

    li {
        margin: 0;
        padding: 0;
        margin-bottom: 20px;
        position: relative;

        a {
            text-decoration: none;
            color: #000;
        }
    }

    .blog {
        box-sizing: border-box;
        height: 250px;
        background-color: #fff;
        border: 1px solid #EBEEF5;
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
        border-radius: 4px;
        display: flex;
        padding: 10px;

    .cover {
        width: 60%;
        height: 100%;
        border-radius: 4px;
        background-position: center;
        background-size: cover;
        background-repeat: no-repeat;
        transition: .5s;
    }

    .content {
        padding: 10px;
        position: relative;

        
        h3:hover {
            color: red;
        }
    }

    .content::after {
        content: '';
        position: absolute;
        top: -10px;
        right: -95px;
        background-color: pink;
        width: 4px;
        height: 0px;
        border-radius: 2px;
        transition: .5s;
      }
    }
  }

  .mask {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 10;
  }

.blog_item:hover {
  cursor: pointer;

  .content::after {
      height: 250px;
      box-shadow: #ffc0cb 0px 0px 6px;
  }

  .cover {
      transform: scale(1.05);
      transition: all 0.5s ease-in-out;
  }
 }
</style>