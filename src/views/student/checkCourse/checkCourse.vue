<template>
  <CourseTable :course="course" :displayProperty="['courseName']" />
</template>

<script>
import CourseTable from "@/components/courseTable.vue";
import {
  allSelectCourse
} from "@/apis/student";
export default {
  components: {
    CourseTable
  },
  created() {
    allSelectCourse().then((res) => {
      this.course = res.data.map(e=>{
        return {
            ...e,
            time: e['courseTime']
        }
      })
    });
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