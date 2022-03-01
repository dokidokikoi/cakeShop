<template>
    <header id="header">
        <h2><router-link to="/">Harukaze</router-link></h2>
        <el-menu
        class="fall_menu"
        mode="horizontal"
        :default-active="$route.path"
        
        text-color="#fff"
        active-text-color="rgb(129, 245, 119)"
        router>
            <el-menu-item index="/index">
                <template style="position: relative;">
                    <svg-icon class="icon" icon-class="home" style="font-size: 1.3em;"></svg-icon>
                    <span>首页</span>
                    <div class="bar"></div>
                </template>
            </el-menu-item>
            <el-submenu index="2">
                <template style="position: relative;" slot="title">
                    <svg-icon class="icon" icon-class="category" style="font-size: 1.3em;"></svg-icon>
                    <span>商品分类</span>
                    <div class="bar"></div>
                </template>
                <el-menu-item :index="`/category/${item.id}`" v-for="item in types" :key="item.id" style="color: black">{{item.name}}</el-menu-item>
            </el-submenu>
            <el-menu-item index="/goods/hot">
                <template style="position: relative;">
                    <svg-icon class="icon" icon-class="hot" style="font-size: 1.3em;"></svg-icon>
                    <span>热销</span>
                    <div class="bar"></div>
                </template>
            </el-menu-item>
            <el-menu-item index="/goods/new">
                <template style="position: relative;">
                    <svg-icon class="icon" icon-class="new" style="font-size: 1.3em;"></svg-icon>
                    <span>新品</span>
                    <div class="bar"></div>
                </template>
            </el-menu-item>
        </el-menu>        
        
        <Search class="search"/>

        <el-badge class="car" :value="count" @click.native="toCar">
            <svg-icon icon-class="car" style="font-size: 2em;"></svg-icon>
        </el-badge>

        <div class="login" v-if="!user || JSON.stringify(user) == '{}'">
            <el-button type="primary" size="mini" @click="login">登录/注册</el-button>
        </div>

        <el-dropdown class="avatar" v-if="user && JSON.stringify(user) != '{}'" trigger="hover">
            <el-avatar size="large" :src="user.avatar"></el-avatar>
            <i class="el-icon-caret-bottom"></i>
            <el-dropdown-menu>
                <el-dropdown-item>
                    <router-link to="/profile" style="text-decoration: none !important;color: black;display:block;">Profile</router-link>
                </el-dropdown-item>
                <el-dropdown-item>
                    <router-link to="/history" style="text-decoration: none !important;color: black;display:block;">history</router-link>
                </el-dropdown-item>
                <el-dropdown-item>
                    <router-link to="/order" style="text-decoration: none !important;color: black;display:block;">order</router-link>
                </el-dropdown-item>
                <el-dropdown-item>
                    <router-link to="/admin" v-if="role.name == 'admin'" style="text-decoration: none !important;color: black;display:block;">admin</router-link>
                </el-dropdown-item>
                <hr style="border: soild .5px gray; margin:0;padding:0;">
                <el-dropdown-item>
                    <a href="#" @click.prevent="logout" style="text-decoration: none !important;color: black;display:block;">Logout</a>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>

        <div class="burger btn" @click="handleNavVisable"></div>
        <transition name="fold_menu" appear>
            <div class="fold_menu btn" v-if="navVisable">
                <div class="fold_close" @click="handleNavVisable"></div>
                <ul class="fold_nav">
                    <li v-for="item in navTitle" :key="item.id">
                        <router-link @click.native="handleNavVisable" :to="'/'+item.path">{{item.authName}}</router-link>
                    </li>
                </ul>
            </div>
        </transition>
        
    </header>
</template>

<script>
import Search from '@/components/search/Search'

import { getAllType } from '@/api/type'
import { getRole } from '@/api/role'

export default {
    name: 'NavBar',
    data() {
        return {
            activeIndex: 1,
            navVisable: false,
            types: [],
            user: {},
            count: 0,
            role: ''
        }
    },
    methods: {
        handleNavVisable() {
            this.navVisable = !this.navVisable 
        },
        getAllType() {
            getAllType().then(res => {
                this.types = res.data.data
            })
        },
        getCarCounts() {
            this.$store.dispatch('getCarCount', this.user.id).then(data => {
                this.count = data
            })
        },
        async init() {
            this.getAllType()
            this.user = await this.$store.dispatch('getUserInfo')
            this.getCarCounts()
            this.getRole()
        },
        toCar() {
            this.$router.push(`/car`)
        },
        login() {
            this.$router.push(`/login`)
        },
        logout() {
            this.$store.dispatch('logout')
            this.$router.push('/login')
        },
        getRole() {
            getRole().then(res => {
                this.role = res.data.data
            })
        }
    },
    created() {
        this.init()
    },
    mounted() {
        this.$bus.$on('getCarCounts', this.getCarCounts)

        window.addEventListener("scroll", function() {
            var header = document.querySelector("#header")
            header.classList.toggle("show", window.scrollY > 0)
        })
    },
    components: {Search}
}
</script>

<style lang="less" scoped>
    header .el-menu {
      background: none;
    }

    .el-menu--horizontal>.el-menu-item:not(.is-disabled):focus {
        background: none;
    }

    .show {
        background-color: #78808B
    }

    header:hover {
        background-color: rgb(65, 139, 105, 0);
    }

    header {
        // padding-left: 20px;
        // display: flex;
        // justify-content: space-around;
        // align-items: center;
        width: 100%;
        transition: 0.5s;
        background: none;
        position: relative;
  }

   h2 {
        margin: 0 40px;
        padding: 15px 0;
    a {
        text-decoration: none;
        color: pink;
    }
  }

  .el-menu {
      position: absolute;
      top: 0;
      left: 400px;
  }

  .el-menu li{
      padding-left: 10px;

      .bar {
          position: absolute;
          height: 5px;
          bottom: -3px;
          left: 0;
          right: 0;
          background-color: white;
          transform: translateY(5px);
          transition: all 0.2s ease-in-out;
      }

      .icon {
          margin-right: 5px;
          transform: rotate(20deg);
          transition: all 0.2s ease-in-out;
      }


    &.is-active {
        background-color: rgba(0,0,0,0) !important;
    }

      &.is-active .icon{
        transform: rotate(0) !important;
        transition: all 0.2s ease-in-out;
    }
  }

  .el-menu  /deep/ .el-submenu__title:hover {
      background-color: rgba(0,0,0,0) !important;
  }

  /deep/ i.el-icon-arrow-down:before {
      color: #fff;
  }

  .el-menu .el-menu-item:hover, .el-submenu:hover {
      background-color: rgba(0,0,0,0) !important;

    .bar {
        transform: translateY(0px);
        transition: all 0.2s ease-in-out;
    }

    .icon {
        transform: rotate(0);
        transition: all 0.2s ease-in-out;
      }
  }

  .car {
      position: absolute;
      top: 15px;
      right: 140px;

      &:hover {
          cursor: pointer;
      }
  }

  .login {
      position: absolute;
      top: 15px;
      right: 40px;
  }

  .avatar {
      position: absolute;
      top: 10px;
      right: 60px;

      &:hover {
          cursor: pointer;
      }
  }

  header .search {
      position: absolute;
      top: 0;
      right: 200px;
      margin: 15px 0;
  }

  .btn:hover {
      cursor: pointer;
  }

    .burger {
        width: 25px;
        height: 25px;
        margin-right: 5px;
        // background: url("../../assets/menu_cd_black.png") no-repeat;
        background-size: 100%;
        display: none;
    }

    .fold_close {
        width: 25px;
        height: 25px;
        margin-right: 5px;
        // background: url("../../assets/fold_close.png") no-repeat;
        background-size: 100%;
        position: fixed;
        top: 10px;
        right: 10px;
    }

  .fold_menu {
      display: block;
      position: fixed;
      background-color: black;
      height: 100%;
      width: 70%;
      top: 0;
      right: 0;
      z-index: 100;
      padding: 50px 0;
  }

  .fold_nav {
      font-size: 1.2em;
      list-style-type: none;
      margin: 0;
      padding: 0;

      li {
          padding: 10px 20px;
          a {
              display: block;
              text-decoration: none;
              color: white;
          }
      }

      li:hover {
          background-color: aqua;
          color: black;
          a {
              color: black;
          }
      }
  }

  .fold_menu-enter-active {
      animation: showNav 0.5s linear;
  }

  .fold_menu-leave-active {
      animation: showNav 0.25s linear reverse;
  }

  @keyframes showNav {
    from{
        transform: translateX(100%);
    }
    to{
        transform: translateX(0);
    }
  }

    @media screen and (max-width: 1065px) {
        header {
            justify-content: space-between;
        }

      .fall_menu {
          display: none;
      }

      .burger {
          display: block;
          margin-right: 20px;
      }
    } 

</style>