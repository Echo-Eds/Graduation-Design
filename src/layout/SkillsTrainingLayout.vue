<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <SkillsSearch></SkillsSearch>
  <el-row>
    <el-col :span="4"></el-col>
    <el-col :span="4">
      <DetialsAside></DetialsAside>
    </el-col>
    <el-col :span="16">
      <router-view></router-view>
    </el-col>
  </el-row>
  <br /><br />
  <SysBottom></SysBottom>
</template>

<script>
import SysHeader from "../components/sys/SysHeader.vue";
import IndexCarousel from "../components/sys/IndexCarousel.vue";
import DetialsAside from "../components/Detials/skillsTrainingAside.vue";
import SysBottom from "../components/sys/SysBottom.vue";
import SkillsSearch from "../components/Detials/skillsTrainingSearch.vue";


export default {
  name: "skillsTraining",
  components: {
    SysHeader,
    IndexCarousel,
    DetialsAside,
    SysBottom,
    SkillsSearch,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
</style>
