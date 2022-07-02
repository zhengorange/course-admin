import request from '@/server/network'

export const getCourse = (data) => request.get("/course", data)
export const saveCourse = (data) => request.post("/course", data)
export const deleteCourse = (id) => request.delete("/course/" + id)

export const teacherCourse = () => request.get("/course/teacherCourse")
export const getGrade = (data) => request.get("/select/grade", data)
export const updateGrade = (id, grade) => request.put(`/select/grade/${id}/${grade}`)