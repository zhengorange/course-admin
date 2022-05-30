import axios from "axios";
import { Message, Notification } from 'element-ui'
import { getToken } from '../utils'

let API_HOST = "";
if (process.env.NODE_ENV === "development") {
  API_HOST = window.location.origin + "/apis";
}
if(process.env.NODE_ENV === "production"){
  API_HOST = window.location.origin + "/api";
}
function baseRequest(
  url,
  method,
  data
) {
  return new Promise((resolve, reject) => {
    axios({
      method,
      url,
      data,
      params: method === 'get' ? data : null,
      headers: {},
    })
      .then((res) => {
        resolve(res);
      })
      .catch((res) => {
        reject(res);
      });
  });
}

axios.interceptors.request.use(
  (config) => {
    config.headers["Content-Type"] = "application/json; charset=utf-8"
    config.headers["token"] = getToken()
    return config;
  },
  (error) => {
    return Promise.reject(error)
  }
);


//请求响应拦截
axios.interceptors.response.use(
  response => {
    switch (response.data.code) {
      case 1000: return response.data  //业务正常
      case 2000: Message.error("授权失效,请重新登录！")
        sessionStorage.clear()
        localStorage.clear()
        setTimeout(() => {
          router.push({
            name: "login"
          })
        }, 800)
        return Promise.reject()
      case 3000: Notification['error']({
        title: '操作反馈',
        message: response.data.msg
      })
        return Promise.reject()
      default: return Promise.reject()
    }
  }
)
const request = {};

['get', 'post', 'put', 'delete', 'patch'].forEach(method => {
  request[method] = (api, data = {}) => baseRequest(API_HOST + api, method, data)
});
export default request