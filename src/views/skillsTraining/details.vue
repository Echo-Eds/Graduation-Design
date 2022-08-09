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
            <span>培训主题：</span>
            <span>{{ name }}</span>
          </div>
          <div class="info1">
            <span>培训类型：</span>
            <span>{{ type }}</span>
          </div>
          
          <div class="info2">
            <span>培训内容：</span>
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
      type: "",
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
      request
        .get("/skillsTraining/getDetails", {
          params: {
            id : this.id
          },
        })
        .then((res) => {
          console.log(res);
          this.profIcon = res.data.img
          this.info = res.data.trainingDetails
          this.name = res.data.trainingTitle
          this.type = res.data.trainingType
        });
    },
    goBack() {
      this.$router.push("/skills_training/main")
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
