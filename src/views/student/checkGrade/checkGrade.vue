<template>
  <div>
    <div class="name">{{ name }}</div>
    <el-input v-model="name" placeholder="请输入内容"></el-input>
    <div v-if="seen">你可以看到</div>
    <!-- <div>
      <span v-for="item in students" :key="item">{{ item + " " }}</span>
    </div>
    <div @click="add" class="but">添加同学</div> -->

    <!-- <div>选择的时间{{date}}</div>
    <el-date-picker
      v-model="date"
      type="datetimerange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期">
    </el-date-picker> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "你的名字",
      seen: true,
      students: ["张三", "李四", "王二"],

      date: null,
    };
  },
  methods: {
    add() {
      this.students.push(`赵${this.students.length + 1}`);
    },
  },
  computed: {
    value1() {
      return this.name && this.seen;
    },
  },
  watch: {
    name(newVal, oldVal) {
      console.log(newVal);
    },
  },
  // 生命周期函数

};
</script>

<style lang="less" scoped>
.name {
  font-size: 36px;
}
.but {
  cursor: pointer;
  user-select: none;
}
</style>