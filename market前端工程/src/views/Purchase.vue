<template>
    <el-container>
        <el-aside id="purchaseAside">
            <el-menu>
                <router-link class="purchaseRouter" to="/spgl">
                    <el-menu-item index="1">商品管理</el-menu-item>
                </router-link>
                <router-link class="purchaseRouter" to="ddxt">
                    <el-menu-item index="2">订单系统</el-menu-item>
                </router-link>
                <router-link class="purchaseRouter" to="gyscx">
                    <el-menu-item index="3">供应商查询</el-menu-item>
                </router-link>
            </el-menu>
        </el-aside>
        <el-main id="purchaseMain">
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "Purchase"
    }
</script>

<style scoped>
    #purchaseAside{
        height: 650px;
        border-radius: 10px;
        box-shadow: 0 0 5px black;
        background: #ffffff;
        margin-left: -10px;
        margin-top: -10px;
    }
    #purchaseMain{
        height: 650px;
        box-shadow: 0 0 5px black;
        margin-left: 10px;
        margin-top: -10px;
        background: #ffffff;
    }
    .purchaseRouter{
        text-decoration: none;
        text-align: center;
    }
</style>