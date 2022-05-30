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
export {
  getToken
}