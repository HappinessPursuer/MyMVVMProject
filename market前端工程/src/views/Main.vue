<template>
    <div id="main">
        <el-header id="navigate">
            <button v-show="$store.state.loginState" id="userLable">{{$store.state.userCurrent.username}}</button>
            <el-button type="danger" round v-show="!$store.state.loginState" @click="loginDialogVisible=true" id="loginButton">登录</el-button>
        </el-header>
        <el-main>
            <router-view></router-view>
            <p v-show="!$store.state.loginState" id="unLoginMainTips">请登录MarketPlus管理系统</p>
        </el-main>

        <el-dialog title="MarketPlus|请登录" :visible.sync="loginDialogVisible" :center="true" id="loginDialog">
            <el-form :model="form" :rules="rules" ref="loginFormRef">
                <el-form-item prop="idUser">
                    <el-input v-model="form.idUser" prefix-icon="el-icon-user"></el-input>
                </el-form-item>
                <el-form-item prop="password" >
                    <el-input v-model="form.password" prefix-icon="el-icon-key" :suffix-icon="passwordSuffixIcon" :type="passwordType" @mouseover.native="showPassword" @mouseout.native="hidePassword"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="danger" round @click="loginSubmit">登录</el-button>
                <el-button type="info" round @click="loginReset">重置</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'Main',
        methods:{
            showPassword(){
                this.passwordType='text';
                this.passwordSuffixIcon='el-icon-view';
            },//密码框显示数字
            hidePassword(){
                this.passwordType='password';
                this.passwordSuffixIcon='el-icon-check';
            },//密码框隐藏数字
            loginSubmit(){
                this.$refs.loginFormRef.validate(async valid=>{
                    if (valid){
                        const {data:res}=await this.$http.post('/officeuser/login',this.form);
                        console.log(res);
                        if(res.springLoginCheck=='loginIsOk'){
                            this.$message.success('登录成功');//设置登陆成功的消息
                            this.$store.state.userCurrent=res.user;//保存后台返回的user的信息
                            this.$store.state.loginState=true;//设置登录状态为true
                            this.loginDialogVisible=false;//关闭登录对话框
                        }else{
                            this.$message({
                                message: '登录失败',
                                type: 'error'
                            });
                            return false;
                        }
                        if(this.$store.state.userCurrent.position=='purchaser'){
                            this.$router.push({path:'/purchase'});
                            return true;
                        }
                        if(this.$store.state.userCurrent.position=='manager'){
                            this.$router.push({path:'/manage'});
                            return true;
                        }
                    } else {
                        this.$message({
                            message: '请正确输入员工号或密码',
                            type: 'warning'
                        });
                        return false;
                    }
                });
            },//确认登录 验证登录form的内容
            loginReset(){
                this.$refs.loginFormRef.resetFields();
            },//重置登录 重置登录form中的内容
        },
        data(){
            return{
                passwordType:'password',//控制登录密码的显隐
                passwordSuffixIcon:'el-icon-view',
                loginDialogVisible: false,//控制登录对话框的显隐
                form: {
                    idUser:'',
                    username: '',
                    password:'',
                    position: ''
                },//form中保存的是登录form中的内容，从后台获得的user被放在vuex的state中，变量名为userCurrent
                rules:{
                    idUser:[
                        {
                            required:true,
                            message:'请输入员工号',
                            trigger:'blur'
                        }
                    ],
                    password:[
                        {
                            required:true,
                            message:'请输入密码',
                            trigger:'blur'
                        },
                        {
                            min:4,
                            message:'密码长度不能小于4',
                            trigger:'blur'
                        },
                        {
                            max:10,
                            message:'密码长度不能大于10',
                            trigger:'blur'
                        }
                    ]
                },//表单验证的规则
            };
        },
    }
</script>

<style>
    #main {
        width: 100%;
        height: 730px;
    }
    #navigate{
        display: flow;
        box-shadow: 0 0 10px #000000;
        background: #909399;
    }
    #loginButton{
        margin-top: 10px;
        margin-left: 93%;
        box-shadow: 0 0 5px #606266;
    }
    #loginDialog{
        width: 1200px;
        margin-left: 150px;
    }
    #unLoginMainTips{
        font-size: xx-large;
        margin-top: 100px;
        margin-left: 500px;
        visibility: visible;
    }
    #userLable{
        width: 200px;
        height: 45px;
        margin-top: 0px;
        margin-left: 0px;
        border-bottom-left-radius: 50px;
        border-bottom-right-radius: 50px;
        border: none;
        background: #f56c6c;
        color: #ffffff;
        box-shadow: 0 0 10px #606266;
    }
    #logoutButton{
        margin-left: 70%;
    }
</style>
