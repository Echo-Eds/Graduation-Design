<template>
  <div>
    <SysHeader></SysHeader>
    <IndexCarousel></IndexCarousel>
    <router-view></router-view>
    <SysBottom></SysBottom>
  </div>
</template>

<script>
import SysHeader from "../components/sys/SysHeader.vue";
import SysBottom from "../components/sys/SysBottom.vue";
import IndexCarousel  from "../components/sys/IndexCarousel.vue";
export default {
  name: "SysLayout",
  components: {
    SysHeader,
    SysBottom,
    IndexCarousel,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
</style>
