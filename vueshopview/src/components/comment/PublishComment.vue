<template>
    <div class="publish">
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
</template>

<script>
import { recover } from '@/api/comment'

export default {
    name: 'PublishComment',
    data() {
        return {
            commentForm: {
                content: '',
                parentId: this.parentId,
                goodsId: this.goodsId,
                toUid: this.toUser
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
            }
        }
    },
    methods: {
        recover() {
            this.$refs.commentForm.validate(valid => {
                if (!valid) return 
                recover(this.commentForm).then(() => {
                    this.$message.success("成功")
                    this.$bus.$emit('flush')
                }).catch(err => {
                    this.$message.error(err)
                })
            })
            this.$refs.commentForm.resetFields()
        }
    },
    props: ["parentId", "goodsId", "toUser"]
}
</script>

<style lang="less" scoped>
</style>