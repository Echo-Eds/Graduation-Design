<template>
  <div>
    <Header></Header>
    <div style="display: flex">
      <Aside></Aside>
      <router-view class="router-v"> </router-view>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
import Table from "../components/Table.vue"
export default {
  name: "Layout",
  components: {
    Header,
    Aside,
    Table
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
/* .router-v {
  flex: 1;
  position: relative;

} */
</style>
