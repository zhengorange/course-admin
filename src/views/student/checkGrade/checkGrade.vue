<template>
  <div>
    <el-table :data="tableData" border v-loading="loading">
      <el-table-column prop="courseId" label="课程编号" align="center">
      </el-table-column>
      <el-table-column prop="courseName" label="课程名称" align="center">
      </el-table-column>
      <el-table-column prop="grade" label="成绩" align="center">
        <template slot-scope="scope">
          {{ scope.row.grade ? scope.row.grade: '暂无成绩' }}
        </template>
      </el-table-column>
      <el-empty slot="empty" description="暂无数据"></el-empty>
    </el-table>
  </div>
</template>

<script>
import { allSelectCourse } from "@/apis/student";

export default {
  data() {
    return {
      tableData: [],
      loading: false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      allSelectCourse()
        .then((res) => {
          this.tableData = res.data;
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
};
</script>

<style lang="less" scoped>
</style>