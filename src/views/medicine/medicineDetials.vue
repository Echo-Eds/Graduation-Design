<template>
  <div class="father">
    <div class="children" v-for="item in CardList" :key="item">
      <el-card :body-style="{ padding: '0px' }" class="el-card">
        <img :src="item.iconPath" class="img" />
        <div style="padding: 14px">
          <span>{{ item.name }}</span>
          <div class="bottom">
            <el-button type="text" class="button" @click="goDetail(item.id)"
              >详情</el-button
            >
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import DetialsAside from "../../components/Detials/professprDetialsAside.vue";
import request from "@/utils/request.js";
export default {
  name: "",
  data() {
    return {
      CardList: [],
      img: "",
      profName1: "",
      imgList: [],
      profNameList: [],
    };
  },
  mounted() {
      request.get("/medicine/getAll").then((res) => {
        console.log(res);  
        let professorNUM = res.data.length;
      //   this.CardList.length = professorNUM;

      for (let flag = 0; flag < professorNUM; flag++) {
        this.CardList.push(res?.data[flag]);
      }
      })
  },
  methods: {
    goDetail(id) {
      this.$router.push({
        path: '/MedicineInfo/details',
        query: {
          medicineId: id
        }
      })
    }
  }
};
</script>

<style scoped>
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
  /* 元素居中 */
  text-align: center;
  margin: 10px 60px;
  width: 70%;
}
.aside {
  height: calc(50);
}

.img {
  width: 200px;
  height: 200px;
}
</style>
