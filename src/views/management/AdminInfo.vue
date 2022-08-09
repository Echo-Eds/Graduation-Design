<template>
  <div class="admin">
    <!-- <h1 class="title">Admin</h1> -->
    <el-form ref="formRef" :model="form" label-width="120px">
      <el-form-item label="账号">
        <el-input v-model="form.adminId" disabled></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.adminPassword" ></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.adminNickname" ></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import request from "@/utils/request.js";
export default {
  name: "AdminInfo",
  data() {
    return {
      form: {},
    };
  },
  created() {
    let str = sessionStorage.getItem("admin") || "{}"
    this.form = JSON.parse(str)
    // this.load();
  },
  methods: {
    // load() {
    //     request.get("admin/getAdmin").then((result) => {
    //        console.log(result)
    //     })
    // },
  }
};
</script>

<style scoped>
.title {
  display: flex;
  flex: 1;
  text-align: center;
}
.admin {
  width: 400px;
  margin: 20px auto;
}
</style>
