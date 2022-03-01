<template>
  <div class="home_container">
      <el-header>
        <NavBar/>
      </el-header>
      
      <el-container>
        <el-main>
          <router-view :key="new Date().getTime()"></router-view>
        </el-main>
        <el-footer>
          <MyFooter/>
        </el-footer>
      </el-container>
    </div>
</template>

<script>
import NavBar from '@/components/layout/NavBar'
import MyFooter from '@/components/layout/MyFooter'

export default {
  name: 'Home',
  components: {
    NavBar,
    MyFooter
  }
}
</script>

<style scoped lang="less">
    .home_container {
    width: 100%;
    position: relative;
  }

  .el-header {
    margin: 0;
    padding: 0;
    overflow: hidden;
    width: 100%;
    display: flex;
    position: fixed;
    top: 0;
    z-index: 999;
  }


  .el-main {
    min-height: 100vh;
    background-color: rgba(0,0,0,0) !important;
    margin: 0;
    margin-top: 60px;
    padding: 0;
  }
</style>
