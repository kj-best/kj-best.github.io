<template>
    <div class="sign-in-container">
        <el-card class="box-card">
            <div class="sign-in-body">
                <div class="sign-in-title">
                    <img src="@/assets/01.png" style="
                                width: 30px;
                                height: 30px;
                                margin: 5px 5px -5px 0;
                                -webkit-user-drag: none;
                                -khtml-user-drag: none;
                                -moz-user-drag: none;
                                user-drag: none;
                            " />
                    <b style="color: black;display: inline-block; margin-bottom: 20px;font-size: 28px;">
                         注册新账号
                    </b>
                </div>
                <el-input placeholder="请输入昵称..." maxlength="30"  v-model="userInfo.nickname" class="sign-in-input" clearable>
                    <template slot="prepend">
                        <div class="iconfont icon-r-user3" style="font-size: 24px;"></div>
                    </template>
                </el-input>
                <el-input placeholder="请输入手机号码..." maxlength="11" v-model="userInfo.accountNumber" class="sign-in-input" clearable>
                    <template slot="prepend">
                        <div class="iconfont icon-r-user2" style="font-size: 24px;"></div>
                    </template>
                </el-input>
                <el-input placeholder="请输入密码..." show-password maxlength="16" v-model="userInfo.userPassword" class="sign-in-input" clearable>
                    <template slot="prepend">
                        <div class="iconfont icon-r-lock" style="font-size: 24px;"></div>
                    </template>
                </el-input>
                <el-input placeholder="请再次输入密码..." show-password maxlength="16" v-model="userPassword2" @keyup.enter.native="signIn" class="sign-in-input" clearable>
                    <template slot="prepend">
                        <div class="iconfont icon-r-lock" style="font-size: 24px;"></div>
                    </template>
                </el-input>
                <div class="sign-in-submit">
                    <el-button type="success" @click="signIn"> 注册</el-button>
                    <el-button type="primary" @click="toLogin" style="margin-left: 20px" > 返回登录</el-button>
                </div>

            </div>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "sign-in",
        data(){
            return{
                userPassword2:'',
                userInfo:{
                    accountNumber:'',
                    userPassword:'',
                    nickname:''
                }
            };
        },
        methods:{
            toLogin(){
                this.$router.replace({path: '/login'});
            },
            signIn(){
                console.log(this.userInfo.nickname);
                if(this.userInfo.accountNumber&&this.userInfo.userPassword&&this.userInfo.nickname){
                    if(this.userInfo.userPassword!==this.userPassword2){
                        this.$message.error('两次输入的密码不相同！');
                    }else {
                        this.$api.signIn(this.userInfo).then(res=>{
                            if(res.status_code===1){
                                this.$message({
                                    message: '注册成功！',
                                    type: 'success'
                                });
                                this.$router.replace({path: '/login'});
                            }else {
                                this.$message.error('注册失败，用户已存！');
                            }
                        }).catch(e=>{
                            console.log(e);
                            this.$message.error('注册失败，网络异常！');
                        })
                    }
                }else{
                    this.$message.error('注册信息未填写完整！');
                }
            }
        }
    }
</script>

<style scoped>
    .sign-in-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        width: 100%;
        background-color: #f1f1f1;
    
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        overflow-y: auto;
        height: 100%;
        background: url("../../assets/background.jpg") center top / cover no-repeat;
    }

    .sign-in-body {
        padding: 30px;
        width: 300px;
        height: 100%;
    }

    .sign-in-title {
        padding-bottom: 30px;
        text-align: center;
        font-weight: 600;
        font-size: 20px;
        color: #409EFF;
    }

    .sign-in-input {
        margin-bottom: 20px;
    }
    .sign-in-submit{
        margin-top: 20px;
        display: flex;
        justify-content: center;
    }
    .login-container{
        padding: 0 10px;
    }
    .login-text{
        color: #409EFF;
        font-size: 16px;
        cursor:pointer;
    }
</style>