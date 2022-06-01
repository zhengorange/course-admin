<template>
  <el-cascader :options="options" :value="mytime" @change="($event)=>{$emit('timeChange', $event[1])}"></el-cascader>
</template>

<script>
// 此组件可以选择上课时间
// 传入 time 
// 事件 timeChange
export default {
  props: {
    time: {
      type: Number,
      default: 0,
    },
  },
  computed: {
    mytime() {
      return [parseInt(this.time / 5) + 100, this.time];
    }
  },
  data() {
    return {
      options: [],
    };
  },
  created() {
    // 一周7天，一天5节课，上午两节，下午两节，晚上一节
    for (let i = 0; i < 7; i++) {
      let current = [];
      for (let j = 0; j < 5; j++) {
        current.push({
          value: i * 5 + j,
          label: `${j < 2 ? "上午" : j < 4 ? "下午" : "晚上"}第${
            (j % 2) + 1
          }节`,
        });
      }
      this.options.push({
        value: i + 100, // 避免和子选项的value重复
        label: `星期${["一", "二", "三", "四", "五", "六", "日"][i]}`,
        children: current,
      });
    }
  }
};
</script>

<style>
</style>