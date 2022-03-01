<template>
  <div class="container">
    <el-card>
        <el-row>
            <el-col :span="3">
                <el-select v-model="typeId" clearable  placeholder="请选择商品分类" @clear="getGoods()" @change="getGoodsByType">
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
        </el-row>

        <el-table
        :data="goodsList"
        style="width: 100%"
         border stripe>
            <el-table-column prop="id" label="id" :width="50"></el-table-column>
            <el-table-column label="封面" :width="150">
                <template slot-scope="scope">
                    <el-image
                    style="width: 100px; height: 100px"
                    :src="`${scope.row.cover}`"
                    fit="cover"></el-image>           
                </template>
            </el-table-column>
            <el-table-column prop="name" label="商品名" :width="180"></el-table-column>
            <el-table-column label="描述" :width="340">
                <template slot-scope="scope">
                    {{scope.row.intro | ellipsis}}                     
                </template>
            </el-table-column>
            <el-table-column prop="stock" label="库存数量" :width="90"></el-table-column>
            <el-table-column prop="sale" label="销量" :width="90"></el-table-column>
            <el-table-column prop="price" label="价格" :width="90"></el-table-column>
            <el-table-column prop="status" label="状态" :width="70">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.stock > 0 && scope.row.status == 1">有货</el-tag>
                    <el-tag v-else-if="scope.row.stock == 0 && scope.row.status == 1" type="warning">无售</el-tag>    
                    <el-tag v-else type="danger">下架</el-tag>    
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button icon="el-icon-edit"  type="primary" size="mini" @click="toEdit(scope.row.id)">修改</el-button>
                    <el-button icon="el-icon-delete" type="danger" size="mini" v-if="scope.row.status == 1" @click="deleteGoods(scope.row.id)">下架</el-button>
                    <el-button icon="el-icon-delete" type="info" size="mini"  v-else @click="resumeGoods(scope.row.id)">恢复</el-button>
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
import { getAllGoods, deleteGoods, getGoodsByTypeId, resumeGoods } from '@/api/goods'
import { getAllType } from '@/api/type'

export default {
    name: 'AdminGoods',
    data() {
        return {
            goodsList: [],
            page: {
                pageNum: 0,
                pageSize: 5,
                query: ''
            },
            total: 0,
            types: [],
            typeId: ''
        }
    },
    methods: {
        getGoods() {
            getAllGoods(this.page).then(res => {
                this.goodsList = res.data.data
                this.total = res.data.total
            })
        },
        getAllType() {
            getAllType().then(res => {
                this.types = res.data.data
            })
        },
        handleSizeChange(newSize) {
            this.page.pageSize = newSize
            this.getGoods()
        },
        handleCurrentChange(newNum) {
            this.page.pageNum = newNum
            this.getGoods()
        },
        search() {
            let that = this
            this.page.pageNum = 1
            this.getGoods()
        },
        add() {
            this.$router.push('/admin/goods/add')
        },
        toEdit(id) {
            this.$router.push(`/admin/goods/edit/${id}`)
        },
        deleteGoods(id) {
            this.$confirm('此操作将删除该商品, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deleteGoods(id).then(() => {
                        this.$message.success("删除成功")
                        this.getGoods()
                    }).catch(err => {
                        this.$message.info(err)
                    }) 
                }).catch(err => {
                    this.$message.info('已取消')
                })
        },
        getGoodsByType() {
            if (!this.typeId) return
            getGoodsByTypeId(this.typeId).then(res => {
                this.goodsList = res.data.data
                this.total = res.data.total
            })
        },
        resumeGoods(id) {
            resumeGoods(id).then(() => {
                this.getGoods()
                this.$message.success("恢复成功")
            })
        }
    },
    created() {
        this.getGoods()
        this.getAllType()
    },
    activated() {
        this.getGoods()
    }
}
</script>

<style>

</style>