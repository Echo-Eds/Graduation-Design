<template>
  <div class="Location">
    <div class="info_title">
      <span>releaseInformation</span>
      <br />
      <span>发布资讯</span>
    </div>
  </div>
  <el-row>
    <el-col :span="6"></el-col>
    <el-col :span="12">
      <div>
        <el-form :model="form">
          <el-form-item prop="title">
            <el-input placeholder="请输入标题" v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item prop="content">
            <div id="richText"></div>
          </el-form-item>
        </el-form>
      </div>
      <div style="text-align: right">
        <el-button type="primary" round size="large" style="width: 150px" @click="upload"
          >提交问题</el-button
        >
      </div>
    </el-col>
    <el-col :span="6"></el-col>
  </el-row>
</template>

<script>
import request from "@/utils/request.js";

import E from "wangeditor";
let editor;

export default {
  name: "",
  data() {
    return {
      form: {},
      prof: this.$cookies.get("professor"),
    };
  },
  mounted() {
    this.load()
  },
  methods: {
    load() {
        this.form = {}
        this.$nextTick(() => {
      editor = new E("#richText");
      editor.create();
    });
    },
    upload() {
      const content = editor.txt.html();
      console.log(this.prof.id);
      let date = new Date();
      this.form.time = date;
      this.form.professorId = this.prof.id;
      this.form.author = this.prof.name;
      this.form.content = content
      request.post("/medicalInformation/insertContent", this.form).then((res) => {
        if (res.code == "0") {
          this.$message({
            type: "success",
            message: "资讯提交成功",
            showClose: true,
            center: true,
          });
          this.form = {}
          location = location
        } else {
          this.$message({
            type: "error",
            message: res.msg,
            showClose: true,
            center: true,
          });
        }
      });
    },
  },
};
</script>

<style scoped>
.Location {
  width: 900px;
  margin: 20px auto;
  text-align: center;
}
.info_title span {
  display: flex;
  justify-content: center;
  font-size: 24px;
  font-weight: 400;
}
.myLocat {
  height: 400px;
}
</style>
