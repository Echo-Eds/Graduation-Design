<template>
  <div class="global">
    <div class="login">
      <h1>Professor</h1>
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-position="left"
        label-width="auto"
        size="normal"
      >
        <el-form-item label="" prop="username">
          <el-input
            v-model="form.name"
            placeholder="请输入用户名"
            prefix-icon="User"
            size = "large"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="" prop="password">
          <el-input
            v-model="form.password"
            placeholder="请输入密码"
            prefix-icon="Key"
            show-password
            size = "large"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            round
            plain
            style="width: 100%; text-align: center"
            @click="login"
            size = "large"
            >login</el-button
          >
        </el-form-item>
      </el-form>
        <el-button type="text" @click="gotoregister" style="color:#FFEBCD	">还没账号？去注册=>></el-button>
      
    </div>
  </div>
</template>

<script>
import request from "@/utils/request.js";
import cookies from "vue-cookies";
export default {
  data() {
    return {
      form: {},
      rules: {
        name: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          {
            min: 6,
            max: 10,
            message: "Length should be 6 to 10",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    login() {
      //表单验证
      this.$refs["formRef"].validate((valid) => {
        if (valid) {
          // 验证通过执行请求
          request.post("/professor/login", this.form).then((res) => {
            if (res.code == "0") {
              this.$message({
                type: "success",
                message: "登录成功",
              });
              console.log(res);
              //   缓存专家信息
              this.$cookies.set("professor", JSON.stringify(res.data))
              
              this.$router.push("/sys/index"); //登录之后跳转
            } else {
              this.$message({
                type: "error",
                message: res.msg,
              });
            }
          });
        }
      });
    },
    gotoregister() {
      this.$router.push("/professorRegister")
    }
  },
};
</script>

<style>
@import url("../../assets/css/el-input.css");

.global {
  width: 100vw;
  height: 100vh;
  background: antiquewhite;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}
.global::before {
  position: absolute;
  content: "";
  display: block;
  width: 100%;
  height: 100%;
  background-image: linear-gradient(to bottom right,
  rgb(	135,206,235),rgb(30,144,255),rgb(	0,0,128));
  filter: blur(4px);
  
}
.login {
  position: relative;
  top: -100px;
  width: 400px;
  color: #fff;
  margin: 200px auto;
}

.login>h1 {
  margin: 50px 0 10px 0;
  font-size: 64px;
}

.head {
  text-align: center;
  font-size: 30px;
  color: aliceblue;
  margin: 20px auto;
}
</style>