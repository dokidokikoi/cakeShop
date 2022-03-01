<template>
  <div class="container">
    <el-card>
        <!-- <el-row>
            <el-col :span="3">
                <el-select v-model="typeId" clearable  placeholder="请选择商品分类" @clear="getGoods()">
                    <el-option
                    v-for="item in types"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="8">
                <el-input placeholder="请输入内容" v-model="page.query" @keydown.enter.native="search" clearable @clear="getGoods()">
                    <el-button slot="append" icon="el-icon-search" @click="search()"></el-button>
                </el-input>
            </el-col>
            <el-col :span="4">
                <el-button type="primary" @click="add">商品上架</el-button>
            </el-col>
        </el-row> -->

        <el-table
        :data="orders"
        style="width: 100%"
         border stripe>
            <el-table-column type="expand">
                <template slot-scope="scope">
                    <el-table
                    :data="scope.row.orderItemVos"
                    style="width: 100%"
                    border stripe>
                        <el-table-column type="index" label="#"></el-table-column>
                        <el-table-column label="封面" :width="150">
                            <template slot-scope="scope">
                                <el-image
                                style="width: 100px; height: 100px"
                                :src="`${scope.row.goods.cover}`"
                                fit="cover"></el-image>           
                            </template>
                        </el-table-column>
                        <el-table-column prop="name" label="商品名" :width="180">
                            <template slot-scope="scope">
                                {{scope.row.goods.name}}        
                            </template>
                        </el-table-column>
                        <el-table-column label="描述" :width="390">
                            <template slot-scope="scope">
                                {{scope.row.goods.intro | ellipsis}}                     
                            </template>
                        </el-table-column>
                        <el-table-column prop="stock" label="库存数量" :width="90">
                            <template slot-scope="scope">
                                {{scope.row.goods.stock}}        
                            </template>
                        </el-table-column>
                        <el-table-column prop="sale" label="销量" :width="90">
                            <template slot-scope="scope">
                                {{scope.row.goods.sale}}        
                            </template>
                        </el-table-column>
                        <el-table-column prop="price" label="价格" :width="90">
                            <template slot-scope="scope">
                                {{scope.row.goods.price}}        
                            </template>
                        </el-table-column>
                    </el-table>
                </template>
            </el-table-column>
            <el-table-column type="index" label="#"></el-table-column>
            <!-- <el-table-column label="下单用户账号" :width="150">
                <template slot-scope="scope">
                    <el-image
                    style="width: 100px; height: 100px"
                    :src="`http://localhost:8080${scope.row.cover}`"
                    fit="cover"></el-image>           
                </template>
            </el-table-column> -->
            <el-table-column prop="name" label="下单用户账号" :width="120">
                <template slot-scope="scope">
                    {{scope.row.user.account}}
                </template>
            </el-table-column>
            <el-table-column prop="name" label="收货人姓名" :width="120"></el-table-column>
            <el-table-column prop="address" label="收货地址" :width="180"></el-table-column>
            <el-table-column prop="phone" label="收货人手机号" :width="130"></el-table-column>
            <el-table-column prop="amount" label="商品数" :width="70"></el-table-column>
            <el-table-column prop="total" label="总款" :width="60"></el-table-column>
            <el-table-column prop="status" label="状态" :width="90">
              <template slot-scope="scope">
                    <el-tag v-if="scope.row.status == 1">已付款</el-tag>   
                    <el-tag type="info" v-else-if="scope.row.status == 0">待付款</el-tag> 
                    <el-tag type="danger" v-else>已取消</el-tag>             
                </template>
            </el-table-column>
            <el-table-column label="创建时间" :width="170">
                <template slot-scope="scope">
                    {{scope.row.createDate | dateFormat}}                     
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.status != 3" icon="el-icon-edit" type="warning" size="mini" @click="cancel(scope.row.id)">取消订单</el-button>
                    <el-button v-else icon="el-icon-edit" type="primary" size="mini" @click.native="resume(scope.row.id)">恢复订单</el-button>
                    <el-button icon="el-icon-delete" type="danger" size="mini" @click="deleteOrder(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>

     <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.pageNum"
      :page-sizes="[5, 10]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import { getAllOrderList, cancelOrder, deleteOrder, resume } from '@/api/order'

export default {
    name: "AdminOrder",
    data() {
      return {
        orders: [],
        page: {
          pageNum: 0,
          pageSize: 5,
        },
        total: 0,
      }
    },
    methods: {
      getAllOrderList() {
        getAllOrderList(this.page).then(res => {
          this.orders = res.data.data
          this.total = res.data.total
        })
      },
      handleSizeChange(newSize) {
          this.page.pageSize = newSize
          this.getAllOrderList()
      },
      handleCurrentChange(newNum) {
          this.page.pageNum = newNum
          this.getAllOrderList()
      },
      cancel(id) {
        cancelOrder(id).then(() => {
          this.$message.success("撤销订单成功")
          this.getAllOrderList()
        })
      },
      deleteOrder(id) {
        this.$confirm('此操作将删除该订单, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            deleteOrder(id).then(() => {
              this.$message.success("删除订单成功")
              this.getAllOrderList()
            })
        }).catch(err => {
            this.$message.info('已取消')
        })
      },
      resume(id) {
        resume(id).then(() => {
          this.$message.success("恢复成功")
        })
      }
    },
    created() {
      this.getAllOrderList()
    }
}
</script>

<style>

</style>