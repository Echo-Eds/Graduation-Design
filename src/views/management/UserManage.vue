<template>
  <div class="home">
    <div>
      <div style="display: inline">
        <el-input
          style="margin: 10px 20px 10px 0; width: 20%; border-radius: 20px"
          v-model="search"
          placeholder="请输入关键字"
          clearable
          size="large"
          round
        />
        <el-button
          type="primary"
          @click="load"
          size="large"
          round
          style="width: 150px; margin: 15px 15px"
          >查询</el-button
        >
      </div>

      <div style="display: inline">
        <el-button
          type="primary"
          @click="add"
          size="large"
          round
          style="width: 150px; margin: 15px 15px"
          >新增用户</el-button
        >
      </div>
    </div>

    <!-- 表格 -->
    <div>
      <el-table :data="tableData" stripe :row-style="{ height: '70px' }">
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="username" label="用户名" />

        <el-table-column prop="nickName" label="昵称" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="address" label="地址" />
        <!-- 操作 -->
        <el-table-column label="操作" width="300px" align="center">
          <template #default="scope">
            <el-button
              @click="handleEdit(scope.$index, scope.row)"
              type="success"
              round
              style="width: 100px"
              >编辑</el-button
            >
            <el-popconfirm
              title="确定删除？"
              @confirm="handleDelete(scope.$index, scope.row.id)"
            >
              <template #reference>
                <el-button type="danger" round style="width: 100px"
                  >删除</el-button
                ></template
              >
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <div class="demo-pagination-block" style="margin: 10px 0">
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
        <!-- <el-button type = "primary" @click="jump">go</el-button> -->
      </div>
    </div>

    <el-dialog v-model="dialogVisible" title="新增用户" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="用户名" style="width: 80%">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称" style="width: 80%">
          <el-input v-model="form.nickName"></el-input>
        </el-form-item>
        <el-form-item label="年龄" style="width: 80%">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" style="width: 80%">
          <div>
            <el-radio label="男" v-model="form.sex" size="small">男</el-radio>
            <el-radio label="女" v-model="form.sex" size="small">女</el-radio>
            <el-radio label="未知" v-model="form.sex" size="small">未知</el-radio>
          </div>
        </el-form-item>

        <el-form-item label="地址" style="width: 80%">
          <el-input v-model="form.address" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false" round style="width: 100px"
            >Cancel</el-button
          >
          <el-button type="primary" @click="save" round style="width: 100px"
            >确定</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import request from "@/utils/request.js";

export default {
  name: "Home",
  components: {},
  data() {
    return {
      form: {},
      search: "",
      dialogVisible: false,
      currentPage4: 1,
      pageSize: 5,
      total: 23,

      tableData: [],
    };
  },
  //页面完成加载运行
  created() {
    this.load();
  },
  methods: {
    //加载完成后后端接口获取到的数据在前端渲染
    load() {
      request
        .get("/user", {
          //get将后端接口传递的参数返回到前端
          //params在用get请求时可以带参数传到后台，例如输入框查询
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            search: this.search,
          },
        })
        .then((res) => {
          console.log(res);
          this.tableData = res.data.records;
          this.total = res.data.total;
        });
      // clear the value of 'search' erveytimes
      this.search = "";
      //添加后台拦截 只有管理员能进入后台
      let adminJSON = sessionStorage.getItem("admin");
      if (!adminJSON) {
        this.$router.push("/adminlogin");
      }
    },
    //添加信息
    add() {
      this.dialogVisible = true;
      //清空表单域（表单所有对象）
      this.form = {};
    },
    save() {
      if (this.form.id) {
        //  如果有表格中存在id 那就执行修改
        request.put("/user", this.form).then((res) => {
          console.log(res);
          console.log(this.form);
          //修改成功提示信息
          if (res.code == "0") {
            this.$message({
              type: "success",
              message: "修改成功",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
        //重新加载表格并关闭表单
        this.load();
        this.dialogVisible = false;
      } else {
        //不存在id执行新增
        request.post("/user", this.form).then((res) => {
          console.log(res);
          console.log(this.form);
          //新增成功提示信息
          if (res.code == "0") {
            this.$message({
              type: "success",
              message: "新增成功",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
        //重新加载表格并关闭表单
        this.load();
        this.dialogVisible = false;
      }
    },
    handleEdit(index, row) {
      //深拷贝
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },
    //DELETE
    handleDelete(index, id) {
      console.log(id);
      //使用delete方法 把id拼接到user之后，去后端调用delete接口
      request.delete("/user/" + id).then((res) => {
        console.log(res);
        if (res.code == 0) {
          this.$message({
            type: "success",
            message: "delete successfully",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
      this.load();
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
@import url("../../assets/css/el-input.css");
.home {
  padding: 10px;
  /*float: right;*/
  width: calc(100% - 200px);
}

.button {
  margin: 10px;
}
</style>
