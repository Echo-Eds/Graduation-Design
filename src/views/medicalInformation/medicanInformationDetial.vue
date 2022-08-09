<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <br />
  <el-row>
    <el-col :span="4"></el-col>
    <el-col :span="16">
      <el-page-header content="资讯详情" @click="goBack" />
    </el-col>
    <el-col :span="4"></el-col>
  </el-row>
  <el-row>
    <el-col :span="4"></el-col>
    <el-col :span="16">
      <el-divider></el-divider>
      <div class="body">
        <div class="title">
          <span>{{ title }}</span>
        </div>

        <el-divider
          style="width: 70%; position: relative; left: 15%"
          border-style="dashed"
          ><el-icon><star-filled /></el-icon
        ></el-divider>

        <div class="info">
          <span>发布时间:</span>
          &nbsp;
          <span>{{ time }}</span>
        </div>
        <br />
      </div>
      <el-card class="content">
        <div v-html="content"></div>
      </el-card>

      <!-- 下面是底下的收藏 转发 模块 -->
      <br />
      <el-row>
        <el-col :span="20"></el-col>
        <el-col :span="4">
          <el-button type="plain" round @click="collect"
            ><el-icon><collection-tag /></el-icon>&nbsp;收藏</el-button
          >
          <el-button type="plain" round @click="forward"
            ><el-icon><share /></el-icon>&nbsp;分享</el-button
          >
        </el-col>
      </el-row>
    </el-col>
    <el-col :span="4"></el-col>
  </el-row>
  <el-dialog v-model="dialogVisible" title="请复制当前链接以分享" width="50%" draggable>
    <span>{{ currentURL }}</span>
  </el-dialog>
  <div style="width: 100%; height: 200px"></div>
  <SysBottom></SysBottom>
</template>

<script>
import SysHeader from "../../components/sys/SysHeader.vue";
import IndexCarousel from "../../components/sys/IndexCarousel.vue";
import SysBottom from "../../components/sys/SysBottom.vue";
import request from "@/utils/request.js";

let UID;
export default {
  name: "",
  components: {
    SysHeader,
    IndexCarousel,
    SysBottom,
  },
  data() {
    return {
      addCollForm: {},
      currentURL: "",
      Id: "",
      title: "",
      author: "",
      time: "",
      content: "",
      dialogVisible: false,
    };
  },
  mounted() {
    //异步加载
    this.$nextTick(() => {
      // console.log(this.$route);
      this.Id = this.$route.query.id;
      request
        .get("/medicalInformation/content/" + this.Id)
        .then((res) => {
          console.log(res);
          this.title = res.data.title;
          this.time = res.data.time;
          this.content = res.data.content;
          UID = res.data.userId;
          // 吧结果的id给新的请求做参数
        });
    });
  },
  methods: {
    goBack() {
      this.$router.back();
    },
    forward() {
      this.dialogVisible = true;
      this.currentURL = "http://localhost:9876/" + this.$route.fullPath;
      console.log(this.currentURL);
    },
    /**
     * 添加到我的收藏
     */
    // collect() {
    //   this.addCollForm.userId = UID;
    //   this.addCollForm.titleId = this.Id;
    //   request.post("/myCollection/addToMyCollection", this.addCollForm).then((res) => {
    //     console.log(res);
    //     if (res.code == "0") {
    //       this.$message({
    //         type: "success",
    //         message: "收藏成功",
    //       });
    //     }else {
    //       this.$message({
    //         type: "error",
    //         message: res.msg,
    //       });
    //     }
    //   });
    // },
  },
};
</script>

<style scoped>
.body {
  width: 100%;
  text-align: center;
}
.title {
  width: 100%;
}
.title span {
  font-size: 3em;
  font-family: Georgia, "Times New Roman", Times, serif;
}
.content {
  height: 30vh;
}
</style>
