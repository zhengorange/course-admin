<template>
  <div class="form">
    <div class="title">教务系统</div>
    <el-form :model="form" status-icon :rules="rules" ref="ruleForm">
      <el-form-item prop="id">
        <el-input
          v-model="form.id"
          :placeholder="idPlaceholder"
          prefix-icon="el-icon-user"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="form.password"
          placeholder="密码"
          prefix-icon="el-icon-lock"
        ></el-input>
      </el-form-item>
      <el-form-item prop="type" label="用户类型：" label-width="50px;">
        <el-select
          v-model="form.type"
          placeholder="请选择用户类型"
          style="width: 80%"
        >
          <el-option label="学生" :value="1"></el-option>
          <el-option label="老师" :value="2"></el-option>
          <el-option label="教学秘书" :value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="item">
        <el-checkbox v-model="localStore">保持我的登陆状态</el-checkbox>
        <span style="margin-left: 120px">没有账号？</span>
        <el-button type="text" @click="toRegister()">点击注册</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()" :loading="loading">登录</el-button>
        <el-button @click="resetForm()">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { login } from "@/apis";
import md5 from 'js-md5'
export default {
  data() {
    return {
      form: {
        id: "",
        password: "",
        type: 1,
      },
      rules: {
        id: [{ required: true, message: "此项不能为空", trigger: "blur" }],
        password: [
          { required: true, message: "此项不能为空", trigger: "blur" },
        ],
      },
      loading: false,
      localStore: false,
    };
  },
  computed: {
    idPlaceholder() {
      return ["学号", "老师编号", "教学秘书编号"][this.form.type - 1];
    },
  },
  methods: {
    submitForm() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store.commit("setAuthenLoading", true);
          login({
            ...this.form,
            password: md5(this.form.password),
          })
            .then((res) => {
              this.$notify({
                title: "操作提示",
                message: res.msg,
                type: "success",
              });

              sessionStorage.clear();
              localStorage.clear();
              if (this.localStore) {
                localStorage.setItem("token", res.data.token);
                localStorage.setItem("type", this.form.type)
                localStorage.setItem("user", JSON.stringify(res.data.user))
              } else {
                sessionStorage.setItem("token", res.data.token);
                sessionStorage.setItem("type", this.form.type)
                sessionStorage.setItem("user", JSON.stringify(res.data.user))
              }
              this.$router.push({
                name: "welcome",
              });
            })
            .finally(() => {
              this.loading = false;
              this.$store.commit("setAuthenLoading", false);
            });
        }
      });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
    toRegister() {
      this.$router.push({ path: "/register" });
    },
  },
};
</script>

<style lang="less" scoped>
.title {
  font-size: 2em;
  margin-bottom: 1em;
}
.form {
  width: 420px;
  padding: 30px;
  padding-bottom: 15px;
  background-color: #f8f8f8e0;
  border-radius: 12px;
  text-align: center;
  box-shadow: 2px 1px 12px 2px rgba(224, 224, 224, 0.466);
}
.item {
  text-align: left;
  margin-left: 10px;
}
</style>