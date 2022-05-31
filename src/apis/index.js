import request from '@/server/network'

export const login = (data) => request.post("/users/login", data)
export const register = (data) => request.post("/users/register", data)

export const allClass = () => request.get("/class/all")
export const getClassByName = (data) => request.get("/class", data)
export const saveClass = (data) => request.post("/class", data)
export const deleteClass = (id) => request.delete("/class/" + id)