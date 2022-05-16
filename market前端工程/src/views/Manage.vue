<template>
    <el-container>
        <el-aside id="manageAside">
            <el-menu>
                <router-link class="manageRouter" to="/ddsp">
                    <el-menu-item index="1">订单审批</el-menu-item>
                </router-link>
            </el-menu>
        </el-aside>
        <el-main id="manageMain">
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "Manage"
    }
</script>

<style scoped>
    #manageAside{
        height: 650px;
        border-radius: 10px;
        box-shadow: 0 0 5px black;
        background: #ffffff;
        margin-left: -10px;
        margin-top: -10px;
    }
    #manageMain{
        height: 650px;
        box-shadow: 0 0 5px black;
        margin-left: 10px;
        margin-top: -10px;
        background: #ffffff;
    }
    .manageRouter{
        text-decoration: none;
    }
</style>