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
          size="large"
          round
          style="width: 150px; margin: 15px 15px"
          type="primary"
          @click="load"
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
          >新增药品资源</el-button
        >
      </div>
    </div>

    <!-- 表格 -->
    <div>
      <el-table :data="tableData" stripe :row-style="{ height: '70px' }">
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="name" label="资源名" />
        <el-table-column prop="type" label="类型" />
        <el-table-column prop="uploadTime" label="上传日期" />
        <el-table-column prop="filePath" label="资源">
          <template #default="scope">
            <el-button type="text" @click="download(scope.row.filePath)"
              >下载资源</el-button
            >
          </template>
        </el-table-column>
        <!-- 操作 -->
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              type="success"
              round
              style="width: 100px"
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
    <el-dialog v-model="dialogVisible" title="资源新增" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="资源名：" style="width: 80%">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="类型：" style="width: 80%">
          <el-input v-model="form.type"></el-input>
        </el-form-item>
        <el-form-item label="资源：">
          <el-upload
            action="http://localhost:9090/files/upload"
            show-file-list
            :on-success="fileUploadSuccess"
            ref="upload"
          >
            <el-button type="text">上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="上传日期：" style="width: 80%">
          <el-date-picker
            v-model="form.uploadTime"
            value-format="YYYY-MM-DD"
            type="date"
            style="width: 100%"
            clearable
          ></el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false" round style="width: 100px">Cancel</el-button>
          <el-button type="primary" @click="save" round style="width: 100px">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import Table from "@/components/Table.vue";
import request from "@/utils/request.js";
export default {
  name: "MedicineResource",
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
      this.$ref["upload"].clearFiles;
    },
    save() {
      if (this.form.id) {
        console.log(this.form);
        request.put("/medicineResource/edit", this.form).then((result) => {
          console.log(result);
          if (result.code == "0") {
            this.$message({
              type: "success",
              message: "更新成功",
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
        request.post("/medicineResource", this.form).then((res) => {
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
        .get("/medicineResource", {
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
      request.delete("/medicineResource/" + id).then((res) => {
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
    fileUploadSuccess(res) {
      console.log(res);
      this.form.filePath = res.data;
    },
    download(filePath) {
      console.log(filePath);
      window.open(filePath);
    },
  },
};
</script>

<style scoped>
.home {
  padding: 10px;
  /*float: right;*/
  width: calc(100% - 200px);
}
</style>
