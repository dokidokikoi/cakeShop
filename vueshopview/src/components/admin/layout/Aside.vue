<template>
  <div class="container">
      <el-menu
      :default-active="$route.path"
      unique-opened
      background-color="#304156"
      text-color="#fff"
      active-text-color="aqua"
      :collapse-transition='false'
      :collapse="isCollapse"
      router>
      <div class="hamburger" @click="toggleCollapse">
          <i :class="isCollapse? 'el-icon-s-unfold':'el-icon-s-fold'"></i>
      </div>
      <el-submenu index="1">
        <template slot="title">
          <svg-icon icon-class="goods" style="font-size:1.3em; margin-right:5px"></svg-icon>
          <span>商品管理</span>
        </template>
          <el-menu-item index="/admin/goods">
              <template slot="title">
            <!-- <i :class="i.icon"></i> -->
            <span>商品管理</span>
            </template>
          </el-menu-item>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <svg-icon icon-class="order" style="font-size:1.3em; margin-right:5px"></svg-icon>
          <span>订单管理</span>
        </template>
          <el-menu-item index="/admin/order">
              <template slot="title">
            <!-- <i :class="i.icon"></i> -->
            <span>订单管理</span>
            </template>
            </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>

export default {
    name: 'Aside',
    data() {
        return {
            menuList: [],
            isCollapse: false,
        }
    },
    
    methods: {
        toggleCollapse() {
            this.isCollapse = !this.isCollapse
        }
    },
    created() {
        
    }
}
</script>

<style scoped lang="less">
    .container {
        height: 100%;
        overflow: hidden;
    }

    .hamburger {
        background-color: #263342;
        color: white;
        display: flex;
        justify-content: center;
        i {
            font-size: 1.3em;
            display: inline-block;
            margin: 10px;
        }
    }

    .el-menu {
        height: 100%;
        border-right: none;
    }

    .hamburger:hover {
        cursor: pointer;
    }

</style>