<template>
    <router-view></router-view>
</template>

<script>
    export default {
        name: 'app',
        mounted() {
            this.$router.push({path:'/main'});
        }
    }
</script>

<style>
    body{
        margin: 0px;
        padding: 0px;
        background: #f4f4f5;
    }
</style>
