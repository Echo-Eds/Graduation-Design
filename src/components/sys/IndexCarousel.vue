<template>
<!-- 首页走马灯 -->
    <div class="carousel">
      <!-- 触发器点击切换，位置在走马灯外部 -->
      <el-carousel trigger="click" height="420px" indicator-position="outside">
        <el-carousel-item v-for="(img, item) in imgList1" :key="item">
          <img v-bind:src="img.url" />
        </el-carousel-item>
      </el-carousel>
    </div>
</template>

<script>
export default {
  name:'IndexCarousel',
  data(){
   return {
       imgList1: [
        { url: require("../../assets/img/home/1620782498440.jpg") },
        { url: require("../../assets/img/home/picture2.png") },
        { url: require("../../assets/img/home/picture3.png") },
      ],
   }
  },
  methods: {
    
  }
}
</script>

<style scoped>
/* 走马灯图片顶满 */
.carousel img {
  width: 100%;
  height: 100%;
}
</style>
