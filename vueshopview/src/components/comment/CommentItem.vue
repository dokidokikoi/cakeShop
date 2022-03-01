<template>
  <li>
    <div>
      <el-avatar size="medium" :src="comment.user.avatar? comment.user.avatar:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"></el-avatar>
      <div class="info">
        <p class="name">{{comment.user.nickname}}</p>
        <p class="date">{{comment.createDate | dateFormat}}</p>
        <p class="content"><span v-if="comment.toUser" class="callName">@{{comment.toUser.nickname}}</span>{{comment.content}}</p>
        <el-button size="mini" type="primary" @click="recover()" style="margin-bottom: 20px">回复</el-button>
        <slot name="recover"></slot>
      </div>
    </div>
    <slot></slot>
  </li>
</template>

<script>

export default {
    name: 'CommentItem',
    props: ['comment'],
    methods: {
      recover() {
        this.$bus.$emit('showRecover', this.comment.id)
      },
    },
}
</script>

<style scoped lang="less">
  ul,li {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  li {
    border-top: dashed 1px #ddd;
    padding-top: 10px;
  }

  .el-avatar {
    float: left;

    &::after {
      content: '';
      display: table;
      clear: both;
    }
  }

  .info {
    margin-left: 50px;
    padding-bottom: 10px;
  }

  .name, .date {
    margin: 0;
    padding: 0;
  }

  .name {
    font-weight: bold;
  }

  .date {
    font-style: italic;
    font-size: .7em;
  }

  .callName {
    font-weight: bold;
    margin-right: 5px;
  }
</style>