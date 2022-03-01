<template>
  <div class="container">
      <router-link to="/index"><h3>HARUKAZE</h3></router-link>
      <el-dropdown class="avatar" v-if="user && JSON.stringify(user) != '{}'" trigger="hover">
            <el-avatar shape="square" size="large" :src="user.avatar"></el-avatar>
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
  </div>
</template>

<script>
import { getRole } from '@/api/role'

export default {
    name: 'Header',
    data() {
        return {
            user: {},
            role: '',
            
        }
    },
    methods: {
        logout() {
            this.$store.dispatch('logout')
            this.$router.push('/login')
        },
        async getRole() {
            this.user = await this.$store.dispatch('getUserInfo')
            getRole().then(res => {
                this.role = res.data.data
            })
        }
    },
    created() {
        this.getRole()
    }
}
</script>

<style scoped lang='less'>
    .container {
        height: 100%;
        width: 100%;
        background-color: blueviolet;
        position: relative;

        h3 {
            display: inline-block;
            margin-top: 12px;
            margin-left: 20px;
            padding: 0;
            font-size: 1.5em;
            color: pink;
        }

        .avatar {
            position: absolute;
            top: 8px;
            right: 20px;
        }

        .el-dropdown:hover {
            cursor: pointer;
        }
    }
</style>