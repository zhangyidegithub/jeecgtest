import api from './index'
import { axios } from '@/utils/request'

/**
 * login func
 * parameter: {
 *     username: '',
 *     password: '',
 *     remember_me: true,
 *     captcha: '12345'
 * }
 * @param parameter
 * @returns {*}
 */
export function login(parameter) {
  return axios({
    url: '/sys/login',
    method: 'post',
    data: parameter
  })
}

export function getSmsCaptcha(parameter) {
  return axios({
    url: api.SendSms,
    method: 'post',
    data: parameter
  })
}

export function getInfo() {
  return axios({
    url: '/api/user/info',
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

export function logout(logoutToken) {
  return axios({
    url: '/sys/logout',
    method: 'post',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
      'X-Access-Token':  logoutToken
    },
  })
}


export function redFpCylindrical(logoutToken,parameter) {
  console.log(parameter)
  return axios({
    url: '/com/aisino/taxInvoice/report',
    method: 'post',
    headers: {
      'Content-Type':'application/json;charset=UTF-8',
      'X-Access-Token':  logoutToken
    },
    data: parameter
  })
}

export function redFpStatisticsMoney(logoutToken,parameter) {
  console.log(parameter)
  return axios({
    url: '/com/aisino/taxInvoice/amount',
    method: 'post',
    headers: {
      'Content-Type':'application/json;charset=UTF-8',
      'X-Access-Token':  logoutToken
    },
    data: parameter
  })
}

export function redFpStatisticsCount(logoutToken,parameter) {
  console.log(logoutToken);
  return axios({
    url: '/com/aisino/taxInvoice/number',
    method: 'post',
    headers: {
      'Content-Type':'application/json;charset=UTF-8',
      'X-Access-Token':  logoutToken
    },
    data: parameter
  })
}