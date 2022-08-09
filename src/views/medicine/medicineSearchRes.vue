<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <el-row>
    <el-col :span="4"></el-col>
    <el-col :span="4"><DetialsAside></DetialsAside></el-col>
    <el-col :span="16">
      <div class="pageHeader">
        <el-page-header content="查询结果：" @click="goBack" />
      </div>

      <div class="father">
        <div class="children" v-for="item in CardList" :key="item">
          <el-card :body-style="{ padding: '0px' }" class="el-card">
            <img :src="item.iconPath" class="img" />
            <div style="padding: 14px">
              <span>{{ item.name }}</span>
              <div class="bottom">
                <el-button type="text" class="button" @click="goDetail(item.id)"
                  >咨询</el-button
                >
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import vueEvent from "@/utils/bus";
import SysHeader from "../../components/sys/SysHeader.vue";
import IndexCarousel from "../../components/sys/IndexCarousel.vue";
import DetialsAside from "../../components/Detials/medicalResourceAside.vue";
import SysBottom from "../../components/sys/SysBottom.vue";
export default {
  name: "",
  components: {
    SysHeader,
    IndexCarousel,
    DetialsAside,
    SysBottom,
  },
  data() {
    return {
      professorDepartment: "外科",
      CardList: [],
      img: "",
      profName1: "",
      imgList: [],
      profNameList: [],
    };
  },
  mounted() {
    vueEvent.on("data", (res) => {
        console.log("总线传值为：");
      console.log(res);
      let professorNUM = res.data.length;
      for (let flag = 0; flag < professorNUM; flag++) {
        this.CardList.push(res?.data[flag]);
      }
    });
  },
  beforeUnmount() {
    vueEvent.off("data"); //每次监听前线解绑事件，避免多次传值；
  },

  methods: {
    goBack() {
      this.$router.back();
    },
    goDetail(id) {
      console.log(id);
      this.$router.push({
        path: "/professorDetails/details",
        query: {
          professorID: id,
        },
      });
    },
  },
};
</script>

<style scoped>
.pageHeader {
  margin: 10px 50px;
}
.father {
  display: flex;
  align-items: center;
  /* align-items: flex-start; */
  justify-content: flex-start;
  flex-wrap: wrap;
}

.father > .children {
  height: 300px;
  width: 33%;
}
.el-card {
  text-align: center;
  margin: 20px 50px 50px 50px;
  width: 90%;
}
.aside {
  height: calc(50);
}

.img {
  max-width: 200px;
  height: 200px;
}
</style>
