<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <br />
  <el-row>
    <el-col :span="4"></el-col>
    <el-col :span="16">
      <el-page-header content="求助详情" @click="goBack" />
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
          <span>提问人:</span>
          &nbsp;
          <span>{{ author }}</span>
          &nbsp;&nbsp;&nbsp;
          <span>提问时间:</span>
          &nbsp;
          <span>{{ time }}</span>
        </div>
        <br />
      </div>
      <el-card>
        <div v-html="content"></div>
      </el-card>

      <!-- 下面是底下的收藏 转发 模块 -->
      <br />
      <el-row>
        <el-col :span="20">
          <el-button type="primary" round style="width: 100px" @click="handleOpen"
            >回复</el-button
          >
        </el-col>
        <el-col :span="4">
          <el-button type="plain" round @click="collect"
            ><el-icon><collection-tag /></el-icon>&nbsp;收藏</el-button
          >
          <el-button type="plain" round @click="forward"
            ><el-icon><share /></el-icon>&nbsp;分享</el-button
          >
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- 回复信息部分 -->
      <span>回复信息：</span>
      <br />
      <br />
      <el-card v-for="item in RepList" :key="item" style="margin: 5px 0">
        <div>
          <el-row>
            <el-col :span="5">
              <span>{{ item.professorName }}</span>
              &nbsp;
              <span>{{ item.time }}</span>
            </el-col>
          </el-row>
        </div>
        <el-divider style="width: 35%"></el-divider>
        <div>
          <span>{{ item.content }}</span>
        </div>
      </el-card>
    </el-col>
    <el-col :span="4"></el-col>
  </el-row>
  <el-dialog v-model="dialogVisible" title="请复制当前链接以分享" width="50%" draggable>
    <span>{{ currentURL }}</span>
  </el-dialog>
  <el-dialog v-model="dialogVisible1" title="请输入回复内容" width="35%">
    <el-form v-model="form">
      <el-form-item label="回复信息：">
        <el-input
          type="textarea"
          autosize
          placeholder="请输入你要回复的内容"
          v-model="form.content"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible1 = false" round>Cancel</el-button>
        <el-button type="primary" @click="upload" round>提交</el-button>
      </span>
    </template>
  </el-dialog>
  <div style="width: 100%; height: 100px"></div>
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
      dialogVisible1: false,
      //回复部分
      form: {},
      RepList: [],
    };
  },
  mounted() {
    //异步加载
      this.$nextTick(() => {
        // console.log(this.$route);
        this.Id = this.$route.query.id;
        request
          .get("/consult/content", {
            params: {
              titleId: this.Id,
            },
          })
          .then((res) => {
            // console.log(res);
            this.title = res.data.title;
            this.time = res.data.time;
            this.content = res.data.content;

            // 吧结果的id给新的请求做参数
            request
              .get("/consult/getAuthorName", {
                params: {
                  userId: res.data.userId,
                },
              })
              .then((result) => {
                this.author = result.data;
              });
          });
        //获取回复信息
        request
          .get("/consultReply/getRep", {
            params: {
              questionID: this.Id,
            },
          })
          .then((res) => {
            console.log(res);
            let professorNUM = res.data.length;
            for (let flag = 0; flag < professorNUM; flag++) {
              this.RepList.push(res?.data[flag]);
            }
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
    collect() {
      let USER = JSON.parse(sessionStorage.getItem("user"));
      UID = USER.id;
      this.addCollForm.userId = UID;
      this.addCollForm.titleId = this.Id;
      request.post("/myCollection/addToMyCollection", this.addCollForm).then((res) => {
        console.log(res);
        if (res.code == "0") {
          this.$message({
            type: "success",
            message: "收藏成功",
            showClose: true,
            center: true,
          });
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

    handleOpen() {
      let str = sessionStorage.getItem("user" || "{}");
      let str1 = this.$cookies.get("professor");
      if (str == null && str1 == null) {
        this.$message({
          type: "warning",
          message: "请先登录后再回复",
          showClose: true,
          center: true,
        });
      }
      if (str == null && str1 != null) {
        this.dialogVisible1 = true;
      }
      if (str != null && str1 == null) {
        this.$message({
          type: "warning",
          message: "普通用户不能回复！",
          showClose: true,
          center: true,
        });
      }
    },
    upload() {
      let str1 = this.$cookies.get("professor");
      let date = new Date();
      this.form.professorId = str1.id;
      this.form.questionId = this.Id;
      this.form.time = date;
      this.form.professorName = str1.name;
      request.post("/consultReply/addRep", this.form).then((res) => {
        console.log(res);
        if (res.code == "0") {
          this.$message({
            type: "success",
            message: "回复成功！",
            showClose: true,
            center: true,
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
            showClose: true,
            center: true,
          });
        }
      });
      this.dialogVisible1 = false;
      
    },
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
.el-divider--horizontal {
  margin: 10px 0;
}
</style>
