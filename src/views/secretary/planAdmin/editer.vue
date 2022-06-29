<template>
  <el-drawer :title="title" :visible.sync="drawer" direction="rtl" :size="500">
    <div class="form_area">
      <el-form
        :model="form"
        size="small"
        label-position="right"
        label-width="100px"
      >
        <el-form-item label="培养方案名称">
          <el-input
            v-model="form.name"
            placeholder="请输入培养方案名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="专业 / 班级">
          <el-select v-model="form.classId" placeholder="请选择专业 / 班级">
            <el-option
              v-for="item in classes"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选修课学分">
          <el-input-number
            v-model="form.electives"
            placeholder="请输入选修课学分"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="必修课内容">
          <el-select v-model="required" multiple placeholder="请选择">
            <el-option
              v-for="item in courses"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选修课内容">
          <el-select v-model="elective" multiple placeholder="请选择">
            <el-option
              v-for="item in courses"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
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
import { savePlan, allClass, allCourse } from "@/apis/secretary";
export default {
  data() {
    return {
      classes: [],
      courses: [],
      form: {},
      elective: [],
      required: [],

      drawer: false,
      title: "",
      loading: false,
    };
  },
  created() {
    allClass().then((res) => {
      this.classes = res.data;
    });
    allCourse().then((res) => {
      this.courses = res.data;
    });
  },
  methods: {
    add() {
      this.title = "添加培养方案";
      this.form = {
        name: "",
        classId: undefined,
        electives: undefined,
      };
      this.electives = [];
      this.required = [];
      this.drawer = true;
    },
    edit(obj) {
      this.title = "编辑培养方案";
      this.form = obj;
      let elective = [];
      let required = [];
      obj.trainingItems.forEach((e) => {
        if (e.required) {
          required.push(e.courseId);
        } else {
          elective.push(e.courseId);
        }
      });
      this.elective = elective;
      this.required = required;
      this.drawer = true;
    },
    submit() {
      for (let i = 0; i < this.elective.length; i++) {
        for (let j = 0; j < this.required.length; j++) {
          if (this.elective[i] == this.required[j]) {
            this.$message.error({ message: "必修课和选修课有重复部分" });
            return;
          }
        }
      }
      if (!this.form.name || !this.form.classId || !this.form.electives) {
        this.$message.error({ message: "表单未填写完整" });
        return;
      }
      this.loading = true;
      let items = [];
      this.elective.forEach((e) => {
        items.push({
          courseId: e,
          required: 0,
        });
      });
      this.required.forEach((e) => {
        items.push({
          courseId: e,
          required: 1,
        });
      });
      savePlan({
        ...this.form,
        trainingItems: items,
      })
        .then(() => {
          this.$notify({ type: "success", message: "操作成功" });
          this.$emit("getList");
          this.loading = false;
          this.drawer = false;
        })
        .catch((e) => {
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