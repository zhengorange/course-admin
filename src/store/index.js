import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isAuthenLoading: false,
  },
  getters: {
  },
  mutations: {
    setAuthenLoading(state, loading){
      state.isAuthenLoading = loading
    },
  },
  actions: {
  },
  modules: {
  }
})
