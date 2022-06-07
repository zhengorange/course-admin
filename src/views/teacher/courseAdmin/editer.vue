<template>
  <el-drawer :title="title" :visible.sync="drawer" direction="rtl" :size="500">
    <div class="form_area">
      <el-form
        :model="form"
        size="small"
        label-position="right"
        label-width="120px"
      >
        <el-form-item label="课程名称">
          <el-input v-model="form.name" placeholder="请输入课程名称"></el-input>
        </el-form-item>
        <el-form-item label="课程最大学生数">
          <el-input-number
            v-model="form.maxStudent"
            :min="1"
            :max="200"
            label="描述文字"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="上课时间">
          <CourseTimeSelector
            :time="form.time"
            @timeChange="
              ($event) => {
                form.time = $event;
              }
            "
          />
        </el-form-item>
      </el-form>
    </div>
    <div class="but_group">
      <el-button type="primary" size="small" @click="submit" :loading="loading"
        >确认</el-button
      >
      <el-button size="small" @click="drawer = false">取消</el-button>
    </div>
  </el-drawer>
</template>

<script>
import { saveCourse } from "@/apis/teacher";
import CourseTimeSelector from "@/components/courseTimeSelector.vue";
export default {
  components: {
    CourseTimeSelector,
  },
  data() {
    return {
      form: {},
      drawer: false,
      title: "",
      loading: false,

      options: [],
    };
  },
  methods: {
    add() {
      this.title = "添加课程";
      this.form = {
        name: "",
        maxStudent: 10,
        time: 0,
      };
      this.drawer = true;
    },
    edit(obj) {
      this.title = "编辑课程";
      this.form = obj;
      this.drawer = true;
    },
    submit() {
      this.loading = true;
      saveCourse(this.form)
        .then(() => {
          this.$notify({ type: "success", message: "操作成功" });
          this.$emit("getList");
          this.drawer = false;
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
};
</script>

<style lang="less" scoped>
.form_area {
  padding: 10px;
}
.but_group {
  position: absolute;
  text-align: right;
  left: 0;
  right: 0;
  bottom: 0;
  padding: 10px;
  box-shadow: 0 -3px 20px 2px rgba(128, 128, 128, 0.534);
}
</style>