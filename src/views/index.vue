<template>
  <div class="home">
    <!-- 首页搜索 -->
    <div class="home_search">
      <el-input
        style="margin: 10px 10px; width: 20%"
        v-model="Search"
        placeholder="查询专家姓名"
        clearable
        size="large"
      />
      <el-button
        size="large"
        round
        type="primary"
        @click="search"
        style="margin: 10px 10px; width: 120px"
      >
        <el-icon><search /></el-icon>查询</el-button
      >
    </div>
    <div class="content">
      <div class="content_lists">
        <div class="content_box">
          <div class="content_box_info">
            <span>Recommend</span>
            <br />
            <span>专家信息推荐</span>
          </div>
          <!-- 走马灯模块 -->
          <el-carousel type="card" height="200px" v-if="imgList[0]">
            <el-carousel-item v-for="(item, key) in imgList" :key="key">
              <img
                v-bind:src="item.iconPath"
                @click="gotoProfessorInfo(item.professorid)"
                style="max-width: 40%"
              />
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>
    <div class="content">
      <div class="content_lists">
        <div class="content_box">
          <div class="content_box_info">
            <span>Recommend</span>
            <br />
            <span>药品信息推荐</span>
          </div>
          <!-- 走马灯模块 -->
          <!-- v-if = "imgList[0]"就是让第一个数据到达之后再渲染 这样就不会有空白出现了 -->
          <el-carousel type="card" height="200px" v-if="imgList1[0]">
            <el-carousel-item v-for="(item, key) in imgList1" :key="key">
              <img
                v-bind:src="item.iconPath"
                style="max-width: 40%"
                @click="goToMedicineInfo(item.medicineid)"
              />
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>
    <div class="content">
      <div class="content_lists">
        <div class="content_box">
          <div class="content_box_info">
            <span>Recommend</span>
            <br />
            <span>医药资源推荐</span>
          </div>
          <!-- 走马灯模块 -->
          <el-carousel type="card" height="200px" v-if="imgList2[0]">
            <el-carousel-item v-for="(item, key) in imgList2" :key="key">
              <img
                v-bind:src="item.iconPath"
                style="max-width: 40%"
                @click="goToMedicineResource()"
              />
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>
    <br /><br />
  </div>
</template>

<script>
import request from "@/utils/request.js";
import { ref } from "@vue/reactivity";
import vueEvent from "../utils/bus";

const carousel = ref(null);
export default {
  name: "Index",
  data() {
    return {
      Search: "",
      // 这个是走马灯需要用到的数组
      imgList: [],
      imgList1: [],
      imgList2: [],
    };
  },
  mounted() {
    this.load();
  },

  methods: {
    mag() {
      this.$router.push("/Mag/userManage");
    },
    load() {
      request.get("/index/proIconPath").then((res) => {
        // console.log(res);
        this.imgList = res.data.reduce((retArr, next) => {
          return [...retArr, next];
        }, []);
      });
      request.get("/index/medIconPath").then((res) => {
        // console.log(res);
        this.imgList1 = res.data.reduce((retArr, next) => {
          return [...retArr, next];
        }, []);
      });
      request.get("/index/medResourceIconPath").then((res) => {
        // console.log(res);
        this.imgList2 = res.data.reduce((retArr, next) => {
          return [...retArr, next];
        }, []);
        console.log(this.imgList2);
      });
    },
    gotoProfessorInfo(id) {
      this.$router.push({
        path: "/professorDetails/details",
        query: {
          professorID: id,
        },
      });
    },
    search() {
      request
        .get("/professorDetails/getProfessorBySearch", {
          params: {
            search: this.Search,
          },
        })
        .then((res) => {
          console.log(res);
          //总线传值
          vueEvent.emit("data", res);
        });
      this.$router.push("/professorDetails/search");
    },
    goToMedicineInfo(id) {
      this.$router.push({
        path: "/MedicineInfo/details",
        query: {
          medicineId: id,
        },
      });
    },
    goToMedicineResource() {
      this.$router.push("/medicalResource/main");
    },
  },
};
</script>

<style scoped>
@import url("../assets/css/el-input.css");
.home {
  width: 100%;
  /* height: 100vh; */
  /* background-color: rgb(191, 228, 216); */
}

.home_search {
  display: flex;
  justify-content: center;
}

/* 首页推荐css */
.content {
  width: 100%;
  /* height: 100vh; */
  display: flex;
  justify-content: center;
}
.content img {
  width: 100%;
  height: 100%;
}
.content_lists {
  display: flex;
  width: 70%;
}
.content_box {
  width: 100%;
  height: 300px;
  justify-content: center;
}

.content_box_info {
  margin: 10px 0;
  width: 100%;
  border-bottom: 2px solid #9e1068;
  text-align: center;
}
.content_box_info span {
  line-height: 1.5;
  color: #eb2f96;
  font-size: 20px;
}

/* .el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
} */
.el-carousel__item img {
  position: relative;
  left: 30%;
}
</style>
