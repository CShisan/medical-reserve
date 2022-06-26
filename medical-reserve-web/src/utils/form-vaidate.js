import * as validate from '@/utils/validate'

/**
 * 表单校验错误提示映射
 */
const tips = {
  'phone': '请输入正确的手机号码',
  'charge': '请输入正确的挂号费用'
}

/**
 * ELement-ui 自定义表单校验
 *
 * @param {Object} rule
 * @param {String} value
 * @param {Function} callback
 */
export function validFormData(rule, value, callback) {
  const { field } = rule
  const key = 'valid' + field.slice(0, 1).toUpperCase() + field.slice(1)
  const status = validate[key](value)
  const reCallback = status ? () => callback() : () => callback(new Error(tips[field]))
  reCallback()
}
