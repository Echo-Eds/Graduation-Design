<template>
  <div class="details">
    <el-card>
      <el-row>
        <div class="pageHeader">
          <el-page-header content="详情：" @click="goBack" />
        </div>
      </el-row>
      <el-divider></el-divider>
      <el-row>
        <el-col :span="12">
          <div><img :src="profIcon" alt="" /></div>
        </el-col>
        <el-col :span="12">
          <div class="info1">
            <span>专家姓名：</span>
            <span>{{ name }}</span>
          </div>
          <div class="info1">
            <span>专家科室：</span>
            <span>{{ department }}</span>
          </div>
          <div class="info1">
            <span>年龄：</span>
            <span>{{ age }}</span>
          </div>
          <div class="info2">
            <span>专家简介：</span>
            <br /><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span>{{ info }}</span>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import SysHeader from "../../components/sys/SysHeader.vue";
import IndexCarousel from "../../components/sys/IndexCarousel.vue";
import SysBottom from "../../components/sys/SysBottom.vue";

import request from "@/utils/request.js";
import { load } from "mime";
export default {
  name: "",
  components: {
    SysHeader,
    IndexCarousel,
    SysBottom,
  },
  data() {
    return {
      id: "",
      profIcon: "",
      name: "test",
      department: "",
      age: "",
      info: "",
    };
  },
  mounted() {
    this.id = this.$route.query.professorID;
    console.log("此页面ID");
    console.log(this.id);
    this.load();
  },
  methods: {
    load() {
      request.get("/professorDetails/selectOne/" + this.id).then((res) => {
        console.log(res);
        this.profIcon = res.data.iconPath;
        this.name = res.data.name;
        this.department = res.data.department;
        this.age = res.data.age;
      });
      request
        .get("/professorDetails/selectDetails", {
          params: {
            id: this.id,
          },
        })
        .then((res) => {
          console.log(res.data.details);
          this.info = res.data.details;
        });
    },
    goBack() {
      this.$router.push("/professorDetails/all")
    }
  },
};
</script>

<style scoped>
.details {
  width: 800px;
  margin: 10px 100px;
}
.details img {
  width: 350px;
  height: 350px;
  border-radius: 20%;
}
.info1 {
  height: 50px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.info2 {
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
