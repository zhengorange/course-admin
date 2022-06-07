import request from '@/server/network'

export const getCourse = (data) => request.get("/course", data)
export const saveCourse = (data) => request.post("/course", data)
export const deleteCourse = (id) => request.delete("/course/" + id)