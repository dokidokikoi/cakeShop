<template>
  <el-card>
      <el-form :model="goods" ref="addForm" :rules="rules" label-width="80px">
            <el-form-item prop="name" label="商品名">
                <el-input v-model="goods.name"></el-input>
            </el-form-item>
            <el-form-item prop="cover" label="封面">
                <el-upload
                title="上传封面"
                class="avatar-uploader"
                action="http://localhost:9000/upload"
                name="image"
                :show-file-list="false"
                :on-success="handleCoverSuccess"
                :before-upload="beforeCoverUpload">
                <img v-if="goods.cover" :src="goods.cover" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>
            <el-form-item prop="image1" label="详情图片1">
                <el-upload
                title="上传详情图片1"
                class="avatar-uploader"
                action="http://localhost:9000/upload"
                name="image"
                :show-file-list="false"
                :on-success="handleImage1Success"
                :before-upload="beforeImage1Upload">
                <img v-if="goods.image1" :src="goods.image1" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>
            <el-form-item prop="image2" label="详情图片2">
                <el-upload
                title="上传详情图片2"
                class="avatar-uploader"
                action="http://localhost:9000/upload"
                name="image"
                :show-file-list="false"
                :on-success="handleImage2Success"
                :before-upload="beforeImage2Upload">
                <img v-if="goods.image2" :src="goods.image2" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>
            <el-form-item prop="price" label="价格">
                <el-input type="number" v-model.number="goods.price"></el-input>
            </el-form-item>
            <el-form-item prop="intro" label="简介">
                <el-input type="textarea" v-model="goods.intro"></el-input>
            </el-form-item>
            <el-form-item prop="stock" label="上架数量">
                <el-input type="number" v-model.number="goods.stock"></el-input>
            </el-form-item>
            <el-form-item prop="types" label="商品分类">
                <el-select v-model="goods.types" multiple placeholder="请选择">
                    <el-option
                    v-for="item in types"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-button type="primary" @click="add">添加商品</el-button>
        </el-form>
  </el-card>
</template>

<script>
import { getAllType } from '@/api/type'
import { saveGoods } from '@/api/goods'

export default {
    name: 'AddGoods',
    data() {
        return {
            goods: {
                name: '',
                cover: '',
                image1: '',
                image2: '',
                price: '',
                intro: '',
                stock: '',
                types: []
            },
            types: [],
            rules: {
                cover: [
                    { required: true, message: '请输入活动名称', trigger: 'blur' }
                ],
                types: [
                    { required: true, message: '请输入活动名称', trigger: 'blur' }
                ],
                name: [
                     { required: true, message: '请输入商品名称', trigger: 'blur' },
                ],
                price: [
                    { required: true, message: '商品价格不能为空', trigger: 'blur' },
                    { type:'number', min: 0, message: '商品价格不能小于0', trigger: 'blur' }
                ],
                intro: [
                    { required: true, message: '商品简介不能为空', trigger: 'blur' },
                ],
                stock: [
                    { required: true, message: '商品数量不能为空', trigger: 'blur' },
                    { type:'number', min: 0, message: '商品数量不能小于0', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        getAllType() {
            getAllType().then(res => {
                this.types = res.data.data
            })
        },
        handleCoverSuccess(res, file) {
            this.goods.cover = res.data
        },
        beforeCoverUpload(file) {
            let isJPG = file.type === 'image/jpeg';
            if (!isJPG) {
                isJPG = file.type === 'image/png'
            }
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        handleImage1Success(res, file) {
            this.goods.image1 = res.data
            console.log(res)
        },
        beforeImage1Upload(file) {
            let isJPG = file.type === 'image/jpeg';
            if (!isJPG) {
                isJPG = file.type === 'image/png'
            }
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        handleImage2Success(res, file) {
            this.goods.image2 = res.data
            console.log(res)
        },
        beforeImage2Upload(file) {
            let isJPG = file.type === 'image/jpeg';
            if (!isJPG) {
                isJPG = file.type === 'image/png'
            }
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        add() {
            this.$refs.addForm.validate(valid => {
                if (!valid) return
                if(!this.goods.cover) return this.$message("封面不能为空")
                if(this.goods.types.length === 0) return this.$message("商品分类不能为空")

                saveGoods(this.goods).then(() => {
                    this.$message.success("添加成功")
                })
            })
        }
    },
    created() {
        this.getAllType()
    }
}
</script>

<style lang="less" scoped>
    .el-form {
        width: 70%;
        margin: auto;
    }

    .avatar-uploader {
        width: 100px;
        height: 100px;
    }

    /deep/ .el-upload {
        border: 1px dashed gray !important;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 100px;
        height: 100px;
        line-height: 100px;
        text-align: center;
    }
    .avatar {
        width: 100px;
        height: 100px;
        display: block;
    }
</style>