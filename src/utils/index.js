const getToken = () => {
  let token = localStorage.getItem("token")
  if (token) {
    return token
  }
  token = sessionStorage.getItem("token")
  if (token) {
    return token
  }
  return ''
}
const getCourseTime = (value) => {
  const day = `星期${["一", "二", "三", "四", "五", "六", "日"][parseInt(value / 5)]}`;
  let i = value % 5;
  const time = `${i < 2 ? "上午" : i < 4 ? "下午" : "晚上"}第${(i % 2) + 1}节`;
  return `${day} ${time}`
}
export {
  getToken, getCourseTime
}