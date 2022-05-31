<template>
  <div>
    <el-form :inline="true" :model="form" size="small">
      <el-form-item label="班级名称">
        <el-input v-model="form.name" placeholder="请输入班级名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="clear">重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="add">添加班级</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData" border :stripe="true">
      <el-table-column prop="id" label="班级编号" align="center">
      </el-table-column>
      <el-table-column prop="name" label="班级名称" align="center">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="140" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="edit(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="text"
            style="color: red"
            @click="dele(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-if="total > 0"
      style="margin-top: 6px; text-align: right"
      :hide-on-single-page="false"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
    <Editer ref="editer" @getList="getList" />
  </div>
</template>

<script>
import { getClassByName, deleteClass } from "@/apis";
import Editer from "./editer.vue";
export default {
  components: {
    Editer,
  },
  data() {
    return {
      tableData: [],
      form: {
        name: "",
      },
      total: 0,
      currentPage: 1,
      pageSize: 10,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleSizeChange(e) {
      this.pageSize = e;
      this.getList();
    },
    handleCurrentChange(e) {
      this.currentPage = e;
      this.getList();
    },
    getList() {
      getClassByName({
        ...this.form,
        current: this.currentPage,
        size: this.pageSize,
      }).then((res) => {
        console.log(res);
        this.total = res.data.total;
        this.tableData = res.data.records;
      });
    },
    onSubmit() {
      this.getList();
    },
    clear() {
      this.currentPage = 1;
      this.pageSize = 10;
      this.form = { name: "" };
      this.getList();
    },
    add() {
      this.$refs.editer.add();
    },
    edit(e) {
      this.$refs.editer.edit({ ...e });
    },
    dele(e) {
      this.$confirm("此操作将永久删除该班级, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteClass(e.id).then((res) => {
          this.getList();
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        });
      });
    },
  },
};
</script>

<style>
</style>