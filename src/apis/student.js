import request from '@/server/network'

export const addSelectCourse = (data) => request.post("/select", data)
export const deleteSelectCourse = (id) => request.delete("/select/" + id)
export const allSelectCourse = () => request.get("/select/all")
export const ableSelectCourse = () => request.get("/select/ableSelect")