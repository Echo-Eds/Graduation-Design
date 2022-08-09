<template>
  <SysHeader></SysHeader>
  <IndexCarousel></IndexCarousel>
  <el-row>
    <el-col>
      <div class="search">
        <el-input
          style="margin: 10px 10px; width: 20%"
          v-model="Search"
          placeholder="查询信息"
          clearable
          size="large"
          @input="handelInputChange"
        />
        <el-button
          type="primary"
          round
          size="large"
          color="#40a9ff"
          @click="load"
          style="margin: 10px 10px"
        >
          <el-icon><search /></el-icon>查询</el-button
        >
      </div>
    </el-col>
  </el-row>
  <el-divider></el-divider>
  <el-row>
    <el-col :span="6"></el-col>
    <el-col :span="12">
      <el-table
        :data="tableData"
        style="width: 100%"
        :row-style="{ height: '70px' }"
        stripe
        @row-click="go_content"
      >
        <el-table-column prop="time" label="日期" align="center" min-width="25%">
        </el-table-column>
        <el-table-column prop="title" label="标题" align="center" min-width="60%">
        </el-table-column>
      </el-table>
      <br />
      <div
        class="pagination"
        style="margin: 10px 0; display: flex; justify-content: center"
      >
        <el-pagination
          v-model:currentPage="currentPage4"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        >
        </el-pagination>
        <el-button
          type="primary"
          round
          @click="jump"
          size="large"
          style="margin: -4px 50px"
          color="#40a9ff"
          ><el-icon><edit-pen /></el-icon>&nbsp;询问专家</el-button
        >
      </div>
    </el-col>
    <el-col :span="6"></el-col>
  </el-row>
  <br />
  <br />
  <SysBottom></SysBottom>

  <!-- WangEditor -->

  <!-- 隐藏的富文本编辑器，点击才打开 -->
  <el-dialog
    v-model="dialogVisible"
    title="请输入您所需要咨询的内容"
    width="70%"
    draggable
  >
    <el-form :model="form">
      <el-form-item prop="title">
        <el-input placeholder="请输入标题" v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item prop="content">
        <div id="richText"></div>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="upload">Confirm</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import SysHeader from "../../components/sys/SysHeader.vue";
import IndexCarousel from "../../components/sys/IndexCarousel.vue";
import SysBottom from "../../components/sys/SysBottom.vue";

import request from "@/utils/request.js";

import E from "wangeditor";

let editor;
export default {
  name: "consultList",
  components: {
    SysHeader,
    IndexCarousel,
    SysBottom,
  },
  data() {
    return {
      form: {},
      currentPage4: 1,
      pageSize: 5,
      total: 23,
      Search: "",
      tableData: [],
      dialogVisible: false,
    };
  },
  mounted() {
    this.load();
  },
  methods: {
    load() {
      request
        .get("/consult/getList", {
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            search: this.Search,
          },
        })
        .then((res) => {
          console.log(res);
          this.tableData = res.data.records;
          this.total = res.data.total;
        });
    },
    handelInputChange() {
      if (this.Search == "") {
        this.load();
      }
    },
    /**
     * 转到每一条提问的详情界面
     */
    go_content(row) {
      console.log(row.id);
      // let item = row;
      //使用带值转发，比总线传值更方便
      this.$router.push({
        path: "/consult/content",
        query: {
          id: row.id,
        },
      });
    },
    /**
     * 去富文本编辑器界面提问
     * 如果未登录则提示去登录
     */
    jump() {
      this.form = {};
      let str = sessionStorage.getItem("user" || "{}");
      if (str) {
        this.dialogVisible = true;

        this.$nextTick(() => {
          editor = new E("#richText");
          editor.create();
        });
      } else {
        this.$message({
          type: "error",
          message: "请先登录再提问",
        });
      }
    },
    /**
     * 将内容提交到数据库保存
     */
    upload() {
      //当前登录用户存在，就存一个表单发给后台添加数据库
      //用户ID 文章标题 文章内容 时间
      const content = editor.txt.html();
      this.form.content = content;
      let str = JSON.parse(sessionStorage.getItem("user" || "{}"));
      console.log(str.id);
      if (str) {
        this.form.userId = str.id;
        let date = new Date();
        this.form.time = date;
        console.log(this.form);
        request.post("/consult/addContent", this.form).then((res) => {
          if (res.code == "0") {
            this.$message({
              type: "success",
              message: "提问成功，请耐心等待回复",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
          this.dialogVisible = false;
        });
      }
    },

    //数据分页
    handleSizeChange(pageSize) {
      //  每页条数改变时触发

      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      //点击页码时触发

      this.pageNum = pageNum;
      this.load();
    },
  },
};
</script>

<style scoped>
.search {
  display: flex;
  justify-content: center;
}

.main_body {
  width: 100%;

  background-color: rgb(180, 181, 186);
}
.pagination {
  text-align: center;
}
</style>
