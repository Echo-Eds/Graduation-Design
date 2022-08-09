<template>
  <div class="global">
    <div class="login">
      <h1>ProfRegister</h1>

      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-position="left"
        label-width="auto"
        size="normal"
      >
        <el-form-item prop="username">
          <el-input
            v-model="form.name"
            placeholder="请输入用户名"
            prefix-icon="User"
            size = "large"
          >
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="form.password"
            placeholder="请输入密码"
            prefix-icon="Key"
            size = "large"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input
            v-model="form.confirm"
            placeholder="请确保两次密码相同"
            prefix-icon="Key"
            size = "large"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            round
            plain
            style="width: 100%; text-align: center"
            @click="register"
            size = "large"
            >register</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request.js";
export default {
  name: "Register",
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
            required: true,
            min: 6,
            max: 10,
            message: "Length should be 6 to 10",
            trigger: "blur",
          },
        ],
        confirm: [
          {
            required: true,
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
    register() {
      if (this.form.password != this.form.confirm) {
        this.$message({
          type: "error",
          message: "两次密码不一致请重新输入",
        });
        return;
      } else {
        //验证值是否通过在提交post请求
        this.$refs["formRef"].validate((valid) => {
          if (valid) {
            request.post("/professor/register", this.form).then((resultr) => {
              if (resultr.code == "0") {
                this.$message({
                  type: "success",
                  message: "注册成功！",
                });
                this.$router.push("/professorLogin");
              } else {
                this.$message({
                  type: "error",
                  message: resultr.msg,
                });
              }
            });
          }
        });
      }
    },
  },
};
</script>

<style scoped>
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
  font-size: 56px;
}
.head {
  text-align: center;
  font-size: 30px;
  color: aliceblue;
  margin: 20px auto;
}
</style>

