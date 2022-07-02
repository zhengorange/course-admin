import request from '@/server/network'

export const getPlan = (data) => request.get("/plan", data)
export const deletePlan = (id) => request.delete("/plan/" + id)
export const savePlan = (data) => request.post("/plan/save", data)

export const allClass = () => request.get("/class/all")
export const allCourse = () => request.get("/course/all")
export const getClassByName = (data) => request.get("/class", data)
export const saveClass = (data) => request.post("/class", data)
export const deleteClass = (id) => request.delete("/class/" + id)