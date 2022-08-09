<template>
  <div class="home">
    <div>
      <div style="display: inline">
        <el-input
          style="margin: 10px 20px 10px 0; width: 20%"
          v-model="search"
          placeholder="请输入关键字"
          clearable
          size="large"
        />
        <el-button
          type="primary"
          @click="load"
          round
          size="large"
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
          >新增医药专家</el-button
        >
      </div>
    </div>

    <!-- 表格 -->
    <div>
      <el-table :data="tableData" stripe :cell-style="{ height: '70px' }">
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="name" label="专家名" />

        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="department" label="专家科室" />
        <!-- 操作 -->
        <el-table-column label="操作" width="300px" align="center">
          <template #default="scope">
            <el-button
              round
              style="width: 100px"
              type="success"
              @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-popconfirm
              title="确定删除？"
              @confirm="handleDelete(scope.$index, scope.row.id)"
            >
              <template #reference>
                <el-button round style="width: 100px" type="danger"
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
    <!-- 弹窗 -->
    <el-dialog v-model="dialogVisible" title="专家新增" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="用户名" style="width: 80%">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <!-- <el-form-item label="昵称" style="width: 80%">
          <el-input v-model="form.password"></el-input>
        </el-form-item> -->
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
          <el-input v-model="form.department" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button round style="width: 100px" @click="dialogVisible = false"
            >Cancel</el-button
          >
          <el-button round style="width: 100px" type="primary" @click="save"
            >确定</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import Table from "@/components/Table.vue";
import request from "@/utils/request.js";
export default {
  name: "Professor",
  components: {
    Table,
  },
  data() {
    return {
      form: {},
      search: "",
      tableData: [],
      dialogVisible: false,
      currentPage4: 1,
      pageSize: 5,
      total: 22,
    };
  },
  created() {
    this.load();
  },
  methods: {
    add() {
      this.dialogVisible = true;
      this.form = {};
    },
    save() {
      if (this.form.id) {
        console.log(this.form);
        request.put("/professor/edit", this.form).then((result) => {
          console.log(result);
          if (result.code == "0") {
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
          this.load();
          this.dialogVisible = false;
        });
      } else {
        request.post("/professor", this.form).then((res) => {
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
        this.load();
        this.dialogVisible = false;
      }
    },
    load() {
      request
        .get("/professor", {
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            search: this.search,
          },
        })
        .then((result) => {
          console.log(result);
          this.tableData = result.data.records;
          this.total = result.data.total;
        })
        .catch((err) => {});
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
      request.delete("/professor/" + id).then((res) => {
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
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
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
</style>
