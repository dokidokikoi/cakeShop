<template>
    <div>
        <div class="comment_body">
            <!-- <PublishComment :articleId="articleId"/> -->
            <CommentItem v-for="comment in comments" :key="comment.id" :comment="comment">
                    <PublishComment slot="recover" v-if="isRecover == comment.id" :parentId="comment.id" :goodsId="goodsId" :toUser="comment.user.id">
                        <el-button size="mini" type="info" style="float: right;" @click="cancel">取消</el-button>
                    </PublishComment>
                    <ul style="margin-left: 50px;">
                        <CommentItem v-for="item in comment.children" :key="item.id" :comment="item">
                            <PublishComment slot="recover"  v-if="isRecover == item.id" :parentId="comment.id" :goodsId="goodsId" :toUser="item.user.id">
                                <el-button size="mini" type="info" style="float: right;" @click="cancel">取消</el-button>
                            </PublishComment>
                        </CommentItem>
                    </ul>
            </CommentItem>
        </div>
        
    </div>
  
</template>

<script>
import { getComments } from '@/api/comment'
import CommentItem from '@/components/comment/CommentItem'
import PublishComment from '@/components/comment/PublishComment'

export default {
    name: 'SubComment',
    data() {
        return {
            comments:[],
            isRecover: ''
        }
    },
    props: ['goodsId'],
    methods: {
        getComment() {
            getComments(this.goodsId).then(res => {
                this.comments = res.data.data
            })
        },
        cancel() {
            this.isRecover = ''
        },
    },
    created() {
        this.getComment()
    },
    mounted() {
        this.$bus.$on('showRecover', (id) => {
            this.isRecover = id
        })
        this.$bus.$on('flush', () => {
            this.getComment()
        })
    },
    components: {CommentItem, PublishComment},
    
}
</script>

<style lang="less" scoped>
    .comment_body {
        background-color: rgba(255, 255, 255, 0.9);
        border-radius: 4px;
        padding: 20px;
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    }

    .publish {
        margin-bottom: 20px;
    }
</style>