<template>
  <CourseTable :course="course" />
</template>

<script>
import CourseTable from "@/components/courseTable.vue";
import { getCourse } from "@/apis/teacher";
export default {
  components: {
    CourseTable,
  },
  created() {
    getCourse({ current: 1, size: 100 }).then(res=>{
      this.course = res.data.records
    })
  },
  data() {
    return {
      course: [],
    };
  },
};
</script>

<style>
</style>