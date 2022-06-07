import request from '@/server/network'

// export const login = (data) => request.post("/users/login", data)
// export const register = (data) => request.post("/users/register", data)

// export const allClass = () => request.get("/class/all")
// export const getClassByName = (data) => request.get("/class", data)
// export const saveClass = (data) => request.post("/class", data)
// export const deleteClass = (id) => request.delete("/class/" + id)

export const login = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve({
            msg: '登录成功',
            data: {
                token: '123',
                user: {
                    id: 1,
                    gender: 1,
                    name: 'test',
                    className: 'test'
                }
            }
        })
    }, 1000)
})
export const register = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve({
            data: {
                id: 1
            }
        })
    }, 1000)
})

export const allClass = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve({
            data: [
                {
                    id: 1,
                    name: 'test'
                }
            ]
        })
    }, 1000)
})
export const getClassByName = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve({
            data: {
                total: 1,
                records: [
                    {
                        id: 1,
                        name: '专硕'
                    }
                ]
            }
        })
    }, 1000)
})
export const saveClass = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve()
    }, 1000)
})
export const deleteClass = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve()
    }, 1000)
})