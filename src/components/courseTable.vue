<template>
  <div>
    <div class="main">
      <div class="title">时间 \ 星期</div>
      <div class="title">周一</div>
      <div class="title">周二</div>
      <div class="title">周三</div>
      <div class="title">周四</div>
      <div class="title">周五</div>
      <div class="title">周六</div>
      <div class="title">周日</div>
    </div>
    <div class="items">
      <div class="item">上午第一节</div>
      <div class="item">上午第二节</div>
      <div class="item">下午第一节</div>
      <div class="item">下午第二节</div>
      <div class="item">晚上第一节</div>
      <div
        v-for="item in 35"
        :key="item"
        class="item"
        :class="{ item_active: getCourse(item) }"
      >
        {{ getCourse(item) }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    course: {
      type: Array,
      default: [],
    },
    displayProperty: {
      type: Array,
      default: () => {
        return ["name"];
      },
    },
  },
  methods: {
    getCourse(time) {
      let item = this.course.find((e) => {
        return e["time"] == time - 1;
      });
      if (!item) {
        return "";
      } else {
        let s = "";
        for (let i = 0; i < this.displayProperty.length; i++) {
          s +=
            i + 1 == this.displayProperty.length
              ? item[this.displayProperty[i]] + " "
              : item[this.displayProperty[i]];
        }
        return s;
      }
    },
  },
};
</script>

<style lang="less" scoped>
.main {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
  gap: 2px;
  margin-bottom: 2px;
  .title {
    background: rgba(117, 117, 117, 0.5);
    padding: 10px;
    font-weight: bold;
    font-size: 1.1em;
    text-align: center;
  }
}
.items {
  display: grid;
  grid-template-rows: auto auto auto auto auto;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
  grid-auto-flow: column;
  gap: 2px;
  .item {
    background: #40a0ffa9;
    font-size: 1.05em;
    color: white;
    min-height: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    padding: 8px;
  }
  .item_active {
    background: #40a0ff;
    transition: all 0.3s;
    animation-name: enter;
    animation-duration: 1.2s;
    &:hover {
      transform: scale(1.05);
      box-shadow: 1px 1px 5px 1px rgba(128, 128, 128, 0.37);
    }
    @keyframes enter {
      0% {
        background: #40a0ffa9;
        filter: blur(10px);
        transform: scale(1.2);
      }
    }
  }
}
</style>