import request from '@/server/network'

export const getCourse = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve({
            data: {
                total: 1,
                records: [
                    {
                        id: 1,
                        name: '计算机网络',
                        maxStudent: 10,
                        time: 12
                    },
                    {
                        id: 2,
                        name: '数据结构',
                        maxStudent: 10,
                        time: 15
                    },
                    {
                        id: 3,
                        name: '计算机组成原理',
                        maxStudent: 10,
                        time: 22
                    }
                ]
            }
        })
    }, 1000)
})
export const saveCourse = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve()
    }, 1000)
})
export const deleteCourse = () => new Promise((resolve, reject)=>{
    setTimeout(()=>{
        resolve()
    }, 1000)
})